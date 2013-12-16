/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

/**
 *
 * @author lalli
 */
public class MazeSolver {
    private Maze maze;
    public MazeSolver(){
        maze = MazeMaker.imageToMaze("test.png");
        solveWithDijsktra();
    }
    
    public void solveWithDijsktra(){
        DijkstraSolver dSolver = new DijkstraSolver(maze);
        dSolver.solve();
        
    }
    
}
