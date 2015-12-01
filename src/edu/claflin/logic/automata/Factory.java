package edu.claflin.logic.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class for creating Automata objects.
 * @author CASII
 */
public final class Factory {
    
    public static final DeterministicFiniteAutomata newDFA() {
        List<String> states = new ArrayList<>();
        states.add("START");
        List<Character> alphabet = new ArrayList<>();
        Map<Tuple<String, Character>, List<String>> delta = new HashMap<>();
        String startState = "START";
        List<String> finalStates = new ArrayList<>();
        
        return new DeterministicFiniteAutomata(states, alphabet, delta, startState, finalStates);
    }
    public static final DeterministicFiniteAutomata buildDFA(List<String> states,
            List<Character> alphabet, Map<Tuple<String, Character>, List<String>> delta,
            String startState, List<String> finalStates) {
        if (startState == null || (!states.contains(startState) || (!states.containsAll(finalStates)))) {
            throw new IllegalArgumentException("ERROR: Bad start or final states.");
        }
        
        return new DeterministicFiniteAutomata(states, alphabet, delta, startState, finalStates);
    }
    
    /**
     * Creates a new Non-deterministic Automata with default state.
     * @return the new NondeterministicFiniteAutomata.
     */
    public static NondeterministicFiniteAutomata newNFA() {
        List<String> states = new ArrayList<>();
        states.add("START");
        List<Character> alphabet = new ArrayList<>();
        Map<Tuple<String, Character>, List<String>> delta = new HashMap<>();
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
    public static NondeterministicFiniteAutomata buildNFA(
            List<String> states, List<Character> alphabet,
            Map<Tuple<String, Character>, List<String>> delta, String startState,
            List<String> finalStates) {
        if (startState == null || (!states.contains(startState) || (!states.containsAll(finalStates)))) {
            throw new IllegalArgumentException("ERROR: Bad start or final states.");
        }
        
        return new NondeterministicFiniteAutomata(states, alphabet, delta, startState, finalStates);
    }
}
