/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.ui;

import pro.imagemazesolver.algorithms.DijkstraSolver;
import pro.imagemazesolver.domain.Maze;
import java.util.Scanner;
import pro.imagemazesolver.algorithms.AStarSolver;
import pro.imagemazesolver.datastructures.NodeStack;

/**
 *
 * @author lalli
 */
public class MazeSolver {

    private Maze maze;
    private MazeMaker mazeMaker;

    /**
     * Metodissa kysytään käyttäjältä kuvatiedoston nimi ja talletustiedoston
     * nimen , jonka jälkeen annetaan tiedostonimi uudelle MazeMakerille,
     * MazeMaker palauttaa kaksiulotteisin taulukon joka voidaan sen jälkeen
     * ratkaista halutulla algoritmillä.
     *
     */
    public MazeSolver() {
        System.out.print("Anna tiedoston nimi: ");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        System.out.print("Anna tallennustiedoston nimi (jotain.png): ");
        String savename = sc.nextLine();
        mazeMaker = new MazeMaker(filename, savename);
        System.out.print("Haluatko että käytetään: 1. Dijkstraa, 2. A* (syötä 1 tai 2): ");
        String solveWith = sc.nextLine();
        maze = mazeMaker.imageToMaze(solveWith);
        if (maze != null) {
            if (solveWith.equals("1")) {
                solveWithDijsktra();
            } else {
                solveWithAStar();
            }
        } else {
            System.out.println("No solutions found!");
        }
    }

    /**
     * Kutsuu DijkstraSolveria ja sen jälkeen antaa mazeMakerille saadun pathin,
     * jotta mazeMaker voi piirtää sen.
     */
    public void solveWithDijsktra() {
        DijkstraSolver dSolver = new DijkstraSolver(maze);
        NodeStack path = dSolver.solve();
        mazeMaker.drawPath(path);


    }

    private void solveWithAStar() {
        AStarSolver aSolver = new AStarSolver(maze);
        NodeStack path = aSolver.solve();
        mazeMaker.drawPath(path);
    }
}
