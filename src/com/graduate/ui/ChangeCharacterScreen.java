package com.graduate.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class ChangeCharacterScreen extends JPanel {

    private Font arcadeFont;

    public ChangeCharacterScreen() {
        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());
        loadCustomFont();
        addTitlePanel();
        addCharacterOptionsPanel();
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
        // Create a panel for the back button
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        // Create and add the back button
        JButton backButton = createBackButton();
        backButton.addActionListener(e -> navigateBack());

        JPanel backButtonContainer = new JPanel(new BorderLayout());
        backButtonContainer.setOpaque(false);
        backButtonContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0)); // Add top and left space
        backButtonContainer.add(backButton, BorderLayout.WEST);

        topPanel.add(backButtonContainer, BorderLayout.WEST);

        // Create the title label
        ShadowText titleCharacter = new ShadowText("CHARACTER", SwingConstants.CENTER, 5, 5);
        titleCharacter.setForeground(Color.BLACK);
        titleCharacter.setFont(arcadeFont.deriveFont(108f));
        titleCharacter.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a title panel and add components
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(topPanel);
        titlePanel.add(Box.createVerticalStrut(30)); // Space between back button and title
        titlePanel.add(titleCharacter);
        titlePanel.add(Box.createVerticalStrut(0)); // Space below title

        add(titlePanel, BorderLayout.NORTH);
    }

    private void addCharacterOptionsPanel() {
        // Create character option buttons
        JPanel adamPanel = createCharacterPanel("ADAM", "src/resources/sprites/adam.png", true);
        JPanel alexPanel = createCharacterPanel("ALEX", "src/resources/sprites/alex.png", true);
        JPanel ameliaPanel = createCharacterPanel("AMELIA", "src/resources/sprites/amelia.png", true);
        JPanel bobPanel = createCharacterPanel("BOB", "src/resources/sprites/bob.png", true);

        // Create a panel for the character options
        JPanel characterOptionsPanel = new JPanel();
        characterOptionsPanel.setOpaque(false);
        characterOptionsPanel.setLayout(new GridLayout(2, 2, 250, 40)); // Increased gaps between components

        // Add character panels to the character options panel
        characterOptionsPanel.add(adamPanel);
        characterOptionsPanel.add(alexPanel);
        characterOptionsPanel.add(ameliaPanel);
        characterOptionsPanel.add(bobPanel);

        // Create a centered panel to maximize the space
        JPanel centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setBackground(new Color(0x5CE1E6)); // Match the background color

        // Add characterOptionsPanel with additional space on the sides
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 100, 0, 100); // Add 100 pixels of space on the left and right sides
        centeredPanel.add(characterOptionsPanel, gbc);

        // Add the centered panel to the center of the ChangeCharacterScreen
        add(centeredPanel, BorderLayout.CENTER);
    }

    private JPanel createCharacterPanel(String text, String imagePath, boolean centerAlignment) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH); // Increase image size
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        JButton button = new JButton(text);
        button.setFont(arcadeFont.deriveFont(45f)); // Increase button font size
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25)); // Increase button border
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(600, 120)); // Increase button size
        button.setUI(new RoundedButtonUI());

        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));
        characterPanel.setOpaque(false);

        if (centerAlignment) {
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        characterPanel.add(imageLabel);
        characterPanel.add(Box.createVerticalStrut(20)); // Space between image and button
        characterPanel.add(button);

        return characterPanel;
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
        frame.getContentPane().add(new SettingsScreen(arcadeFont));
        frame.revalidate();
        frame.repaint();
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

    static class RoundedButtonUI extends BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }

        private void paintBackground(Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fill(new RoundRectangle2D.Float(0, yOffset, size.width, size.height - yOffset, size.height * 0.9f, size.height * 0.9f));
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            c.setOpaque(false);
            c.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        }
    }
}
