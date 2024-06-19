package com.graduate.ui;

import com.graduate.util.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Entry point for the splash screen application
public class SplashScreen extends JFrame {

    private final JPanel mainPanel;

    public SplashScreen() {
        initializeFrame();
        mainPanel = new JPanel(new BorderLayout());
        addComponentsToMainPanel();
        add(mainPanel);
        setVisible(true);
        startSplashScreenTimer();
        SoundPlayer.loopSound("src/resources/sounds/menu-sound.wav");
    }

    private void initializeFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Escape The Labyrinth");
        setResizable(false);
        setLocationRelativeTo(null);
        loadCustomFont();
    }

    private void loadCustomFont() {
        try {
            Font arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/arcade_ya/ARCADE_N.TTF")).deriveFont((float) 96.0);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(arcadeFont);
            UIManager.put("Label.font", arcadeFont);
            UIManager.put("Button.font", arcadeFont.deriveFont((float) 24.0));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void addComponentsToMainPanel() {
        mainPanel.setBackground(new Color(0x5CE1E6));
        mainPanel.add(createTitlePanel(), BorderLayout.CENTER);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(Box.createVerticalStrut(300));
        titlePanel.add(createShadowTextLabel());
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(createImageLabel());

        return titlePanel;
    }

    private ShadowTextLabel createShadowTextLabel() {
        ShadowTextLabel shadowTextLabel = new ShadowTextLabel("ESCAPE THE", SwingConstants.CENTER, 5, 5);
        shadowTextLabel.setForeground(Color.BLACK);
        shadowTextLabel.setFont(shadowTextLabel.getFont().deriveFont((float) 90.0));
        shadowTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return shadowTextLabel;
    }

    private JLabel createImageLabel() {
        JLabel imageLabel = new JLabel(new ImageIcon("src/resources/images/LABYRINTH.png"), SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return imageLabel;
    }

    private void startSplashScreenTimer() {
        Timer timer = new Timer(5000, e -> {
            mainPanel.removeAll();
            addGameMenuToMainPanel();
            revalidate();
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void addGameMenuToMainPanel() {
        GameMenu gameMenu = new GameMenu();
        mainPanel.add(gameMenu, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }

    // Custom JLabel class for text with shadow effect
    static class ShadowTextLabel extends JLabel {
        private final int shadowOffsetX;
        private final int shadowOffsetY;

        public ShadowTextLabel(String text, int horizontalAlignment, int shadowOffsetX, int shadowOffsetY) {
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