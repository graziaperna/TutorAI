%----------MODULI----------
:- use_module(library(random)).

%---------Dichiarazione fatti----------
%:- dynamic visitato/1, giafatto/1, ritorno/1, crociera/1, info_r/1, proposta/1, tipo/1, profilo/1, fine/1, meta/1, chiesto/1, pernottamento/1, condivisione_hotel/1, tipo_sistemazione/1, sistemazione_proposta/3, sistemazione_finale/5, infoScopo/1, budgetT/1, infoDurata/1, r_budget/1, duratap/1, documento/1, infoBudget/1, infoEta/1, eta/1, consiglio/1, durataf/1, viaggio/1, budgetT/1, budget/1, infoViaggio/1, compagnia/1, maggiorenne/1, infoCompagnia/1, infoPeriodo/1, periodo/1, infoPreferenze/1, preferenze/1, scopo/1, scopo_sec/1.
%:- dynamic raggio/1, planning/1, ti_trovi_qui/3, tempo/1, scelta/1, escludi/1, itinerario/3, pasto/1, mostra/1, proposta_itinerario/5, controllato/1, durata_itinerario/1, itinerario_giorno/4, stampa/1, tempo_max/1, giorno/1, dormi_qui/4.

%----------FUNZIONI DI SERVIZIO---------- 

lunghezza([],0).
lunghezza([_|T],L) :- lunghezza(T,LI), L is LI+1.

append([],X,[X]).
append([H|T],X,[H|R]) :- append(T,X,R).

%Effettua l'ordinamento di una lista (quicksort).
ordinaItinerario([],[], _).
ordinaItinerario([[X,Y,Z,A,B,R]|T],TI,C) :- (C==1 -> (ls(T,Y,S),ld(T,Y,D),ordinaItinerario(S,SI,C),ordinaItinerario(D,DI,C),append(SI,[X,Y,Z,A,B,R],P),appendl(P,DI,TI)); (ls_r(T,R,Y,S),ld_r(T,R,Y,D),ordinaItinerario(S,SI,C),ordinaItinerario(D,DI,C),append(SI,[X,Y,Z,A,B,R],P),appendl(P,DI,TI))).
						
elemp_itinerario([],_,fail,fail,fail,fail,fail,fail).
elemp_itinerario([[A,B,C,D,E,R]|_],1,A,B,C,D,E,R) :- !.
elemp_itinerario([_|T],X,A,B,C,D,E,R) :- Z is X-1, elemp_itinerario(T,Z,A,B,C,D,E,R).
							
													
%Concatena due liste.
appendl([],[],[]).
appendl(X,[],X) :- !.
appendl([],X,X) :- !.
appendl(X,[H|T],R) :- append(X,H,RI), appendl(RI,T,R).

%Sottolista sinistra degli elementi minori di un certo numero.
ls([],_,[]).
ls([[X,Y,Z,A,B,R]|T],V,TI) :- Y<V,!, ls(T,V,TII), append(TII,[X,Y,Z,A,B,R],TI).
ls([_|T],V,TI) :- ls(T,V,TI).

%Sottolista destra degli elementi maggiori o uguali di un certo numero.
ld([],_,[]).
ld([[X,Y,Z,A,B,R]|T],V,TI) :- Y>=V,!, ld(T,V,TII), append(TII,[X,Y,Z,A,B,R],TI).
ld([_|T],V,TI) :- ld(T,V,TI).

%Sottolista sinistra degli elementi maggiori di un certo numero.
ls_r([],_,_,[]).
ls_r([[X,Y,Z,A,B,R]|T],V,D,TI) :- R>V,!, ls_r(T,V,D,TII), append(TII,[X,Y,Z,A,B,R],TI).
ls_r([[X,Y,Z,A,B,R]|T],V,D,TI) :- R==V, Y<D,!, ls_r(T,V,D,TII), append(TII,[X,Y,Z,A,B,R],TI).
ls_r([_|T],V,D,TI) :- ls_r(T,V,D,TI).


%Sottolista destra degli elementi minori di un certo numero.
ld_r([],_,_,[]).
ld_r([[X,Y,Z,A,B,R]|T],V,D,TI) :- R<V,!, ld_r(T,V,D,TII), append(TII,[X,Y,Z,A,B,R],TI).
ld_r([[X,Y,Z,A,B,R]|T],V,D,TI) :- R==V, Y>=D,!, ld_r(T,V,D,TII), append(TII,[X,Y,Z,A,B,R],TI).
ld_r([_|T],V,D,TI) :- ld_r(T,V,D,TI).


%-----------------------------------Funzioni planning

defineAge(E, R) :- (E<18 -> R=under18; ((E>=18, E<35) -> R=e1835; ((E>=35, E<65) -> R=e3565; R=over65))).

defineBudget(B, R) :- ((B>=150, B<300) -> R=moltobasso; ((B>=300, B=<800) -> R=basso; ((B>800, B<1500) -> R=medio; ((B>=1500, B<3000) -> R=alto; (B<150 -> R=nullo; R=altissimo))))).

distance(Lat1, Lon1, Lat2, Lon2, Dis):-
    P is 0.017453292519943295,
    A is (0.5 - cos((Lat2 - Lat1) * P) / 2 + cos(Lat1 * P) * cos(Lat2 * P) * (1 - cos((Lon2 - Lon1) * P)) / 2),
    Dis is (12742 * asin(sqrt(A))).
	
arrotonda(X,R) :- M is X*100, N is floor(M), R is N/100.
converti_ore_minuti(X) :-  H is floor(X), R is X-H, N is R*60,M is floor(N), write(H), write('h '), write(M), write('m').


listaItinerari(L) :- findall([X,Y,Z,A,B,R], fact(_,proposta_itinerario(X,Y,Z,A,B,R),_), L).
listaMete(L) :- findall([X,Y,Z], fact(_,itinerario(X,Y,Z),_), L).
listaGite(L) :- findall(X, fact(_,proposta_itinerario(X,_,'Intera giornata',_,_,_),_), L).
listaPiano(L) :- findall([N, X, Y, Z], fact(_,itinerario_giorno(N, X, Y, Z),_), L).

count_proposte(Count) :- listaItinerari(L), lunghezza(L, Count).
count_gite(Count) :- listaGite(L), lunghezza(L, Count).

stampaItinerari([],_).
stampaItinerari([[X,Y,Z,_,_,R]|T],N):- write(N), write('* '), write(X),
										   write(' | Distanza: '), write(Y), write(' km'),
										   write(' | Tempo previsto: '), (Z \='Intera giornata' -> converti_ore_minuti(Z); write(Z)),
										   write(' | Rating: '), arrotonda(R, RArr), write(RArr), write('/5'),
										   lunghezza(T,LUNG), (LUNG>0, N<5 -> write(','),
							    NN is N+1, stampaItinerari(T,NN);true).
																
stampaMete([],_).
stampaMete([[X,Y,Z]|T],N):- write(N), write('. '), write(X),
										   write(' | Distanza dalla meta precedente: '), write(Y), write(' km'),
										   write(' | Tempo necessario: '), (Z \='Intera giornata' -> converti_ore_minuti(Z); write(Z)),
										   lunghezza(T,LUNG), (LUNG>0 -> write('<br>'),
							    NN is N+1, stampaMete(T,NN);true).
								
stampaPiano([],_).
stampaPiano([[G,X,Y,Z]|T],N):- write(N), write('. '), write(X),
										   write(' | Distanza dalla meta precedente: '), write(Y), write(' km'),
										   write(' | Tempo necessario: '), ((Z \='Intera giornata', Z\='/') -> converti_ore_minuti(Z); write(Z)),
										   write(' | Giornata: '), write(G), write('<br>'),
										   lunghezza(T,LUNG), (LUNG>0 -> NN is N+1, stampaPiano(T,NN);true).
								
%1 = Città con metro; 2 = città a piedi; 3 = road trip su strade non ad alta percorrenza; 4 = road trip con strade ad alta viabilità
convertitore_ore_km(Z, KM, D) :- (Z==1 -> D is (12*KM)/60; (Z==2 -> D is (20*KM)/60; (Z==3 -> D is (1.2*KM)/60; D is (0.6*KM)/60))).