package com.graduate.ui;

import javax.swing.*;

public class GameOverScreenTest {

    public static void main(String[] args) {
        // Create a new JFrame to hold the game over the screen
        JFrame frame = new JFrame("Game Over Screen Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the game over
        GameOverScreen gameOverScreen = new GameOverScreen();

        // Add the game over screen to the frame
        frame.add(gameOverScreen);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
