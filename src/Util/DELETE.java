package Util;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;

public class DELETE extends JFrame {

    private JTextArea mainTextArea;
    private JTextArea overlayTextArea;
    private JLayeredPane layeredPane;

    // The keyword used to toggle the overlay
    private final String TOGGLE_KEYWORD = "/toggle";

    public DELETE() {
        setTitle("Text Area Overlay Toggle Example");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Create the Layered Pane to handle overlapping components
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Manual bounds management for absolute positioning

        // 2. Base Layer: Main Text Area inside a Scroll Pane
        mainTextArea = new JTextArea("Type '" + TOGGLE_KEYWORD + "' and press Enter to toggle the overlay.\n");
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);

        JScrollPane mainScrollPane = new JScrollPane(mainTextArea);
        mainScrollPane.setBounds(10, 10, 460, 340);
        // Add to DEFAULT_LAYER (bottom)
        layeredPane.add(mainScrollPane, JLayeredPane.DEFAULT_LAYER);

        // 3. Overlay Layer: The toggleable Text Area
        overlayTextArea = new JTextArea("--- OVERLAY TEXT AREA ---\nThis sits on top of the main area.");
        overlayTextArea.setBackground(new Color(240, 240, 240, 235)); // Slightly transparent gray
        overlayTextArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        overlayTextArea.setLineWrap(true);

        JScrollPane overlayScrollPane = new JScrollPane(overlayTextArea);
        overlayScrollPane.setBounds(50, 50, 360, 200);
        overlayScrollPane.setVisible(false); // Hidden by default
        // Add to PALETTE_LAYER (higher up than default)
        layeredPane.add(overlayScrollPane, JLayeredPane.PALETTE_LAYER);

        // 4. Map the Enter key to check for the prompt keyword
        mainTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "checkKeyword");
        mainTextArea.getActionMap().put("checkKeyword", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnterPress(overlayScrollPane);
            }
        });
        add(layeredPane);
    }

    private void handleEnterPress(JScrollPane overlay) {
        String text = mainTextArea.getText();

        // Check if the current text ends with our keyword before the enter action
        if (text.trim().endsWith(TOGGLE_KEYWORD)) {
            // Remove the keyword from the text area so it doesn't clutter the view
            int lastIndex = text.lastIndexOf(TOGGLE_KEYWORD);
            mainTextArea.setText(text.substring(0, lastIndex).trim());

            // Toggle the visibility of the overlay layer
            overlay.setVisible(!overlay.isVisible());
        } else {
            // If it's not the keyword, just insert a regular newline
            mainTextArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DELETE().setVisible(true);
        });
    }
}