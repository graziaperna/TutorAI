:- dynamic (fact/3).
:- dynamic (rule/5).

askable(ha(X,Y),'Ha ',Y).
askable(presenta(X,Y),'Presenta ',Y).
askable(accusa(X,Y), 'Accusa ',Y).

askablemenu(indice_bmi(X,Y), 'Indica indice di massa corporea', [minore_di_15, compreso_tra_15_e_15_99, compreso_tra_16_e_16_99, maggiore_o_uguale_a_17], indice_bmi, X, Y).
askablemenu(frequenza_abbuffate(X,Y), 'Indica la frequenza media in 3 mesi di abbuffate e condotte compensatorie', [una_tre_volte_a_settimana, quattro_sette_volte_a_settimana, otto_tredici_volte_a_settimana, piu_di_quattordici_volte_a_settimana], frequenza_abbuffate, X, Y).
askablemenu(frequenza_compulsiva(X,Y), 'Indica la frequenza media in 6 mesi di abbuffate compulsive', [una_tre_volte_a_settimana, quattro_sette_volte_a_settimana, otto_tredici_volte_a_settimana, piu_di_quattordici_volte_a_settimana], frequenza_compulsiva, X, Y).
askablemenu(eta(X,Y), 'Indica eta', [tra_0_2_anni, tra_3_11_anni, tra_12_25_anni, maggiore_25_anni], eta, X, Y).
askablemenu(peso_bmi(X,Y), 'Indica peso rispetto a indice di massa corporea', [regolare, sovrappeso, sottopeso], peso_bmi, X, Y).

%diagnosi
rule(1,manifesta(X,'anoressia nervosa in forma lieve'),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_lieve)]),1,1).
rule(2,manifesta(X,'anoressia nervosa in forma moderata'),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_moderata)]),1,1).
rule(3,manifesta(X,'anoressia nervosa in forma grave'),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_grave)]),1,1).
rule(4,manifesta(X,'anoressia nervosa in forma gravissima'),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,gravita_disturbo_gravissima)]),1,1).
rule(94,manifesta(X,'anoressia nervosa'),and([mostra(X,comportamento_generale_0),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,no_indice_gravita_anoressia)]),1,1).
rule(22,manifesta(X,'anoressia nervosa atipica'),and([not(mostra(X,comportamento_generale_0)),not(mostra(X,criterio_diagnostico_basso_peso)),not(mostra(X,criterio_diagnostico_paura_aumentare_peso)),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,bmi_regolare)]),1,1).

rule(5,manifesta(X,'bulimia nervosa in forma lieve'),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_1_a_3_volta_sett_per_3mesi)]),1,1).
rule(6,manifesta(X,'bulimia nervosa in forma moderata'),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_4_a_7_volta_sett_per_3mesi)]),1,1).
rule(7,manifesta(X,'bulimia nervosa in forma grave'),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_da_8_a_13_volta_sett_per_3mesi)]),1,1).
rule(8,manifesta(X,'bulimia nervosa in forma gravissima'),and([mostra(X,comportamento_generale_1),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),mostra(X,criterio_diagnostico_comportamento_compenso),mostra(X,criterio_diagnostico_abbuffata_media_piu_di_14_volte_sett_per_3mesi)]),1,1).

rule(9,manifesta(X,'bed disturbo da alimentazione incontrollata in forma lieve'),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_1_a_3_volta_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(10,manifesta(X,'bed disturbo da alimentazione incontrollata in forma moderata'),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_4_a_7_volta_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(11,manifesta(X,'bed disturbo da alimentazione incontrollata in forma grave'),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_8_a_13_volta_sett_per_6mesi_),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).
rule(12,manifesta(X,'bed disturbo da alimentazione incontrollata in forma gravissima'),and([mostra(X,comportamento_generale_2),mostra(X,criterio_diagnostico_abbuffate_incontrollate),mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),not(mostra(X,criterio_diagnostico_comportamento_compenso)),mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_piu_di_14_volte_sett_per_6mesi),mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)]),1,1).

rule(13,manifesta(X,'disturbo evitante restrittivo dell assunzione di cibo'),and([mostra(X,comportamento_generale_3),mostra(X,criterio_diagnostico_assenza_interesse_cibo),mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali),mostra(X,fattore_caratterizzante_incapacita_raggiungere_peso_crescita),mostra(X,criterio_diagnostico_no_condizione_medica_mentale)]),1,1).
rule(14,manifesta(X,'anoressia riversa vigoressia'),and([mostra(X,comportamento_generale_4),mostra(X,fattore_caratterizzante_anomalia_percezione_peso_e_corpo),mostra(X,fattore_caratterizzante_abuso_integratori_diete),mostra(X,fattore_caratterizzante_esercizio_fisico_compulsivo)]),1,1).
rule(15,manifesta(X,'ortoressia'),and([mostra(X,comportamento_generale_5),mostra(X,fattore_caratterizzante_ossessione_cibo_sano),mostra(X,fattore_caratterizzante_focalizzazione_qualita_cibo),mostra(X,fattore_caratterizzante_evitamento_ossessivo_cibi_non_controllati),mostra(X,fattore_caratterizzante_evitamento_situzioni_sociali_cibo_non_controllato),mostra(X,fattore_caratterizzante_convizione_proprie_scelte)]),1,1).
rule(16,manifesta(X,'disturbo da ruminazione'),and([mostra(X,comportamento_generale_6),mostra(X,criterio_diagnostico_ripetuto_rigurgito),mostra(X,criterio_diagnostico_no_condizione_medica_mentale)]),1,1).
rule(17,manifesta(X,'pica'),and([mostra(X,comportamento_generale_6),mostra(X,criterio_diagnostico_ingestione_sostanze_non_alimentari),mostra(X,criterio_diagnostico_no_condizione_medica_mentale),mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali)]),1,1).

rule(18,manifesta(X,'sindrome da alimentazione notturna'),and([mostra(X,comportamento_generale_7),mostra(X,fattore_caratterizzante_alimentazione_notturna),not(mostra(X,criterio_diagnostico_mancanza_controllo_mangiare))]),1,1).
rule(19,manifesta(X,'disturbo purgativo'),and([mostra(X,comportamento_generale_8),not(mostra(X,criterio_diagnostico_abbuffate_incontrollate)),not(mostra(X,criterio_diagnostico_mancanza_controllo_mangiare)),mostra(X,criterio_diagnostico_comportamento_compenso),not(mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_1_a_3_volta_sett_per_6mesi)),not(mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive)),mostra(X,fattore_caratterizzante_comportamento_purgativo)]),1,1).
rule(20,manifesta(X,'anoressia nervosa con restrizioni'),and([mostra(X,comportamento_generale_9),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),not(mostra(X,criterio_diagnostico_abbuffate_incontrollate)),not(mostra(X,criterio_diagnostico_comportamento_compenso)),not(ha(X,'avuto durante gli ultimi 3 mesi episodi di abbuffate o comportamenti di eliminazione'))]),1,1).
rule(21,manifesta(X,'anoressia nervosa con abbuffate o manovre di eliminazione'),and([mostra(X,comportamento_generale_10),mostra(X,criterio_diagnostico_basso_peso),mostra(X,criterio_diagnostico_paura_aumentare_peso),mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),ha(X,'avuto negli ultimi 3 mesi ricorrenti episodi di abbuffate o comportamenti di eliminazione')]),1,1).

%comportamenti
rule(30,mostra(X,comportamento_generale_0),and([mostra(X,bmi_sottopeso),or([mostra(X,sesso_femminile),mostra(X,eta_rischio_12_25_anni)])]),1,1).
rule(31,mostra(X,comportamento_generale_1),and([mostra(X,abbuffate),or([mostra(X,vomito),mostra(X,lassativi)]),or([mostra(X,sesso_femminile),mostra(X,eta_rischio_12_25_anni)])]),1,1).
rule(32,mostra(X,comportamento_generale_2),and([mostra(X,abbuffate),mostra(X,mancanza_controllo_mangiare),not(mostra(X,vomito)),not(mostra(X,lassativi)),or([mostra(X,abbuffate_incontrollate),ha(X,'abbuffate convulsive nelle quali mangia grandi quantita di cibo pur non sentendo fame'),ha(X,'abbuffate convulsive nelle quali mangia in solitudine a causa dellimbarazzo per le quantita di cibo ingerite'),ha(X,'abbuffate convulsive nelle quali prova disgusto di se,depressione o intensa colpa dopo aver mangiato troppo')])]),1,1).
rule(33,mostra(X,comportamento_generale_3),and([mostra(X,bmi_sottopeso),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi)),or([ha(X,'significativa carenza nutrizionale'),ha(X,'dipendenza dalla nutrizione enterale o da supplementi nutrizionali orali'),ha(X,'marcata interferenza col funzionamento psicosociale')])]),1,1).
rule(34,mostra(X,comportamento_generale_4),and([or([mostra(X,bmi_sovrappeso),mostra(X,bmi_regolare)]),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi)),mostra(X,sesso_maschile)]),1,1).
rule(35,mostra(X,comportamento_generale_5),and([or([mostra(X,bmi_regolare),mostra(X,bmi_sottopeso)]),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(36,mostra(X,comportamento_generale_6),and([not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),or([mostra(X,eta_infantile_0_2_anni),mostra(X,eta_3_11_anni)]),not(mostra(X,lassativi))]),1,1).
rule(37,mostra(X,comportamento_generale_7),and([mostra(X,abbuffate),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(38,mostra(X,comportamento_generale_8),and([not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),mostra(X,vomito),mostra(X,lassativi)]),1,1).
rule(39,mostra(X,comportamento_generale_9),and([mostra(X,bmi_sottopeso),not(mostra(X,abbuffate)),not(mostra(X,mancanza_controllo_mangiare)),not(mostra(X,vomito)),not(mostra(X,lassativi))]),1,1).
rule(40,mostra(X,comportamento_generale_10),and([mostra(X,bmi_sottopeso),or([mostra(X,abbuffate),mostra(X,vomito),mostra(X,lassativi)])]),1,1).

rule(41,mostra(X,criterio_diagnostico_basso_peso),and([mostra(X,bmi_sottopeso),accusa(X,'restrizione apporto energetico che induce un significativo basso peso ovvero un peso minore del minimo normale o per i bambini e gli adolescenti minore del minimo atteso')]),1,1).
rule(42,mostra(X,criterio_diagnostico_paura_aumentare_peso),and([accusa(X,'intensa paura di aumentare di peso o dingrassare'),accusa(X,'comportamento persistente che interferisce con laumento di peso nonostante il peso sia significativamente basso')]),1,1).
rule(43,mostra(X,criterio_diagnostico_anomalia_percezione_peso_e_corpo),and([accusa(X,'valutazione di se inappropriata influenzata dalla forma e dal peso del corpo')]),1,1).
rule(44,mostra(X,criterio_diagnostico_abbuffate_incontrollate),and([mostra(X,abbuffate_incontrollate),mostra(X,mancanza_controllo_mangiare)]),1,1).
rule(45,mostra(X,criterio_diagnostico_mancanza_controllo_mangiare),and([or([mostra(X,mancanza_controllo_mangiare),presenta(X,'sensazione di perdita di controllo durante labbuffata')])]),1,1).
rule(46,mostra(X,criterio_diagnostico_comportamento_compenso),and([or([mostra(X,vomito),mostra(X,lassativi)])]),1,1).
rule(49,mostra(X,criterio_diagnostico_disagio_sofferenza_abbuffate_compulsive),and([presenta(X,'abbuffate compulsive che suscitano sofferenza e disagio')]),1,1).
rule(50,mostra(X,criterio_diagnostico_assenza_interesse_cibo),and([presenta(X,'assenza di interesse per lalimentazione o per il cibo'),presenta(X,'evitamento basato sulle caratteristiche sensoriali del cibo')]),1,1).
rule(52,mostra(X,criterio_diagnostico_no_mancanza_cibo_pratiche_culturali),and([or([accusa(X,'disturbo non connesso con la mancanza di cibo o associato a pratiche culturali'),accusa(X,'comportamento di ingestione che non fa parte di una pratica culturalmente sancita')])]),1,1).
rule(55,mostra(X,criterio_diagnostico_no_condizione_medica_mentale),and([or([accusa(X,'anomalia non meglio attribuibile a una condizione medica o ad un altro disturbo mentale'),presenta(X,'comportamento non dovuto ad una condizione gastrointestinale associata o ad un altra condizione medica generale'),accusa(X,'comportamento di ingestione che si manifesta esclusivamente durante il decorso di un altro disturbo mentale o condizione medica sufficientemente grave da giustificare di per se attenzione clinica')])]),1,1).
rule(57,mostra(X,criterio_diagnostico_ripetuto_rigurgito),and([presenta(X,'ripetuto rigurgito di cibo per un periodo di almeno 1 mese. Il cibo puo essere rimasticato, ringoiato o eliminato')]),1,1).
rule(58,mostra(X,criterio_diagnostico_ingestione_sostanze_non_alimentari),and([presenta(X,'persistente ingestione di sostanze non alimentari per un periodo di almeno 1 mese'),presenta(X,'ingestione di sostanze non alimentari inappropriata rispetto al livello di sviluppo')]),1,1).

rule(47,mostra(X,criterio_diagnostico_abbuffata_media_da_1_a_3_volta_sett_per_3mesi),and([frequenza_abbuffate(X,una_tre_volte_a_settimana)]),1,1).
rule(75,mostra(X,criterio_diagnostico_abbuffata_media_da_4_a_7_volta_sett_per_3mesi),and([frequenza_abbuffate(X,quattro_sette_volte_a_settimana)]),1,1).
rule(76,mostra(X,criterio_diagnostico_abbuffata_media_da_8_a_13_volta_sett_per_3mesi),and([frequenza_abbuffate(X,otto_tredici_volte_a_settimana)]),1,1).
rule(77,mostra(X,criterio_diagnostico_abbuffata_media_piu_di_14_volte_sett_per_3mesi),and([frequenza_abbuffate(X,piu_di_quattordici_volte_a_settimana)]),1,1).

rule(48,mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_1_a_3_volta_sett_per_6mesi),and([frequenza_compulsiva(X,una_tre_volte_a_settimana)]),1,1).
rule(78,mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_4_a_7_volta_sett_per_6mesi),and([frequenza_compulsiva(X,quattro_sette_volte_a_settimana)]),1,1).
rule(79,mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_da_8_a_13_volta_sett_per_6mesi),and([frequenza_compulsiva(X,otto_tredici_volte_a_settimana)]),1,1).
rule(80,mostra(X,criterio_diagnostico_abbuffata_compulsiva_media_piu_di_14_volte_sett_per_6mesi),and([frequenza_compulsiva(X,piu_di_quattordici_volte_a_settimana)]),1,1).

rule(51,mostra(X,fattore_caratterizzante_ossessione_cibo_sano),and([presenta(X,'ossessione per il cibo sano')]),1,1).
rule(53,mostra(X,fattore_caratterizzante_abuso_integratori_diete),and([presenta(X,'abuso di integratori anabolizzanti e diete iperproteiche')]),1,1).
rule(54,mostra(X,fattore_caratterizzante_incapacita_raggiungere_peso_crescita),and([presenta(X,'significativa perdita di peso o nei bambini incapacita a raggiungere il peso relativo alla crescita')]),1,1).
rule(56,mostra(X,fattore_caratterizzante_esercizio_fisico_compulsivo),and([presenta(X,'esercizio fisico compulsivo')]),1,1).
rule(59,mostra(X,fattore_caratterizzante_alimentazione_notturna),and([presenta(X,'ricorrenti episodi di alimentazione notturna che si manifestano dopo il risveglio o con un eccessivo consumo di cibo dopo il pasto serale'),presenta(X,'alimentazione notturna non giustificata da influenze esterne quali cambiamenti del ciclo individuale del sonno/veglia o da norme sociali specifiche')]),1,1).
rule(60,mostra(X,fattore_caratterizzante_anomalia_percezione_peso_e_corpo),and([presenta(X,'autopercezione di gracilita eminentemente maschile'),presenta(X,'comportamento alimentare alterato')]),1,1).
rule(61,mostra(X,fattore_caratterizzante_focalizzazione_qualita_cibo),and([presenta(X,'focalizzazione non sulla quantita di cibo ma sulla qualita')]),1,1).
rule(62,mostra(X,fattore_caratterizzante_evitamento_ossessivo_cibi_non_controllati),and([presenta(X,'evitamento ossessivo per i cibi non controllati')]),1,1).
rule(63,mostra(X,fattore_caratterizzante_evitamento_situzioni_sociali_cibo_non_controllato),and([presenta(X,'evitamento delle situazioni sociali che espongono al non controllo del cibo')]),1,1).
rule(64,mostra(X,fattore_caratterizzante_convizione_proprie_scelte),and([presenta(X,'convinzione fideistica delle proprie scelte')]),1,1).
rule(65,mostra(X,fattore_caratterizzante_comportamento_purgativo),and([presenta(X,'comportamento purgativo ricorrente per modificare peso e forma del corpo')]),1,1).

rule(69,mostra(X,abbuffate),and([presenta(X,'episodi di abbuffate')]),1,1).
rule(70,mostra(X,mancanza_controllo_mangiare),and([presenta(X,'mancanza di controllo nell atto di mangiare')]),1,1).
rule(72,mostra(X,vomito),and([presenta(X,'episodi di vomito autoindotto')]),1,1).
rule(73,mostra(X,lassativi),and([presenta(X,'uso di lassativi')]),1,1).
rule(92,mostra(X,vecchia_situazione_cura_dca),and([presenta(X,'una vecchia situazione di cura per dca')]),1,1).
rule(71,mostra(X,pensieri_tentativi_suicidio),and([presenta(X,'pensieri o tentativi di suicidio')]),1,1).

rule(66,mostra(X,bmi_regolare),and([peso_bmi(X,regolare)]),1,1).
rule(67,mostra(X,bmi_sovrappeso),and([peso_bmi(X,sovrappeso)]),1,1).
rule(68,mostra(X,bmi_sottopeso),and([peso_bmi(X,sottopeso)]),1,1).

rule(74,mostra(X,abbuffate_incontrollate),and([mostra(X,abbuffate),ha(X,'abbuffate convulsive nelle quali mangia molto piu rapidamente del normale')]),1,1).

rule(81,mostra(X,gravita_disturbo_lieve),and([indice_bmi(X,maggiore_o_uguale_a_17)]),1,1).
rule(82,mostra(X,gravita_disturbo_moderata),and([indice_bmi(X,compreso_tra_16_e_16_99)]),1,1).
rule(83,mostra(X,gravita_disturbo_grave),and([indice_bmi(X,compreso_tra_15_e_15_99)]),1,1).
rule(84,mostra(X,gravita_disturbo_gravissima),and([indice_bmi(X,minore_di_15)]),1,1).

rule(85,mostra(X,sesso_maschile),and([presenta(X,'sesso maschile')]),1,1).
rule(86,mostra(X,sesso_femminile),and([presenta(X,'sesso femminile')]),1,1).

rule(87,mostra(X,eta_infantile_0_2_anni),and([eta(X,tra_0_2_anni)]),1,1).
rule(88,mostra(X,eta_3_11_anni),and([eta(X,tra_3_11_anni)]),1,1).
rule(89,mostra(X,eta_rischio_12_25_anni),and([eta(X,tra_12_25_anni)]),1,1).
rule(90,mostra(X,eta_superiore_25_anni),and([eta(X,maggiore_25_anni)]),1,1).

rule(91,mostra(X,no_indice_gravita_anoressia),and([not(mostra(X,gravita_disturbo_lieve)),not(mostra(X,gravita_disturbo_moderata)),not(mostra(X,gravita_disturbo_grave)),not(mostra(X,gravita_disturbo_gravissima))]),1,1).

