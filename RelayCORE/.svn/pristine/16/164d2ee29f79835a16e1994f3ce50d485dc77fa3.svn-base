/* seva_start.pl
  	Realizzazione: Febbraio, 2014 - Francesco Solare
  		Obiettivo: MAIN del SEVA
  	Revisione : Giugno,2014 - Fabio Fiorella & Davide Giannico
	Revisione: Novembre, 2016 - Iovine A. - Lovascio C.
		Obiettivo: Gestione dei moduli
		-Modifiche in loader
		-Modificato il path per i file seva_defuzzyness, seva_fuzzyness, seva_refresh e seva_setting
**********************************************/
:- ensure_loaded(setting).

%inizializzazione dell'ambiente SEVA
%:- reconsult('seva_textgui.pl').
:- reconsult('seva_setting.pl').
:- reconsult('seva_explain.pl').
:- reconsult('seva_utility.pl').
:- reconsult('seva_modules.pl'). % Iovine-Lovascio: Gestione moduli

% Setting iniziale dell'ambiente
%path_frame('gie/FRAME/').
%path_relay('C:/Users/Vincenzo/workspace_32/ReLay/').

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
	reconsult('ES/IE/seva_fuzzyness.pl'),
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
	retractall(ufunctinput(_,_,_,_)).
	%gui_popup(info0).


loader :-
	read(Kb),
	loader(Kb).
loader(Kb) :-	
	%gui_fuzzymodule(loader),
	%reconsult(Kb),
	%load_first(Kb),			% Modifica Iovine-Lovascio: Caricamento del modulo
	load_direct(Kb),
	%inizializzo il file .log
	create_filelog(Kb),
	fuzzyfier(Kb).
/*************************************************************************
        Realizzazione: Gestione del modulo di defuzzificazione
*************************************************************************/
defuzzyfier :-
	%reconsult('WebContent/ES/IE/seva_defuzzyness.pl'),
	%reconsult('C:/Users/Vincenzo/workspace_32/ReLay/WebContent/ES/IE/seva_defuzzyness.pl'),
	reconsult('ES/IE/seva_defuzzyness.pl'),
	%gui_fuzzymodule(heading_defuzzyness),
	%text_ui(start),
	defuzzyness_module.
	%text_ui(finish).

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
	%reconsult('WebContent/ES/IE/seva_refresh.pl'),			
	%reconsult('WebContent/ES/IE/seva_setting.pl').	
	%reconsult('C:/Users/Vincenzo/workspace_32/ReLay/WebContent/ES/IE/seva_refresh.pl'),			
	%reconsult('C:/Users/Vincenzo/workspace_32/ReLay/WebContent/ES/IE/seva_setting.pl').	
	reconsult('ES/IE/seva_refresh.pl'),			
	reconsult('ES/IE/seva_setting.pl').	

startExplainer :-
	%gui_expmodule(heading),
	%estraggo le regole usate per inferire nuovi fatti
	retrievaling_usedrules,
	%costruisco le spiegazioni
	building_explain,
	%gui_expmodule(closing),
	!.

refresh :-
	initializer,
	cleaner.

%-------------------------------------- CICLO MAIN ----------------------------------------------
	
runonserver(RulePath, KbPath) :- 
			multiconsult(RulePath),
			curr_task(0),	
			retractall(curr_task(_)),
			assert(curr_task(1)), 
			loader(KbPath),
			curr_task(1),
			start_count,	%Modifica apportata da Luigi Tedone, fa partire il timer che misura la durata del processo inferenziale
			retractall(curr_task(_)),
			assert(curr_task(2)), 
			%reconsult('WebContent/ES/IE/gie/gie_forward.pl'), %Modificare il path del file gie_forward.pl per run in locale. path Server UNIBA = "".
			%path_relay(PATH),
			%atom_concat(PATH,'WebContent/ES/IE/gie/gie_forward.pl',GIE),
			%reconsult(GIE), %Modificare il path del file gie_forward.pl per run in locale. path Server UNIBA = "".
			%in java la workingdirectory 衩l workingspace del progetto, navigare con ./gie/gie_forward genera errore
			reconsult('ES/IE/gie/gie_forward.pl'),
			%creo i predicati irule per ciascuna regola utili all'indicizzazione
			initindexrules,	
			%creo le liste (iniziali e di servizio) con le regole presenti nella wm	
			initialrules,
			%creo le liste (iniziali e di servizio) con i fatti presenti nella wm
			initialfacts, 
			%con le regole presenti nella wm creo i nuovi alberi aventi la la radice con le condizioni di ogni regola
			init_trees, 
			%reconsult('WebContent/ES/IE/gie/gie_frame.pl'),
			%atom_concat(PATH,'WebContent/ES/IE/gie/gie_frame.pl',FRAME), 
			%reconsult(FRAME), 
			reconsult('ES/IE/gie/gie_frame.pl'),
			findframe, %se non è stato definito use_frame(FRAME) i frame non saranno utilizzati

			%richiamo il gestore principale
			mainloop, 
			curr_task(2),
			retractall(curr_task(_)),
			assert(curr_task(3)), 
			save_dedfact,
			defuzzyfier.
			
			/*******
AGGIUNTO DA VINCENZO RAIMONDI PERCHè DIMENTICATO(?) DA VISAGGIO DOMENICO PER INTERPROLOG
******/
nonDeterministicGoal(InterestingVarsTerm,G,ListTM) :-
  findall(InterestingVarsTerm,G,L), buildTermModel(L,ListTM).
