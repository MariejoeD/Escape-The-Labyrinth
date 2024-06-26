package com.graduate.game;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.graduate.ui.ChangeCharacterScreen;
import com.graduate.ui.ChangeThemeScreen;
import com.graduate.ui.GameScreen;
import com.graduate.ui.WinScreen;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeWithSprite extends JPanel {
    public static JPanel mazePanel = new JPanel();
    static int maxSize;
    private static String themePath = ChangeThemeScreen.getThemePath();
    static ImageIcon wall = new ImageIcon(themePath);
    static Image wallImage = wall.getImage();

    private static int[] initFinishLine = {5, 10};

    private static int[] finish;
    private static int[][] initialMaze = {
            { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
    };

    private static int[][] maze;

    private static int[] initialPlayerPos = { 5, 0 };
    private static int[] playerPos;

    static BufferedImage spriteSheet;

    static final int SPRITE_WIDTH = 16;
    static final int SPRITE_HEIGHT = 32;

    static int spriteRow = 3;

    public static void mazePanel(int x) {
        mazePanel.setBounds(0, 0, x, x);
        mazePanel.setLayout(null);
        mazePanel.setVisible(true);
        mazePanel.setFocusable(true);
        mazePanel.requestFocusInWindow();
        maxSize = x;
    }

    public static void updateTheme(String newThemePath) {
        themePath = newThemePath;
        drawMaze(); // Redraw the maze with the new theme
    }

    public static void setMaze(int[][] maze) {
        MazeWithSprite.initialMaze = maze;
    }

    public static void setPlayerPos(int[] playerPos) {
        MazeWithSprite.initialPlayerPos = playerPos;
    }

    public static void setFinsih(int[] finish){MazeWithSprite.initFinishLine = finish;}

    public static void resetGame() {
        // Reset maze to initial state
        maze = new int[initialMaze.length][];
        for (int i = 0; i < initialMaze.length; i++) {
            maze[i] = initialMaze[i].clone();
        }
        spriteRow = 3;
        // Reset player position
        playerPos = initialPlayerPos.clone();
        finish = initFinishLine.clone();

        // Redraw the maze
        drawMaze();
    }

    public static void map(int x, int y, int size, boolean isPlayer) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isPlayer) {
                    int spriteX = spriteRow * SPRITE_WIDTH;
                    g.drawImage(spriteSheet.getSubimage(spriteX, 0, SPRITE_WIDTH, SPRITE_HEIGHT), 0, 0, getWidth(),
                            getHeight(), null);
                } else if (maze[y][x] == 1) {
                    g.drawImage(wallImage, 0, 0, (maxSize / size), (maxSize / size), this);
                } else {
                    // Draw ground
                    g.setColor(new Color(0x5CE1E6)); // This sets the color of the ground
                    g.fillRect(0, 0, (maxSize / size), (maxSize / size));
                }
            }
        };

        if (maze[y][x] != 1) {
            panel.setBackground(new Color(0x5CE1E6));
        }

        panel.setBounds(x * (maxSize / size), y * (maxSize / size), (maxSize / size), (maxSize / size));
        mazePanel.add(panel);
    }

    public static void drawMaze() {
        // Update wallImage based on the current themePath
        wall = new ImageIcon(themePath);
        wallImage = wall.getImage();

        mazePanel.removeAll();
        int x = maze[0].length, y = maze.length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                map(j, i, x, (i == playerPos[1] && j == playerPos[0]));
            }
        }
        mazePanel.setPreferredSize(new Dimension(maxSize, maxSize));
        mazePanel.revalidate();
        mazePanel.repaint();
    }

    public static void movePlayer(int dx, int dy) {
        int newX = playerPos[0] + dx;
        int newY = playerPos[1] + dy;

        if (newX >= 0 && newX < maze[0].length && newY >= 0 && newY < maze.length && maze[newY][newX] != 1) {
            playerPos[0] = newX;
            playerPos[1] = newY;
            updateSprite(dx, dy);
            drawMaze();
        }
        finish();
    }


    public static void finish() {
        if (playerPos[0] == finish[0] && playerPos[1] == finish[1]) {
            drawMaze();
            WinScreen win = new WinScreen();
            GameScreen.frame.getContentPane().removeAll();
            GameScreen.frame.getContentPane().add(win);
            GameScreen.frame.revalidate();
            GameScreen.frame.repaint();
        }
    }

    public static void updateSprite(int dx, int dy) {
        if (dx < 0) {
            spriteRow = 2;
        } else if (dx > 0) {
            spriteRow = 0;
        } else if (dy < 0) {
            spriteRow = 1;
        } else if (dy > 0) {
            spriteRow = 3;
        }
    }

    public static void mazeInit(int x) {
        try {
            spriteSheet = ImageIO.read(new File(ChangeCharacterScreen.getCharacterPath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Initialize maze with specified size
        mazePanel(x);
        resetGame(); // Initialize maze and player positions
        drawMaze(); // Draw the maze
        attachKeyListener(); // Attach key listener for player movement
    }

    private static void attachKeyListener() {
        for (KeyListener keyAdapter : mazePanel.getKeyListeners()) {
            mazePanel.removeKeyListener(keyAdapter);
        }

        mazePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> movePlayer(0, -1);
                    case KeyEvent.VK_S -> movePlayer(0, 1);
                    case KeyEvent.VK_A -> movePlayer(-1, 0);
                    case KeyEvent.VK_D -> movePlayer(1, 0);
                }
            }
        });

        mazePanel.setFocusable(true);
        mazePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        mazeInit(800);
    }
}
