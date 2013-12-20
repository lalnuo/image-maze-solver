/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.logic;

import pro.imagemazesolver.domain.Maze;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import pro.imagemazesolver.domain.Node;

/**
 *
 * @author lalli
 */
public class MazeHandlerTest extends TestCase {

    public void testMazeHandlerFindsRightStartNode() {
        MazeHandler mazeHandler = new MazeHandler("testmaze.png", "testfile.png");
        Maze maze = mazeHandler.imageToMaze(1);

        assertEquals(maze.getStartNode().getX(), 2);
        assertEquals(maze.getStartNode().getY(), 0);
    }

    public void testMazeHandlerReturnsNullIfEndOrStartNotFound() {
        MazeHandler mazeHandler = new MazeHandler("testmaze2.png", "testfile.png");
        Maze maze = mazeHandler.imageToMaze(1);
        assertEquals(maze, null);
    }

    public void testMazeHandlerThrowsExceptionOnUnknownFile() {
        MazeHandler mazeHandler = new MazeHandler("ei tämmöstä ole", "testfile.png");
        assertEquals(mazeHandler.errorThrown, true);
    }

    public void testMazeHandlerFindsRightEndNode() {
        MazeHandler mazeHandler = new MazeHandler("testmaze.png", "testfile.png");
        Maze maze = mazeHandler.imageToMaze(1);

        assertEquals(maze.getEndNode().getX(), 0);
        assertEquals(maze.getEndNode().getY(), 2);
    }

   
    
    public void testOverflowWorks(){
        MazeHandler mazeHandler = new MazeHandler("testmaze.png","testfile.png");
        mazeHandler.mazeHeight = 100;
        assertEquals(true,mazeHandler.overflow(0, 100));
    }
    public void testOverflowWorks2(){
        MazeHandler mazeHandler = new MazeHandler("testmaze.png","testfile.png");
        mazeHandler.mazeHeight = 100;
        assertEquals(false,mazeHandler.overflow(0, 99));
    }
    
 
}
