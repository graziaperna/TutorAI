stampa(Message):-
	%visualizzerà nella console Java qualsiasi messaggio
	javaMessage('java.lang.System'-out,println(string(Message))).

%invocato da user_answer, permette di inviare le domande a Java e visualizzarle
domanda(Domanda,Catena,Obiettivo,Risposta):-
	current_project_path(Project_path), with_output_to(atom(Java_project_path), write(Project_path)),	
	buildQuestionWhy(Catena, "", QuestionWhy),
	name(Java_question_why, QuestionWhy),
	with_output_to(atom(Java_obiettivo),write(Obiettivo)),
	with_output_to(atom(Java_domanda),write(Domanda)),
	javaMessage('java.lang.System'-out,println(string(Java_domanda))),
	javaMessage('backward.PrologInteraction',Risposta_java,prologQuestion(string(Java_project_path), string(Java_domanda), string(Java_obiettivo), string(Java_question_why))),
	javaMessage('java.lang.System'-out,println(string(' DOMANDA inviata  da Prolog a Java'))),
	javaMessage('java.lang.System'-out,println(string('RISPOSTA:'))),
	with_output_to(atom(Risp),write(Risposta_java)),
	javaMessage('java.lang.System'-out,println(string(Risp))),
	%conversione dell'output di java da string(y) in y (e analogamente per n)
	convert_risp(Risp,Risposta). 

% PER MENU ASK
domanda(Domanda,Catena,Obiettivo,Risposta, Menu, Paziente):-
	stampa('domanda multipla'),
	current_project_path(Project_path), with_output_to(atom(Java_project_path), write(Project_path)),
	buildQuestionWhy(Catena, "", QuestionWhy),
	name(Java_question_why, QuestionWhy),	
	with_output_to(atom(Java_obiettivo),write(Obiettivo)),
	with_output_to(atom(Java_domanda),write(Domanda)),
	with_output_to(atom(Java_menu), write(Menu)),
	with_output_to(atom(Java_paziente), write(Paziente)),
	stampa(Java_obiettivo),
	stampa(Java_domanda),
	stampa(Java_menu),
	javaMessage('java.lang.System'-out,println(string(Java_domanda))),
	javaMessage('backward.PrologInteraction',Risposta_java,prologQuestionMultiple(string(Java_project_path), string(Java_domanda), string(Java_obiettivo), string(Java_menu), string(Java_paziente), string(Java_question_why))),
	javaMessage('java.lang.System'-out,println(string(' DOMANDA inviata  da Prolog a Java'))),
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
convert_risp('string(halt)', Risposta):-
	halt.
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
    
%invocato da present/2 e permette di inviare i risultati delle deduzioni a Java, per permettere la successiva
%visualizzazione nella interfaccia
deduzione(Goal,Prob,Trace):-
	current_project_path(Project_path), with_output_to(atom(Java_project_path), write(Project_path)),
	with_output_to(atom(Java_goal),write(Goal)),
	with_output_to(atom(Java_prob),write(Prob)),
	with_output_to(atom(Java_trace),write(Trace)),
	javaMessage('java.lang.System'-out,println(string('Invio risposta da Prolog a Java'))),
	javaMessage('backward.PrologInteraction',Risposta_java, appendResult(string(Java_project_path), string(Java_goal),string(Java_prob), string(Java_trace))).
	
%permette di chiedere all'utente se voglia altre deduzioni da parte del motore inferenziale
chiediAltreRisposte(Risposta):-
	current_project_path(Project_path), with_output_to(atom(Java_project_path), write(Project_path)),
	javaMessage('backward.PrologInteraction',Risposta_java, otherDeductions(string(Java_project_path))),
	!,
	with_output_to(atom(Risp),write(Risposta_java)),
	convert_risp(Risp,Risposta). 

buildQuestionWhy([], TempWhy, TempWhy).
buildQuestionWhy([Id-Goal|RestGoals], InputWhy, OutputWhy) :- 
	name(Id, NameId),
	with_output_to(atom(Atom_goal), write(Goal)),
	name(Atom_goal, NameGoal),
	
	StringTemplate = ["Richiesto per la dimostrazione dalla regola ", NameId, "\t", NameGoal, "\n"],
	buildStringFromList(StringTemplate, InputWhy, TempWhy),	
	buildQuestionWhy(RestGoals, TempWhy, OutputWhy).

buildStringFromList([], InputString, InputString).
buildStringFromList([H|T], InputString, OutputString) :-
	append(InputString, H, TempString),
	buildStringFromList(T, TempString, OutputString).