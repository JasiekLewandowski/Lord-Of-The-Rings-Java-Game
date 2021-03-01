import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PauseMenu implements ActionListener {
    static Image img = Toolkit.getDefaultToolkit().getImage("Icons/pausemenu.png");
    JFrame frame;
    JButton exitToMenuButton;
    JButton continueButton;
    JButton replayButton;
    JButton exitButton;

    public PauseMenu() {
        frame = new JFrame("PAUSE");
        frame.setSize(600, 450);
        RendererPauseMenu renderer = new RendererPauseMenu();
        frame.add(renderer);
        renderer.setLayout(new GridLayout(4, 0));

        continueButton = new JButton(new ImageIcon("Icons/continue.png"));
        continueButton.setSize(273, 86);
        continueButton.setOpaque(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        renderer.add(continueButton);
        continueButton.addActionListener(this);
        continueButton.setActionCommand("CONTINUE");

        replayButton = new JButton(new ImageIcon("Icons/replay.png"));
        replayButton.setSize(200, 63);
        replayButton.setOpaque(false);
        replayButton.setContentAreaFilled(false);
        replayButton.setBorderPainted(false);
        renderer.add(replayButton);
        replayButton.addActionListener(this);
        replayButton.setActionCommand("REPLAY");

        exitToMenuButton = new JButton(new ImageIcon("Icons/exittomenu.png"));
        exitToMenuButton.setSize(273, 86);
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
        if (e.getActionCommand().equals("CONTINUE")) {
            frame.dispose();
            Pong.pong.gameStatus = 2;
        }
        if (e.getActionCommand().equals("EXIT")) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getActionCommand().equals("EXITTOMENU")) {
            frame.dispose();
            Pong.pong.frame.dispose();
            Pong.pong.music.stopMusic();
            Pong.pong.menu = new MainMenu();
        }
        if (e.getActionCommand().equals("REPLAY")) {
            Pong.pong.gameStatus = 0;
            frame.dispose();
            Pong.pong.music.stopMusic();
            Pong.pong.start();
        }
    }
}
