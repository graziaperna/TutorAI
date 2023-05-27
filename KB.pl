:- dynamic (fact/3).
:- dynamic (rule/5).

askablemenu(colors(X,Y), 'Quando studi tendi a sottolineare i concetti fondamentali con vari colori?',[si, no],colors,X,Y).
askablemenu(questions_topic(X,Y), 'Al termine dello studio chiedi ad altre persone di porti delle domande sull’argomento studiato?',[si, no, a_volte],questions_topic,X,Y).
askablemenu(subject_type(X,Y), 'Preferisci materie umanistiche o scientifiche?',[umanistiche, scientifiche],subject_type, X,Y).
askablemenu(audio(X,Y), 'Ascolti podcast e musica?',[si, no],audio,X,Y).
askablemenu(experts(X,Y), 'Credi che siano utili inverventi di specialisti?',[si, no],experts,X,Y).
askablemenu(deepening(X,Y), 'Quando studi un argomento di solito sei interessato ad approfondirlo?',[si, no, a_volte],deepening,X,Y).
askablemenu(interaction(X,Y), 'Durante la spiegazione del docente sei propenso a interagire con lui e a fare domande?',[si, no],interaction,X,Y).
askablemenu(period_test(X,Y), 'Preferisci le verifiche settimanali o semestrali?',[settimanali,semestrali],period_test,X,Y).
askablemenu(book(X,Y), 'Preferisci i libri cartacei o gli eBook?',[cartacei, ebook],book,X,Y).
askablemenu(book_type(X,Y), 'Preferisci libri cartacei/documenti o audiobook?',[documenti,audiobook],book_type,X,Y).
askablemenu(exercise(X,Y), 'Durante lo svolgimento degli esercizi in classe preferisci che li svolga il docente o gli studenti?',[docente, studenti],exercise,X,Y).
askablemenu(computer(X,Y), 'Quanto spesso usi il computer?',[ogni_giorno, ogni_tre_giorni, nessuno_tra_queste],computer,X,Y).
askablemenu(enigma(X,Y), 'Il cadavere di una donna è ancora riverso sul marciapiede. Ci sono quattro uomini indiziati per l’omicidio. Alle prime domande della polizia rispondono:
ANTONIO: “Ho visto Carlo e Dario sul luogo del delitto, quindi uno di loro è l’assassino.”
BERNARDO: “Non sono stato io.”
CARLO: “E’ stato Dario. L’ho visto sparare!”
DARIO: “E’ stato Bernardo. L’ho visto mentre fuggiva.”
Se solo l’assassino ha mentito, chi è il colpevole?',[antonio, bernardo, carlo, dario],enigma,X,Y).
askablemenu(notes(X,Y), 'Durante la lezione, preferisci seguire attivamente ciò che dice linsegnate o prendere appunti?',[seguire_attivamente, prendere_notes],notes,X,Y).
askablemenu(movie(X,Y), 'Ti piacciono i film tratti da storie/situazioni vere?',[si, no],movie,X,Y).
askablemenu(group_study(X,Y), 'Preferisci studiare da solo o in compagnia?',[da_solo, in_compagnia],group_study,X,Y).
askablemenu(videogames(X,Y), 'Trovi siano utili i videogiochi didattici?',[si, no],videogames,X,Y).
askablemenu(books_quantity(X,Y), 'Quanti libri leggi?',[almeno_1_al_mese, uno_ogni_2_mesi, uno_ogni_6_mesi, uno_all_anno],books_quantity,X,Y).
askablemenu(guided_visits(X,Y), 'Trovi interessanti le visite guidate?',[si, no],guided_visits,X,Y).
askablemenu(group_problem(X,Y), 'Se un membro del team iniziasse a prendersi il merito dei tuoi contributi, cosa faresti?',[cercherei_di_risolvere_la_situazione, cercherei_di_cambiare_team],group_problem,X,Y).
askablemenu(group_sports(X,Y), 'Preferisci sport di gruppo o individuali?',[di_gruppo, individuali],group_sports,X,Y).
askablemenu(group_divergences(X,Y), 'Se tu e un membro del team non siete d’accordo su come procedere con un progetto di gruppo, come si arriva a una decisione?',[ne_discutiamo_in_modo_costruttivo_valutando_i_pro_e_i_contro, non_ascolto_cosa_altro_ho_da_dire, subito_capisco_che_ha_ragione_lui],group_divergences,X,Y).
askablemenu(notes_type(X,Y), 'Preferisci prendere appunti sul quaderno o sul computer?',[quaderno, computer],notes_type,X,Y).
askablemenu(lessons_type(X,Y), 'Preferisci seguire le lezioni online o in presenza?',[online, in_presenza],lessons_type,X,Y).
askablemenu(exercise_place(X,Y), 'Preferisci svolgere gli esercizi in classe o a casa?',[in_classe, a_casa],exercise_place,X,Y).


rule(1,method(X,'Cooperative learning e simulazione'),and([is(X,collective_preparation),is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(2,method(X,'Cooperative learning e approccio tutoriale'),and([is(X,collective_preparation),is(X,test_explanation)]),1,1).
rule(3,method(X,'Video e approccio tutoriale'),and([is(X,video_explanation), is(X,test_explanation)]),1,1).
rule(4,method(X,'Video e simulazione'),and([is(X,video_explanation), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(5,method(X,'Audio e approccio tutoriale'),and([is(X,audio_explanation), is(X,test_explanation)]),1,1).
rule(6,method(X,'Audio e simulazione'),and([is(X,audio_explanation), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(7,method(X,'Video e dimostrazione'),and([is(X,video_explanation), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(8,method(X,'Mappe e approccio tutoriale'),and([is(X,key_doc), is(X,test_explanation)]),1,1).
rule(9,method(X,'Libri cartacei e approccio tutoriale'),and([is(X,document), is(X,test_explanation), not([is(X,multimedia_doc)])]),1,1).
rule(10,method(X,'Documenti digitali e dimostrazione'),and([is(X,document), is(X,multimedia_doc), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(11,method(X,'Documenti digitali e approccio tutoriale'),and([isrule(14,is(X,multimedia_doc),and([is(X, multimedia), book(X,ebook), ]),1,1).(X,document), is(X,multimedia_doc), is(X,test_explanation)]),1,1).
rule(12,method(X,'Slide e approccio tutoriale'),and([is(X,multimedia_doc), is(X,test_explanation)]),1,1).
rule(13,method(X,'Slide e dimostrazione'),and([is(X,multimedia_doc), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(14,method(X,'Interdisciplinarita e simulazione'),and([is(X,deep_learning), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(15,method(X,'Problem solving e simulazione'),and([is(X,analitic_learning), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).

rule(16,is(X,multimedia_doc),and([is(X, paper_study), is(X, digital)]),1,1).
rule(17,is(X,multimedia_doc),and([is(X, sample_multimedial), is(X, digital)]),1,1).
rule(18,is(X,key_doc),and([is(X, paper_study), is(X, logic_linking), is(X, sample_multimedial)]),1,1).
rule(19,is(X,test_explanation),and([is(X, supported_learning), is(X, digital)]),1,1).
rule(20,is(X,test_explanation),and([is(X, digital), is(X, paper_study)]),1,1).
rule(21,is(X,video_explanation),and([is(X, supported_learning), is(X,sample_multimedial)]),1,1).
rule(22,is(X,video_explanation),and([is(X, digital), is(X,supported_learning)]),1,1).
rule(23,is(X,deep_learning),and([is(X, logic_linking), is(X, sample_multimedial)]),1,1).
rule(24,is(X,deep_learning),and([is(X, paper_study), is(X, logic_linking)]),1,1).
rule(25,is(X,audio_explanation),and([is(X, sample_multimedial), is(X, digital), is(X, supported_learning)]),1,1).
rule(26,is(X,collective_preparation),and([is(X,supported_learning), is(X,help_study)]),1,1).
rule(27,is(X,collective_preparation),and([is(X,paper_study), is(X,help_study)]),1,1).

rule(28,is(X,sample_multimedial),and([is(X, multimedia), is(X, sample_doc)]),1,1).
rule(29,is(X,supported_learning),and([is(X, guided_learning), is(X, practical_learning)]),1,1).
rule(30,is(X,supported_learning),and([is(X, guided_learning), is(X, practical_learning), is(X, multimedia)]),1,1).
rule(31,is(X,digital),and([is(X, multimedia)]),1,1).
rule(32,is(X,digital),and([is(X, visual)]),1,1).
rule(33,is(X,step_study),and([is(X, logical_mind), is(X, practical_learning)]),1,1).
rule(34,is(X,logic_linking),and([is(X, logical_mind), is(X, analitic_learning)]),1,1).
rule(35,is(X,logic_linking),and([is(X, logical_mind), is(X, analitic_learning), is(X,sample_doc)]),1,1).
rule(36,is(X,paper_study),and([is(X, document), is(X, sample_doc)]),1,1).
rule(37,is(X,paper_study),and([is(X, document), is(X, sample_doc), is(X, analitic_learning)]),1,1).
rule(38,is(X,help_study),and([is(X, guided_learning), is(X, group_learning)]),1,1).

/* Definitions of intermediate rules */
rule(39,is(X,multimedia),and([audio(X, si)]),1,1).
rule(40,is(X,multimedia),and([book_type(X, audiobook)]),1,1).
rule(41,is(X,multimedia),and([movie(X, si)]),1,1).
rule(42,is(X,multimedia),and([videogames(X, si)]),1,1).
rule(43,is(X,multimedia),and([lessons_type(X, online)]),1,1).
rule(44,is(X,multimedia),and([notes(X, prendere_notes), (X, computer)]),1,1).
rule(45,is(X,document),and([book_type(X, documenti)]),1,1).
rule(46,is(X,document),and([subject_type(X, umanistiche)]),1,1).
rule(47,is(X,document),or([deepening(X, si), deepening(X, a_volte)]),1,1).
rule(48,is(X,document),or([books_quantity(X, almeno_1_al_mese), books_quantity(X, uno_ogni_2_mesi)]),1,1).
rule(49,is(X,document),and([notes(X, prendere_notes), notes_type(X, quaderno)]),1,1).
rule(50,is(X,sample_doc),and([colors(X, si)]),1,1).
rule(51,is(X,sample_doc),and([notes(X, prendere_notes)]),1,1).
rule(52,is(X,practical_learning),and([period_test(X, settimanali)]),1,1).
rule(53,is(X,practical_learning),and([exercise(X, studenti)]),1,1).
rule(54,is(X,practical_learning),and([subject_type(X, scientifiche)]),1,1).
rule(55,is(X,practical_learning),and([experts(X, si)]),1,1).
rule(56,is(X,practical_learning),and([guided_visits(X, si)]),1,1).
rule(57,is(X,practical_learning),and([exercise_place(X, a_casa)]),1,1).
rule(58,is(X,guided_learning),and([exercise(X, studenti)]),1,1).
rule(59,is(X,guided_learning),and([subject_type(X, scientifiche)]),1,1).
rule(60,is(X,guided_learning),and([experts(X, si)]),1,1).
rule(61,is(X,guided_learning),or([questions_topic(X, si), questions_topic(X, a_volte)]),1,1).
rule(62,is(X,guided_learning),and([enigma(X, dario)]),1,1).
rule(63,is(X,guided_learning),and([interaction(X, si)]),1,1).
rule(64,is(X,guided_learning),and([group_study(X, in_compagnia)]),1,1).
rule(65,is(X,guided_learning),and([exercise_place(X, in_classe)]),1,1).
rule(66,is(X,analitic_learning),and([enigma(X, si)]),1,1).
rule(67,is(X,analitic_learning),or([deepening(X, si), deepening(X, a_volte)]),1,1).
rule(68,is(X,visual),and([colors(X, si)]),1,1).
rule(69,is(X,visual),and([movie(X, si)]),1,1).
rule(70,is(X,visual),and([book_type(X, documenti)]),1,1).
rule(71,is(X,visual),and([videogames(X, si)]),1,1).
rule(72,is(X,visual),or([books_quantity(X, almeno_1_al_mese), books_quantity(X, uno_ogni_2_mesi)]),1,1).
rule(73,is(X,visual),or([computer(X, ogni_giorno), computer(X, ogni_tre_giorni)]),1,1).
rule(74,is(X,visual),and([lessons_type(X, online)]),1,1).
rule(75,is(X,logical_mind),and([subject_type(X, scientifiche)]),1,1).
rule(76,is(X,group_learning),and([group_study(X, in_compagnia)]),1,1).
rule(77,is(X,group_learning),and([group_divergences(X, ne_discutiamo_in_modo_costruttivo_valutando_i_pro_e_i_contro)]),1,1).
rule(78,is(X,group_learning),and([group_problem(X, cercherei_di_risolvere_la_situazione)]),1,1).
rule(79,is(X,group_learning),and([group_sports(X, di_gruppo)]),1,1).
rule(80,is(X,group_learning),and([questions_topic(X, si), questions_topic(X, a_volte)]),1,1).
rule(81,is(X,group_learning),and([exercise(X, studenti)]),1,1).









