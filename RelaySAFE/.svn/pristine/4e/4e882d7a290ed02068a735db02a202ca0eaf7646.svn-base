%Base di conoscenza creata da Giuliana Spinelli
%askable
askable(ha(Moneta, Caratteristica), ha(Moneta, Caratteristica)).
askable(fatta(Moneta, Caratteristica), fatta(Moneta, Caratteristica)).
askable(presenta(Moneta, Caratteristica), presenta(Moneta, Caratteristica)).
askable(non(Moneta, Caratteristica), non(Moneta, Caratteristica)).
askable(si(Moneta, Caratteristica), si(Moneta, Caratteristica)).
askable(coniata(Moneta, Caratteristica), coniata(Moneta, Caratteristica)).

%regole di alto livello per le monete costituite da metalli preziosi

%regole di alto livello per le monete di oro 
rule(1, identificata(Moneta,'100 mila lire Banca d"Italia'), 
and([coniata(Moneta,oroCommemerativaPersonaggio), 
presenta(Moneta,'sul dritto un volto girato di fronte'),
presenta(Moneta,'sul rovescio la scritta L 100 Mila sul rovescio'),
presenta(Moneta,'sul rovescio la banca d" Italia')]),1,1).

rule(2, identificata(Moneta,'100 mila lire Santa Croc'), 
and([coniata(Moneta,oroCommemorativaCitta),
presenta(Moneta,'sul rovescio la scritta VII centenario della basilica di Santa Croce in Firenze come legenda'),
presenta(Moneta,'sul rovescio archi architettonici'),
presenta(Moneta,'sul rovescio la scritta 100 Mila lire')]),1,1).

rule(3, identifita(Moneta,'100 mila lire Certosa di Pavia'), 
and([coniata(Moneta,oroCommemorativaCitta),
presenta(Moneta,'sul rovescio la scritta VI centenario della Certosa di Pavia come legenda'),
presenta(Moneta,'sul rovescio archi architettonici'),
presenta(Moneta,'sul rovescio la scritta 100 Mila lire')]),1,1).

rule(4, identificata(Moneta,'100 mila lire Basilica San Nicola'), 
and([coniata(Moneta,oroCommemorativaCitta),
presenta(Moneta,'sul rovescio la scritta dedicazione basilica S Nicola di Bari 1197 1997 come legenda'),
presenta(Moneta,'sul rovescio archi architettonici'),
presenta(Moneta,'sul rovescio la scritta 100 Mila lire')]),1,1).

rule(5, identificata(Moneta,'100 mila lire Torre del Mangia'), 
and([coniata(Moneta,oroCommemorativaCitta),
presenta(Moneta,'sul rovescio la scritta completamento della torre del Mangia del palazzo pubblico di Siena del anniversario come legenda'),
presenta(Moneta,'sul rovescio tre uomini'),
presenta(Moneta,'sul rovescio la scritta 100 Mila lire')]),1,1).

rule(6, identificata(Moneta,'100 mila lire Basilica di Assisi'), 
and([coniata(Moneta,oroCommemorativaCitta), 
presenta(Moneta,'sul rovescio l"anno di coniatura sul rovescio'),
presenta(Moneta,'sul rovescio un rosone'),
presenta(Moneta,'sul rovescio la scritta 100 Mila lire')]),1,1).

rule(7, identificata(Moneta,'100 mila lire Primo Giubileo'), 
and([coniata(Moneta,nobileCommemorativeRepubblica), 
fatta(Moneta,'di oro'),
presenta(Moneta,'sul dritto una iconografia'),
presenta(Moneta,'sul dritto la legenda Repubblica Italiana'),
presenta(Moneta,'contorno rigato'),
presenta(Moneta,'sul dritto scritta Primo-Giubileo Bonifacio -VII-Pont-Max')]),1,1).

%regole di alto livello per le monete di argento

rule(8, identificata(Moneta,'1000 lire di Roma Capitale'), 
and([coniata(Moneta,argentoCommemorativaRepubblica), 
presenta(Moneta,'sul rovescio il progetto di Michelangelo per la pavimentazione antistante il Campidoglio')]),1,1).

rule(9, identificata(Moneta,'500 lire Caravelle'), 
and([presenta(Moneta,'sul rovescio la legenda Repubblica Italiana'),
presenta(Moneta,'sul rovescio caravelle con le bandiere al vento rivolte verso destra'), 
or([coniata(Moneta, argentoCommemorativaRepubblica), 
coniata(Moneta, pregiataNuovaMonetazione)])]),1,1).

rule(10, identificata(Moneta,'500 lire di Dante Alighieri'), 
and([coniata(Moneta,argentoCommemorativaPersonaggio), 
presenta(Moneta,'sul dritto la legenda Repubblica Italiana'),
presenta(Moneta,'sul rovescio la rappresentazione dell"Inferno'), 
no(coniata(Moneta,monetaDucato)), 
coniata(Moneta,monetaMonarchica),
coniata(Moneta,fiorino)]),1,1).

rule(11, identificata(Moneta,'500 lire dell"unita d"Italia'), 
and([coniata(Moneta,argentoCommemorativaCentenario), 
presenta(Moneta,'sul dritto la legenda Repubblica Italiana'),
presenta(Moneta,'sul rovescio la triade di cavalli')]),1,1).

%regole di alto livello per le monete costituite da metalli comuni

%regole di alto livello per le monete di acmonital

rule(12, identificata(Moneta,'100 Lire Minerva 1° tipo'), 
and([ha(Moneta,caratteristicheAcmonital),
presenta(Moneta,'sul dritto il capo di Minerva'),
presenta(Moneta,'sul rovescio Minerva in piedi vicino un ulivo')]),1,1).

rule(13, identificata(Moneta,'100 Lire Minerva 2° tipo'), 
and([ha(Moneta,acmonitalNuovaMonetazione),
presenta(Moneta,'sul dritto il capo di Minerva'),
presenta(Moneta,'sul rovescio Minerva in piedi vicino un ulivo')]),1,1).

rule(14, identificata(Moneta,'50 Lire Vulcano 1° tipo'), 
and([ha(Moneta,caratteristicheAcmonital),
presenta(Moneta,'sul dritto il capo di Vulcano'),
presenta(Moneta,'sul rovescio Vulcano in piedi che batte il ferro')]),1,1).

rule(15, identificata(Moneta,'50 Lire Vulcano 2° tipo'), 
and([ha(Moneta,acmonitalNuovaMonetazione),
presenta(Moneta,'sul dritto il capo di Vulcano'),
presenta(Moneta,'sul rovescio Vulcano in piedi che batte il ferro')]),1,1).

rule(16, identificata(Moneta,'100 lire Guglielmo Marconi'), 
and([coniata(Moneta,acmonitalCommemorativa), 
presenta(Moneta,'sul dritto volto di Guglielmo Marconi,sotto le indicazione dell"autore (MONASSI)'),
presenta(Moneta,'sul rovescio l"antenna della radiotrasmittente inventata da Marconi,a destra gli anni 1874 1974 su due righe,a sinistra l"indicazione del valore (L 100) e il segno della zecca (R) sempre su due righe,sotto la scritta GUGLIELMO MARCONI')]),1,1).

rule(17, identificata(Moneta,'100 lire FAO 1 tipo '), 
and([coniata(Moneta,acmonitalCommemorativa),
presenta(Moneta,'sul dritto la testa di una giovane rivolta a sinistra con una treccia,lungo il bordo REPUBBLICA ITALIANA,in basso una rosetta e sotto l"indicazione dell"autore (GIANDOMENICO)'),
presenta(Moneta,'sul rovescio decentrati a sinistra, una mucca che allatta un vitello,a destra l"indicazione del valore (100 LIRE) su due righe,sotto indicazione del millesimo (1979),lungo il bordo, in alto, NUTRIRE IL MONDO, in basso, FAO, a ore 9 il segno della zecca (R),sullo sfondo raffigurazione di meridiani e paralleli a simboleggiare il mondo')]),1,1).

rule(18, identificata(Moneta,'100 lire Accademia Navale di Livorno'), 
and([ha(Moneta,acmonitalMonetaAccademica),
presenta(Moneta,'sul dritto un timone e un" ancora sormontati da una corona turrita,intorno REPUBBLICA ITALIANA e due rombi,in basso l"indicazione dell"autore (Moneta VALLUCCI)'),
presenta(Moneta,'sul rovescio la bandiera della Marina Militare Italiana sovrapposta al palazzo dell"Accademia navale di Livorno,sopra il palazzo e le date 1881 e 1981,in basso, indicazione del valore (L 100),a destra il segno della zecca (R) e l"indicazione dell"autore (MOPPI),intorno CENTENARIO ACCADEMIA NAVALE DI LIVORNO')]),1,1).

%regole di alto livello per le monete di italma

rule(19, identificata(Moneta,'10 Lire Ulivo'), 
and([coniata(Moneta,italmaPatriottismoLiberta), 
presenta(Moneta,'sul dritto un cavallo alato'),
presenta(Moneta,'sul rovescio la scritta L 10'),
presenta(Moneta,'in incuso')]),1,1).
 
rule(20, identificata(Moneta,'2 Lire Spiga'), 
and([ha(Moneta, monetaRappresentanteRinascita),
presenta(Moneta, 'sul dritto un uomo con un aratro'),
presenta(Moneta, 'sul rovescio la scritta L 2')]),1,1).

rule(21, identificata(Moneta,'10 Lire Spighe'), 
and([coniata(Moneta, monetaRappresentanteRinascita), 
presenta(Moneta, 'sul dritto un aratro'), 
presenta(Moneta, 'sul rovescio la scritta 10')]),1,1).

rule(22, identificata(Moneta,'5 Lire Uva'), 
and([coniata(Moneta, italmaCelebrativaRepubblica), 
presenta(Moneta,'sul rovescio un grappolo di uva'), 
presenta(Moneta, 'sul rovescio la scritta L 5')]), 1,1).

rule(23, identificata(Moneta,'5 Lire Delfino'), 
and([presenta(Moneta, 'sul dritto timone di nave'), 
presenta(Moneta, 'sul rovescio un delfino'), 
presenta(Moneta, 'sul rovescio la scritta 5'),
coniata(Moneta, caratteristicheItalma)]), 1,1).

rule(24, identificata(Moneta,'2 Lire Olivo'), 
and([presenta(Moneta, 'un ramo di ulivo e la scritta 2 sul rovescio'), 
presenta(Moneta, 'un" ape sul dritto'), 
presenta(Moneta, 'contorno rigato')]), 1,1).

rule(25, identificata(Moneta,'1 Lira Arancia'), 
and([presenta(Moneta, 'sul dritto una testa di donna'),
presenta(Moneta, 'sul rovescio una arancia col ramo '),
presenta(Moneta, 'sul rovescio la scritta L 1 '),
coniata(Moneta, italmaAgricoltura)]), 1,1).

rule(26, identificata(Moneta,'1 Lira Cornucopia'), 
and([presenta(Moneta,'sul dritto una bilancia a due piatti'),
presenta(Moneta,'sul rovescio una cornucopia'),
coniata(Moneta, italmaVecchiaMonetazione)]), 1,1).

%regole ad alto livello monete in Bronzital
rule(27, identificata(Moneta,'200 Lire Lavoro'), 
and([presenta(Moneta,moneteForzeDellOrdine),
presenta(Moneta,'sul rovescio la ruota dentata come simbolo del lavoro'),
presenta(Moneta,'sul rovescio la scritta 200 Lire'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(28, identificata(Moneta,'200 lire FAO Montessori'), 
and([coniata(Moneta,monetaOrganizzazioneUmanitaria),
presenta(Moneta,'sul dritto il busto di Maria Montessori, sotto l"indicazione dell"autore (GIANDOMENICO),in alto, lungo il bordo,la scritta REPUBBLICA ITALIANA e a seguire l"emblema dell"anno della donna'),
presenta(Moneta,'sul rovescio l"immagine di una donna con un bambino,un libro e una vanga,lungo il bordo in alto la scritta VALORIZZAZIONE DELLA DONNA e in basso FAO,a destra l"indicazione del valore (LIRE 200) su due righe, sotto il segno di zecca (R),a sinistra il millesimo (1980)'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(29, identificata(Moneta,'200 lire FAO Villa Lubin'), 
and([coniata(Moneta, bronzitalCelebrativa), 
presenta(Moneta, 'sul dritto la Villa Lubin, sede dell"Istituto Nazionale dell"Agricoltura, intorno la scritta REPUBBLICA ITALIANA e una stella'), 
presenta(Moneta, 'contorno rigato'),
presenta(Moneta, 'sul rovescio una figura femminile con una cornucopia e alcuni simboli agricoli,in alto l"indicazione del valore L 200,a sinistra l"anno 1981 e lungo il bordo GIORNATA MONDIALE DELL"ALIMENTAZIONE, 16 OTT, FAO')]), 1,1).

rule(30, identificata(Moneta,'200 Lire Arsenale di Taranto'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio una nave con le date 1989-1989'),
presenta(Moneta,'sul rovescio la scritta L 200'),
presenta(Moneta,'sul rovescio la legenda CENTENARIO DELL"ARSENALE MILITARE MARITTIMO DI TARANTO'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(31, identificata(Moneta,'200 Lire Consiglio di Stato'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio il Palazzo Spada'),
presenta(Moneta,'sul rovescio la scritta 200 Lire'),
presenta(Moneta,'sul rovescio la scritta CONSIGLIO DI STATO IV SEZIONE 1890-1990'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(32, identificata(Moneta,'200 lire Filatelia Tematica'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio vele al vento'),
presenta(Moneta,'sul rovescio in basso la scritta GENOVA 92'),
presenta(Moneta,'sul rovescio a sinistra data 1992 e segno di zecca R'),
presenta(Moneta,'sul rovescio la legenda ESPOSIZIONE DI FILATELIA TEMATICA'),
presenta(Moneta,'sul rovescio sul rovescio la scritta L 200'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(33, identificata(Moneta,'200 lire Areonautica Militare'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio stemma diviso in quattro parti con in alto due leoni di cui il leone di sinistra porta una fiaccola e in basso a destra un altro leone che è il leone di San Marco e a sinistra il quadrifoglio con la data 1993'),
presenta(Moneta,'sul rovescio la scritta 70° AERONAUTICA MILITARE 1923 L 200'),
presenta(Moneta,'sul rovescio sulla legenda l"aquila sormontata da una corona'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(34, identificata(Moneta,'200 lire Carabinieri 1° tipo'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio lo Stemma dell"Arma,l" indicazione 180° (CLXXX) e il motto NEI SECOLI FEDELE'),
presenta(Moneta,'sul rovescio sulla legenda lungo il bordo, una stella,l"anno 1814,una fiamma,la scritta ARMA DEI CARABINIERI, un"altra stella'),
presenta(Moneta,'sul rovescio la scritta L 200'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(35, identificata(Moneta,'200 lire Carabinieri 2° tipo'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio sulla legenda la scritta CARABINIERI TUTELA PATRIMONIO ARTISTICO'),
presenta(Moneta,'sul rovescio gli anni 1969-1999 in alto'),
presenta(Moneta,'sul rovescio la scritta L 200'),
presenta(Moneta,'sul rovescio lo Stemma dell"Arma'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(36, identificata(Moneta,'200 lire Accademia GF'), 
and([coniata(Moneta, bronzitalCelebrativa), 
presenta(Moneta,'sul dritto un nastro centrale recante la scritta in incuso REPUBBLICA ITALIANA, nella parte superiore, la Reggia di Caserta (prima sede dell"Accademia nel 1896), nella parte inferiore la sede dell"Accademia della GdF a Bergamo,in basso L"indicazione dell"autore (DRIUTTI)'),
presenta(Moneta,'sul rovescio lo stemma GdF con cartiglio recante il motto dannunziano NEC RECISA RECEDIT, a destra il millesimo 1896 e a sinistra il segno di zecca (R), nella parte inferiore un berretto e uno spadino da Cadetto e il millesimo 1996,in centro cartiglio indicante il nominale (L 200), lungo il bordo la scritta CENTENARIO DELL"ACCADEMIA DELLA GUARDIA DI FINANZA e una stella'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(37, identificata(Moneta, '200 lire Lega Navale Italiana'), 
and([coniata(Moneta, moneteForzeDellOrdine), 
presenta(Moneta,'sul rovescio il distintivo della Lega Navale Italiana sovrapposto ad un veliero'),
presenta(Moneta,'sul rovescio ai lati i millesimi 1897 e 1997'),
presenta(Moneta,'sul rovescio lungo il bordo indicazione dell"autore (E L FRAPPICINI)'),
presenta(Moneta,'sul rovescio ai lati dello stemma l"indicazione del valore (LIRE 200)'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(38, identificata(Moneta, '20 lire Quercia'), 
and([ha(Moneta, caratteristicheBronzital), 
presenta(Moneta,'sul dritto una testa di donna ornata da spighe, intorno REPUBBLICA ITALIANA,sotto il nome dell"autore (GIAMPAOLI)'),
presenta(Moneta,'sul rovescio un ramo di quercia con 4 foglie e una ghianda,a sinistra il valore (L 20) e il segno della zecca(R) su due righe,a destra il millesimo')]), 1,1).

%regole ad alto livello monete in cupronichel
rule(39, identificata(Moneta, '100 lire Italia Turrina 1° tipo'), 
and([coniata(Moneta, cupronichelAgricoltura), 
presenta(Moneta,'sul dritto la testa di Minerva con un velo da cui fuoriescono ciocche di capelli intrecciate a modo di DNA simbolo dell"origine e della storia degli italiani,in basso due stelle a cinque punte e l"indicazione dell"autrice (L CRETARA),lungo il bordo REPVBBLICA ITALIANA'),
presenta(Moneta,'la testa è di piccole dimensioni sul dritto'),
presenta(Moneta,'la legenda non vicina al bordo'),
presenta(Moneta,'sul rovescio all"interno di un cerchio, l"indicazione del valore (100), sotto la scritta LIRE e il millesimo su due righe in caratteri di piccole dimensioni,intorno, lungo il bordo,un gabbiano (in alto) e un delfino (in basso), simboli della liberta del cielo e dell"acqua,un ramo d"olivo (a destra) e una spiga di grano (a sinistra), simboli di pace e di liberta'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(40, identificata(Moneta, '100 lire Italia Turrina 2° tipo'), 
and([coniata(Moneta, cupronichelAgricoltura), 
presenta(Moneta,'la testa di Minerva con un velo da cui fuoriescono ciocche di capelli intrecciate a modo di DNA simbolo dell"origine e della storia degli italiani,in basso due stelle a cinque punte e l"indicazione dell"autrice (L CRETARA),lungo il bordo REPVBBLICA ITALIANA'),
presenta(Moneta,'la testa è di grandi dimensioni sul dritto'),
presenta(Moneta,'la legenda vicina al bordo'),
presenta(Moneta,'sul rovescio all"interno di un cerchio, l"indicazione del valore (100), sotto la scritta LIRE e il millesimo su due righe in caratteri di piccole dimensioni,intorno, lungo il bordo,un gabbiano (in alto) e un delfino (in basso), simboli della liberta del cielo e dell"acqua,un ramo d"olivo (a destra) e una spiga di grano (a sinistra), simboli di pace e di liberta'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(41, identificata(Moneta, '100 lire FAO 2° tipo'), 
and([coniata(Moneta, cupronichelAgricoltura), 
presenta(Moneta,'la testa di Minerva con un velo da cui fuoriescono ciocche di capelli intrecciate a modo di DNA simbolo dell"origine e della storia degli italiani,in basso due stelle a cinque punte e l"indicazione dell"autrice (L CRETARA),lungo il bordo REPVBBLICA ITALIANA'),
presenta(Moneta,'la legenda vicina al bordo sul dritto'),
presenta(Moneta,'sul rovescio l"indicazione del valore (100) intrecciato con un ramo di olivo e una spiga, sotto la scritta LIRE,sullo sfondo meridiani e paralleli che richiamano il globo terrestre, il cui polo è il simbolo del 50° anniversario della FAO. con le date 1945-1995'),
presenta(Moneta,'la testa è di grandi dimensioni sul dritto'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(42, identificata(Moneta, '200 lire Lega Navale Italiana'), 
and([coniata(Moneta, cupronichelCelebrativaRepubblica), 
presenta(Moneta,'la testa di Minerva con un velo da cui fuoriescono ciocche di capelli intrecciate a modo di DNA simbolo dell"origine e della storia degli italiani,in basso due stelle a cinque punte e l"indicazione dell"autrice (L CRETARA),lungo il bordo REPVBBLICA ITALIANA sul dritto'),
presenta(Moneta,'la legenda vicina al bordo sul dritto'),
presenta(Moneta,'sul rovescio l"indicazione del valore (50),sopra il millesimo e sotto LIRE entrambi con caratteri di piccole dimensioni,intorno, lungo il bordo, dei simboli precedentemente utilizzati su altre monete della Repubblica Italiana, quali la ruota dentata (200 lire), la cornucopia (1 lira cornucopia),il ramo di quercia (20 lire quercia),il ramo di alloro (100 lire Minerva) e il grappolo di uva (5 lire uva)'),
presenta(Moneta,'la testa è di grandi dimensioni sul dritto'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

%regole di alto livello per le monete bimetallo

rule(43, identificata(Moneta, '1000 Lire Italia Turrita del 1° tipo'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio la cartina dell"Europa errata(i confini dell"Olanda e della Danimarca sono sbagliati e manca la Germania Democratica)'),
fatta(Moneta,'di cupronichel e bronzital'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(44, identificata(Moneta, '1000 Lire Italia Turrita del 2° tipo'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio la cartina dell"Europa con la Germania riunificata e i confini corretti dell"Olanda e del Lussemburgo)'),
fatta(Moneta,'di cupronichel e bronzital'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(45, identificata(Moneta, '500 Lire'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio raffigurata la piazza del Quirinale, con il palazzo e le statue dei Dioscuri'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(46, identificata(Moneta, '500 Lire Banca d"Italia'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio sul rovescio raffigurato il logo della Banca d"Italia'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(47, identificata(Moneta, '500 Lire Luca Pacioli'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio raffigurato il capo di Luca Pacioli e la legenda con il suo nome'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(48, identificata(Moneta, '500 Lire ISTAT'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio raffigurato il palazzo Istat e la laenda Istituto nazionale di statistica'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(49, identificata(Moneta, '500 Lire Polizia stradale'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio raffigurato il logo della polizia stradale la legenda Polizia di stato'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(50, identificata(Moneta, '500 Lire 20 IFAD'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio il logo della IFAD'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(51, identificata(Moneta, '500 Lire Elezioni Parlamento Europeo'), 
and([ha(Moneta,caratteristicheBimetallica),
presenta(Moneta,'sul rovescio a legenda Elezioni Parlamento Europeo'),
fatta(Moneta,'di acmonital e bronzita'),
presenta(Moneta,'godronatura discontinua')]), 1,1).

rule(52, diagnosi(Moneta, Diagnosi), and([manifesta(Moneta, Diagnosi)]), 1, 1).

% regole di mendio livello 
rule(53, coniata(Moneta, oroCommemerativaPersonaggio), 
and([coniata(Moneta,nobileCommemorativeRepubblica),
fatta(Moneta,'di oro'),
presenta(Moneta,'un uomo sul dritto'),
presenta(Moneta,'la legenda Repubblica Italiana sul dritto'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(54, coniata(Moneta, oroCommemorativaCitta), 
and([coniata(Moneta,nobileCommemorativeRepubblica),
fatta(Moneta,'di oro'),
presenta(Moneta,'una chiesa sul dritto'),
presenta(Moneta,'la legenda Repubblica Italiana sul dritto'),
presenta(Moneta,'contorno rigato')]), 1,1).

rule(55, coniata(Moneta, nobileCommemorativeRepubblica), 
and([coniata(Moneta,pregiataVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),
no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(56, coniata(Moneta, acmonitalCommemorativa), 
and([coniata(Moneta,ranniNuovaMonetazione),
fatta(Moneta,'di acmonital'),
presenta(Moneta,'sul dritto un capo'),
presenta(Moneta,'sul dritto la legenda Repubblica Italiana')]), 1,1).

rule(57, coniata(Moneta, comportamento_generale_7), 
and([coniata(Moneta, abbuffate), 
no(coniata(Moneta, mancanza_controllo_mangiare)), 
no(coniata(Moneta, vomito)), 
no(ha(Moneta,acmonitalCommemorativa))]),1,1).

rule(58, coniata(Moneta, oroCommemerativaPersonaggio9), 
and([coniata(Moneta, bmi_sottopeso), 
no(coniata(Moneta, abbuffate)), 
no(ha(Moneta,caratteristicheAcmonital)), 
no(coniata(Moneta, vomito)), 
no(coniata(Moneta, lassativi))]), 1,1).

rule(59, coniata(Moneta, oroCommemorativaCitta0), 
and([coniata(Moneta, bmi_sottopeso), 
or([coniata(Moneta, abbuffate), 
coniata(Moneta, vomito), 
coniata(Moneta, lassativi)])]), 1,1).

rule(60, coniata(Moneta, criterio_diagnostico_basso_peso), 
and([coniata(Moneta, bmi_sottopeso), 
fatta(Moneta, 'restrizione apporto energetico che induce un significativo basso peso ovvero un peso minore del minimo normale o per i bambini e gli adolescenti minore del minimo atteso')]), 1,1).

%regole di basso livello riferite ai materiali preziosi

rule(61, coniata(Moneta, nobileVecchiaMonetazione), 
and([fatta(Moneta, 'materiale prezioso'), fatta(Moneta,'prima del 1970'), fatta(Moneta,'peso minore uguale 5 g')]), 1,1). 

rule(62, coniata(Moneta, pregiataVecchiaMonetazione), 
and([fatta(Moneta,'materiale prezioso'), 
fatta(Moneta,'prima del 1970'), 
no(fatta(Moneta,'peso minore uguale 5 g'))]), 1,1). 

rule(63, coniata(Moneta, nobileNuovaMonetazione), 
and([fatta(Moneta,'materiale prezioso'), 
fatta(Moneta,'peso minore uguale 5 g'), 
no(fatta(Moneta,'prima del 1970'))]), 1,1). 

rule(64, coniata(Moneta, pregiataNuovaMonetazione), 
and([fatta(Moneta,'materiale prezioso'), 
no(fatta(Moneta,'peso minore uguale 5 g')), 
no(fatta(Moneta,'prima del 1970'))]), 1,1). 

rule(65, coniata(Moneta, monetaDucato), 
and([coniata(Moneta,pregiataVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),fatta(Moneta,'con contorno liscio')]),1,1).


rule(66,coniata(Moneta,monetaMonarchica),
and([coniata(Moneta,nobileVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(67,coniata(Moneta,fiorino),
and([coniata(Moneta,nobileVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),fatta(Moneta,'con contorno liscio')]),1,1).

%regole di basso livello riferite ai materiali comuni

rule(68,coniata(Moneta,monetaComune),
and([no(fatta(Moneta,'materiale prezioso'))]),1,1).

rule(69,coniata(Moneta,sempliceVecchiaMonetazione),
and([coniata(Moneta,monetaComune),fatta(Moneta,'prima del 1970'),no(fatta(Moneta,'peso minore uguale 5 g'))]),1,1).

rule(70,coniata(Moneta,piccioloVecchiaMonetazione),
and([coniata(Moneta,monetaComune),fatta(Moneta,'prima del 1970'),fatta(Moneta,'peso minore uguale 5 g')]),1,1).

rule(71,coniata(Moneta,sempliceNuovaMonetazione),
and([coniata(Moneta,monetaComune),no(fatta(Moneta,'prima del 1970')),no(fatta(Moneta,'peso minore uguale 5 g'))]),1,1).

rule(72,coniata(Moneta,piccioloNuovaMonetazione),
and([coniata(Moneta,monetaComune),no(fatta(Moneta,'prima del 1970')),fatta(Moneta,'peso minore uguale 5 g')]),1,1).

rule(73,coniata(Moneta,ranniVecchiaMonetazione),
and([coniata(Moneta,sempliceVecchiaMonetazione), 
fatta(Moneta,'dimensione maggiore 22 mm'),
no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(74,coniata(Moneta,sondinoNuovaMonetazione),
and([coniata(Moneta,sempliceNuovaMonetazione),no(fatta(Moneta,'dimensione maggiore 22 mm')),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(75,coniata(Moneta,ranniNuovaMonetazione),
and([coniata(Moneta,sempliceNuovaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(76,coniata(Moneta,soldino),
and([coniata(Moneta,piccioloVecchiaMonetazione),no(fatta(Moneta,'dimensione maggiore 22 mm')),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(77,coniata(Moneta,bagattinoSecondaSerie),
and([coniata(Moneta,piccioloNuovaMonetazione),no(fatta(Moneta,'dimensione maggiore 22 mm')),fatta(Moneta,'con contorno liscio')]),1,1).

rule(78,coniata(Moneta,gazzettaNuovaMonetazione),
and([coniata(Moneta,piccioloNuovaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(79,coniata(Moneta,gazzettaVecchiaMonetazione),
and([coniata(Moneta,piccioloVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),no(fatta(Moneta,'con contorno liscio'))]),1,1).

rule(80,coniata(Moneta,torsello),
and([coniata(Moneta,piccioloVecchiaMonetazione),fatta(Moneta,'dimensione maggiore 22 mm'),fatta(Moneta,'con contorno liscio')]),1,1).

rule(81, coniata(Moneta,bagattinoPrimaSerie),
and([coniata(Moneta,piccioloVecchiaMonetazione),no(fatta(Moneta,'dimensione maggiore 22 mm')),fatta(Moneta,'con contorno liscio')]),1,1).

rule(82,coniata(Moneta,sondinoVecchiaMonetazione),
and([coniata(Moneta,piccioloVecchiaMonetazione),no(fatta(Moneta,'dimensione maggiore 22 mm')),no(fatta(Moneta,'con contorno liscio'))]),1,1).

%regole di mendio livello con materiale prezioso 

%regole di medio livello per monete fatte di argento

rule(83,coniata(Moneta,argentoCommemorativaRepubblica),
and([coniata(Moneta,nobileCommemorativeRepubblica),fatta(Moneta,'di argento'),presenta(Moneta,'una testa di donna sul dritto')]),1,1).

rule(84,coniata(Moneta,argentoCommemorativaPersonaggio),
and([coniata(Moneta,nobileCommemorativeRepubblica),fatta(Moneta,'di argento'),presenta(Moneta,'una testa di uomo sul dritto')]),1,1).

rule(85,coniata(Moneta,argentoCommemorativaCentenario),
and([coniata(Moneta,nobileCommemorativeRepubblica),fatta(Moneta,'di argento'),presenta(Moneta,'una donna seduta sul dritto')]),1,1).

%regole di mendio livello con materiale comune

%regole di medio livello per monete fatte di acmonital
rule(86,ha(Moneta,caratteristicheAcmonital),
and([coniata(Moneta,ranniVecchiaMonetazione),fatta(Moneta,'di acmonital'),presenta(Moneta,'un capo sul dritto'),presenta(Moneta,'la legenda Repubblica Italiana sul dritto'), presenta(Moneta,'contorno rigato')]),1,1).

rule(87,ha(Moneta,acmonitalNuovaMonetazione),
and([coniata(Moneta,sondinoNuovaMonetazione),fatta(Moneta,'di acmonital'),presenta(Moneta,'un capo sul dritto'),presenta(Moneta,'la legenda Repubblica Italiana sul dritto'),presenta(Moneta,'contorno rigato')]),1,1).

rule(88,ha(Moneta,acmonitalCelebrazioneLavoro),
and([coniata(Moneta,bagattinoSecondaSerie),fatta(Moneta,'di acmonital'),presenta(Moneta,'sul dritto un capo'),presenta(Moneta,'sul dritto la legenda Repubblica Italiana')]),1,1).

rule(89,ha(Moneta,acmonitalMonetaAccademica),
and([coniata(Moneta,ranniNuovaMonetazione),fatta(Moneta,'di acmonital'),presenta(Moneta,'sul dritto un timone'),presenta(Moneta,'sul dritto la legenda Repubblica Italiana')]),1,1).

%regole di medio livello per monete fatte di italma

rule(90,ha(Moneta,italmaPatriottismoLiberta),
and([coniata(Moneta,gazzettaVecchiaMonetazione),fatta(Moneta,'di italmal'),presenta('sul dritto la legenda Repubblica Italiana'),presenta('sul rovescio un ramo di ulivo')]),1,1).

rule(91, ha(Moneta,monetaRappresentanteRinascita),
and([coniata(Moneta,torsello),fatta(Moneta,'di italmal'),presenta('sul dritto la legenda Repubblica Italiana'),presenta('sul rovescio una o due spighe')]),1,1).

rule(92,ha(Moneta,italmaCelebrativaRepubblica),
and([coniata(Moneta,gazzettaVecchiaMonetazione),fatta(Moneta,'di italmal'),presenta('sul dritto la legenda Repubblica Italiana'),presenta('sul dritto una testa di donna')]),1,1).

rule(93,ha(Moneta,caratteristicheItalma),
and([fatta(Moneta,'di italmal'),presenta('sul dritto la legenda Repubblica Italiana'),
or([coniata(Moneta,sondinoVecchiaMonetazione),coniata(Moneta,sondinoNuovaMonetazione)])]),1,1).

rule(94,ha(Moneta,italmaAgricoltura),
and([fatta(Moneta,'di italmal'),coniata(Moneta,bagattinoPrimaSerie),presenta('sul dritto la legenda Repubblica Italiana')]),1,1).

rule(95,cha(Moneta,italmaVecchiaMonetazione),
and([fatta(Moneta,'di italmal'),presenta('sul dritto la legenda Repubblica Italiana'),
or([coniata(Moneta,bagattinoSecondaSerie),coniata(Moneta,bagattinoPrimaSerie)])]),1,1).

%regole di medio livello per le monete fatte di due metalli

rule(96,ha(Moneta,caratteristicheBimetallica),
and([coniata(Moneta,ranniNuovaMonetazione),fatta(Moneta,'di due metalll'),presenta('sul dritto una testa di donna'),presenta('sul dritto la legenda Repubblica Italiana')]),1,1).

%regole di medio livello per le monete fatte di bronzital

rule(97,ha(Moneta,moneteForzeDellOrdine),
and([coniata(Moneta,gazzettaNuovaMonetazione),fatta(Moneta,'di bronzital'),presenta('sul dritto la testa muliebre girata a destra'),presenta('sul dritto la legenda Repubblica Italiana')]),1,1).

rule(98,ha(Moneta,bronzitalCelebrativa),
and([coniata(Moneta,gazzettaNuovaMonetazione),fatta(Moneta,'di bronzital'),presenta('sul dritto un palazzo'),presenta('sul dritto la legenda Repubblica Italiana')]),1,1).

rule(99,ha(Moneta,monetaOrganizzazioneUmanitaria),
and([coniata(Moneta,gazzettaNuovaMonetazione),fatta(Moneta,'di bronzital'),presenta('sul dritto una donna'),presenta('sul dritto la legenda Repubblica Italiana')]),1,1).

rule(100,ha(Moneta,caratteristicheBronzital),
and([fatta(Moneta,'di bronzital'),presenta('sul dritto una donna'),presenta('sul dritto la legenda Repubblica Italiana'),or([or([or([coniata(Moneta,sondinoNuovaMonetazione),coniata(Moneta,bagattinoSecondaSerie)]),coniata(Moneta,bagattinoPrimaSerie)]),coniata(Moneta,sondinoVecchiaMonetazione)])]),1,1).

%regole di medio livello monete di cupronichel

rule(101,ha(Moneta,cupronichelAgricoltura),
and([coniata(Moneta,sondinoNuovaMonetazione),fatta(Moneta,'di cupronichell'),presenta('sul dritto un capo di donna'),presenta('sul rovescio un ramo di ulivo')]),1,1).

rule(102,ha(Moneta,cupronichelCelebrativaRepubblica),
and([coniata(Moneta,bagattinoSecondaSerie),fatta(Moneta,'di cupronichell'),presenta('sul dritto un capo di donna')]),1,1).

%fact(1,fatta(moneta,'materiale prezioso'),1).



