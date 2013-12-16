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

    /**
     * Metodissa kysytään käyttäjältä kuvatiedoston nimi ja talletustiedoston nimen
     * , jonka jälkeen annetaan tiedostonimi uudelle MazeMakerille,
     * MazeMaker palauttaa kaksiulotteisin taulukon joka voidaan
     * sen jälkeen ratkaista halutulla algoritmillä.
     *
     */
    public MazeSolver() {
        System.out.print("Anna tiedoston nimi: ");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        System.out.print("Anna tallennustiedoston nimi (jotain.png): ");
        String savename = sc.nextLine();
        mazeMaker = new MazeMaker(filename,savename);
        maze = mazeMaker.imageToMaze();
        if (maze != null) {
            solveWithDijsktra();
        }else{
            System.out.println("No solutions found!");
        }
    }

    /**
     * Kutsuu DijkstraSolveria ja sen jälkeen antaa mazeMakerille
     * saadun pathin, jotta mazeMaker voi piirtää sen.
     */
    public void solveWithDijsktra() {
        DijkstraSolver dSolver = new DijkstraSolver(maze);
        Stack<Node> path = dSolver.solve();
        mazeMaker.drawPath(path);


    }
}