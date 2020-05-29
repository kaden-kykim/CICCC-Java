package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1;

import java.util.Random;

// this class CANNOT be seen outside the Lessions.Lesson1 package
class SomethingYouCantSeeOutsideThePackage extends Something {

}


// this class CAN be seen outside the Lessions.Lession1 package
public class VisibilityAndScopeExperiment extends Something {
    // There can only be ONE main PUBLIC class inside a java package
    // and it must match the name of the filename, otherwise, java cannot
    // export the object inside it and make it visible to other things!

    // this class is called our 'module'. Modules exist inside 'packages'
    // each folder inside the src folder is a 'package' and each .Java file
    // that exists inside of those folder's are Modules and each module
    // typically has at least ONE public class. This is what we use
    // with the 'package' and the 'import' keywords at the top of files


    // despite the name, the constructor here is VISIBLE, this means, we
    // are able to create the object because the constructor is visible
    // This code is the most visible when PUBLIC is used and can be used
    // anywhere
    public VisibilityAndScopeExperiment() {
        // TODO Auto-generated constructor stub
    }

    // PRIVATE makes sure that this method cant be seen outside of THIS class
    // this means, subclasses (classes that extend from this class) are unable
    // to use it, and outside of this package also, it can't be used. Its the
    // most restrive visiblity level
    private VisibilityAndScopeExperiment(boolean cantSeeMeOutsideThePackageOrSubClass) {
        // TODO Auto-generated constructor stub
    }

    // This hides the method from use outside the package, but allows use by
    // subclasses - so ancesters who use extends on this class can use it
    protected VisibilityAndScopeExperiment(int cantSeeMeOutsideThePackage) {
        // TODO Auto-generated constructor stub
    }

    // If I don't provide PUBLIC, PRIVATE or PROTECTED, then the default
    // is to just be available within the package, but no where else.
    VisibilityAndScopeExperiment(String defaultVisibility) {
        // TODO Auto-generated constructor stub
    }


    // Test can you see this from program?
    // Test can you see this from within the constructor?
    // Test can you see this in an inhertied class?
    // Test can you see this from within another class inside this package?
    // Can you access it through SomethingInvisible. (dot) <something>
    // Do you first have to create new SomethingInvisible().hideFromEveryone.....?
    private void hideFromEveryoneExceptThisClass() {

    }

    void onlyVisibleWithinThePackage() {

    }

    protected void allowAncesterClassesToSeeThis() {

    }

    public void anyoneCanUseThis() {

    }


    public class VisibleClass {

    }

    private class InvisibleClass {

    }

    protected class SemiVisibleClass {

    }

    final public class YouCantExtendFromThisClass {

    }

    final public void youCantOverrideThisMethod() {

    }

    static public void thisVesionIsStatic_NotAvailableInTheInstance() {

    }

    static private void thisVesionIsStaticToo_NotAvailableInTheInstance() {

    }

    public int aInteger = 1; // is public
    protected int anotherInteger = 2; // is semi public
    private int yetAnotherInt = 3; // is most private
    int intWithDefaultVisibility = 4; // is default

    final int aIntegerYouCantOverride = 1; // and has default visibility level

    static public int aStaticInteger = 1; // is public
    static protected int aStaticanotherInteger = 2; // is semi public
    static private int aStaticyetAnotherInt = 3; // is most private
    static int aStaticintWithDefaultVisibility = 4; // is default

    public void changeAValue() {
        System.out.println("Entering changeAValue inside " + this.getClass().getName());
        Random rnd = new Random();
        System.out.println("\tValue of aInteger: " + aInteger);
        this.aInteger = rnd.nextInt(1000);
        System.out.println("\tChanging it to random number:" + this.aInteger);
        System.out.println(this.getClass().getName() + " = " + aInteger);


        VisibilityAndScopeExperiment.aStaticInteger = 999;
        System.out.println("Leaving changeAValue inside " + this.getClass().getName());
    }


}
