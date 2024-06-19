package com.graduate.ui;

import javax.swing.*;

public class PauseScreenTest {

    public static void main(String[] args) {
        // Create a new JFrame to hold the pause screen
        JFrame frame = new JFrame("Pause Screen Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the PauseScreen
        PauseScreen pauseScreen = new PauseScreen();

        // Add the pause screen to the frame
        frame.add(pauseScreen);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
