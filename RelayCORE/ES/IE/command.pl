:- module(kb_explorer,[command/1]).

% ESPLORAZIONE base di conoscenza (Giuliana Spinelli)
%ste E' TUTTO BASATO SU UN FILE KB.pl CHE PERO' NON VIENE GENERATO
command(stampakb) :-
	nl,
	write('*****************************************Stampa della base di conoscenza*****************************************'),
	nl,
	forall(rule(Id,H,B,Q,P),writeln(rule(Id,H,B,Q,P))),
	read(_),
	forall(fact(Id,F,P),writeln(fact(Id,F,P))),
	read(_),
%    open('KB.pl', read, Str),
%    read_kb(Str),
%	!,
%    close(Str),
	nl,
	write('*****************************************************************************************************************'),
	nl.
 

read_kb(Stream) :-
    at_end_of_stream(Stream).
read_kb(Stream) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
    stampatutto(X),
	read_kb(Stream).

stampatutto(askable(X, Y)):- 
    write('askable:'),
	write(X),
	nl,
	nl,
	!.
stampatutto(rule(Y, X, C, Q, Z)):-
    nl,
	write('rule:'),
	write(Y),
	write('-'),
	write(X),
	nl,
	!.
stampatutto(fact(Y,X,Z)):-
    nl,
	write('fact:'),
	write(Y),
	write('-'),
	write(X),
	nl,
	!.
stampatutto(X):- 
   write(X),
   !.	

%**********************************************************************************************************
% Stampa delle regole della base di conoscenza aggiunto da Giuliana Spinelli
%**********************************************************************************************************	
command(stamparegole) :-
    nl,
    write('******************************Stampa delle  regole presenti nella base di conoscenza*****************************'),
	nl,
    open('KB.pl', read, Str),
    read_regole(Str),
	!,
    close(Str),
	nl,
	write('*****************************************************************************************************************'),
	nl.

read_regole(Stream) :-
    at_end_of_stream(Stream).
read_regole(Stream) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
    stampa_se_regola(X),
	read_regole(Stream).

stampa_se_regola(rule(Y, X, C, Q, Z)):-
    nl,
	write('rule:'),
	write(Y),
	write('-'),
	write(X),
	nl,
	!.
stampa_se_regola(X):- !.
    
%**********************************************************************************************************
% Stampa i fatti della base di conoscenza aggiunto da Giuliana Spinelli
%**********************************************************************************************************	
command(stampafatti) :-
 open('KB.pl', read, Str),
    read_fatti(Str),
	!,
	nl,
    close(Str).

read_fatti(Stream) :-
    at_end_of_stream(Stream).
read_fatti(Stream) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
    stampa_se_fatti(X),
	read_fatti(Stream).

stampa_se_fatti(askable(X, Y)):- 
    write('askable:'),
	write(X),
	nl,
	nl,
	!.
stampa_se_fatti(fact(Y,X,Z)):-
  nl,
	write('fact:'),
	write(Y),
	write('-'),
	write(X),
	nl,
	!.
stampa_se_fatti(X):- !.
	
%*****************************************************************************************************************
% visualizzazione di una determinata regola identificata da un id chiesto all'utente aggiunto da Giuliana Spinelli
%*****************************************************************************************************************	
command(chiediregola) :-     
	nl,write('Inserisci Id della regola che vuoi visualizzare:  '),
	read(Ris),
	stamparegola(Ris),
	!.

stamparegola(Ris):-
    open('KB.pl', read, Str),
    read_regola(Str,Ris),
	!,
    close(Str).

read_regola(Stream,Ris) :-
    at_end_of_stream(Stream).
read_regola(Stream,Ris) :-
    \+ at_end_of_stream(Stream),
    read(Stream,X),
    stampa_regola(X,Ris),
	read_regola(Stream,Ris).

stampa_regola(rule(Y, X, C, Q, Z),Y):-
    nl,
	write(rule(Y, X, C, Q, Z)),
	nl,
	nl,
	!.
stampa_regola(rule(Y, X, C, Q, Z),A):- !.
stampa_regola(X,Y):- !.
stampa_regola(X,A):- !.

	
