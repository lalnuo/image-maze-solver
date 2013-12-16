package pro.imagemazesolver;

import java.awt.Color;
import java.util.Stack;
import static junit.framework.Assert.assertEquals;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void testNodeNumberOfNeightboursIsCorrectAfterAdd() {
        Node node = new Node(0, 0, Color.RED);
        node.addNaapuri(node);
        node.addNaapuri(node);
        node.addNaapuri(node);
        assertEquals("Get naapurit must be 3", node.getNaapurit().size(), 3);

    }

    public void testNodeNumberOfNeightboursIsZeroAtFirst() {
        Node node = new Node(0, 0, Color.RED);
        assertEquals("Get naapurit must be 0", 0, node.getNaapurit().size());
    }

    public void testNodeIsNotWallAsDefault() {
        Node node = new Node(0, 0, Color.RED);
        assertEquals(false, node.isWall());
    }

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

    public void testMazeMakerFindsRightStartNode() {
        MazeMaker mazeMaker = new MazeMaker("testmaze.png");
        Maze maze = mazeMaker.imageToMaze();
        
        assertEquals(maze.getStartNode().getX(),0);
        assertEquals(maze.getStartNode().getY(),2);
    }
    
     public void testMazeMakerFindsRightEndNode() {
        MazeMaker mazeMaker = new MazeMaker("testmaze.png");
        Maze maze = mazeMaker.imageToMaze();
        
        assertEquals(maze.getEndNode().getX(),2);
        assertEquals(maze.getEndNode().getY(),0);
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
