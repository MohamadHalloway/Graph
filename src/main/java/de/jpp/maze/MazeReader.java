package de.jpp.maze;

import de.jpp.io.TwoDimImgReader;
import de.jpp.io.interfaces.GraphReader;
import de.jpp.io.interfaces.ParseException;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MazeReader implements GraphReader<XYNode, Double, TwoDimGraph, Maze> {

    public static BufferedImage mazeAsImage(Maze maze){
        int width  = (maze.getWidth()  * 2) + 1;
        int height = (maze.getHeight() * 2) + 1;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);

        int[] linksRechts = {0,width-1};
        int[] obenUnten = {0,height-1};
        int black = Color.BLACK.getRGB();
        int white = Color.WHITE.getRGB();

        //Rand
        for (int i = 0; i < height; i++) {
            for (int index: linksRechts) {
                bufferedImage.setRGB(index,i,black);
            }
        }
        for (int j = 0; j < width; j++) {
            for (int index:obenUnten) {
                bufferedImage.setRGB(j,index,black);
            }
        }
//
//        //HWall
//        int x = 3;
//        int y = 2;
//        for (int i = 0; i < maze.getHeight(); i++) {
//            for (int j = 0; j < maze.getWidth(); j++) {
//                if (maze.isHWallActive(j,i)){
//                    bufferedImage.setRGB(x,y,black);
//                }
//                else {
//                    bufferedImage.setRGB(x,y,white);
//                }
//                x += 2;
//            }
//            y += 2;
//        }
//
//        //VWall
//        x = 2;
//        y = 3;
//        for (int i = 0; i < maze.getHeight(); i++) {
//            for (int j = 0; j < maze.getWidth(); j++) {
//                if (maze.isVWallActive(j,i)){
//                    bufferedImage.setRGB(x,y,black);
//                }
//                else {
//                    bufferedImage.setRGB(x,y,white);
//                }
//                x += 2;
//            }
//            y += 2;
//        }
        boolean[][] cell = maze.getCell();
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (cell[i-1][j-1]){
                    bufferedImage.setRGB(j,i,black);
                }
                else{
                    bufferedImage.setRGB(j,i,white);

                }
            }
        }
        return bufferedImage;
    }

    @Override
    public TwoDimGraph read(Maze input) throws ParseException {
        BufferedImage bufferedImage = MazeReader.mazeAsImage(input);
        TwoDimImgReader twoDimImgReader = new TwoDimImgReader();
        return twoDimImgReader.read(bufferedImage);
    }

    public static void main(String[] args) {
        MazeImpl maze = new MazeImpl(new Random(),5,3);
        BufferedImage bufferedImage = MazeReader.mazeAsImage(maze);
        File f = new File("TestFiles/img/testMazeReader.png");
        try {
            ImageIO.write(bufferedImage,"png",f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
