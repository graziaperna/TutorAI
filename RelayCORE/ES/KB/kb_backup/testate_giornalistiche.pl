module_name('testate_giornalistiche.pl').

fact(1,testata_giornalistica_it(avanti),1).	
fact(2,testata_giornalistica_it(avvenire),1).	
fact(3,testata_giornalistica_it(corriere,sera),1).	
fact(5,testata_giornalistica_it(fatto,quotidiano),1).	
fact(6,testata_giornalistica_it(foglio),1).	
fact(7,testata_giornalistica_it(giornale),1).	
fact(8,testata_giornalistica_it(giorno),1).	
fact(9,testata_giornalistica_it(manifesto),1).	
fact(10,testata_giornalistica_it(mattino),1).	
fact(11,testata_giornalistica_it(messaggero),1).	
fact(12,testata_giornalistica_it(resto,carlino),1).	
fact(13,testata_giornalistica_it(secolo,xix),1).	
fact(14,testata_giornalistica_it(sole,24),1).	
fact(15,testata_giornalistica_it(tempo),1).	
fact(16,testata_giornalistica_it(italia,oggi),1).	
fact(17,testata_giornalistica_it(gazzetta,mezzogiorno),1).	
fact(18,testata_giornalistica_it(nazione),1).	
fact(19,testata_giornalistica_it(padania),1).	
fact(20,testata_giornalistica_it(repubblica),1).	
fact(21,testata_giornalistica_it(stampa),1).	
fact(22,testata_giornalistica_it(liberazione),1).	
fact(23,testata_giornalistica_it(libero),1).	
fact(24,testata_giornalistica_it(indipendente),1).	
fact(25,testata_giornalistica_it(opinione),1).	
fact(26,testata_giornalistica_it(osservatore,romano),1).	
fact(27,testata_giornalistica_it(unita),1).	
fact(28,testata_giornalistica_it(milano,finanza),1).	
fact(29,testata_giornalistica_it(secolo,italia),1).

%testate internazionali	
fact(30,testata_giornalistica_int(abc),1).	
fact(31,testata_giornalistica_int(daily,lebanon),1).	
fact(32,testata_giornalistica_int(el,mundo),1).	
fact(33,testata_giornalistica_int(pais),1).	
fact(34,testata_giornalistica_int(mondo),1).	
fact(35,testata_giornalistica_int(figaro),1).	
fact(36,testata_giornalistica_int(liberation),1).	
fact(37,testata_giornalistica_int(der,spiegel),1).	
fact(38,testata_giornalistica_int(the,economist),1).	
fact(39,testata_giornalistica_int(the,guardian),1).	
fact(40,testata_giornalistica_int(the,indipendent),1).	
fact(41,testata_giornalistica_int(jerusalem_post),1).	
fact(42,testata_giornalistica_int(york,times),1).	
fact(43,testata_giornalistica_int(washington,post),1).	






%regole per l individuazione della testata giornalistica nazionali 

rule(1,testata_nazionale(TST),and([tag(nom(TST),pos(_),F),testata_giornalistica_it(TST),call_p(F<3)]),1,1).

rule(2,testata_nazionale(TST1,TST2),and([tag(nom(TST1),pos(P1),F),tag(nom(TST2),pos(P2),F),testata_giornalistica_it(TST1,TST2),call_p(F<3),call_p((P2-P1)<3)]),1,1).

%regole per l individuazione della testata giornalistica internazionali 

rule(3,testata_internazionale(TST),and([tag(nom(TST),pos(_),F),testata_giornalistica_int(TST),call_p(F<3)]),1,1).

rule(4,testata_internazionale(TST1,TST2),and([tag(nom(TST1),pos(P1),F),tag(nom(TST2),pos(P2),F),testata_giornalistica_int(TST1,TST2),call_p(F<3),call_p((P2-P1)<3)]),1,1).