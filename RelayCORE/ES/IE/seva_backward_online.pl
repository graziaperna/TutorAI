%:- module(backward,[backward/1]).
% ---------------------------------------------------------------------------------
% Backward reasoning
% ---------------------------------------------------------------------------------

:- dynamic fact/3.
:- dynamic yet_found/1.
:- dynamic fact_not_found/1.
:- dynamic cur_goal/1.
:- dynamic fact_new/3.
:- dynamic max_id/1.
:- ensure_loaded(backward).
%:- ensure_loaded(command).
%:- reconsult('gie/gie_operation').

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


backward_console(Obiettivo):-
	consult('ES/IE/seva_backward_online_modules/console.pl'),
	backward(Obiettivo).
	
backward_java(Obiettivo):-
	consult('ES/IE/seva_backward_online_modules/java.pl'),
	backward(Obiettivo).

backward(Obiettivo) :-
	stampa('Avviato il motore backward lato prolog.'),
	esplora(Obiettivo, [], Traccia),
	present(Obiettivo,Traccia),
	chiediAltreRisposte(Ris),
	altreRisposte(Ris).

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


%regola che permette di mostrare le deduzioni

present(Goal,Prob-Trace) :-
	deduzione(Goal,Prob),
	chiediCome(Answer),
	
	rispostaCome(Answer, Trace),
	%( Answer == y ; Answer == n ),
	!.

