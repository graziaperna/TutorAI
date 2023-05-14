%****************************************************

%Restituisce la prossima richiesta con priorità più alta
%next_request_by_priority(RequestIDs,NextRequest)
%RequestIDs: Lista degli ID delle richieste
%NextRequestID: ID della prossima richiesta
next_request_by_priority(RequestIDs,NextRequestID):- 
	my_max(RequestIDs, is_higher_priority_request, NextRequestID).
	
%Restituisce la prossima richiesta con timestamp di richiesta inferiore (politica FIFO)
%next_request_by_fifo(RequestIDs,NextRequest)
%RequestIDs: Lista degli ID delle richieste
%NextRequestID: ID della prossima richiesta
next_request_by_fifo(RequestIDs,NextRequestID):- 
	my_max(RequestIDs, is_earlier_request, NextRequestID).

%Restituisce la prossima richiesta con timestamp di richiesta superiore (politica LIFO)
%next_request_by_lifo(RequestIDs,NextRequest)
%RequestIDs: Lista degli ID delle richieste
%NextRequestID: ID della prossima richiesta
next_request_by_lifo(RequestIDs,NextRequestID):- 
	my_max(RequestIDs, is_later_request, NextRequestID).


%Restituisce la prossima richiesta con scadenza richiesta più vicina 
%next_request_by_expiration(RequestIDs,NextRequest)
%RequestIDs: Lista degli ID delle richieste
%NextRequestID: ID della prossima richiesta
next_request_by_expiration(RequestIDs,NextRequestID):- 
	my_max(RequestIDs, is_earlier_request_expiration, NextRequestID).
	

	
%*************************************

/*
Ordina gli stati in base alla priorità
oreder_states_by_priority(+StateIDs,-OrderedStatesIDs)
StateIDs: Lista ID degli stati
OrderedStatesIDs: Lista ordinata degli ID degli stati
*/
oreder_states_by_priority(StateIDs,OrderedStatesIDs):-
	bubblesort(StateIDs, is_higher_priority_state, OrderedStatesIDs).
	

/*
Funzione che determina la soddisfaciblità della richiesta 
satisfiability(+RequestID,+StateID)
RequestID: ID della richiesta
StateID: ID dello stato candidato
*/
satisfiability(_RequestID,_StateID).
