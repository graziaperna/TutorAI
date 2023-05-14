:- dynamic (fact/3).
:- dynamic (rule/5).

askable(ha(X,Y),'Ha ',Y).
askable(presenta(X,Y),'Presenta ',Y).
askable(accusa(X,Y), 'Accusa ',Y).

%**********************************************************************************************************
% Domande a risposta multipla
%**********************************************************************************************************	
askablemenu(localizzazione(X,Y),'Dove accusa dolore?', [spazio_retromolare, dente_singolo, diffuso_emiarcata], localizzazione, X, Y).
askablemenu(colorazione_carie(X,Y), 'Indichi il colore della carie', [lattescente, bruna, grigia], colorazione_carie, X, Y).
askablemenu(dimensione(X,Y), 'Indichi la grandezza della carie', [piccola, media,grande,estesa], dimensione, X, Y).
askablemenu(colore_gengive(X,Y), 'Indichi la colorazione attuale delle sue gengive', [rosso, rosa, tendenti_al_grigio], colore_gengive, X, Y).
askablemenu(tipo_sanguinamento(X, Y), 'Indichi la tipologia di sanguinamento', [allo_spazzolamento, spontaneo, alla_masticazione], tipo_sanguinamento, X, Y).
askablemenu(frequenza(X,Y), 'Indichi con che frequenza accusa dolore', [intermittente, continuo, peggioramento_graduale], frequenza, X, Y).
askablemenu(comparsa(X,Y), 'Indichi la modalita'' in cui si manifesta il dolore', [stimoli_termici, masticazione, zuccheri, spontanea], comparsa, X, Y).
askablemenu(intensita(X,Y), 'Indichi l intensita'' del dolore avvertito', [sopportabile, intollerabile, acuto], intensita, X, Y).
askablemenu(durata(X,Y), 'Indichi la durata del dolore', [breve,lunga], durata, X, Y).
%**********************************************************************************************************

rule(1,manifesta(X,'Pericoronarite'),and([infiammazione(X,gengive),linfoadenopatia(X,laterocervicale),mostra(X,febbre)]),1,1).
rule(2,manifesta(X,'Gengivite'),and([infiammazione(X,gengive),mostra(X,placca), mostra(X,alitosi)]),1,1).
rule(3,manifesta(X,'Parodontite'),and([infiammazione(X,gengive),linfoadenopatia(X,laterocervicale),mostra(X,febbre)]),1,1).
rule(4,manifesta(X,'Gengivite ulcerativa necrotizzante'),and([infiammazione(X,gengive),linfoadenopatia(X,laterocervicale),mostra(X,febbre), mostra(X, alitosi)]),1,1).
rule(5,manifesta(X,'Paradentosi'),and([infiammazione(X,gengive),recessione(X,gengive)]),1,1).
rule(6,manifesta(X,'Carie di stadio 1'),and([dolore(X,dentale),colorazione_carie(X,lattescente),dimensione(X,piccola)]),1,1).
rule(7,manifesta(X,'Carie di stadio 2'),and([dolore(X,dentale),colorazione_carie(X,bruna),dimensione(X,media)]),1,1).
rule(8,manifesta(X,'Carie di stadio 3'),and([dolore(X,dentale),colorazione_carie(X,bruna),dimensione(X,grande), mostra(X, riflessi_simpatici)]),1,1).
rule(9,manifesta(X,'Carie di stadio 4'),and([dolore(X,dentale),colorazione_carie(X,grigia),dimensione(X,estesa)]),1,1).
rule(10,manifesta(X,'Pulpite'),and([dolore(X,dentale),infiammazione(X,gengive)]),1,1).
rule(11,manifesta(X,'Ascesso'),and([infiammazione(X,gengive),dolore(X,dentale), mostra(X,febbre), linfoadenopatia(X,laterocervicale)]),1,1).
rule(12,manifesta(X,'Epulide'),and([mostra(X, nodulo), infiammazione(X,gengive), recessione(X, gengive), mostra(X, alitosi)]),1,1).
rule(13,manifesta(X,'Alveolite'),and([mostra(X, estrazione), dolore(X, mandibolare), infiammazione(X, gengive), linfoadenopatia(X, laterocervicale), mostra(X, febbre), mostra(X, alitosi)]),1,1).
rule(14,manifesta(X,'Scialoadenite'),and([infiammazione(X, ghiandole_salivari), linfoadenopatia(X, laterocervicale), mostra(X, febbre)]),1,1).
rule(15,manifesta(X,'Scialolitiasi'),and([infiammazione(X, ghiandole_salivari), linfoadenopatia(X, laterocervicale)]),1,1).
rule(16,manifesta(X,'Stomatite aftosa ricorrente'),and([mostra(X,eta_inferiore_40anni), dolore(X,mucosa), mostra(X, afte)]),1,1).
rule(17,manifesta(X,'Xerostomia'),and([mostra(X,eta_superiore_65anni), dolore(X,mucosa), mostra(X, afte), mostra(X, salivazione_schiumosa), mostra(X, disgeusia), mostra(X, disfagia)]),1,1).
rule(18,manifesta(X,'Glossodinia'),and([mostra(X,eta_superiore_65anni), dolore(X,mucosa), mostra(X, disgeusia), mostra(X, disfagia)]),1,1).
rule(19,manifesta(X,'Bruxismo'),and([dolore(X, mandibolare), mostra(X, digrigna), mostra(X, emicrania), mostra(X, otalgia_riferita), mostra(X, trisma), mostra(X, disfagia)]),1,1).


%**********************************************************************************************************
% Regole intermedie
%**********************************************************************************************************	
rule(20, infiammazione(X,gengive), and([dolore(X, gengive), mostra(X, gonfiore), colore_gengive(X,rosso)]),1,1).
rule(21, infiammazione(X,gengive), and([dolore(X, gengive), mostra(X, gonfiore), colore_gengive(X,rosso), mostra(X, sanguinamento), or([tipo_sanguinamento(X,allo_spazzolamento), tipo_sanguinamento(X,spontaneo)])]),1,1).
rule(22, infiammazione(X,gengive), and([dolore(X, gengive), mostra(X, sanguinamento), or([tipo_sanguinamento(X,alla_masticazione), and([tipo_sanguinamento(X,spontaneo), mostra(X, erosione_gengivale)])])]),1,1).
rule(23, infiammazione(X,gengive), and([mostra(X, gonfiore), colore_gengive(X,rosso), or([dolore(X, dentale), mostra(X, pus)])]),1,1).
rule(24, infiammazione(X,gengive), and([mostra(X, gonfiore), colore_gengive(X,rosso)]), 1,1).

rule(25, infiammazione(X,ghiandole_salivari), and([mostra(X,edema), mostra(X,eritema), or([mostra(X, pus_ghiandole), mostra(X, calcoli)]), dolore(X, mandibolare)]), 1,1).

rule(26, recessione(X, gengive), and([mostra(X, mobilita_dentale),mostra(X, spazio_intradentale), or([mostra(X, denti_lunghi), and([mostra(X, denti_lunghi), mostra(X, caduta_spontanea)])])]), 1,1).
rule(27, recessione(X, gengive), and([mostra(X, mobilita_dentale),mostra(X, spazio_intradentale)]), 1,1).

rule(28, linfoadenopatia(X, laterocervicale), and([mostra(X, ingrossamento_linfonodi), mostra(X, disfagia), mostra(X, tumefazione_facciale), or([mostra(X, sciallorea), mostra(X, mal_di_gola)])]), 1, 1).
rule(29, linfoadenopatia(X, laterocervicale), and([mostra(X, ingrossamento_linfonodi), mostra(X, disfagia), or([mostra(X, sciallorea), mostra(X, mal_di_gola)])]), 1, 1).
rule(30, linfoadenopatia(X, laterocervicale), and([mostra(X, ingrossamento_linfonodi), mostra(X, emicrania), or([mostra(X, otalgia_riferita), and([mostra(X, trisma),mostra(X, disfagia)])])]), 1,1).
rule(31, linfoadenopatia(X, laterocervicale), and([mostra(X, ingrossamento_linfonodi), mostra(X, emicrania), mostra(X, otalgia_riferita), mostra(X, trisma), mostra(X, mal_di_gola), mostra(X, tumefazione_facciale)]), 1, 1).

rule(32, dolore(X, gengive), and([mostra(X, sensibilita), comparsa(X,stimoli_termici), or([intensita(X,sopportabile), intensita(X,intollerabile)])]), 1, 1).
rule(33, dolore(X, gengive), and([localizzazione(X, spazio_retromolare), frequenza(X,intermittente)]), 1, 1).
rule(34, dolore(X, gengive), and([mostra(X, sensibilita), comparsa(X,masticazione), or([intensita(X,sopportabile), intensita(X,acuto)])]), 1, 1).

rule(35, dolore(X, dentale), and([intensita(X,acuto), mostra(X, sensibilita), comparsa(X,masticazione), frequenza(X,continuo), localizzazione(X, dente_singolo)]), 1, 1).
rule(36, dolore(X, dentale), and([intensita(X,acuto), mostra(X, sensibilita), comparsa(X,masticazione), localizzazione(X, dente_singolo)]), 1, 1).
rule(37, dolore(X, dentale), and([localizzazione(X, dente_singolo), frequenza(X,continuo), durata(X,breve), mostra(X, sensibilita), or([and([intensita(X,sopportabile), comparsa(X,stimoli_termici)]), and([intensita(X,acuto), comparsa(X,zuccheri)])])]), 1, 1).
rule(38, dolore(X, dentale), and([intensita(X,acuto), mostra(X, sensibilita), comparsa(X,spontanea), durata(X,breve), localizzazione(X, diffuso_emiarcata), mostra(X, riflessi_simpatici)]), 1, 1).
rule(39, dolore(X, dentale), and([intensita(X,intollerabile), mostra(X, sensibilita), comparsa(X,spontanea), durata(X,lunga), localizzazione(X, diffuso_emiarcata)]), 1, 1).
rule(40, dolore(X, dentale), and([intensita(X,sopportabile), mostra(X, sensibilita), comparsa(X,stimoli_termici), durata(X,lunga), localizzazione(X, diffuso_emiarcata), frequenza(X,continuo)]), 1, 1).

rule(41, dolore(X, mandibolare), and([intensita(X,acuto), mostra(X, sensibilita), comparsa(X,spontanea), frequenza(X,continuo)]), 1, 1).
rule(42, dolore(X, mandibolare), and([intensita(X,acuto), mostra(X, sensibilita), comparsa(X,masticazione), durata(X,breve)]), 1, 1).

rule(43, dolore(X, mucosa), and([mostra(X, bruciore), intensita(X,acuto)]), 1, 1).
rule(44, dolore(X, mucosa), and([mostra(X, bruciore), mostra(X, formicolio), mostra(X, secchezza), mostra(X, sensibilita), comparsa(X,spontanea), frequenza(X,peggioramento_graduale)]), 1, 1).
rule(45, dolore(X, mucosa), and([mostra(X, bruciore), mostra(X, formicolio), mostra(X, sensibilita), comparsa(X,spontanea), or([frequenza(X,peggioramento_graduale), frequenza(X,continuo), frequenza(X,intermittente)])]), 1, 1).


rule(50, mostra(X, febbre), and([presenta(X, 'febbre')]), 1, 1).
rule(51, mostra(X, febbre), and([accusa(X, 'dolori muscolari'), mostra(X,emicrania), accusa(X,'debolezza diffusa nel corpo')]), 1, 1).
rule(52, mostra(X, febbre), and([presenta(X, 'vivo colorito rosso in viso'), accusa(X, 'brividi')]), 1, 1).
rule(53, mostra(X, febbre), and([presenta(X, 'sudorazione eccessiva'), presenta(X, 'disidratazione')]), 1, 1).
rule(54, mostra(X, riflessi_simpatici), and([mostra(X, congestione_facciale), mostra(X, congestione_oculare), mostra(X, lacrimazione)]), 1, 1).
rule(55, mostra(X, riflessi_simpatici), and([mostra(X, congestione_oculare), mostra(X, lacrimazione)]), 1, 1).
rule(56, mostra(X, placca), and([presenta(X, 'placca batterica')]), 1, 1).
rule(57, mostra(X, alitosi), and([presenta(X, 'un alito poco fresco')]), 1, 1).
rule(58, mostra(X, congestione_facciale), and([accusa(X, 'congestione del viso')]), 1, 1).
rule(59, mostra(X, congestione_oculare), and([presenta(X, 'occhi arrossati e gonfi')]), 1, 1).
rule(60, mostra(X, lacrimazione), and([presenta(X, 'lacrimazione spontanea')]), 1, 1).
rule(61, mostra(X, nodulo), and([presenta(X, 'una cisti o una formazione di tipo cistico che interessa la gengiva o un''altra zona del cavo orale')]), 1, 1).
rule(62, mostra(X, estrazione), and([ha(X, 'subito estrazioni dentali nell''ultimo periodo')]), 1, 1).
rule(63, mostra(X, afte), and([presenta(X, 'afte o ulcere orali')]), 1, 1).
rule(64, mostra(X, salivazione_schiumosa), and([presenta(X, 'un anomalo ed eccessivo accumulo di saliva')]), 1, 1).
rule(65, mostra(X, disgeusia), and([accusa(X, 'una distorsione o abbassamento del senso del gusto')]), 1, 1).
rule(66, mostra(X, disfagia), and([accusa(X, 'una sensazione di deglutizione difficoltosa')]), 1, 1).
rule(67, mostra(X, gonfiore), and([presenta(X, 'gengive gonfie ed edematose')]), 1, 1).
rule(68, mostra(X, erosione_gengivale), and([presenta(X, 'punte delle gengive erose e con una membrana bianca-grigiastra')]), 1, 1).
rule(69, mostra(X, pus), and([presenta(X, 'visibile pus all''interno della gengiva')]), 1, 1).
rule(70, mostra(X, edema), and([presenta(X, 'edema')]), 1, 1).
rule(71, mostra(X, eritema), and([presenta(X, 'eritema')]), 1, 1).
rule(72, mostra(X, pus_ghiandole), and([presenta(X, 'visibile pus sulle ghiandole salivari')]), 1, 1).
rule(73, mostra(X, calcoli), and([ha(X, 'calcoli salivari all''interno dei dotti escretori')]), 1, 1).
rule(74, mostra(X, mobilita_dentale), and([accusa(X, 'un aumento della mobilita'' dei denti')]), 1, 1).
rule(75, mostra(X, spazio_intradentale), and([presenta(X, 'spazio tra un dente e l''altro')]), 1, 1).
rule(76, mostra(X, denti_lunghi), and([presenta(X, 'denti che appaiono lunghi')]), 1, 1).
rule(77, mostra(X, caduta_spontanea), and([ha(X, 'denti che tendono a cadere spontaneamente')]), 1, 1).
rule(78, mostra(X, ingrossamento_linfonodi), and([presenta(X, 'linfonodi sottomandibolari ingrossati e doloranti')]), 1, 1).
rule(79, mostra(X, tumefazione_facciale), and([presenta(X, 'viso gonfio nella zona interessata dal dolore')]), 1, 1).
rule(80, mostra(X, mal_di_gola), and([ha(X, 'mal di gola')]), 1, 1).
rule(81, mostra(X, emicrania), and([presenta(X, 'mal di testa')]), 1, 1).
rule(82, mostra(X, trisma), and([presenta(X, 'difficolta'' ad aprire la bocca')]), 1, 1).
rule(83, mostra(X, otalgia_riferita), and([accusa(X, 'otalgia riferita')]), 1, 1).
rule(84, mostra(X, bruciore), and([accusa(X, 'un senso di bruciore')]), 1, 1).
rule(85, mostra(X, formicolio), and([accusa(X, 'un senso di formicolio')]), 1, 1).
rule(86, mostra(X, secchezza), and([presenta(X, 'secchezza delle fauci')]), 1, 1).
rule(87, mostra(X, rumori_articolari), and([presenta(X, 'rumori articolari aprendo la bocca')]), 1, 1).
rule(88, mostra(X, sensibilita), and([presenta(X, 'sensibilita'' dentale')]), 1, 1).
rule(89, mostra(X, sanguinamento), and([presenta(X, 'facile sanguinamento alle gengive')]), 1, 1).
rule(90, mostra(X, digrigna), and([presenta(X, 'digrigna i denti durante la notte o il giorno')]), 1, 1).
rule(91,mostra(X,sesso_maschile),and([presenta(X,'sesso maschile')]),1,1).
rule(92,mostra(X,sesso_femminile),and([presenta(X,'sesso femminile')]),1,1).
rule(93,mostra(X,eta_inferiore_40anni),and([presenta(X,'eta'' inferiore a 40 anni')]),1,1).
rule(94,mostra(X,eta_compresa_40_65anni),and([presenta(X,'eta'' compresa tra 40 e 65 anni')]),1,1).
rule(95,mostra(X,eta_superiore_65anni),and([presenta(X,'eta'' superiore ai 65 anni')]),1,1).
rule(96, mostra(X, buona_salute), and([presenta(X, 'uno stato di buona salute')]), 1, 1).
rule(97, mostra(X, uso_stupefacenti), and([presenta(X, 'abitudine a sostanze stupefacenti')]), 1, 1).
rule(98, mostra(X, sostanze_alcoliche), and([presenta(X, 'abitudine a sostanze alcoliche')]), 1, 1).
rule(99, mostra(X, fumatore), and([presenta(X, 'abitudine al fumo')]), 1, 1).
rule(100, mostra(X, gravidanza), and([presenta(X, 'stato di gravidanza')]), 1, 1).
rule(101, mostra(X, farmaci), and([presenta(X, 'assunzione di farmaci')]), 1, 1).
rule(102, mostra(X, interventi), and([presenta(X, 'interventi')]), 1, 1).
rule(103, mostra(X, malattie_sistemiche), and([presenta(X, 'malattie sistemiche')]), 1, 1).
rule(104, mostra(X, problemi_cardiaci), and([presenta(X, 'problemi cardiaci')]), 1, 1).
rule(105, mostra(X, ipertensione), and([presenta(X, 'ipertensione')]), 1, 1).
rule(106, mostra(X, diabete), and([presenta(X, 'diabete')]), 1, 1).
rule(106, mostra(X, problemi_coagulazione), and([presenta(X, 'problemi di coagulazione sanguigna')]), 1, 1).
rule(107, mostra(X, numero_sigarette), and([presenta(X, 'numero sigarette fumate al giorno')]), 1, 1).
rule(108, mostra(X, farmaci_assunti), and([presenta(X, 'tipi di farmaci assunti')]), 1, 1).



