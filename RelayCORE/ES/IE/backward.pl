%:- module(backward,[backward/1]).
% ---------------------------------------------------------------------------------
% Backward reasoning
% ---------------------------------------------------------------------------------
:- dynamic askable/3.
:- dynamic askablemenu/6.
:- dynamic fact/3.
:- dynamic yet_found/1.
:- dynamic fact_not_found/1.
:- dynamic cur_goal/1.
:- dynamic fact_new/3.
:- dynamic max_id/1.
:- ensure_loaded(command).
:- ensure_loaded(nec_suf).
:- reconsult('gie/gie_operation').

get_kb(KB) :-
	curr_kb(KB),
	!.
get_kb(test_run).

relay_flag(prob0,fail).
:- [appl], assert(max_id(333)).
%

% max_id(333). %da gestire in modo tale che parta dall'id massimo effettivo

fact_not_found(0). %è 1 se il fatto non è stato trovato
yet_found(1).	   %è 1 se è la soluzione è stata già trovata


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
	%retract(fact_not_found(_)),
	%assert(fact_not_found(1)),
	esplora_no(X, Catena, Traccia, Prob).
	%fact_not_found(0).


esplora(X, _Catena, 0-fact(Id, X, 0)) :- % fatto falso
	fact(Id, X, 0),
	retract(fact_not_found(_)),
	assert(fact_not_found(0)),
	!,
	fail.

esplora(X, _Catena, Prob-fact(Id, X, Prob)):- % dimostrabile con fatto
	fact(Id, X, Prob).
	%retract(fact_not_found(_)),
	%assert(fact_not_found(0)).
	
esplora(X, Catena, ProbH-rule(Id, X, ProbB-Traccia, Prior, ProbR)) :- % dimostrabile con regola
	rule(Id, X, C, Prior, ProbR),
%	assertz(cur_goal(X)), %ste PRESO DA RISP_UT
	esplora(C, [Id-X|Catena], ProbB-Traccia),
	ProbH is ProbB * ProbR.
	%ste asserire la conclusione?
	
esplora(Obiettivo, Catena, Answer) :-
	user_answer(Obiettivo, Catena, Answer).

% Pastore regole esplora_and, esplora_or e eplora_no spostate per leggibilità
esplora_and([], _Catena, [], 1.0).
esplora_and([H|T], Catena, [ProbH-TracciaH|TracciaT], Prob) :-
	esplora(H, Catena, ProbH-TracciaH),
	fail_check(ProbH),
	!,
	esplora_and(T, Catena, TracciaT, ProbT),
	Prob is min(ProbH,ProbT).

esplora_or([H|_T], Catena, Prob-Traccia, Prob) :-
	esplora(H, Catena, Prob-Traccia),
	fail_check(Prob).
esplora_or([_|T], Catena, Traccia, Prob) :-
	esplora_or(T, Catena, Traccia, Prob),
	fail_check(Prob).
%ste oppure valutare tutti e Prob is max(ProbH,ProbT)

esplora_no(X, Traccia1, Prob-Traccia, ProbNeg) :- % X è vero
	esplora(X, Traccia1, Prob-Traccia),
	!,
	ProbNeg is 1 - Prob,
	fail_check(ProbNeg).
esplora_no(X, _Traccia1, failed(X), 1.0). % X è falso

fail_check(Prob) :- %ste implementare probabilità minima per non fallire
	( relay_flag(prob0,fail) -> Prob > 0.0 ; true).


user_answer(Obiettivo, Catena, Cert-known(Id,Obiettivo,Cert)) :-
	with_output_to(atom(Traccia),write(Obiettivo)),
	fact_new(Id,Obiettivo,Cert).
	%retract(fact_new(Id,Obiettivo,Cert)).
	%fact(Id,Obiettivo,Cert). % potrebbe essere stato asserito dopo esplora/fact
	
user_answer(Obiettivo, Catena, Certezza-said(Obiettivo,Ris)) :-
	\+ fact(_Id,Obiettivo,_Cert),
	askable(Obiettivo, VerboDomanda, TestoDomanda),
	atom_concat(VerboDomanda, TestoDomanda, Domanda),
	repeat,
	domanda(Domanda,Obiettivo,Risposta),
	risposta(Risposta, Obiettivo, Catena, Certezza),
	( Risposta == y ; Risposta == n ),
	!,
	retract(fact_not_found(_)),
	assert(fact_not_found(0)).
	
% INSERITO PER MENU ASKABLE
user_answer(Obiettivo, Catena, Certezza-said(Obiettivo,Ris)) :-
	\+ fact(_Id,Obiettivo,_Cert),
	askablemenu(Obiettivo, Domanda, Menu, NomePredicato, Paziente, ParametroObiettivo),
	repeat,
	domanda(Domanda,Obiettivo,Risposta, Menu, Paziente),
	risposta_multipla(Risposta, Obiettivo, Catena, Certezza, Menu, NomePredicato, Paziente, ParametroObiettivo),
	!,
	retract(fact_not_found(_)),
	assert(fact_not_found(0)).
	
%:- ensure_loaded(kb_explorer).


%Risposta Caso why
risposta(Risposta, _Obiettivo, Catena, _Certezza) :-
	certainty(Risposta,why),
	nl,write('This is a question '),
	presentaCatena(Catena),
	nl,
	!,
	fail.

risposta(Risposta, Obiettivo, Catena, Certezza) :-
	certainty(Risposta,Certezza),
	new_fact(_Id, Obiettivo, Certezza).

risposta(Answer, _Obiettivo, _Catena, _Certezza) :-
	other_answers(Answer).

%risposta multipla caso why
risposta_multipla(Risposta, Obiettivo, Catena, Certezza, Menu, NomePredicato, Paziente, ParametroObiettivo) :-
	certainty(Risposta,why),
	nl,write('This is a question '),
	presentaCatena(Catena),
	nl,
	!,
	fail.

risposta_multipla(Risposta, Obiettivo, Catena, Certezza, Menu, NomePredicato, Paziente, ParametroObiettivo) :-
	member(Risposta,Menu), !,
	check_risposta_obiettivo(Obiettivo, Risposta, ParametroObiettivo, Certezza),
	stampa('(Prolog) asserzione del fatto chiesto...'),
	atomic_list_concat([NomePredicato,'(', Paziente, ',', Risposta,')'],Obiettivo_risposta),
	term_to_atom(Obiettivo_finale, Obiettivo_risposta),
	new_fact(_Id, Obiettivo_finale, 1.0).
	

risposta_multipla(Answer, _Obiettivo, _Catena, _Certezza, Menu, NomePredicato, Paziente, ParametroObiettivo) :-
	other_answers(Answer).

check_risposta_obiettivo(Obiettivo, Risposta, ParametroObiettivo, Certezza) :-
	Risposta \== ParametroObiettivo,
	Certezza is 0.0,
	new_fact(_Id, Obiettivo, Certezza).

check_risposta_obiettivo(Obiettivo, Risposta, ParametroObiettivo, Certezza) :-
	Risposta == ParametroObiettivo,
	Certezza is 1.0.


certainty(yes,1.0).
certainty(y,1.0).
certainty(sure,1.0).
certainty(yeah,1.0).
certainty(ye,1.0).
certainty(ok,1.0).
certainty(no,0.0).
certainty(n,0.0).
certainty(not,0.0).
certainty(never,0.0).
certainty(impossible,0.0).
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
	concludi_spiegazione(R).
	
presentaCatena([Id-Goal|RestGoals]):-
	spiega_domanda(Id,Goal),
	presentaCatena(RestGoals).

other_answers(Answer) :-
	command(Answer),
	!.
other_answers(_):-
	writeln('Comando non valido!'),
	!,
	fail.



