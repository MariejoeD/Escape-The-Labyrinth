package com.graduate.ui;

import javax.swing.*;

import com.graduate.game.MazeWithSprite;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JPanel {

    private ImageIcon pauseIcon;
    private ImageIcon hourglassIcon;
    private JLabel timerLabel;

    public GameScreen() {
        // Load the icons
        pauseIcon = new ImageIcon(new ImageIcon("src/resources/images/pause.png").getImage().getScaledInstance(100, 100,
                Image.SCALE_SMOOTH));
        hourglassIcon = new ImageIcon(new ImageIcon("src/resources/images/hourglass.png").getImage()
                .getScaledInstance(55, 55, Image.SCALE_SMOOTH));

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
    public static void gameStart() {
        JFrame frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame.setSize(1000, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        JPanel mainPanel = new JPanel();
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Now that the frame is visible, we can safely get the size
                Dimension contentPaneSize = frame.getContentPane().getSize();
                int width = contentPaneSize.width;
                int height = contentPaneSize.height;
                int panelSize = Math.min(width, height); // Use the smaller dimension to keep the maze square
                int x = (width - panelSize) / 2;
                
                //Set Maze
                MazeWithSprite.setMaze(StartClass.getMaze());
                MazeWithSprite.setPlayerPos(StartClass.getPlayerPos());
                MazeWithSprite.mazeInit(panelSize);
                mainPanel.setBounds(x, 0, panelSize, panelSize);
                mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze(); // Assuming you have a method to redraw the maze

                // Refresh the mainPanel to reflect the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        frame.add(mainPanel);
        frame.add(new GameScreen());
        mainPanel.add(MazeWithSprite.mazePanel);
        frame.setVisible(true);
    }
    public static void gameStartnoSetMaze() {
        JFrame frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        JPanel mainPanel = new JPanel();
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Now that the frame is visible, we can safely get the size
                Dimension contentPaneSize = frame.getContentPane().getSize();
                int width = contentPaneSize.width;
                int height = contentPaneSize.height;
                int panelSize = Math.min(width, height); // Use the smaller dimension to keep the maze square
                int x = (width - panelSize) / 2;
                
                //Set Maze
                MazeWithSprite.mazeInit(panelSize);
                mainPanel.setBounds(x, 0, panelSize, panelSize);
                mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze(); // Assuming you have a method to redraw the maze

                // Refresh the mainPanel to reflect the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        frame.add(mainPanel);
        frame.add(new GameScreen());
        mainPanel.add(MazeWithSprite.mazePanel);

        

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        gameStartnoSetMaze();
    }
}
