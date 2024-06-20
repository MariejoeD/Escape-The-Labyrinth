package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JPanel {

    private Font arcadeFont;

    public GameMenu() {
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        loadCustomFont();
        addTitlePanel();
        addButtonsPanel();
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
        ShadowText titleEscape = new ShadowText("ESCAPE THE", SwingConstants.CENTER, 5, 5);
        titleEscape.setForeground(Color.BLACK);
        titleEscape.setFont(arcadeFont.deriveFont(90f));

        JLabel labyrinthImage = new JLabel(new ImageIcon("src/resources/images/LABYRINTH.png"));
        labyrinthImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        titlePanel.add(Box.createVerticalStrut(70));
        titleEscape.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleEscape);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(labyrinthImage);

        add(titlePanel, BorderLayout.CENTER);
    }

    private void addButtonsPanel() {
        JButton startButton = createStyledButton("START", arcadeFont, e -> handleStartButtonClick());
        JButton settingsButton = createStyledButton("SETTINGS", arcadeFont, e -> handleSettingsButtonClick());
        JButton quitButton = createStyledButton("QUIT GAME", arcadeFont, e -> handleQuitButtonClick());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(settingsButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(quitButton);
        buttonPanel.add(Box.createVerticalStrut(120));

        JPanel combinedPanel = new JPanel();
        combinedPanel.setOpaque(false);
        combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
        combinedPanel.add(Box.createVerticalGlue());
        combinedPanel.add(Box.createVerticalStrut(50));
        combinedPanel.add(buttonPanel);
        combinedPanel.add(Box.createVerticalGlue());

        JPanel centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.add(combinedPanel);

        add(centeredPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Font font, ActionListener actionListener) {
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
        button.setFont(font.deriveFont(50f));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.BLACK);
        button.addActionListener(actionListener);
        return button;
    }

    private void handleStartButtonClick() {
        ImageIcon easyIcon = new ImageIcon("src/resources/images/easy.png");
        ImageIcon mediumIcon = new ImageIcon("src/resources/images/medium.png");
        ImageIcon extremeIcon = new ImageIcon("src/resources/images/extreme.png");
        ImageIcon timerIcon500 = new ImageIcon("src/resources/images/500.png");
        ImageIcon timerIcon1500 = new ImageIcon("src/resources/images/1500.png");
        ImageIcon timerIcon6000 = new ImageIcon("src/resources/images/6000.png");

        StartClass start = new StartClass(arcadeFont, easyIcon, mediumIcon, extremeIcon, timerIcon500, timerIcon1500, timerIcon6000);
        removeAll();
        add(start);
        revalidate();
        repaint();
    }

    private void handleSettingsButtonClick() {
        SettingsScreen settings = new SettingsScreen(arcadeFont);
        removeAll();
        add(settings);
        revalidate();
        repaint();
    }

    private void handleQuitButtonClick() {
        System.exit(0);
    }

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

            g2.setColor(Color.GRAY);
            g2.drawString(getText(), shadowOffsetX, getHeight() - shadowOffsetY);

            g2.setColor(getForeground());
            g2.drawString(getText(), 0, getHeight() - shadowOffsetY);

            g2.dispose();
        }
    }
}
