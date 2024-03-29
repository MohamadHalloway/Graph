package de.jpp.io;

import de.jpp.factory.GraphFactory;
import de.jpp.io.interfaces.GraphReader;
import de.jpp.io.interfaces.ParseException;
import de.jpp.model.TwoDimGraph;
import de.jpp.model.XYNode;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.util.Optional;

public class TwoDimImgReader implements GraphReader<XYNode,Double, TwoDimGraph, BufferedImage> {
    private final Optional<Double> distance = Optional.of(1.0);

    public TwoDimImgReader() {
    }

    @Override
    public TwoDimGraph read(BufferedImage image){
        GraphFactory graphFactory = new GraphFactory();
        TwoDimGraph twoDimGraph =   graphFactory.createNewTwoDimGraph();
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 1; i < height - 1 ; i++) {
            for (int j = 1; j < width - 1; j++) {
                if(calculateHSB(image.getRGB(j,i)) >= 0.5){ //if bright
                    XYNode aktuell = new XYNode("",j,i);
                    twoDimGraph.addNode(aktuell);
                    if (checkRechts(j,i,width,image)){
                        XYNode rechts = new XYNode("",j+1,i);
                        twoDimGraph.addEdge(aktuell,rechts,distance);
                        twoDimGraph.addEdge(rechts,aktuell,distance);
                    }
                    if (checkUnten(j,i,height,image)){
                        XYNode unten = new XYNode("",j,i+1);
                        twoDimGraph.addEdge(aktuell,unten,distance);
                        twoDimGraph.addEdge(unten,aktuell,distance);
                    }
                }
            }
        }
        return twoDimGraph;
    }

    private boolean checkUnten(int x, int y, int height, BufferedImage image) {
        if (y == height - 1){        //if last row
            return false;
        }
        if (calculateHSB(image.getRGB(x,y + 1)) < 0.5){      //if not bright
            return false;
        }
        return true;
    }

    private boolean checkRechts(int x,int y,int width, BufferedImage image) {
        if (x == width - 1){        //if last row
            return false;
        }
        if (calculateHSB(image.getRGB(x + 1,y)) < 0.5){      //if not bright
            return false;
        }
        return true;
    }

    private float calculateHSB(int RGB){
        int red = (RGB >> 16) & 0xFF;
        int green = (RGB >> 8) & 0xFF;
        int blue = RGB & 0xFF;
        float[] hsb =  Color.RGBtoHSB(red,green,blue,null);
        return hsb[2];
    }





    public static void main(String[] args) throws ParseException {
        //read image
        try{
            File f = new File("TestFiles/img/valid/maze_small.png");
            BufferedImage img = ImageIO.read(f);
            TwoDimImgReader twoDimImgReader = new TwoDimImgReader();
            TwoDimGraph twoDimGraph = twoDimImgReader.read(img);
            System.out.println(true);
        }catch(Exception e){
            System.out.println("test");
        }
    }
}


