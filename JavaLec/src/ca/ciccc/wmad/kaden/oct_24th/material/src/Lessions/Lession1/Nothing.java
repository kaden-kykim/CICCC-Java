package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1;

/**
 * When we learn a programing language, most people start from the 'Top' and work
 * their way down getting more detailed as they go on.
 * <p>
 * Also, the first time you learn 'programming' you are not 'learning to code' you
 * are actually learning to 'think' and 'problem solve' but express that 'solution'
 * in the 'language' we are learning. So we actually have alot to learn, not just
 * programming.
 * <p>
 * This is why the top down method is not for everyone and also results in 'bad programmers'
 * for the most part.
 * <p>
 * Top down teaching allows students to construct applications almost straight away
 * but while this is fun it doesn't give give them alot of knowledge on how things
 * work under the hood. The simple truth is, if you learn from the bottom up you can
 * work with more things and be more versitile more quickly typcially that someone
 * who starts from a top down approach. Top down approaches are good for udemy, youtube
 * stackoverflow and so on, but not for creating realy good programmers and engineers
 * <p>
 * With some practice, careful attention to details, it will just come. Trust me.
 * <p>
 * Here, from the bottom up, I will assume 'nothing' and build everything.
 * <p>
 * In truth, there is only one way to learn something correctly and that is to start
 * from the 'bottom' and go up. At each level we look left and right, then take a step up
 * and continue that. :D
 * <p>
 * So that is what we are going to do.
 * <p>
 * We are going to start with 'Nothing' and then look at 'Nothing' and then build
 * 'Something' from 'Nothing'. Then we will build SomethingElse and in doing so
 * get to know, intimatly, what a javascript object is, through experimentation.
 * <p>
 * We will teach patterns and testing and debugging at the very same time.
 */


/*
 * 1.1 Introduction
  	Java programming language, originated in Sun Microsystems and released back in 1995, 
  	is one of the most widely used programming languages in the world,
  	 It is attractive to software developers primarily due to its powerful library and runtime, 
  	 simple syntax (compaired to other languages), rich set of supported platforms (Write Once, Run Anywhere - WORA) 
  	 and awesome community. 
  	 
  	 In this series we are going to cover advanced Java concepts, the book starts by assuming that our readers 
  	 already have some basic knowledge of the language. But it is not necessary as we will build up in pieces by example. 
  	 
  	 It is by no means a complete reference, rather a detailed guide to move your Java skills to the next level. 
  	 
  	 Along the course, there will be a lot of code to look at. 
  
     But we will learn by example - which means we do not read - we do!

 */


// in the beginning, there was nothing
public abstract class Nothing {
    // This class has no code, but it has stuff 'built in' that Java
    // already provides, one of these things is called a 'Constructor'.
    // A Constructor is code that is executed only once when an 'instance' or
    // version of your object is created by java in memory, the constructor is
    // usually responsible for 'setting things up'.

    /**
     * Java allows to define a class without any constructors but it does not mean
     * the class will not have any. They all have them.
     *
     * For example, in this class, the 'Nothing' class
     *
     *                    public abstract class Nothing {} // has no code
     *
     * This class has no constructor code but Java compiler will generate one
     * 'implicitly' and the creation of new class 'instances' will be possible
     * using 'new' keyword.
     *
     * Every 'Object' therefore in Java, has a constructor - even if you never see
     * it and every time you make an object, that objects constructor is called.
     *
     * char, int, float, double, String, byte, all have constructors also but these
     * are special cases, we do not need to define them with the new keyword.
     *
     * int x = 1
     * is the same as writing
     * int x = new int(1)
     *
     * which means, the 'new' creates room in memory to store an object of type
     * int then it calls the ints constructor and gives it the value 1
     * then the int's constructor 'saves' 1 to memory and stores it. Now the object
     * is ready to be used.
     *
     * With our Nothing class, the same kind of behaviour is going on.
     *
     * This is called the 'Constructor' pattern.
     *
     */
}

// Here we have an abstract class and we called it Nothing.
// It doesn't do anything for us, but that doesn't mean it is doing 'nothing' at
// all, as we say above, 'stuff did happen' but we dont care what that 'stuff was'. 
// java takes care of it for us, We just need to remember that it did happen, and 
// just need to use it. 

// The class 'Nothing' itself, It is just an 'idea' that represents something. 
// An Idea. Objects and Classes in Java and all programing languages just represent
// an 'Idea'. In this case Nothing class is empty,  has no code, no constructor 
// and just represents an empty java object that does 'Nothing Interesting'. 
//
// Because we mark it as 'abstract' we can't actually use it directly,
// but we can use it to 'build stuff' from.

// ABSTRACT is a word that means 'this is an abstraction, you need to build something
// from it and not use it directly, java wont let us use it directly. 
// 
// A class is 'just an object' in Java. Everything inside of Java is considered
// to be an 'Object'. What is an Object? It is just a way to group code in such
// a way as to make it 'easier' for us to manage. We as humans are more able to
// deal with Objects as abstractions for complex things like 
// binary 		0101010101010101010101
// or hex		FF00 AA03 3300 FFFF FABA
// or assmbly	MOV	CL, 10
//				L1:
//				<LOOP-BODY>
//				DEC	CL
//				JNZ	L1
// The closer we are 'to the machine' the closer we are to binary and the more
// difficult and complex things come, we lose sign of the bigger picture easily
// and bugs are harder find. 
// binary is too difficult for most ppl
// so asm converts things like MOV to 0010 which the CPU understands, thats an abstraction
// Java just formalizes the rules with behaviours and patterns, thats all any 'language' does

/*
 * Java is an object-oriented language and as such the creation of a new class
 * instances(objects) is, probably, the most important concept of it.
 *
 * To explore these concepts, we are going to start by implementing a very
 * basic pattern in computer engineering.
 *
 * A pattern is mearly just an 'idea' on how we can approach solving problems
 * and as programmers, that is what we do, we implement solutions for problems
 * using patterns (called algorythms) that we create.
 *
 * To explore these idea's, lets do something useful. Goto 'Something.java'
 *
 * The pattern we will implement is the Creation or Factory patterns and
 * we will use it to test our ideas and also to begin our first 'testing'
 *

 */

/**
 * Move on to Something.java
 */


