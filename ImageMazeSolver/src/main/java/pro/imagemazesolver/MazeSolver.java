/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author lalli
 */
public class MazeSolver {

    private Maze maze;
    private MazeMaker mazeMaker;

    public MazeSolver() {
        System.out.println("Anna tiedoston nimi: ");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        mazeMaker = new MazeMaker(filename);
        maze = mazeMaker.imageToMaze();
        solveWithDijsktra();
    }

    public void solveWithDijsktra() {
        DijkstraSolver dSolver = new DijkstraSolver(maze);
        Stack<Node> path = dSolver.solve();
        mazeMaker.drawPath(path);


    }
}
