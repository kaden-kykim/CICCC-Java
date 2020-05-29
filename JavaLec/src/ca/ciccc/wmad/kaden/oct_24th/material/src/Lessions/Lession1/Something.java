package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1;

/**
 * We started out with 'Nothing' in Nothing.java
 * <p>
 * An Abstract Class that represented 'Nothing Interesting' inside of Nothing.java.
 * <p>
 * Nothing is a Java Object. It doesn't do anything - but it does have already things
 * like 'properties' and 'methods'. Stuff is doing on. We saw for example that it has
 * a 'Constructor'. Which is just a method, like our main function, that is called
 * whenever an object is created by java. A constructor acts like the main function
 * of our Program except each object has its own main function.
 * <p>
 * An object in Java (and any language) has what we call a 'Life Cycle'.
 * <p>
 * A 'Life Cycle' is the sequence of events that happens during the 'lifetime' of
 * an object's existence in memory. We should get to know the Lifecycle of Java objects
 * or any language we wish to study. We are essentially 'inserting our code' into different
 * points in the 'lifecycle' between its creation and destructor.
 * <p>
 * The life cycle begins when we create an object, for example
 * <p>
 * int --> this is the data type
 * int x --> we give the data type a name, in this case x
 * (after this, the int life cycle begins)
 * new keyword is called by java
 * space is made in memory for an object of type int
 * the ints constructor is called and any arguments are passed to it if exists (in this case none)
 * Constructor Runs
 * See that int x is the default value (we didn't give x a value when we declared it)
 * gives X the default value of 0
 * exits
 * <p>
 * (now our code continues)
 * <p>
 * x++ --> increase the variable by 1
 * the variables data changes and control is returned to our code
 * <p>
 * (now our code continues)
 * <p>
 * x-- --> decrease the variable by 1
 * the variables data changes and control is returned to our code
 * (now our code continues)
 * <p>
 * (end of function)
 * <p>
 * When our program ends, what happens to int x?
 * - well, its 'Destructor' is called by the java 'garbage collector'
 * which runs every now and then to 'clear' the memory that is no longer needed
 * anymore.
 * <p>
 * So an object goes through the follows steps for everything inside of java, even if
 * we explicitly wrote it or not.
 * <p>
 * declare an object
 * create the object
 * LIFE CYCLE BEGINS
 * CONSTRUCTOR IS CALLED
 * our code runs as normal
 * DESTRUCTOR IS CALLED by java garbage collector later
 * <p>
 * In languages like C and C++ when we declare an object we are also responsible
 * for destroying the object later when it is no longer needed so that the memory
 * is freed up for other programs to use, but in Java and other modern object orientated
 * languages, this task is taken over by a special program called the 'Garbage Collector'
 * that does this for us, this is because, programers would often 'forget' to free the
 * memory correctly.
 * <p>
 * So, everything in Java is an Object right? and they all have Constructors and they
 * all have Destructors right? - so what? - well, this is pretty good - because it means
 * that we know how every object 'should' behave and so we know what the 'pattern' is.
 * <p>
 * so, int, char, String, ArrayList, Random and many other things are all 'Objects'
 * inside of java.
 * <p>
 * if I type
 * <p>
 * String myName = "Ivan";
 * <p>
 * we see that String is the data type and myName is the variable name
 * we know that String's constructor is called around the time of assignment =  the value "Ivan"
 * then gets 'saved'
 * from then on we can use myName to access that 'data'
 * <p>
 * How and what specifically the constructor is doing, we dont care, we just know it happened.
 * <p>
 * String lastName = "Malone";
 * constructor is called, "Malone" is passed into it
 * gets saved
 * back to our program
 * ...
 * ...
 * but, that is not the 'only' object we see here. Depending on how much details
 * we 'want to know' we can zoom further, and further, the more you look the deeper
 * the rabbit hole goes, so don't delve to deep or you'll get lost!
 * <p>
 * "Ivan" and "Malone" are also objects.
 * <p>
 * for example;
 * <p>
 * String myName = "Ivan".toLowerCase(); // demonstracte calling toLowerCase on a string object
 * <p>
 * whats really going on here?
 * its more complex even that I explained before!
 * <p>
 * "Ivan" is a string type, so Java compiler creates an object for it in memory (we just dont have a variable name for it)
 * a String is created by java compiler for us
 * its constructor called,
 * "Ivan" is passed into it
 * the string that was created (the one we dont have a variable name for).toLowerCase(); is called next
 * toLowerCase(); looks at the saved data "Ivan" and changes it to "ivan"
 * now we have a string type in memory that is lower case
 * = means assignment, so java takes that 'saved string object with no variable name'
 * and first, creates a new String object
 * it calls it myName
 * it calls its constructor
 * it takes the data that = saved and passes it to the constuctor for myName
 * myName saves the data
 * return to our program
 * <p>
 * so all of that stuff is going on in the background, because all objects in Java
 * follow the 'Creational' pattern where every object's constructor is called by
 * the java runtime environment.
 * <p>
 * This is the same as every operating system calling the main function of a program
 * you just started.
 * <p>
 * We can zoom down even further but we dont need to, it gets complex and confusing
 * but all we need to know about it, is 'the pattern'. The details are not important
 * just to use them, ie: we dont need to know HOW exactly the constructor is doing its job
 * or even what the job is doing, we just need to know that the constructor is called
 * always and that its job is to set things up for us. And if it isn't called somewhere
 * in the 'chain of things happening' we will end up with a 'null' that probably crash
 * or cause an errors. So things are 'tightly glued together' in such a way that things
 * 'ebb and flow' from 'parent to child and from child to parent' and that java's
 * virtual machine makes sure that these 'patterns' are used all the time. We follow
 * those rules.
 * <p>
 * end of program
 * <p>
 * GARBAGE COLLECTOR will free the available memory for us
 * <p>
 * Another important aspect of Objects is 'Inheritence'
 * <p>
 * Inheritence is the 'pattern' which allows us to create 'new objects' that do
 * stuff we want it to do.
 * <p>
 * When the creators of java created the first 'object' then then 'derived' new
 * objects from that, such as int, char, String and so on.
 * <p>
 * We use that very same mechanism to create our own objects and this creates a
 * kind of 'Parent-->Child' relationship between objects.
 * <p>
 * For example;
 */
class NothingWithAConstructor extends Nothing { // here we see, that by using the extends keyword
    // we create a class that 'inherits' from 'Nothing'
    // The 'Parent' of the NothingWithAConstructor class is 'Nothing'
    // and the Parent of the 'Nothing' class is 'Object'

    /**
     * This implies then that
     * <p>
     * boolean --> parent is object
     * byte --> parent is an object
     * short --> parent is object
     * char --> parent is object
     * String --> is a class that also inherits from object but includes
     * code to manage an array of chars, which, gives us more complex
     * behaviour that we know of as a String
     * <p>
     * Somewhere down the line, all object's 'Grandparents, or Great Grand Parent'
     * will always resolve back to Object. Because Object has Constructors, Properties
     * and Methods, all objects in Java will have those also and we just need to build
     * new stuff on top of them.
     * <p>
     * that is what we did with
     * <p>
     * class Something extends Nothing {}
     * <p>
     * we created a class that builds upon the abstract class of Nothing and because
     * we 'dont' use the 'abstract' keyword, this lets us actually do something with it
     */


    // Here, this is where we 'override' the 'parents' constructor with a 'constructor'
    // of our own. This means that, when this object is created, we get an oppertunity to
    // do something, such as, set some things up if we need to.
    public NothingWithAConstructor() {
        super(); // here, is a special keyword, that I typed 'manually' just so you can see
        /**
         * super() is a method that is part Object, which means, it is available for use
         * on every single Object we create, what it means here, is,
         *     call the parent's constructor before running my constructor
         *
         * when we NothingWithAConstructor in memory with
         *
         * NothingWithAConstructor nothing = new NothingWithAConstructor();
         *
         * space is made in memory for NothingWithAConstructor
         * 	the name nothing is assigned to it so we can access this 'instance' of the object
         *  	java follows the extends keyword back down to the root level first
         *  		all the way to object and creates all the objects from the bottom up
         *  		according to its 'pattern of behaviour'
         *  		Object.Constructor() is called
         *  			Nothing.Constructor() is called
         *  				NothingWithAConstructor().Constructor() is called
         *
         *  When making a 'Constructor' as we have done here, we do not need to
         *  include the word 'super' because java does it automaticly for us
         *  but it is just important to remember that super means, we are telling
         *  java to talk to the 'parent' of the class, that which we are 'derived from'
         *  and not 'this', the class we are actually coding for. This will become useful
         *  later.
         *
         *
         */
        System.out.println("This class does nothing except print this message");

    }
}


/**
 * Lets do something useful, lets create 'Something'
 * <p>
 * We will do this by creating a class that inherits from Nothing
 * <p>
 * I do this just to show the concept of inheritence but when you make your own
 * classes you will either write it like this;
 * <p>
 * class Something {.....}
 * or
 * class Something extends SomethingElse {......}
 * <p>
 * but even when we dont type extends, it always extends from Object anyway
 */

// Create Something from Nothing AND make sure it is public so it can be used
// outside of this 'file'
public class Something extends Nothing {
    // here we choose to override the 'Constructor' so that we may provide
    // our own code, the parents constructor is 'still called' before our
    // code executes code, this is good, because this means we can build
    // objects in layers as we are about to do here
    // Layer 0: Object - provided by java
    // Layer 1: Nothing - which extends from Object by default
    // Layer 2: Something - which extends from Nothing and we override the constructor
    // Layer 3: <later>
    // etc etc etc

    public Something() {
        // here, we override the constructor and we print a message to let us know when
        // the constructor happened
        System.out.println("Something Constructor was called");
    }

    // Java 'technically' doesn't expose 'Destructors' like other languages do, because
    // languages that have 'garbage collectors' have their own methods of dealing with and
    // freeing memory. The closet we get is the Finalize method. We cannot predict when java's
    // garbage collector will 'jump in and free memory we no longer need' so we tend not to
    // worry about it and never implement them, but now and then, we need to do something when
    // an object is being destroyed (such as release a network connection) and if we do need
    // to do that we can 'override' another method provided by java's scripts Object type.

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//    }


	@Override // this means, the following function will override a method that already
			  // exists in the parent class - we can only override methods that already
	          // exist and were designed to be overrided in ancester (child) classes like this one
	public void finalize() {
		System.out.println("Something Finalize called, an object is about to be destroyed");
		System.out.println("- We see this method because during 'destruction' it was called by whatever was"
				+ " doing the 'destruction for us'");
		}
}

/**
 * Move on to SomethingElse.java
 */
