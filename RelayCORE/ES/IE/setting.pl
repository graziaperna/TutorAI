:- use_module(library(lists)).

% PREDICATI DI SETTING DELL'AMBIENTE
:- dynamic fact/3. %rappresentazione dei fatti della kb
:- dynamic rfact/4. %rappresentazione dei fatti grezzi della kb
:- dynamic rule/5. %rappresentazione delle regole della kb
:- dynamic idf/1. %id dei fatti
:- dynamic idr/1. %id delle regole
:- dynamic idg/1. %id delle variabili globali
:- dynamic initfacts/1. %lista dei fatti originali
:- dynamic initrules/1. %lista delle regole originali
:- dynamic initglobals/1. %lista delle variabili globali
:- dynamic used_fact/3. %rappresentazione dei fatti usati nel processo inferenziale
:- dynamic used_rule/5. %regole usate nel processo inferenziale
:- dynamic irule/3. %indicizzazione delle regole
:- dynamic tracker/2. %tracking del percorso di risoluzione forward
:- dynamic inferedrule/2. %regole utilizzate per inferire la nuova conoscenza durante il processo deduttivo

% PREDICATI DI SETTING DEL GIE
:- dynamic confl/1. %lista delle regole in conflitto con quella considerata durante il processo inferenziale
:- dynamic fireset/1. %lista dei fatti già considerati durante il processo inferenziale
:- dynamic verified/2. %rappresenta una regola che è stata verificata
:- dynamic frule/5. %regole già considerate durante il processo inferenziale

% PREDICATI DI I/0
:- dynamic curr_kb/1. %kb corrente
:- dynamic curr_rudekb/1. %kb grezza corrente
:- dynamic fuzzycheck/1. %validazione dell'utilizzo del modulo di fuzzyness
:- dynamic curr_flog/1. %id del file .log
:- dynamic curr_task/1. %scelta da menu corrente
:- dynamic ufunctinput/4. %rappresentazione delle membership di input % Morisco-Petrera
:- dynamic ufunctoutput/4. %rappresentazione delle membership di output % Morisco-Petrera
:- dynamic final_adjustment/2. %rappresentazione del valore di regolazione da applicare al setting ambientale
:- dynamic(frame/2). %rappresentazione dei frame definiti in gie/ FRAME

:- dynamic fact_presence/1.
:- dynamic max_id/1.
:- dynamic explained_fact/1.
:- dynamic explained_condition_rule/2.
:- dynamic final_k/3.

% Setting iniziale dell'ambiente
curr_task(0).
curr_kb(unknow).
curr_flog(unknow).
idf([]).
idr([]).
idg([]).
initfacts([]).
initrules([]).
initglobals([]).
fireset([]).
fact_presence(0). % variabile che indica se ci sono o meno semplici fact.
max_id(0).  % terrà traccia del massimo ID tra i fatti inizialmnete presenti nella KB
explained_fact( 0 ).
explained_condition_rule(0 , 0).
final_k(0 , 1, 0).


