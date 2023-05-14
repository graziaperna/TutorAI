:- use_module(library(system)).

%**************** ID ********************
	
%Calcola il successivo ScheduleID
%increment_schedule_id(-NextScheduleID)
%NextScheduleID: prossimo ScheduleID 
increment_schedule_id(NextScheduleID):-
	findall(ScheduleID,user:schedule(ScheduleID,_RequestID,_StateIDs),IDs),
	max_list(IDs,MaxScheduleID),
	NextScheduleID is MaxScheduleID +1.
%Se non ci sono schedule parte da 1
increment_schedule_id(1).


%Calcola il successivo StateID
%increment_state_id(-NextStateID)
%NextStateID: prossimo StateID 
increment_state_id(NextStateID):-
	findall(StateID,user:state(StateID,_ResourceID,_StateQty,_StartEnd,_Priority,_FatherStateID),IDs),
	max_list(IDs,MaxStateID),
	NextStateID is MaxStateID +1.
%Gli stati dovranno essere obbligatoriamente asseriti nei fatti iniziali, pertanto non si partirà da 0
	
	


%**************** FILTERING CONDITIONS ********************


%Confronta due stati sulla base delle priorità. Restituirà vero se il primo ha priorità maggiore del secondo
%is_higher_priority_state(+StateID1,+StateID2)
%StateID1: ID della stato 1
%StateID2: ID della stato 2
is_higher_priority_state(StateID1,StateID2):-
	user:state(StateID1,_ResourceID1,_StateQty1,_StartEnd1,Priority1,_FatherStateID1),
	user:state(StateID2,_ResourceID2,_StateQty2,_StartEnd2,Priority2,_FatherStateID2),
	Priority1 < Priority2,!.	
is_higher_priority_state(StateID1,StateID2):-
	user:state(StateID1,_ResourceID1,_StateQty1,[StartTime1,_EndTime1],Priority1,_FatherStateID1),
	user:state(StateID2,_ResourceID2,_StateQty2,[StartTime2,_EndTime2],Priority2,_FatherStateID2),
	Priority1 = Priority2,
	StartTime1 > StartTime2.
	
is_greater_id_state(StateID1,StateID2):-
	user:state(StateID1,_ResourceID1,_StateQty1,_StartEnd1,_Priority1,_FatherStateID1),
	user:state(StateID2,_ResourceID2,_StateQty2,_StartEnd2,_Priority2,_FatherStateID2),
	StateID1 > StateID2.	
	
/*
Determina se due stati sono contigui in termini temporali
is_contiguos_state(+StateID1,+StateID2)
%StateID1: ID della stato 1
%StateID2: ID della stato 2
*/	
is_contiguos_state(StateID1,StateID2) :-
	user:state(StateID1,ResourceID,_StateQty1,[_StartTime1,EndTime1],_Priority1,_FatherStateID1),
	user:state(StateID2,ResourceID,_StateQty2,[StartTime2,_EndTime2],_Priority2,_FatherStateID2),
	EndTime1 = StartTime2.
	
%Confronta due richieste sulla base delle priorità. Restituirà vero se il primo ha priorità maggiore del secondo
%is_higher_priority_request(+RequestID1,+RequestID2)
%RequestID1: ID della richiesta 1
%RequestID2: ID della richiesta 2
is_higher_priority_request(RequestID1,RequestID2):-
	user:s_request(RequestID1,_Timestamp1,Priority1,_Duration1,_RequestedQty1,_RequestorID1,_ResourceID1, _Expiration1, _Execution1),
	user:s_request(RequestID2,_Timestamp2,Priority2,_Duration2,_RequestedQty2,_RequestorID2,_ResourceID2, _Expiration2, _Execution2),
	Priority1 > Priority2,!.
is_higher_priority_request(RequestID1,RequestID2):-
	user:s_request(RequestID1,Timestamp1,Priority1,_Duration1,_RequestedQty1,_RequestorID1,_ResourceID1, _Expiration1, _Execution1),
	user:s_request(RequestID2,Timestamp2,Priority2,_Duration2,_RequestedQty2,_RequestorID2,_ResourceID2, _Expiration2, _Execution2),
	Priority1 = Priority2,
	Timestamp1 < Timestamp2.

%Confronta due richieste sulla base del timestamp di arrivo. Restituirà vero se il primo è precedente al secondo
%is_earlier_request(+RequestID1,+RequestID2)
%RequestID1: ID della richiesta 1
%RequestID2: ID della richiesta 2
is_earlier_request(RequestID1,RequestID2):-
	user:s_request(RequestID1,Timestamp1,_Priority1,_Duration1,_RequestedQty1,_RequestorID1,_ResourceID1, _Expiration1, _Execution1),
	user:s_request(RequestID2,Timestamp2,_Priority2,_Duration2,_RequestedQty2,_RequestorID2,_ResourceID2, _Expiration2, _Execution2),
	Timestamp1 < Timestamp2.
	
	
%Confronta due richieste sulla base della scadenza. Restituirà vero se il primo è precedente al secondo
%is_earlier_request_expiration(+RequestID1,+RequestID2)
%RequestID1: ID della richiesta 1
%RequestID2: ID della richiesta 2
is_earlier_request_expiration(RequestID1,RequestID2):-
	user:s_request(RequestID1,Timestamp1,_Priority1,_Duration1,_RequestedQty1,_RequestorID1,_ResourceID1, Expiration1, _Execution1),
	user:s_request(RequestID2,Timestamp2,_Priority2,_Duration2,_RequestedQty2,_RequestorID2,_ResourceID2, Expiration2, _Execution2),
	(Timestamp1+Expiration1) < (Timestamp2+Expiration2).

	
%Confronta due richieste sulla base della scadenza. Restituirà vero se il primo è successivo al secondo
%is_later_request_expiration(+RequestID1,+RequestID2)
%RequestID1: ID della richiesta 1
%RequestID2: ID della richiesta 2
is_later_request_expiration(RequestID1,RequestID2):-
	not(is_earlier_request_expiration(RequestID1,RequestID2)).

%Confronta due richieste sulla base del startTime. Restituirà vero se il primo è successivo al secondo
%is_later_request(+RequestID1,+RequestID2)
%RequestID1: ID della richiesta 1
%RequestID2: ID della richiesta 2
is_later_start_schedule(ScheduleID1,ScheduleID2):-
	user:schedule(ScheduleID1,_RequestID1,StartTime1),
	user:schedule(ScheduleID2,_RequestID2,StartTime2),
	StartTime1 > StartTime2.

%Confronta due numeri. Restituirà vero se il primo è maggiore del secondo
%is_earlier_request(+RequestID1,+RequestID2)
%X: primo numero
%Y: secondo numero	
is_greater(X,Y):-
	X > Y.

%**************** STATES ********************
	
/*
Dato uno stato, restituisce lo stato padre
father_state(+StateID,-FatherStateID)
StateID: ID dello stato figlio
FatherStateID: ID dello stato padre
*/
father_state(StateID,FatherStateID):-
	get_state(StateID,State),
	State = state(StateID,_ResourceID,_StateQty,_StartEnd,_Priority,FatherStateID).	
	
/*
Data una lista di stati, restituisce una lista costituita dai primi figli di ogni stato dato in input
first_childrenOfstates(+FatherStateIDs,-ChildIDs)
FatherStateIDs: Lista di ID degli stati padre
ChildIDs: Lista di ID degli stati figlio
*/
first_childrenOfstates([],[]).
first_childrenOfstates([FatherStateID|Tail],[ChildID|Tail2]):-
	first_child_state(FatherStateID,ChildID),
	first_childrenOfstates(Tail,Tail2).

/*
Dato uno stato padre, restituisce lo stato primo figlio
first_child_state(+FatherStateID,-FirstChildStateID)
FatherStateID: ID dello stato padre
FirstChildStateID: ID del primo stato figlio
*/	
first_child_state(FatherStateID,FirstChildStateID):-
	children_states(FatherStateID,ChildrenStateIDs),
	min_list(ChildrenStateIDs,FirstChildStateID).

/*
Dato uno stato padre, restituisce lo stato secondo figlio
first_child_state(+FatherStateID,-SecondChildStateID)
FatherStateID: ID dello stato padre
SecondChildStateID: ID del secondo stato figlio
*/		
second_child_state(FatherStateID,SecondChildStateID):-
	children_states(FatherStateID,ChildrenStateIDs),
	ChildrenStateIDs = [_First,_Second], %assicurarsi che i figli sono 2
	max_list(ChildrenStateIDs,SecondChildStateID).

/*
Dato uno stato padre, restituisce gli stati figli
children_states(+FatherStateID,-ChildrenStateIDs)
FatherStateID: ID dello stato padre
ChildrenStateIDs: Lista ID degli stati figli
*/	
children_states(FatherStateID,ChildrenStateIDs):-
	user:used_states(UsedStates),
	findall(state(StateID,ResourceID,StateQty,StartEnd,Priority,Father),user:state(StateID,ResourceID,StateQty,StartEnd,Priority,Father),ActiveStates),
	append(UsedStates,ActiveStates,States),
	recursive_children_states(States,FatherStateID,ChildrenStateIDs).
recursive_children_states([],_FatherStateID,[]).
recursive_children_states([State|T],FatherStateID,[ChildStateID|OtherChildStateID]):-
	State = user:state(ChildStateID,_ResourceID,_StateQty,_StartEnd,_Priority,FatherStateID),!,
	recursive_children_states(T,FatherStateID,OtherChildStateID).
recursive_children_states([_State|T],FatherStateID,ChildrenStateID):-
	recursive_children_states(T,FatherStateID,ChildrenStateID).

/*
Dato uno stato, restituisce gli stati successori
successor_states(+StateID,-SuccessorIDs)
StateID: ID dello stato
SuccessorIDs: Lista ID degli stati successori
*/

successor_states([],[]).
successor_states([StateID|Tail],SuccessorIDs):-
	children_states(StateID,ChildrenIDs),
	successor_states(Tail,SuccessorIDsTmp1),
	successor_states(ChildrenIDs,SuccessorIDsTmp2),
	append(ChildrenIDs,SuccessorIDsTmp1,SuccessorIDsTmp3),
	append(SuccessorIDsTmp3,SuccessorIDsTmp2,SuccessorIDs).



/*
Restituisce gli stati attivi, ossia non usati
active_states(-ActiveStateIDs)
ActiveStateIDs: Lista di ID degli stati attivi
*/
active_states(ActiveStateIDs):-
	findall(StateID,user:state(StateID,_ResourceID,_StateQty,_StartEnd,_Priority,_FatherStateID),ActiveStateIDs).
	

/*
Restituisce le richieste pendenti, ossia non usate
open_requests(-OpenRequestIDs)
OpenRequestIDs: Lista di ID delle richieste pendenti
*/
open_requests(OpenRequestIDs):-
	findall(RequestID,user:s_request(RequestID,_Timestamp,_Priority,_Duration,_RequestedQty,_RequestorID,_ResourceID,_Expiration, now),OpenRequestIDs).


/*
Dato un ID restituisce lo stato sia che sia un fatto già usato (asserito in used_states) che non usato
get_state(+StateID,-State)
StateID: ID dello stato
State: Stato restituito
*/
get_state(StateID,State):-
	user:used_states(UsedStates),
	findall(state(StateID,ResourceID,StateQty,StartEnd,Priority,Father),user:state(StateID,ResourceID,StateQty,StartEnd,Priority,Father),ActiveStates),
	append(UsedStates,ActiveStates,StateList),
	recursive_get_state(StateList,StateID,State).
recursive_get_state([],_StateID,_State):- 
	fail.
recursive_get_state([State|_T],StateID,State):-
	State = state(StateID,_ResourceID,_StateQty,_StartEnd, _Priority, _FatherStateID),!.
recursive_get_state([_State|T],StateID,State):-
	recursive_get_state(T,StateID,State).
	

/*
Dato uno stato e una richiesta, calcola il tempo ancora necessario supponendo l'intero utilizzo dello stato.
Presuppone che lo stato in questione non abbia tempo a sufficienza per soddisfare la richiesta
needed_time(+StateID,+RequestID,-NeededTime)
StateID: ID dello stato
RequestID: ID della richiesta
NeededTime: Ulteriore tempo necessario
*/
needed_time(StateID,RequestID,NeededTime):-
	user:s_request(RequestID,_Timestamp,_RequestPriority,Duration,_RequestedQty,_RequestorID,ResourceID, _Expiration, _Execution),
	user:state(StateID,ResourceID,_StateQty,[Start,End], _StatePriority, _FatherStateID),
	NeededTime is Duration - (End - Start).

/*
Data una lista di stati, ne somma tutte le durate
sum_state_times(+StateIDs,-Sum)
StateIDs: lista di ID degli stati
Sum: Somma dei tempi degli stati 
*/
sum_state_times([],0).
sum_state_times([StateID|Tail],Sum):-
	user:state(StateID,_ResourceID,_StateQty,[Start,End], _StatePriority, _FatherStateID),
	Time is End - Start,
	sum_state_times(Tail,PartialSum),
	Sum is PartialSum + Time.


%**************** STATE/RESOURCE QTY ********************
%Filtra gli stati in base al timestamp di richiesta. Scarta gli stati temporalmente superati.
%filter_states(+StateIDs,+RequestID,-FilteredStateIDs)
%StateIDs: Lista di ID degli stati
%RequestID: ID della richiesta
%FilteredStateIDs: Lista degli ID filtrati
filter_states(StateIDs,RequestID,FilteredStateIDs):-
	filter(StateIDs,RequestID,is_state_not_over,FilteredStateIDs).
	
%Dato uno Stato e una Richiesta, restituisce vero se lo stato è relativo ad un intervallo temporale successivo al timestamp della richiesta
%is_state_not_over(+StateID,+RequestID):-
%StateID: ID dello stato
%RequestID: ID della richiesta
is_state_not_over(RequestID,StateID):-
	user:state(StateID,_ResourceID,_StateQtyList,[_Start,End],_Priority,_FatherStateID),
	user:s_request(RequestID,Timestamp,_RequestPriority,_Duration,_RequestedQty,_RequestorID,_RequestedResourceID, _Expiration, _Execution),
	End > Timestamp.

/*
Dato uno stateID, restituisce la quantità della risorsa nello specifico stato
state_qtylist(+StateID,-StateQty)
StateID: ID dello stato
StateQty: QUantità della risorsa nello StateID
*/
state_qtylist(StateID,StateQtyList):-
	user:state(StateID,_ResourceID,StateQtyList,_StartEnd,_Priority,_FatherStateID).	
	
/*
Dato un requestID, restituisce la quantità della risorsa richiesta
qtylist_requested(+RequestID,-RequestedQty)
RequestID: ID della richiesta
RequestedQty: QUantità richiesta
*/
qtylist_requested(RequestID,RequestedQtyList):-
	user:s_request(RequestID, _Timestamp, _Priority, _Duration, RequestedQtyList, _RequestorID, _ResourceID, _Expiration, _Execution).


/*
Data una lista di quantità di una risorsa (formata da predicati e valori), la appittisce creando una lista dove i predicati e relativi valori sono elementi
flatten_qtylist(+ResourceQtyList,-ListQty)
ResourceQtyList: Lista delle quantità della risorsa. ex: [watt(200),..]
ListQty: Lista appiattita. ex: [watt,200,..]
*/
flatten_qtylist([],[]).
flatten_qtylist([ResourceQty|T],[Qty,NameQty|T2]):-
	ResourceQty =.. [NameQty, Qty],
	flatten_qtylist(T,T2).


/*
Data una lista di quantità, e un nome, restiusce l'intero predicato relativo al nome cercato
find_qty_by_name(+QtyList,+Name,-Qty)
QtyList: Lista di quantità. ex: [watt(200),..]
Name: Nome della quantità. ex: watt
Qty: Predicato completo. ex: watt(200)
*/
find_qty_by_name([],_Name,_Qty):-
	fail.
find_qty_by_name([Qty|_Tail],Name,Qty):-
	Qty =.. [Name,_Number].
find_qty_by_name([Qty|Tail],Name,Qty):-
	find_qty_by_name(Tail,Name,Qty).
	
	
	
%**************** REQUEST ********************
	
/*
Dato un ID restituisce una request già utilizzata e quindi asserita in used_requests
get_used_request(+RequestID,-UsedRequest))
RequestID: ID della richiesta
UsedRequest: richiesta restituita
*/	
get_used_request(RequestID,UsedRequest):-
	user:used_requests(UsedRequestList),
	recursive_get_used_request(UsedRequestList,RequestID,UsedRequest).	
recursive_get_used_request([],_RequestID,_Request):- 
	fail.
recursive_get_used_request([Request|_T],RequestID,Request):-
	Request = s_request(RequestID, _Timestamp, _Priority, _Duration, _RequestedQta, _RequestorID, _ResourceID, _Expiration, _Execution),!.
recursive_get_used_request([_Request|T],RequestID,Request):-
	recursive_get_used_request(T,RequestID,Request).

	