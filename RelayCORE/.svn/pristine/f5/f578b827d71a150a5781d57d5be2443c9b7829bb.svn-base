%:- module(backward,[backward/1]).
% ---------------------------------------------------------------------------------
% Backward reasoning
% ---------------------------------------------------------------------------------
:- dynamic askable/2.
:- dynamic fact/3.
:- dynamic yet_found/1.
:- dynamic fact_not_found/1.
:- dynamic cur_goal/1.
:- dynamic fact_new/3.
:- dynamic max_id/1.
:- ensure_loaded(command).
:- reconsult('gie/gie_operation').

/* N.B: in esplora/n va messa la assert di cur_goal che viene usato in questo modulo - da capire a cosa serve
esplora(X, Catena, ProbH-rule(Id, X, ProbB-Traccia, Prior, ProbR)) :- % dimostrabile con regola
	rule(Id, X, C, Prior, ProbR),
%	assertz(cur_goal(X)), %ste PRESO DA RISP_UT
	esplora(C, [Id-X|Catena], ProbB-Traccia),
	ProbH is ProbB * ProbR.
*/
	
%% Sezione NW/SW %%
% **** Probabilità tramite necessità (NW) e sufficienza (SW) ****
% nwsw([E1...En], Threshold, Start)
% Ei nella forma [Obiettivo, NW, SW]
% nwsw(*) ha successo se il valore finale è maggiore o uguale a Threshold, in caso contrario il predicato fallisce
% Il valore finale è calcolato moltiplicando, per ogni Ei, il valore iniziale (Start) 
% per NW nel caso in cui il fatto abbia probabilità 0 (o venga asserito con tale probabilità)
% e per SW nel caso di fatto con probabilità 1. Ad ogni iterazione il valore ottenuto dalla precedente
% è quindi il nuovo valore iniziale
% ---------------------------------------------------------------------------------

verificaRispUt(nwsw([H|T], Threshold, StartingValue), Traccia1, Traccia) :-
	verificaRispUtNwSw([H|T], StartingValue, FinalValue, Traccia1, Traccia2),
	append(Traccia2, [FinalValue], Traccia3),
	!,
	verificaThreshold(FinalValue, Threshold, Traccia3, Traccia).

verificaThreshold(FinalValue, Threshold, Traccia1, Traccia) :-
	FinalValue >= Threshold,
	!,
	%Se FinalValue ha superato la soglia, allora possiamo asserire il fatto
	retract(cur_goal(Obiettivo)),
	new_fact(Id, Obiettivo, 1),
	atom_concat('>=', Threshold, Text),
	append(Traccia1, [Text], Traccia).
verificaThreshold(FinalValue, Threshold, Traccia1, Traccia) :-
	retract(cur_goal(Obiettivo)),
	new_fact(Id, Obiettivo, 0),
	atom_concat('<', Threshold, Text),
	append(Traccia1, [Text], Traccia),
	!,
	fail.

%
verificaRispUtNwSw([H|T], StartingValue, FinalValue, Traccia1, Traccia) :-
	check(H, StartingValue, MidValue, Traccia1, Traccia2),
	!,
	verificaRispUtNwSw(T, MidValue, FinalValue, Traccia2, Traccia),
	retract(yet_found(_)),
	assert(yet_found(0)).
verificaRispUtNwSw([], FinalValue, FinalValue, Traccia1, Traccia1).


% Verifico il contenuto di nwsw()

% L'Obiettivo è un fatto già asserito, vero
check([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	fact(_, Obiettivo, 1),
	compute_probability(y, Value, FinalValue, [NW, SW]).

% L'Obiettivo è un fatto già asserito, falso
check([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	fact(_, Obiettivo, 0),
	compute_probability(n, Value, FinalValue, [NW, SW]).


% L'obiettivo è la testa di una regola
% check(Params) rule()
% ---------------------------------------------------------------------------------
% Caso 1: La regola esiste e la verifica delle condizioni ha successo (SW)
% Caso 2: La regola esiste e la verifica delle condizioni fallisce (NW)
% Caso 3: La regola non esiste e si procede alla check successiva
% ---------------------------------------------------------------------------------

check([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	rule(_, Obiettivo, C, _ , P),
	%retract(yet_found(Val)),
	%assert(yet_found(1)),
	append(Traccia1, [Obiettivo], Traccia2),
	!,
	verificaRispUt(Obiettivo, Traccia2, Traccia),
	%controllo che non sia una soluzione già trovata con esplora
	%yet_found(0),
	compute_probability(y, Value, FinalValue, [NW, SW]).
	%check([Obiettivo, NW, SW], Value, FinalValue, Traccia3, Traccia).
check([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	rule(_, Obiettivo, C,  _, P),
	append(Traccia1, [Obiettivo], Traccia),
	compute_probability(n, Value, FinalValue, [NW, SW]).
% L'obiettivo è un askable(Obiettivo, Domanda)
check([Obiettivo, NW, SW], Value, FinalValue, Traccia1, said(Obiettivo,Ris)) :-
	retract(yet_found(Val)),
	assert(yet_found(1)),
	askable(Obiettivo, Domanda),
	write(Domanda),write('? [y,n,w] '),
	read(Ris),
	compute_probability(Ris, Value, MidValue, [NW, SW]),
	!,
	risposta(Ris, [Obiettivo, NW, SW], MidValue, FinalValue, Traccia1, Traccia).


% Evito 'zero_divisor'
compute_probability(_Ris, Value, MidValue, _) :-
	Value = 0.0,
	MidValue is 0.0.
compute_probability(Ris, Value, MidValue, [NW, SW]) :-
	gvalue(Ris, [NW, SW], Multiplier),
	Odds is Value/(1-Value),
	MidValue is Odds*Multiplier/(1+TOdds).
compute_probability(_, Value, Value ,_).

%A seconda della risposta utilizzo SW o NW
gvalue(y, [NW, SW], SW).
gvalue(n, [NW, SW], NW).

%Risposta per NW/SW
risposta(y, [Obiettivo, _NW, _SW], _FinalValue, _FinalValue, Traccia1, _Traccia) :-
	risposta(y, Obiettivo, Traccia1).
risposta(n, [Obiettivo, NW, SW], FinalValue, FinalValue, Traccia1, Traccia) :-
	new_fact(Id, Obiettivo, 0).
risposta(w, [Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	nl,
	presentaCatena(Traccia1),
	nl,
	!,
	chiedi([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia).
risposta(Answer, [Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia) :-
	other_answers(Answer).
risposta(_, [Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia):-
	!,
	write('Risposta non valida. Riprovare'),
	nl,
	chiedi([Obiettivo, NW, SW], Value, FinalValue, Traccia1, Traccia).



