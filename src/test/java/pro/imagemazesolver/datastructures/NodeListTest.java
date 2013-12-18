/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import pro.imagemazesolver.datastructures.NodeList;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class NodeListTest extends TestCase {

    public void testAdd() {
        Node node = new Node(0, 0, Color.red);
        NodeList list = new NodeList();
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        assertEquals(4, list.size());
    }

    public void testRemoveDecreasesListSize() {
        Node node = new Node(0, 0, Color.red);
        NodeList list = new NodeList();
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.remove(2);
        assertEquals(3, list.size());
    }

    public void testGetWorks() {
        Node node = new Node(0, 0, Color.red);
        Node node2 = new Node(0, 0, Color.red);
        NodeList list = new NodeList();
        list.add(node);
        node2.setWeight(100);
        list.add(node2);
        list.add(node);
        float hund = 100; // jostain syystä 100.0 ei toimi assertEqualissa
        assertEquals(hund, list.get(1).getWeight());
    }

    public void testRemoveMovesOtherValuesCorrectly() {
        NodeList list = new NodeList();
        Node node = new Node(0, 0, Color.blue);
        Node node1 = new Node(0, 0, Color.blue);
        Node node2 = new Node(0, 0, Color.blue);
        list.add(node);
        list.add(node1);
        list.add(node2);
        list.remove(0);
        System.out.println(list.get(1));
        assertEquals(node1, list.get(0));
        assertEquals(node2, list.get(1));

    }
    
    public void testNodeListDuplicatesListSizeWhenItGetsFull(){
        NodeList list = new NodeList();
        Node node = new Node(0,0,Color.blue);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        list.add(node);
        assertEquals(16,list.list.length);
    }
    
    public void testNodeListIncreaseArraySizeWorks(){
        NodeList list = new NodeList();
        list.increaseArraySize();
        assertEquals(16,list.list.length);
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
