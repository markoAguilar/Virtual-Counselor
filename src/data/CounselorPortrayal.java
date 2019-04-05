package data;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CounselorPortrayal extends JComponent {
    private String state_str;
    //private Image expression_image;
    private ClassLoader classLoader;
    private File fileToLoad;
    private BufferedImage expression_image;

    public CounselorPortrayal(){
        state_str = "greet";
        getExpression(state_str);
        //setPreferredSize(new Dimension(500,500));
    }

    public CounselorPortrayal(String stateIn){
        classLoader = getClass().getClassLoader();
        state_str = stateIn;
        getExpression(state_str);
    }

    // YOU MIGHT FACE PROBLEMS HERE/////////////////////////////////////////////////////////////////
    public void getExpression(String state){
        if(state_str.equals("greet")) {
            try { //Note&ask why here your filename requires '/'
                expression_image = ImageIO.read(getClass().getResource("/coverFace.jpg"));
            }
            catch (IOException exception){
                System.out.println("Image not read!!");
            }
        }
        if(state_str.equals("speak")) {
            try {
                expression_image = ImageIO.read(getClass().getResource("/speakFace.jpg"));
            }
            catch (IOException exception){
                System.out.println("Image not read!!");
            }
        }
        if(state_str.equals("reject")) {
            try {
                expression_image = ImageIO.read(getClass().getResource("/failFace.jpg"));
            }
            catch (IOException exception){
                System.out.println("Image not read!!");
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(expression_image, 10,10, this);
        g2.finalize();
    }
}
