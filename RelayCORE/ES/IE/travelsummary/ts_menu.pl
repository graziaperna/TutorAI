diary(heading) :-
	nl,
	write('-----------------------------------------------------------------------'),nl,
    write('                            TRAVEL DIARY                               '),nl,
    write('-----------------------------------------------------------------------'),nl.

menu_diary :-
	diary(menu),
	nl, read(C),
	menu_d(C).

diary(menu) :-
	nl,nl,
	write('1. Start Travel'),nl,
	write('2. Add Traveler'),nl,
	write('3. Add Group'),nl,
	write('4. Add Stage'),nl,
	write('5. Add Multimedia'),nl,
	write('6. Add Note'),nl,
	write('7. Close Travel'),nl,
	write('8. Generate Travel Summary'),nl,
	write('0. Exit'),nl.
	
	
menu_d(1):-
	not(viaggio(_,_,_,_)),
	writeln('Let\'s start the travel. Do you want to give it a name? (y/n)'),
	read(Answer),
	((Answer == yes ; Answer == y) 
	  -> 
	  writeln('Insert the travel name'),
	  read(Name),
	  writeln('Insert start date'),
	  read(StartDate),
	  assert(viaggio(1,Name,StartDate,'')) ; 
	  writeln('Insert start date'),
	  read(StartDate),
	  assert(viaggio(1,'Il mio viaggio',StartDate,''))),
	writeln("Travel Added!"),
	%assert(tappe([])),
	!,
	menu_diary.

menu_d(1):-
	viaggio(_,_,_,_),
	writeln("Travel already added."),
	!,
	menu_diary.

menu_d(2):-
	writeln('Insert traveler name'),
	read(Nome),
	idTr(A),
	IdT is A+1,
	retractall(idTr(A)),
	assert(idTr(IdT)),
	assert(turista(IdT,Nome)),
	assert_fact(turista(IdT,Nome)),
	(gruppo(A,_) -> assert(fa_parte_di(IdT,A)),assert_fact(fa_parte_di(IdT,A)) ; true),
	writeln('Traveler added'),
	!,
	menu_diary.

menu_d(3):-
	not(gruppo(_,_)),
	writeln('Insert group name'),
	read(Nome),
	idTr(A),
	IdT is A+1,
	retractall(idTr(A)),
	assert(idTr(IdT)),
	assert(gruppo(IdT,Nome)),
	assert_fact(gruppo(IdT,Nome)),
	add_appartenenza,
	writeln("Group added"),
	!,
	menu_diary.
menu_d(3):-
	gruppo(_,_),
	writeln("Group already added"),
	!,
	menu_diary.

menu_d(4) :-
	writeln('Insert Journey Stop'),
	read(Stop),
	(add_tappa(Stop) ->	writeln('Journey Stop added') ; writeln('Invalid journey stop')) ,
	!,
	menu_diary.

menu_d(5) :-
	writeln('Insert Multimedia URL'),
	read(URL),
	writeln('Which type? (p/photo - v/video)'),
	read(Tipo),
	writeln('About which place?'),
	read(Place),
	idxPlaces(IDP,Place),
	(turista(IdTur,_) -> assert(multimedia(IDP,Tipo,URL,IdTur)) ; assert(multimedia(IDP,Tipo,URL,default))),
	add_tappa(Place),
	writeln("Multimedia added"),
	!,
	menu_diary.
menu_d(5) :-
	write('Place invalid'),
	!,
	menu_diary.

menu_d(6) :-
	writeln('Insert Note'),
	read(Nota),
	writeln('About which place?'),
	read(Place),
	idxPlaces(IDP,Place),
	(turista(IdT,_) -> assert(nota(IDP,Nota,IdT)) ; assert(nota(IDP,Nota,default))),
	add_tappa(Place),
	writeln("Note Added"),
	!,
	menu_diary.	
	
menu_d(6) :-
	write('Place invalid'),
	!,
	menu_diary.
	
menu_d(7) :-
	writeln('Insert Journey End Date'),
	read(Date),
	viaggio(A,B,C,_),
	retractall(viaggio(A,_,_,_)),
	assert(viaggio(A,B,C,Date)),
	assert_fact(viaggio(A,B,C,Date)),
	!,
	menu_diary.
menu_d(8) :-
	viaggio(A,B,C,Date),
	assert_fact(viaggio(A,B,C,Date)),
	get_FL,
	save_facts,
	tappe_principali,
    cleaner,
    loader_facts_simple('./travelsummary/temp.pl'),
    loader_facts_simple('./travelsummary/template.pl'),
    initindexrules,
    initialrules,
    initialfacts,
    init_trees, 
    mainloop_complete,
    save_dedfact,
    save_travel_summary,
    menu_d(0).
    

menu_d(0) :- !.