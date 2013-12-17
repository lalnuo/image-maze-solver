/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

import java.util.Arrays;

/**
 *
 * @author lalli
 */
public class Heap {

    private Comparable[] heap;
    int heapSize = 0;

    public Heap() {
        heap = new Comparable[1];
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public Comparable delMin() {
        Comparable min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        heap[heapSize] = null;
        heapify(0);


        return min;
    }

    public void add(Comparable d) {
        if (heapSize == heap.length) {
            increaseArraySize();
        }
        int parent = parent(heapSize);
        if (heapSize == 0) {
            heap[0] = d;
        } else {
            int i = heapSize;
            while (i > 0 && heap[parent(i)].compareTo(d) > 0) {
                heap[i] = heap[parent(i)];
                i = parent(i);
            }
            heap[i] = d;

        }
        heapSize++;
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest;
        if (r < heapSize) {
            largest = (heap[l].compareTo(heap[r]) < 0) ? l : r;
            if (heap[i].compareTo(heap[largest]) > 0) {
                swap(i, largest);
                heapify(largest);
            }
        } else if (l == heapSize - 1 && heap[i].compareTo(heap[l]) > 0) {
            swap(i, l);
        }

    }

    private void swap(int i, int largest) {
        Comparable temp = heap[i];
        heap[i] = heap[largest];
        heap[largest] = temp;
    }

    private void increaseArraySize() {
        Comparable[] uusi = new Comparable[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            uusi[i] = heap[i];

        }
        heap = uusi;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        if (i == 0) {
            return 1;
        }
        return 2 * i - 1;
    }

    private int right(int i) {
        if (i == 0) {
            return 2;
        }
        return 2 * i;
    }
}
