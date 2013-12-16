package pro.imagemazesolver;

import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    
    public void testNodeNumberOfNeightboursIsCorrectAfterAdd(){
        Node node = new Node(0,0,Color.RED);
        node.addNaapuri(node);
        node.addNaapuri(node);
        node.addNaapuri(node);
        assertEquals("Get naapurit must be 3", node.getNaapurit().size(),3);
        
    }
    
    public void testNodeNumberOfNeightboursIsZeroAtFirst(){
        Node node = new Node(0,0,Color.RED);
        assertEquals("Get naapurit must be 0", node.getNaapurit().size(),0); 
    }
    
    public void testNodeIsNotWallAsDefault(){
        Node node = new Node(0,0,Color.RED);
        assertEquals(node.isWall(),true);
    }
}
