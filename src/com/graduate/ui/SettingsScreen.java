package com.graduate.ui;

import com.graduate.util.SoundPlayer;
import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JPanel {

    private final Font arcadeFont;
    private JButton soundButton;

    public SettingsScreen(Font arcadeFont) {
        this.arcadeFont = arcadeFont;
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        addTitlePanel();
        addSettingsButtonsPanel();
        updateSoundButton();
    }

    private void addTitlePanel() {
        JLabel settingsTitle = createTitleLabel();

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());

        // Create a panel for the back button and add it to the title panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        JButton backButton = createBackButton();
        backButton.addActionListener(e -> navigateBack());

        // Create a container panel with padding for the back button
        JPanel backButtonContainer = new JPanel(new BorderLayout());
        backButtonContainer.setOpaque(false);
        backButtonContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0)); // Add top and left space
        backButtonContainer.add(backButton, BorderLayout.WEST);

        topPanel.add(backButtonContainer, BorderLayout.WEST);

        titlePanel.add(topPanel, BorderLayout.NORTH);
        titlePanel.add(settingsTitle, BorderLayout.CENTER);
        titlePanel.add(Box.createVerticalStrut(110), BorderLayout.SOUTH); // Space below settings title

        add(titlePanel, BorderLayout.NORTH);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("SETTINGS");
        titleLabel.setFont(arcadeFont.deriveFont(108f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return titleLabel;
    }

    private void addSettingsButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(90)); // Space above buttons

        soundButton = createStyledButton("SOUND: ON");
        soundButton.addActionListener(e -> toggleSound());

        buttonPanel.add(soundButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons

        JButton themeButton = createStyledButton("THEME");
        themeButton.addActionListener(e -> navigateToChangeThemeScreen());
        buttonPanel.add(themeButton);

        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons

        JButton characterButton = createStyledButton("CHARACTER");
        characterButton.addActionListener(e -> navigateToChangeCharacterScreen());
        buttonPanel.add(characterButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void toggleSound() {
        SoundPlayer.setSoundOn(!SoundPlayer.isSoundOn());
        updateSoundButton();
    }

    private void updateSoundButton() {
        if (SoundPlayer.isSoundOn()) {
            soundButton.setText("SOUND: ON");
        } else {
            soundButton.setText("SOUND: OFF");
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90);
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

        button.setFont(arcadeFont.deriveFont(50f));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.BLACK);

        Dimension buttonSize = new Dimension(800, 100); // Set preferred button size
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
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

    private void navigateBack() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new GameMenu()); // No need to pass arcadeFont, GameMenu loads its own font
        frame.revalidate();
        frame.repaint();
    }

    private void navigateToChangeThemeScreen() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new ChangeThemeScreen());
        frame.revalidate();
        frame.repaint();
    }

    private void navigateToChangeCharacterScreen() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new ChangeCharacterScreen());
        frame.revalidate();
        frame.repaint();
    }
}
