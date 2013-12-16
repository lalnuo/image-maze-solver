/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author lalli
 */
public class DijkstraSolver {

    private Maze maze;

    public DijkstraSolver(Maze maze) {
        this.maze = maze;
    }

    public Stack<Node> solve() {
        Node start = maze.getStartNode();
        PriorityQueue<Node> heap = new PriorityQueue();
        heap.add(start);
        while (!heap.isEmpty()) {
            Node node = heap.poll();
           
            if (node.equals(maze.getEndNode())) {
                break;
            }
            ArrayList<Node> naapurit = node.getNaapurit();
            for (Node node1 : naapurit) {
                if (!node1.isVisited() && !node.isWall() && node1.getWeight() < node.getWeight()+1) {
                    node1.setPath(node);
                    node1.setWeight(1 + node.getWeight());
                    heap.add(node1);
                }

            }
            node.setVisited(true);
        }
        Stack<Node> path = new Stack<Node>();
        Node pathNode = maze.getEndNode();
        while (pathNode != null) {
            path.add(pathNode);
            pathNode = pathNode.getPath();
        }
        return path;
    }
}
