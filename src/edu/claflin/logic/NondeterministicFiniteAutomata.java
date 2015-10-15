package edu.claflin.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Non-Deterministic Finite Automata in memory. 
 */
public class NondeterministicFiniteAutomata extends Automata<String, Character> {
    
    /** The Alphabet symbol representing an empty character. */
    public static final Character ALPHABET_EPSILON = '\u03b5';

    /**
     * Constructs the Nondeterministic Automata.
     * @param states the List of states.
     * @param alphabet the List of alphabet
     * @param delta the transition map.
     * @param startState the start state.
     * @param finalStates the final states.
     */
    protected NondeterministicFiniteAutomata(List<String> states,
            List<Character> alphabet, Map<Tuple<String, Character>,
                    String> delta, String startState, List<String> finalStates) {
        super(states, alphabet, delta, startState, finalStates);
    }
    
    /**
     * Creates a new Non-deterministic Automata with default state.
     * @return the new NondeterministicFiniteAutomata.
     */
    public static NondeterministicFiniteAutomata newAutomata() {
        List<String> states = new ArrayList<>();
        states.add("START");
        List<Character> alphabet = new ArrayList<>();
        Map<Tuple<String, Character>, String> delta = new HashMap<>();
        String startState = "START";
        List<String> finalStates = new ArrayList<>();
        
        return new NondeterministicFiniteAutomata(states, alphabet, delta, startState, finalStates);
    }
    
    /**
     * Creates a new NFA using the supplied informations.
     * @param states the List of states.
     * @param alphabet the List of alphabet
     * @param delta the transition map
     * @param startState the start state
     * @param finalStates the final states.
     * @return the new NondeterministicFiniteAutomata.
     */
    public static NondeterministicFiniteAutomata buildAutomata(
            List<String> states, List<Character> alphabet,
            Map<Tuple<String, Character>, String> delta, String startState,
            List<String> finalStates) {
        if (startState == null || (!states.contains(startState) || (!states.containsAll(finalStates)))) {
            throw new IllegalArgumentException("ERROR: Bad start or final states.");
        }
        
        return new NondeterministicFiniteAutomata(states, alphabet, delta, startState, finalStates);
    }
}