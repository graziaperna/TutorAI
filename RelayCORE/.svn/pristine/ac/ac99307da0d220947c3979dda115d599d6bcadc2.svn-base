
%deduzione eventuali date presenti nell'articolo
rule(2,date(D,M,Y),and([data(D,M,Y)]),1,1).

%deduzione eventuali nomi di persona
rule(3,entita_person(Nome,Cognome),and([entita_persona(Nome,Cognome)]),1,1).

%deduzioni per le testate giornalistiche
rule(4,testata_nazional(TST),and([testata_nazionale(TST)]),1,1).
rule(5,testata_nazional(TST1,TST2),and([testata_nazionale(TST1,TST2)]),1,1).
rule(6,testata_internazional(TST),and([testata_internazionale(TST)]),1,1).
rule(7,testata_internazional(TST1,TST2),and([testata_internazionale(TST1,TST2)]),1,1).

%deduzioni luoghi
rule(8,luogo_italia(C,Y),and([luogo_italiano(C,Y)]),1,1).
rule(9,citta_europa(Y),and([citta_europea(Y)]),1,1).
rule(10,citta_mondo(C,Y),and([citta_mondiale(C,Y)]),1,1).

%deduzioni parole categorie cronaca
rule(11,entita_generale_asserita_cronac(C,F),and([entita_generale_asserita_cronaca(C,F)]),1,1).
rule(12,oggetto_generale_asserito_cronac(C,F),and([oggetto_generale_asserito_cronaca(C,F)]),1,1).
rule(13,verbo_generale_asserito_cronac(C,F),and([verbo_generale_asserito_cronaca(C,F)]),1,1).
rule(14,entita_discriminante_asserita_furt(C,F),and([entita_generale_asserita_cronaca(C,F)]),1,1).
rule(15,oggetto_discriminante_asserito_furt(C,F),and([oggetto_discriminante_asserito_furto(C,F)]),1,1).
rule(16,entita_discriminante_asserita_omicidi(C,F),and([entita_discriminante_asserita_omicidio(C,F)]),1,1).
rule(17,oggetto_discriminante_asserito_omicidi(C,F),and([oggetto_discriminante_asserito_omicidio(C,F)]),1,1).
rule(18,entita_discriminante_asserita_terrorism(C,F),and([entita_discriminante_asserita_terrorismo(C,F)]),1,1).
rule(19,oggetto_discriminante_asserito_terrorism(C,F),and([oggetto_discriminante_asserito_terrorismo(C,F)]),1,1).
rule(20,entita_discriminante_asserita_terremot(C,F),and([entita_discriminante_asserita_terremoto(C,F)]),1,1).
rule(21,oggetto_discriminante_asserito_terremot(C,F),and([entita_discriminante_asserita_terremoto(C,F)]),1,1).
rule(22,entita_discriminante_asserita_mafi(C,F),and([entita_discriminante_asserita_mafia(C,F)]),1,1).
rule(23,oggetto_discriminante_asserito_mafi(C,F),and([oggetto_discriminante_asserito_mafia(C,F)]),1,1).
rule(24,entita_discriminante_asserita_corruzion(C,F),and([entita_discriminante_asserita_corruzione(C,F)]),1,1).
rule(25,oggetto_discriminante_asserito_corruzion(C,F),and([oggetto_discriminante_asserito_corruzione(C,F)]),1,1).
rule(26,relazione_cronac(S,V,O),and([relazione_cronaca(S,V,O)]),1,1).

%deduzioni cultura
rule(27,verbo_asserito_cultur(C,F),and([verbo_asserito_cultura(C,F)]),1,1).
rule(28,entita_asserita_cultur(C,F),and([entita_asserita_cultura(C,F)]),1,1).
rule(29,oggetto_asserito_cultur(C,F),and([oggetto_asserito_cultura(C,F)]),1,1).
rule(30,relazione_cultur(S,V,O),and([relazione_cultura(S,V,O)]),1,1).

%deduzioni economia
rule(31,verbo_asserito_economi(C,F),and([verbo_asserito_economia(C,F)]),1,1).
rule(32,entita_generale_asserita_economi(C,F),and([entita_generale_asserita_economia(C,F)]),1,1).
rule(33,oggetto_generale_asserito_economi(C,F),and([oggetto_generale_asserito_economia(C,F)]),1,1).
rule(34,entita_discriminante_asserita_economi(C,F),and([entita_generale_asserita_economia(C,F)]),1,1).
rule(35,oggetto_discriminanti_asserito_economi(C,F),and([oggetto_generale_asserito_economia(C,F)]),1,1).
rule(36,relazione_economi(S,V,O),and([relazione_economia(S,V,O)]),1,1).

%deduzioni mondo
rule(37,verbo_asserito_mond(C,F),and([verbo_asserito_mondo(C,F)]),1,1).
rule(38,entita_generale_asserita_mond(C,F),and([entita_generale_asserita_mondo(C,F)]),1,1).
rule(39,oggetto_generale_asserito_mond(C,F),and([oggetto_generale_asserito_mondo(C,F)]),1,1).
rule(40,entita_discriminante_asserita_mond(C,F),and([entita_discriminante_asserita_mondo(C,F)]),1,1).
rule(41,oggetto_discriminante_asserito_mond(C,F),and([oggetto_discriminante_asserito_mondo(C,F)]),1,1).
rule(42,relazione_mond(S,V,O),and([relazione_mondo(S,V,O)]),1,1).

%deduzioni politica
rule(43,verbo_asserito_politic(C,F),and([verbo_asserito_politica(C,F)]),1,1).
rule(44,entita_generale_asserita_politic(C,F),and([entita_generale_asserita_politica(C,F)]),1,1).
rule(45,oggetto_generale_asserito_politic(C,F),and([oggetto_generale_asserito_politica(C,F)]),1,1).
rule(46,entita_discriminante_asserita_politic(C,F),and([entita_discriminante_asserita_politica(C,F)]),1,1).
rule(47,oggetto_discriminante_asserito_politic(C,F),and([oggetto_discriminante_asserito_politica(C,F)]),1,1).
rule(48,relazione_politic(S,V,O),and([relazione_politica(S,V,O)]),1,1).

%deduzioni sport
rule(49,verbo_asserito_sportt(C,F),and([verbo_asserito_sport(C,F)]),1,1).
rule(50,entita_generale_asserita_sportt(C,F),and([entita_generale_asserita_sport(C,F)]),1,1).
rule(51,oggetto_generale_asserito_sportt(C,F),and([oggetto_generale_asserito_sport(C,F)]),1,1).
rule(52,relazione_sportt(S,V,O),and([relazione_sport(S,V,O)]),1,1).
rule(53,entita_discriminante_asserita_calci(C,F),and([entita_discriminante_asserita_calcio(C,F)]),1,1).
rule(54,oggetto_discriminante_asserito_calci(C,F),and([oggetto_discriminante_asserito_calcio(C,F)]),1,1).
rule(55,entita_discriminante_asserita_pallavol(C,F),and([entita_discriminante_asserita_pallavolo(C,F)]),1,1).
rule(56,oggetto_discriminante_asserito_pallavol(C,F),and([oggetto_discriminante_asserito_pallavolo(C,F)]),1,1).
rule(57,entita_discriminante_asserita_baske(C,F),and([entita_discriminante_asserita_basket(C,F)]),1,1).
rule(58,oggetto_discriminante_asserito_baske(C,F),and([oggetto_discriminante_asserito_basket(C,F)]),1,1).
rule(60,entita_discriminante_asserita_tenni(C,F),and([entita_discriminante_asserita_tennis(C,F)]),1,1).
rule(61,oggetto_discriminante_asserito_tenni(C,F),and([oggetto_discriminante_asserito_tennis(C,F)]),1,1).
rule(62,entita_discriminante_asserita_formula_un(C,F),and([entita_discriminante_asserita_formula_uno(C,F)]),1,1).
rule(63,oggetto_discriminante_asserito_formula_un(C,F),and([oggetto_discriminante_asserito_formula_uno(C,F)]),1,1).
rule(64,entita_discriminante_asserita_nuot(C,F),and([entita_discriminante_asserita_nuoto(C,F)]),1,1).
rule(65,oggetto_discriminante_asserito_nuot(C,F),and([oggetto_discriminante_asserito_nuoto(C,F)]),1,1).
rule(66,entita_discriminante_asserita_motog(C,F),and([entita_discriminante_asserita_motogp(C,F)]),1,1).
rule(67,oggetto_discriminante_asserito_motog(C,F),and([oggetto_discriminante_asserito_motogp(C,F)]),1,1).

%deduzione categoria articolo
rule(68,categoria_articol(Categoria),and([categoria_articolo(Categoria)]),1,1).

rule(69,sotto_categoria_articol(SottoCategoria),and([sotto_categoria_articolo(SottoCategoria)]),1,1).
