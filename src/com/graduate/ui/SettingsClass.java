package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsClass extends JPanel {

    public SettingsClass(Font arcadeFont) {
        // Create title label for settings
        JLabel settingsTitle = new JLabel("SETTINGS");
        settingsTitle.setFont(arcadeFont.deriveFont(120f)); // Set font size for settings title
        settingsTitle.setForeground(Color.BLACK); // Set text color
        settingsTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Create buttons for settings
        JButton soundButton = createStyledButton("SOUND: ON", arcadeFont);
        JButton themeButton = createStyledButton("THEME", arcadeFont);
        JButton characterButton = createStyledButton("CHARACTER", arcadeFont);

        // Create panel for settings buttons with vertical spacing
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setOpaque(false); // Make settings panel transparent
        settingsPanel.add(soundButton);
        settingsPanel.add(Box.createVerticalStrut(20)); // Add space between buttons
        settingsPanel.add(themeButton);
        settingsPanel.add(Box.createVerticalStrut(20)); // Add space between buttons
        settingsPanel.add(characterButton);

        // Create container panel to properly position the title and buttons
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.setOpaque(false);
        containerPanel.add(Box.createVerticalStrut(50), BorderLayout.NORTH); // Space above settings text
        containerPanel.add(settingsTitle, BorderLayout.CENTER);
        containerPanel.add(Box.createVerticalStrut(150), BorderLayout.SOUTH); // Space below settings text

        // Add components to the main panel
        setLayout(new BorderLayout()); // Set layout for the panel
        setBackground(new Color(0x5CE1E6)); // Set background color
        add(containerPanel, BorderLayout.NORTH); // Add container panel to the top
        add(settingsPanel, BorderLayout.CENTER); // Add settings buttons to the center
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

        Dimension buttonSize = new Dimension(800, 100); // Set preferred button size
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
    }
}
