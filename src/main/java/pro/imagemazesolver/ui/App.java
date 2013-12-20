package pro.imagemazesolver.ui;

import java.util.Scanner;

public class App {

    /**
     * Metodi luo uuden instanssin MazeSolverista halutuilla asetuksilla.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.print("Anna tiedoston nimi: ");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        System.out.print("Anna tallennustiedoston nimi (jotain.png): ");
        String savename = sc.nextLine();
        System.out.print("Haluatko että käytetään: 1. Dijkstraa, 2. A* (syötä 1 tai 2): ");
        int solveWith = Integer.parseInt(sc.nextLine());
        MazeSolver solver = new MazeSolver(filename,savename,solveWith);
//        MazeSolver solver = new MazeSolver("maze.gif","final2.png", 1);  // nopeustesteihin
        


    }
}