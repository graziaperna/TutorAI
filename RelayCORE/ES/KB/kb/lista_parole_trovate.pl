module_name('lista_parole_trovate.pl').


%CULTURA calcolo delle parole e delle relazioni
rule(1,lista_entita_cultura(RisFindallEntitaCultura),and([call_p(findall((Y,F),entita_asserita_cultura(Y,F),RisFindallEntitaCultura))]),1,1).
rule(2,lista_oggetto_cultura(RisFindallOggettoCultura),and([call_p(findall((Y1,F1),oggetto_asserito_cultura(Y1,F1),RisFindallOggettoCultura))]),1,1).
rule(3,lista_verbi_cultura(RisFindallVerboCultura),and([call_p(findall((Y2,F2),verbo_asserito_cultura(Y2,F2),RisFindallVerboCultura))]),1,1).

%n_parole xk cultura nn ha discriminanti
rule(4,n_parole_cultura(NCL),and([lista_entita_cultura(L1),lista_verbi_cultura(L2),lista_oggetto_cultura(L3),call_p(length(L1,N1)),call_p(length(L2,N2)),call_p(length(L3,N3)),call_p(NCL is N1+N2+N3)]),1,1).

rule(5,lista_relazioni_cultura(RisFindallRelazioniCultura),and([call_p(findall((X,Y,Z),relazione_cultura(X,Y,Z),RisFindallRelazioniCultura))]),1,1).
rule(6,n_relazioni_cultura(NRC),and([lista_relazioni_cultura(LC),call_p(length(LC,NRC))]),1,1).



%POLITICA
rule(7,lista_entita_generali_politica(RisFindallEntitaPolitica),and([call_p(findall((Y,F),entita_generale_asserita_politica(Y,F),RisFindallEntitaPolitica))]),1,1).
rule(8,lista_oggetto_generali_politica(RisFindallOggettoPolitica),and([call_p(findall((Y,F),oggetto_generale_asserito_politica(Y,F),RisFindallOggettoPolitica))]),1,1).
rule(9,lista_verbi_politica(RisFindallVerboPolitica),and([call_p(findall((Y,F),verbo_asserito_politica(Y,F),RisFindallVerboPolitica))]),1,1).
rule(10,lista_oggetto_discriminanti_politica(RisFindallOggettoDiscPolitica),and([call_p(findall((Y,F),oggetto_discriminante_asserito_politica(Y,F),RisFindallOggettoDiscPolitica))]),1,1).
rule(11,lista_entita_discriminanti_politica(RisFindallEntitaDiscPolitica),and([call_p(findall((Y,F),entita_discriminante_asserita_politica(Y,F),RisFindallEntitaDiscPolitica))]),1,1).

rule(12,n_parole_politica_generali(NPOLG),and([lista_entita_generali_politica(PL1),lista_oggetto_generali_politica(PL2),call_p(length(PL1,N1)),call_p(length(PL2,N2)),call_p(NPOLG is N1+N2)]),1,1).
rule(13,n_parole_politica_discriminanti(NPOLD),and([lista_verbi_politica(PL1),lista_entita_discriminanti_politica(PL2),lista_oggetto_discriminanti_politica(PL3),call_p(length(PL1,N1)),call_p(length(PL2,N2)),call_p(length(PL3,N3)),call_p(NPOLD is N1+N2+N3)]),1,1).

rule(14,lista_relazioni_politica(RisFindallRelazioniPolitica),and([call_p(findall((X,Y,Z),relazione_politica(X,Y,Z),RisFindallRelazioniPolitica))]),1,1).
rule(15,n_relazioni_politica(NRP),and([lista_relazioni_politica(LP),call_p(length(LP,NRP))]),1,1).



%MONDO
rule(16,lista_entita_generali_mondo(RisFindallEntitaMondo),and([call_p(findall((Y,F),entita_generale_asserita_mondo(Y,F),RisFindallEntitaMondo))]),1,1).
rule(17,lista_oggetto_generali_mondo(RisFindallOggettoMondo),and([call_p(findall((Y,F),oggetto_generale_asserito_mondo(Y,F),RisFindallOggettoMondo))]),1,1).
rule(18,lista_verbi_mondo(RisFindallVerboMondo),and([call_p(findall((Y,F),verbo_asserito_mondo(Y,F),RisFindallVerboMondo))]),1,1).
rule(19,lista_oggetto_discriminanti_mondo(RisFindallOggettoDiscMondo),and([call_p(findall((Y,F),oggetto_discriminante_asserito_mondo(Y,F),RisFindallOggettoDiscMondo))]),1,1).
rule(20,lista_entita_discriminanti_mondo(RisFindallDiscMondo),and([call_p(findall((Y,F),entita_discriminante_asserita_mondo(Y,F),RisFindallDiscMondo))]),1,1).

rule(21,n_parole_mondo_generali(NMNDG),and([lista_entita_generali_mondo(MNDL1),lista_oggetto_generali_mondo(MNDL2),call_p(length(MNDL1,N1)),call_p(length(MNDL2,N2)),call_p(NMNDG is N1+N2)]),1,1).
rule(22,n_parole_mondo_discriminanti(NMNDD),and([lista_verbi_mondo(MNDL1),lista_oggetto_discriminanti_mondo(MNDL2),lista_entita_discriminanti_mondo(MNDL3),call_p(length(MNDL1,N1)),call_p(length(MNDL2,N2)),call_p(length(MNDL3,N3)),call_p(NMNDD is N1+N2+N3)]),1,1).

rule(23,lista_relazioni_mondo(RisFindallRelazioniMondo),and([call_p(findall((X,Y,Z),relazione_mondo(X,Y,Z),RisFindallRelazioniMondo))]),1,1).
rule(24,n_relazioni_mondo(NRMND),and([lista_relazioni_mondo(LMND),call_p(length(LMND,NRMND))]),1,1).


%ECONOMIA
rule(25,lista_entita_generali_economia(RisFindallEntitaEconomia),and([call_p(findall((Y,F),entita_generale_asserita_economia(Y,F),RisFindallEntitaEconomia))]),1,1).
rule(26,lista_oggetto_generali_economia(RisFindallOggettoEconomia),and([call_p(findall((Y,F),oggetto_generale_asserito_economia(Y,F),RisFindallOggettoEconomia))]),1,1).
rule(27,lista_verbi_economia(RisFindallVerboEconomia),and([call_p(findall((Y,F),verbo_asserito_economia(Y,F),RisFindallVerboEconomia))]),1,1).
rule(28,lista_oggetto_discriminanti_economia(RisFindallOggettoDiscEconomia),and([call_p(findall((Y,F),oggetto_discriminante_asserito_economia(Y,F),RisFindallOggettoDiscEconomia))]),1,1).
rule(29,lista_entita_discriminanti_economia(RisFindallDiscEconomia),and([call_p(findall((Y,F),entita_discriminante_asserita_economia(Y,F),RisFindallDiscEconomia))]),1,1).

rule(30,n_parole_economia_generali(NECNG),and([lista_entita_generali_economia(ECNL1),lista_oggetto_generali_economia(ECNL2),call_p(length(ECNL1,N1)),call_p(length(ECNL2,N2)),call_p(NECNG is N1+N2)]),1,1).
rule(31,n_parole_economia_discriminanti(NECND),and([lista_verbi_economia(ECNL1),lista_oggetto_discriminanti_economia(ECNL2),lista_entita_discriminanti_economia(ECNL3),call_p(length(ECNL1,N1)),call_p(length(ECNL2,N2)),call_p(length(ECNL3,N3)),call_p(NECND is N1+N2+N3)]),1,1).

rule(32,lista_relazioni_economia(RisFindallRelazioniEconomia),and([call_p(findall((X,Y,Z),relazione_economia(X,Y,Z),RisFindallRelazioniEconomia))]),1,1).
rule(33,n_relazioni_economia(NRECN),and([lista_relazioni_economia(LECN),call_p(length(LECN,NRECN))]),1,1).



%CRONACA
rule(34,lista_entita_generali_cronaca(RisFindallEntGenCronaca),and([call_p(findall((Y,F),entita_generale_asserita_cronaca(Y,F),RisFindallEntGenCronaca))]),1,1).
rule(35,lista_oggetti_generali_cronaca(RisFindallOggGenCronaca),and([call_p(findall((Y,F),oggetto_generale_asserito_cronaca(Y,F),RisFindallOggGenCronaca))]),1,1).
rule(36,lista_verbi_generali_cronaca(RisFindallVerbCronaca),and([call_p(findall((Y,F),verbo_generale_asserito_cronaca(Y,F),RisFindallVerbCronaca))]),1,1).

rule(37,lista_entita_discriminanti_furto(RisFindallEntDiscFurto),and([call_p(findall((Y,F),entita_discriminante_asserita_furto(Y,F),RisFindallEntDiscFurto))]),1,1).
rule(38,lista_oggetti_discriminanti_furto(RisFindallOggDiscFurto),and([call_p(findall((Y,F),oggetto_discriminante_asserito_furto(Y,F),RisFindallOggDiscFurto))]),1,1).

rule(39,lista_entita_discriminanti_omicidio(RisFindallEntDiscOmicidio),and([call_p(findall((Y,F),entita_discriminante_asserita_omicidio(Y,F),RisFindallEntDiscOmicidio))]),1,1).
rule(40,lista_oggetti_discriminanti_omicidio(RisFindallOggDiscOmicidio),and([call_p(findall((Y,F),oggetto_discriminante_asserito_omicidio(Y,F),RisFindallOggDiscOmicidio))]),1,1).

rule(41,lista_entita_discriminanti_terrorismo(RisFindallEntDiscTerrorismo),and([call_p(findall((Y,F),entita_discriminante_asserita_terrorismo(Y,F),RisFindallEntDiscTerrorismo))]),1,1).
rule(42,lista_oggetti_discriminanti_terrorismo(RisFindallOggDiscTerrorismo),and([call_p(findall((Y,F),oggetto_discriminante_asserito_terrorismo(Y,F),RisFindallOggDiscTerrorismo))]),1,1).

rule(43,lista_entita_discriminanti_mafia(RisFindallEntDiscMafia),and([call_p(findall((Y,F),entita_discriminante_asserita_mafia(Y,F),RisFindallEntDiscMafia))]),1,1).
rule(44,lista_oggetti_discriminanti_mafia(RisFindallOggDiscMafia),and([call_p(findall((Y,F),oggetto_discriminante_asserito_mafia(Y,F),RisFindallOggDiscMafia))]),1,1).

rule(45,lista_entita_discriminanti_corruzione(RisFindallEntDiscCorruzione),and([call_p(findall((Y,F),entita_discriminante_asserita_corruzione(Y,F),RisFindallEntDiscCorruzione))]),1,1).
rule(46,lista_oggetti_discriminanti_corruzione(RisFindallOggDiscCorruzione),and([call_p(findall((Y,F),oggetto_discriminante_asserito_corruzione(Y,F),RisFindallOggDiscCorruzione))]),1,1).

rule(47,lista_entita_discriminanti_terremoto(RisFindallEntDiscTerremoto),and([call_p(findall((Y,F),entita_discriminante_asserita_terremoto(Y,F),RisFindallEntDiscTerremoto))]),1,1).
rule(48,lista_oggetti_discriminanti_terremoto(RisFindallOggDiscTerremoto),and([call_p(findall((Y,F),oggetto_discriminante_asserito_terremoto(Y,F),RisFindallOggDiscTerremoto))]),1,1).

rule(49,n_entita_verbi_cronaca(NECNC),and([lista_verbi_generali_cronaca(CNCL1),lista_entita_discriminanti_furto(CNCL2),lista_entita_discriminanti_omicidio(CNCL3),lista_entita_discriminanti_terrorismo(CNCL4),lista_entita_discriminanti_mafia(CNCL5),lista_entita_discriminanti_corruzione(CNCL6),lista_entita_discriminanti_terremoto(CNCL7),call_p(length(CNCL1,N1)),call_p(length(CNCL2,N2)),call_p(length(CNCL3,N3)),call_p(length(CNCL4,N4)),call_p(length(CNCL5,N5)),call_p(length(CNCL6,N6)),call_p(length(CNCL7,N7)),call_p(NECNC is N1+N2+N3+N4+N5+N6+N7)]),1,1).
rule(50,n_oggetti_cronaca(NOCNC),and([lista_oggetti_discriminanti_furto(CNCOL1),lista_oggetti_discriminanti_omicidio(CNCOL2),lista_oggetti_discriminanti_terrorismo(CNCOL3),lista_oggetti_discriminanti_mafia(CNCOL4),lista_oggetti_discriminanti_corruzione(CNCOL5),lista_oggetti_discriminanti_terremoto(CNCOL6),call_p(length(CNCOL1,N1)),call_p(length(CNCOL2,N2)),call_p(length(CNCOL3,N3)),call_p(length(CNCOL4,N4)),call_p(length(CNCOL5,N5)),call_p(length(CNCOL6,N6)),call_p(NOCNC is N1+N2+N3+N4+N5+N6)]),1,1).

rule(51,n_parole_generali_cronaca(NCNCG),and([lista_oggetti_generali_cronaca(CNCGL1),lista_entita_generali_cronaca(CNCGL2),call_p(length(CNCGL1,N1)),call_p(length(CNCGL2,N2)),call_p(NCNCG is N1+N2)]),1,1).
rule(52,n_parole_discriminanti_cronaca(NCNCD),and([n_entita_verbi_cronaca(NECNC),n_oggetti_cronaca(NOCNC),call_p(NCNCD is NECNC + NOCNC)]),1,1).

rule(53,lista_relazioni_cronaca(RisFindallRelazioniCronaca),and([call_p(findall((X,Y,Z),relazione_cronaca(X,Y,Z),RisFindallRelazioniCronaca))]),1,1).
rule(54,n_relazioni_cronaca(NRCNC),and([lista_relazioni_cronaca(LCNC),call_p(length(LCNC,NRCNC))]),1,1).



%SPORT
rule(55,lista_entita_generali_sport(RisFindallEntGenSport),and([call_p(findall((Y,F),entita_generale_asserita_sport(Y,F),RisFindallEntGenSport))]),1,1).
rule(56,lista_oggetto_generali_sport(RisFindallOggGenSport),and([call_p(findall((Y,F),oggetto_generale_asserito_sport(Y,F),RisFindallOggGenSport))]),1,1).
rule(57,lista_verbo_generali_sport(RisFindallVerbSport),and([call_p(findall((Y,F),verbo_asserito_sport(Y,F),RisFindallVerbSport))]),1,1).

rule(58,lista_entita_discriminanti_motogp(RisFindallEntDiscMotogp),and([call_p(findall((Y,F),entita_discriminante_asserita_motogp(Y,F),RisFindallEntDiscMotogp))]),1,1).
rule(59,lista_oggetto_discriminanti_motogp(RisFindallOggDiscMotogp),and([call_p(findall((Y,F),oggetto_discriminante_asserito_motogp(Y,F),RisFindallOggDiscMotogp))]),1,1).

rule(60,lista_entita_discriminanti_nuoto(RisFindallEntDiscNuoto),and([call_p(findall((Y,F),entita_discriminante_asserita_nuoto(Y,F),RisFindallEntDiscNuoto))]),1,1).
rule(61,lista_oggetto_discriminanti_nuoto(RisFindallOggDiscNuoto),and([call_p(findall((Y,F),oggetto_discriminante_asserito_nuoto(Y,F),RisFindallOggDiscNuoto))]),1,1).

rule(62,lista_entita_discriminanti_f1(RisFindallEntDiscF1),and([call_p(findall((Y,F),entita_discriminante_asserita_f1(Y,F),RisFindallEntDiscF1))]),1,1).
rule(63,lista_oggetto_discriminanti_f1(RisFindallOggDiscF1),and([call_p(findall((Y,F),oggetto_discriminante_asserito_f1(Y,F),RisFindallOggDiscF1))]),1,1).

rule(64,lista_entita_discriminanti_tennis(RisFindallEntDiscTennis),and([call_p(findall((Y,F),entita_discriminante_asserita_tennis(Y,F),RisFindallEntDiscTennis))]),1,1).
rule(65,lista_oggetto_discriminanti_tennis(RisFindallOggDiscTennis),and([call_p(findall((Y,F),oggetto_discriminante_asserito_tennis(Y,F),RisFindallOggDiscTennis))]),1,1).

rule(66,lista_entita_discriminanti_basket(RisFindallEntDiscBasket),and([call_p(findall((Y,F),entita_discriminante_asserita_basket(Y,F),RisFindallEntDiscBasket))]),1,1).
rule(67,lista_oggetto_discriminanti_basket(RisFindallOggDiscBasket),and([call_p(findall((Y,F),oggetto_discriminante_asserito_basket(Y,F),RisFindallOggDiscBasket))]),1,1).

rule(68,lista_entita_discriminanti_pallavolo(RisFindallEntDiscPallavolo),and([call_p(findall((Y,F),entita_discriminante_asserita_pallavolo(Y,F),RisFindallEntDiscPallavolo))]),1,1).
rule(69,lista_oggetto_discriminanti_pallavolo(RisFindallOggDiscPallavolo),and([call_p(findall((Y,F),oggetto_discriminante_asserito_pallavolo(Y,F),RisFindallOggDiscPallavolo))]),1,1).

rule(70,lista_entita_discriminanti_calcio(RisFindallEntDiscCalcio),and([call_p(findall((Y,F),entita_discriminante_asserita_calcio(Y,F),RisFindallEntDiscCalcio))]),1,1).
rule(71,lista_oggetto_discriminanti_calcio(RisFindallOggDiscCalcio),and([call_p(findall((Y,F),oggetto_discriminante_asserito_calcio(Y,F),RisFindallOggDiscCalcio))]),1,1).

rule(72,n_entita_verbi_sport(NESPT),and([lista_verbo_generali_sport(SPTL1),lista_entita_discriminanti_motogp(SPTL2),lista_entita_discriminanti_nuoto(SPTL3),lista_entita_discriminanti_f1(SPTL4),lista_entita_discriminanti_tennis(SPTL5),lista_entita_discriminanti_basket(SPTL6),lista_entita_discriminanti_pallavolo(SPTL7),lista_entita_discriminanti_calcio(SPTL8),call_p(length(SPTL1,N1)),call_p(length(SPTL2,N2)),call_p(length(SPTL3,N3)),call_p(length(SPTL4,N4)),call_p(length(SPTL5,N5)),call_p(length(SPTL6,N6)),call_p(length(SPTL7,N7)),call_p(length(SPTL8,N8)),call_p(NESPT is N1+N2+N3+N4+N5+N6+N7+N8)]),1,1).
rule(73,n_oggetti_sport(NOSPT),and([lista_oggetto_discriminanti_motogp(SPTOL1),lista_oggetto_discriminanti_nuoto(SPTOL2),lista_oggetto_discriminanti_f1(SPTOL3),lista_oggetto_discriminanti_tennis(SPTOL4),lista_entita_discriminanti_basket(SPTOL5),lista_oggetto_discriminanti_pallavolo(SPTOL6),lista_oggetto_discriminanti_calcio(SPTOL7),call_p(length(SPTOL1,N1)),call_p(length(SPTOL2,N2)),call_p(length(SPTOL3,N3)),call_p(length(SPTOL4,N4)),call_p(length(SPTOL5,N5)),call_p(length(SPTOL6,N6)),call_p(length(SPTOL7,N7)),call_p(NOSPT is N1+N2+N3+N4+N5+N6+N7)]),1,1).

rule(74,n_parole_generali_sport(NSPTG),and([lista_entita_generali_sport(SPTEL1),lista_oggetto_generali_sport(SPTOL1),call_p(length(SPTEL1,N1)),call_p(length(SPTOL1,N2)),call_p(NSPTG is N1+N2)]),1,1).
rule(75,n_parole_discriminanti_sport(NSPTD),and([n_entita_verbi_sport(NESPT),n_oggetti_sport(NOSPT), call_p(NSPTD is NESPT + NOSPT)]),1,1).

rule(76,lista_relazioni_sport(RisFindallRelazioniSport),and([call_p(findall((X,Y,Z),relazione_sport(X,Y,Z),RisFindallRelazioniSport))]),1,1).
rule(77,n_relazioni_sport(NRSPT),and([lista_relazioni_sport(LSPT),call_p(length(LSPT,NRSPT))]),1,1).


