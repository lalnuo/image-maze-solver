/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.domain;

import java.awt.Color;
import pro.imagemazesolver.datastructures.NodeList;

/**
 *
 * @author lalli
 */
public class Node implements Comparable<Node> {

    private int y;
    private int x;
    private Color color;
    private float weight;
    private boolean wall;
    private NodeList naapurit = new NodeList();
    private boolean visited;
    private Node path;
    private int distanceToPrevNode = Integer.MAX_VALUE;

    public Node(int y, int x, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.wall = false;
        this.visited = false;
    }

    public int getDistanceToPrevNode() {
        return distanceToPrevNode;
    }

    public void setDistanceToPrevNode(int distanceToPrevNode) {
        this.distanceToPrevNode = distanceToPrevNode;
    }

    public Node getPath() {
        return path;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNaapuri(Node node) {
        naapurit.add(node);
    }

    public float getWeight() {
        return weight;
    }

    public void setPath(Node path) {
        this.path = path;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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

    public NodeList getNaapurit() {
        return naapurit;
    }

    public int compareTo(Node t) {
        return (int) (this.getWeight() - t.getWeight());

    }

    public String toString() {
        return this.weight + "";
    }
}