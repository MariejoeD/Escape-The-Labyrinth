package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PauseScreen extends JPanel {

    private Font arcadeFont;
    private GameScreen gameScreen;

    public PauseScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        loadCustomFont();
        addTitlePanel();
        addButtonsPanel();
    }

    public PauseScreen() {

    }

    private void loadCustomFont() {
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/arcade_ya/ARCADE_N.TTF"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(arcadeFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void addTitlePanel() {
        ShadowText titlePaused = new ShadowText("GAME PAUSED", SwingConstants.CENTER, 5, 5);
        titlePaused.setForeground(Color.BLACK);
        titlePaused.setFont(arcadeFont.deriveFont(108f));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(90)); // The space above title
        titlePaused.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titlePaused);
        titlePanel.add(Box.createVerticalStrut(150)); // Space below title

        add(titlePanel, BorderLayout.NORTH);
    }

    private void addButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(90)); // Space above buttons

        buttonPanel.add(createStyledButton("RESUME", e -> gameScreen.resumeGame()));
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(createStyledButton("RESTART LEVEL", e -> gameScreen.restartGame()));
        buttonPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel.add(createStyledButton("QUIT GAME", e -> quitGame()));

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
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

        button.addActionListener(actionListener);

        return button;
    }

    private void restartLevel() {
        // Implement logic to restart the level
    }

    private void quitGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new GameMenu());
        frame.revalidate();
        frame.repaint();
    }

    // Custom JLabel class for text with shadow effect
    static class ShadowText extends JLabel {
        private final int shadowOffsetX;
        private final int shadowOffsetY;

        public ShadowText(String text, int horizontalAlignment, int shadowOffsetX, int shadowOffsetY) {
            super(text, horizontalAlignment);
            this.shadowOffsetX = shadowOffsetX;
            this.shadowOffsetY = shadowOffsetY;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Draw shadow
            g2.setColor(Color.GRAY);
            g2.drawString(getText(), shadowOffsetX, getHeight() - shadowOffsetY);

            // Draw text
            g2.setColor(getForeground());
            g2.drawString(getText(), 0, getHeight() - shadowOffsetY);

            g2.dispose();
        }
    }
}