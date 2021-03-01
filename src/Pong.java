import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Pong implements ActionListener, KeyListener {

    public int gameStatus = 0;
    public static int scoreLimit = 3;
    public static int ballSpeed = 10;
    public static int playerSpeed = 14;
    public static int botSpeed = 14;

    Image background;

    public static MainMenu menu;
    public static Pong pong;
    public static boolean bot;

    public int width = 1000;
    public int height = 700;
    public JFrame frame;
    public Renderer renderer;
    public Music music;
    boolean w, s, up, down;
    public Ball ball;

    public Paddle player1;
    public Paddle player2;


    public static Random random;

    public static void main(String[] args) {
        menu = new MainMenu();
    }

    public static void startPong() {
        pong = new Pong();
    }

    public Pong() {
        Timer timer = new Timer(20, this);

        frame = new JFrame("Pong");
        frame.setSize(width + 15, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);

        renderer = new Renderer();
        frame.add(renderer);

        start();

        timer.start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        int setupIndex = setBackground();
        music = new Music(setupIndex);
        gameStatus = 2;
        player1 = new Paddle(this, 1);
        player2 = new Paddle(this, 2);
        ball = new Ball(this);
    }

    public void update() {
        if (player1.score >= scoreLimit) {
            printScore(0);
        }

        if (player2.score >= scoreLimit) {
            if (!bot)
                printScore(1);
            if (bot)
                printScore(2);
        }
        if (w) {
            player1.move(true);
        }
        if (s) {
            player1.move(false);
        }
        if (!bot) {
            if (up) {
                player2.move(true);
            }
            if (down) {
                player2.move(false);
            }
        }
        if (bot) {
            player2.autoMove();
        }
        ball.update(player1, player2);
    }

    public void render(Graphics2D g) {
        if (gameStatus == 2 || gameStatus == 1) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(Pong.pong.background, 0, 0, 1000, 700, null);
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(7));
            g.drawLine(width / 2, 0, width / 2, height);
            String score1 = String.valueOf(player1.score);
            String score2 = String.valueOf(player2.score);
            g.setFont(new Font("Montserrat", 1, 50));
            g.drawString(score1, width / 2 - 50, 50);
            g.drawString(score2, width / 2 + 23, 50);
            player1.render(g);
            player2.render(g);
            ball.render(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = true;
        }
        if (id == KeyEvent.VK_S) {
            s = true;
        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        } else if (id == KeyEvent.VK_ESCAPE) {
            if (gameStatus == 2) {
                pauseMenu();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

    public int setBackground() {
        random = new Random();
        int randomNumber = random.nextInt(5);
        String imagePath = "Backgrounds/" + randomNumber + ".jpg";
        this.background = Toolkit.getDefaultToolkit().getImage(imagePath);
        return randomNumber;
    }

    public void pauseMenu() {
        this.gameStatus = 1;
        new PauseMenu();
    }

    public void printScore(int winner) {
        this.gameStatus = 1;
        new ScoreMenu(winner);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus == 2) {
            update();
        }
        renderer.repaint();
    }
}

