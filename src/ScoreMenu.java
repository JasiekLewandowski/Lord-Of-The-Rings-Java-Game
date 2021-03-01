import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ScoreMenu extends JPanel implements ActionListener {
    JFrame frame;
    JButton replayButton;
    JButton exitToMenuButton;
    JButton exitButton;
    JButton winnerButton;
    String winnerFilePath;

    public ScoreMenu(int winner) {
        winnerFilePath = "Icons/" + winner + ".png";

        frame = new JFrame("PAUSE");
        frame.setSize(600, 450);
        RendererPauseMenu renderer = new RendererPauseMenu();
        frame.add(renderer);
        renderer.setLayout(new GridLayout(4, 0));

        winnerButton = new JButton(new ImageIcon(winnerFilePath));
        winnerButton.setSize(350, 82);
        winnerButton.setOpaque(false);
        winnerButton.setContentAreaFilled(false);
        winnerButton.setBorderPainted(false);
        renderer.add(winnerButton);

        replayButton = new JButton(new ImageIcon("Icons/replay.png"));
        replayButton.setSize(200, 63);
        replayButton.setOpaque(false);
        replayButton.setContentAreaFilled(false);
        replayButton.setBorderPainted(false);
        renderer.add(replayButton);
        replayButton.addActionListener(this);
        replayButton.setActionCommand("REPLAY");

        exitToMenuButton = new JButton(new ImageIcon("Icons/exittomenu.png"));
        exitToMenuButton.setSize(200, 63);
        exitToMenuButton.setOpaque(false);
        exitToMenuButton.setContentAreaFilled(false);
        exitToMenuButton.setBorderPainted(false);
        renderer.add(exitToMenuButton);
        exitToMenuButton.addActionListener(this);
        exitToMenuButton.setActionCommand("EXITTOMENU");

        exitButton = new JButton(new ImageIcon("Icons/exit.png"));
        exitButton.setSize(200, 63);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        renderer.add(exitButton);
        exitButton.addActionListener(this);
        exitButton.setActionCommand("EXIT");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("REPLAY")) {
            Pong.pong.gameStatus = 0;
            frame.dispose();
            Pong.pong.music.stopMusic();
            Pong.pong.start();
        }
        if (e.getActionCommand().equals("EXITTOMENU")) {
            Pong.pong.gameStatus = 0;
            frame.dispose();
            Pong.pong.frame.dispose();
            Pong.pong.music.stopMusic();
            Pong.pong.menu = new MainMenu();
        } else if (e.getActionCommand().equals("EXIT")) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
