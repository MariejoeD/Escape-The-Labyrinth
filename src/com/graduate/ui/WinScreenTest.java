package com.graduate.ui;

import javax.swing.*;

public class WinScreenTest {

    public static void main(String[] args) {
        // Create a new JFrame to hold the win screen
        JFrame frame = new JFrame("Win Screen Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the WinScreen
        WinScreen winScreen = new WinScreen();

        // Add the pause screen to the frame
        frame.add(winScreen);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
