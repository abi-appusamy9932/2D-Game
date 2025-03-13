package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48x48

    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // 4:3 aspect ratio
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    final int secondsToNano = 1000000000;
    final int secondsToMilli = 1000000;

    int FPS = 60;

    KeyManager keyManager = new KeyManager();
    Thread gameThread;

    int playerX = 100; //default position
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyManager);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawIntreval = secondsToNano/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawIntreval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta = 0;
                drawCount++;
            }

            if (timer >= secondsToNano) {
                System.out.println("FPS:" + drawCount);
                timer = 0;
            }
        }
    }

    public void update() {
        if(keyManager.upPressed == true) {
            playerY -= playerSpeed;
        }
        else if(keyManager.downPressed == true) {
            playerY += playerSpeed;
        }
        else if(keyManager.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if(keyManager.rightPressed == true) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D)graphics;

        graphics2.setColor(Color.white);
        graphics2.fillRect(playerX, playerY, tileSize, tileSize);
        graphics2.dispose();
    }

}