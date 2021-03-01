import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ball {
    public int x, y;
    public int motionX, motionY;
    public Random random;
    public Pong pong;
    public int radius = 50;

    public Ball(Pong pong) {
        this.random = new Random();
        this.pong = pong;
        spawn();
    }

    public void spawn() {
        this.x = pong.width / 2 - radius;
        this.y = pong.height / 2 - radius;

        this.motionY = -2 + random.nextInt(4);

        if (motionY == 0) {
            motionY = 1;
        }
        if (random.nextBoolean()) {
            motionX = 1;
        } else {
            motionX = -1;
        }
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        this.x += motionX * Pong.ballSpeed;
        this.y += motionY * Pong.ballSpeed;

        if (this.y + radius - motionY > pong.height || this.y + motionY < 0) {
            if (this.motionY < 0) {
                this.y = 0;
                this.motionY = random.nextInt(4);

                if (motionY == 0) {
                    motionY = 1;
                }
            } else {
                this.motionY = -random.nextInt(4);
                this.y = pong.height - radius;

                if (motionY == 0) {
                    motionY = -1;
                }
            }
        }
        if (checkCollision(paddle1) == 1) {
            this.motionX = 1;
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {
                motionY = 1;
            }
        } else if (checkCollision(paddle2) == 1) {
            this.motionX = -1;
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {
                motionY = 1;
            }
        }
        if (checkCollision(paddle1) == 2) {
            paddle2.score++;
            spawn();
        } else if (checkCollision(paddle2) == 2) {
            paddle1.score++;
            spawn();
        }
    }

    public int checkCollision(Paddle paddle) {
        if (this.x < paddle.x + paddle.width && this.x + radius + 5 > paddle.x && this.y < paddle.y + paddle.height && this.y + radius > paddle.y) {
            playSound(3);
            return 1; //bounce
        } else if ((paddle.x > x && paddle.paddleNumber == 1) || (paddle.x < x - radius && paddle.paddleNumber == 2)) {
            playSound(2);
            return 2; //score
        }
        return 0; //nothing
    }

    public void render(Graphics2D g) {
        Image image = new ImageIcon("Icons/ball.png").getImage();
        g.clip(new Ellipse2D.Double(x, y, radius, radius));
        g.drawImage(image, x, y, null);
    }

    public void playSound(int soundIndex) {
        try {
            String audioPath = "Sounds/" + soundIndex + ".wav";
            File audioFile = new File(audioPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            System.out.println("File not found");
        }
    }

    public int getY() {
        return y;
    }
}
