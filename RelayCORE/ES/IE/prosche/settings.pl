:- dynamic prosche_settings:discarding_log/2.

:- dynamic user:schedule/3.
:- dynamic user:state/6.
:- dynamic user:s_request/9.
:- dynamic user:used_requests/1.
:- dynamic user:used_states/1.
:- dynamic user:discarded_request/1.


% *********** MESSAGGI SPIEGAZIONE *********

%message(Language,Code,Message)
prosche_settings:message(it, request_msg1, 'La richiesta con ID ').
prosche_settings:message(it, request_msg2, ' è avvenuta alle ').
prosche_settings:message(it, request_msg3, ' con priorità ').
prosche_settings:message(it, request_msg4, ' da parte di ').
prosche_settings:message(it, request_msg5, ' verso la risorsa ').
prosche_settings:message(it, request_msg6, ' per una durata di ').
prosche_settings:message(it, request_msg7, ' secondi e la quantità di ').

prosche_settings:message(it, schedule_msg1, ', presa in carico dallo schedule ').
prosche_settings:message(it, schedule_msg2, ', sarà processata alle ').
prosche_settings:message(it, schedule_msg3, ' fino alle ').
prosche_settings:message(it, schedule_msg4, ' perché lo stato della risorsa ').
prosche_settings:message(it, schedule_msg5, ' al momento della schedulazione era pari a ').


prosche_settings:message(it, discarded_request_msg1, ' non è stata schedulata per la seguente motivazione: ').



prosche_settings:message(it,state_msg1, 'Lo stato della risorsa ' ).
prosche_settings:message(it,state_msg2, ' con ID ' ).
prosche_settings:message(it,state_msg3, ' relativo al periodo ' ).
prosche_settings:message(it,state_msg4, ' ha le seguenti disponibilità: ' ).


prosche_settings:message(it,resource_unavailable, 'disponibilità della risorsa non sufficiente').
prosche_settings:message(it,not_satisfiable, 'dichiesta non soddisfacibile').
prosche_settings:message(it,not_available_duration, 'disponibilità temporale della risorsa non sufficiente').
prosche_settings:message(it,state_too_late, 'scadenza della richiesta non soddisfatta').
