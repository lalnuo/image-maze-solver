/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

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

    /**
     *
     * @return totuusarvo onko heap tyhjä
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Palauttaa ja poistaa pienimmän eli taulukon ensimmäisen, vaihtaa sen
     * paikalle taulukon viimeisen arvon ja sen jälkeen kutsuu heapifytä jotta
     * heapin rakenne korjataan
     *
     * @return heapin pienin arvo
     */
    public Comparable delMin() {
        Comparable min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        heap[heapSize] = null;
        heapify(0);


        return min;
    }

    /**
     * Lisää heappiin Comparablen d, aloittaa etsinnän puun alhaalta ja siirtää
     * edellisiä arvoja puussa alemmas kunnes löydetään sopiva kohta
     * Comparablelle.
     *
     * @param d
     */
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

    /**
     * Korjaa rikkoutuneen heapin rakenteen siten että aloittaa virhekorjaukset
     * kohdasta i.
     *
     * @param i Kohta josta kekoa ruvetaan korjaaman
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if (r < heapSize) {
            smallest = (heap[l].compareTo(heap[r]) < 0) ? l : r;
            if (heap[i].compareTo(heap[smallest]) > 0) {
                swap(i, smallest);
                heapify(smallest);
            }
        } else if (l == heapSize - 1 && heap[i].compareTo(heap[l]) > 0) {
            swap(i, l);
        }

    }

    /**
     * Vaihtaa kahden arvon paikkoja heapissa.
     *
     * @param i Vaihdettava
     * @param largest Toinen vaihdettava
     */
    protected void swap(int i, int i2) {
        Comparable temp = heap[i];
        heap[i] = heap[i2];
        heap[i2] = temp;
    }

    /**
     * Metodi kasvattaa heap arrayn kokoa, mikäli se tulee täyteen.
     */
    protected void increaseArraySize() {
        Comparable[] uusi = new Comparable[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            uusi[i] = heap[i];

        }
        heap = uusi;
    }

    /**
     * Tutkii mikä node on i:n yläpuolella
     *
     * @param i kohta jonka parentti halutaan
     * @return i:n parentti
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     *  Metodi palauttaa i:n vasemman lapsen indeksin
     * @param i kohta jonka vasemmanpuoleisen lapsen indeksi halutaan
     * @return i:n vasemmanpuoleinen lapsi
     */
    private int left(int i) {
        if (i == 0) {
            return 1;
        }
        return 2 * i - 1;
    }

    /**
     * Metodi palauttaa i:n oikean lapsen indeksin
     *
     * @param i kohta jonka oikeanpuoleisen lapsen indeksi halutaan
     * @return i:n vasen lapsi
     */
    private int right(int i) {
        if (i == 0) {
            return 2;
        }
        return 2 * i;
    }

    /**
     * Metodi palauttaa alkiot välilyönneillä eroteltuina.
     * Metodi on luotu testaamisen helpottamiseksi.
     * @return heapin alkiot stringinä
     */
    @Override
    public String toString() {
        String toStr = "";
        for (int i = 0; i < heapSize; i++) {
            toStr += heap[i] + " ";
        }
        return toStr;
    }
}
