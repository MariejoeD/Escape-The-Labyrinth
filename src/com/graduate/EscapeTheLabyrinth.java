package com.graduate;

import com.graduate.ui.SplashScreen;

import javax.swing.*;

public class EscapeTheLabyrinth {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new); // Start the game
    }
}