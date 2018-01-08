package dfa;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Survivalcraft.at - Benni
 * @version 1.0
 */
public class DFA {

    private String start = "";
    private ConcurrentHashMap<String, State> states = new ConcurrentHashMap<>();


    /**
     * Standard constructor to initialize the dfa.DFA
     */
    public DFA() {
    };


    /**
     * Constructor to initialize the dfa.DFA with a state
     * @param stateName Name of a state
     */
    public DFA(String stateName) {
        addState(stateName);
    }


    /**
     * Adds a state to your dfa.DFA
     * @param stateName Name of a state
     */
    public void addState(String stateName) {
        if (!states.containsKey(stateName)) {
            State tmp = new State();
            states.put(stateName, tmp);
        } else {
            System.out.println("dfa.State already exists");
        }
    }


    /**
     * Sets a state final
     * @param stateName Name of a state
     */
    public void setFinal(String stateName) {
        if (states.containsKey(stateName)) {
            states.get(stateName).setFinal(true);
        } else {
            System.out.println("Specified state does not exist");
        }
    }


    /**
     * Revokes the final attribute from a state
     * @param stateName Name of a state
     */
    public void revokeFinal(String stateName) {
        if (states.containsKey(stateName)) {
            states.get(stateName).setFinal(false);
        } else {
            System.out.println("Specified state does not exist");
        }
    }


    /**
     * Checks if a dfa.State has the attribute final
     * @param stateName Name of a state
     * @return True or False
     */
    public boolean isFinal(String stateName) {
        if (states.containsKey(stateName)) {
            return states.get(stateName).isFinal();
        } else {
            System.out.println("Specified state does not exist");
            return false;
        }
    }


    /**
     * Adds a transition to a state
     * @param stateName Name of a state
     * @param value Value of transition
     * @param targetStateName Name of the target state
     */
    public void addTransition(String stateName, Character value, String targetStateName) {
        if (states.containsKey(stateName)) {
            if (states.containsKey(targetStateName)) {
                states.get(stateName).addTransition(value, states.get(targetStateName));
            } else {
                System.out.println("Target state does not exist, please create it before usage");
            }
        } else {
            System.out.println("Specified state does not exist");
        }
    }


    /**
     * Removes a transiotion from a state over its value
     * @param stateName Name of a state
     * @param value Value of the transition
     */
    public void revokeTransition(String stateName, Character value) {
        if (states.containsKey(stateName)) {
            if (states.get(stateName).getConnections().containsKey(value)) {
                states.get(stateName).getConnections().remove(value);
            } else {
                System.out.println("Value is not in any transition");
            }
        } else {
            System.out.println("Specified states does not exist");
        }
    }


    /**
     * Removes a state, use with CAUTION!
     * Map is in this logic form:
     * MAP<state of which the transition was deleted, value of the transition>
     * @param stateName Name of a dfa.State
     * @return ConcurrentHashMap<String, String> with deleted transitions
     */
    public ConcurrentHashMap<String, Character> removeState(String stateName) {
        ConcurrentHashMap<String, Character> delKeys = new ConcurrentHashMap<>();
        if (states.containsKey(stateName)) {
            State del = states.get(stateName);
            for (String key : states.keySet()) {
                ConcurrentHashMap<Character, State> sta = states.get(key).getConnections();
                for (Character keyTrans : sta.keySet()) {
                    if (states.get(key).getConnections().get(keyTrans) == del) {
                        states.get(key).getConnections().remove(keyTrans);
                        delKeys.put(key, keyTrans);
                    }
                }
            }
            if (del.isFinal()) {
                System.out.println("You deleted a final state, functionality may be compromised!");
            }else{
                System.out.println("You deleted a state, functionality may be compromised!");
            }
            states.remove(stateName);
        } else {
            System.out.println("Specified state does not exist");
        }
        return delKeys;
    }


    /**
     * Checks if a state exists
     * @param stateName Name of a state
     * @return True or False
     */
    public boolean existsState(String stateName) {
        return states.containsKey(stateName);
    }


    /**
     * Tests if an string can be build with this dfa.DFA
     * @param toTest String which shall be tested
     * @return True or False
     * @throws NullPointerException
     */
    public boolean testString(String toTest) throws NullPointerException{
        if (start == "") {
            System.out.println("You have to set an entry point!");
            return false;
        } else {
            State next = states.get(start);
            for (int i = 0; i < toTest.length(); ++i) {
                next = next.getConnections().get(toTest.charAt(i));
            }
            return next.isFinal();
        }
    }


    /**
     * Sets the startstate
     * @param startState Name of a dfa.State
     */
    public void setStart(String startState) {
        if (states.containsKey(startState)) {
            start = startState;
        } else {
            System.out.println("dfa.State has to exist to make it an entry point");
        }
    }
}
