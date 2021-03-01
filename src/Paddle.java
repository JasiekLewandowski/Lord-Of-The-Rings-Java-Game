import javax.swing.*;
import java.awt.*;

public class Paddle {
    public int paddleNumber;
    public int x, y, width = 50, height = 200;
    public int score;
    Image image;

    public Paddle(Pong pong, int paddleNumber) {
        this.paddleNumber = paddleNumber;
        if (paddleNumber == 1) {
            this.x = 0;
        }
        if (paddleNumber == 2) {
            this.x = pong.width - width;
        }
        this.y = pong.height / 2 - this.height / 2;
    }

    public void render(Graphics2D g) {
        if (paddleNumber == 1) {
            image = new ImageIcon("Icons/frodo.png").getImage();
            g.drawImage(image, x, y, null);
        } else {
            image = new ImageIcon("Icons/sauron.png").getImage();
            g.drawImage(image, x, y, null);
        }
    }

    public void move(boolean up) {
        if (up) {
            if (y < 1) {
                y = 0;
            } else {
                y -= Pong.playerSpeed;
            }
        } else {
            if (y + height < Pong.pong.height) {
                y += Pong.playerSpeed;
            }
        }
    }

    public void autoMove() {
        if (y < 0) {
            y = 0;
        }
        if (y + height > Pong.pong.height) {
            y = Pong.pong.height - height;
        }
        if (Pong.pong.ball.getY() > y + height / 2) {
            y += Pong.botSpeed;
        } else {
            y -= Pong.botSpeed;
        }
    }
}
