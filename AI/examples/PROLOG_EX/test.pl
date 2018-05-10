parent(dan,jim).
parent(lily,jim).
parent(dan,tommy).
parent(lily,tommy).
sibling(X,Y):-
        parent(Z,X),
        parent(Z,Y),
	X\=Y.

dog(spot).
hastail(X):-
	dog(X).

