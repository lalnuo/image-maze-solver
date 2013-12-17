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
    
    public int size(){
        return stackLength;
    }
    public void add(Node n) {
        if (stack.length == stackLength) {
            increaseArraySize();
        }
        stack[stackLength] = n;
        stackLength++;

    }
    
    public boolean isEmpty(){
        return stackLength == 0;
    }

    public Node pop() {
        Node popped = stack[stackLength - 1];
        stackLength--;
        return popped;
    }

    private void increaseArraySize() {
        Node[] uusi = new Node[stack.length * 2];
        for (int i = 0; i < stack.length; i++) {
            uusi[i] = stack[i];

        }
        stack = uusi;
    }
}
