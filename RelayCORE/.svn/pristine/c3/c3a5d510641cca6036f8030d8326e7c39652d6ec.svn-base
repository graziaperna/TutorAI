/* seva_modules.pl - Implementa il caricamento e lo scaricamento dei moduli
*	Realizzazione: Settembre, 2016 (Andrea Iovine, Cosimo Lovascio)
**********************************************/
:- use_module(library(lineutils)).
:- use_module(library(charsio)).

% scarica il modulo di nome Module
unload(Module) :-
	retractall(rule(id(Module, _), _, _, _, _)),
	retractall(used_rule(id(Module, _), _, _, _, _)),
	retractall(irule(id(Module, _), _, _)),
	retractall(t_rule(id(Module, _), _)).
	
/* Caricamento iniziale del modulo
*/	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%Modifica Occhionero - Mennitti:	

load_first(ModulePath) :-
	getAbsolutePathFirstModule(ModulePath, NewModulePath),
	list_butlast(NewModulePath,ModulePathS),
	atomic_list_concat(ModulePathS, '', Atom),
	open(Atom, 'read', Stream),					%Carica il file
	readModuleName(Stream, ModuleName),				%Leggi il nome del modulo, se presente
	close(Stream),
	!,
	open(Atom, 'read', Stream2),
	readAssertionsFromModule(ModuleName, Stream2).

% Nel caso il modulo non ha nome
load_first(ModulePath) :-
	getAbsolutePathFirstModule(ModulePath, NewModulePath),
	list_butlast(NewModulePath,ModulePathS),
	gui_popup(warn0),
	createModuleName(ModulePathS,ModuleName),
	atomic_list_concat(ModulePathS, '', Atom),
	open(Atom, 'read', Stream),
	readAssertionsFromModule(ModuleName, Stream).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


/** 
Predicato per generare il nome del modulo di un file che non ne contiene uno.
il nome del modulo sarà il nome stesso del file
**/
createModuleName(ModulePath,ModuleName) :-
	last(ModulePath, ModuleNameFull),
	split_string(ModuleNameFull,".","",List),
	nth0(0,List,ModuleName).


/* load_direct(F) : (Galetta F.)
Permette il loading diretto del file F per operazioni intermedie. 
Creato per superare un problema del predicato load che non permetteva di sfruttare file temporanei presenti nella stessa cartella,
a meno che non si inserisca il path completo.
*/
load_direct(File) :-
	open(File,read,Stream),
	readAssertionsFromModule(Stream).

/* Caricamento dei moduli durante il processo di inferenza
*/	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%Modifica Occhionero - Mennitti:
load(ModulePath) :- % Caso in cui il file possiede un nome di modulo
	getAbsolutePath(ModulePath, AbsPath),
	atomic_list_concat(AbsPath, '', AbsPathAtom),
	open(AbsPathAtom, 'read', Stream), 			 %Carica il file
	readModuleName(Stream, ModuleName),			 %Leggi il nome del modulo, se presente
	close(Stream),
	!,
	load2(AbsPathAtom, ModuleName).
	
load2(ModulePath, ModuleName) :-
	check_not_already_loaded(ModuleName),			%Controlla che il modulo non sia già caricato
	open(ModulePath, 'read', Stream2), 
	readAssertionsFromModule(ModuleName, Stream2),
	deuse_facts,
	indexing,
	init_trees(ModuleName),
	initialfacts(ModuleName).
	initialrules(ModuleName).
	initialglobals(ModuleName).
load2(ModulePath, ModuleName).

load(ModulePath) :- % Nel caso in cui il modulo non dispone di un nome, si usa un semplice id incrementale.
	getAbsolutePath(ModulePath, AbsPath),
	atomic_list_concat(AbsPath, '', AbsPathAtom),
	open(AbsPathAtom, 'read', Stream), 
	readAssertionsFromModule(Stream),
	deuse_facts,
	retractall(t_rule(_,_)), % Ardillo: pulizia degli alberi a seguito del caricamento, migliorate le prestazioni.
	init_trees,
	indexing.
load(ModulePath).
	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Controlla se non sia già stato caricato un modulo con lo stesso nome
check_not_already_loaded(ModuleName) :-
	\+ rule(id(ModuleName, _), _, _, _, _),
	\+ fact(id(ModuleName, _), _, _).

/* readModuleName(+S,-M) :
  Legge dallo stream S il nome del modulo M, che deve essere specificato
  all'inizio del file con il predicato module_name(M).
*/
readModuleName(Stream, ModuleName) :-
	read(Stream, Assertion),
	readModuleName(Stream, Assertion, ModuleName).

readModuleName(Stream, module_name(ModuleName), ModuleName).
readModuleName(Stream, end_of_file, ModuleName) :- !, fail.

/**
	Lettura del file
*/
% Legge i predicati Prolog uno alla volta	
readAssertionsFromModule(ModuleName, Stream) :-
	read(Stream, X),
	dispatchAssertion(ModuleName, Stream, X).

% Versione senza il nome del modulo
readAssertionsFromModule(Stream) :-
	read(Stream, X),
	dispatchAssertion(Stream, X).

/**
	Versione con il nome del modulo
**/	
	
% Se si è raggiunta la fine del file, termina l'esecuzione.
dispatchAssertion(ModuleName, Stream, end_of_file) :- !.
% Se l'asserzione letta è una regola, creala
dispatchAssertion(ModuleName, Stream, rule(IDNumber, Con, Ant, Prior, Crt)) :-
	makeARule(ModuleName, rule(IDNumber, Con, Ant, Prior, Crt)),
	!,
	readAssertionsFromModule(ModuleName, Stream).			%ripeti
dispatchAssertion(ModuleName, Stream, fact(IDNumber, Fact, Crt)) :-
	makeAFact(ModuleName, fact(IDNumber, Fact, Crt)),
	!,
	readAssertionsFromModule(ModuleName, Stream).  			%ripeti
%asserzione variabile globale
dispatchAssertion(ModuleName, Stream, global(IDNumber, Global, Value)) :-
	makeAGlobal(ModuleName, global(IDNumber, Global, Value)),
	!,
	readAssertionsFromModule(ModuleName, Stream).
dispatchAssertion(ModuleName, Stream, module_name(_)) :-
	!,
	readAssertionsFromModule(ModuleName, Stream).  			%ripeti
dispatchAssertion(ModuleName, Stream, X) :-
	makeAPrologClause(X),
	!,
	readAssertionsFromModule(ModuleName, Stream).  			%ripeti

% Crea una regola, modifica l'ID secondo la struttura id(Modulo, Numero) e la aggiunge alla KB
makeARule(ModuleName, rule(IDNumber, Con, Ant, Prior, Crt)) :-
	assert(rule(id(ModuleName, IDNumber), Con, Ant, Prior, Crt)).
	
% Crea un fatto, modifica l'ID secondo la struttura id(Modulo, Numero) e lo aggiunge alla KB
makeAFact(ModuleName, fact(IDNumber, Fact, Crt)) :-
	assert(fact(id(ModuleName, IDNumber), Fact, Crt)).
	
%creazione variabile globale
makeAGlobal(ModuleName, global(IDNumber, Global, Value)) :-
	assert(global(id(ModuleName, IDNumber), Global, Value)).
	
/**
	Versione senza nome del modulo
**/
% Se si è raggiunta la fine del file, termina l'esecuzione.
dispatchAssertion(Stream, end_of_file) :- !.
% Se l'asserzione letta è una regola, creala
dispatchAssertion(Stream, rule(IDNumber, Con, Ant, Prior, Crt)) :-
	makeARule(rule(IDNumber, Con, Ant, Prior, Crt)),
	!,
	readAssertionsFromModule(Stream).			%ripeti
dispatchAssertion(Stream, fact(IDNumber, Fact, Crt)) :-
	makeAFact(fact(IDNumber, Fact, Crt)),
	!,
	readAssertionsFromModule(Stream).  			%ripeti
%creazione variabile globale
dispatchAssertion(Stream, global(Id, Variable, Value)) :-
	makeAGlobal(global(Id, Variable, Value)),
	!,
	readAssertionsFromModule(Stream).  
dispatchAssertion(Stream, X) :-
	makeAPrologClause(X),
	!,
	readAssertionsFromModule(Stream).  			%ripeti
	

% Caso in cui la regola è caricata durante il processo di inferenza,
% Bisogna aggiornare l'id in modo corretto
makeARule(rule(IDNumber, Con, Ant, Prior, Crt)) :-
	current_predicate(maxidr/1), %% modifica occhionero - mennitti
	maxidr(M),
	IdrNew is M + 1,
	idrlist_upd(IdrNew),
	assert(rule(IdrNew, Con, Ant, Prior, Crt)),
	init_tree(rule(IdrNew, Con, Ant, Prior, Crt)),
	initialrule(rule(IdrNew, Con, Ant, Prior, Crt)).
% Caso in cui la regola è caricata all'inizio
% Si usa semplicemente l'id scritto nel file
makeARule(rule(IDNumber, Con, Ant, Prior, Crt)) :-
	assert(rule(IDNumber, Con, Ant, Prior, Crt)).
	
% Caso in cui il fatto è caricato durante il processo di inferenza,
% Bisogna aggiornare l'id in modo corretto
makeAFact(fact(IDNumber, Fact, Crt)) :-
	current_predicate(maxidf/1), %% modifica occhionero - mennitti
	maxidf(M),
	IdfNew is M + 1,
	idflist_upd(IdfNew),
	assert(fact(IdfNew, Fact, Crt)),
	initialfact(fact(IdfNew, Fact, Crt)).
% Caso in cui il fatto è caricato all'inizio
% Si usa semplicemente l'id scritto nel file
makeAFact(fact(IDNumber, Fact, Crt)) :-
	assert(fact(IDNumber, Fact, Crt)).
	
	
% Caso in cui la variabile e' caricata durante il processo di inferenza,
% Bisogna aggiornare l'id in modo corretto
makeAGlobal(global(Id, Variable, Value)) :-
	current_predicate(maxidg/1),
	maxidg(M),
	IdgNew is M + 1,
	idglist_upd(IdgNew),
	assert(global(IdgNew, Variable, Value)),
	initialglobal(global(IdgNew, Value, Value)).

% Caso in cui la variabile globale e' caricata all'inizio
% Si usa semplicemente l'id scritto nel file
makeAGlobal(global(Id, Variable, Value)) :-
        assert((global(Id, Variable, Value))).

% Caricamento di una clausola prolog pura
makeAPrologClause(X) :-
	catch(assert(X), error(permission_error(_,_,_),_), true).

/*****
	Utility per la lettura del path
*****/	
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%Modifica Occhionero - Mennitti:

%%Ottiene il path del modulo iniziale
getAbsolutePathFirstModule(KbPath, DirectoryS) :-
	readPath(KbPath, DirectoryS).
%%Ottiene il path del modulo da caricare
getAbsolutePath(Filename, AbsPath) :-
	curr_rules(KbPath), 				%%contiene il path completo del primo modulo caricato(compreso di nome del file)
	readPath(KbPath, DirectoryS),
	atomic_list_concat(FilenameS, ' ', Filename),	%%trasforma l'atomo in una lista 
	list_butlast(DirectoryS,DirectorySS),		%%Rimozione del primo modulo caricato dal path
	list_butlast(DirectorySS,DirectorySSS),		%%Rimozione del simbolo "/"
	append(DirectorySSS, FilenameS, AbsPath).


% Legge il path di un file, e restituisce la directory in cui esso si trova 	
readPath(PathS, CompletePathS) :-			%%caso ubuntu
	atomic_list_concat(ListSplit, ',', PathS),	%%effettua lo split sulla virgola e restituendo una lista
	name(PathS,NewPathS),
	not(starts_with_C(NewPathS)),
	reverse(ListSplit,ListSplitRev),		%%inserimento dello slash iniziale
	append(ListSplitRev,[/],ListSplitNew),		%%
	reverse(ListSplitNew,ListSplitWithSlash),	%%
	add_slash_ubuntu(ListSplitWithSlash, CompletePathS).
readPath(PathS, CompletePathS) :- 			%%caso windows	
	atomic_list_concat(ListSplit, ',', PathS),	%%effettua lo split sulla virgola e restituendo una lista
	name(PathS,NewPathS),
	starts_with_C(NewPathS),	
	add_slash_Windows(ListSplit, CompletePathS).


%%Inserimento del carattere "/" nel caso di esecuzione su Ubuntu
add_slash_ubuntu([H|T], X) :-
	add_slash_ubuntu([H|T], [], X).

add_slash_ubuntu([], Partial, Partial).
add_slash_ubuntu([H|T], Partial, X) :-
	append([H],[/], Concat),
	append(Partial, Concat, C),
	add_slash_ubuntu(T, C, X).

%%Inserimento del carattere "/" nel caso di esecuzione su Windows
add_slash_Windows([H|T], X) :-
	add_slash_Windows([H|T], [], X).

add_slash_Windows([], Partial, Partial).
add_slash_Windows([H|T], Partial, X) :- 		
	append([H],[/], Concat),
	append(Partial, Concat, C),
	add_slash_Windows(T, C, X).


%%Verifica se il path inizia con "C" (caso windows)
starts_with_C([C|T]) :-C =:= 67.
%%Pinto: Supporto per "c" minuscolo
starts_with_C([C|T]) :-C =:= 99.


%%Elimina l'ultimo elemnto in una lista
list_butlast([X|Xs], Ys) :-                 
   list_butlast_prev(Xs, Ys, X).            

list_butlast_prev([], [], _).
list_butlast_prev([X1|Xs], [X0|Ys], X0) :-  
   list_butlast_prev(Xs, Ys, X1). 

