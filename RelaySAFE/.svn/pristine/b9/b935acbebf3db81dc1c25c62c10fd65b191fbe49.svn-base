sopracciglia(alzate) :- au(uno), au(due).
sopracciglia(oblique) :- au(uno), au(quattro).

sguardo(sorridente) :- au(sei), au(sette).
sguardo(torvo) :- au(quattro), au(cinque).
sguardo(occhi_spalancati):- au(tre),au(cinque).

bocca(mezza_aperta) :- au(venticinque), au(ventisei).
bocca(chiusa) :- au(ventitre), au(ventiquattro).
bocca(spalancata):-au(ventisette).

faccia(sguardo_oppo) :- au(cinquantuno), au(sessantadue).
faccia(sguardo_oppo) :- au(cinquantadue), au(sessantuno).

postura(aperta) :- au(cinquantotto), au(settanta).
postura(chiusa) :- au(sessantaquattro), au(settantuno).
tronco(dritto) :- postura(aperta), au(settantadue).
tronco(indietro) :- postura(aperta), au(settantaquattro).
postura(rabbia) :- tronco(dritto), au(settantotto).
postura(rabbia) :- tronco(dritto), au(settantacinque).
postura(felicita) :- tronco(dritto), au(settantadue).
postura(sorpresa) :- tronco(indietro), au(ottanta).
postura(disgusto):- tronco(indietro), au(ottanta).
postura(tristezza) :- postura(chiusa), au(cinquantasette), au(settantatre),au(settantasei).
postura(paura) :- postura(chiusa), au(cinquantotto), au(ottantuno).

sorriso(autentico):-sguardo(sorridente),au(dodici).
ghigno(rabbioso):-bocca(chiusa),au(diciasette).
ghigno(rabbioso):-au(dieci),au(ventidue).
viso_sup(felicita):-sorriso(autentico).
viso_inf(felicita):-bocca(mezza_aperta).
viso_sup(rabbia):- sguardo(occhi_spalancati),sguardo(torvo).
viso_inf(rabbia):-ghigno(rabbioso).
viso_sup(tristezza):-sopracciglia(oblique),au(sei).
viso_inf(tristezza):-au(quindici),au(diciasette).
viso_sup(paura):-sopracciglia(alzate),sguardo(occhi_spalancati).
viso_inf(paura):-au(venti),au(venticinque).
viso_inf(paura):-au(venti),au(ventisei).
viso_inf(paura):-au(venti),au(ventisette).
viso_sup(disgusto):-au(sette),au(nove).
viso_inf(disgusto):-bocca(mezza_aperta).
viso_inf(disgusto):-au(quindici),au(sedici).
viso_inf(disgusto):-au(quindici),au(diciasette).
viso_inf(disgusto):-au(sedici),au(diciasette).
viso_sup(sorpresa):-sopracciglia(alzate),sguardo(occhi_spalancati).
viso_inf(sorpresa):-bocca(mezza_aperta).
viso_inf(sorpresa):-bocca(spalancata).

emozione(felicita) :- viso_sup(felicita), viso_inf(felicita), postura(felicita).
emozione(rabbia) :- viso_sup(rabbia), viso_inf(rabbia), postura(rabbia).
emozione(tristezza) :- viso_sup(tristezza), viso_inf(tristezza), postura(tristezza).
emozione(paura) :- viso_sup(paura), viso_inf(paura), postura(paura).
emozione(disgusto) :- viso_sup(disgusto), viso_inf(disgusto), postura(disgusto).
emozione(sorpresa) :- viso_sup(sorpresa), viso_inf(sorpresa), postura(sorpresa).
emozione(divertimento) :- emozione(felicita), au(cinquantatre).
emozione(timidezza) :- emozione(felicita), faccia(sguardo_oppo),
au(cinquantaquattro).
