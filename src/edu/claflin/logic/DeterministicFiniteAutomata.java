package edu.claflin.logic;

import java.util.List;
import java.util.Map;

/**
 * Represents a DeterministicFiniteAutomata in memory.  Differs from a NFA 
 * solely in that it enforces certain restrictions on it's generating data.
 * Specifically, to load, save, or execute this automata it SHOULD be in a 
 * deterministic state.  Attempting to do so when it is not in such a state 
 * will cause an error.
 * 
 * Note that the ALPHABET_EPSILON character can still be entered manually by the
 * user for use in the system, but it will NOT be interpreted in a special 
 * manner when executing this automata as a DFA.
 * 
 * @author CASII
 */
public class DeterministicFiniteAutomata extends NondeterministicFiniteAutomata {

    public DeterministicFiniteAutomata(List<String> states, 
            List<Character> alphabet, Map<Tuple<String, Character>, 
                    List<String>> delta, String startState, List<String> finalStates) {
        super(states, alphabet, delta, startState, finalStates);
    }
    
    @Override
    public boolean validateTarget(List<String> target) {
        return states.containsAll(target) && target.size() <= 1;
    }
}
