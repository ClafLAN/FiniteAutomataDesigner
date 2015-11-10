package edu.claflin.logic;

/**
 * Used to represent the Cartesian Product between types A and B.
 * The Cartesian Product is defined as the product of two sets, (A X B), and
 * is itself a set containing the tuples (a, b) where a is an element of A
 * and b is an element of B.
 * @param <A> the type A in the product.
 * @param <B> the type B in the product.
 */
public class Tuple<A, B> {
    /** The object A. */
    private final A a;
    /** The object B. */
    private final B b;

    /**
     * Constructs a tuple of type (A X B).
     * @param a a value from set A.
     * @param b a value from set B.
     */
    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    /** @return the 'a' value of this tuple. */
    public A getA() {
        return a;
    }

    /** @return the 'b' value of this tuple. */
    public B getB() {
        return b;
    }
    
}
