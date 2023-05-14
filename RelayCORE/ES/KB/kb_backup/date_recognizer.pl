module_name('date_recognizer.pl').



%mesi
fact(1,nome_mese(gennaio),1).
fact(2,nome_mese(febbraio),1).
fact(3,nome_mese(marzo),1).
fact(4,nome_mese(aprile),1).
fact(5,nome_mese(maggio),1).
fact(6,nome_mese(giugno),1).
fact(7,nome_mese(luglio),1).
fact(8,nome_mese(agosto),1).
fact(9,nome_mese(settembre),1).
fact(10,nome_mese(ottobre),1).
fact(11,nome_mese(novembre),1).
fact(12,nome_mese(dicembre),1).



%individuo le date anche in base alla loro posizione nella frase oltre che al tag

rule(1,data(D,M,Y),and([tag(num(D),pos(P1),F),tag(nom(M),pos(P2),F),tag(num(Y),pos(P3),F),
call_p(number(D)),nome_mese(M),call_p(Y>1600),call_p(D>0),call_p(D<31),call_p((P2-P1)<3),call_p((P3-P2)<3)]),1,1).



rule(2,data(D,M,Y),and([tag(num(D),pos(P1),F),tag(ogg(M),pos(P2),F),tag(num(Y),pos(P3),F),
		call_p(number(D)),mese(M),call_p(Y>1600),call_p(D>0),call_p(D<31),call_p((P2-P1)<3),call_p((P3-P2)<3)]),1,1).



