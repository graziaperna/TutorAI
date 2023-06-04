:- dynamic (fact/3).
:- dynamic (rule/5).

askablemenu(colors(X,Y), 'Quando studi tendi a sottolineare i concetti fondamentali con vari colori?',[si, no],colors,X,Y).
askablemenu(questions_topic(X,Y), 'Al termine dello studio chiedi ad altre persone di porti delle domande sull\'argomento studiato?',[si, no, a_volte],questions_topic,X,Y).
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
askablemenu(enigma(X,Y), 'Il cadavere di una donna e\' ancora riverso sul marciapiede. Ci sono quattro uomini indiziati per l\'omicidio. Alle prime domande della polizia rispondono:
ANTONIO: "Ho visto Carlo e Dario sul luogo del delitto, quindi uno di loro e\' l\'assassino."
BERNARDO: "Non sono stato io."
CARLO: "E\' stato Dario. L\'ho visto sparare!"
DARIO: "E\' stato Bernardo. L\'ho visto mentre fuggiva."
Se solo l\'assassino ha mentito, chi e\' il colpevole?',[antonio, bernardo, carlo, dario],enigma,X,Y).
askablemenu(notes(X,Y), 'Durante la lezione, preferisci seguire attivamente cio\' che dice l\'insegnante o prendere appunti?',[seguire_attivamente, prendere_appunti],notes,X,Y).
askablemenu(movie(X,Y), 'Ti piacciono i film tratti da storie/situazioni vere?',[si, no],movie,X,Y).
askablemenu(group_study(X,Y), 'Preferisci studiare da solo o in compagnia?',[da_solo, in_compagnia],group_study,X,Y).
askablemenu(videogames(X,Y), 'Trovi siano utili i videogiochi didattici?',[si, no],videogames,X,Y).
askablemenu(books_quantity(X,Y), 'Quanti libri leggi?',[almeno_1_al_mese, uno_ogni_2_mesi, uno_ogni_6_mesi, nessuno_tra_queste],books_quantity,X,Y).
askablemenu(guided_visits(X,Y), 'Trovi interessanti le visite guidate?',[si, no],guided_visits,X,Y).
askablemenu(group_problem(X,Y), 'Se un membro del team iniziasse a prendersi il merito dei tuoi contributi, cosa faresti?',[cercherei_di_risolvere_la_situazione, cercherei_di_cambiare_team],group_problem,X,Y).
askablemenu(group_sports(X,Y), 'Preferisci sport di gruppo o individuali?',[di_gruppo, individuali],group_sports,X,Y).
askablemenu(group_divergences(X,Y), 'Se tu e un membro del team non siete d\'accordo su come procedere con un progetto di gruppo, come si arriva a una decisione?',[ne_discutiamo_in_modo_costruttivo_valutando_i_pro_e_i_contro, non_ascolto_cosa_gli_altri_hanno_da_dire, subito_capisco_che_ho_torto],group_divergences,X,Y).
askablemenu(notes_type(X,Y), 'Preferisci prendere appunti sul quaderno o sul computer?',[quaderno, computer],notes_type,X,Y).
askablemenu(lessons_type(X,Y), 'Preferisci seguire le lezioni online o in presenza?',[online, in_presenza],lessons_type,X,Y).
askablemenu(exercise_place(X,Y), 'Preferisci svolgere gli esercizi in classe o a casa?',[in_classe, a_casa],exercise_place,X,Y).

/*Definitions of rules*/

/*LEVEL 0*/
rule(cl_sim,method(X,'Cooperative learning e simulazione'),and([is(X,collective_preparation),is(X,subject_experience)]),1,1).
rule(cl_at,method(X,'Cooperative learning e approccio tutoriale'),and([is(X,collective_preparation),is(X,test_explanation)]),1,1).
rule(v_at,method(X,'Video e approccio tutoriale'),and([is(X,video_explanation), is(X,test_explanation)]),1,1).
rule(v_sim,method(X,'Video e simulazione'),and([is(X,video_explanation), is(X,subject_experience)]),1,1).
rule(a_at,method(X,'Audio e approccio tutoriale'),and([is(X,audio_explanation), is(X,test_explanation)]),1,1).
rule(a_sim,method(X,'Audio e simulazione'),and([is(X,audio_explanation), is(X,subject_experience)]),1,1).
rule(v_dim,method(X,'Video e dimostrazione'),and([is(X,video_explanation), is(X,subject_experience), is(X,multimedia_doc), is(X,video_explanation), is(X,audio_explanation)]),1,1).
rule(m_at,method(X,'Mappe e approccio tutoriale'),and([is(X,key_doc), is(X,test_explanation)]),1,1).
rule(lc_at,method(X,'Libri cartacei e approccio tutoriale'),and([is(X,old_school), is(X,test_explanation)]),1,1).
rule(dd_d,method(X,'Documenti digitali e dimostrazione'),and([is(X,multimedia_doc), is(X,subject_experience)]),1,1).
rule(dd_at,method(X,'Documenti digitali e approccio tutoriale'),and([is(X,multimedia_doc), is(X,test_explanation)]),1,1).
rule(sl_at,method(X,'Slide e approccio tutoriale'),and([is(X,multimedia_doc), is(X,key_doc), is(X,test_explanation)]),1,1).
rule(sl_dim,method(X,'Slide e dimostrazione'),and([is(X,multimedia_doc), is(X,key_doc),is(X,subject _experience), is(X,video_explanation), is(X,audio_explanation)]),1,1).
rule(int_sim,method(X,'Interdisciplinarita e simulazione'),and([is(X,deep_learning), is(X,subject_experience)]),1,1).
rule(ps_sim,method(X,'Problem solving e simulazione'),and([is(X,logical_reasoning), is(X,deep_learning), is(X,subject_experience)]),1,1).
rule(ps_at,method(X,'Problem solving e approccio tutoriale'),and([is(X,logical_reasoning), is(X,deep_learning), is(X,test_explanation)]),1,1).

/*LEVEL 1*/
rule(multimedia_doc_1,is(X,multimedia_doc),and([is(X,sample_multimedial), is(X,digital), is(X,supported_learning)]),1,1).
rule(multimedia_doc_2,is(X,multimedia_doc),and([is(X,paper_study), is(X,digital), is(X,supported_learning)]),1,1).
rule(key_doc_1,is(X,key_doc),and([is(X,paper_study), is(X,logic_linking), is(X,sample_multimedial), is(X,step_study)]),1,1).
rule(test_explanation_1,is(X,test_explanation),and([is(X,digital), is(X,paper_study)]),1,1).
rule(video_explanation_1,is(X,video_explanation),and([is(X,supported_learning), is(X,sample_multimedial)]),1,1).
rule(video_explanation_2,is(X,video_explanation),and([is(X,digital), is(X,supported_learning)]),1,1).
rule(deep_learning_1,is(X,deep_learning),and([is(X,logic_linking), is(X,sample_multimedial)]),1,1).
rule(deep_learning_2,is(X,deep_learning),and([is(X,paper_study), is(X,logic_linking)]),1,1).
rule(audio_explanation_1,is(X,audio_explanation),and([is(X,sample_multimedial), is(X,digital), is(X,supported_learning)]),1,1).
rule(collective_preparation_1,is(X,collective_preparation),and([is(X,supported_learning), is(X,help_study)]),1,1).
rule(collective_preparation_2,is(X,collective_preparation),and([is(X,paper_study), is(X,help_study)]),1,1).
rule(subject_experience_1,is(X,subject_experience),and([is(X,supported_learning), is(X,step_study), (X,help_study)]),1,1).
rule(old_school_1,is(X,old_school),and([is(X,paper_study), is(X,supported_learning), not([is(X,digital)])]),1,1).
rule(logical_reasoning_1,is(X,logical_reasoning),and([is(X,logic_linking), is(X,step_study)]),1,1).

/*LEVEL 2*/
rule(sample_multimedial_1,is(X,sample_multimedial),and([is(X,multimedia), is(X,sample_doc)]),1,1).
rule(sample_multimedial_2,is(X,sample_multimedial),and([is(X,document), is(X,sample_doc)]),1,1).
rule(supported_learning_1,is(X,supported_learning),and([is(X,guided_learning), is(X,practical_learning), is(X,group_learning)]),1,1).
rule(digital_1,is(X,digital),and([or([is(X,multimedia), is(X,sample_doc)]), is(X,visual)]),1,1).
rule(step_study_1,is(X,step_study),and([is(X,logical_mind), is(X,practical_learning)]),1,1).
rule(step_study_2,is(X,step_study),and([is(X,analitic_learning), is(X,practical_learning)]),1,1).
rule(step_study_3,is(X,step_study),and([is(X,analitic_learning), is(X,guided_learning)]),1,1).
rule(step_study_4,is(X,step_study),and([is(X,logical_mind), is(X,guided_learning)]),1,1).
rule(logic_linking_1,is(X,logic_linking),and([is(X,logical_mind), is(X,analitic_learning), is(X,sample_doc)]),1,1).
rule(paper_study_1,is(X,paper_study),and([is(X,document), is(X,sample_doc), is(X,analitic_learning)]),1,1).
rule(help_study_1,is(X,help_study),and([is(X,guided_learning), is(X,group_learning), is(X,multimedia)]),1,1).
rule(help_study_2,is(X,help_study),and([is(X,guided_learning), is(X,group_learning), is(X,document)]),1,1).
rule(help_study_3,is(X,help_study),and([is(X,guided_learning), is(X,group_learning), is(X,sample_doc)]),1,1).

/*LEVEL 3*/
rule(multimedia_1,is(X,multimedia),and([audio(X,si)]),1,1).
rule(multimedia_2,is(X,multimedia),and([book_type(X,audiobook)]),1,1).
rule(multimedia_3,is(X,multimedia),and([movie(X,si)]),1,1).
rule(multimedia_4,is(X,multimedia),and([videogames(X,si)]),1,1).
rule(multimedia_5,is(X,multimedia),and([lessons_type(X,online)]),1,1).
rule(multimedia_6,is(X,multimedia),and([notes(X,prendere_appunti), notes_type(X,computer)]),1,1).
rule(document_1,is(X,document),and([book_type(X,documenti)]),1,1).
rule(document_2,is(X,document),and([subject_type(X,umanistiche)]),1,1).
rule(document_3,is(X,document),or([deepening(X,si), deepening(X,a_volte)]),1,1).
rule(document_4,is(X,document),or([books_quantity(X,almeno_1_al_mese), books_quantity(X,uno_ogni_2_mesi)]),1,1).
rule(document_5,is(X,document),and([notes(X,prendere_appunti), notes_type(X,quaderno)]),1,1).
rule(sample_doc_1,is(X,sample_doc),and([notes(X,prendere_appunti), colors(X,si)]),1,1).
rule(practical_learning_1,is(X,practical_learning),and([period_test(X,settimanali)]),1,1).
rule(practical_learning_2,is(X,practical_learning),and([exercise(X,studenti)]),1,1).
rule(practical_learning_3,is(X,practical_learning),and([experts(X,si)]),1,1).
rule(practical_learning_4,is(X,practical_learning),and([guided_visits(X,si)]),1,1).
rule(practical_learning_5,is(X,practical_learning),and([exercise_place(X,a_casa)]),1,1).
rule(practical_learning_6,is(X,guided_learning),and([exercise(X,studenti)]),1,1).
rule(guided_learning_1,is(X,guided_learning),and([subject_type(X,scientifiche)]),1,1).
rule(guided_learning_2,is(X,guided_learning),and([experts(X,si)]),1,1).
rule(guided_learning_3,is(X,guided_learning),or([questions_topic(X,si), questions_topic(X,a_volte)]),1,1).
rule(guided_learning_4,is(X,guided_learning),and([enigma(X,dario)]),1,1).
rule(guided_learning_5,is(X,guided_learning),and([interaction(X,si)]),1,1).
rule(guided_learning_6,is(X,guided_learning),and([group_study(X,in_compagnia)]),1,1).
rule(guided_learning_7,is(X,guided_learning),and([exercise_place(X,in_classe)]),1,1).
rule(analitic_learning_1,is(X,analitic_learning),and([enigma(X,si)]),1,1).
rule(analitic_learning_2,is(X,analitic_learning),or([deepening(X,si), deepening(X,a_volte)]),1,1).
rule(visual_1,is(X,visual),and([colors(X,si)]),1,1).
rule(visual_2,is(X,visual),and([movie(X,si)]),1,1).
rule(visual_3,is(X,visual),and([book_type(X,documenti)]),1,1).
rule(visual_4,is(X,visual),and([videogames(X,si)]),1,1).
rule(visual_5,is(X,visual),or([books_quantity(X,almeno_1_al_mese), books_quantity(X,uno_ogni_2_mesi)]),1,1).
rule(visual_6,is(X,visual),or([computer(X,ogni_giorno), computer(X,ogni_tre_giorni)]),1,1).
rule(visual_7,is(X,visual),and([lessons_type(X,online)]),1,1).
rule(logical_mind_1,is(X,logical_mind),and([subject_type(X,scientifiche),enigma(X,dario)]),1,1).
rule(group_learning_1,is(X,group_learning),and([group_study(X,in_compagnia)]),1,1).
rule(group_learning_2,is(X,group_learning),and([group_divergences(X,ne_discutiamo_in_modo_costruttivo_valutando_i_pro_e_i_contro)]),1,1).
rule(group_learning_3,is(X,group_learning),and([group_problem(X,cercherei_di_risolvere_la_situazione)]),1,1).
rule(group_learning_4,is(X,group_learning),and([group_sports(X,di_gruppo)]),1,1).
rule(group_learning_5,is(X,group_learning),and([questions_topic(X,si), questions_topic(X,a_volte)]),1,1).
rule(group_learning_6,is(X,group_learning),and([exercise(X,studenti)]),1,1).
