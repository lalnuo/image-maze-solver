/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author lalli
 */
public class Node implements Comparable<Node> {

   
    private Color color;
    private int weight;
    private boolean wall;
    private ArrayList<Node> naapurit = new ArrayList<Node>();
    private boolean visited;
    private Node path;

    public Node getPath() {
        return path;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node(int y, int x, Color color) {
       
        this.color = color;
        this.wall = false;
        this.visited = false;
    }

    public void addNaapuri(Node node) {
        naapurit.add(node);
    }

    public int getWeight() {
        return weight;
    }

    public void setPath(Node path) {
        this.path = path;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setWall(boolean b) {
        this.wall = b;
    }

    public boolean isWall() {
        return wall;
    }

    public ArrayList<Node> getNaapurit() {
        return naapurit;
    }

    public int compareTo(Node t) {
        return this.getWeight() - t.getWeight();

    }
}