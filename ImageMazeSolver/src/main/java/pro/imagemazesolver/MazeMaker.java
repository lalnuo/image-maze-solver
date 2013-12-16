/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lalli
 */
class MazeMaker {

    static Maze imageToMaze(String testpng) {
        try {
            File image = new File(testpng);
            BufferedImage buffImage = ImageIO.read(image);
            int height = buffImage.getHeight();
            int width = buffImage.getWidth();
            Maze maze = new Maze();

            Node[][] mazeArray = new Node[height][width];


            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Node node = new Node(i, j, new Color(buffImage.getRGB(i, j)));
                    if (node.getColor().equals(Color.BLACK)) {
                        node.setWall(true);
                    } else if (node.getColor().equals(Color.RED)) {
                        maze.setStartNode(node);
                    } else if (node.getColor().equals(Color.BLUE)) {
                        maze.setEndNode(node);
                    }

                    mazeArray[i][j] = node;

                }
            }
            setNeightbours(mazeArray);
            maze.setMaze(mazeArray);

            return maze;

        } catch (IOException ex) {
            System.out.println("Incorrect file");
            return null;
        }

    }

    private static void setNeightbours(Node[][] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                Node node = mazeArray[i][j];
                if (i + 1 < mazeArray.length) {
                    node.addNaapuri(mazeArray[i + 1][j]);
                }
                if (i - 1 >= 0) {
                    node.addNaapuri(mazeArray[i - 1][j]);
                }
                if (j + 1 < mazeArray[0].length) {
                    node.addNaapuri(mazeArray[i][j + 1]);
                }
                if (j - 1 >= 0) {
                    node.addNaapuri(mazeArray[i][j - 1]);
                }
            }
        }


    }
}
