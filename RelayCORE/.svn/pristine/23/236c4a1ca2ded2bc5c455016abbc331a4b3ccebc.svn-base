%:- module(backward,[backward/1]).
% ---------------------------------------------------------------------------------
% Backward reasoning
% ---------------------------------------------------------------------------------
:- dynamic askable/2.

:- dynamic yet_found/1.
:- dynamic fact_not_found/1.
:- dynamic cur_goal/1.

:- ensure_loaded(command).
:- reconsult('gie/gie_operation').

%
get_kb(KB) :-
	curr_kb(KB),
	!.
get_kb(test_run).

relay_flag(prob0,fail).
:- [appl], assert(max_id(333)).
%

fact_not_found(0). %è 1 se il fatto non è stato trovato
yet_found(1).	   %è 1 se è la soluzione è stata già trovata
	 
backward(Obiettivo) :-
%ste da qui preso da ex risposta_utente
	get_kb(KB),
	open(KB,write,Stream),
	write(Stream,'%Asked facts'),  nl(Stream),
	close(Stream),
	!,
%ste fin qui preso da ex risposta_utente
	esplora(Obiettivo, [], Traccia),
	present(Obiettivo,Traccia),
	nl,
	write('Altre soluzioni? [y, n] '),
	read(Ris),
	altreRisposte(Ris).


/* esplora(+O,+C,-T) :
  Dimostra in backward nella base di conoscenza l'obiettivo O,
  derivato dalla catena di obiettivi C, restituendo il tracciato del ragionamento T
*/
esplora(L, Catena, Prob-and(Traccia)) :- % lista = congiunzione - DEPRECATA
	is_list(L),
	!,
	esplora_and(L, Catena, Traccia, Prob).
esplora(call_p(S), _Catena, 1-call_p(S)) :- % generalizzare a qualunque eseguibile (command e operation)
	!,
	call_p(S).
esplora(and(L), Catena, Prob-and(Traccia)) :- % congiunzione
	!,
	esplora_and(L, Catena, Traccia, Prob).
esplora(or(L), Catena, Prob-or(Traccia)) :- % disgiunzione
	!,
	esplora_or(L, Catena, Traccia, Prob).
esplora(no(X), Catena, Prob-no(Traccia)) :- % negazione
	!,
	retract(fact_not_found(_)),
	assert(fact_not_found(1)),
	esplora_no(X, Catena, Traccia, Prob),
	fact_not_found(0).
esplora(X, _Catena, 0-fact(Id, X, 0)) :- % fatto falso
	fact(Id, X, 0),
	retract(fact_not_found(_)),
	assert(fact_not_found(0)),
	!,
	fail.
esplora(X, _Catena, Prob-fact(Id, X, Prob)):- % dimostrabile con fatto
	fact(Id, X, Prob),
	retract(fact_not_found(_)),
	assert(fact_not_found(0)).
esplora(X, Catena, ProbH-rule(Id, X, ProbB-Traccia, Prior, ProbR)) :- % dimostrabile con regola
	rule(Id, X, C, Prior, ProbR),
%	assertz(cur_goal(X)), %ste PRESO DA RISP_UT
	esplora(C, [Id-X|Catena], ProbB-Traccia),
	ProbH is ProbB * ProbR.
	%ste asserire la conclusione?
esplora(Obiettivo, Catena, Answer) :-
	user_answer(Obiettivo, Catena, Answer).

user_answer(Obiettivo, Catena, Cert-known(Id,Obiettivo,Cert)) :-
	fact(Id,Obiettivo,Cert). % potrebbe essere stato asserito dopo esplora/fact
user_answer(Obiettivo, Catena, Certezza-said(Obiettivo,Ris)) :-
	\+ fact(_Id,Obiettivo,_Cert),
	askable(Obiettivo, Domanda),
	repeat,
	write(Domanda),write('? [y,n,w] '),
	read(Ris),
	risposta(Ris, Obiettivo, Catena, Certezza),
%	( Ris == y ; Ris == n ), %ste generalizzare
	!,
%	Ris == y, %ste e per tutte le altre risposte fallisce?!
	retract(fact_not_found(_)),
	assert(fact_not_found(0)).


esplora_and([], _Catena, [], 1.0).
esplora_and([H|T], Catena, [ProbH-TracciaH|TracciaT], Prob) :-
	esplora(H, Catena, ProbH-TracciaH),
	esplora_and(T, Catena, TracciaT, ProbT),
	Prob is min(ProbH,ProbT).

esplora_or([H|_T], Catena, Prob-Traccia, Prob) :-
	esplora(H, Catena, Prob-Traccia).
esplora_or([_|T], Catena, Traccia, Prob) :-
	esplora_or(T, Catena, Traccia, Prob).
%ste oppure valutare tutti e Prob is max(ProbH,ProbT)

esplora_no(X, Traccia1, Prob-Traccia, ProbNeg) :- % X è vero
	esplora(X, Traccia1, Prob-Traccia),
	!,
	Prob < 1,
	ProbNeg is 1 - Prob.
esplora_no(X, _Traccia1, failed(X), 1.0). % X è falso


risposta(Risposta, _Obiettivo, Catena, _Certezza) :-
	certainty(Risposta,why),
	write('This is a question '),
	presentaCatena(Catena),
	nl,
	!,
	fail.
risposta(Risposta, Obiettivo, Catena, Certezza) :-
	certainty(Risposta,Certezza),
	new_fact(_Id, Obiettivo, Certezza).
risposta(Answer, _Obiettivo, _Catena, _Certezza) :-
	other_answers(Answer).
	
certainty(yes,1.0).
certainty(y,1.0).
certainty(sure,1.0).
certainty(yeah,1.0).
certainty(ye,1.0).
certainty(aha,1.0).
certainty(huhu,1.0).
certainty(ok,1.0).
certainty(no,0.0).
certainty(n,0.0).
certainty(not,0.0).
certainty(never,0.0).
certainty(impossible,0.0).
certainty(haha,0.0).
certainty(why,why).
certainty(w,why).
certainty(explain,why).


new_fact(NewIdFact, Obiettivo, Probability) :-
	retract(max_id(Max)),
	NewIdFact is Max + 1,
	NewFact = fact(NewIdFact, Obiettivo, Probability),
	assertz(NewFact),
	assertz(max_id(NewIdFact)),
%append_fact_to_kb(NewFact) :-
	get_kb(KB),
	open(KB,append,Stream),
	write(Stream,NewFact), nl(Stream),
	close(Stream).


presentaCatena([]) :-
	writeln('which was your initial goal.'),nl.
presentaCatena([Id-Goal|RestGoals]):-
	write('which is needed to prove, by rule '),writeln(Id),
	write('\t'),writeln(Goal),
	presentaCatena(RestGoals).

present(Goal,Prob-Trace) :-
	repeat,
	write(Goal),write(' with certainty '),write(Prob),nl,
	write('Vuoi sapere come? [y, n] ' ),
	read(Answer),
	rispostaCome(Answer, Trace),
	( Answer == y ; Answer == n ),
	!.
	
rispostaCome(y, Traccia) :-
	nl,
	browse_trace(Traccia),
	!.																		
rispostaCome(n, _Traccia):-
	!.																		
rispostaCome(Answer, _Traccia) :- %aggiunto da Giuliana Spinelli
	other_answers(Answer).


altreRisposte(y) :-
	!,
	fail.
altreRisposte(n).
altreRisposte(Answer) :-
	other_answers(Answer).


other_answers(Answer) :-
	command(Answer),
	!.
other_answers(_):-
	writeln('Comando non valido!'),
	!,
	fail.


browse_trace(T) :- %ste da implementare, con navigazione
	writeln(T),nl.


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

