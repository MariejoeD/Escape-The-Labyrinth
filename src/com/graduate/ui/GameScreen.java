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
    }
    private void togglePause() {
        pauseGame();
    }

    private void pauseGame() {
        isPaused = true;
        PauseScreen pause = new PauseScreen();
        frame.getContentPane().add(pause);
        frame.revalidate();
        frame.repaint();
    }

    

    public void updateTimer(String time) {
        timerLabel.setText(time);
    }

    public static void gameStart() {
        frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        JPanel mainPanel = new JPanel();
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
                mainPanel.setBounds(x, 0, panelSize, panelSize);
                mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze();

                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        frame.add(mainPanel);
        frame.add(new GameScreen());
        mainPanel.add(MazeWithSprite.mazePanel);
        frame.setVisible(true);
    }

    public static void gameStartnoSetMaze() {
        frame = new JFrame("Game Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        JPanel mainPanel = new JPanel();
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension contentPaneSize = frame.getContentPane().getSize();
                int width = contentPaneSize.width;
                int height = contentPaneSize.height;
                int panelSize = Math.min(width, height);
                int x = (width - panelSize) / 2;

                MazeWithSprite.mazeInit(panelSize);
                mainPanel.setBounds(x, 0, panelSize, panelSize);
                mainPanel.setPreferredSize(new Dimension(panelSize, panelSize));
                mainPanel.setLayout(null);
                MazeWithSprite.mazeInit(panelSize);
                MazeWithSprite.drawMaze();

                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        frame.add(mainPanel);
        frame.add(new GameScreen());
        mainPanel.add(MazeWithSprite.mazePanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        gameStartnoSetMaze();
    }
}
