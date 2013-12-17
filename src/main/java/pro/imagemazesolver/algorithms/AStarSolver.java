/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.algorithms;

import java.util.HashSet;
import pro.imagemazesolver.datastructures.Heap;
import pro.imagemazesolver.datastructures.NodeList;
import pro.imagemazesolver.datastructures.NodeStack;
import pro.imagemazesolver.domain.Maze;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class AStarSolver {

    private Maze maze;

    public AStarSolver(Maze maze) {
        this.maze = maze;
    }

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
            NodeList naapurit = node.getNaapurit();
            for (int i = 0; i < naapurit.size(); i++) {
                Node naapuri = naapurit.get(i);
                if (!naapuri.isVisited() && !naapuri.isWall()) {
                    int distanceFromNodeToNode = node.getDistanceToPrevNode() + 1;
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
            node.setVisited(true);

        }
        NodeStack path = new NodeStack();
        return findPath(path);
    }

    protected NodeStack findPath(NodeStack path) {
        Node pathNode = maze.getEndNode();
        while (pathNode != null) {
            path.add(pathNode);
            pathNode = pathNode.getPath();
        }
        return path;
    }

    private float getDistanceFromNodeToNode(Node start, Node end) {
        float dx = Math.abs(start.getX() - end.getX());
        float dy = Math.abs(start.getY() - end.getY());
        return (dx + dy);
    }
}
