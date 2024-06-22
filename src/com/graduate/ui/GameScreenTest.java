package com.graduate.ui;

import javax.swing.*;

public class GameScreenTest {

    public static void main(String[] args) {
        // Create a new JFrame to hold the game screen
        JFrame frame = new JFrame("Game Screen Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the PauseScreen
        GameScreen gameScreen = new GameScreen();

        // Add the pause screen to the frame
        frame.add(gameScreen);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
