package com.graduate.ui;

import javax.swing.*;

public class ChangeThemeScreenTest {

    public static void main(String[] args) {
        // Create a new JFrame to hold the change theme screen
        JFrame frame = new JFrame("Change Theme Screen Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an instance of the change theme screen
        ChangeThemeScreen changeThemeScreen = new ChangeThemeScreen();

        // Add the change theme screen to the frame
        frame.add(changeThemeScreen);

        // Maximize the frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}