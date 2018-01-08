package dfa;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Class to store a state with its transitions
 * @author Survivalcraft.at - Benni
 * @version 1.0
 */
public class State {

    private boolean finalState = false;
    private ConcurrentHashMap<Character, State> connections = new ConcurrentHashMap<>();


    /**
     * Adds a transition to this state
     * @param s Key (value of transition)
     * @param to Value (target dfa.State)
     */
    public void addTransition(Character s, State to) {
        getConnections().put(s, to);
    }


    /**
     * Checks if this state is final
     * @return True or False
     */
    public boolean isFinal() {
        return this.finalState;
    }


    /**
     * Sets this state final
     * @param b True or False
     */
    public void setFinal(boolean b) {
        this.finalState = b;
    }


    /**
     * Returns the Map of transitions
     * @return Map of transitions
     */
    public ConcurrentHashMap<Character, State> getConnections() {
        return connections;
    }
}
