:-style_check(-discontiguous).
%% :- [utility].

write_list_of_facts([]).
write_list_of_facts([H|T]) :-
	term_string(H,S),
	string_concat(S,".",NS),
	writeln(F,NS),
	write_list_of_facts(T).


% Default file delle regole.
%% start_stricomm :- 
%% 	start_stricomm('rules.pl').
sublist_from(0, List, List) :-
	!.
sublist_from(Index, [X|Y], Res) :- 
	I is Index - 1,
	sublist_from(I, Y, Res).

ie_dir(IE_dir) :- gtrace,
	absolute_file_name('', Res), 
	split_string(Res, '/', '', L), 
	reverse(L, Rev),
	sublist_from(2, Rev, IE_dir_list_rev),
	reverse(IE_dir_list_rev, IE_dir_list),
	atomic_list_concat(IE_dir_list, '/', IE_dir), writeln(IE_dir).

start_stricomm :-
	%open('regole_inv.txt', write, F), write(F,''), close(F),
	% modificare con "../" se questo modulo é invocato direttamente, lasciarlo cosí per il funzionamento da seva_start.pl
	reconsult('../gie/gie_forward.pl'), 
	reconsult('../seva_start.pl'),
	assert(stricomm), % Asserisco l'uso in corso della comunicazione stricomm per variare i comportamenti del MI GIE.
	assert(stricomm_facts_path('./facts.pl')),
	assert(stricomm_writes_path('./writes.pl')), % path del file contenente l'ultima print effettuata durante l'ultimo processo inferenziale.
	start_count,gtrace,
	ie_dir(IE_dir),
	string_concat(IE_dir, '/stricomm/facts.pl', Loaderfacts_path), % siccome uso loaderfacts che é di GIE, sono costretto ad usare il path assoluto.
	loaderfacts(Loaderfacts_path),
	start_stricomm_common(IE_dir).

start_stricomm_seva :-
	absolute_file_name('', IE_dir), 
	string_concat(IE_dir, 'seva_start.pl', Seva_start_dir), 
	string_concat(IE_dir, 'gie/gie_forward.pl', Gie_fwd_dir),
	reconsult(Gie_fwd_dir), 
	reconsult(Seva_start_dir),
	assert(stricomm), % Asserisco l'uso in corso della comunicazione stricomm per variare i comportamenti del MI GIE.
	assert(stricomm_facts_path('./stricomm/facts.pl')),
	assert(stricomm_writes_path('./stricomm/writes.pl')), % path del file contenente l'ultima print effettuata durante l'ultimo processo inferenziale.
	start_count,
	string_concat(IE_dir, 'stricomm/facts.pl', Stricomm_facts_dir),
	loaderfacts(Stricomm_facts_dir),
	start_stricomm_common(IE_dir).

start_stricomm_common(IE_dir) :-
	writeln('path regole: '),
	% possibilitá opzionale di usare diversi file di regole sfruttando un fatto di tipo fase(X) e caricarne la kb. Ad esempio, avendo un file
	% contenente una kb denominato kb.pl allora se presente nella wm il fatto fase(kb) allora caricherá le regole contenute in tale file, altrimenti
	% userá il file rules.pl di default.
	((fact(_, fase(X), _),!, string_concat(X, '.pl', RulesFilename)) ; RulesFilename='rules.pl'), write('Fase: '), writeln(RulesFilename),
	string_concat(IE_dir, '/stricomm/', Stricomm_dir),
	string_concat(Stricomm_dir, RulesFilename, RuleAbsPath),
	loader(RuleAbsPath),
	%creo i predicati irule per ciascuna regola utili all'indicizzazione
	%initindexrules,
	initialrules, %creo le liste (iniziali e di servizio) con le regole presenti nella wm
	initialfacts, %creo le liste (iniziali e di servizio) con i fatti presenti nella wm
	init_trees, %con le regole presenti nella wm creo i nuovi alberi aventi la la radice con le condizioni di ogni regola
	%richiamo il gestore principale
	mainloop%gtrace,
	%retract_used_fact
	%findall(used_fact(X,Y,Z), used_fact(X,Y,Z), L), write_list_of_facts(L)
	%mainloop
	.