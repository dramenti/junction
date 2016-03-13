#Junction

###Overview
This is Junction, a functional programming language implemented in Java. 
The name is either a portmanteau of Java+Function, or Jayanth+Function. Or both.

###Language Features
Currently, Junction is heavily based on Scheme/LISP, and its syntax is essentially identical,
at least as of now. Functions are first class objects, and can be passed as parameters. 
Also, there is lexical closure - functions can be defined inside other functions, and their
'parent frame' is the frame in which they were defined.
Tail call optimization is implemented, though not in the normal way. Ordinary functions defined with `def` are not optimized.
Creation of a tail call optimized function requires using `iter`. This does not optimize mutual recursion.

###Example Code
An example of lambdas (the actual lambda symbol 'λ' is supported!)

    ((def (f g) (+ (g 10) 4) ) (λ (u) (* (+ u 1) u)))

This evaluates to (10+1)\*(10) + 4 = 114

Lists are now a thing

    (get 3 (list 1 2 3 4 1000)) 

The `list` function constructs a new list, and `get` retrieves the value at the ith index. Thus, the above line would evaluate to 4.

Tail call optimization is here, though perhaps in a different guise than in proper Lisp or other functional languages. Here is iterative factorial.

    (iter (factorial n i prod) (> i n) (n (+ i 1) (* prod i)) prod)

An `iter` is a special type of function that is designed for iteration. Creating an iterative function: `(iter (name <params>) <until_cond> (<rebinds>) <base>)`. In essence, an `iter` is sort of (in fact, it literally is!) a "while loop" wrapper rather than a "true" tail recursive function. 

###Current status
* Basic runtime environment
* Lexical analyzer
* Parser
* Lambdas 
* Iteration


###Source description
* jayanth/junction contains the source .java files

* bin contains the compiled .class files
