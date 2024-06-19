package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ChangeThemeScreen extends JPanel {

    private Font arcadeFont;

    public ChangeThemeScreen() {
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        loadCustomFont();
        addTitlePanel();
        addThemeOptionsPanel();
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
        ShadowText titleTheme = new ShadowText("CHOOSE THEME", SwingConstants.CENTER, 5, 5);
        titleTheme.setForeground(Color.BLACK);
        titleTheme.setFont(arcadeFont.deriveFont(108f));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(50)); // Space above title
        titleTheme.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleTheme);
        titlePanel.add(Box.createVerticalStrut(50)); // Space below title

        add(titlePanel, BorderLayout.NORTH);
    }

    private void addThemeOptionsPanel() {
        // Create theme option buttons
        JButton magicButton = createThemeButton("MAGIC", "src/resources/walls/roygbiv_wall.png");
        JButton woodButton = createThemeButton("WOOD", "src/resources/walls/wood_wall.png");
        JButton dungeonButton = createThemeButton("DUNGEON", "src/resources/walls/dungeon_wall.png");

        // Create a panel for the theme options
        JPanel themeOptionsPanel = new JPanel();
        themeOptionsPanel.setOpaque(false);
        themeOptionsPanel.setLayout(new GridLayout(1, 3, 20, 0)); // 1 row, 3 columns, horizontal gap

        // Add buttons to the theme options panel
        themeOptionsPanel.add(magicButton);
        themeOptionsPanel.add(woodButton);
        themeOptionsPanel.add(dungeonButton);

        JPanel centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.add(themeOptionsPanel);

        add(centeredPanel, BorderLayout.CENTER);
    }

    private JButton createThemeButton(String text, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JButton button = new JButton(text, icon) {
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
        button.setFont(arcadeFont.deriveFont(30f));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.BLACK);
        return button;
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