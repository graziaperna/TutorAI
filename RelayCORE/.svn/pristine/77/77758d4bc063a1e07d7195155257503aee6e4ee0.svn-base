/*
Regole usate per generare il grafo di pianificazione
*/

/*
state_level(Level, Predicate, Action, Type)
Level: livello di stato
Predicate: letterale appartenente al livello
Action: Azione che ha come effetto Predicate
Type: add/del indica che Predicate è un effetto dell'add/delete list dell'azione Action
*/
:- dynamic state_level/4.

/*
action_level(Level, Action, Predicate)
Level: livello d'azione
Action: azione appartenente al livello
Predicate: precondizione di Action presente al livello di stato precedente
*/
:- dynamic action_level/3.

/*
mutex_predicates(Level, Predicate1, Predicate2)
Level: livello di stato
Predicate1, Predicate2: coppia di predicati mutex
*/
:- dynamic mutex_predicates/3.

/*
mutex_actions(Level, Action1, Action2)
Level: livello d'azione
Action1, Action2: coppia di azioni mutex
*/
:- dynamic mutex_actions/3.

%livello 0 composto dai letterali di InitialState
planning_graph(0, InitialState) :-
	add_level(0, _, InitialState, add, state).
	
%viene generato un nuovo livello del grafo se viene trovata almeno un'azione applicabile al livello di stato precedente
planning_graph(Level, _) :-
	Level > 0,
	PreviousLevel is Level-1,
	applicable_action(PreviousLevel, Action, Preconditions, AddList, DeleteList),
	add_level(PreviousLevel, Action, Preconditions, _, action),
	add_level(Level, Action, AddList, add, state),
	add_level(Level, Action, DeleteList, del, state).

%aggiunta delle azioni e predicati mutex per il livello generato
planning_graph(Level, _) :-
	Level > 0,
	PreviousLevel is Level-1,
	mutex_actions(PreviousLevel),
	mutex_predicates(Level).

add_level(_, _, [], _, _).

add_level(Level, Action, [H|T], Type, state) :-
	add_state_level(Level, H, Action, Type),
	add_level(Level, Action, T, Type, state).

add_level(Level, Action, [H|T], _, action) :-
	add_action_level(Level, Action, H),
	add_level(Level, Action, T, _, action).
	
%predicato aggiunto al livello di stato se non è già presente
add_state_level(Level, Predicate, Action, Type) :-
	state_level(Level, Predicate, Action, Type),
	!.
	
add_state_level(Level, Predicate, Action, Type) :-
	assert(state_level(Level, Predicate, Action, Type)).
	
%azione aggiunta al livello d'azione se non è già presente	
add_action_level(Level, Action, Predicate) :-
	action_level(Level, Action, Predicate),
	!.
	
add_action_level(Level, Action, Predicate) :-
	assert(action_level(Level, Action, Predicate)).
	
%un'azione può essere applicata se le sue precondizioni sono soddisfatte in un livello e non sono presenti due predicati mutex
applicable_action(Level, Action, Preconditions, AddList, DeleteList) :-
	plan_operator(Action, Preconditions, AddList, DeleteList, _),
	check_predicates(Level, Preconditions),
	not_mutex_predicates_set(Level, Preconditions).

%oppure è possibile applicare l'azione di persistenza
applicable_action(Level, no_op(Predicate), [Predicate], [Predicate], []) :-
	state_level(Level, Predicate, _, add). %dal grafo recupero i predicati dell'add list
	
check_predicates(_, []).

%controlla che tutti i predicati nella lista siano presenti nel livello di stato Level
check_predicates(Level, [Predicate|T]) :-
	state_level(Level, Predicate, _, add),
	check_predicates(Level, T),
	!.
	
not_mutex_predicates_set(_, [_]) :- 
	!.

%recupera tutte le coppie di predicati da PredSet e controlla che non ci sia una coppia mutex al livello di stato Level
not_mutex_predicates_set(Level, PredSet) :-
	setof( 
		(Predicate1, Predicate2),
		(p_member(Predicate1, PredSet), p_member(Predicate2, PredSet), Predicate1 \= Predicate2),
		PairSet
		),
	no_mutex(Level, PairSet, predicates).

not_mutex_actions_set(_, [_]) :-
	!.

%recupera tutte le coppie di azioni da ActionSet e controlla che non ci sia una coppia mutex al livello d'azione Level
not_mutex_actions_set(Level, ActionSet) :-
	setof( 
		(Action1, Action2),
		(p_member(Action1, ActionSet), p_member(Action2, ActionSet), Action1 \= Action2), 
		PairSet
		),
	no_mutex(Level, PairSet, actions).
	
no_mutex(_, [], _).
	
%fallisce quando nell'insieme trova una coppia di predicati mutex
no_mutex(Level, [(Predicate1, Predicate2)|T], predicates) :-
	not(mutex_predicates(Level, Predicate1, Predicate2)),
	no_mutex(Level, T, predicates).

%fallisce quanto nell'insieme trova una coppia di azioni mutex
%utile quando determino un set di azioni che soddisfano un predicato e voglio controllare che in quel set non ce ne siano 2 mutex
no_mutex(Level, [(Action1, Action2)|T], actions) :-
	not(mutex_actions(Level, Action1, Action2)),
	no_mutex(Level, T, actions).

%effetti inconsistenti: un'azione nega l'effetto di un'altra
%interferenza: un'azione cancella una precondizione di un'altra
mutex_actions(Level) :-	
	NextLevel is Level+1,
	state_level(NextLevel, Predicate, Action1, del),
	(
		state_level(NextLevel, Predicate, Action2, add);
		action_level(Level, Action2, Predicate)
	),
	Action1 \= Action2,
	add_mutex(Level, Action1, Action2, actions).

%necessità in competizione: una precondizione di un'azione è mutex con una precondizione di un'altra
mutex_actions(Level) :-
	mutex_predicates(Level, Predicate1, Predicate2),
	action_level(Level, Action1, Predicate1),
	action_level(Level, Action2, Predicate2),
	Action1 \= Action2,
	add_mutex(Level, Action1, Action2, actions).
	
%2 predicati sono mutex se ogni coppia di azioni che permette di ottenerli è mutex: 
%cioè non esistono azioni non-mutex fra di loro con cui posso ottenerli
mutex_predicates(Level) :-
	PreviousLevel is Level-1,
	mutex_actions(PreviousLevel, Action1, Action2), %prende 2 azioni mutex nel livello di azione precedente
	state_level(Level, Predicate1, Action1, add), %prende il predicato ottenuto dalla prima azione
	state_level(Level, Predicate2, Action2, add), %prende il predicato ottenuto dalla seconda azione
	Predicate1 \= Predicate2, %predicati diversi
	not(mutex_predicates(Level, Predicate1, Predicate2)), %controlla che non siano già stati identificati come mutex
	not((
		state_level(Level, Predicate1, A1, add), %controlla che non esistano altre azioni non-mutex che permettano di ottenere 
		state_level(Level, Predicate2, A2, add), %quei 2 predicati
		not(mutex_actions(PreviousLevel, A1, A2))
		)),
	add_mutex(Level, Predicate1, Predicate2, predicates).

add_mutex(Level, Action1, Action2, actions) :-
	not(mutex_actions(Level, Action1, Action2)),
	assert(mutex_actions(Level, Action1, Action2)),
	assert(mutex_actions(Level, Action2, Action1)).
	
add_mutex(Level, Predicate1, Predicate2, predicates) :-
	not(mutex_predicates(Level, Predicate1, Predicate2)),
	assert(mutex_predicates(Level, Predicate1, Predicate2)),
	assert(mutex_predicates(Level, Predicate2, Predicate1)).