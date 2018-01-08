package example;

import dfa.DFA;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to show some examples on how to use the API
 * example.Example dfa.DFA for binary numbers divisible by 3
 * @author Survivalcraft.at - Benni
 * @version 1.0
 */
public class Example {
    public static void main(String[] args) {


        /*
            Initialize the dfa.DFA
         */
        DFA ex = new DFA();


        /*
            Add states.
         */
        ex.addState("q0");
        ex.addState("q1");
        ex.addState("q2");


        /*
            Add transistions between the states.
         */
        ex.addTransition("q0",'0',"q0");
        ex.addTransition("q0",'1',"q1");
        ex.addTransition("q1",'0',"q2");
        ex.addTransition("q1",'1',"q0");
        ex.addTransition("q2",'0',"q1");
        ex.addTransition("q2",'1',"q2");
        ex.revokeTransition("q2",'0');
        ex.addTransition("q2",'0',"q1");


        /*
            Add a final state q0 and check if q0 is final.
         */
        ex.setFinal("q0");
        System.out.println("Is q0 final: " + ex.isFinal("q0"));


        /*
            Set the start state to q0.
         */
        ex.setStart("q0");


        /*
            Check if string can be build with the dfa.DFA.
         */
        System.out.println("Is 10010 divisible by 3: " + ex.testString("10010"));


        /*
            Removes a state from the dfa.DFA and returns the transitions deleted.
            In order to now work with the dfa.DFA again, you have to add the missing transitions otherwise the testString
            method will throw an NullPointerException.
            The Iteration after the renmove shows per state what transitions were deleted.
         */
        ConcurrentHashMap<String, Character> deleted = ex.removeState("q2");
        for (Iterator<String> keys = deleted.keySet().iterator(); keys.hasNext();) {
            String key = keys.next();
            System.out.printf("Transition: %s -> %s from state %s deleted!\n", deleted.get(key), "q2", key);
        }


        /*
            Output of existsState with the dfa.State q2.
         */
        System.out.println("Does state q2 exist: " + ex.existsState("q2"));


        /*
            This thorws a NullPointerException!
            This is intentional to show what happens when you delete a state, but dont correct the existing states
            and their transistions!
         */
        try{
            System.out.println("Is 10010 divisible by 3: " + ex.testString("10010"));
        }catch (NullPointerException e){
            System.out.println("This was intentional!");
        }
    }
}
