package com.graduate.ui;

import javax.swing.*;
import com.graduate.game.MazeWithSprite;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameScreen extends JPanel {

    private ImageIcon pauseIcon;
    private ImageIcon hourglassIcon;
    private JLabel timerLabel;
    public static JFrame frame;
    private static boolean isPaused = false;
    private JPanel mainPanel;
    private JPanel cards;
    private CardLayout cardLayout;
    private PauseScreen pauseScreen;

    public GameScreen() {
        pauseIcon = new ImageIcon(new ImageIcon("src/resources/images/pause.png").getImage().getScaledInstance(100, 100,
                Image.SCALE_SMOOTH));
        hourglassIcon = new ImageIcon(new ImageIcon("src/resources/images/hourglass.png").getImage()
                .getScaledInstance(55, 55, Image.SCALE_SMOOTH));

        setBackground(new Color(0x5CE1E6));
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        JButton pauseButton = new JButton(pauseIcon);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setOpaque(false);
        pauseButton.addActionListener(e -> togglePause());

        topPanel.add(pauseButton, BorderLayout.WEST);

        JPanel timerPanel = new JPanel();
        timerPanel.setOpaque(false);
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));

        JLabel hourglassLabel = new JLabel(hourglassIcon);
        timerLabel = new JLabel("2:00");
        timerLabel.setFont(new Font("Consolas", Font.PLAIN, 36));
        timerLabel.setForeground(Color.BLACK);

        timerPanel.add(hourglassLabel);
        timerPanel.add(Box.createHorizontalStrut(5));
        timerPanel.add(timerLabel);

        topPanel.add(timerPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        pauseScreen = new PauseScreen(this);

        // Initialize the CardLayout and the cards panel
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0x5CE1E6));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);

        cards.add(mainPanel, "Game");
        cards.add(pauseScreen, "Pause");

        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);
    }

    private void togglePause() {
        if (isPaused) {
            resumeGame();
        } else {
            pauseGame();
        }
    }

    private void pauseGame() {
        isPaused = true;
        cardLayout.show(cards, "Pause");
    }

    public void resumeGame() {
        isPaused = false;
        cardLayout.show(cards, "Game");
        MazeWithSprite.mazePanel.requestFocusInWindow(); // Ensure the maze panel has focus
    }

    public void updateTimer(String time) {
        timerLabel.setText(time);
    }

    public static void gameStart() {
        frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        GameScreen gameScreen = new GameScreen();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension contentPaneSize = frame.getContentPane().getSize();
                int width = contentPaneSize.width;
                int height = contentPaneSize.height;
                int panelSize = Math.min(width, height);
                int x = (width - panelSize) / 2;

                MazeWithSprite.setMaze(StartClass.getMaze());
                MazeWithSprite.setPlayerPos(StartClass.getPlayerPos());
                MazeWithSprite.mazeInit(panelSize);
                gameScreen.mainPanel.setBounds(x, 0, panelSize, panelSize);
                gameScreen.mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                gameScreen.mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze();

                gameScreen.mainPanel.revalidate();
                gameScreen.mainPanel.repaint();
            }
        });
        frame.add(gameScreen);
        gameScreen.mainPanel.add(MazeWithSprite.mazePanel);
        frame.setVisible(true);
    }

    public static void gameStartnoSetMaze() {
        frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        GameScreen gameScreen = new GameScreen();

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension contentPaneSize = frame.getContentPane().getSize();
                int width = contentPaneSize.width;
                int height = contentPaneSize.height;
                int panelSize = Math.min(width, height);
                int x = (width - panelSize) / 2;

                MazeWithSprite.mazeInit(panelSize);
                gameScreen.mainPanel.setBounds(x, 0, panelSize, panelSize);
                gameScreen.mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                gameScreen.mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze();

                gameScreen.mainPanel.revalidate();
                gameScreen.mainPanel.repaint();
            }
        });
        frame.add(gameScreen);
        gameScreen.mainPanel.add(MazeWithSprite.mazePanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        gameStartnoSetMaze();
    }
}