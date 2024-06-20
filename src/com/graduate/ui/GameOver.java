package com.graduate.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameOver extends JPanel {

    public GameOver(Font arcadeFont, String imagePath) {
        // Create title label for game over
        JLabel gameOverTitle = new JLabel("GAME OVER");
        gameOverTitle.setFont(arcadeFont.deriveFont(120f)); // Set font size for game over title
        gameOverTitle.setForeground(Color.BLACK); // Set text color
        gameOverTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Create subtitle label for play again
        JLabel playAgainLabel = new JLabel("PLAY AGAIN?");
        playAgainLabel.setFont(arcadeFont.deriveFont(80f)); // Set font size for play again text
        playAgainLabel.setForeground(Color.BLACK); // Set text color
        playAgainLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Create buttons for game over options
        JButton yesButton = createStyledButton("YES", arcadeFont);
        JButton noButton = createStyledButton("NO", arcadeFont);

        // Create panel for game over buttons with horizontal alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false); // Make button panel transparent
        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createHorizontalStrut(20)); // Add space between buttons
        buttonPanel.add(noButton);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button panel

        // Load the image
        JLabel imageLabel = new JLabel();
        try {
            Image image = ImageIO.read(new File("src/resources/images/gameOver.png"));
            image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust size of the image
            imageLabel.setIcon(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Image not found");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        }

        // Create a panel for the text components
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false); // Make text panel transparent
        textPanel.add(gameOverTitle);
        textPanel.add(Box.createVerticalStrut(50)); // Space between game over text and play again text
        textPanel.add(playAgainLabel);
        textPanel.add(Box.createVerticalStrut(50)); // Space between play again text and buttons
        textPanel.add(buttonPanel);

        // Create a container panel for the image and text panels
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setOpaque(false); // Make container panel transparent
        containerPanel.add(Box.createVerticalGlue()); // Space above image
        containerPanel.add(imageLabel);
        containerPanel.add(Box.createVerticalStrut(50)); // Space between image and text panel
        containerPanel.add(textPanel);
        containerPanel.add(Box.createVerticalGlue()); // Space below text panel
        containerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the container panel

        // Create main layout for the GameOver panel
        setLayout(new BorderLayout());
        setBackground(new Color(0x5CE1E6)); // Set background color
        add(containerPanel, BorderLayout.CENTER); // Add container panel to the center
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBackground() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 90, 90); // Rounded corners with radius 90
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
        button.setFocusPainted(false); // Disable focus painting
        button.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        button.setForeground(Color.BLACK);

        Dimension buttonSize = new Dimension(200, 100); // Set preferred button size
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Load custom font (use a default font if arcadeFont is unavailable)
            Font arcadeFont;
            try {
                arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/arcade_ya/ARCADE_N.TTF")).deriveFont(50f);
            } catch (Exception e) {
                arcadeFont = new Font("Arial", Font.PLAIN, 50);
            }

            // Create the main frame
            JFrame frame = new JFrame("Game Over");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame to cover the whole screen
            frame.setUndecorated(true); // Remove window decorations

            // Add the GameOver panel to the frame
            frame.add(new GameOver(arcadeFont, "/mnt/data/image.png"));

            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
