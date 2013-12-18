/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.algorithms;

import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import pro.imagemazesolver.datastructures.Heap;
import pro.imagemazesolver.datastructures.NodeStack;
import pro.imagemazesolver.domain.Maze;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class AStarSolverTest extends TestCase {

    public void testAStarSolverFindsShortestPath() {
        Node[][] mazeArray = createMazeArray();
        mazeArray[1][1].setWall(true);
        Maze maze = new Maze();
        maze.setMaze(mazeArray);
        maze.setStartNode(mazeArray[2][0]);
        maze.setEndNode(mazeArray[2][2]);
        AStarSolver solver = new AStarSolver(maze);

        assertEquals(1, solver.solve().size());
    }

    public void testAStarSolverFindsShortestPath2() {
        Node[][] mazeArray = createMazeArray();
        mazeArray[1][1].setWall(true);
        mazeArray[0][1].setWall(true);
        Maze maze = new Maze();

        maze.setMaze(mazeArray);
        maze.setStartNode(mazeArray[0][0]);
        maze.setEndNode(mazeArray[0][2]);
        AStarSolver solver = new AStarSolver(maze);
        assertEquals(1, solver.solve().size());
    }

    public void testGetDistanceFromNodeToNode() {
        Node x = new Node(0, 0, Color.black);
        Node y = new Node(0, 10, Color.red);
        AStarSolver solver = new AStarSolver(new Maze());
        float ten = 10; // testi bugaa pelkällä 10.0
        assertEquals(ten, solver.getDistanceFromNodeToNode(x, y));
    }

    public void testGetDistanceFromNodeToNode2() {
        Node x = new Node(15, 4, Color.black);
        Node y = new Node(20, 10, Color.red);
        AStarSolver solver = new AStarSolver(new Maze());
        float test = 11; // testi bugaa pelkällä 11.0
        assertEquals(test, solver.getDistanceFromNodeToNode(x, y));
    }

    public void testFindBestNeightboursWork() {
        Maze maze = new Maze();
        Node end = new Node(0, 0, Color.black);
        maze.setEndNode(end);
        AStarSolver solver = new AStarSolver(maze);
        Node node = new Node(0, 1, Color.BLACK);
        Node node2 = new Node(0, 2, Color.BLACK);
        Node node3 = new Node(0, 3, Color.BLACK);
        node.setWeight(100);
        node2.setWeight(100);
        node3.setWeight(100);
        node2.setWeight(30);
        node3.setWeight(300);
        node.addNaapuri(node2);
        node.addNaapuri(node);

        solver.findBestNeightbours(node, new Heap());
        assertNull(node3.getPath());
        assertEquals(node2.getPath(),node);
    }

    public Node[][] createMazeArray() {
        Node node1 = new Node(0, 0, Color.RED);
        Node node2 = new Node(0, 1, Color.RED);
        Node node3 = new Node(0, 2, Color.RED);
        Node node4 = new Node(0, 1, Color.RED);
        Node node5 = new Node(1, 1, Color.RED);
        Node node6 = new Node(2, 1, Color.RED);
        Node node7 = new Node(0, 2, Color.RED);
        Node node8 = new Node(1, 2, Color.RED);
        Node node9 = new Node(2, 2, Color.RED);
        Node[][] mazeArray = {
            {node1, node2, node3},
            {node4, node5, node6},
            {node7, node8, node9}
        };
        setNeightbours(mazeArray);
        return mazeArray;
    }

    public void setNeightbours(Node[][] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                Node node = mazeArray[i][j];
                if (i + 1 < mazeArray.length) {
                    node.addNaapuri(mazeArray[i + 1][j]);
                }
                if (i - 1 >= 0) {
                    node.addNaapuri(mazeArray[i - 1][j]);
                }
                if (j + 1 < mazeArray[0].length) {
                    node.addNaapuri(mazeArray[i][j + 1]);
                }
                if (j - 1 >= 0) {
                    node.addNaapuri(mazeArray[i][j - 1]);
                }
            }
        }
    }
}
