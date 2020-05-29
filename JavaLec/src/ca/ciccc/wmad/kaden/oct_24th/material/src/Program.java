package ca.ciccc.wmad.kaden.oct_24th.material.src;

import ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1.*;
import ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.AUniverseExperiment.Deity;
import ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.AUniverseExperiment.Universe;

public class Program {

    private static void LessionOneTests() {
        drawLine("#", 20);
        test_createNothing(); // see Nothing.java

        test_createSomething(); // see Something.java

        test_createSomething(3);

        test_createSomethingElse(); // see SomethingElse.java

        test_createSomethingElse(3);

        test_createSomethingElseWithParams();
        drawLine("#", 20);

        LookingAtVisibilityAndScope();
        drawLine("#", 20);
        /** CHALLANGE: Intermediate use of Abstractions and Generics - Deep Dive
         * This experiment will be quite confusing to new devs, study generics, interfaces to
         * understand whats going on
         */
        drawLine("#", 20);
        test_UseOfGenerics();
        drawLine("#", 20);


    }

    public static void main(String[] args) {

        //LessionOneTests();

        Universe aVerse = Deity.getInstance().CreateUniverse();

    }

    private static boolean test_createNothing() {
        System.out.println();
        // lets try and create NOTHING! from our abstract nothing class
        // Remove the comment from the line below and try and run, notice java wont compile!
        // public Nothing nothing = new Nothing();

        // (if you try this, will not work, class is ABSTRACT!)
        // so you need to comment it out
        return false; // just return false, to indicuate a failure state
    }

    private static void test_createSomething() {

        System.out.println("\ntest_createSomething is running:");

        // create a new Something, if it works, it won't be 'null' it will 'exist'
        // we use 'assert' which will check if that is the case, we use assert
        // when we are writing 'test' code (unit testing, integration testing, functional testing)
        Something aSomething = new Something();

        assert (aSomething != null); // assert will ONLY work if we turn on assertions, by default it is off
        System.out.println("\nPassed Test: \t[" + (aSomething != null) + "]\n");

        // but we will turn it on later

        // at some point in the future, java's garbage collector will clear it out after createSomething ends
    }

    private static void test_createSomething(int howMany) {
        drawLine("#", 20);
        System.out.println("\ntest_createSomething will be run:" + howMany + " times");
        // create a new Something
        int cnt = howMany; // lets count them down!
        do {
            // lets just call our createSomething() method over and over
            test_createSomething();
            cnt--; // count down by one
        } while (cnt > 0);
        drawLine("#", 20);
        // at some point in the future, java's garbage collector will clear it out after createSomething ends
    }

    private static void test_createSomethingElse() {
        System.out.println("\ntest_createSomething is running:");
        // create a new SomethingElse, if it works, it won't be 'null' it will 'exist'
        // we use 'assert' which will check if that is the case, we use assert
        // when we are writing 'test' code (unit testing, integration testing, functional testing)
        SomethingElse aSomethingElse = new SomethingElse();

        assert (aSomethingElse != null);
        System.out.println("\nPassed Test: \t[" + (aSomethingElse != null) + "]\n");
        // at some point in the future, java's garbage collector will clear it out after createSomething ends
    }

    private static void test_createSomethingElse(int howMany) {
        drawLine("#", 20);
        System.out.println("\ntest_createSomethingElse will be run:" + howMany + " times");
        int cnt = howMany; // lets count them down!
        do {
            // lets just call our createSomethingElse() method over and over
            test_createSomethingElse();
            cnt--; // count down by one
        } while (cnt > 0);
        drawLine("#", 20);
    }

    private static void test_createSomethingElseWithParams() {
        System.out.println("\test_createSomethingElseWithParams is running:");

        // lets create an Array of somethings this time,
        // NOTE: this line does NOT call the
        // constructor on somethingelse, it is NOT initialized, only the ARRAY [] part
        // gets initialized, we still have to 'intitialize each object' we plan to put
        // into the array

        // create the array large enough to hold the elements we plan to create
        SomethingElse[] arrayOfSomethingElse = new SomethingElse[5];

        // initialize each item and put it into the array, now the constructors are called
        arrayOfSomethingElse[0] = new SomethingElse("Hello World");
        // lets test the other possibilities for the various constructors
        arrayOfSomethingElse[1] = new SomethingElse("Hello World", false, false);
        arrayOfSomethingElse[2] = new SomethingElse("Hello World", true, false);
        arrayOfSomethingElse[3] = new SomethingElse("Hello World", false, true);
        arrayOfSomethingElse[4] = new SomethingElse("Hello World", true, true);

        // now, lets loop through our array and see if any of the values are null;
        // if they are null, that means one of the objects never got created
        // none of them should be null, they should 'contain the object instance'
        int testNum = 0;
        do {
            assert (arrayOfSomethingElse[testNum] != null);
            System.out.println("\nPassed Test " + testNum + ": \t[" + (arrayOfSomethingElse[testNum] != null) + "]\n");
            testNum++;
        } while (testNum < arrayOfSomethingElse.length);
    }


    static void test_UseOfGenerics() {
        drawLine("#", 20);
        System.out.println("\ntest_UseOfGenerics will be run:");
        // Here, I make a handy help class, I often create these as static members of
        // a class I am working on so I can run quick tests of code, then, I remove them
        // in the final version when they are no longer needed.
        // Writing test code as you go is good practice and a lost art!
        // Writing test code is also a good entry into software development! Q&A! DevOps, LiveOps
        // and a good way to join existing companies and grow into a job you want later.


        System.out.println("Creating SomeGeneric of type Integer");
        SomethingGeneric<Integer> pretendToBeAnInt = new SomethingGeneric<Integer>(1);
        System.out.println("Results:");
        System.out.println("\tValue is: " + pretendToBeAnInt);
        System.out.println("\tLooks Like: " + pretendToBeAnInt.toString());


        System.out.println("Creating SomeGeneric of type String, Bool and Character");
        // I do the same with String, Boolean and Characters, notice how the 'constructor' part changes to
        // reflect the 'datatype' given, this is because of how generics work.
        SomethingGeneric<String> pretendToBeAString = new SomethingGeneric<String>("Hello World");
        SomethingGeneric<Boolean> pretendToBeABool = new SomethingGeneric<Boolean>(true);
        SomethingGeneric<Character> pretendToBeAChar = new SomethingGeneric<Character>('A');
        System.out.println("Results:");
        System.out.println(pretendToBeAString);
        System.out.println(pretendToBeABool);
        System.out.println(pretendToBeAChar);


        System.out.println("Doing something funky with an abstract class!");
        // here, I can use a generic to create the 'abstract' class I could not before! and I can make it
        // 'derive from any datatype and any locally initialized variable' from any class I wish!
        SomethingGeneric<Nothing> pretendToBeNothingA = new SomethingGeneric<Nothing>(pretendToBeAChar);
        SomethingGeneric<Nothing> pretendToBeNothingB = new SomethingGeneric<Nothing>(pretendToBeAString);
        SomethingGeneric<Nothing> pretendToBeNothingC = new SomethingGeneric<Nothing>(pretendToBeABool);
        SomethingGeneric<SomethingElse> pretendToBeNothingD = new SomethingGeneric<SomethingElse>(pretendToBeAString);
        System.out.println("Results:");
        // pretendToBeNothing will be a Char here
        System.out.println(pretendToBeNothingA);
        System.out.println(pretendToBeNothingB);
        System.out.println(pretendToBeNothingC);
        System.out.println(pretendToBeNothingD);

        System.out.println("Create somethingFromNothing and somethingToNothing");
        // here I am doing some advanced kung fu with generics, can you explain/discover what is happening?
        SomethingGeneric<Nothing> somethingFromNothing = new SomethingGeneric<Nothing>(pretendToBeNothingA);
        SomethingGeneric<Something> somethingToNothing = new SomethingGeneric<Something>(pretendToBeNothingA);

        System.out.println("Results:");
        System.out.println(somethingFromNothing);
        System.out.println(somethingToNothing);

        System.out.println("Is somethingFromNothing EQUAL TO SomethingGeneric<Nothing>(pretendToBeNothingA); : " + somethingFromNothing.equals(pretendToBeNothingA));
        System.out.println("Is somethingToNothing EQUAL TO SomethingGeneric<Something>(pretendToBeNothingA); : " + somethingToNothing.equals(pretendToBeNothingA));
        System.out.println("Is somethingToNothing EQUAL TO SomethingGeneric<Something>(pretendToBeNothingA); : " + somethingToNothing.toString().toLowerCase().equals(somethingToNothing.toString().toLowerCase()));

        System.out.println("Confused yet? - dont worry!");
    }

    static public void LookingAtVisibilityAndScope() {
        // create TWO instances of SomethingInvisible
        VisibilityAndScopeExperiment objectA = new VisibilityAndScopeExperiment();
        VisibilityAndScopeExperiment objectB = new VisibilityAndScopeExperiment();

        // we access the STATIC objects through
        // SomethingInvisible.(dot)(something)
        // Static Objects exist ONCE in memory only and are shared
        // across all objects of that data type

        // Set a value
        VisibilityAndScopeExperiment.aStaticInteger = 2;
        System.out.println("SomethingInvisible.aStaticInteger = " + VisibilityAndScopeExperiment.aStaticInteger);
        // we access the INSTANCE objects through
        // objectA.(dot)(something)
        objectA.aInteger = VisibilityAndScopeExperiment.aStaticInteger;
        objectB.aInteger = VisibilityAndScopeExperiment.aStaticInteger;

        // atm all values are the same
        System.out.println("objectA.aInteger = " + objectA.aInteger);
        System.out.println("objectB.aInteger = " + objectB.aInteger);

        // lets change them to be different
        objectA.aInteger = 10;
        objectB.aInteger = 20;


        // what do they look like now?
        System.out.println("objectA.aInteger = " + objectA.aInteger);
        System.out.println("objectB.aInteger = " + objectB.aInteger);
        System.out.println("SomethingInvisible.aStaticInteger = " + VisibilityAndScopeExperiment.aStaticInteger);
        // notice that both 'objects' have different values for integer
        // lets call the objects instance method, changeAValue


        VisibilityAndScopeExperiment.aStaticInteger = 2;
        objectA.changeAValue();
        // Can you explain why 		TestSomethingInvisible.aStaticInteger is now 999?

        objectB.changeAValue();
        System.out.println("objectA.aInteger = " + objectA.aInteger);
        System.out.println("objectB.aInteger = " + objectB.aInteger);
        System.out.println("SomethingInvisible.aStaticInteger = " + VisibilityAndScopeExperiment.aStaticInteger);


    }


    private static void drawLine(String of, int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.print(of);
        }
        System.out.print("\n");
    }
}
