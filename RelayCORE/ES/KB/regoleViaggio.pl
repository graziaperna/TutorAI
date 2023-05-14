fact(1, attraction(david), 1).
fact(2, attraction(colosseo), 1).
fact(3, relatedTo(david, rinascimento), 1).
fact(4, relatedTo(colosseo, rinascimento), 1).

rule(5, allRinascimento(A), and([relatedTo(A, rinascimento), attraction(A)]), 1000, 1).
