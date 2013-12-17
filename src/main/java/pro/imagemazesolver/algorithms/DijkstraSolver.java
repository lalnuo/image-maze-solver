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
public class DijkstraSolver {

    private Maze maze;
    private boolean foundSolution = false;

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

        NodeStack path = new NodeStack();
        if (!foundSolution) {
            return path;
        }
        return findPath(path);
    }

    /**
     * Relax merkkaa uuden lyhimmän reitin kyseiseen node1 mikäli siihen pääsee
     * nopeammin kun aiemmin löydetty eikä node1 ole seinää.
     *
     * @param node1 Maalinode
     * @param node Lähtönode
     * @param heap Heap johon läpikäymättömät nodet kasataan
     */
    protected void relax(Node node1, Node node, Heap heap) {
        if (!node1.isVisited() && !node.isWall() && node1.getWeight() < node.getWeight() + 1) {
            node1.setPath(node);
            node1.setWeight(1 + node.getWeight());
            heap.add(node1);
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
    protected NodeStack findPath(NodeStack path) {
        Node pathNode = maze.getEndNode();
        while (pathNode != null) {
            path.add(pathNode);
            pathNode = pathNode.getPath();
        }
        return path;
    }

    /**
     * Metodi tarkastaa onko annettu node mazen maalinode
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
