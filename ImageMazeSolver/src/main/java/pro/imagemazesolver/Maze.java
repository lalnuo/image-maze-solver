/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

/**
 *
 * @author lalli
 */
public class Maze {

   
    private Node startNode;
    private Node endNode;
    private Node[][] maze;

    public Maze() {
    }

    public Node[][] getMaze() {
        return maze;
    }

    public void setMaze(Node[][] maze) {
        this.maze = maze;
    }


    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }
}
