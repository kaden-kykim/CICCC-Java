package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1;

public class SomethingElse extends Something {

    // here, we override the default constructor
    //    (notice we dont have to provide @override, it is 'implied' for us)
    //    (notice also we dont have to provide 'void' that is because the type is already defined for us'
    // Constructors are written by taking the 'Same Name' as the class like so
    public SomethingElse() {
        // do somethingElse
        System.out.println("SomethingElse Main Constructor Called");
    }

    // another important concept of Objects is the ability to 'overload'.

    /**
     * Overloading, is the ability, to provide different 'versions' of methods, like the constructor
     * for providing similiar, but slightly different behaviour, based on 'arguments'
     */

    // here, we create a constructor that requires a 'string' message
    public SomethingElse(String aStringMessage) {
        // do somethingElse with a paramater
        System.out.println("SomethingElse(String aStringMessage=" + aStringMessage + ")");
    }

    // here, we create a constructor that requires a 'string' message and we specifiy some fun options
    public SomethingElse(String aStringMessage, boolean showInLowerCase, boolean showInUpperCase) {
        // do somethingElse with a paramater
        // call the 'other' SomethingElse, so we dont have to repeat its code here
        this(aStringMessage);

        // now add our custom code based on the extra paramaters we added

        if (showInLowerCase) {
            System.out.println(aStringMessage.toLowerCase());
        }

        if (showInUpperCase) {
            System.out.println(aStringMessage.toUpperCase());
        }
    }

    /**
     * if I were to do this
     * SomethingElse something_big = new Something();
     *
     * the default constructor would be called
     *
     * if I were to do this
     *
     * SomethingElse something_big = new Something("Hello World");
     *
     * the default constructor is still called first, but then, our second constructor
     * is called that wants the string value
     *
     * and if I do this
     *
     * SomethingElse something_big = new Something("HelloWorld", true, false);
     * SomethingElse something_small = new Something("HelloWorld", false, true);
     * SomethingElse something_small = new Something("HelloWorld", true, true);
     *
     * again, will call the default constructor, then calls the constructor that
     * needs the bools and the string values, which prints the message in "lower case" or "upper case" if I so wish
     *
     * Java compiler will 'determine which method to call' based on the 'order of its paramaters'
     * and the datatype's of those paramaters.
     *
     */
}
