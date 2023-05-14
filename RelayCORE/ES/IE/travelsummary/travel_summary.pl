% Modulo principale per la creazione del racconto di un viaggio

:- reconsult('../gie/gie_forward.pl'). 
:- reconsult('ts_menu.pl').
:- reconsult('ts_utility.pl').
:- dynamic viaggio/4.
:- dynamic turista/2.
:- dynamic gruppo/2.
:- dynamic idTr/1.
:- dynamic fa_parte_di/2.
:- dynamic tappa/3.
:- dynamic multimedia/4.
:- dynamic nota/3.
:- dynamic idxPlaces/2.
:- dynamic punto_importante/1.
:- dynamic punto_di_interesse/2.
:- dynamic tappe/1.
:- dynamic first/1.
:- dynamic last/1.
:- dynamic unique/1.
:- dynamic idTappa/1.


start_diary :-
	init,
	diary(heading),
	menu_diary.
	

add_appartenenza :-
	turista(A,_),
	gruppo(B,_),
	assert(fa_parte_di(A,B)),
	assert_fact(fa_parte_di(A,B)),
	fail.
add_appartenenza.

add_tappa(Tappa) :-
	idxPlaces(IDP,Tappa),
	viaggio(Id,_,_,_),
	(tappa(Id,IDP,Occorrenze) -> (retract(tappa(Id,IDP,Occorrenze)),Occ2 is Occorrenze+1, assert(tappa(Id,IDP,Occ2))) ; assert(tappa(Id,IDP,1))),
	(tappe(L)->	(retract(tappe(L)),append(L,[IDP],L1),assert(tappe(L1))); assert(tappe([IDP]))),!.
	

compose_intro(Stream) :-
	findall(Frase,frase_inizio(Frase),Frasi),
	random_member(F,Frasi),
	writeln(Stream,F).

compose_middle(Stream,List) :-
	head(List,H),
	findall(Frase,frase(H,Frase,_),Frasi),
	random_member(F,Frasi),
	idTappa(IdT),
	((turista(Turista,_),multimedia(H,_,Url,Turista))->true;multimedia(H,_,Url,_)),
	(tappa_principale(H,_)->atomic_list_concat(["Tappa ",IdT," [",Url,"]"],'',Intro);atomic_list_concat(["Tappa ",IdT],'',Intro)),
	writeln(Stream,Intro),
	retractall(idTappa(IdT)),
	IdT1 is IdT+1,
	assert(idTappa(IdT1)),
	writeln(Stream,F),
	delete(List,H,NewList),
	compose_middle(Stream,NewList).
compose_middle(_,_).

compose_end(Stream) :-
	findall(Frase,frase_fine(Frase),Frasi),
	random_member(F,Frasi),
	writeln(Stream,F).

get_coord(Stream) :-
	findall(Coord,coord(_,Coord),Coordinate),
	writeln(Stream,Coordinate).
get_coord(_).


save_travel_summary:-
	open('./travelsummary/Travel_summary.txt',write,Stream),
	write(Stream,''),
	close(Stream),
	open('./travelsummary/Travel_summary.txt',append,Stream2),
	assert_frasi,
	tappe(List),
	remove_dups(List,NewList),
	compose_intro(Stream2),
	compose_middle(Stream2,NewList),
	compose_end(Stream2),
	get_coord(Stream2),
	close(Stream2),
	write('Travel Summary Created').

assert_frasi:-
	used_fact(_,Fatto,_),
	=..(Fatto,[Term|Arguments]),
	(Term == frase;Term==frase_inizio;Term==frase_fine;Term==coord;Term==multimedia),
	assert(Fatto),
	fail.
assert_frasi.

tappe_principali :-
	tappa(Idv,ID,Occorrenze),
	idxPlaces(ID,Nome),
	((Occorrenze >1 ; punto_importante(ID); first(ID) ; last(ID)) -> (assert(tappa_principale(ID,Nome)),assert_fact(tappa_principale(ID,Nome))) ; assert_fact(tappa(ID,Nome))),
	fail.
tappe_principali.	



get_FL :-
	tappe(List),
	head(List,H),
	assert(first(H)),
	assert_fact(first(H)),
	tail(List,L),
	assert(last(L)),
	assert_fact(last(L)),
	assert_fact(tappe(List)).


init_facts :-
	used_fact(Id,Fatto,Certezza),
	=..(Fatto,[Head|Args]),
	((Head==idxPlaces; Head==punto_importante; Head==nota; Head==viaggio ; Head == tappa; Head==tappe) -> assert(Fatto);true),
	fail.

init_facts.


init :-
	open('./travelsummary/temp.pl',write,Stream),
	write(Stream,''),
	close(Stream),
	assert(idTr(0)),
	assert(idTappa(1)),
	init_facts.