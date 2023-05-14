/* seva_start.pl
	Realizzazione: Febbraio, 2014 - Francesco Solare
  		Obiettivo: MAIN del SEVA
	Revisione : Giugno,2014 - Fabio Fiorella & Davide Giannico
	Revisione : Novembre, 2016 - Iovine A. - Lovascio C.
		Obiettivo:
			-Gestione dei moduli
			-Modifiche in loader e loaderfacts
			-Incluso il file seva_modules
	Revisione : Novembre, 2016 - Bruno S. - Morelli N. - Pinto A.
		Obiettivo:
			- Rimozione forzata (Aggiunto predicato delete_fact)
			- Filtri (Incluso file seva_filter)
**********************************************/
:- use_module(library(system)).      % aggiunto da Anzivino Giuseppe, Novembre 2016

:- ensure_loaded(setting).

% PREDICATI DI SETTING DELL'AMBIENTE
:- multifile (rule/5).
:- dynamic curr_rules/1. %predicato che contiene il path del file contenente le regole

%inizializzazione dell'ambiente SEVA
%:- reconsult('seva_setting.pl').
:- reconsult('seva_explain.pl').
:- reconsult('seva_utility.pl').
%:- reconsult('seva_backward.pl').
:- reconsult('seva_genera_setting.pl'). % Pasquadibisceglie V. Zaza G.:separazione del modulo di seva_setting 
:- reconsult('seva_setting_unfunction.pl').
:- reconsult('seva_setting_explainer.pl').
:- reconsult('seva_modules.pl'). % Iovine-Lovascio: Gestione moduli
:- reconsult('seva_filter.pl'). % Bruno S. - Morelli N. - Pinto A. : Filtri
:- reconsult('planner/planner_start'). % Primiceri D.
:- reconsult('stricomm/stricomm.pl'). % Ardillo I.
:- reconsult('travelsummary/travel_summary.pl'). % Modifica Galetta F.		
:- use_module(prosche/prosche).

% Setting iniziale dell'ambiente
path_frame('gie/FRAME/').



/**********************************************************************************
      	Setting delle impostazioni del current flag di Swi Prolog Mennitti-Occhionero
*********************************************************************************/
%:-current_prolog_flag(unknown,X).
%:-set_prolog_flag(unknown,fail).

/*************************************************************************
        Realizzazione: Gestione del modulo di fuzzificazione
*************************************************************************/
/** edited By Fabio F. & Davide G.**/
/** modifica successiva di Domenico V. , aggiunta check_max***/

check_max :-
	not(fact(_,_,_)),
	fuzzyness_module.
check_max :-
	find_max,
	fuzzyness_module.


fuzzyfier(Kb) :-
	call(rfact(_,_,_,_)),
	!,
	retractall(fuzzycheck(_)),
	assert(fuzzycheck(1)),
	retractall(curr_kb(_)),
	retractall(curr_rudekb(_)),
	assert(curr_rudekb(Kb)),
	reconsult('seva_fuzzyness.pl'),
	check_max.
	%find_max,          % si ricerca il valore di ID massimo
	%fuzzyness_module.
 %   reassert_fact. % si riasseriscono i fatti valutati da find_max
fuzzyfier(Kb) :-
	call(fact(_,_,_)),
	!,
	retractall(curr_rudekb(_)),
	retractall(curr_kb(_)),
	retractall(fuzzycheck(_)),
	assert(curr_kb(Kb)),
	assert(fuzzycheck(0)),
	%elimino le funzioni di membership per l'input, mantendo quelle per l'output
	retractall(ufunctinput(_,_,_,_)),
	gui_popup(info0).


% --------- modifica Anzivino Giuseppe, Pasquadibisceglie Vincenzo, Zaza Gianluca, Novembre 2016
% modificato il predicato loader, e aggiunto il predicato loaderfacts
loaderfacts :-
	text_ui(loaderfacts),
	read(Facts),
	%reconsult(Facts),
	load_first(Facts),
%%%%Modifica Mennitti-Occhionero %%%%%%%%%%%%%%%%%%
	getAbsolutePathFirstModule(Facts, NewFacts),
	list_butlast(NewFacts,FactsS),
	reverse(FactsS,Lista),
	getHead(Lista,Modulo),
	assert(modulo_iniziale(Modulo)),
	atomic_list_concat(FactsS, '', FactsWithSlash),	
	%inizializzo il file .log
	create_filelog(FactsWithSlash),
	fuzzyfier(FactsWithSlash). % richiamato in loaderfacts invece che in loader (Pasquadibisceglie Vincenzo, Zaza Gianluca)


loaderfacts(Facts) :- % Ivan Ardillo: lettura del path esplicito dei fatti
	text_ui(loaderfacts),
	load_first(Facts),
	getAbsolutePathFirstModule(Facts, NewFacts),
	list_butlast(NewFacts,FactsS),
	reverse(FactsS,Lista),
	getHead(Lista,Modulo),
	assert(modulo_iniziale(Modulo)),
	atomic_list_concat(FactsS, '', FactsWithSlash),	
	%inizializzo il file .log
	create_filelog(FactsWithSlash).

% Modifica Galetta F. lettura dei fatti più rapida per operazioni intermedie		
loader_facts_simple(Path) :-		
        load_direct(Path),		
        getAbsolutePathFirstModule(Path, NewFacts),		
        list_butlast(NewFacts,FactsS),		
        reverse(FactsS,Lista),		
        getHead(Lista,Modulo),		
        assert(modulo_iniziale(Modulo)),		
        atomic_list_concat(FactsS, '', FactsWithSlash), 		
        %inizializzo il file .log		
        create_filelog(FactsWithSlash).
        
getHead([X|T],X).
getHead([],X).


/** Modifica Galetta
loader adesso importa file e regole da un unico file
*/

loader :-
	gui_fuzzymodule(loader),
	read(Kb),
	load_first(Kb),					% Modifica Iovine-Lovascio: Caricamento del modulo iniziale
	retractall(curr_rules(_)),
	assert(curr_rules(Kb)),
	getAbsolutePathFirstModule(Kb, NewFacts),
	list_butlast(NewFacts,FactsS),
	reverse(FactsS,Lista),
	getHead(Lista,Modulo),
	split_string(Modulo,".","",L),
	nth0(0,L,ModuleName),
	assert(modulo_iniziale(ModuleName)),
	atomic_list_concat(FactsS, '', FactsWithSlash),	
	create_filelog(FactsWithSlash),
	fuzzyfier(FactsWithSlash).

loader(Kb) :- % Ivan Ardillo: loading dei fatti tramite path esplicito nella variabile Kb
	load_first(Kb),
	retractall(curr_rules(_)),
	assert(curr_rules(Kb)).


%-----------------------

/*************************************************************************
        Realizzazione: Rimozione forzata
*************************************************************************/
delete_fact(X) :-
	del_fact(X),
	fact(_, X, _),
	retract(del_fact(X)),
	retract(fact(_, X, _)).
delete_fact(X).		
	
/*************************************************************************
        Realizzazione: Gestione del modulo di defuzzificazione
*************************************************************************/
defuzzyfier :-
	reconsult('ES/IE/seva_defuzzyness.pl'),
	gui_fuzzymodule(heading_defuzzyness),
	text_ui(start),
	defuzzyness_module,
	text_ui(finish).

/****************************************************************************
        Realizzazione: Gestione del modulo di reinizializzazione del SEVA
****************************************************************************/
initializer :-
	%recupera lista fatti iniziali
	initfacts(InitFacts),
	%ripristina i fatti e le regole allo stato iniziale, reinizializzando l'ambiente
	initializefacts(InitFacts),
	initializerules,
	%svuoto l'albero di risoluzione del precedente processo inferenziale
	retractall(fireset(_)),
	assert(fireset([])),
	retractall(final_adj(_DedFact,_Regulation)),
	retractall(fact_ded(_Idf,_F,_Cf)),
	retractall(t_rule(_,_)).

/****************************************************************************
        Realizzazione: Gestione del modulo di pulizia della Working Memory
****************************************************************************/
%Modifica apportata da Domenico V.
%elimino le membership di output
cleaner :-
	retractall(ufunctoutput(_,_,_,_)), fail.
cleaner :-
	retractall(ufunctoutputok(_,_,_,_)), fail.
cleaner :-
	%richiamo il cleaner e inizializzo il SEVA
	reconsult('ES/IE/seva_refresh.pl'),
	%reconsult('seva_setting.pl').
	%aggiunto Pasquadibisceglie V. - Zaza G.
	reconsult('ES/IE/seva_setting_explainer.pl'), 
    reconsult('ES/IE/seva_setting_unfunction.pl').
    
 	
%-------------------------------------- CICLO MAIN ----------------------------------------------

startGie:-
	start_count,	% Luigi Tedone, fa partire il timer che misura la durata del processo inferenziale
	retractall(curr_task(_)),
	assert(curr_task(2)),
	reconsult('./gie/gie_forward.pl'),
	gui_gie(heading),
	%creo i predicati irule per ciascuna regola utili all'indicizzazione
	initindexrules,
	%creo le liste (iniziali e di servizio) con le regole presenti nella wm
	initialrules,
	%creo le liste (iniziali e di servizio) con i fatti presenti nella wm
	initialfacts,
	%creo le liste (iniziali e di servizio) con le variabili globali presenti nella wm
        initialglobals,
	%con le regole presenti nella wm creo i nuovi alberi aventi la la radice con le condizioni di ogni regola
	init_trees,
	%richiamo il ricercatore di frame e lo cerco
	reconsult('./gie/gie_frame.pl'),
	findframe, %se non è stato definito use_frame(FRAME) i frame non saranno utilizzati
	%richiamo il gestore principale
	mainloop,
	retract_used_fact,
	gui_gie(closing),
	!.

travelSummary :-
    start_count,
    retractall(curr_task(_)),
    assert(curr_task(19)),
    reconsult('./gie/gie_forward.pl'),
    gui_gie(heading),
    initindexrules,
    initialrules,
    initialfacts,
    init_trees,
    reconsult('./gie/gie_frame.pl'),
    findframe, 
    mainloop_complete,
    start_diary,
    gui_gie(closing),
    !.