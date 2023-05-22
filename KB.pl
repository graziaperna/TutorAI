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

rule(1,method(X,'Video e approccio tutoriale'),and([is(X,video_explanation), is(X,test_explanation)]),1,1).
rule(2,method(X,'Video e simulazione'),and([is(X,video_explanation), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(3,method(X,'Audio e approccio tutoriale'),and([is(X,audio_explanation), is(X,test_explanation)]),1,1).
rule(4,method(X,'Audio e simulazione'),and([and([is(X,audio_explanation), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(5,method(X,'Video e dimostrazione'),and([is(X,video_explanation), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(6,method(X,'Mappe e approccio tutoriale'),and([is(X,key_doc), is(X,test_explanation)]),1,1).
rule(7,method(X,'Libri cartacei e approccio tutoriale'),and([is(X,document), is(X,test_explanation), not([is(X,multimedia_doc)])]),1,1).
rule(8,method(X,'Documenti digitali e dimostrazione'),and([is(X,document), is(X,multimedia_doc), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(9,method(X,'Documenti digitali e approccio tutoriale'),and([is(X,document), is(X,multimedia_doc), is(X,test_explanation)]),1,1).
rule(10,method(X,'Slide e approccio tutoriale'),and([is(X,multimedia_doc), is(X,test_explanation)]),1,1).
rule(11,method(X,'Slide e dimostrazione'),and([is(X,multimedia_doc), is(X,guided_learning), is(X,practical_learning)]),1,1).
rule(12,method(X,'Interdisciplinarita e simulazione'),and([is(X,deep_learning), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).
rule(13,method(X,'Problem solving e simulazione'),and([is(X,analitic_learning), is(X,practical_learning), not([is(X,guided_learning)])]),1,1).

rule(14,is(X,multimedia_doc),and([is(X, multimedia), book(X,ebook)]),1,1).
rule(15,is(X,key_doc),and([is(X, analitic_learning), is(X, document)]),1,1).
rule(16,is(X,test_explanation),and( [ is(X, sample_doc), or( [ book_type(X, audiobook), audio(X, si) ] ) ] ),1,1).
rule(17,is(X,video_explanation),and([is(X, practical_learning), is(X, multimedia)]),1,1).
rule(18,is(X,deep_learning),and([is(X, analitic_learning), is(X, document), is(X, multimedia)]),1,1).
rule(19,is(X,audio_explanation),and([is(X, multimedia), is(X, guided_learning)]),1,1).

/* Definitions of intermediate rules */
rule(20,is(X,multimedia),and([audio(X, si)]),1,1).
rule(21,is(X,multimedia),and([book_type(X, audiobook)]),1,1).
rule(22,is(X,multimedia),and([movie(X, si)]),1,1).
rule(23,is(X,document),and([book_type(X, documenti)]),1,1).
rule(24,is(X,document),and([subject_type(X, umanistiche)]),1,1).
rule(25,is(X,document),or([deepening(X, si)], deepening(X, a_volte)]),1,1).
rule(26,is(X,sample_doc),and([colors(X, si)]),1,1).
rule(27,is(X,sample_doc),and([notes(X, prendere_notes)]),1,1).
rule(28,is(X,practical_learning),and([period_test(X, settimanali)]),1,1).
rule(29,is(X,practical_learning),and([exercise(X, studenti)]),1,1).
rule(30,is(X,practical_learning),and([subject_type(X, scientifiche)]),1,1).
rule(31,is(X,practical_learning),and([experts(X, si)]),1,1).
rule(32,is(X,guided_learning),and([exercise(X, studenti)]),1,1).
rule(33,is(X,guided_learning),and([subject_type(X, scientifiche)]),1,1).
rule(34,is(X,guided_learning),and([experts(X, si)]),1,1).
rule(35,is(X,guided_learning),or([questions_topic(X, si)], questions_topic(X, a_volte)]),1,1).
rule(36,is(X,guided_learning),and([enigma(X, dario)]),1,1).
rule(37,is(X,guided_learning),and([interaction(X, si)]),1,1).
rule(38,is(X,analitic_learning),and([enigma(X, si)]),1,1).
rule(39,is(X,analitic_learning),or([deepening(X, si)], deepening(X, a_volte)]),1,1).









