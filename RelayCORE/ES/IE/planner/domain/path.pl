/*
Find a path from one place to another.
Only one operator: go.
*/

connected(a,b,5).
connected(a,e,8).
connected(a,f,3).
connected(b,c,6).
connected(b,d,7).
connected(b,g,2).
connected(b,f,4).
connected(c,d,11).
connected(c,k,8).
connected(d,l,4).
connected(d,n,5).
connected(e,d,15).
connected(e,f,2).
connected(e,h,5).
connected(g,f,2).
connected(g,c,9).
connected(h,i,7).
connected(j,k,10).
connected(n,m,2).

plan_operator(go(X,Y),
    [at(X)],
	[at(Y)],
	[at(X)],
	Cost) :- 
	connected(X, Y, Cost).