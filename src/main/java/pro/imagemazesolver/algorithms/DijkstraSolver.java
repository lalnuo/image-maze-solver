/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.algorithms;

import pro.imagemazesolver.domain.Node;
import pro.imagemazesolver.domain.Maze;
import pro.imagemazesolver.datastructures.Heap;
import pro.imagemazesolver.datastructures.NodeList;
import pro.imagemazesolver.datastructures.NodeStack;

/**
 *
 * @author lalli
 */
public class DijkstraSolver implements Solver {

    private Maze maze;
    private boolean foundSolution = false;
    NodeStack path = new NodeStack();

    /**
     *
     * @param maze maze joka halutaan ratkaista. Sisältää kaksiulotteisen
     * taulukon sekä tiedot aloitus- ja lopetuspisteistä
     */
    public DijkstraSolver(Maze maze) {
        this.maze = maze;
    }

    /**
     * Metodi ratkaisee konstruktorissa annetusta mazesta nopeimman reitin
     * Dijkstran algoritmillä.
     *
     * @return nopein reitti stackina
     */
    public NodeStack solve() {
        Node start = maze.getStartNode();
        start.setWeight(0);
        Heap heap = new Heap();
        heap.add(start);

        while (!heap.isEmpty()) {
            Node node = (Node) heap.delMin();

            if (isThisGoal(node)) {
                break;
            }

            NodeList naapurit = node.getNaapurit();
            for (int i = 0; i < naapurit.size(); i++) {
                relax(naapurit.get(i), node, heap);
            }
            node.setVisited(true);
        }

        if (!foundSolution) {
            return path;
        }
        return findPath();
    }

    /**
     * Relax merkkaa uuden lyhimmän reitin kyseiseen node1 mikäli siihen pääsee
     * nopeammin kun aiemmin löydetty eikä node1 ole seinää. CountWeight laskee
     * että onko siirtymä sivuttainen, sivuttaissiirtymät on painoltaan 2.
     *
     * @param node1 Maalinode
     * @param node Lähtönode
     * @param heap Heap johon läpikäymättömät nodet kasataan
     */
    protected void relax(Node naapuri, Node node, Heap heap) {
        int countWeight = (node.getX() != naapuri.getX() && node.getY() != naapuri.getY()) ? 2 : 1;

        if (!naapuri.isVisited() && !naapuri.isWall() && naapuri.getWeight() > node.getWeight() + countWeight) {
            naapuri.setPath(node);
            naapuri.setWeight(countWeight + node.getWeight());
            heap.add(naapuri);
        }
    }

    /**
     * Metodi palauttaa nopeimman reitin aloittamalla maalista ja käymällä
     * edellisiä nodeja niin kauan kunnes edellinen node on null.
     *
     * @param path Tyhjä stacki johon reitti halutaan
     *
     * @return nopein reitti stackina
     */
    protected NodeStack findPath() {
        Node pathNode = maze.getEndNode();
        while (pathNode != null) {
            path.add(pathNode);
            pathNode = pathNode.getPath();
        }

        return path;

    }

    /**
     * Metodi tarkastaa onko annettu node mazen maalinode
     *
     * @param node Tarkastettava node
     * @return totuusarvon oliko kyseessä maalinode
     */
    protected boolean isThisGoal(Node node) {
        if (node == maze.getEndNode()) {
            foundSolution = true;
        }
        return foundSolution;
    }
}
