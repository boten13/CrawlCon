package Util;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GUI extends JFrame {
    public GUI() {
        this.setTitle("Dungeon Crawler Terran V1"); //Sets frame title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits out of the application, but default HIDESONCLOSE
        this.setResizable(false); //Prevents frame from being resized
        this.setVisible(true);//Makes the frame visible
        //Checks the screen size running the application
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Converts the size of the display frame to 75% of the screen size game is playing on
        int frameWidth = (int) (screenSize.getWidth() * 0.50);
        int frameHeight = (int) (screenSize.getHeight() * 0.75);
        //Sets the display size
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);//Sets in the center of the screen
    }
}
