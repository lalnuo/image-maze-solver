/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class NodeStack {

    private Node[] stack = new Node[1];
    private int stackLength = 0;

    public int size() {
        return stackLength;
    }

    /**
     * Lisää stackkiin noden n
     *
     * @param n stackkiin lisättävä node
     */
    public void add(Node n) {
        if (stack.length == stackLength) {
            increaseArraySize();
        }
        stack[stackLength] = n;
        stackLength++;

    }

    /**
     *
     * @return totuusarvo onko stack tyhjä
     */
    public boolean isEmpty() {
        return stackLength == 0;
    }

    /**
     * Palauttaa stackin viimeisen alkion
     *
     * @return listan viimeinen alkio
     */
    public Node pop() {
        Node popped = stack[stackLength - 1];
        stackLength--;
        return popped;
    }

    /**
     * Kasvattaa stack arrayn kokoa mikäli se tulee täyteen.
     */
    private void increaseArraySize() {
        Node[] uusi = new Node[stack.length * 2];
        for (int i = 0; i < stack.length; i++) {
            uusi[i] = stack[i];

        }
        stack = uusi;
    }
}
