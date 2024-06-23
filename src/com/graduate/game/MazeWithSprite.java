package com.graduate.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeWithSprite extends JPanel{
    public static JPanel mazePanel = new JPanel();
    static int maxSize;

    public static void setMaze(int[][] maze) {
        MazeWithSprite.maze = maze;
    }

    private static int[][] maze = {
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
    };

    public static void setPlayerPos(int[] playerPos) {
        MazeWithSprite.playerPos = playerPos;
    }

    //    static int playerPos[0] = 5, playerPos[1] = 0; // Starting at the first 0 at the top of the maze
    private static int[] playerPos = {5,0};

    // Load sprite sheet image
    static BufferedImage spriteSheet;

    // Sprite dimensions
    static final int SPRITE_WIDTH = 16;
    static final int SPRITE_HEIGHT = 32;

    // Current sprite position in the sprite sheet
    static int spriteRow = 3; // Initialize to the down sprite row

    public static void mazePanel(int x) {
        // mazePanel.setSize(x, x);
        mazePanel.setBounds(0,0,x,x);
        mazePanel.setLayout(null);  // Ensure that we are using absolute positioning for components
        mazePanel.setVisible(true);
        maxSize = x;
    }

    public static void map(int x, int y, int size, boolean isPlayer) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isPlayer) {
                    int spriteX = spriteRow * SPRITE_WIDTH;
                    g.drawImage(spriteSheet.getSubimage(spriteX, 0, SPRITE_WIDTH, SPRITE_HEIGHT), 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        
        panel.setBackground(maze[y][x] == 1 ? Color.black : Color.white);
        panel.setBounds(x * (maxSize / size), y * (maxSize / size), (maxSize / size), (maxSize / size));
        mazePanel.add(panel);
    }

    public static void drawMaze() {
        int x = maze[0].length, y = maze.length;
        mazePanel.removeAll();
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
            updateSprite(dx, dy); // Update sprite position in the sprite sheet based on movement
            drawMaze();
            if (newX == maze[0].length - 6 && newY == maze.length - 1) {
                JOptionPane.showMessageDialog(mazePanel, "Congratulations! You've reached the finish line!");
                System.exit(0);
            }
        }
    }

    public static void updateSprite(int dx, int dy) {
        if (dx < 0) { // Moving left
            spriteRow = 2; // Adjust this based on your sprite sheet layout
        } else if (dx > 0) { // Moving right
            spriteRow = 0; // Adjust this based on your sprite sheet layout
        } else if (dy < 0) { // Moving up
            spriteRow = 1; // Adjust this based on your sprite sheet layout
        } else if (dy > 0) { // Moving down
            spriteRow = 3; // Adjust this based on your sprite sheet layout
        }
    }

    public static void mazeInit(int x)
    {
        try {
            spriteSheet = ImageIO.read(new File("src\\resources\\sprites\\adam.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        mazePanel(x);
    
        drawMaze();

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

        // Adjust the mazePanel size slightly
        // mazePanel.setSize(maxSize, maxSize);
    }
    public static void main(String[] args) {
        mazeInit(800);
    }
}
