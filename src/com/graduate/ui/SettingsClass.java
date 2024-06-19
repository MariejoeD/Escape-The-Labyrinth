package com.graduate.ui;

import javax.swing.*;
import java.awt.*;

public class SettingsClass extends JPanel {

    private final Font arcadeFont;

    public SettingsClass(Font arcadeFont) {
        this.arcadeFont = arcadeFont;
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        addTitlePanel();
        addSettingsButtonsPanel();
    }

    private void addTitlePanel() {
        JLabel settingsTitle = createTitleLabel();

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(Box.createVerticalStrut(90), BorderLayout.NORTH); // Space above settings title
        titlePanel.add(settingsTitle, BorderLayout.CENTER);
        titlePanel.add(Box.createVerticalStrut(150), BorderLayout.SOUTH); // Space below settings title

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
        buttonPanel.add(Box.createVerticalStrut(90), BorderLayout.NORTH);

        buttonPanel.add(createStyledButton("SOUND: ON"));
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(createStyledButton("THEME"));
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(createStyledButton("CHARACTER"));

        add(buttonPanel, BorderLayout.CENTER);
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
}