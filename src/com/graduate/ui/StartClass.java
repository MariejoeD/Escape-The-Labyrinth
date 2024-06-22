package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class StartClass extends JPanel {

    private Font arcadeFont;

    public StartClass(Font arcadeFont, ImageIcon easyIcon, ImageIcon mediumIcon, ImageIcon extremeIcon, ImageIcon timerIcon500, ImageIcon timerIcon1500, ImageIcon timerIcon6000) {
        this.arcadeFont = arcadeFont;
        setLayout(new BorderLayout());
        setBackground(new Color(0x5CE1E6)); // Set panel background color

        // Create a back button
        JButton backButton = createBackButton();
        backButton.addActionListener(e -> navigateBack());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0)); // Adjust top and left space

        // Create a container panel with padding for the back button
        JPanel backButtonContainer = new JPanel(new BorderLayout());
        backButtonContainer.setOpaque(false);
        backButtonContainer.add(backButton, BorderLayout.NORTH);

        topPanel.add(backButtonContainer, BorderLayout.WEST);

        // Create a title label
        JLabel title = new JLabel("CHOOSE DIFFICULTY", SwingConstants.CENTER);
        title.setFont(arcadeFont.deriveFont(58f));
        title.setForeground(Color.BLACK);

        // Create panel for title
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Adjust top and bottom padding
        titlePanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.CENTER);


        // Create panel for maze difficulty levels
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(1, 3, 20, 0));
        mazePanel.setOpaque(false);

        // Add maze images and difficulty labels
        mazePanel.add(createMazePanel("EASY", easyIcon, timerIcon500, arcadeFont, new Color(0x4CAF50)));
        mazePanel.add(createMazePanel("MEDIUM", mediumIcon, timerIcon1500, arcadeFont, new Color(0xFF9800)));
        mazePanel.add(createMazePanel("EXTREME", extremeIcon, timerIcon6000, arcadeFont, new Color(0xF44336)));

        add(mazePanel, BorderLayout.SOUTH);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setFont(arcadeFont.deriveFont(20f));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        return backButton;
    }

    private JPanel createMazePanel(String difficulty, ImageIcon icon, ImageIcon timeIcon, Font font, Color buttonColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel mazeLabel = new JLabel(icon);
        mazeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel(timeIcon);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = createRoundedButton(difficulty, font, buttonColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(mazeLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(timeLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(button);

        return panel;
    }

    private JButton createRoundedButton(String text, Font font, Color backgroundColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70); // Rounded corners with radius 70
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

        button.setFont(font.deriveFont(20f));
        button.setBackground(backgroundColor);
        button.setFocusPainted(false); // Disable focus painting
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        return button;
    }

    private void navigateBack() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new GameMenu()); // No need to pass arcadeFont, GameMenu loads its own font
        frame.revalidate();
        frame.repaint();
    }
}
