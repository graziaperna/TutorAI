/* 
Implementazione dell'algoritmo Graphplan
*/

:- [planning_graph].
:- [planner_utility].

%costruzione del grafo di pianificazione
graphplan(Level, InitialState, _, _) :-
	planning_graph(Level, InitialState),
	fail.

/*
Algoritmo Graphplan interrotto se l'obiettivo è verificato nell'ultimo livello generato ed è possibile estrarre un piano
Level: ultimo livello di stato generato
InitialState: stato iniziale che si tenterà di raggiungere con ricerca backward
GoalState: obiettivo da soddisfare nel problema di pianificazione
PlanSet: insieme dei piani d'azione trovati composto da piano e costo
*/
graphplan(Level, InitialState, GoalState, PlanSet) :-
	check_predicates(Level, GoalState),
	not_mutex_predicates_set(Level, GoalState),
	assert(plan_id(0)),
	setof([Plan,Cost,PlanId], find_plan(Level, InitialState, GoalState, [], Plan, Cost, PlanId), PlanSet),
	retractall(plan_id(_)),
	!.
	
%se il livello generato è uguale al precedente, allora il grafo è livellato e il processo di ricerca di un piano può essere interrotto
graphplan(Level, _, _, _) :-
	Level > 0,
	PreviousLevel is Level-1,
	findall(Predicate, state_level(Level, Predicate, _, add), State),
	findall(Predicate1, state_level(PreviousLevel, Predicate1, _, add), PreviousState),
	State == PreviousState,
	%se le condizioni sono verificate termina l'esecuzione con fallimento
	!,
	fail.
	
%generazione di un nuovo livello
graphplan(Level, InitialState, GoalState, PlanSet) :-
	NextLevel is Level+1,
	%write('Livello:'), write('--'), write(NextLevel),nl,
	graphplan(NextLevel, InitialState, GoalState, PlanSet).

%ultimo passo della ricerca backward
%ricerca del piano termina con successo se al livello 0 GoalState è un sottoinsieme di InitialState
find_plan(0, InitialState, GoalState, Plan, Plan, 0, NextPlanId) :-
	p_subset(GoalState, InitialState),
	plan_id(PlanId),!,
	NextPlanId is PlanId + 1,
	asserta(plan_id(NextPlanId)).

/*
Ricerca backward del piano
Level: ultimo di livello di stato generato da cui parte la ricerca
InitialState: stato iniziale
GoalState: stato goal che verrà aggiornato durante la ricerca
PartialPlan: piano parziale aggiornato durante la ricerca con il set d'azioni che soddisfano GoalState
Plan: piano finale
Cost: costo totale del piano finale
*/
find_plan(Level, InitialState, GoalState, PartialPlan, Plan, Cost, PlanId) :-
	Level > 0,
	PreviousLevel is Level-1,
	goal_actions(Level, GoalState, ActionSet),
	new_goal(PreviousLevel, ActionSet, NewGoal),
	remove_no_op_actions(ActionSet, NewActionSet),
	list_empty(NewActionSet, false),
	actions_cost(NewActionSet, ActionSetCost),
	%assert(graphplan_states(NewGoal)), %aggiunto per la sperimentazione
	find_plan(PreviousLevel, InitialState, NewGoal, [NewActionSet|PartialPlan], Plan, C1, PlanId),
	Cost is ActionSetCost + C1,
	assert(plan_explanation(PlanId, NewGoal, NewActionSet, GoalState)).

/*
Level: livello di stato
GoalState: obiettivo
ActionSet: insieme d'azioni che soddisfano GoalState
*/
goal_actions(Level, GoalState, ActionSet) :-
	setof(Actions, goal_actions(Level, GoalState, [], Actions), Set),
	p_member(ActionSet, Set).
	
goal_actions(Level, [], ActionList, ActionSet) :-
	PreviousLevel is Level-1,
	sort(ActionList, ActionSet),
	not_mutex_actions_set(PreviousLevel, ActionSet).

%ActionSet è l'insieme di Azioni che soddisfano l'insieme di predicati contenuti in GoalState
goal_actions(Level, GoalState, ActionList, ActionSet) :-
	p_member(Predicate, GoalState),
	state_level(Level, Predicate, Action, add),
	%recupera l'add list dell'azione in modo da controllare se soddisfa altri predicati del goal
	setof(Pred, state_level(Level, Pred, Action, add), AddList),
	%NewGoalState è il nuovo goal contenente i predicati non ancora soddisfatti
	p_subtract(GoalState, AddList, NewGoalState),
	goal_actions(Level, NewGoalState, [Action|ActionList], ActionSet).
	
actions_cost([], 0).

%ActionSetCost: costo totale del set di azioni contenute nella lista
actions_cost([Action|T], ActionSetCost) :-
	plan_operator(Action, _, _, _, Cost),
	actions_cost(T, C1),
	ActionSetCost is Cost + C1.
	
/*
Il nuovo goal da soddisfare è composto dalle precondizioni delle azioni in ActionSet
Level: livello d'azione
ActionSet: insieme d'azioni
NewGoal: insieme di precondizioni delle azioni in ActionSet
*/
new_goal(Level, ActionSet, NewGoal) :-
	findall(Predicate, (p_member(Action, ActionSet), action_level(Level, Action, Predicate)), Preconditions),
	sort(Preconditions, NewGoal).