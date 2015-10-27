package edu.claflin.logic;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author CASII
 */
public class DeterministicFiniteAutomata extends NondeterministicFiniteAutomata {

    public DeterministicFiniteAutomata(List<String> states, List<Character> alphabet, Map<Tuple<String, Character>, String> delta, String startState, List<String> finalStates) {
        super(states, alphabet, delta, startState, finalStates);
    }
    
}
