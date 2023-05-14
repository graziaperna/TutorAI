%invocato per stampare a video.
stampa(X):-
	writeln(X).
	
%invocato da user answer
domanda(Domanda,Obiettivo,Risposta):-
	write(Domanda),write('? [y,n,w] '),
    	read(Risposta).
    
% invocato da user answer per menu ask
domanda(Domanda,Obiettivo,Risposta, Menu,Paziente):-
	write(Domanda),write(Menu),
	read(Risposta).

% Definizione di member per il controllo dell'esistenza della Risposta digitata
member(H,[H|T]).
member(X,[H|T]) :- member(X,T).


%invocato da presentaCatena([Id-Goal|RestGoals]):-
spiega_domanda(Id,Goal) :-
	write('Richiesto per la dimostrazione della regola '),writeln(Id),
	write('\t'),writeln(Goal).
	
%invocato da  presentaCatena ([])
concludi_spiegazione(R):-
	writeln('Che era il tuo obiettivo iniziale'),nl.

%invocato da Present(Goal,Prob-Trace)
deduzione(Goal,Prob):-
	write(Goal),write(' Con certezza '),write(Prob),nl.

%utile a chiedere all'utente se voglia sapere come la deduzione sia stata ottenuta
chiediCome(Answer):-
	write('Vuoi sapere come? [y, n] ' ),
	read(Answer).

%permette di chiedere all'utente se voglia altre deduzioni da parte del motore inferenziale
chiediAltreRisposte(Ris):-
	nl,
	write('Altre soluzioni? [y, n] '),
	read(Ris).
	
%visualizza la spiegazione della deduzione (tramite la traccia T)	
browse_trace(T) :- 
	writeln(T),nl.    
