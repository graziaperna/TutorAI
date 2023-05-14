module_name('categorizza.pl').

%punteggio della cultura
rule(1,punteggio_parole_cultura(TotParClt),and([n_parole_cultura(NPCLT),call_p(TotParClt is NPCLT*0.75)]),1,1).
rule(2,punteggio_relazione_cultura(TotRelClt),and([n_relazioni_cultura(NRCLT),call_p(TotRelClt is NRCLT*4)]),1,1).

rule(3,punteggio_cultura(TotCLT),and([punteggio_relazione_cultura(PTRCLT),punteggio_parole_cultura(PTPCLT),call_p(TotCLT is PTRCLT+PTPCLT)]),1,1).



%punteggio politica
rule(4,punteggio_parole_politica(TotParPol),and([n_parole_politica_generali(NGPOL),
				      		n_parole_politica_discriminanti(NDPOL),
				      		call_p(TotGenPol is NDPOL*0.85),
				     	        call_p(TotDiscPol is NGPOL*0.50),
				      		call_p(TotParPol is TotGenPol+TotDiscPol) 
				        ]),1,1).

rule(5,punteggio_relazione_politica(TotRelPol),and([n_relazioni_politica(NRPOL),call_p(TotRelPol is NRPOL*4)]),1,1).

rule(6,punteggio_politica(TotCLT),and([punteggio_relazione_politica(NRPOL),punteggio_parole_politica(NPPOL),call_p(TotCLT is NPPOL+NRPOL)]),1,1).


%punteggio mondo
rule(7,punteggio_parole_mondo(TotParMnd),and([n_parole_mondo_generali(NGMND), 
				      	      n_parole_mondo_discriminanti(NDMND),
				              call_p(TotGenMnd is NDMND*0.85),
				              call_p(TotDiscMnd is NGMND*0.50),
				              call_p(TotParMnd is TotGenMnd+TotDiscMnd) 
				  	]),1,1).

rule(8,punteggio_relazione_mondo(TotRelMnd),and([n_relazioni_mondo(NRMND),call_p(TotRelMnd is NRMND*4)]),1,1).

rule(9,punteggio_mondo(TotMDN),and([punteggio_relazione_mondo(NRMND),punteggio_parole_mondo(NPMND),call_p(TotMDN is NRMND+NPMND)]),1,1).


%punteggio economia
rule(10,punteggio_parole_economia(TotParEcn),and([n_parole_economia_generali(NGECN),
				      		  n_parole_economia_discriminanti(NDECN),
				      	          call_p(TotDiscEcn is NDECN*0.85),
				      		  call_p(TotGenEcn is NGECN*0.50),
				      		  call_p(TotParEcn is TotDiscEcn+TotGenEcn) 
					]),1,1).


rule(11,punteggio_relazione_economia(TotRelEcn),and([n_relazioni_economia(NRECN),call_p(TotRelEcn is NRECN*4)]),1,1).

rule(12,punteggio_economia(TotECN),and([punteggio_relazione_economia(NRECN),punteggio_parole_economia(NPECN),call_p(TotECN is NRECN+NPECN)]),1,1).


%punteggio cronaca
rule(13,punteggio_parole_cronaca(TotParCnc),and([n_parole_generali_cronaca(NGCNC), 
				       	        n_parole_discriminanti_cronaca(NDCNC),	
				      	        call_p(TotDiscCnc is NDCNC*0.85),
						call_p(TotGenCnc is NGCNC*0.50),
				      	        call_p(TotParCnc is TotDiscCnc+TotGenCnc)
					]),1,1).


rule(14,punteggio_relazione_cronaca(TotRelCNC),and([n_relazioni_cronaca(NRCNC),call_p(TotRelCNC is NRCNC*4)]),1,1).

rule(15,punteggio_cronaca(TotCNC),and([punteggio_relazione_cronaca(NRCNC),punteggio_parole_cronaca(NPCNC),call_p(TotCNC is NRCNC+NPCNC)]),1,1).




%punteggio sport
rule(16,punteggio_parole_sport(TotParSpt),and([n_parole_generali_sport(NGSPT), 
				    	      n_parole_discriminanti_sport(NDSPT),
				    	      call_p(TotDiscSpt is NDSPT*0.85),
				    	      call_p(TotGenSpt is NGSPT*0.50),
				    	      call_p(TotParSpt is TotDiscSpt+TotGenSpt)
					 ]),1,1).




rule(17,punteggio_relazione_sport(TotRelSpt),and([n_relazioni_sport(NRSPT),call_p(TotRelSpt is NRSPT*4)]),1,1).

rule(18,punteggio_sport(TotSPT),and([punteggio_relazione_sport(NRSPT),punteggio_parole_sport(NPSPT),call_p(TotSPT is NRSPT+NPSPT)]),1,1).




%calcolo della categoria col punteggio massimo
rule(19,categoria_articolo(cultura),and([punteggio_cultura(X1),punteggio_sport(X2),punteggio_politica(X3),punteggio_economia(X4),punteggio_mondo(X5),punteggio_cronaca(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).


rule(20,categoria_articolo(politica),and([punteggio_politica(X1),punteggio_cultura(X2),punteggio_sport(X3),punteggio_economia(X4),punteggio_mondo(X5),punteggio_cronaca(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).


rule(21,categoria_articolo(economia),and([punteggio_economia(X1),punteggio_politica(X2),punteggio_cultura(X3),punteggio_sport(X4),punteggio_mondo(X5),punteggio_cronaca(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).


rule(22,categoria_articolo(mondo),and([punteggio_mondo(X1),punteggio_economia(X2),punteggio_politica(X3),punteggio_cultura(X4),punteggio_sport(X5),punteggio_cronaca(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).

rule(23,categoria_articolo(cronaca),and([punteggio_cronaca(X1),punteggio_mondo(X2),punteggio_economia(X3),punteggio_politica(X4),punteggio_cultura(X5),punteggio_sport(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).


rule(24,categoria_articolo(sport),and([punteggio_sport(X1),punteggio_cultura(X2),punteggio_politica(X3),punteggio_economia(X4),punteggio_mondo(X5),punteggio_cronaca(X6),           
					call_p(X1>X2),call_p(X1>X3),call_p(X1>X4),call_p(X1>X5),call_p(X1>X6)					
					]),1,1).




%%Regole per il calcolo delle sottocategorie di sport e cronaca


%%Cronaca
rule(25,punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
	and([
		categoria_articolo(cronaca),
		lista_oggetti_discriminanti_terremoto(OggDiscTerr),
		lista_entita_discriminanti_terremoto(EntDiscTerr),
		call_p(length(OggDiscTerr,N1)),
		call_p(length(EntDiscTerr,N2)),
		call_p(NTerremoto is N1+N2)
	]),1,1).

rule(26,punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
	and([
		lista_oggetti_discriminanti_corruzione(OggDiscCorr), 
		lista_entita_discriminanti_corruzione(EntDiscCorr),
		call_p(length(OggDiscCorr,N3)),
		call_p(length(EntDiscCorr,N4)),
		call_p(NCorruzione is N3+N4)
	]),1,1).

rule(27,punteggio_sotto_categorie_cronaca_mafia(NMafia),
	and([
		lista_oggetti_discriminanti_mafia(OggDiscMaf),
		lista_entita_discriminanti_mafia(EntDiscMaf),
		call_p(length(OggDiscMaf,N5)),
		call_p(length(EntDiscMaf,N6)),
		call_p(NMafia is N5+N6)
	]),1,1).

rule(28,punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
	and([
		lista_oggetti_discriminanti_terrorismo(OggDiscTerror),
		lista_entita_discriminanti_terrorismo(EntDiscTerror),
		call_p(length(OggDiscTerror,N7)),
		call_p(length(EntDiscTerror,N8)),
		call_p(NTerrorismo is N7+N8)
	]),1,1).

rule(29,punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
	and([
		lista_oggetti_discriminanti_omicidio(OggDiscOmi), 
		lista_entita_discriminanti_omicidio(EntDiscOmi),
		call_p(length(OggDiscOmi,N9)),
		call_p(length(EntDiscOmi,N10)),
		call_p(NOmicidio is N9+N10)
	]),1,1).


rule(30,punteggio_sotto_categorie_cronaca_furto(NFurto),
	and([
		lista_oggetti_discriminanti_furto(OggDiscFur),
		lista_entita_discriminanti_furto(EntDiscFur),
		call_p(length(OggDiscFur,N11)),
		call_p(length(EntDiscFur,N12)),
		call_p(NFurto is N11+N12)
	]),1,1).








%%Sport

rule(31,punteggio_sotto_categorie_sport_calcio(NCalcio),
	and([
		categoria_articolo(sport),
		lista_entita_discriminanti_calcio(EntDiscCal),
		lista_oggetto_discriminanti_calcio(OggDiscCal),
		call_p(length(EntDiscCal,N13)),
		call_p(length(OggDiscCal,N14)),
		call_p(NCalcio is N13+N14)
	]),1,1).


rule(32,punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
	and([
		lista_oggetto_discriminanti_pallavolo(OggDiscPall),
		lista_entita_discriminanti_pallavolo(EntDiscPall),
		call_p(length(OggDiscPall,N15)),
		call_p(length(EntDiscPall,N16)),
		call_p(NPallavolo is N15+N16)
	]),1,1).


rule(33,punteggio_sotto_categorie_sport_basket(NBasket),
	and([
		lista_oggetto_discriminanti_basket(OggDiscBask),
		lista_entita_discriminanti_basket(EntDiscBask),
		call_p(length(OggDiscBask,N17)),
		call_p(length(EntDiscBask,N18)),
		call_p(NBasket is N17+N18)
	]),1,1).


rule(34,punteggio_sotto_categorie_sport_tennis(NTennis),
	and([
		lista_oggetto_discriminanti_tennis(OggDiscTenn),
		lista_entita_discriminanti_tennis(EntDiscTenn),
		call_p(length(OggDiscTenn,N19)),
		call_p(length(EntDiscTenn,N20)),
		call_p(NTennis is N19+N20)
	]),1,1).

rule(35,punteggio_sotto_categorie_sport_formula_uno(NF1),
	and([
		lista_oggetto_discriminanti_f1(OggDiscFuno),
		lista_entita_discriminanti_f1(EntDiscFuno),
		call_p(length(OggDiscFuno,N21)),
		call_p(length(EntDiscFuno,N22)),
		call_p(NF1 is N21+N22)
	]),1,1).


rule(36,punteggio_sotto_categorie_sport_nuoto(NNuoto),
	and([
		lista_oggetto_discriminanti_nuoto(OggDiscNuo),
		lista_entita_discriminanti_nuoto(EntDiscNuo),
		call_p(length(OggDiscNuo,N23)),
		call_p(length(EntDiscNuo,N24)),
		call_p(NNuoto is N23+N24)
	]),1,1).

rule(37,punteggio_sotto_categorie_sport_moto_gp(NMotoGp),
	and([
		lista_oggetto_discriminanti_motogp(OggDiscMoto),
		lista_entita_discriminanti_motogp(EntDiscMoto),
		call_p(length(OggDiscMoto,N25)),
		call_p(length(EntDiscMoto,N26)),
		call_p(NMotoGp is N25+N26)
	]),1,1).




%%Calcolo della sottocategoria cronaca
rule(38,sotto_categoria_articolo(terremoto),and([punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
						 punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						 punteggio_sotto_categorie_cronaca_mafia(NMafia),
						 punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						 punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						 punteggio_sotto_categorie_cronaca_furto(NFurto),           
					call_p(NTerremoto>NCorruzione),call_p(NTerremoto>NMafia),call_p(NTerremoto>NTerrorismo),call_p(NTerremoto>NOmicidio),call_p(NTerremoto>NFurto)					
					]),1,1).

rule(39,sotto_categoria_articolo(corruzione),and([punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
						  punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						  punteggio_sotto_categorie_cronaca_mafia(NMafia),
						  punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						  punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						  punteggio_sotto_categorie_cronaca_furto(NFurto),          
					call_p(NCorruzione>NTerremoto),call_p(NCorruzione>NMafia),call_p(NCorruzione>NTerrorismo),call_p(NCorruzione>NOmicidio),call_p(NCorruzione>NFurto)					
					]),1,1).

rule(40,sotto_categoria_articolo(mafia),and([    punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
					         punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						 punteggio_sotto_categorie_cronaca_mafia(NMafia),
						 punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						 punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						 punteggio_sotto_categorie_cronaca_furto(NFurto),           
					call_p(NMafia>NTerremoto),call_p(NMafia>NCorruzione),call_p(NMafia>NTerrorismo),call_p(NMafia>NOmicidio),call_p(NMafia>NFurto)					
					]),1,1).

rule(41,sotto_categoria_articolo(terrorismo),and([punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
						 punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						 punteggio_sotto_categorie_cronaca_mafia(NMafia),
						 punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						 punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						 punteggio_sotto_categorie_cronaca_furto(NFurto),           
					call_p(NTerrorismo>NTerremoto),call_p(NTerrorismo>NCorruzione),call_p(NTerrorismo>NMafia),call_p(NTerrorismo>NOmicidio),call_p(NTerrorismo>NFurto)					
					]),1,1).

rule(42,sotto_categoria_articolo(omicidio),and([punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
						 punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						 punteggio_sotto_categorie_cronaca_mafia(NMafia),
						 punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						 punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						 punteggio_sotto_categorie_cronaca_furto(NFurto),            
					call_p(NOmicidio>NTerremoto),call_p(NOmicidio>NCorruzione),call_p(NOmicidio>NMafia),call_p(NOmicidio>NTerrorismo),call_p(NOmicidio>NFurto)					
					]),1,1).

rule(43,sotto_categoria_articolo(furto),and([	 punteggio_sotto_categorie_cronaca_terremoto(NTerremoto),
						 punteggio_sotto_categorie_cronaca_corruzione(NCorruzione),
						 punteggio_sotto_categorie_cronaca_mafia(NMafia),
						 punteggio_sotto_categorie_cronaca_terrorismo(NTerrorismo),
						 punteggio_sotto_categorie_cronaca_omicidio(NOmicidio),
						 punteggio_sotto_categorie_cronaca_furto(NFurto),          
					call_p(NFurto>NTerremoto),call_p(NFurto>NCorruzione),call_p(NFurto>NMafia),call_p(NFurto>NTerrorismo),call_p(NFurto>NOmicidio)					
					]),1,1).





%%calcolo della sottocategoria di sport
rule(44,sotto_categoria_articolo(calcio),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),          
					call_p(NCalcio>NPallavolo),call_p(NCalcio>NBasket),call_p(NCalcio>NTennis),call_p(NCalcio>NF1),call_p(NCalcio>NNuoto),call_p(NCalcio>NMotoGp)					
					]),1,1).

rule(45,sotto_categoria_articolo(pallavolo),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						 punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						 punteggio_sotto_categorie_sport_basket(NBasket),
						 punteggio_sotto_categorie_sport_tennis(NTennis),
						 punteggio_sotto_categorie_sport_formula_uno(NF1),
						 punteggio_sotto_categorie_sport_nuoto(NNuoto),
						 punteggio_sotto_categorie_sport(NMotoGp),           
					call_p(NPallavolo>NCalcio),call_p(NPallavolo>NBasket),call_p(NPallavolo>NTennis),call_p(NPallavolo>NF1),call_p(NPallavolo>NNuoto),call_p(NPallavolo>NMotoGp)					
					]),1,1).

rule(46,sotto_categoria_articolo(basket),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),           
					call_p(NBasket>NCalcio),call_p(NBasket>NPallavolo),call_p(NBasket>NTennis),call_p(NBasket>NF1),call_p(NBasket>NNuoto),call_p(NBasket>NMotoGp)					
					]),1,1).

rule(47,sotto_categoria_articolo(tennis),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),            
					call_p(NTennis>NCalcio),call_p(NTennis>NPallavolo),call_p(NTennis>NBasket),call_p(NTennis>NF1),call_p(NTennis>NNuoto),call_p(NTennis>NMotoGp)					
					]),1,1).

rule(48,sotto_categoria_articolo(formula1),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),            
					call_p(NF1>NCalcio),call_p(NF1>NPallavolo),call_p(NF1>NBasket),call_p(NF1>NTennis),call_p(NF1>NNuoto),call_p(NF1>NMotoGp)					
					]),1,1).

rule(49,sotto_categoria_articolo(nuoto),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),             
					call_p(NNuoto>NCalcio),call_p(NNuoto>NPallavolo),call_p(NNuoto>NBasket),call_p(NNuoto>NTennis),call_p(NNuoto>NF1),call_p(NNuoto>NMotoGp)					
					]),1,1).

rule(50,sotto_categoria_articolo(motogp),and([punteggio_sotto_categorie_sport_calcio(NCalcio),
						punteggio_sotto_categorie_sport_pallavolo(NPallavolo),
						punteggio_sotto_categorie_sport_basket(NBasket),
						punteggio_sotto_categorie_sport_tennis(NTennis),
						punteggio_sotto_categorie_sport_formula_uno(NF1),
						punteggio_sotto_categorie_sport_nuoto(NNuoto),
						punteggio_sotto_categorie_sport_moto_gp(NMotoGp),            
					call_p(NMotoGp>NCalcio),call_p(NMotoGp>NPallavolo),call_p(NMotoGp>NBasket),call_p(NMotoGp>NTennis),call_p(NMotoGp>NF1),call_p(NMotoGp>NNuoto)					
					]),1,1).




