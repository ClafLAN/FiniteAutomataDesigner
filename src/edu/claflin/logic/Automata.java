package edu.claflin.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An automata object specification.  Used as the foundation class for all 
 * automata within the project.
 * @author CASII
 * @param <S> the type of States held in this automata.
 * @param <A> the type of Alphabets held in this automata.
 * @param <D> the type of the return from the Delta function
 */
public abstract class Automata<S, A, D> {
    
    /** The Alphabet symbol representing an empty character. */
    public static final Character ALPHABET_EPSILON = '\u03b5';
    /** The State representing the empty state. */
    public static final String STATE_EMPTY = "\u2205";
    
    /** The List containing the automata states. */
    protected final List<S> states;
    /** The List containing the automata alphabet. */
    protected final List<A> alphabet;
    /** The Transition function delta: (Q X E) -> Q */
    protected final Map<Tuple<S, A>, D> delta;
    /** The start state of the automata. */
    protected S startState;
    /** The final states in this automata. */
    protected final List<S> finalStates;
    
    /**
     * Constructs the Automata.
     * @param states the states within this automata.
     * @param alphabet the alphabet used by this automata.
     * @param delta the transition function for moving from state to state
     * @param startState the start state
     * @param finalStates the set of final states.
     */
    public Automata(List<S> states, List<A> alphabet, Map<Tuple<S, A>, D> delta,
            S startState, List<S> finalStates) {
        if (states == null || alphabet == null || delta == null ||
                startState == null || finalStates == null) {
            throw new IllegalArgumentException("ERROR: Constructing automata with null values.");
        }
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.delta = new HashMap<>();
        this.finalStates = new ArrayList<>();
        
        states.stream().forEach(s -> this.addState(s));
        alphabet.stream().forEach(a -> this.addAlphabet(a));
        delta.forEach((key, value) -> this.putDelta(key, value));
        if (states.isEmpty()) {
            throw new RuntimeException("Bad automata creation!");
        }
        this.setStartState(this.states.get(0)); // Set Default Start State
        this.setStartState(startState);
        finalStates.stream().forEach(f -> this.addFinalState(f));
    }
    /** @return the set of States */
    public final List<S> getStates() {
        return states;
    }
    /** @return the alphabet of this automata */
    public final List<A> getAlphabet() {
        return alphabet;
    }
    /** @return the transition function for this automata */
    public final Map<Tuple<S, A>, D> getDelta() {
        return delta;
    }
    /** @return the start state */
    public final S getStartState() {
        return startState;
    }
    /** @return the set of accept states */
    public final List<S> getAcceptStates() {
        return finalStates;
    }
    
    /**
     * Adds a state to the State list.
     * @param state the S state to add.
     * @return true if successfully added, false if already present.
     */
    public final boolean addState(S state) {
        if (!states.contains(state)) {
            states.add(state);
            return true;
        }
        return false;
    }
    /**
     * Removes a state from the state list.
     * @param state the S state to remove
     * @return true if successfully removed, false if startState or the only 
     * state in the automata.
     */
    public final boolean removeState(S state) {
        if (!states.contains(state) || states.size() == 1 || state.equals(startState)) {
            return false;
            // XXX - Maybe a better alternative than just returning false?
        } else {
            removeFinalState(state);
            states.remove(state);
            return true;
        }
    }
    
    /**
     * Adds an alphabet to the automata.
     * @param alpha the A alphabet to add.
     * @return true if successfully added, false if already present.
     */
    public final boolean addAlphabet(A alpha) {
        if (!alphabet.contains(alpha)) {
            alphabet.add(alpha);
            return true;
        }
        return false;
    }
    /**
     * Removes an alphabet from the automata.
     * Also removes all transitions in the delta that depend on this alphabet
     * @param alpha the A alphabet to remove.
     * @return true if successfully removed, false if not present.
     */
    public final boolean removeAlphabet(A alpha) {
        if (!alphabet.contains(alpha))
            return false;
        
        alphabet.remove(alpha);
        Set<Tuple<S, A>> keys = delta.keySet();
        for (Iterator<Tuple<S, A>> it = keys.iterator(); it.hasNext();) {
            Tuple<S, A> key = it.next();
            if (key.getB().equals(alpha)) {
                it.remove();
            }
        }
        
        return true;
    }
    
    /**
     * Puts a new transition in the delta.  Overrides any existing transition.
     * @param key the Tuple representing the transition to add.
     * @param value the D type representing the transition target.
     * @return true if successfully added, false if the tuple is invalid for 
     * this automata.
     */
    public final boolean putDelta(Tuple<S, A> key, D value) {
        if (states.contains(key.getA()) && alphabet.contains(key.getB()) &&
                validateTarget(value)) {
            delta.put(key, value);
            return true;
        }
        return false;
    }
    /**
     * Removes a transition from the delta if present.
     * @param key the Tuple representing the transition to remove.
     */
    public final void removeDelta(Tuple<S, A> key) {
        delta.remove(key);
    }

    /**
     * Sets the start state of the automata.
     * @param state the S state to set as start.
     * @return true if successfully set, false if state is not present
     */
    public final boolean setStartState(S state) {
        if (!states.contains(state))
            return false;
        
        startState = state;
        return true;
    }
    
    /**
     * Adds a final state to the automata.
     * @param state the S state to make final.
     * @return true if successfully set as final, false if the state is not 
     * present in the automata.
     */
    public final boolean addFinalState(S state) {
        if (!states.contains(state))
            return false;
        
        finalStates.add(state);
        return true;
    }
    /**
     * Removes a state from the final set.
     * @param state the S state to remove from the final set.
     * @return true if successfully removed, false if the state was not in the 
     * final set.
     */
    public final boolean removeFinalState(S state) {
        if (!finalStates.contains(state))
            return false;
        
        finalStates.remove(state);
        return true;
    }
    
    /**
     * Implemented by subclasses to validate if the D represents an appropriate 
     * target for a transition based on that automata's execution method.
     * @param target the D target to validate
     * @return true if valid, false otherwise
     */
    protected abstract boolean validateTarget(D target);
}
