%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                     Coin identifier
%
%  Sistema esperto per la classificazione di una moneta 
% 
%  Autore: Giuliana Spinelli
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

:- reconsult('seva_start').

startMonete :-
 nl,
 write('*************************************************************************************************'),
 nl,
 write('caricamento base di conoscenza'),
 loader,
 nl,
 write('*************************************************************************************************'),
 nl,
 write('Coin identifier'),
 nl,
 write('Sistema esperto per la classificazione di una moneta e attribuzione del suo valore'),
 nl,
 nl,
 write('Autrice:Giuliana Spinelli'),
 nl,
 nl,
 nl,
 write('*************************************************************************************************'),
 nl,
 inizio(0).

inizio(0):-
 nl,
 write('-------------------------------------------------------------------------------------------------'),
 nl,
 write('Questionario per la catalogazione della moneta'),
 nl,
 nl,
 chiedi(materiale),
 nl,
 chiedi(periodo),
 nl,
 chiedi(peso),
 nl,
 chiedi(dimensione),
 nl,
 chiedi(contorno),
 nl,
 write('-------------------------------------------------------------------------------------------------'),
 nl,
 nl,
 write('Inserisci la domanda (es. identificata(moneta,"100 lire Guglielmo Marconi").)'),
 gui_popup(info4),
 read(Goal),
 gui_backmodule(beginning),
 backward(Goal),
 gui_backmodule(closing).
 

%domanda riferita alla tipologia di metallo di cui è costituita la moneta
%per materiale prezioso si intende oro e argento
chiedi(materiale):- 
 write('La moneta e" stata coniata con materiale prezioso o comune?'),
 nl,
 write('prezioso/comune'),
 nl,
 write('Risposta: '),
 read(Materiale),
 riconosciMateriale(Materiale),
 !.

riconosciMateriale(Materiale):- 
 materiale1(Materiale),
 assertz(fact(1,fatta(moneta,'materiale prezioso'),1)).

riconosciMateriale(Materiale):- 
 materiale2(Materiale),
 assertz(fact(2,not(fatta(moneta,'materiale prezioso')),1)).
 
riconosciMateriale(stampakb):- 
  stampakb,
  chiedi(materiale).
   
 riconosciMateriale(stamparegole):- 
   stamparegole,
   chiedi(materiale).
 
    
 riconosciMateriale(stampafatti):- 
   stampafatti,
   chiedi(materiale).
     
  riconosciMateriale(chiediregola):- 
   chiediregola,
   chiedi(materiale).  
    
 riconosciMateriale(_):- 
 nl,
 write('Risposta sconosciuta,riprova'),
 nl,
 chiedi(materiale).
 


materiale1(prezioso).
materiale2(comune).

%domanda riferita alla data in cui è stata coniata la moneta 
chiedi(periodo):- 
 write('La moneta e" stata coniata prima del 1970, nel prima e" compreso il 1970)?'),
 nl,
 write('prima/dopo'),
 nl,
 write('Risposta: '),
 read(Periodo),
 riconosciPeriodo(Periodo),
 !.

riconosciPeriodo(Periodo):- 
 periodo1(Periodo),
 assertz(fact(3,fatta(moneta,'prima del 1970'),1)).

riconosciPeriodo(Periodo):- 
 periodo2(Periodo),
 assertz(fact(4,not(fatta(moneta,'prima del 1970')),1)).
 
 riconosciPeriodo(stampakb):- 
  stampakb,
  chiedi(periodo).
   
 riconosciPeriodo(stamparegole):- 
   stamparegole,
   chiedi(periodo).
     
 riconosciPeriodo(stampafatti):- 
   stampafatti,
   chiedi(periodo).

riconosciPeriodo(chiediregola):- 
   chiediregola,
   chiedi(periodo).

  
riconosciPeriodo(_):- 
 nl,
 write('Risposta sconosciuta,riprova'),
 nl,
 chiedi(periodo).

periodo1(prima).
periodo2(dopo).

%domanda riferita al peso della moneta
chiedi(peso):- 
 write('La moneta pesa meno o uguale ai 5 g?'),
 nl,write('si/no'),
 nl,
 write('Risposta: '),
 read(Peso),
 riconosciPeso(Peso),
 !.

riconosciPeso(Peso):- 
 peso1(Peso),
 assertz(fact(5,fatta(moneta,'peso minore uguale 5 g'),1)).

riconosciPeso(Peso):- 
 peso2(Peso),
 assertz(fact(6,not(fatta(moneta,'peso minore uguale 5 g')),1)).
 
 riconosciPeso(stampakb):- 
  stampakb,
  chiedi(peso).
   
 riconosciPeso(stamparegole):- 
   stamparegole,
   chiedi(peso).
     
 riconosciPeso(stampafatti):- 
   stampafatti,
   chiedi(peso). 

riconosciPeso(chiediregola):- 
   chiediregola,
   chiedi(peso).  
      
riconosciPeso(_):- 
 nl,
 write('Risposta sconosciuta,riprova'),
 nl,
 chiedi(peso).


peso1(si).
peso2(no).

%domanda riferita alla dimensione
chiedi(dimensione):- 
 write('La moneta ha dimensione maggiore di 22 mm?'),
 nl,
 write('si/no'),
 nl,
 write('Risposta: '),
 read(Dimensione),
 riconosciDimensione(Dimensione),
 !.

riconosciDimensione(Dimensione):- 
 dimensione1(Dimensione),
 assertz(fact(7,fatta(moneta,'dimensione maggiore 22 mm'),1)).

riconosciDimensione(Dimensione):- 
 dimensione2(Dimensione),
 assertz(fact(8,not(fatta(moneta,'dimensione maggiore 22 mm')),1)).

 riconosciDimensione(stampakb):- 
  stampakb,
  chiedi(dimensione).
   
riconosciDimensione(stamparegole):- 
   stamparegole,
   chiedi(dimensione).
     
 riconosciDimensione(stampafatti):- 
   stampafatti,
   chiedi(dimensione). 
   
riconosciDimensione(chiediregola):- 
   chiediregola,
   chiedi(dimensione).   
   
 riconosciDimensioni(_):- 
 nl,
 write('Risposta sconosciuta,riprova'),
 nl,
 chiedi(dimensioni).


dimensione1(si).
dimensione2(no).

%doamnda riferita al contorno della moneta 

chiedi(contorno):- 
 write('La moneta possiede contorno liscio?'),
 nl,
 write('si/no'),
 nl,
 write('Risposta: '),
 read(Contorno),
 riconosciContorno(Contorno),
 !.
 
riconosciContorno(Contorno):- 
 contorno1(Contorno),
 assertz(fact(9,fatta(moneta,'con contorno liscio'),1)).

riconosciContorno(Contorno):- 
 contorno2(Contorno),
 assertz(fact(10,not(fatta(moneta,'con contorno liscio')),1)).

 riconosciContorno(stampakb):- 
  stampakb,
  chiedi(contorno).
   
riconosciContorno(stamparegole):- 
   stamparegole,
   chiedi(contorno).
     
 riconosciContorno(stampafatti):- 
   stampafatti,
   chiedi(contorno). 
   
riconosciContorno(chiediregola):- 
   chiediregola,
   chiedi(contorno). 
   
riconosciContorno(_):- 
 nl,
 write('Risposta sconosciuta,riprova'),
 nl,
 chiedi(contorno).

 
contorno1(si).
contorno2(no).
 	
ottieniScarto(0.1).
uno(1).
due(2).
tre(3).
quattro(4).
stampaKb(stampaKb).	
	  