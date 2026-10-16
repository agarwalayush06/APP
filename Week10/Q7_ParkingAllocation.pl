slot(s1, car, available).
slot(s2, bike, occupied).
slot(s3, car, available).
slot(s4, bike, available).
slot(s5, car, occupied).

available_slot(S, V) :-
    slot(S, V, available).