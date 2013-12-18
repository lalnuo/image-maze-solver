/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.ui;

import pro.imagemazesolver.algorithms.DijkstraSolver;
import pro.imagemazesolver.domain.Maze;
import pro.imagemazesolver.algorithms.AStarSolver;
import pro.imagemazesolver.algorithms.Solver;

/**
 *
 * @author lalli
 */
public class MazeSolver {

    private Maze maze;
    private MazeMaker mazeMaker;

    /**
     * Metodille annetaan luettavan labyrintin nimi, talletustiedoston nimi ja 1
     * tai 2 sen mukaan halutaanko ratkaista Dijkstralla vai A*. Metodi pyytää
     * mazeMakeria lukemaan labyrintin, sen jälkeen ratkaisee labyrintin
     * halutulla algoritmillä ja lopuksi pyytää mazeMakeria piirtämään reitin.
     */
    public MazeSolver(String filename, String savename, int solveWith) {
        Solver solver;

        mazeMaker = new MazeMaker(filename, savename);
        maze = mazeMaker.imageToMaze(solveWith);
        if (maze == null) {
            System.out.println("No solutions found.");
            return;
        }
        switch (solveWith) {
            case 1:
                solver = new DijkstraSolver(maze);
            case 2:
                solver = new AStarSolver(maze);
            default:
                solver = new AStarSolver(maze);

        }
        mazeMaker.drawPath(solver.solve());
    }
}
