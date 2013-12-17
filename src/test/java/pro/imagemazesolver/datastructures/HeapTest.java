/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import pro.imagemazesolver.datastructures.Heap;

/**
 *
 * @author lalli
 */
public class HeapTest extends TestCase {

    public void testHeapAddWorksRight() {
        Heap heap = new Heap();
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals("1 2 3 ", heap.toString());

    }

    public void testHeapAddWorksRight2() {
        Heap heap = new Heap();
        heap.add(300);
        heap.add(2321);
        heap.add(1432);
        heap.add(123);
        heap.add(4);
        heap.add(2);
        heap.add(399);
        heap.add(391);
        assertEquals("2 4 123 391 2321 300 1432 399 ", heap.toString());

    }

    public void testHeapifyWorks() {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.delMin();
        assertEquals("2 3 ", heap.toString());
    }
    
    

    public void testHeapifyWorks2() {
        Heap heap = new Heap();
        heap.add(300);
        heap.add(2321);
        heap.add(1432);
        heap.add(1323);
        heap.add(34);
        heap.add(22);
        heap.add(391);
        heap.delMin();
        assertEquals("34 300 391 1323 2321 1432 ", heap.toString());
    }
    
   

    public void testHeapDelReturnsRight() {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        assertEquals(1, heap.delMin());
    }

    public void testHeapDelReturnsHeapRight2() {
        Heap heap = new Heap();
        heap.add(300);
        heap.add(2321);
        heap.add(1432);
        heap.add(1323);
        heap.add(34);
        heap.add(22);
        heap.add(391);
        heap.delMin();
        assertEquals(34, heap.delMin());
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
