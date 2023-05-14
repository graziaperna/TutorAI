remove_dups([], []).

remove_dups([First | Rest], NewRest) :- member(First, Rest), remove_dups(Rest, NewRest).

remove_dups([First | Rest], [First | NewRest]) :- not(member(First, Rest)), remove_dups(Rest, NewRest). 

head([A|_],A).
tail([X],X).
tail([_|Y],X) :- tail(Y,X).

fact_copy(Stream,List) :-
	used_fact(Id,Fatto,Certezza),
	=..(Fatto,[Head|Args]),
	nth0(0,Args,IdP),
	member(IdP,List),
	(Head==punto_di_interesse -> nth0(1,Args,Idl),idxPlaces(Idl,Name),assert_fact(idxPlaces(Idl,Name)) ; true),
	(Head==punto_di_interesse, not(unique(Idl)) -> nota(Idl,Nota,Origine),assert_fact(nota(Idl,Nota,Origine)),assert(unique(Idl)) ; true),
	write(Stream,'fact('),
	write(Stream,Id),
	write(Stream,','),
	writeq(Stream,Fatto),
	write(Stream,','),
	write(Stream,Certezza),
	writeln(Stream,').'),
	fail.
fact_copy(_,_).

save_facts :-
	tappe(List),
	remove_dups(List,ListNoDup),
	open('./travelsummary/temp.pl',append,Stream),
	fact_copy(Stream,ListNoDup),
	close(Stream).

assert_fact(Fact) :-
	open('./travelsummary/temp.pl',append,Stream),
	modulo_iniziale(Modulo),
	maxidf(M),
	IdfNew is M+1,
	write(Stream,'fact(id('),
	write(Stream,Modulo),
	write(Stream,','),
	write(Stream,IdfNew),
	write(Stream,'),'),
	writeq(Stream,Fact),
	write(Stream,','),
	write(Stream,1),
	writeln(Stream,').'),
	idflist_upd(IdfNew),
	close(Stream).
