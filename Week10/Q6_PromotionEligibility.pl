experience(ravi, 6).
training(ravi, completed).
rating(ravi, 5).

experience(priya, 3).
training(priya, completed).
rating(priya, 4).

experience(kumar, 8).
training(kumar, not_completed).
rating(kumar, 5).

experience(meena, 7).
training(meena, completed).
rating(meena, 4).

eligible(X) :-
    experience(X, Y),
    Y >= 5,
    training(X, completed),
    rating(X, R),
    R >= 4.