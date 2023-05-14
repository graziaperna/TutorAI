/*
s_request(RequestID, Timestamp, Priority, Duration, RequestedQty, RequestorID, ResourceID, Expiration, Execution).
- ID della richiesta
- Timestamp di arrivo della richiesta
- Priorità della richiesta
- Durata di utilizzo della risorsa
- Lista di quantità della risorsa richiesta
- ID del richiedente
- ID della risorsa
- Scadenza della richiesta definito come periodo di tempo dal timestamp di arrivo
- Momento di esecuzione della richiesta, se immediatamente (now), o in differita (after)

La quantità è una lista perché potrebbero essere più di una
*/
s_request(1, 1533109063, 7, 100, [watt(4500)], 1, 1, 180000, now).
s_request(2, 1533109064, 9, 200, [watt(500)], 1, 1, 180, now).
s_request(3, 1533109065, 4, 300, [watt(550)], 2, 1, 450000, after).
s_request(4, 1533109066, 3, 200, [watt(250)], 1, 1, 450000, now).
s_request(5, 1533109067, 10,400, [litri(550)], 2, 2,180000, now).
s_request(6, 1533109068, 3, 500, [watt(300)], 2, 1, 300000, now).

/*
s_resource(ID,Resource,QtyList)
- ID della risorsa
- Nome della risorsa
- Lista delle disponibilità massime della risorsa
*/
s_resource(1,energia,[watt(3000)]).
s_resource(2,acqua,[litri(1000)]).


%deve esserci almeno uno stato iniziale
/*
state(ID,ResourceID,StateQty,[Start,End], Priority, FatherStateID).
- ID dello stato
- ID della risorsa
- Quantità corrente della risorsa nel periodo Start-End
- Timestamp di inizio e fine del periodo a cui si riferisce lo stato
- Priorità dello stato
- FatherState è l'id dello stato da cui è stato generato, se [] è uno stato radice.
Gli stati possono avere priorità differenti. Utile nel caso delle fasce orarie
*/
state(1,1,[watt(300)],[1533109063,1533109163],10, none).
state(2,1,[watt(1400)],[1533109163,1533119163],9, none).
state(3,2,[litri(250)],[1533109063,1533111063],9, none).


/*
Schedule(ScheduleID,RequestID,StateID)
- ID dello schedule
- ID della richiesta
- Lista di ID degli stati assegnati alla richiesta

schedule(ScheduleID,RequestID,StateIDs)
*/

%requestor(RequestorID,Requestor).
requestor(1,lavatrice).
requestor(2,lavastoviglie).

fact(1,scheduling_plan(not_ready),1).
