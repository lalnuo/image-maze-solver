/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.imagemazesolver.ui;

import pro.imagemazesolver.domain.Node;
import pro.imagemazesolver.domain.Maze;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pro.imagemazesolver.datastructures.NodeStack;

/**
 *
 * @author lalli
 */
class MazeMaker {

    private BufferedImage buffImage;
    boolean errorThrown;
    private String savename;
    protected int mazeHeight;
    protected int mazeWidth;

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
            this.mazeHeight = buffImage.getHeight();
            this.mazeWidth = buffImage.getWidth();
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
    public Maze imageToMaze(int algorithm) {
        Maze maze = new Maze();
        Node[][] mazeArray = new Node[mazeHeight][mazeWidth];
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                pixelToNode(i, j, maze, mazeArray);
            }
        }
        if (maze.getStartNode() == null || maze.getEndNode() == null) {
            System.out.println("Didn't find start or end point");
            return null;
        }
        setNeightbours(mazeArray);
        maze.setMaze(mazeArray);

        return maze;
    }

    /**
     * Metodi muuttaa kuvasta otetun pikselin Nodeksi.
     *
     * @param y pikselin Y
     * @param x pikselin X
     * @param maze maze jota käsitellään
     * @param mazeArray mazeen talletettava mazeArray
     * @param initializeValue arvo jolla noden paino alustetaan
     */
    protected void pixelToNode(int y, int x, Maze maze, Node[][] mazeArray) {
        int color;
        color = buffImage.getRGB(x, y);
        Node node = new Node(y, x, new Color(color));
        if (node.getColor().equals(Color.RED)) {
            maze.setStartNode(node);
        } else if (node.getColor().equals(Color.BLUE)) {
            maze.setEndNode(node);
        } else if (node.getColor().getRGB() < -100000) {
            node.setWall(true);
        }
        node.setWeight(Integer.MAX_VALUE);
        mazeArray[y][x] = node;
    }

    /**
     * Metodi laskee mazeArrayn jokaiselle nodelle naapurinodet ja asettaa ne
     * kyseisen noden vieruslistaan.
     *
     * @param mazeArray kaksiulotteinen taulukko joka sisältää nodet
     */
    private void setNeightbours(Node[][] mazeArray) {
        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                Node node = mazeArray[i][j];

                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (!overflow(node.getX() + l, node.getY() + k) && !(k == 0 && j == 0)) {
                            node.addNaapuri(mazeArray[node.getY() + k][node.getX() + l]);
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodi tarkastaa meneekö koordinaatit mazen rajojen yli
     *
     * @param x x koordinaatti
     * @param y y koordinaati
     * @return menikö yli vai ei
     */
    public boolean overflow(int x, int y) {
        if (x >= 0 && x < mazeWidth && y < mazeHeight && y >= 0) {
            return false;
        }
        return true;
    }

    /**
     * Metodi käy stackin läpi, hakee nodeista X ja Y koordinaatit ja värjää
     * BufferedImagessa olevan pisteen punaiseksi merkkaamaan reittiä. Lopulta
     * ImageIO. kirjoittaa kuvan halutun nimiseen tiedostoon.
     *
     * @params path reitti maalista alkuun.
     *
     */
    public void drawPath(NodeStack path) {
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
    /**
     * Metodi palauttaa algoritmiin tarvittavan alustusarvon nodeille.
     *
     * @param algorithm Käyttäjän valitsema algoritmi
     * @return arvo jolla nodet tullaan alustamaan
     */
}
