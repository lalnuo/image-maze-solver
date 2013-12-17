/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.datastructures;

import pro.imagemazesolver.domain.Node;

/**
 * NodeList on lista joka on muokattu nimenomaan labyrintinratkomista varten.
 * @author lalli
 */
public class NodeList {

    protected Node[] list = new Node[4];
    private int listSize = 0;

    public NodeList() {
    }

    /**
     * Palauttaa halutun kohdan taulukosta
     * @param i minkä kohdan taulukosta käyttäjä haluaa
     * @return taulukosta palautettava arvo
     */
    public Node get(int i) {
        return list[i];
    }

    /**
     *
     * @return palauttaa listan koon
     */
    public int size() {
        return listSize;
    }

    /**
     * Poistaa halutun arvon ja siirtää sen perässä olevia yhden vasemmalle.
     * Lopuksi pienentää listan kokoa
     * @param poistettava indeksi
     */
    public void remove(int i) {
        listSize--;
        for (int j = i; j < list.length - 1; j++) {
            list[j] = list[j + 1];
        }
    }

    /**
     * Lisää noden taulukkoon ja kasvattaa noden arvoa
     * @param i Lisättävä arvo
     */
    public void add(Node i) {
        if (listSize == list.length) {
            increaseArraySize();
        }
        list[listSize] = i;
        listSize++;
    }

    /**
     * Kasvattaa taulukon kokoa. Tätä tosin ei tarvita kyseisessä ongelmassa
     * koska maksimi naapurein määrä on 4
     * 
     */
    protected void increaseArraySize() {
        Node[] uusi = new Node[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            uusi[i] = list[i];

        }
        list = uusi;
    }
}
