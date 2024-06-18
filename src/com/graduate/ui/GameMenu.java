package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JPanel {

    private SplashScreen splashScreen;
    private Font arcadeFont; // Declare arcadeFont as a class member

    public GameMenu(SplashScreen splashScreen) {
        this.splashScreen = splashScreen;

        setLayout(new BorderLayout()); // Set layout for the panel
        setBackground(new Color(0x5CE1E6)); // Set panel background color

        // Load custom font
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/arcade_ya/ARCADE_N.TTF"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(arcadeFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Add title labels with shadow effect
        ShadowText titleEscape = new ShadowText("ESCAPE THE", SwingConstants.CENTER, 5, 5);
        titleEscape.setForeground(Color.black); // Set title text color
        titleEscape.setFont(arcadeFont.deriveFont(90f)); // Set bigger font size for "ESCAPE THE"

        // Create panel to hold the title labels and center them vertically
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Make title panel transparent
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS)); // Set vertical box layout
        titleEscape.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleEscape); // Add title label for "ESCAPE THE" to the top

        // Create a labyrinth image label
        JLabel labyrinthImage = new JLabel(new ImageIcon("src/resources/images/LABYRINTH.png"));
        labyrinthImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a main panel to hold both titlePanel and labyrinthImage
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false); // Make main panel transparent
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Decrease vertical space to 20 pixels
        mainPanel.add(labyrinthImage);

        // Add the main panel to the center
        add(mainPanel, BorderLayout.CENTER);

        // Create buttons for "Start" and "Settings"
        JButton startButton = createStyledButton("START", arcadeFont);
        JButton settingsButton = createStyledButton("SETTINGS", arcadeFont);

        // Add action listeners to buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new class for Start
                // Replace StartClass with the actual class name you want to open for Start
                StartClass start = new StartClass();
                // Call method to start the game or do whatever is needed

            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new class for Settings
                // Replace SettingsClass with the actual class name you want to open for Settings
                SettingsClass settings = new SettingsClass(arcadeFont);
                // Clear current panel and add settings panel
                removeAll();
                add(settings);
                revalidate();
                repaint();
            }
        });

        // Create panel for buttons and center it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.add(startButton); // Add start button
        buttonPanel.add(Box.createVerticalStrut(30)); // Add space between buttons
        buttonPanel.add(settingsButton); // Add settings button
        buttonPanel.add(Box.createVerticalStrut(200)); // Add space below the buttons

        // Center the button panel
        JPanel centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.add(buttonPanel);

        // Add centered panel to the bottom
        add(centeredPanel, BorderLayout.SOUTH);
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90); // Rounded corners with radius 30
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
            }
        };

        button.setFont(font.deriveFont(50f));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false); // Disable focus painting
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.BLACK);
        return button;
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
