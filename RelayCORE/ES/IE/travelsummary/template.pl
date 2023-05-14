rule(1,coord(ID,Coord),and([or([tappa(ID,_),tappa_principale(ID,_)]),attributo(ID,coordinate,Coord)]),1,1).
rule(2,frase_inizio(Output),and([first(ID),last(ID2),viaggio(_,NomeV,Inizio,Fine),
	tappa_principale(ID,Nome1),tappa_principale(ID2,Nome2), 
	call_p(atomic_list_concat(
		[NomeV,":un viaggio emozionante alla scoperta di luoghi straordinari. Partendo da ",Nome1,
		" fino a raggiungere ",Nome2,".Questo è il racconto del tuo viaggio!"],' ',Output))]),1,1).
rule(3,frase_inizio(Output),and([first(ID),last(ID2),viaggio(_,NomeV,Inizio,Fine),tappa_principale(ID,Nome1),tappa_principale(ID2,Nome2), 
	call_p(atomic_list_concat(
		["Questo è il racconto del tuo viaggio:\"",NomeV,"\" iniziato il ",Inizio," e terminato il ",Fine,". Un viaggio avventuroso partito da ",Nome1,
		" e terminato a ",Nome2,"!"],' ',Output))]),1,1).


%template tappe normali
rule(4,frase(Id,Frase,Origine),and([tappa(Id,Nome),nota(Id,Nota,Origine),call_p(atomic_list_concat([Nome,":",Nota],' ',Frase))]),1,1).
rule(5,frase(Id,Frase,Origine),and([tappa(Id,Nome),punto_di_interesse(Id,IdL),idxPlaces(IdL,NomeL),attributo(Id,tipo,Tipo),attributo(Id,data,Data),
nota(Id,Nota,Origine),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"costruita nel",Data,"che si trova in",NomeL,".",Nota],' ',Frase))]),1,1).
rule(6,frase(Id,Frase,default),and([tappa(Id,Nome),attributo(Id,tipo,Tipo),attributo(Id,materiale,Materiale),
	attributo(Id,stile,Stile),
	call_p(atomic_list_concat([Nome,"è un magnifico",Tipo,"fatta di",Materiale,"con uno stile",Stile,"."],' ',Frase))]),1,1).
rule(7,frase(Id,Frase,default),and([tappa(Id,Nome),attivita(Id,Idl),idxPlaces(Idl,Luogo),
	call_p(atomic_list_concat(["C'è stato anche il tempo di una fantastica attività.",Nome,"a",Luogo,",che divertente!"],' ',Frase))]),1,1).
rule(8,frase(Evento,Frase,default),and([tappa(Evento,Luogo),evento(Evento,_),
	call_p(atomic_list_concat([Evento," a ",Luogo,": che evento fantastico a cui hai partecipato!"],' ',Frase))]),1,1).


%template tappe principali
rule(9,frase(Id,Frase,Origine),and([tappa_principale(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),attributo(Id,data,Data),
	idxPlaces(IdL,NomeL),nota(Id,Nota,Origine),nota(IdL,NotaL,_),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"costruita nel",Data,"che si trova in",NomeL,":",NotaL,".",Nota,"."],' ',Frase))]),1,1).
rule(10,frase(Id,Frase,Origine),and([tappa_principale(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),
	idxPlaces(IdL,NomeL),nota(Id,Nota,Origine),nota(IdL,NotaL,_),
	call_p(atomic_list_concat([Nome,"è un magnifico",Tipo,"che si trova in",NomeL,":",NotaL,".",Nota,"."],' ',Frase))]),1,1).
rule(11,frase(Id,Frase,Origine),and([tappa_principale(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),attributo(Id,data,Data),
	idxPlaces(IdL,NomeL),nota(IdL,NotaL,Origine),
	call_p(atomic_list_concat([Nome,"è un",Tipo,"costruita nel",Data,"che si trova in",NomeL,":",NotaL,"."],' ',Frase))]),1,1).
rule(12,frase(Id,Frase,default),and([tappa_principale(Id,Nome),attivita(Id,Idl),idxPlaces(Idl,Luogo),
	call_p(atomic_list_concat(["C'è stato anche il tempo di una fantastica attività.",Nome,"a",Luogo,",che divertente!"],' ',Frase))]),1,1).
rule(13,frase(Evento,Frase,default),and([tappa_principale(Evento,Luogo),evento(Evento,_),
	call_p(atomic_list_concat([Evento," a ",Luogo,":che evento fantastico a cui hai partecipato!"],' ',Frase))]),1,1).
rule(14,frase(Id,Frase,Origine),and([tappa_principale(Id,Nome),nota(Id,Nota,Origine),call_p(atomic_list_concat([Nome,":",Nota],' ',Frase))]),1,1).


rule(15,frase_fine(Frase),and([last(Id),idxPlaces(Id,Luogo),
	call_p(atomic_list_concat(["Si chiude a",Luogo,"il tuo fantastico viaggio. Ora non resta che aspettare il prossimo!"],' ',Frase))]),1,1).
rule(16,frase_fine(Frase),and([last(Id),idxPlaces(Id,Luogo),
	call_p(atomic_list_concat(["Infine sei giunto a",Luogo,"dove si conclude il tuo splendido viaggio!"],' ',Frase))]),1,1).

%template archiviati
/*
rule(8,frase(Id,Frase,Origine),and([tappa(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),
	idxPlaces(IdL,NomeL),nota(Id,Nota,Origine),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"che si trova in",NomeL,".",Nota],' ',Frase))]),1,1).
rule(9,frase(Id,Frase,default),and([tappa(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),attributo(Id,data,Data),
	idxPlaces(Idl,NomeL),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"costruita nel",Data,"che si trova in",NomeL,"."],' ',Frase))]),1,1).
rule(10,frase(Id,Frase,default),and([tappa(Id,Nome),punto_di_interesse(Id,IdL),attributo(Id,tipo,Tipo),idxPlaces(IdL,NomeL),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"che si trova in",NomeL,"."],' ',Frase))]),1,1).

rule(11,frase(Id,Frase,default),and([tappa(Id,Nome),punto_di_interesse(Id,_),attributo(Id,tipo,Tipo),attributo(Id,materiale,Materiale),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"fatta di",Materiale,"."],' ',Frase))]),1,1).

rule(16,frase(Id,Frase,Origine),and([tappa_principale(Id,Nome),punto_di_interesse(Id,_),attributo(Id,tipo,Tipo),
	si_trova_in(Id,IdL),luogo_generico(IdL,NomeL),nota(IdL,NotaL,Origine),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"che si trova in",NomeL,":",NotaL,"."],' ',Frase))]),1,1).
rule(17,frase(Id,Frase,default),and([tappa_principale(Id,Nome),punto_di_interesse(Id,_),attributo(Id,tipo,Tipo),attributo(Id,materiale,Materiale),
	si_trova_in(Id,IdL),luogo_generico(IdL,NomeL),nota(IdL,NotaL,Origine),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"fatta di",Materiale,"che si trova in",NomeL,":",NotaL,"."],' ',Frase))]),1,1).
rule(18,frase(Id,Frase,default),and([tappa_principale(Id,Nome),punto_di_interesse(Id,_),
	attributo(Id,tipo,Tipo),attributo(Id,materiale,Materiale),attributo(Id,stile,Stile),si_trova_in(Id,IdL),luogo_generico(IdL,NomeL),nota(IdL,NotaL,Origine),
	call_p(atomic_list_concat([Nome,"è una splendida",Tipo,"fatta di",Materiale,"con uno stile",Stile,"che si trova in",NomeL,":",NotaL,"."],' ',Frase))]),1,1).
*/
