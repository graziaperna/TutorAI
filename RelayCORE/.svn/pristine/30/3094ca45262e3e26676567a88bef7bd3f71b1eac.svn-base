
:-_dynamic_(fact/3).
:-_dynamic_(rule/5).

askable(presenta(Paziente,Y), [Paziente, ' presenta ', Y ,' ?']).
askable(ha(Paziente,Y), [Paziente, ' ha ', Y ,' ?']).
askable(non(Paziente,Y), [Paziente, ' non ', Y , ' ?']).
askable(si(Paziente,Y), [Paziente, ' si ', Y, ' ?']).
askable(accusa(Paziente, Y), [Paziente, ' accusa ', Y, ' ?']).

%diagnosi

rule(0,manifesta(X,anoressia_nervosa),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,no_indice_gravita_anoressia)]),1,1).
rule(1,manifesta(X,anoressia_nervosa_in_forma_lieve),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_lieve)]),1,1).
rule(2,manifesta(X,anoressia_nervosa_in_forma_moderata),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_moderata)]),1,1).
rule(3,manifesta(X,anoressia_nervosa_in_forma_grave),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_grave)]),1,1).
rule(4,manifesta(X,anoressia_nervosa_in_forma_gravissima),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_gravissima)]),1,1).

rule(5,manifesta(X,bulimia_nervosa_in_forma_lieve),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_1_a_3_volta_sett_per_3mesi)]),1,1).
rule(6,manifesta(X,bulimia_nervosa_in_forma_moderata),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_4_a_7_volta_sett_per_3mesi)]),1,1).
rule(7,manifesta(X,bulimia_nervosa_in_forma_grave),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_8_a_13_volta_sett_per_3mesi)]),1,1).
rule(8,manifesta(X,bulimia_nervosa_in_forma_gravissima),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_piu_di_14_volte_sett_per_3mesi)]),1,1).

rule(9,manifesta(X,bed-disturbo_da_alimentazione_incontrollata_in_forma_lieve),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_1_a_3_volta_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(10,manifesta(X,bed-disturbo_da_alimentazione_incontrollata_in_forma_moderata),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_4_a_7_volta_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(11,manifesta(X,bed-disturbo_da_alimentazione_incontrollata_in_forma_grave),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_8_a_13_volta_sett_per_6mesi_),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(12,manifesta(X,bed-disturbo_da_alimentazione_incontrollata_in_forma_gravissima),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_piu_di_14_volte_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).

rule(13,manifesta(X,disturbo_evitante/restrittivo_dellassunzione_di_cibo),and([mostra(X,comportamento_generale_3),mostra(X,criterio_diagnostico_assenza_interesse_cibo),mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali),mostra(X,fattore_caratterizzante_incapacita_raggiungere_peso_crescita),mostra(X,criterio_diagnostico_no_condizione_medica_mentale)]),1,1).
rule(14,manifesta(X,anoressia_riversa_(vigoressia)),and([mostra(X,comportamento_generale_4),mostra(X,fattore_caratterizzante_anomalia_percezione_peso_e_corpo),mostra(X,fattore_caratterizzante_abuso_integratori_diete),mostra(X,fattore_caratterizzante_esercizio_fisico_compulsivo)]),1,1).
rule(15,manifesta(X,ortoressia),and([mostra(X,comportamento_generale_5),mostra(X,fattore_caratterizzante_ossessione_cibo_sano),mostra(X,fattore_caratterizzante_focalizzazione_qualita_cibo),mostra(X,fattore_caratterizzante_evitamento_ossessivo_cibi_non_controllati),mostra(X,fattore_caratterizzante_evitamento_situazioni_sociali_cibo_non_controllato),mostra(X,fattore_caratterizzante_convizione_proprie_scelte)]),1,1).
rule(16,manifesta(X,disturbo_da_ruminazione),and([mostra(X,comportamento_generale_6),mostra(X,criterio_diagnostico_ripetuto_rigurgito),mostra(X,criterio_diagnostico_no_condizione_medica_mentale)]),1,1).
rule(17,manifesta(X,pica),and([mostra(X,comportamento_generale_6),mostra(X,criterio_diagnostico_ingestione_sostanze_non_alimentari),mostra(X,criterio_diagnostico_no_condizione_medica_mentale),mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali)]),1,1).

rule(18,manifesta(X,sindrome_da_alimentazione_notturna),and([mostra(X,comportamento_generale_7),mostra(X,fattore_caratterizzante_alimentazione_notturna),not(mostra(X,criterio_diagnostico_mancanza_controllo_mangiare))]),1,1).
rule(19,manifesta(X,disturbo_purgativo),and([mostra(X,comportamento_generale_8),not(mostra(X,criterio_diagnostico_abbuffate_incontrollate)),not(mostra(X,criterio_diagnostico_mancanza_controllo_mangiare)),mostra(X,criterio_diagnostico_comportamento_compenso),not(mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_1_a_3_volta_sett_per_6mesi)),not(mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)),mostra(X,fattore_caratterizzante_comportamento_purgativo)]),1,1).
rule(20,manifesta(X,anoressia_nervosa_con_restrizioni),and([mostra(X,comportamento_generale_09),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),not(mostra(X,criterio_diagnostico_abbuffate_incontrollate)),not(mostra(X,criterio_diagnostico_comportamento_compenso)),non(X,ha_avuto_durante_gli_ultimi_3_mesi,_episodi_di_abbuffate_o_comportamenti_di_eliminazione)]),1,1).
rule(21,manifesta(X,anoressia_nervosa_con_abbuffate_e/o_manovre_di_elinazione),and([mostra(X,comportamento_generale_10),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),ha(X,avuto_negli_ultimi_3_mesi,_ricorrenti_episodi_di_abbuffate_o_comportamenti_di_eliminazione)]),1,1).
rule(22,manifesta(X,anoressia_nervosa_atipica),and([not(mostra(X,comportamento_generale_0)),not(mostra(X,criterio_diagnostico_basso_peso)),not(mostra(X,criterio_diagnostico_paura_aumentare_peso)),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),_mostra(X,bmi_regolare)]),1,1).

%comportamenti
rule(30,mostra(X,comportamento_generale_0),and([mostra(X,bmi_sottopeso),or([mostra(X,sesso_femminile),mostra(X,eta_rischio_12_25_anni)])]),1,1).
rule(31,mostra(X,comportamento_generale_1),and([mostra(X,abbuffate),or([mostra(X,vomito),mostra(X,lassativi)]),or([mostra(X,sesso_femminile),mostra(X,eta_rischio_12_25_anni)])]),1,1).
rule(32,mostra(X,comportamento_generale_2),and([mostra(X,abbuffate),mostra(X,mancanza_controllo_mangiare),not(mostra(X,vomito)),not(mostra(X,lassativi)),or([mostra(X,abbuffate_incontrollate),ha(X,abbuffate_convulsive_nelle_quali_mangia_grandi_quantita_di_cibo_pur_non_sentendo_fame),ha(X,abbuffate_convulsive_nelle_quali_mangia_in_solitudine_a_causa_dellimbarazzo_per_le_quantita_di_cibo_ingerite),ha(X,abbuffate_convulsive_nelle_quali_prova_disgusto_di_se,_depressione_o_intensa_colpa_dopo_aver_mangiato_troppo)])]),1,1).
rule(33,mostra(X,comportamento_generale_3),and([mostra(X,bmi_sottopeso),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi)),or([ha(X,significativa_carenza_nutrizionale),ha(X,dipendenza_dalla_nutrizione_enterale_o_da_supplementi_nutrizionali_orali),ha(X,marcata_interferenza_col_funzionamento_psicosociale)])]),1,1).
rule(34,mostra(X,comportamento_generale_4),and([or([mostra(X,bmi_sovrappeso),mostra(X,bmi_regolare)]),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi)),mostra(X,sesso_maschile)]),1,1).
rule(35,mostra(X,comportamento_generale_5),and([or([mostra(X,bmi_regolare),mostra(X,bmi_sottopeso)]),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(36,mostra(X,comportamento_generale_6),and([not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),or([mostra(X,eta_infantile_0_2_anni),mostra(X,eta_3_11_anni)]),not(mostra(X,lassativi))]),1,1).
rule(37,mostra(X,comportamento_generale_7),and([mostra(X,abbuffate),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(38,mostra(X,comportamento_genermale_8),and([not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),mostra(X,vomito),mostra(X,lassativi)]),1,1).
rule(39,mostra(X,comportamento_generale_09),and([mostra(X,bmi_sottopeso),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(40,mostra(X,comportamento_generale_10),and([mostra(X,bmi_sottopeso),or([mostra(X,abbuffate),mostra(X,vomito),mostra(X,lassativi)])]),1,1).

%robe
rule(41,mostra(X,criterio_diagnostico_basso_peso),and([mostra(X,bmi_sottopeso),accusa(X,restrizione,_apporto_energetico_che_induce_un_significativo_basso_peso_ovvero_un_peso_minore_del_minimo_normale_o,_per_i_bambini_e_gli_adolescenti,_minore_del_minimo_atteso)]),1,1).
rule(42,mostra(X,criterio_diagnostico_paura_aumentare_peso),and([accusa(X,intensa_paura_di_aumentare_di_peso_o_di_ngrassare),accusa(X,comportamento_persistente_che_interferisce_con_l_aumento_di_peso,_nonostante_il_peso_sia_significativamente_basso)]),1,1).
rule(43,mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),and([accusa(X,valutazione_di_se_inappropriata_influenzata_dalla_forma_e_dal_peso_del_corpo)]),1,1).
rule(44,mostra(X,criterio_diagnostico_abbuffate_incontrollate),and([mostra(X,abbuffate_incontrollate),mostra(X,mancanza_controllo_mangiare)]),1,1).
rule(45,mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),and([or([mostra(X,mancanza_controllo_mangiare),presenta(X,sensazione_di_perdita_di_controllo_durante_labbuffata)])]),1,1).
rule(46,mostra(X,criterio_diagnostico_comportamento_compenso),and([or([mostra(X,vomito),mostra(X,lassativi)])]),1,1).
rule(47,mostra(X,criterio_diagnostico_abbuffata_media_da_1_a_3_volta_sett_per_3mesi),and([ha(X,una_frequenza_di_abbuffate_e_condotte_compensatorie_che_avvengono_entrambe_in_media_da_una_a_tre_volte_a_settimana_per_3_mesi)]),1,1).
rule(48,mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_1_a_3_volta_sett_per_6mesi),and([ha(X,abbuffate_compulsive,_in_media,_almeno_da_una_a_tre_volte_a_settimana_per_almeno_sei_mesi)]),1,1).
rule(49,mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive),and([ha(X,abbuffate_compulsive_che_suscitano_sofferenza_e_disagio)]),1,1).
rule(50,mostra(X,criterio_diagnostico_assenza_interesse_cibo),and([presenta(X,assenza_di_interesse_per_l’alimentazione_o_per_il_cibo),presenta(X,evitamento_basato_sulle_caratteristiche_sensoriali_del_cibo)]),1,1).
rule(51,mostra(X,fattore_caratterizzante_ossessione_cibo_sano),and([presenta(X,ossessione_per_il_cibo_sano)]),1,1).




rule(52,mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali),and([or([accusa(X,disturbo_non_connesso_con_la_mancanza_di_cibo_o_associato_a_pratiche_culturali),accusa(X,comportamento_di_ingestione_che_non_fa_parte_di_una_pratica_culturalmente_sancita)])]),1,1).
rule(53,mostra(X,fattore_caratterizzante_abuso_integratori_diete),and([presenta(X,abuso_di_integratori,_anabolizzanti_e_diete_iperproteiche)]),1,1).
rule(54,mostra(X,fattore_caratterizzante_incapacita_raggiungere_peso_crescita),and([presenta(X,significativa_perdita_di_peso_o_nei_bambini_incapacita_a_raggiungere_il_peso_relativo_alla_crescita)]),1,1).
rule(55,mostra(X,criterio_diagnostico_no_condizione_medica_mentale),and([or([accusa(X,anomalia_non_meglio_attribuibile_a_una_condizione_medica_o_ad_un_altro_disturbo_mentale),presenta(X,comportamento_non_dovuto_ad_una_condizione_gastrointestinale_associata_o_ad_unaltra_condizione_medica_generale_(per_es.,_reflusso_gastro-esofageo,_stenosi_del_piloro)),accusa(X,comportamento_di_ingestione_si_manifesta_esclusivamente_durante_il_decorso_di_un_altro_disturbo_mentale_(per_es.,_ritardo_mentale,_disturbo_dello_spetto_autistico,_schizofrenia,_disturbo_pervasivo_dello_sviluppo),_o_condizione_medica_(gravidanza_compresa)_e_sufficientemente_grave_da_giustificare_di_per_se_attenzione_clinica)])]),1,1).
rule(56,mostra(X,fattore_caratterizzante_esercizio_fisico_compulsivo),and([presenta(X,esercizio_fisico_compulsivo)]),1,1).
rule(57,mostra(X,criterio_diagnostico_ripetuto_rigurgito),and([presenta(X,ripetuto_rigurgito_di_cibo_per_un_periodo_di_almeno_1_mese._il_cibo_puo_essere_rimasticato,_ringoiato_o_eliminato)]),1,1).
rule(58,mostra(X,criterio_diagnostico_ingestione_sostanze_non_alimentari),and([presenta(X,persistente_ingestione_di_sostanze_non_alimentari_per_un_periodo_di_almeno_1_mese),presenta(X,ingestione_di_sostanze_non_alimentari_inappropriata_rispetto_al_livello_di_sviluppo)]),1,1).
rule(59,mostra(X,fattore_caratterizzante_alimentazione_notturna),and([presenta(X,ricorrenti_episodi_di_alimentazione_notturna_che_si_manifestano_dopo_il_risveglio_o_con_un_eccessivo_consumo_di_cibo_dopo_il_pasto_serale),presenta(X,alimentazione_notturna_non_giustificata_da_influenze_esterne_quali_cambiamenti_del_ciclo_individuale_del_sonno/veglia_o_da_norme_sociali_specifiche)]),1,1).
rule(60,mostra(X,fattore_caratterizzante_anomalia_percezione_peso_e_corpo),and([presenta(X,autopercezione_di_gracilita,_eminentemente_maschile),presenta(X,comportamento_alimentare_alterato)]),1,1).
rule(61,mostra(X,fattore_caratterizzante_focalizzazione_qualita_cibo),and([presenta(X,focalizzazione_non_sulla_quantita_di_cibo_ma_sulla_qualita)]),1,1).
rule(62,mostra(X,fattore_caratterizzante_evitamento_ossessivo_cibi_non_controllati),and([presenta(X,evitamento_ossessivo_per_i_cibi_non_controllati)]),1,1).
rule(63,mostra(X,fattore_caratterizzante_evitamento_situazioni_sociali_cibo_non_controllato),and([presenta(X,evitamento_delle_situazioni_sociali_che_espongono_al_non_controllo_del_cibo)]),1,1).
rule(64,mostra(X,fattore_caratterizzante_convizione_proprie_scelte),and([presenta(X,convinzione_fideistica_delle_proprie_scelte)]),1,1).
rule(65,mostra(X,fattore_caratterizzante_comportamento_purgativo),and([presenta(X,comportamento_purgativo_ricorrente_per_modificare_peso_e_forma_del_corpo)]),1,1).
rule(66,mostra(X,bmi_regolare),and([presenta(X,peso_regolare_attraverso_l_indice_di_massa_corporea_(BMI))]),1,1).
rule(67,mostra(X,bmi_sovrappeso),and([presenta(X,sovrappeso_attraverso_l_indice_di_massa_corporea_(BMI))]),1,1).
rule(68,mostra(X,bmi_sottopeso),and([presenta(X,sottopeso_attraverso_l_indice_di_massa_corporea_(BMI))]),1,1).
rule(69,mostra(X,abbuffate),and([presenta(X,episodi_di_abbuffate)]),1,1).
rule(70,mostra(X,mancanza_controllo_mangiare),and([presenta(X,mancanza_di_controllo_nell_atto_di_mangiare)]),1,1).
rule(71,mostra(X,pensieri_tentativi_suicidio),and([presenta(X,pensieri_o_tentativi_di_suicidio)]),1,1).
rule(72,mostra(X,vomito),and([presenta(X,episodi_di_vomito_autoindotto)]),1,1).
rule(73,mostra(X,lassativi),and([presenta(X,uso_di_lassativi)]),1,1).
rule(74,mostra(X,abbuffate_incontrollate),and([mostra(X,abbuffate),ha(X,abbuffate_convulsive_nelle_quali_mangia_molto_piu_rapidamente_del_normale)]),1,1).
rule(75,mostra(X,criterio_diagnostico_abbuffata_media_da_4_a_7_volta_sett_per_3mesi),and([ha(X,una_frequenza_di_abbuffate_e_condotte_compensatorie_che_avvengono_in_media_da_quattro_a_sette_volte_a_settimana_per_3_mesi)]),1,1).
rule(76,mostra(X,criterio_diagnostico_abbuffata_media_da_8_a_13_volta_sett_per_3mesi),and([ha(X,una_frequenza_di_abbuffate_e_condotte_compensatorie_che_avvengono_in_media_da_otto_a_tredici_volte_a_settimana_per_3_mesi)]),1,1).
rule(77,mostra(X,criterio_diagnostico_abbuffata_media_piu_di_14_volte_sett_per_3mesi),and([ha(X,una_frequenza_di_abbuffate_e_condotte_compensatorie_che_avvengono_in_media_più_di_quattordici_volte_a_settimana_per_3_mesi)]),1,1).
rule(78,mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_4_a_7_volta_sett_per_6mesi),and([ha(X,abbuffate_compulsive,_in_media,_almeno_da_quattro_a_sette_volte_a_settimana_per_almeno_sei_mesi)]),1,1).
rule(79,mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_da_8_a_13_volta_sett_per_6mesi),and([ha(X,abbuffate_compulsive,_in_media,_almeno_da_otto_a_tredici_volte_a_settimana_per_almeno_sei_mesi)]),1,1).
rule(80,mostra(X,criterio_diagnostico_abbuffata_convulsiva_media_piu_di_14_volte_sett_per_6mesi),and([ha(X,abbuffate_compulsive,_in_media,_almeno_da_una_a_tre_volte_a_settimana_per_almeno_sei_mesi)]),1,1).
rule(81,mostra(X,gravita_disturbo_lieve),and([presenta(X,un_indice_di_massa_corporea_BMI_maggiore_o_uguale_a_17)]),1,1).
rule(82,mostra(X,gravita_disturbo_moderata),and([presenta(X,un_indice_di_massa_corporea_BMI_compreso_tra_16_e_16_99)]),1,1).
rule(83,mostra(X,gravita_disturbo_grave),and([presenta(X,un_indice_di_massa_corporea_BMI_compreso_tra_15_e_15_99)]),1,1).
rule(84,mostra(X,gravita_disturbo_gravissima),and([presenta(X,un_indice_di_massa_corporea_BMI_minore_di_15)]),1,1).
rule(85,mostra(X,sesso_maschile),and([presenta(X,sesso_maschile)]),1,1).
rule(86,mostra(X,sesso_femminile),and([presenta(X,sesso_femminile)]),1,1).
rule(87,mostra(X,eta_infantile_0_2_anni),and([presenta(X,eta_infantile_compresa_tra_0_e_2_anni)]),1,1).
rule(88,mostra(X,eta_3_11_anni),and([presenta(X,eta_compresa_tra_3_e_11_anni)]),1,1).
rule(89,mostra(X,eta_rischio_12_25_anni),and([presenta(X,eta_a_rischio_compresa_tra_i_12_e_i_25_anni)]),1,1).
rule(90,mostra(X,eta_superiore_25_anni),and([presenta(X,eta_superiore_ai_25_anni)]),1,1).

rule(91,mostra(X,no_indice_gravita_anoressia),and([not(mostra(X,gravita_disturbo_lieve)),not(mostra(X,gravita_disturbo_moderata)),not(mostra(X,gravita_disturbo_grave)),not(mostra(X,gravita_disturbo_gravissima))]),1,1).
rule(92,mostra(X,vecchia_situazione_cura_dca),and([presenta(X,una_vecchia_situazione_di_cura_per_DCA)]),1,1).

