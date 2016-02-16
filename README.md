#Junction

###Overview
This is Junction, a functional programming language implemented in Java. 
The name is either a portmanteau of Java+Function, or Jayanth+Function. Or both.

###Language Features
Currently, Junction is heavily based on Scheme/LISP, and its syntax is essentially identical,
at least as of now. Functions are first class objects, and can be passed as parameters. 
Also, there is lexical closure - functions can be defined inside other functions, and their
'parent frame' is the frame in which they were defined.

###Current status
* Basic runtime environment
* Lexical analyzer
* Parser (still needs work)


###Source description
* jayanth/junction contains the source .java files

* bin contains the compiled .class files
