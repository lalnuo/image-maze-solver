/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.algorithms;

import pro.imagemazesolver.domain.Node;
import pro.imagemazesolver.domain.Maze;
import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import pro.imagemazesolver.datastructures.Heap;

/**
 *
 * @author lalli
 */
public class DijkstraSolverTest {

    public void testDijkstaSolverFindsShortestPath() {
        Node[][] mazeArray = createMazeArray();
        mazeArray[1][1].setWall(true);
        Maze maze = new Maze();
        maze.setMaze(mazeArray);
        maze.setStartNode(mazeArray[2][0]);
        maze.setEndNode(mazeArray[2][2]);
        DijkstraSolver solver = new DijkstraSolver(maze);

        assertEquals(3, solver.solve().size());

    }

    public void testDijkstaSolverFindsShortestPath2() {
        Node[][] mazeArray = createMazeArray();
        mazeArray[1][1].setWall(true);
        mazeArray[0][1].setWall(true);
        Maze maze = new Maze();

        maze.setMaze(mazeArray);
        maze.setStartNode(mazeArray[0][0]);
        maze.setEndNode(mazeArray[0][2]);
        DijkstraSolver solver = new DijkstraSolver(maze);

        assertEquals(7, solver.solve().size());
    }

    public void testRelaxWorks() {
        Maze maze = new Maze();
        DijkstraSolver solver = new DijkstraSolver(maze);
        Heap heap = new Heap();
        Node node = new Node(0, 0, Color.blue);
        node.setWeight(5);
        Node node1 = new Node(0, 0, Color.blue);
        node1.setWeight(10);
        solver.relax(node1, node, heap);
        assertEquals(node1.getPath(), null);
    }

    public void testRelaxWorks2() {
        Maze maze = new Maze();
        DijkstraSolver solver = new DijkstraSolver(maze);
        Heap heap = new Heap();
        Node node = new Node(0, 0, Color.blue);
        node.setWeight(10);
        Node node1 = new Node(0, 0, Color.blue);
        node1.setWeight(5);
        solver.relax(node1, node, heap);
        assertEquals(node1.getPath(), node);
    }

    public void testSolverReturnsEmptyStackIfNoWayWasFound() {
        Node[][] mazeArray = createMazeArray();
        mazeArray[1][1].setWall(true);
        mazeArray[0][1].setWall(true);
        mazeArray[1][0].setWall(true);
        Maze maze = new Maze();

        maze.setMaze(mazeArray);
        maze.setStartNode(mazeArray[0][0]);
        maze.setEndNode(mazeArray[0][2]);
        DijkstraSolver solver = new DijkstraSolver(maze);

        assertEquals(0, solver.solve().size());
    }

    public void testIsThisGoalReturnsTrueOnRightGoal() {
        Maze maze = new Maze();
        Node node = new Node(0, 0, Color.blue);
        maze.setEndNode(node);
        DijkstraSolver solver = new DijkstraSolver(maze);
        assertEquals(true, solver.isThisGoal(node));
    }

    public void testIsThisGoalReturnsFalseOnWrongGoal() {
        Maze maze = new Maze();
        Node node = new Node(0, 0, Color.blue);
        Node node2 = new Node(0, 0, Color.blue);
        maze.setEndNode(node2);
        DijkstraSolver solver = new DijkstraSolver(maze);
        assertEquals(false, solver.isThisGoal(node));
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
