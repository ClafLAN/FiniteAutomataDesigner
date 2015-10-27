package edu.claflin.logic;

import java.util.List;
import java.util.Map;

/**
 * An automata object specification.  Used as the foundation class for all 
 * automata within the project.
 * @author Charles Allen Schultz II
 * @param <S> the type of States held in this automata.
 * @param <A> the type of Alphabets held in this automata.
 */
public abstract class Automata<S, A> {
    
    /** The State representing the empty state. */
    public static final String STATE_EMPTY = "\u2205";
    
    /** The List<S> containing the automata states. */
    protected final List<S> states;
    protected final List<A> alphabet;
    protected final Map<Tuple<S, A>, List<S>> delta;
    protected S startState;
    protected final List<S> finalStates;
    
    /**
     * Constructs the Automata.
     * @param states the states within this automata.
     * @param alphabet the alphabet used by this automata.
     * @param delta the transition function for moving from state to state
     * @param startState the start state
     * @param finalStates the set of final states.
     */
    public Automata(List<S> states, List<A> alphabet, Map<Tuple<S, A>, List<S>> delta,
            S startState, List<S> finalStates) {
        if (states == null || alphabet == null || delta == null ||
                startState == null || finalStates == null) {
            throw new IllegalArgumentException("ERROR: Constructing automata with null values.");
        }
        this.states = states;
        this.alphabet = alphabet;
        this.delta = delta;
        this.startState = startState;
        this.finalStates = finalStates;
    }
    /** @return the set of States */
    public List<S> getStates() {
        return states;
    }
    /** @return the alphabet of this automata */
    public List<A> getAlphabet() {
        return alphabet;
    }
    /** @return the transition function for this automata */
    public Map<Tuple<S, A>, List<S>> getDelta() {
        return delta;
    }
    /** @return the start state */
    public S getStartState() {
        return startState;
    }
    /** @return the set of accept states */
    public List<S> getAcceptStates() {
        return finalStates;
    }
    
    public boolean addState(S state) {
        if (!states.contains(state)) {
            states.add(state);
            return true;
        }
        return false;
    }
    public boolean removeState(S state) {
        if (!states.contains(state) || states.size() == 1) {
            return false;
            // XXX - Maybe a better alternative than just returning false?
        }
    }

    /**
     * Used to represent the Cartesian Product between types A and B.
     * The Cartesian Product is defined as the product of two sets, (A X B), and
     * is itself a set containing the tuples (a, b) where a is an element of A 
     * and b is an element of B.
     * @param <A> the type A in the product.
     * @param <B> the type B in the product.
     */
    public static class Tuple<A, B> {

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
}
