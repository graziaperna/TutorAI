:- module(prosche,[start_scheduler/3,cancel_schedule/1,schedule_explainer/2,state_explainer/2]).

:- use_module(library(lists)).

:- consult(settings).
:- consult(generic_utilities).
:- consult(default_policies).
:- consult(scheduler_utilities).

%************ INIZIALIZZAZIONE ****************

%inizializzazione delle liste discarded_requests, used_requests, used_states, e l'ultimo state ID
init :- init_used_requests,
	init_used_states.


% Se used_states è già inizializzato non fa nulla, altrimenti asserisce una lista vuota
init_used_states :-
	user:used_states(_).
init_used_states :-
	assert(user:used_states([])).

% Se used_requests è già inizializzato non fa nulla, altrimenti asserisce una lista vuota	
init_used_requests :-
	user:used_requests(_).
init_used_requests :-
	assert(user:used_requests([])).

%***************************

/*
Main dello scheduler. Avvia l'inizializzazione e il processo di scheduling
start_scheduler(+NextRequestFunctionName,+OrderStatesFunctionName,+SatisfiabilityFunctionName)
NextRequestFunctionName: Nome della funzione che restituisce la prossima richiesta da processare
OrderStatesFunctionName: Nome della funzione di ordinamento degli stati
SatisfiabilityFunctionName: Nome della funzione che determina la condizione di schedulazione
*/
start_scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName):-
	init,
	scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName).


/* 
Attivazione dello scheduler
scheduler(+NextRequestFunctionName,+OrderStatesFunctionName,+SatisfiabilityFunctionName)
NextRequestFunctionName: Nome della funzione che restituisce la prossima richiesta da processare
OrderStatesFunctionName: Nome della funzione di ordinamento degli stati
SatisfiabilityFunctionName: Nome della funzione che determina la condizione di schedulazione
*/
scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName):-
	%fallisce se nono sono definite le policy di scheduling
	current_predicate(NextRequestFunctionName/2),
	current_predicate(OrderStatesFunctionName/2),
	current_predicate(SatisfiabilityFunctionName/2),
	open_requests([]),!.% condizione di terminazione della ricorsione
scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName):-
	open_requests(OpenRequestIDs),
	call(NextRequestFunctionName,OpenRequestIDs,NextRequestID),
	active_states(ActiveStateIDs),
	filter_states(ActiveStateIDs,NextRequestID,FilteredStateIDs),
	call(OrderStatesFunctionName,FilteredStateIDs,OrderedStates),
	assert(prosche_settings:discarding_log(NextRequestID,init)),
	do_schedule(NextRequestID,OrderedStates,SatisfiabilityFunctionName),
	scheduler(NextRequestFunctionName,OrderStatesFunctionName,SatisfiabilityFunctionName).
	
/*
Funzione che evade la singola richiesta
do_schedule(+RequestID,+StateIDs,+SatisfiabilityFunctionName,+ChangeStateFunction)
RequestID: ID della richiesta
StateIDs: Lista di ID dei possibili stati della risorsa
SatisfiabilityFunctionName: Nome della funzione che determina la condizione di schedulazione
*/
%Se stateID è lista vuota significa che non ci sono più stati disponibili, quindi fallisce lo schedule della richiesta
do_schedule(RequestID,[],_SatisfiabilityFunctionName):-
	discard_request(RequestID),!.
do_schedule(RequestID,[StateID|_T],SatisfiabilityFunctionName):-
	discarding_logger(RequestID,state_too_late),check_request_expiration(StateID,RequestID),
	user:s_request(RequestID,_Timestamp,_Priority,_Duration,_RequestedQty,_RequestorID,_ResourceID, _Expiration, _Execution),
	discarding_logger(RequestID,resource_unavailable),availability_state_qtylist(StateID,RequestID),
	discarding_logger(RequestID,not_satisfiable),call(SatisfiabilityFunctionName,RequestID,StateID),
	discarding_logger(RequestID,not_available_duration),check_duration_availability(StateID,RequestID),
	create_states([StateID],RequestID,AllocatedStateIDs),
	close_request(RequestID,AllocatedStateIDs),
	disable_states([StateID]),
	discarding_logger(RequestID,succeded),!.
%Se la durata non è sufficiente, prova a collidere gli stati
do_schedule(RequestID,[StateID|_T],SatisfiabilityFunctionName):-
	check_request_expiration(StateID,RequestID),
	user:s_request(RequestID,_Timestamp,_Priority,_Duration,_RequestedQty,_RequestorID,_ResourceID, _Expiration, _Execution),
	availability_state_qtylist(StateID,RequestID),
	call(SatisfiabilityFunctionName,RequestID,StateID),
	not(check_duration_availability(StateID,RequestID)),
	needed_time(StateID,RequestID,NeededTime),
	contiguos_states(StateID,RequestID,NeededTime,SatisfiabilityFunctionName,TmpContiguosStateIDs),
	append([StateID],TmpContiguosStateIDs,ContiguosStateIDs),
	create_states(ContiguosStateIDs,RequestID,AllocatedStateIDs),
	close_request(RequestID,AllocatedStateIDs),
	disable_states(ContiguosStateIDs),
	discarding_logger(RequestID,succeded),!.
%procede con lo stato successivo
do_schedule(RequestID,[_StateID|T],SatisfiabilityFunctionName):-
	do_schedule(RequestID,T,SatisfiabilityFunctionName).

/*
Chiude la richiesta asserendo il nuovo schedule, dichiarando la richiesta come usata
close_request(+RequestID,+AllocatedStateIDs)
RequestID: ID della richiesta
AllocatedStateIDs: Lista di ID degli stati assegnati alla richiesta 
*/
close_request(RequestID,AllocatedStateIDs):-
	user:used_requests(UsedRequests),
	user:s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution),
	add_elem(UsedRequests,s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution),NewUsedRequests),
	retract(user:used_requests(UsedRequests)),
	assert(user:used_requests(NewUsedRequests)),
	retract(user:s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution)),
	increment_schedule_id(IncrementedScheduleID),
	assert(user:schedule(IncrementedScheduleID,RequestID,AllocatedStateIDs)).

/*
Scarta la richiesta
%discard_request(+RequestID)
%RequestID: ID della richiesta 	
*/
discard_request(RequestID):-
	user:used_requests(UsedRequests),
	user:s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution),
	add_elem(UsedRequests,s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution),NewUsedRequests),
	retract(user:used_requests(UsedRequests)),
	assert(user:used_requests(NewUsedRequests)),
	retract(user:s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID,Expiration,Execution)),
	assert(user:discarded_request(RequestID)).


%Genera uno o più stati a partire da uno stato individuato per l'allocazione della richieta
%StateID: stato da cui generare i figli
%RequestID: ID della richiesta
%ResidualDuration: Durata residua da soddisfare con lo stato in questione
%NewResidualDuration: Durata residua risultante dall'allocazione dello stato in questione
%AllocatedStateID: ID dello stato figlio che è assegnato allo schedule
%generate_state(+StateID,+RequestID,+ResidualDuration,-NewResidualDuration,-AllocatedStateID)
generate_state(_StateID,_RequestID,0,_NewResidualDuration,[]). %condizione di terminazione
%inizio stato è successivo al timestamp di richiesta
%Residual duration è  >= alla durata dello stato
%Genera solo uno stato con stesso intervallo ma quantità modificata
generate_state(StateID,RequestID,ResidualDuration,NewResidualDuration,AllocatedStateID):-
	user:s_request(RequestID,Timestamp,_RequestPriority,_Duration,_RequestedQty,_RequestorID,_RequestResourceID, _Expiration, _Execution),
	user:state(StateID,_ResourceID,_StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	StartTime >= Timestamp,
	EndTime - StartTime =< ResidualDuration,!,
	evaluate_new_state_qtylist(StateID,RequestID,NewStateQty),
	generate_child_state(StateID,StartTime,EndTime,NewStateQty,AllocatedStateID),
	NewResidualDuration is ResidualDuration - (EndTime - StartTime),!.
%inizio stato  è successivo al timestamp di richiesta
%Residual duration è < alla durata dello stato
%genera due stati:
% - il primo inizia allo starttime del padre e finisce quando finisce la richiesta. La quantità è modificata dalla richiesta
% - il secondo inizia alla fine della richiesta e finisce all'endtime del padre. La quantità è la stessa del padre.
generate_state(StateID,RequestID,ResidualDuration,NewResidualDuration,AllocatedStateID):-
	user:s_request(RequestID,Timestamp,_RequestPriority,_Duration,_RequestedQty,_RequestorID,_RequestResourceID, _Expiration, _Execution),
	user:state(StateID,_ResourceID,StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	StartTime >= Timestamp,
	EndTime - StartTime > ResidualDuration,
	NewEndTime is StartTime + ResidualDuration,
	evaluate_new_state_qtylist(StateID,RequestID,NewStateQty),
	generate_child_state(StateID,StartTime,NewEndTime,NewStateQty,AllocatedStateID),
	generate_child_state(StateID,NewEndTime,EndTime,StateQty,_ChildStateID),
	NewResidualDuration is 0,!. %significa che questo stato esaurisce la richiesta temporale
%inizio stato è precedente al timestamp di richiesta
%Residual duration è  >= alla durata dello stato
%genera due stati:
% - il primo inizia allo starttime del padre e finisce al timestamp della richeista. La quantità è la stessa del padre
% - il secondo inizia al timestamp di richiesta e finisce all'Endtime del padre. La quantità è modificata dalla richiesta
generate_state(StateID,RequestID,ResidualDuration,NewResidualDuration,AllocatedStateID):-
	user:s_request(RequestID,Timestamp,_RequestPriority,_Duration,_RequestedQty,_RequestorID,_RequestResourceID, _Expiration, _Execution),
	user:state(StateID,_ResourceID,StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	StartTime < Timestamp,
	EndTime - Timestamp =< ResidualDuration,
	evaluate_new_state_qtylist(StateID,RequestID,NewStateQty),
	generate_child_state(StateID,StartTime,Timestamp,StateQty,_ChildStateID),
	generate_child_state(StateID,Timestamp,EndTime,NewStateQty,AllocatedStateID),
	NewResidualDuration is ResidualDuration - (EndTime - Timestamp),!.
%inizio stato è precedente al timestamp di richiesta
%Residual duration è < alla durata dello stato
%genera tre stati:
% - il primo inizia allo starttime del padre e finice al timestamp della richiesta. La quantità è la stessa del padre
% - il secondo inizia al timestamp di richiesta e finisce al termine della richiesta. La quantità è modificata dalla richiesta
% - il terzo inizia al fine della richiesta e finisce all'endtime del padre. La quantità è la stessa del padre
generate_state(StateID,RequestID,ResidualDuration,NewResidualDuration,AllocatedStateID):-
	user:s_request(RequestID,Timestamp,_RequestPriority,_Duration,_RequestedQty,_RequestorID,_RequestResourceID, _Expiration, _Execution),
	user:state(StateID,_ResourceID,StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	StartTime < Timestamp,
	EndTime - Timestamp > ResidualDuration,
	evaluate_new_state_qtylist(StateID,RequestID,NewStateQty),
	NewEndTime is Timestamp + ResidualDuration,
	generate_child_state(StateID,StartTime,Timestamp,StateQty,_ChildStateID1),
	generate_child_state(StateID,Timestamp,NewEndTime,NewStateQty,AllocatedStateID),
	generate_child_state(StateID,NewEndTime,EndTime,StateQty,_ChildStateID3),
	NewResidualDuration is 0,!. %significa che questo stato esaurisce la richiesta temporale


%Dato uno stato genera un figlio con tempo di inizio, fine e quantità data in input
%asserisce il nuovo stato e disabilita il padre
%generate_child_state(+FatherStateID,+Start,+End,+NewQty,-ChildStateID)
%FatherStateID: ID dello stato da cui generare i figli
%Start: Tempo di inizio del nuovo stato
%End: Tempo di fine del nuovo
%NewQty: Quantità del nuovo stato
%ChildStateID: ID dello stato generato
generate_child_state(FatherStateID,Start,End,NewQty,ChildStateID):-
	increment_state_id(ChildStateID),
	user:state(FatherStateID,ResourceID,_StateQty,_StartEnd,Priority,_GranFatherStateID),
	assertz(user:state(ChildStateID,ResourceID,NewQty,[Start,End],Priority,FatherStateID)).
	
/*
Quando una richiesta viene chiusa, vengono generati nuovi stati
create_states(+StateID,+RequestID,-AllocatedStateIDs)
StateID: ID dello stato a partire da cui veranno generati i nuovi
RequestID: ID della richiesta assegnata
AllocatedStateIDs:Lista di ID degli stati allocati alla richiesta
*/
create_states(StateIDs,RequestID,AllocatedStateIDs):-
	user:s_request(RequestID,_Timestamp,_RequestPriority,Duration,_RequestedQty,_RequestorID,_RequestResourceID, _Expiration, _Execution),
	recursive_create_states(StateIDs,RequestID,Duration,AllocatedStateIDs).
recursive_create_states([],_RequestID,_ResidualDuration,[]).
recursive_create_states([StateID|T],RequestID,ResidualDuration,[AllocatedStateID|T2]):-
	generate_state(StateID,RequestID,ResidualDuration,NewResidualDuration,AllocatedStateID),
	recursive_create_states(T,RequestID,NewResidualDuration,T2).


/*
Disablita gli stati asserendoli come fatti usati
disable_states(+StateIDs)
StateIDs: Lista di ID degli stati da disabilitare
*/
disable_states([]).
disable_states([StateID|Tail]):-
	%sposta lo stato in used_states
	user:used_states(UsedStates),
	user:state(StateID,ResourceID,StateQtyList,[Start,End], Priority, FatherStateID),
	add_elem(UsedStates,state(StateID,ResourceID,StateQtyList,[Start,End], Priority, FatherStateID),NewUsedStates),
	retract(user:used_states(UsedStates)),
	assert(user:used_states(NewUsedStates)),
	retract(user:state(StateID,ResourceID,StateQtyList,[Start,End], Priority, FatherStateID)),
	disable_states(Tail).
	
/*
In seguito all'assegnazione di una richiesta, calcola la nuova quantità disponibile della risorsa
evaluate_new_state_qtylist(+StateID,+RequestID,-NewStateQtyList)
StateID = ID dello stato
RequestID = ID della richiesta
NewStateQtyList = Quantità calcolata
*/
evaluate_new_state_qtylist(StateID,RequestID,NewStateQtyList):-
	state_qtylist(StateID,StateQtyList),
	qtylist_requested(RequestID,RequestedQtyList),
	recursive_evaluate_new_state_qtylist(RequestedQtyList,StateQtyList,NewStateQtyList).
recursive_evaluate_new_state_qtylist([], _StateQtyList, []).
recursive_evaluate_new_state_qtylist([RequestedQty|Tail], StateQtyList, [NewStateQty|Tail2]):-
	evaluate_new_state_qty(StateQtyList,RequestedQty,NewStateQty),
	recursive_evaluate_new_state_qtylist(Tail, StateQtyList,Tail2).
evaluate_new_state_qty(StateQtyList,RequestedQty,NewStateQty):-
	RequestedQty =.. [RequestedName,RequestedNumber],
	find_qty_by_name(StateQtyList,RequestedName,Qty),
	Qty =.. [_Name,Number],
	NewNumber is Number + RequestedNumber,
	NewStateQty =.. [RequestedName,NewNumber].

%********************** CANCEL SCHEDULE **************************
	
/*
Al termine del processo di inferenza è possibile richiedere l'annullamento di uno schedule e, eventualmente rieseguire il processo inferenziale
cancel_schedule(+ScheduleID)
ScheduleID: ID dello schedule da annullare
*/
cancel_schedule(ScheduleID):-
	user:schedule(ScheduleID,RequestID,StateID),
	retract(user:schedule(ScheduleID,RequestID,StateID)),
	remove_used_request(RequestID),
	successor_states(StateID,SuccessorIDs),
	active_states(ActiveStateIDs),
	append(SuccessorIDs,[StateID],FatherAndSonsList), %nel caso lo stato dello schedule da annullare sia una foglia
	intersection(FatherAndSonsList,ActiveStateIDs,IntersectionStateIDs),
	restore_state_list(IntersectionStateIDs,RequestID).

/*
In seguito all'annullamento di uno schedule, sarà necessario riassegnare la quantità alloccata agli stati attivi figli dello stato assegnato alla richiesta
restore_state_list(+StateIDs,+RequestID)
StateIDs: Lista di ID da ripristinare
RequestID: ID della richeista annullata
*/
restore_state_list([],_RequestID).
restore_state_list([StateID|Tail],RequestID):-
	restore_state(StateID,RequestID),
	restore_state_list(Tail,RequestID).
restore_state(StateID,RequestID):-
	user:state(StateID,ResourceID,StateQty,StartEnd,Priority,FatherStateID),
	evaluate_restored_state_qtylist(StateID,RequestID,RestoredStateQtyList),
	assert(user:state(StateID,ResourceID,RestoredStateQtyList,StartEnd,Priority,FatherStateID)),
	retract(user:state(StateID,ResourceID,StateQty,StartEnd,Priority,FatherStateID)),!.
evaluate_restored_state_qtylist(StateID,RequestID,RestoredStateQtyList):-
	state_qtylist(StateID,StateQtyList),
	qtylist_requested(RequestID,ToRestoreQtyList),
	recursive_evaluate_restored_state_qtylist(ToRestoreQtyList,StateQtyList,RestoredStateQtyList).
recursive_evaluate_restored_state_qtylist([], _StateQtyList, []).
recursive_evaluate_restored_state_qtylist([ToRestoreQty|Tail], StateQtyList, [RestoredStateQty|Tail2]):-
	evaluate_restored_state_qty(StateQtyList,ToRestoreQty,RestoredStateQty),
	recursive_evaluate_restored_state_qtylist(Tail, StateQtyList,Tail2).	
evaluate_restored_state_qty(StateQtyList,ToRestoreQty,RestoredStateQty):-
	ToRestoreQty =.. [ToRestoreName,ToRestoreNumber],
	find_qty_by_name(StateQtyList,ToRestoreName,Qty),
	Qty =.. [_Name,Number],
	RestoredNumber is Number - ToRestoreNumber,
	RestoredStateQty =.. [ToRestoreName,RestoredNumber].
	
/*
In seguito all'annullamento di uno schedule, la s_request sarà rimossa dai fatti usati e tornerà come fatto attivo
remove_used_request(+RequestID)
RequestID: ID della richiesta da annullare
*/
remove_used_request(RequestID):-
	user:used_requests(UsedRequestList),
	get_used_request(RequestID,UsedRequest),
	assert(UsedRequest),
	delete(UsedRequestList,UsedRequest,NewUsedRequestList),
	retract(user:used_requests(UsedRequestList)),
	assert(user:used_requests(NewUsedRequestList)).

	

%*******************************************************

/*
Dato uno stato, verifica che la durata di uno stato, soddifsfi la durata della richiesta 
check_duration_availability(+StateID,+RequestID)
StateID: ID dello stato
Duration: Durata della richiesta
*/
check_duration_availability(StateID,RequestID):-
	user:state(StateID,_ResourceID,_StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	user:s_request(RequestID,Timestamp,_RequestPriority,Duration,_RequestedQty,_RequestorID,_RequestedResourceID,_Expiration,_Execution),
	StartTime >= Timestamp,
	EndTime - StartTime >= Duration.
%Se la richiesta arriva a stato iniziale, il controllo deve essere fatto a partire dal timestamp
check_duration_availability(StateID,RequestID):-
	user:state(StateID,_ResourceID,_StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
	user:s_request(RequestID,Timestamp,_RequestPriority,Duration,_RequestedQty,_RequestorID,_RequestedResourceID,_Expiration,_Execution),
	StartTime < Timestamp,
	EndTime - Timestamp >= Duration.

/*
Dato uno stato candidato per una richiesta, verifica che la richiesta finisca entro il termine della scadenza
check_request_expiration(+StateID,+RequestID)
StateID: ID dello stato
RequestID: Durata della richiesta
*/
check_request_expiration(StateID,RequestID):-
	user:state(StateID,_ResourceID,_StateQty,[StartTime,_EndTime],_Priority,_FatherStateID),
	user:s_request(RequestID,Timestamp,_RequestPriority,Duration,_RequestedQty,_RequestorID,_RequestedResourceID, Expiration, _Execution),
	(Timestamp + Expiration) >= (StartTime + Duration).


/*
Dato uno stato e una richiesta, verifica che ci sia quantità disponibile a sufficienza
availability_state_qtylist(+StateID,+RequestID)
StateID: ID dello stato
RequestID: ID della richiesta da allocare
*/
availability_state_qtylist(StateID,RequestID):-
	state_qtylist(StateID,StateQtyList),
	user:s_request(RequestID,_Timestamp,_Priority,_Duration,RequestedQtyList,_RequestorID,ResourceID,_Expiration,_Execution),
	user:s_resource(ResourceID,_Name,ResourceQtyList),
	recursive_availability_state_qtylist(RequestedQtyList,StateQtyList,ResourceQtyList).
recursive_availability_state_qtylist([], _StateQtyList, _ResourceQtyList).
recursive_availability_state_qtylist([RequestedQty|Tail], StateQtyList, ResourceQtyList):-
	availability_state_qty(StateQtyList,RequestedQty,ResourceQtyList),
	recursive_availability_state_qtylist(Tail, StateQtyList,ResourceQtyList).
availability_state_qty(StateQtyList,RequestedQty,ResourceQtyList):-
	RequestedQty =.. [RequestedName,RequestedNumber],
	find_qty_by_name(StateQtyList,RequestedName,StateQty),
	StateQty =.. [_Name,StateNumber],
	find_qty_by_name(ResourceQtyList,RequestedName,ResourceQty),
	ResourceQty =.. [_ResourceQtyName,ResourceQtyNumber],
	NewNumber is StateNumber + RequestedNumber,
	ResourceQtyNumber >= NewNumber.

	
%************** COLLAPSE STATE *****************

/*
Dato uno stato, ne trova un altro contiguo in termini temporali
find_contiguos_state(+StateID,-ContiguosStateID)
StateID: ID dello stato
ContiguosStateID: ID dello stato contiguo
*/
find_contiguos_state(StateID,ContiguosStateID):-
	active_states(ActiveStateIDs),
	recursive_find_contiguos_state(StateID,ActiveStateIDs,ContiguosStateID).		
recursive_find_contiguos_state(_StateID,[],_ContiguosStateID):- 
	fail.
recursive_find_contiguos_state(StateID,[ContiguosStateID|_T],ContiguosStateID ):-
	is_contiguos_state(StateID,ContiguosStateID),!.
recursive_find_contiguos_state(StateID,[_ContiguosStateID|T],ContiguosStateID):-
	recursive_find_contiguos_state(StateID,T,ContiguosStateID).
/*
Dato uno stato candidato per una richiesta, la durata rimanente da soddisfare, la funzione di soddisfacibilità, restituisce la lista degli stati contigui sufficienti per soddisfare la richiesta
contiguos_states(+StateID,+RequestID,+ResidualDuration,+SatisfiabilityFunctionName,-ContiguosStateIDs)
StateID: ID dello stato candidato
RequestID: ID della richiesta
ResidualDuration: Durata residua della richiesta
SatisfiabilityFunctionName: Nome della funzione di soddisfacibilità
ContiguosStateIDs: Lista di ID degli stati contigui da unire con merge_states
*/
contiguos_states(StateID,RequestID,ResidualDuration,SatisfiabilityFunctionName,[ContiguosStateID]):-
	find_contiguos_state(StateID,ContiguosStateID),
	user:state(ContiguosStateID,_ContiguosResourceID,_ContiguosStateQty,[ContiguosStartTime,ContiguosEndTime], _ContiguosPriority,_ContiguosFatherStateID),
	call(SatisfiabilityFunctionName,RequestID,ContiguosStateID),
	availability_state_qtylist(ContiguosStateID,RequestID),
	ContiguosEndTime - ContiguosStartTime >= ResidualDuration,!. %Se con questo stato la durata è sufficiente
contiguos_states(StateID,RequestID,ResidualDuration,SatisfiabilityFunctionName,[ContiguosStateID|T]):-
	find_contiguos_state(StateID,ContiguosStateID),
	user:state(ContiguosStateID,_ContiguosResourceID,_ContiguosStateQty,[ContiguosStartTime,ContiguosEndTime], _ContiguosPriority,_ContiguosFatherStateID),
	call(SatisfiabilityFunctionName,RequestID,ContiguosStateID),
	availability_state_qtylist(ContiguosStateID,RequestID),
	ContiguosEndTime - ContiguosStartTime < ResidualDuration,
	NeededTime is ResidualDuration - (ContiguosEndTime - ContiguosStartTime),
	contiguos_states(ContiguosStateID,RequestID,NeededTime,SatisfiabilityFunctionName,T).
	
%**************EXPLANATION***********************

%predicato di avvio del modulo di spiegazone
%Lang: codice lingua dell'output
s_explain(Lang) :-
	writeln('************** RICHIESTE ************'),
	nl,
	request_explainer(Lang,RequestExplanation),
	write(RequestExplanation),
	nl,nl,
	writeln('************** SCHEDULE ************'),
	nl,
	schedule_explainer(Lang,ScheduleExplanation),
	write(ScheduleExplanation),
	nl,nl,
	writeln('************** STATI ATTIVI ************'),
	nl,
	state_explainer(Lang,StateExplanation),
	write(StateExplanation),
	nl,nl.


/*
Logga le ragioni per cui una richiesta è stata scartata
discarding_logger(+RequestID,+ConditionName)
RequestID: ID della richiesta scartata
DiscardedReason: Costante corrispondente alla condizione fallita per lo schedule
*/
%se succeded la richiesta è stata chiusa e non scartata
discarding_logger(RequestID,succeded):-
	retract(prosche_settings:discarding_log(RequestID,_Condition)).
discarding_logger(RequestID,ConditionName):-
	retract(prosche_settings:discarding_log(RequestID,_Condition)),
	assert(prosche_settings:discarding_log(RequestID,ConditionName)).


%Avendo una richiesta e un codice lingua in input, produce la spiegazione testuale relativa alla richiesta
%explain_request(+RequestID,+Lang,-Explanation)
%Request: richiesta da spiegare
%Lang: codice lingua
%Explanation: Spiegazione testuale
explain_request(Request,Lang,Explanation):-
	build_request_explanation(Request,Lang,ExplanationTmp),
	atom_concat(ExplanationTmp,'\n',Explanation).
	

build_request_explanation(Request,Lang,Explanation):-
	Request = s_request(RequestID,Timestamp,Priority,Duration,RequestedQty,RequestorID,ResourceID, _Expiration, _Execution),
        flatten_qtylist(RequestedQty,RequestedQtyList),
        atomic_list_concat(RequestedQtyList,' ',RequestedQtyString),
        user:s_resource(ResourceID,Resource,_MaxQty),
        user:requestor(RequestorID,RequestorObject), % dipendenza da requestor
        prosche_settings:message(Lang,request_msg1, Msg1),
        atom_concat('\n',Msg1,Tmp),
        atom_number(RequestIDstr,RequestID),
        atom_concat(Tmp,RequestIDstr,Tmp1),
        prosche_settings:message(Lang,request_msg2, Msg2),
        atom_concat(Tmp1,Msg2,Tmp2),
        atom_number(TimestampStr,Timestamp),
        atom_concat(Tmp2,TimestampStr,Tmp3),
        prosche_settings:message(Lang,request_msg3, Msg3),
        atom_concat(Tmp3,Msg3,Tmp4),
        atom_number(PriorityStr,Priority),
        atom_concat(Tmp4,PriorityStr,Tmp5),
        prosche_settings:message(Lang,request_msg4, Msg4),
        atom_concat(Tmp5,Msg4,Tmp6),
        atom_concat(Tmp6,RequestorObject,Tmp7),
        prosche_settings:message(Lang,request_msg5, Msg5),
        atom_concat(Tmp7,Msg5,Tmp8),
        atom_concat(Tmp8,Resource,Tmp9),
        prosche_settings:message(Lang,request_msg6, Msg6),
        atom_concat(Tmp9,Msg6,Tmp10),
        atom_number(DurationStr,Duration),
        atom_concat(Tmp10,DurationStr,Tmp11),
        prosche_settings:message(Lang,request_msg7, Msg7),
        atom_concat(Tmp11,Msg7,Tmp12),
        atom_concat(Tmp12,RequestedQtyString,Tmp13),
        atom_concat(Tmp13,'\n',Explanation).
        
%Avendo un ID richiesta e un codice lingua in input, produce la spiegazione testuale relativa al fallimento della richiesta
%explain_discarded_request(+RequestID,+Lang,Explanation)
%RequestID: ID della richiesta
%Lang: codice lingua
%Explanation: Spiegazione testuale
explain_discarded_request(RequestID,Lang,Explanation):-
	build_discarded_request_explanation(RequestID,Lang,ExplanationTmp),
	atom_concat(ExplanationTmp,'\n',Explanation).

build_discarded_request_explanation(RequestID,Lang,Explanation):-
	prosche_settings:discarding_log(RequestID,DiscardReason),
        prosche_settings:message(Lang,request_msg1, Msg1),
        atom_concat('\n',Msg1,Tmp),
        atom_number(RequestIDstr,RequestID),
        atom_concat(Tmp,RequestIDstr,Tmp1),
        prosche_settings:message(Lang,discarded_request_msg1, Msg2),
        atom_concat(Tmp1,Msg2,Tmp2),
        prosche_settings:message(Lang,DiscardReason, Msg3),
        atom_concat(Tmp2,Msg3,Tmp3),
        atom_concat(Tmp3,'\n',Explanation).
        
%Avendo un ID schedule e un codice lingua in input, produce la spiegazione testuale relativa allo schedule
%explain_schedule(+ScheduleID,+Lang,-Explanation)
%ScheduleID: ID dello schedule
%Lang: codice lingua
%Explanation: spiegazione testuale
explain_schedule(ScheduleID,Lang,Explanation):-
	build_schedule_explanation(ScheduleID,Lang,ExplanationTmp),
	atom_concat(ExplanationTmp,'\n',Explanation).

build_schedule_explanation(ScheduleID,Lang,Explanation):-
        user:schedule(ScheduleID,RequestID,StateIDs),
        StateIDs = [FirstStateID|_T],
        %si prende il padre per stampare la disponibilità della risorsa prima dell'allocazione della richiesta
        father_state(FirstStateID,FatherStateID),
        get_state(FatherStateID,FatherState),
        FatherState = state(FatherStateID,ResourceID,StateQty,[_StartTime,_FatherEndTime],_FatherPriority,_GranFatherStateID),
       %si prende il primo stato allocato poiché coincide con l'inizio della richiesta
        get_state(FirstStateID,FirstState),
        FirstState = state(FirstStateID,ResourceID,_StateQty,[StartTime,_EndTime],_Priority,_FatherStateID),
        flatten_qtylist(StateQty,StateQtyList),
        atomic_list_concat(StateQtyList,' ',StateQtyString),
        user:s_resource(ResourceID,Resource,_MaxQty),
        prosche_settings:message(Lang,request_msg1, Msg1),
        atom_concat('\n',Msg1,Tmp),
        atom_number(RequestIDstr,RequestID),
        atom_concat(Tmp,RequestIDstr,Tmp1),
        prosche_settings:message(Lang,schedule_msg1, Msg2),
        atom_concat(Tmp1,Msg2,Tmp2),
        atom_number(ScheduleIDstr,ScheduleID),
        atom_concat(Tmp2,ScheduleIDstr,Tmp3),
        prosche_settings:message(Lang,schedule_msg2, Msg3),
        atom_concat(Tmp3,Msg3,Tmp4),
        atom_number(StartTimeStr,StartTime),
        atom_concat(Tmp4,StartTimeStr,Tmp5),
        prosche_settings:message(Lang,schedule_msg4, Msg4),
        atom_concat(Tmp5,Msg4,Tmp6),
        atom_concat(Tmp6,Resource,Tmp7),
        prosche_settings:message(Lang,schedule_msg5, Msg5),
        atom_concat(Tmp7,Msg5,Tmp8),
        atom_concat(Tmp8,StateQtyString,Tmp9),
        atom_concat(Tmp9,'\n',Explanation).
        
%Avendo un ID state e un codice lingua in input, produce la spiegazione testuale relativa allo stato
%explain_state(+StateID,+Lang,-Explanation)
%StatoID: ID dello stato
%Lang: codice lingua
%Explanation: spiegazione testuale
explain_state(StateID,Lang,Explanation):- 
	build_state_explanation(StateID,Lang,ExplanationTmp),
	atom_concat(ExplanationTmp,'\n',Explanation).

build_state_explanation(StateID,Lang,Explanation):- 
        user:state(StateID,ResourceID,StateQty,[StartTime,EndTime],_Priority,_FatherStateID),
        flatten_qtylist(StateQty,StateQtyList),
        atomic_list_concat(StateQtyList,' ',StateQtyString),
        user:s_resource(ResourceID,Resource,_MaxQty),
        prosche_settings:message(Lang,state_msg1, Msg1),
        atom_concat('\n',Msg1,Tmp),
        atom_concat(Tmp,Resource,Tmp1),
        prosche_settings:message(Lang,state_msg2, Msg2),
        atom_concat(Tmp1,Msg2,Tmp2),
        atom_number(StateIDstr,StateID),
        atom_concat(Tmp2,StateIDstr,Tmp3),
        prosche_settings:message(Lang,state_msg3, Msg3),
        atom_concat(Tmp3,Msg3,Tmp4),
        atom_number(StartTimeStr,StartTime),
        atom_concat(Tmp4,StartTimeStr,Tmp5),
        atom_concat(Tmp5,' - ',Tmp6),
        atom_number(EndTimeStr,EndTime),
        atom_concat(Tmp6,EndTimeStr,Tmp7),
        prosche_settings:message(Lang,state_msg4, Msg4),
        atom_concat(Tmp7,Msg4,Tmp8),
        atom_concat(Tmp8,StateQtyString,Tmp9),
        atom_concat(Tmp9,'\n',Explanation).


%Cicla la funzione explain_request su tutte le richieste
%request_explainer(+Lang,Explanation)
%Lang: codice lingua
%Explanation: spiegazione testuale
request_explainer(Lang,Explanation):-
	user:used_requests(UsedRequestList),
	sort(UsedRequestList,OrderedUsedRequestList),
	recursive_request_explainer(OrderedUsedRequestList,Lang,Explanation).
recursive_request_explainer([],_Lang,'').
recursive_request_explainer([Request|T],Lang,Explanation):-
        explain_request(Request,Lang,ExplanationStep),!,
        recursive_request_explainer(T,Lang,ExplanationRecur),
        atom_concat(ExplanationStep,ExplanationRecur,Explanation).

        
%Cicla la funzione explain_schedule su tutte gli schedule
%schedule_explainer(+Lang,-Explanation)
%Lang: codice lingua
%Explanation: spiegazione testuale
schedule_explainer(Lang,Explanation):-
	findall(ScheduleID,user:schedule(ScheduleID,_RequestID,_StateIDs),ScheduleIDs),
	recursive_schedule_explainer(ScheduleIDs,Lang,SchExplanation),
	discarded_request_explainer(Lang,DiscExplanation),
	atom_concat(SchExplanation,DiscExplanation,Explanation).
recursive_schedule_explainer([],_Lang,'').
recursive_schedule_explainer([Schedule|T],Lang,Explanation):-
        explain_schedule(Schedule,Lang,ExplanationStep),!,
        recursive_schedule_explainer(T,Lang,ExplanationRecur),
        atom_concat(ExplanationStep,ExplanationRecur,Explanation).

%Cicla la funzione explain_request su tutte le discarded request
%discarded_request_explainer(+Lang,-Explanation)
%Lang: codice lingua
%Explanation: spiegazione testuale
discarded_request_explainer(Lang,Explanation):-
	findall(RequestID,user:discarded_request(RequestID),DiscardedRequestIDs),
	recursive_discarded_request_explainer(DiscardedRequestIDs,Lang,Explanation).
recursive_discarded_request_explainer([],_Lang,'').
recursive_discarded_request_explainer([Request|T],Lang,Explanation):-
        explain_discarded_request(Request,Lang,ExplanationStep),!,
        recursive_discarded_request_explainer(T,Lang,ExplanationRecur),
        atom_concat(ExplanationStep,ExplanationRecur,Explanation).
	


        
%Cicla la funzione explain_state su tutte gli stati attivi
%state_explainer(+Lang,-Explanation)
%Lang: codice lingua
%Explanation: spiegazione testuale
state_explainer(Lang,Explanation):-
	active_states(ActiveStateIDs),
	recursive_state_explainer(ActiveStateIDs,Lang,Explanation).
recursive_state_explainer([],_Lang,'').
recursive_state_explainer([StateID|T],Lang,Explanation):-
        explain_state(StateID,Lang,ExplanationStep),!,
        recursive_state_explainer(T,Lang,ExplanationRecur),
        atom_concat(ExplanationStep,ExplanationRecur,Explanation).
