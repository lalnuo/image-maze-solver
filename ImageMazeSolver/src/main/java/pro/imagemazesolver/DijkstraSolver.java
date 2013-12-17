/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import pro.imagemazesolver.domain.Node;
import pro.imagemazesolver.domain.Maze;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import pro.imagemazesolver.datastructures.Heap;

/**
 *
 * @author lalli
 */
public class DijkstraSolver {

    private Maze maze;

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
    public Stack<Node> solve() {
        Node start = maze.getStartNode();
        boolean foundSolution = false;
        Heap heap = new Heap();
//        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        heap.add(start);
        while (!heap.isEmpty()) {
            Node node = (Node) heap.delMin();
//            Node node = heap.poll();

            if (node.equals(maze.getEndNode())) {
                foundSolution = true;
                break;
            }
            ArrayList<Node> naapurit = node.getNaapurit();
            for (Node node1 : naapurit) {
                relax(node1, node, heap);
            }
            node.setVisited(true);
        }

        Stack<Node> path = new Stack<Node>();
        if (!foundSolution) {
            return path;
        }

        return findPath(path);
    }

    /**
     *
     * @param node1
     * @param node
     * @param heap
     */
    public void relax(Node node1, Node node, Heap heap) {
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
    private Stack<Node> findPath(Stack<Node> path) {
        Node pathNode = maze.getEndNode();
        while (pathNode != null) {
            path.add(pathNode);
            pathNode = pathNode.getPath();
        }
        return path;
    }
}
