package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessions.Lession1;

import java.util.Comparator;
import java.util.Iterator;

// This class has being designed to be very confusing!
// when you create it - it 'hides' the original data type, then
// overrides various methods that belong to Object and when asked
// for that data so it provides alternatives , returns data from the hidden object instead
// This is polymorphism and generics and its use can become pretty advanced. 
public class SomethingGeneric<T> extends SomethingElse
        // see java: interfaces
        implements Comparator<T> {

    protected T data;


    public SomethingGeneric(T someObject) {
        System.out.print("SomethingGeneric.Constructor(T someObject = " + someObject.getClass().toString() + ")");
        data = someObject;
    }


    public void SetData(T data) {
        this.data = data;
    }

    public T GetData() {
        return this.data;
    }

    // compare is a method that I override that exists in Object
    // It helps developers to compare to 'like' classes, especially in use
    // with numbers (such as is 1 (as an int) the same as 1 (as a float)
    // or if 1 is the same as "1" or "one". Here, I redirect the request for
    // my class to that of the data class
    @Override
    public int compare(T o1, T o2) {
        // TODO Auto-generated method stub
        return data.getClass().toString().compareTo(o2.getClass().toString());
    }


    // here, I override the equals method (which is not the same as ==)
    // I use this to make my 'data' look like the other 'data'.
    // but my 'position in memory' is still different to that of 'data' so
    // the == operator won't be fooled, since it compares if two objects exist
    // at the same memory location, not if their data is the same (like .equals() does)
    @Override
    public boolean equals(Object object) {
        return (object == data);
    }

    @Override
    public void finalize() {
        System.out.println("Destroying the generic type masqerating as a " + this.data.getClass().toString());
    }

    // this fools most of java into thinking that this class is of the type of some other
    // many things call SomeObject.toString() to find out if something is simimilar or the same to something else,
    // so we think like a hacker, override it, and return the copy from our data instead
    @Override
    public String toString() {
        return data.toString();
    }

    // we also override hashCode, hashCode contains a unique hash for our class instance, so we don't 'reveal' what our
    // real hash is, instead, we return the data.hasCode() instead
    @Override
    public int hashCode() {
        return data.hashCode();
    }

}
