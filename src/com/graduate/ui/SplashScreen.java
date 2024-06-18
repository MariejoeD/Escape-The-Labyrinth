package com.graduate.ui;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SplashScreen extends JFrame {

    private JPanel mainPanel;

    // Constructor
    public SplashScreen() {
        initializeFrame(); // Initialize frame properties
        mainPanel = new JPanel(new BorderLayout()); // Create the main panel
        addComponents();   // Add components to the main panel
        add(mainPanel); // Add main panel to the frame
        setVisible(true); // Make the frame visible
        startTimer(); // Start the 5-second timer
    }

    // Method to initialize frame properties
    private void initializeFrame() {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation
        setTitle(""); // Set frame title
        setResizable(false); // Disable frame resizing
        setLocationRelativeTo(null); // Center the frame on the screen
        try {
            // Load custom font
            Font arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/arcade_ya/ARCADE_N.TTF")).deriveFont(96f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(arcadeFont); // Register the font
            UIManager.put("Label.font", arcadeFont); // Set default font for JLabel
            UIManager.put("Button.font", arcadeFont.deriveFont(24f)); // Set default font for JButton with smaller size
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Handle font loading exception
        }
    }

    public void addComponents() {
        mainPanel.setBackground(new Color(0x5CE1E6)); // Set panel background color to #5CE1E6

        // Create title labels with shadow effect
        ShadowText titleEscape = new ShadowText("ESCAPE THE", SwingConstants.CENTER, 5, 5);
        titleEscape.setForeground(Color.black); // Set title text color
        titleEscape.setFont(titleEscape.getFont().deriveFont(90f)); // Set bigger font size for "ESCAPE THE"

        // Create panel to hold the title labels and center them vertically
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false); // Make title panel transparent

        // Add vertical space above the title
        titlePanel.add(Box.createVerticalStrut(200)); // Adjust the space as needed
        titleEscape.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleEscape); // Add title label for "ESCAPE THE"

        JLabel labyrinthImage = new JLabel(new ImageIcon("src/resources/images/LABYRINTH.png"), SwingConstants.CENTER); // Create image label
        labyrinthImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the title panel
        titlePanel.add(Box.createVerticalStrut(10)); // Adjust the space between the text and the image
        titlePanel.add(labyrinthImage); // Add image below the text

        // Add title panel to the main panel
        mainPanel.add(titlePanel, BorderLayout.CENTER); // Add title panel to the center
    }

    private void startTimer() {
        Timer timer = new Timer(5000, e -> {
            // Replace components with game menu after 5 seconds
            mainPanel.removeAll(); // Remove all components from the panel
            addGameMenu(); // Add game menu components
            revalidate(); // Refresh the frame
            repaint(); // Repaint the frame
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Start the timer
    }

    private void addGameMenu() {
        GameMenu gameMenu = new GameMenu(this);
        mainPanel.add(gameMenu, BorderLayout.CENTER);
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
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

            // Draw shadow
            g2.setColor(Color.GRAY); // Shadow color
            g2.drawString(getText(), shadowOffsetX, getHeight() - shadowOffsetY);

            // Draw text
            g2.setColor(getForeground()); // Text color
            g2.drawString(getText(), 0, getHeight() - shadowOffsetY);

            g2.dispose();
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
