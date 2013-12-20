/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.algorithms;

import pro.imagemazesolver.datastructures.Heap;
import pro.imagemazesolver.datastructures.NodeList;
import pro.imagemazesolver.datastructures.NodeStack;
import pro.imagemazesolver.domain.Maze;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class AStarSolver implements Solver {

    private Maze maze;
    NodeStack path = new NodeStack();

    public AStarSolver(Maze maze) {
        this.maze = maze;
    }

    /**
     * Metodi "ohjaa" AStarin toimintaa. Heapista otetaan aina sillä hetkellä
     * parhaalta vaikuttava node ja mikäli tullaan maaliin niin lopetetaan.
     *
     * @return nopein mahdollinen reitti NodeStackina
     */
    public NodeStack solve() {
        maze.getStartNode().setWeight(0);
        maze.getStartNode().setDistanceToPrevNode(0);
        Heap heap = new Heap();
        heap.add(maze.getStartNode());
        while (!heap.isEmpty()) {
            Node node = (Node) heap.delMin();
            if (node == maze.getEndNode()) {
                break;
            }
            findBestNeightbours(node, heap);
            node.setVisited(true);

        }
        return findPath();
    }

    /**
     * Metodi kasaa stackiin parhaan mahdollisen reitin aloittaen viimeisestä
     * nodesta kutsumalla aina parenttia niin kauan kunnes parent = null eli
     * ollaan tultu lähtöpisteeseen
     *
     * @return paras mahdollinen reitti NodeStackina
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
     * Palauttaa kahden noden välisen etäisyyden
     *
     * @param start Lähtönode
     * @param end Päätenode
     * @return etäisyys lähtönoden ja päätenoden välillä
     */
    protected float getDistanceFromNodeToNode(Node start, Node end) {
        float dx = Math.abs(start.getX() - end.getX());
        float dy = Math.abs(start.getY() - end.getY());
        return (dx + dy);
    }

    /**
     * Metodi tutkii pääseekö annetun noden kautta sen naapureihin nopeammin kun
     * aiemmin. Mikäli pääsee, päivitetään nodea ja lisätään se heappiin.
     * Paremmuus lasketaan tutkimalla etäisyys maaliin + reitin paino alkaen
     * alusta. CountDist laskee onko siirto sivuttainen.
     *
     * @param node Node jonka naapureita tutkitaan
     * @param heap heap johon lisätään läpikäyty parempi naapuri
     */
    protected void findBestNeightbours(Node node, Heap heap) {
        NodeList naapurit = node.getNaapurit();
        for (int i = 0; i < naapurit.size(); i++) {
            Node naapuri = naapurit.get(i);
            if (!naapuri.isVisited() && !naapuri.isWall()) {
                int countDist = (node.getX() != naapuri.getX() && node.getY() != naapuri.getY()) ? 2 : 1;

                int distanceFromNodeToNode = node.getDistanceToPrevNode() + countDist;
                float distanceFromNodeToEnd = getDistanceFromNodeToNode(naapurit.get(i), maze.getEndNode());
                float weight = distanceFromNodeToEnd + distanceFromNodeToNode;
                if (naapuri.getWeight() > weight) {
                    naapuri.setPath(node);
                    naapuri.setWeight(weight);
                    naapuri.setDistanceToPrevNode(distanceFromNodeToNode);
                    heap.add(naapuri);
                }
            }
        }
    }
}
