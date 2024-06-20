package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class GamePause extends JPanel {

    public GamePause(Font arcadeFont) {
        // Create title label for game pause
        JLabel pauseTitle = new JLabel("GAME PAUSED");
        pauseTitle.setFont(arcadeFont.deriveFont(120f)); // Set font size for pause title
        pauseTitle.setForeground(Color.BLACK); // Set text color
        pauseTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Create buttons for game pause options
        JButton resumeButton = createStyledButton("RESUME", arcadeFont);
        JButton restartButton = createStyledButton("RESTART LEVEL", arcadeFont);
        JButton quitButton = createStyledButton("QUIT GAME", arcadeFont);

        // Create panel for pause buttons with vertical spacing
        JPanel pausePanel = new JPanel();
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
        pausePanel.setOpaque(false); // Make pause panel transparent
        pausePanel.add(Box.createVerticalStrut(150)); // Add space above the buttons
        pausePanel.add(resumeButton);
        pausePanel.add(Box.createVerticalStrut(20)); // Add space between buttons
        pausePanel.add(restartButton);
        pausePanel.add(Box.createVerticalStrut(20)); // Add space between buttons
        pausePanel.add(quitButton);
        pausePanel.add(Box.createVerticalStrut(150)); // Add space below the buttons

        // Create container panel to properly position the title and buttons
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(Box.createVerticalStrut(100), BorderLayout.NORTH); // Space above pause text
        containerPanel.add(pauseTitle, BorderLayout.CENTER);
        containerPanel.add(Box.createVerticalStrut(50), BorderLayout.SOUTH); // Space below pause text

        // Add components to the main panel
        setLayout(new BorderLayout()); // Set layout for the panel
        setBackground(new Color(0x5CE1E6)); // Set background color
        add(containerPanel, BorderLayout.NORTH); // Add container panel to the top
        add(pausePanel, BorderLayout.CENTER); // Add pause buttons to the center
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90); // Rounded corners with radius 90
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

        Dimension buttonSize = new Dimension(800, 100); // Set preferred button size
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Load custom font (use a default font if arcadeFont is unavailable)
            Font arcadeFont;
            try {
                arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("src/resources/arcade_ya/ARCADE_N.TTF")).deriveFont(50f);
            } catch (Exception e) {
                arcadeFont = new Font("Arial", Font.PLAIN, 50);
            }

            // Create the main frame
            JFrame frame = new JFrame("Game Pause");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize frame to cover the whole screen
            frame.setUndecorated(true); // Remove window borders

            // Add the GamePause panel to the frame
            frame.add(new GamePause(arcadeFont));

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
