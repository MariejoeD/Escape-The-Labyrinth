package com.graduate;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JPanel {

    private SplashScreen splashScreen;

    public GameMenu(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;

        setLayout(new BorderLayout()); // Set layout for the panel
        setBackground(new Color(255, 224, 102)); // Set panel background color

        // Load custom font
        Font arcadeFont = null;
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/arcade_ya/ARCADE_N.TTF"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(arcadeFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create and add back button to the upper left corner
        JButton backButton = new JButton("BACK");
        backButton.setFont(arcadeFont.deriveFont(24f));
        backButton.setBackground(new Color(0x545454)); // Set button background color
        backButton.setForeground(Color.WHITE); // Set button text color
        backButton.setFocusPainted(false); // Disable focus painting
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Set button border with padding

        backButton.addActionListener(e -> {
            // Logic to go back to splash screen
            splashScreen.getPanel().removeAll(); // Remove all components from splash screen panel
            splashScreen.addComponents(); // Add splash screen components
            splashScreen.getPanel().revalidate(); // Refresh the panel
            splashScreen.getPanel().repaint(); // Repaint the panel
        });

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setOpaque(false); // Make back button panel transparent
        backButtonPanel.add(backButton); // Add back button

        // Add back button panel to the top left corner
        add(backButtonPanel, BorderLayout.NORTH); // Add back button panel to the top

        // Add title labels with shadow effect
        ShadowText titleEscape = new ShadowText("ESCAPE THE", SwingConstants.CENTER, 5, 5);
        titleEscape.setForeground(new Color(84, 84, 84)); // Set title text color
        titleEscape.setFont(arcadeFont.deriveFont(100f)); // Set bigger font size for "ESCAPE THE"

        // Create panel to hold the title labels and center them vertically
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Make title panel transparent
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); // Set vertical box layout
        titleEscape.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleEscape); // Add title label for "ESCAPE THE" to the top

        // Create a labyrinth image label
        JLabel labyrinthImage = new JLabel(new ImageIcon("src/Resources/LABYRINTH.png"));
        labyrinthImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a main panel to hold both titlePanel and labyrinthImage
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false); // Make main panel transparent
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Decrease vertical space to 20 pixels
        mainPanel.add(labyrinthImage);

        // Add the main panel to the center
        add(mainPanel, BorderLayout.CENTER);

        // Create buttons for "Difficulty" and "Settings"
        JButton difficultyButton = new JButton("DIFFICULTY");
        difficultyButton.setFont(arcadeFont.deriveFont(60f));
        difficultyButton.setBackground(new Color(255, 224, 102));
        difficultyButton.setFocusPainted(false); // Disable focus painting
        difficultyButton.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40)); // Add padding to the button

        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setFont(arcadeFont.deriveFont(60f));
        settingsButton.setBackground(new Color(255, 224, 102));
        settingsButton.setFocusPainted(false); // Disable focus painting
        settingsButton.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40)); // Add padding to the button

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 40)); // GridLayout with 2 rows, 1 column, and vertical gap of 40
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.add(difficultyButton); // Add difficulty button
        buttonPanel.add(settingsButton); // Add settings button

        // Create a wrapper panel to add vertical space and lift the button panel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(buttonPanel, BorderLayout.NORTH); // Add button panel to the top

        // Add an empty border to add extra space at the bottom
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));

        // Add wrapper panel to the bottom
        add(wrapperPanel, BorderLayout.SOUTH);
    }

    // Custom JLabel class for text with shadow effect
    class ShadowText extends JLabel {

        private int shadowOffsetX;
        private int shadowOffsetY;

        public ShadowText(String text, int horizontalAlignment, int shadowOffsetX, int shadowOffsetY) {
            super(text, horizontalAlignment);
            this.shadowOffsetX = shadowOffsetX;
            this.shadowOffsetY = shadowOffsetY;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Draw shadow
            g2.setColor(Color.GRAY); // Shadow color
            g2.drawString(getText(), shadowOffsetX, getHeight() - shadowOffsetY);

            // Draw text
            g2.setColor(getForeground()); // Text color
            g2.drawString(getText(), 0, getHeight()); // Adjusted the position for actual text

            g2.dispose();
        }
    }
}
