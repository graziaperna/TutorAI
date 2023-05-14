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
:- dynamic current_project_path/1.
:- ensure_loaded(backward).

relay_flag(prob0,fail).

fact_not_found(0). %è 1 se il fatto non è stato trovato
yet_found(1).	   %è 1 se è la soluzione è stata già trovata
	
backward_java(Obiettivo):-
	consult('ES/IE/seva_backward_server/java.pl'),
	backward(Obiettivo).

backward(Obiettivo) :-
	stampa('Avviato il motore backward lato prolog.'),
	esplora(Obiettivo, [], Traccia),
	present(Obiettivo,Traccia),
	chiediAltreRisposte(Ris),
	altreRisposte(Ris).

altreRisposte(y) :-
	!,
	fail.
altreRisposte(n).
altreRisposte(Answer) :-
	other_answers(Answer).

%regola che permette di mostrare le deduzioni
present(Goal,Prob-Trace) :-
	deduzione(Goal,Prob,Trace),
	!.

