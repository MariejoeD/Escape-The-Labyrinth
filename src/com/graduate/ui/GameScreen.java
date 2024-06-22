package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private ImageIcon pauseIcon;
    private ImageIcon hourglassIcon;
    private JLabel timerLabel;

    public GameScreen() {
        // Load the icons
        pauseIcon = new ImageIcon(new ImageIcon("src/resources/images/pause.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        hourglassIcon = new ImageIcon(new ImageIcon("src/resources/images/hourglass.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH));

        // Set the background color
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());

        // Create the top panel for the pause button and timer
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        // Create the pause button
        JButton pauseButton = new JButton(pauseIcon);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setOpaque(false);

        // Add the pause button to the left of the top panel
        topPanel.add(pauseButton, BorderLayout.WEST);

        // Create the timer label with the hourglass icon
        JPanel timerPanel = new JPanel();
        timerPanel.setOpaque(false);
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40)); // Add space to the right

        JLabel hourglassLabel = new JLabel(hourglassIcon);
        timerLabel = new JLabel("2:00");
        timerLabel.setFont(new Font("Consolas", Font.PLAIN, 36));
        timerLabel.setForeground(Color.BLACK);

        timerPanel.add(hourglassLabel);
        timerPanel.add(Box.createHorizontalStrut(5));
        timerPanel.add(timerLabel);

        // Add the timer panel to the right of the top panel
        topPanel.add(timerPanel, BorderLayout.EAST);

        // Add the top panel to the main panel
        add(topPanel, BorderLayout.NORTH);
    }

    // Method to update the timer label
    public void updateTimer(String time) {
        timerLabel.setText(time);
    }

    // Example main method to test the UI
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GameScreen());
        frame.setVisible(true);
    }
}
