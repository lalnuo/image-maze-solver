/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.domain;

import pro.imagemazesolver.domain.Node;
import java.awt.Color;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author lalli
 */
public class NodeTest {
     public void testNodeNumberOfNeightboursIsCorrectAfterAdd() {
        Node node = new Node(0, 0, Color.RED);
        node.addNaapuri(node);
        node.addNaapuri(node);
        node.addNaapuri(node);
        assertEquals("Get naapurit must be 3", node.getNaapurit().size(), 3);

    }

    public void testNodeNumberOfNeightboursIsZeroAtFirst() {
        Node node = new Node(0, 0, Color.RED);
        assertEquals("Get naapurit must be 0", 0, node.getNaapurit().size());
    }

    public void testNodeIsNotWallAsDefault() {
        Node node = new Node(0, 0, Color.RED);
        assertEquals(false, node.isWall());
    }
    
    public void testNodeComparingWorks(){
        Node node = new Node(0,0,Color.red);
        Node node1 = new Node(0,0,Color.red);
        Node result;
        if(node.compareTo(node)>node.compareTo(node1)){
            result = node;
        }else{
            result=node1;
        }
        assertEquals(node1,result);
    }
    
}
