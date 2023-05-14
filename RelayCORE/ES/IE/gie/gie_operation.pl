/* gie_operation.pl
  	Obiettivo: Separare la logica del funzionamento delle operazioni prolog-based inserite nelle condizioni delle regole dal resto del motore forward.
		Autori: Liso Giuseppe, Proscia Adriano (Luglio 2015)
		Revisore: Vincenzo Raimondi (Ottobre 2016)
	Autori: Iovine A. - Lovascio C.
		Obiettivo: Aggiunta dell'operatore not_exists
			-Modifiche in evaluate_cond
*************************************************************************/

% evaluate_cond(+C,-EvaluatedC)
evaluate_cond(and(L),and(NC)) :-
	evaluate_cond(L,NC).
evaluate_cond(or(L),or(NC)) :-
	evaluate_cond(L,NC).
evaluate_cond([],[]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% call_p\2 deprecato
evaluate_cond([call_p(S,Z)|List],[1|EvList]) :-
	call_p(S),
	!,
        assert(explainer(call_p(S,Z),0,'Operazione Call '+S,[])),
	evaluate_cond(List,EvList). %vincenzo modifica per pi� condizioni contemporanee
%operazone effettuata con successo, le variabili saranno bind anche in altri predicati della stessa regola
evaluate_cond([call_p(X,Y)|List],[call_p(X,Y)|EvList]):-
        !,
        %call_p(S), valutare S ha fallito
	evaluate_cond(List,EvList). %vincenzo modifica per pi� condizioni contemporanee


% call_p\1,
% esegue il predicato e in caso di successo sostituisce la sua dichiarazione nella lista con il valore 1.
% il valore 1 indica che l'operazione � stata eseguita con successo, e quindi � una condizione verificata.
% Autore: Vincenzo Raimondi
evaluate_cond([call_p(S)|List],[1|EvList]) :-
	call_p(S),
	!,
	assert(explainer(call_p(S),0,'Operazione Call '+S,[])),
	evaluate_cond(List,EvList). %vincenzo modifica per pi� condizioni contemporanee
%operazone effettuata con successo, le variabili saranno bind anche in altri predicati della stessa regola
evaluate_cond([call_p(X)|List],[call_p(X)|EvList]) :-
	!,
	evaluate_cond(List,EvList). %vincenzo modifica per pi� condizioni contemporanee

/* % Quaranta
 get_certainty(+Fact,-Certainty) consente di leggere il valore di certezza Certainty
 del fatto Fact (ad es. per poi confrontarlo con delle soglie predefinite).
*/

% Dato in input una lista il cui primo elemento sia un predicato di tipo procedure\1,
% esegue il predicato e in caso di non fallimento sostituisce la sua dichiarazione nella lista con il valore 1.
% il valore 1 indica che l'operazione � stata eseguita con successo, e quindi � una condizione verificata.
evaluate_cond([procedure(X,S)|List],EvList):-
        procedure(X,S),
		 !,
        assert(explainer(procedure(X,S),0,'Operazione Procedure '+S,[])),
		evaluate_cond(List,EvList1),
		append([1],EvList1,EvList). %operazione effettuata con successo, le variabili saranno bind anche in altri predicati della stessa regola

%recupero del valore della variabile globale
evaluate_cond([get_global(Name,Value)|List],EvList):-
       get_global(Name,Value),
              !,
        assert(explainer(get_global(Name,Value),0,'Operazione Get Global '+S,[])),
		evaluate_cond(List,EvList1),
		append([1],EvList1,EvList).

%Name � il nome della variabile specificata in input nella base di conoscenza, Value � il valore recuperato		
get_global(Name,Value):-
       global(Id,NameVariable,ValueVariable),
       NameVariable == Name,
       copy_term(ValueVariable,Value).

evaluate_cond([get_certainty(Fact, Certainty)|List],[1|EvList]) :-
	fact(_, Fact, Certainty), %ste e se e' usedFact?
	!,
        % assert(explainer(get_certainty(Fact, Certainty),0,'Operazione Get Certainty '+Fact,[])),
	evaluate_cond(List,EvList).
evaluate_cond([get_certainty(F,C)|List],[get_certainty(F,C)|EvList]) :-
	!,
	evaluate_cond(List,EvList).

% ---------------------------------------------------------------------------- %

% operatori logici	
evaluate_cond([and(A)|B],[and(EvA)|EvB]) :-
	evaluate_cond(A,EvA),
	evaluate_cond(B,EvB).
evaluate_cond([or(A)|B],[or(EvA)|EvB]) :-
	evaluate_cond(A,EvA),
	evaluate_cond(B,EvB).
evaluate_cond([no(A)|B],[no(EvA)|EvB]) :-
	evaluate_cond([A],[EvA]),
	evaluate_cond(B,EvB).
evaluate_cond([not_exists(A)|B],[not_exists(EvA)|EvB]) :-
	evaluate_cond([A],[EvA]),
	!,
	evaluate_cond(B,EvB).
evaluate_cond([A|B],[A|EvB]) :-
	evaluate_cond(B,EvB).


/** Modifica di Ivan Ardillo
  Se la call_p e' ad una write/writeln si evita l'invocazione esplicita della write
  poich� torna sempre true, che durante il checking delle condizioni
  delle regole la innescherebbe molte volte.
**/
call_p(S) :-
	functor(S, Name, _),
	(Name=write; Name=writeln),
	!. %ste aggiunto per evitare backtracking sulle successive
call_p(S) :- %write(rule2),
	functor(S,F,_),
	F \== findall,
	F \== bagof,
	F \== setof,
	catch(S, X, error_process(X)).
call_p(S) :- %write(rule3),% nel caso di findall,bagof e setof
	functor(S,F,Z),
	memberchk(F/Z,[findall/3,bagof/3,setof/3]),
	not(used_fact(_,_,_)),
	arg(1,S,ARG1),
	arg(2,S,ARG2),
	arg(3,S,ARG3),
	FACTARG=..[fact,_,ARG2,_],
	S1=..[F,ARG1,FACTARG,ARG3],
	catch(S1, X, error_process(X)). 

% in caso di eccezione il ciclo segner� fallimento e continuer�. 
%error_process(instantiation_error).   
error_process(E) :-
	%writeln(E),
	fail.

%esecuzione procedura S contenuta nel file X
%nel primo argomento va inserito il percorso della directory in cui � memorizzato il file delle procedure
%di default si suppone sia stato memorizzato nella directory corrente altrimenti settare il path nel primo argomento
procedure(X,S):-
    atom_concat('C://Users/user/Documents/',X,FileProcedure),
    use_module(FileProcedure),
    X:S.

%%%%%%%%%%%%%%CODICE non utilizzato
operation:-
	rule(_,_,C,_,_),
	(C=and(X) ; C=or(X)),
	evaluate_cond(X).
operation. %vincenzo, passo base
	

find_operation(or([L|Lr]),NC):-
	find_operation(L,NC1),
	!,
	find_operation(or(Lr),NC2),
	append(NC1,NC2,NC).

find_operation(or([]),[]):-!.

find_operation(or([call_p(X,Y)|Lr]),NC):-
	find_operation(or(Lr),NL),
	append([call_p(X,Y)],NL,NC).
find_operation(or([_|Lr]),NC):-
	find_operation(or(Lr),NC).

find_operation(no(call_p(X,Y)),[call_p(X,Y)]).
find_operation(no(_),_).

	find_operation(and([L|Lr]),NC):-
	find_operation(L,NC1),
	!,
	find_operation(and(Lr),NC2),
	append(NC1,NC2,NC).

find_operation(and([]),[]):-!.

find_operation(and([call_p(X,Y)|Lr]),NC):-
	find_operation(and(Lr),NL),
	append([call_p(X,Y)],NL,NC).
	
find_operation(and([_|Lr]),NC):-
	find_operation(and(Lr),NC).
	

find_operation(or([L|Lr]),NC):-
	find_operation(L,NC1),
	!,
	find_operation(or(Lr),NC2),
	append(NC1,NC2,NC).

find_operation(or([]),[]):-!.

find_operation(or([call_p(X,Y)|Lr]),NC):-
	find_operation(or(Lr),NL),
	append([call_p(X,Y)],NL,NC).
find_operation(or([_|Lr]),NC):-
	find_operation(or(Lr),NC).

find_operation(no(call_p(X,Y)),[call_p(X,Y)]).
find_operation(no(_),_).
