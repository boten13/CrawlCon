package Util;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        this.setTitle("Dungeon Crawler Terran V1"); //Sets frame title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits out of the application, but default HIDESONCLOSE
        this.setResizable(false); //Prevents frame from being resized
        this.setSize(1000, 1000); //Sets the size of the frame, (x dem, y dem)
        this.setVisible(true); //Makes the frame visible

//        ImageIcon image = new ImageIcon(File Path or FIle Name); // Creates an image icon
//        frame.setIconImage(image.getImage()); //sets the image icon for the frame
        this.getContentPane().setBackground(Color.CYAN); //Change color of background (new Color()) Custom color
    }
}
