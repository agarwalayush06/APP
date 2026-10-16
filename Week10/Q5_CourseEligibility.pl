python_pass(arun).
ds_pass(arun).

python_pass(bala).

python_pass(charan).
ds_pass(charan).

ds_pass(divya).

eligible(X) :-
    python_pass(X),
    ds_pass(X).