package edu.claflin.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a Non-Deterministic Finite Automata in memory.
 * 
 * @author CASII
 */
public class NondeterministicFiniteAutomata extends Automata<String, Character, List<String>> {

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
                    List<String>> delta, String startState, List<String> finalStates) {
        super(states, alphabet, delta, startState, finalStates);
    }
    
    /**
     * Adds a String to a target for a delta.
     * @param key the Tuple representing the state X alphabet combo.
     * @param value the target State (string) to insert.
     * @return true if successfully added
     */
    public final boolean addToDelta(Tuple<String, Character> key, String value) {
        if (!states.contains(key.getA()) || !states.contains(value) || 
                !alphabet.contains(key.getB()))
            return false;
        
        List<String> target = delta.get(key);
        if (target != null) {
            target.add(value);
            boolean valid = validateTarget(target);
            if (!valid) target.remove(value);
            return valid;
        } else {
            target = new ArrayList<>();
            target.add(value);
            return putDelta(key, target);
        }
    }
    /**
     * Removes a state from a transition
     * @param key the tuple representing the transition to look at.
     * @param value the State to remove.
     * @return true if successfully removed, false otherwise
     */
    public final boolean removeFromDelta(Tuple<String, Character> key, String value) {
        if (!states.contains(key.getA()) || !states.contains(value) || 
                !alphabet.contains(key.getB()))
            return false;
        
        List<String> target = delta.get(key);
        if (target != null) {
            return target.remove(value);
        } else {
            return false;
        }
    }

    @Override
    protected boolean validateTarget(List<String> target) {
        return states.containsAll(target);
    }
}