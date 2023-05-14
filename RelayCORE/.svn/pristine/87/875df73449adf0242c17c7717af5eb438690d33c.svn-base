/* seva_textgui.pl
	Realizzazione: Febbraio, 2014
  		Obiettivo: GUI testuale del SEVA
       		Autore: Francesco Solare
       	 
*************************************************************************/
:- ensure_loaded(command).
:- reconsult('seva_start.pl').

%-------------------------------------- CICLO MAIN ----------------------------------------------
start :-
	text_ui(heading),
	repeat,
	text_ui(menu),
	read_choice(Chc),
	do(Chc),
	Chc == 0,
	!.

read_choice(Chc) :-
	repeat,
	write('     Type choice: '),
	read(Chc),
	integer(Chc),
	Chc >=0,
	Chc =<20,
	!.


/****************************
	SEVA/GIE GENERIC
****************************/
text_ui(heading) :- 
	nl,
        write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),nl,
        write('@                        LACAM-ML / DIB / UniBA                         @'),nl,
	write('@                        ARTIFICIAL BRAIN S.r.l.                        @'),nl,
        write('@-----------------------------------------------------------------------@'),nl,
        write('@                                ReLay                                  @'),nl,
        write('@                (REasoning LAYer - General Inference Engine)           @'),nl,
	write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@').
text_ui(menu) :- 	
        nl,nl,write('     *************************** MENU ******************************'),nl,
	nl,write('     1 - Load Knowledge Base '),      % modifica Galetta, Luglio 2019
	nl,write('     2 - Start GIE - Generic Inferential Engine '),
	nl,write('     3 - Saving Adjustments '),
	nl,write('     4 - Explain Deductions '),
	nl,write('     5 - Ask Questions'),
	nl,write('     6 - Reinitialize SEVA Environment '),	
	nl,write('     7 - Refresh Working Memory '),
	nl,write('     8 - Modify membership setting '),   %aggiunto da Pasquadibisceglie V. - Zaza G.
	nl,write('    9 - Forced Removal '),   %aggiunto da Bruno S. - Morelli N. - Pinto A.
	nl,write('    10 - Filters '),   %aggiunto da Bruno S. - Morelli N. - Pinto A.
	nl,write('    11 - Create RETE structure '),	%aggiunto Macchiarulo N. - Rinalidi F. - Rizzi S.
	nl,write('    12 - Start GIE with RETE pattern matching '),	%aggiunto Macchiarulo N. - Rinalidi F. - Rizzi S.
	nl,write('    13 - Display RETE structure '),	%aggiunto Macchiarulo N. - Rinalidi F. - Rizzi S.
	nl,write('    14 - Display working memory '),	%aggiunto Macchiarulo N. - Rinalidi F. - Rizzi S.
	nl,write('    15 - Start scheduling inference '), %aggiunto da G.Marzulli
	nl,write('    16 - Explain scheduling inference '), %aggiunto da G.Marzulli
	nl,write('    17 - Planning module '), %aggiunto da Primiceri D.
	nl,write('    18 - Start inference with Stricomm'), %aggiunto da Ardillo I.
	nl,write('    19 - Travel Summary'), %aggiunto da Galetta F.
	nl,write('    20 - Esplorazione KB - DA IMPLEMENTARE'), %aggiunto da Galetta F.
	nl,write('     0 - Exit'),
	nl,nl.
text_ui(closing) :- 
        nl,write('@--------------------------- CLOSING ------------------------------@').

gui_gie(heading) :- 
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),
	nl,write('@                                                                       @'),
	nl,write('@                     GIE - Generic Inference Engine                    @'),
        nl,nl,write('     ********************** START INFERENCE ************************'),nl,nl.
gui_gie(closing) :- 
        nl,nl,write('     ************************* FINISH ******************************'),
	nl,write('@                                                                       @'),
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@').


/***
Modifica Luglio 2019, Galetta 
Il caricamento dei fatti e delle regole avviene da un file unico
*/


do(1) :-
	curr_task(0),
	loader,
	retractall(curr_task(_)),
	assert(curr_task(1)),
	!.
do(1) :-
    curr_task(1),
	gui_popup(info1).

do(2) :-
	curr_task(1),
	startGie,
	!.
do(2) :-
	gui_popup(wrong2),
	!.
do(3) :-
	curr_task(XChs),
	XChs > 2,
	!,
	gui_popup(info3).
do(3) :-
	curr_task(2),
	retractall(curr_task(_)),
	assert(curr_task(3)),
	save_dedfact,
	defuzzyfier,
	gui_popup(saving),
	!.
do(3) :-
	gui_popup(wrong1),
	!.
do(4) :-
	curr_task(XChs),
	XChs < 3,
	gui_popup(wrong4),
	!.
do(4) :-
	gui_expmodule(heading),
	%estraggo le regole usate per inferire nuovi fatti
	retrievaling_usedrules,
	
	%retrievaling_usedrules_2,                                                     % sbloccare nel caso si voglia utilizzare 
	%open('rulefact.pl', write, SS),                                               % l'altro modulo di spiegazione 
        %write(SS,'''.''.'),                                                           % (Anzivino Giuseppe, Novembre 2016), 
	%nl(SS),                                                                       % e commentare 
	%close(SS),                                                                    % retrievaling_usedrules
	
	%costruisco le spiegazioni
	building_explain,
	
	%building_explain_2,                                                           % sbloccare nel caso si voglia utilizzare 
	%rules,                                                                        % l'altro modulo di spiegazione 
	%stamp_rule_fact,                                                              % (Anzivino Giuseppe, Novembre 2016), 
	%delete_file('explain.pl'),                                                    % e commentare building_explain
	
	gui_expmodule(closing),
	!.
do(5) :-
	curr_task(XChs),
	XChs < 1,
	gui_popup(wrong5),
	!.
do(5) :-
	text_ui(backward),
	gui_popup(info4),
	read(Goal),
	text_ui(start),
	backward(Goal),
	text_ui(finish),
	!.
do(5) :-
	gui_popup(info5),
	text_ui(finish),
	!.
do(6) :-
	curr_task(0),
	gui_popup(wrong2),
	!.
do(6) :-
	retractall(curr_task(_)),
	assert(curr_task(0)),
	initializer,
	gui_popup(info0).
do(7) :-
	cleaner,
	gui_popup(info0).
do(8) :- % Pasquadibisceglie V. - Zaza G. : inserimento della funzionalità della modifica delle membership
	curr_task(XChs),
	XChs > 0,
	gui_popup(wrong7).
do(8) :-
	inizio,
	!.
do(9) :- % Bruno S. - Morelli N. - Pinto A. : Aggiunta di del_fact per la rimozione forzata
	curr_task(1),
	gui_popup(info7),
	read(DelFact),
	assert(del_fact(DelFact)),
	delete_fact(DelFact).
do(9) :-
    curr_task(0),
	gui_popup(wrong2).
do(10) :- % Bruno S. - Morelli N. - Pinto A. : Aggiunta di un modulo per i filtri
	curr_task(XChs),
	XChs < 3,
	gui_popup(wrong1).
do(10) :-
	filter_menu.	
do(11) :- % Macchiarulo N. - Rinaldi F. - Rizzi S.
	curr_task(0),
	gui_popup(wrong8).
do(11) :-
	curr_task(1),
	reconsult('./rete/retecompile.pl'),
	compile,
	gui_popup(info8).
do(11) :-
	gui_popup(wrong8).
do(12) :- % Macchiarulo N. - Rinaldi F. - Rizzi S.
	curr_task(0),
	gui_popup(wrong2).
do(12) :-
	curr_task(1),
	reconsult('./rete/retego.pl'),
	initialize,
	go,
	gui_popup(info8).
do(12) :-
	gui_popup(wrong2).
do(13) :- % Macchiarulo N. - Rinaldi F. - Rizzi S.
	curr_task(0),
	gui_popup(wrong8).
do(13) :-
	curr_task(1),
	reconsult('./rete/retewrite.pl'), 
	display_net,
	gui_popup(info8).
do(13) :-
	gui_popup(wrong8).
do(14) :- % Macchiarulo N. - Rinaldi F. - Rizzi S.
	curr_task(0),
	gui_popup(wrong8).
do(14) :-
	curr_task(1),
	reconsult('./rete/retewrite.pl'),
	print,
	gui_popup(info8).
do(14) :-
	gui_popup(wrong8).
do(15) :-
	not(current_predicate(s_resource/3)),
	not(current_predicate(state/6)),
	not(current_predicate(requestor/2)),
	not(current_predicate(s_request/9)),
	gui_popup(wrong9).
do(15) :-
	curr_task(XChs),
	XChs < 1,
	gui_popup(wrong2).
do(15) :-
	gui_popup(info9),
	read(NextRequestFunctionName),
	gui_popup(info10),
	read(OrderStatesFunctionName),
	gui_popup(info11),
	read(SatisfiabilityFunctionName),
	start_scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName),
	gui_popup(info12).
do(15) :-
	gui_popup(wrong10).
do(16) :-
	curr_task(XChs),
	XChs < 1,
	gui_popup(wrong2).
do(16) :-
	schedule(_,_,_),
	gui_expmodule(heading),
	schedule_explainer(it,ReqExplanation),
	state_explainer(it,StExplanation),
	atom_concat(ReqExplanation,StExplanation,Explanation),
	write(Explanation),
	gui_expmodule(closing).
do(17) :- % Primiceri D.
	gtrace,
	start_planner.
do(18) :- % Ardillo I.
	gtrace,
	start_stricomm_seva.
do(19) :- % Galetta
	curr_task(0),
	gui_popup(wrong2).
do(19) :-
	curr_task(1),
	travelSummary,
	!.
do(19) :-
	gui_popup(wrong2),
	!.	
do(20) :- % comandi diretti
	nl,write('     ___________________ Command ____________       '),
	nl,write('     Please enter a command: '),nl,
	read(C),
	( 
		command(C)
	;
		writeln('unknown command')
	),
	!.

do(0) :-
	text_ui(closing).
do(_) :-
	gui_popup(wrong0).


/****************************
	SEVA POPUP
****************************/

 % modifica Anzivino giuseppe, Pasquadibisceglie Vincenzo, Zaza Gianluca, Novembre 2016
 % aggiunto gui_popup(info6), modificato gui_popup(wrong1), modificato gui_popup(wrong2), aggiunto gui_popup(wrong6)


gui_popup(info0) :-
	nl,write('     _____________________ STAGE COMPLETED _____________________       '),nl.
gui_popup(info1) :-
	nl,write('     ___________________ WARNING: reccurring choice ____________       '),
	nl,write('     Explain: loading knowledge base completed.'),nl.
gui_popup(info2) :-
	nl,write('     ___________________ WARNING: reccurring choice ____________       '),
	nl,write('     Explain: processing GIE completed.'),nl.
gui_popup(info3) :-
	nl,write('     ___________________ WARNING: reccurring choice ____________       '),
	nl,write('     Explain: saving deductions completed.'),nl.
gui_popup(info4) :-
	nl,write('     ___________________ QUESTION ____________       '),
	nl,write('     Insert the question to ask: '),nl.
gui_popup(info5) :-
	nl,nl,write('     ____________ INFORMING: no answer found ___________          '),
	nl,write('     Explain: There is no answer to the question'),nl.
gui_popup(info6) :-
	nl,write('     ___________________ WARNING: reccurring choice ____________       '),
	nl,write('     Explain: loading facts completed.'),nl.
gui_popup(info7) :-
	nl,write('     ___________________ FORCED REMOVAL ____________       '),
	nl,write('     Insert the fact to not assert during the GIE process: '),nl.
gui_popup(info8) :-
	nl,write('     ___________________ STRUCTURE CREATED ____________       '),
	nl,write('     Explain: The RETE structure has been created correctly. '),nl.
gui_popup(info9) :-
	nl,write('     ___________________ ProSche: INSERT REQUESTS ORDER POLICY _______________      '),
	nl,write('     Insert: the requests order policy. Example: next_request_by_priority '),nl.
gui_popup(info10) :-
	nl,write('     ___________________ ProSche: INSERT STATES ORDER POLICY _______________      '),
	nl,write('     Insert: the states order policy. Example: oreder_states_by_priority '),nl.
gui_popup(info11) :-
	nl,write('     ___________________ ProSche: INSERT SATISFIABILITY POLICY _______________      '),
	nl,write('     Insert: the satisfiability policy. Example: satisfiability '),nl.
gui_popup(info12) :-
	nl,write('     ___________________ ProSche info: _______________      '),
	nl,write('     Scheduling inference completed '),nl.
gui_popup(saving) :-
	curr_flog(FileLog),
	nl,nl,write('     ____________ INFORMING: saving of deductions ___________          '),
	nl,write('     Explain: saving completed on "'),write(FileLog),write('".'),nl.
gui_popup(wrong0) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: type one of the number corresponding to the choices above, please.'),nl.
gui_popup(wrong1) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: please produce inferences (choice 2) first.'),nl.
gui_popup(wrong2) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: please load a knowledge base (choice 1) first.'),nl.
gui_popup(wrong3) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: SEVA Environment is already initialized.'),nl.
gui_popup(wrong4) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: No deduction to explain.'),nl.
gui_popup(wrong5) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: Error in backward.'),nl.
gui_popup(wrong6) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),
	nl,write('     Explain: please load a knowledge base (choice 1) first.'),nl.
gui_popup(wrong7) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),%aggiunto da Pasquadibisceglie V. - Zaza G.
	nl,write('     Explain: In order to change the membership values, the environment must be initialized, please.'),nl.	
gui_popup(wrong8) :-
	nl,write('     ___________________ WARNING: wrong choice _________________       '),%aggiunto da noi
	nl,write('     Explain: please load a knowledge base (choice 1) first.'),nl.	
gui_popup(wrong9) :-
	nl,write('     ___________________ ProSche: insufficient scheduling facts _________________       '),
	nl,write('     Explain: you need to specify requests, requestors, resources and states.'),nl.
gui_popup(wrong10) :-
	nl,write('     ___________________ ProSche: error _________________       '),
	nl,write('     Explain: Scheduling inference process ended. Are policies correct?'),nl.

gui_popup(warn0) :-
	nl,write('     ___________________ WARNING: module not found _________________       '),
	nl,write('     Explain: For a correct execution provide the name of the module'),nl,
	nl,write('              The file name will be used as module\'s name'),nl.
/****************************
	FUZZY MODULE
****************************/
gui_fuzzymodule(loader) :-
	nl,nl,write('     *************************** LOADER ****************************'),
	nl,nl,write('     Please insert path of complete knowledge base: '),nl.
	
% ---------------modifica Anzivino giuseppe, Pasquadibisceglie Vincenzo, Zaza Gianluca, Novembre 2016

text_ui(loaderfacts) :-
	nl,nl,write('     *************************** LOADER ****************************'),
	nl,nl,write('     Please insert path of complete facts: '),nl.

% ---------------------------
	
gui_fuzzymodule(heading_fuzzyness) :-
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),
	nl,write('@                                                                       @'),
	nl,write('@                           Fuzzyness Module                            @').
gui_fuzzymodule(heading_defuzzyness) :-
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),
	nl,write('@                                                                       @'),
	nl,write('@                           Defuzzyness Module                          @'),
        nl,nl,write('     *********************** INIZIALIZING **************************'),nl.

text_ui(start) :-
	nl,nl,write('     *************************** START *****************************'),nl.
text_ui(finish) :-
	nl,nl,write('     *************************** FINISH ****************************'),
	nl,write('@                                                                       @'),
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@').

/****************************
	EXPLAIN MODULE
****************************/
gui_expmodule(heading) :-
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),
	nl,write('@                                                                                                @'),
	nl,write('@                                       Explain Module                                           @').
gui_expmodule(closing) :-
	nl,nl,write('@                                                                                                @'),
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@').
/****************************
	BACKWARD MODULE
****************************/
text_ui(backward) :-
	nl,write('@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'),
	nl,write('@                                                                                                @'),
	nl,write('@                                       Backward Module                                          @').
