stampa(Message):-
	%visualizzerà nella console Java qualsiasi messaggio
	javaMessage('java.lang.System'-out,println(string(Message))).

%invocato da user_answer, permette di inviare le domande a Java e visualizzarle
domanda(Domanda,Obiettivo,Risposta):-
	with_output_to(atom(Java_obiettivo),write(Obiettivo)),
	with_output_to(atom(Java_domanda),write(Domanda)),
	javaMessage('java.lang.System'-out,println(string(Java_domanda))),
	javaMessage('gui.PrologInteraction',Risposta_java,prolog_question(string(Java_domanda),string(Java_obiettivo))),
	javaMessage('java.lang.System'-out,println(string(' DOMANDA inviata  da Prolg a Java'))),
	javaMessage('java.lang.System'-out,println(string('RISPOSTA:'))),
	with_output_to(atom(Risp),write(Risposta_java)),
	javaMessage('java.lang.System'-out,println(string(Risp))),
	%conversione dell'output di java da string(y) in y (e analogamente per n)
	convert_risp(Risp,Risposta). 

% PER MENU ASK
domanda(Domanda,Obiettivo,Risposta, Menu, Paziente):-
	stampa('domanda multipla'),
	with_output_to(atom(Java_obiettivo),write(Obiettivo)),
	with_output_to(atom(Java_domanda),write(Domanda)),
	with_output_to(atom(Java_menu), write(Menu)),
	with_output_to(atom(Java_paziente), write(Paziente)),
	stampa(Java_obiettivo),
	stampa(Java_domanda),
	stampa(Java_menu),
	javaMessage('java.lang.System'-out,println(string(Java_domanda))),
	javaMessage('gui.PrologInteraction',Risposta_java,prolog_question_multiple(string(Java_domanda),string(Java_obiettivo), string(Java_menu), string(Java_paziente))),
	javaMessage('java.lang.System'-out,println(string(' DOMANDA inviata  da Prolg a Java'))),
	javaMessage('java.lang.System'-out,println(string('RISPOSTA:'))),
	with_output_to(atom(Risp),write(Risposta_java)),
	%conversione dell'output di java da string(y) in y (e analogamente per n)
	convert_risp(Risp,Risposta). 

	
convert_risp('string(y)',Risposta) :-
	Risposta = y.
convert_risp('string(n)',Risposta):-
	Risposta = n.
convert_risp('string(w)',Risposta):-
	Risposta = why, !.
	
convert_risp(Risp,Risposta) :-
	deepest_string(Risp,T),
	Risposta = T.	
	
deepest_string(Risp,T) :-
    atom_codes(Risp,CS),
    last_opening(CS,X),
    extract_substring(CS,X,CT),
    atom_codes(T,CT).

%Cerca la prima parentesi aperta
last_opening(L,X) :-
    last_opening(L,0,0,X).

last_opening([],J,_,J).
last_opening([40|T],_,I,X) :-
    !,
    I1 is I+1,
    last_opening(T,I1,I1,X).
last_opening([_|T],J,I,X) :-
    I1 is I+1,
    last_opening(T,J,I1,X).

%Estrazione sottostringa
extract_substring(L,0,S) :-
    !,
    extract_substring2(L,S).
extract_substring([_|T],N,S) :-
    N1 is N-1,
    extract_substring(T,N1,S).

extract_substring2([],[]).
extract_substring2([41|_],[]) :-
    !.
extract_substring2([L|T],[L|U]) :-
    extract_substring2(T,U).
    
		
%invocato da presentaCatena, permette di concatenare le motivazioni per cui una domanda è posta all'utente
spiega_domanda(Id,Goal):-
	with_output_to(atom(Atom_id),write(Id)),
	with_output_to(atom(Atom_goal),write(Goal)),
	atom_concat('Richiesto per la dimostrazione dalla regola ',Atom_id, Java_rule),
	atom_concat('\t',Atom_goal, Java_goal),
	javaMessage('gui.PrologInteraction', append_why(string(Java_rule))),
	javaMessage('gui.PrologInteraction', append_why(string(Java_goal))).

%invocato da presentaCatena per visualizzare la spiegazione lato Java, mediante popup
concludi_spiegazione(R):-
	javaMessage('gui.PrologInteraction',R, show_why).

%invocato da present/2 e permette di inviare i risultati delle deduzioni a Java, per permettere la successiva
%visualizzazione nella interfaccia
deduzione(Goal,Prob):-
	with_output_to(atom(Java_goal),write(Goal)),
	with_output_to(atom(Java_prob),write(Prob)),
	javaMessage('java.lang.System'-out,println(string('Invio risposta da Prolg a Java'))),
	javaMessage('gui.PrologInteraction',Risposta_java, append_results_prolog(string(Java_goal),string(Java_prob))),
	with_output_to(atom(Risp),write(Risposta_java)),
	convert_risp(Risp, Procedere),
	(Procedere == n -> fail ; Procedere == y ->	javaMessage('gui.PrologInteraction', mostra_risultati)).
	%javaMessage('gui.PrologInteraction', mostra_risultati).
	
%mostra un popup nell'interfaccia Java per chiedere se l'utente voglia sapere come la deduzione sia stata ottenuta
chiediCome(Risposta):-
	javaMessage('gui.PrologInteraction',Risposta_java, chiedi_stampaCome),
	with_output_to(atom(Risp),write(Risposta_java)),
	convert_risp(Risp,Risposta). 

%mostra la spiegazione dell'obiettivo corrente in un popup Java, inviando al metodo la traccia T
browse_trace(T) :- 
	javaMessage('java.lang.System'-out,println(string('browse_trace'))),
	with_output_to(atom(Traccia),write(T)),
	javaMessage('java.lang.System'-out,println(string('Traccia convertita in atomo'))),
	javaMessage('gui.PrologInteraction',R, stampaCome(string(Traccia))).
	
%permette di chiedere all'utente se voglia altre deduzioni da parte del motore inferenziale
chiediAltreRisposte(Risposta):-
	javaMessage('gui.PrologInteraction',Risposta_java, altre_risposte),
	!,
	with_output_to(atom(Risp),write(Risposta_java)),
	convert_risp(Risp,Risposta). 
	
	
	
	
