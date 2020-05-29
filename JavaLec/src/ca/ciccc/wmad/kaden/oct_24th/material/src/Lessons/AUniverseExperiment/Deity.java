package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.AUniverseExperiment;

import java.util.ArrayList;
import java.util.Random;

/**
 * In the beginning, the only 'thing' that really existed was an
 * all powerful Deity
 * <p>
 * He is known by many names - really, he is abstract.
 * <p>
 * There is only one of him. He can exist in many places but really
 * no matter which 'version' of him you talk to, or from where, its
 * always the same guy.
 * <p>
 * He is a Singleton and he extends from TheVoid
 */

/**
 * Sometimes it�s important for some classes to have exactly one instance. 
 *
 * There are many objects we only need one instance of them and if we instantiate 
 * more than one, we�ll run into all sorts of problems like incorrect program 
 * behavior, overuse of resources, or inconsistent results.
 *
 * You may require only one object of a class, for example, when you are a 
 * creating the context of an application, or a thread manageable pool, 
 * registry settings, a driver to connect to the input or output console etc. 
 *
 * More than one object of that type clearly will cause inconsistency to your program.
 * (ie: two keyboards, two mice)
 *
 * The Singleton Pattern ensures that a class has only one instance, and provides
 *  a global point of access to it. However, although the Singleton is the 
 *  simplest in terms of its class diagram because there is only one single class, 
 *  its implementation is a bit trickier.
 *
 *  Patterns are not 'rules' - they are 'patterns' and they are implemented 
 *  depending on the requirements of the program. 
 *
 *
 */

// every time an Object is created by the 'new' keyword, that object gets an
// 'instance' (a copy of itself in memory that is independent from the others). 
// This instance is saved to a variable called 'instance'.
//
// To implement the Singleton patterns, we just need to make sure that the instance
// only exists once, so, 
public final class Deity extends TheVoid {
    private static Deity instance;
    ArrayList<Universe> universes = new ArrayList<Universe>();

    // here, we make the Constructor is 'private'.
    // this ensures that no one is able to call the 'new Deity()' by hand
    private Deity() {
    }


    public Universe CreateUniverse() {
        System.out.println("Get ready, here comes the big bang!");
        Universe aNewUniverse = new Universe(new Random().nextInt(Integer.MAX_VALUE));


        universes.add(aNewUniverse);
        return aNewUniverse;
    }

    public boolean[] CreateUniverses(int howManyUniverses) {
        System.out.println("ALL HAIL THE ALLMIGHTY DEITY, PROGRAMMER BE THY NAME!");
        boolean[] successes = new boolean[howManyUniverses];
        int done = 0;
        do {
            successes[done] = (CreateUniverse() != null);
        } while (successes.length < howManyUniverses);
        return successes;
    }

    // here, we implement our 'version' of the getInstance() method, and in it
    // we implement the singleton patterns
    public static Deity getInstance() {
        // this is the Pattern

        // if 'instance' doesn't exist (is null) then create a new Instance
        // and save it
        //
        // The first time we create the Deity class, instance WILL be null, so
        // we allow the class to be created and do instance = new Deity();

        if (instance == null) {
            instance = new Deity();
            System.out.println("An all powerful Deity just popped into existence: " + instance.getClass().getName().toString());

        }
        // HOWEVER
        // if the instance is NOT null, ie: it MUST have therefore already
        // have been created, so, instead of creating a new instance of Deity
        // we instance RETURN the previous instance. Thereby, helping to ensure
        // that only one version exists. 
        //
        // The Singleton behalves a little like STATIC classes, methods etc except
        // that with static, they are NOT instance'd, but with the Singleton it IS
        // instanced, but, there is only one instance allowed. 
        return instance;
    }


}



