package entity;
import main.GamePanel;
import main.KeyManager;

import java.awt.*;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyManager keyManager;

    public Player(GamePanel gamePanel, KeyManager keyManager) {
        this.gamePanel = gamePanel;
        this.keyManager = keyManager;
        setDefault();
    }

    public void setDefault() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if(keyManager.upPressed == true) {
            y -= speed;
        }
        else if(keyManager.downPressed == true) {
            y += speed;
        }
        else if(keyManager.leftPressed == true){
            x -= speed;
        }
        else if(keyManager.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D graphics2) {
        graphics2.setColor(Color.white);
        graphics2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
