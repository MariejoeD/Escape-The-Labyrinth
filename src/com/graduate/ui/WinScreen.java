package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WinScreen extends JPanel {

    private Font arcadeFont;

    public WinScreen() {
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        loadCustomFont();
        addTitlePanel();
        addPromptAndButtonsPanel();
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
        WinScreen.ShadowText titleWin = new WinScreen.ShadowText("YOU WIN!", SwingConstants.CENTER, 5, 5);
        titleWin.setForeground(Color.BLACK);
        titleWin.setFont(arcadeFont.deriveFont(108f));

        JLabel leftImage = new JLabel(new ImageIcon("src/resources/images/crying-pepe-left-side.png"));
        JLabel rightImage = new JLabel(new ImageIcon("src/resources/images/crying-pepe-right-side.png"));
        leftImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(leftImage);
        titlePanel.add(Box.createHorizontalStrut(10)); // Adjust the spacing as needed
        titlePanel.add(titleWin);
        titlePanel.add(Box.createHorizontalStrut(10)); // Adjust the spacing as needed
        titlePanel.add(rightImage);
        titlePanel.add(Box.createHorizontalGlue());

        JPanel titleContainer = new JPanel();
        titleContainer.setOpaque(false);
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        titleContainer.add(Box.createVerticalStrut(90)); // Space above title
        titleContainer.add(titlePanel);
        titleContainer.add(Box.createVerticalStrut(50)); // Space below title

        add(titleContainer, BorderLayout.NORTH);
    }

    private void addPromptAndButtonsPanel() {
        ShadowText promptTryAgain = new ShadowText("PLAY AGAIN?", SwingConstants.CENTER, 5, 5);
        promptTryAgain.setForeground(Color.BLACK);
        promptTryAgain.setFont(arcadeFont.deriveFont(60f));
        promptTryAgain.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton yesButton = createStyledButton("YES", arcadeFont, e -> handleYesButtonClick());
        JButton noButton = createStyledButton("NO", arcadeFont, e -> handleNoButtonClick());

        JPanel promptAndButtonsPanel = new JPanel();
        promptAndButtonsPanel.setOpaque(false);
        promptAndButtonsPanel.setLayout(new BoxLayout(promptAndButtonsPanel, BoxLayout.Y_AXIS));
        promptAndButtonsPanel.add(promptTryAgain);
        promptAndButtonsPanel.add(Box.createVerticalStrut(20)); // Space between prompt and buttons

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Adjust the spacing as needed
        buttonPanel.add(noButton);
        buttonPanel.add(Box.createHorizontalGlue());

        promptAndButtonsPanel.add(buttonPanel);
        promptAndButtonsPanel.add(Box.createVerticalStrut(20)); // Space below buttons

        JPanel promptAndButtonsContainer = new JPanel();
        promptAndButtonsContainer.setOpaque(false);
        promptAndButtonsContainer.setLayout(new BoxLayout(promptAndButtonsContainer, BoxLayout.Y_AXIS));
        promptAndButtonsContainer.add(Box.createVerticalGlue());
        promptAndButtonsContainer.add(promptAndButtonsPanel);
        promptAndButtonsContainer.add(Box.createVerticalGlue());

        add(promptAndButtonsContainer, BorderLayout.CENTER);
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

    private void handleYesButtonClick() {
        // Implement your logic for the "YES" button click
        System.out.println("YES button clicked");
    }

    private void handleNoButtonClick() {
        // Implement your logic for the "NO" button click
        System.out.println("NO button clicked");
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