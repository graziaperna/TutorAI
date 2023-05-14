rule(1,idxPlaces(ID,Nome),and([not_exists(idxPlaces(ID,Nome)),or([nazione(ID,Nome),regione(ID,Nome),citta(ID,Nome),quartiere(ID,Nome)])]),1,1).
rule(2,idxPlaces(ID,Posto),and([not_exists(idxPlaces(ID,Posto)),attributo(ID,nome,Posto)]),1,1).
rule(3,idxPlaces(ID,Posto),and([not_exists(idxPlaces(ID,Posto)),evento(ID,Luogo),or([nazione(Luogo,Posto),regione(Luogo,Posto),citta(Luogo,Posto),quartiere(Luogo,Posto)])]),1,1).

