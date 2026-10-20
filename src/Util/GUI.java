package Util;
import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    JTextArea outputConsoleResponse;
    JTextArea mapDisplay;
    CardLayout cardLayout;
    JPanel cardPanel;
    JTextField inputUserCommand;
    public GUI() {
        this.setTitle("Dungeon Crawler Terran V1");//Sets frame title
        ImageIcon icon = new ImageIcon("Resource/Images/Brain.png");//Locates image in files
        this.setIconImage(icon.getImage());//Sets Imageicon to chosen image
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
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        //Code for the console output into the GUI display
        outputConsoleResponse = new JTextArea();
        outputConsoleResponse.setBackground(Color.BLACK);
        outputConsoleResponse.setForeground(Color.GREEN);
        outputConsoleResponse.setFont(new Font("Consolas", Font.PLAIN, 17));
        outputConsoleResponse.setEditable(false);
        outputConsoleResponse.setLineWrap(true);
        outputConsoleResponse.setWrapStyleWord(true);
        //Allows for scrolling on previous output
        JScrollPane mainScrollPane = new JScrollPane(outputConsoleResponse);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        mainScrollPane.setBorder(null);
        //Code for the user to input commands into the GUI display
        inputUserCommand = new JTextField();
        inputUserCommand.setBackground(Color.BLACK);
        inputUserCommand.setForeground(Color.GREEN);
        inputUserCommand.setCaretColor(Color.WHITE);
        inputUserCommand.setFont(new Font("Consolas", Font.PLAIN, 17));
        inputUserCommand.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
        //Code for the map display window to be toggled
        mapDisplay = new JTextArea();
        mapDisplay.setBackground(Color.BLACK);
        mapDisplay.setForeground(Color.GREEN);
        mapDisplay.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 17));
        mapDisplay.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
        mapDisplay.setLineWrap(false);
        mapDisplay.setWrapStyleWord(true);
        mapDisplay.setVisible(true);
        //Set scroll pane for text Area
        JScrollPane secondScrollPane = new JScrollPane(mapDisplay);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBorder(null);
        //Add scroll panels to card layout
        cardPanel.add(mainScrollPane);
        cardPanel.add(secondScrollPane);
        //Sets the layout for everything
        this.setLayout(new BorderLayout());
        //Sets the scroller to the middle section of the layout
        this.add(cardPanel, BorderLayout.CENTER);
        //adds user input box to the bottom of the display
        this.add(inputUserCommand, BorderLayout.SOUTH);
        //Activates the new window
        this.setVisible(true);
        //Creates listener for user input
    }
    public void toggle() {
        //Clears the mapDisplay so it is not displaying previous versions of the map
        mapDisplay.setText(" ");
        //Flips the panels, 1 displays the rest of the game, the other just the map.
        cardLayout.next(cardPanel);
    }
    public JTextArea getMapDisplay() {
        return mapDisplay;
    }
    public JTextArea getOutputConsoleResponse() {
        return outputConsoleResponse;
    }
    public JTextField getInputUserCommand() {
        return inputUserCommand;
    }
}
