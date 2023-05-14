%add elem to a list
%add_elem(+List,+Elem,-NewList).
add_elem(T,X,Z):-append(T,[X],Z).

%Trova il massimo (o minimo) elemento in una lista utilizzando la funzione CompareFunction data in input
%my_max(+Lista, +CompareFunction, -Max)
my_max([X|Xs], CompareFunction, Max):- 
	my_max(Xs, CompareFunction, X, Max ). 
my_max([], _, Max, Max).
my_max([X|Xs], CompareFunction, WK, Max):- 
	call(CompareFunction, X, WK), !,
	my_max(Xs, CompareFunction, X, Max).
my_max([X|Xs], CompareFunction, WK, Max):- 
	not(call(CompareFunction, X, WK)), 
	my_max(Xs, CompareFunction, WK, Max).
	
%Non usato in scheduler
%Filtra una lista sulla base della funzione FilterCondition data in input
%filter(+List,+ElementToCompare,+FilterCondition,-FilteredList)
filter([],_,_FilterCondition,[]).
filter([H|T],X,FilterCondition,[H|T2]):-
	call(FilterCondition,X,H), !,
	filter(T,X,FilterCondition, T2).
filter([_|T],X,FilterCondition,T2):-
	filter(T,X,FilterCondition, T2).

%Algoritmo di ordinamento bubblesort che effettua i confronti chiamando la funzione CompareFunction data in input
%bubblesort(+List,+CompareFunction,-SortedList).
bubblesort(List, CompareFunction, SortedList) :-
	swap(List,CompareFunction, NewList), !,
	bubblesort(NewList,CompareFunction, SortedList).
bubblesort(SortedList,_CompareFunction, SortedList).
swap([A,B|List], CompareFunction, [B,A|List]) :-
	call(CompareFunction,A,B).
swap([A|List], CompareFunction, [A|NewList]) :-
	swap(List, CompareFunction, NewList).	

	

	
