package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class StartClass extends JPanel {

    private static int [][] maze;
    private static int [] playerPos;
    
    public static int[] getPlayerPos() {
        return playerPos;
    }

    public static int[][] getMaze() {
        return maze;
    }

    public StartClass(Font arcadeFont, ImageIcon easyIcon, ImageIcon mediumIcon, ImageIcon extremeIcon, ImageIcon timerIcon500, ImageIcon timerIcon1500, ImageIcon timerIcon6000) {
        setLayout(new BorderLayout());
        setBackground(new Color(0x5CE1E6)); // Set panel background color

        // Create a title label with space above and below
        JLabel title = new JLabel("CHOOSE DIFFICULTY", SwingConstants.CENTER);
        title.setFont(arcadeFont.deriveFont(80f));
        title.setForeground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setOpaque(false);

        // Create a panel for the back button and add it to the title panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        JButton backButton = createBackButton(arcadeFont);
        backButton.addActionListener(e -> navigateBack());

        // Create a container panel with padding for the back button
        JPanel backButtonContainer = new JPanel(new BorderLayout());
        backButtonContainer.setOpaque(false);
        backButtonContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0)); // Add top and left space
        backButtonContainer.add(backButton, BorderLayout.WEST);

        topPanel.add(backButtonContainer, BorderLayout.WEST);

        titlePanel.add(topPanel, BorderLayout.NORTH);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(Box.createVerticalStrut(100), BorderLayout.SOUTH); // Increased space below the text
        add(titlePanel, BorderLayout.NORTH);

        // Create panel for maze difficulty levels
        JPanel mazePanel = new JPanel();
        mazePanel.setLayout(new GridLayout(1, 3, 20, 0));
        mazePanel.setOpaque(false);

        // Add maze images and difficulty labels
        mazePanel.add(createMazePanel("EASY", easyIcon, timerIcon500, arcadeFont, new Color(0x4CAF50)));
        mazePanel.add(createMazePanel("MEDIUM", mediumIcon, timerIcon1500, arcadeFont, new Color(0xFF9800)));
        mazePanel.add(createMazePanel("EXTREME", extremeIcon, timerIcon6000, arcadeFont, new Color(0xF44336)));

        add(mazePanel, BorderLayout.CENTER);
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

        button.addActionListener(e -> {
            System.out.println("Button clicked: " + difficulty); // Example action: printing to console
            // Replace the above line with whatever action you want to perform on click
            switch(difficulty) {
                case "EASY":
                    maze = new int[][]{
                        {1,1,1},
                        {1,0,1},
                        {1,1,1}
                    };
                    playerPos = new int[]{1,1};
                    break;
                case "MEDIUM":
                    maze = new int[][]{
                        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                    };
                    playerPos = new int[]{5,0};
                    break;
                    
            }

            GameScreen.gameStart();
        });

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

    private JButton createBackButton(Font font) {
        JButton backButton = new JButton("Back");
        backButton.setFont(font.deriveFont(20f));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        return backButton;
    }

    private void navigateBack() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new GameMenu()); // Assuming GameMenu is another JPanel
        frame.revalidate();
        frame.repaint();
    }
}
