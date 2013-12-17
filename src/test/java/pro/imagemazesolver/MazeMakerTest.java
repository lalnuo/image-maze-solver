/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import pro.imagemazesolver.domain.Maze;
import java.awt.Color;
import static junit.framework.Assert.assertEquals;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
 * @author lalli
 */
public class MazeMakerTest extends TestCase {

    public void testMazeMakerFindsRightStartNode() {
        MazeMaker mazeMaker = new MazeMaker("testmaze.png","testfile.png");
        Maze maze = mazeMaker.imageToMaze();

        assertEquals(maze.getStartNode().getX(), 2);
        assertEquals(maze.getStartNode().getY(), 0);
    }
    public void testMazeMakerReturnsNullIfEndOrStartNotFound(){
        MazeMaker mazeMaker = new MazeMaker("testmaze2.png","testfile.png");
        Maze maze = mazeMaker.imageToMaze();
        assertEquals(maze,null);
    }
    public void testMazeMakerThrowsExceptionOnUnknownFile() {
        MazeMaker mazeMaker = new MazeMaker("ei tämmöstä ole","testfile.png");
        assertEquals(mazeMaker.errorThrown,true);
    }

    public void testMazeMakerFindsRightEndNode() {
        MazeMaker mazeMaker = new MazeMaker("testmaze.png","testfile.png");
        Maze maze = mazeMaker.imageToMaze();

        assertEquals(maze.getEndNode().getX(), 0);
        assertEquals(maze.getEndNode().getY(), 2);
    }
    
  
}
