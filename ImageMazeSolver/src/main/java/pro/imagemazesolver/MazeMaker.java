/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lalli
 */
class MazeMaker {

    private BufferedImage buffImage;
    boolean errorThrown;
    private String savename;

    /**
     * Metodi on luokan konstruktori. Konstruktorissa luetaan anettu tiedosto ja
     * muutetaan se buffered imageksi
     *
     * @params testpng on kuvatiedoston nimi.
     * @params savename tiedosto johon tulos tallennetaan
     *
     */
    public MazeMaker(String imagepath, String savename) {
        this.savename = savename;
        try {
            File image = new File(imagepath);
            buffImage = ImageIO.read(image);
        } catch (IOException ex) {
            errorThrown = true;
            Logger.getLogger(MazeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * Metodi muuntaa aiemmin konstruktorissa annetun tiedoston
     * kaksiulotteiseksi taulukoksi, ja kutsuu setNeightbours metodia
     * naapureiden asettamiseksi.
     *
     * @return palauttaa mazen joka sisältää 2 ulotteisen taulukon,
     * aloituspisteen ja maalipisteen.
     */
    public Maze imageToMaze() {
        int height = buffImage.getHeight();
        int width = buffImage.getWidth();
        Maze maze = new Maze();

        Node[][] mazeArray = new Node[height][width];
        boolean startFound = false;
        boolean endFound = false;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color;

                color = buffImage.getRGB(j, i);

                Node node = new Node(i, j, new Color(color));
                if (node.getColor().equals(Color.RED)) {
                    maze.setStartNode(node);
                    startFound = true;
                } else if (node.getColor().equals(Color.BLUE)) {
                    maze.setEndNode(node);
                    endFound = true;
                } else if (node.getColor().getRGB() < -100000) {
                    node.setWall(true);
                }
                mazeArray[i][j] = node;
            }
        }
        if (!startFound || !endFound) {
            System.out.println("Didn't find start or end point");
            return null;
        }
        setNeightbours(mazeArray);
        maze.setMaze(mazeArray);

        return maze;



    }

    /**
     * Metodi muuntaa aiemmin konstruktorissa annetun tiedoston
     * kaksiulotteiseksi taulukoksi, ja kutsuu setNeightbours metodia
     * naapureiden asettamiseksi.
     *
     * @return palauttaa mazen joka sisältää 2 ulotteisen taulukon,
     * aloituspisteen ja maalipisteen.
     */
    private void setNeightbours(Node[][] mazeArray) {
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

    /**
     * Metodi käy stackin läpi, hakee nodeista X ja Y koordinaatit ja värjää
     * BufferedImagessa olevan pisteen punaiseksi merkkaamaan reittiä. Lopulta
     * ImageIO. kirjoittaa kuvan halutun nimiseen tiedostoon.
     * @params path reitti maalista alkuun.
     * 
     */
    public void drawPath(Stack<Node> path) {
        try {
            int color = Color.RED.getRGB();
            while (!path.isEmpty()) {

                Node node = path.pop();
                buffImage.setRGB(node.getX(), node.getY(), color);


            }
            File outputfile = new File(savename);
            ImageIO.write(buffImage, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(MazeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
