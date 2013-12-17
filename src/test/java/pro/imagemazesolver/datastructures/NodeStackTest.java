package pro.imagemazesolver.datastructures;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import pro.imagemazesolver.datastructures.NodeStack;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class NodeStackTest extends TestCase {

    public void testStackAdd() {
        NodeStack stack = new NodeStack();
        Node node = new Node(0, 0, Color.black);
        stack.add(node);
        stack.add(node);
        stack.add(node);
        assertEquals(3, stack.size());
    }

    public void testPopReturnsRight() {
        NodeStack stack = new NodeStack();
        Node node = new Node(0, 0, Color.black);
        Node node1 = new Node(0, 0, Color.black);
        Node node2 = new Node(0, 0, Color.black);
        stack.add(node);
        stack.add(node1);
        stack.add(node2);
        assertEquals(node2, stack.pop());
    }
    
    public void testPopDecreasesSize(){
        NodeStack stack = new NodeStack();
        Node node = new Node(0, 0, Color.black);
        Node node1 = new Node(0, 0, Color.black);
        Node node2 = new Node(0, 0, Color.black);
        stack.add(node);
        stack.add(node1);
        stack.add(node2);
        stack.pop();
        assertEquals(2, stack.size()); 
    }
    
    public void testNewStackIsEmpty(){
        assertEquals(true,new NodeStack().isEmpty());
    }
}
