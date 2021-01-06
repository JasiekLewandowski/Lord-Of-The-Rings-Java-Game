import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondMenu implements ActionListener {
    JFrame frame;
    RendererMenu renderer;
    JButton singleplayerButton;
    JButton multiplayerButton;
    JButton settingsButton;

    public SecondMenu() {
        frame = new JFrame("CHOOSE SETTINGS");
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        renderer = new RendererMenu();
        frame.add(renderer);

        singleplayerButton = new JButton(new ImageIcon("Icons/singleplayer.png"));
        singleplayerButton.setActionCommand("SINGLEPLAYER");
        singleplayerButton.setOpaque(false);
        singleplayerButton.setContentAreaFilled(false);
        singleplayerButton.setBorderPainted(false);
        singleplayerButton.addActionListener(this);

        multiplayerButton = new JButton(new ImageIcon("Icons/multiplayer.png"));
        multiplayerButton.setActionCommand("MULTIPLAYER");
        multiplayerButton.setOpaque(false);
        multiplayerButton.setContentAreaFilled(false);
        multiplayerButton.setBorderPainted(false);
        multiplayerButton.addActionListener(this);

        settingsButton = new JButton(new ImageIcon("Icons/settings.png"));
        settingsButton.setActionCommand("SETTINGS");
        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.addActionListener(this);

        renderer.setLayout(new GridLayout(3,1));
        renderer.add(singleplayerButton);
        renderer.add(multiplayerButton);
        renderer.add(settingsButton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SETTINGS")) {
            new SettingsMenu();
        }
        if (e.getActionCommand().equals("SINGLEPLAYER") || e.getActionCommand().equals("MULTIPLAYER")) {
            frame.dispose();
            MainMenu.menuMusic.stopMusic();
            if (e.getActionCommand().equals("SINGLEPLAYER")) {
                Pong.bot = true;
            } else {
                Pong.bot = false;
            }
            Pong.pong.startPong();
        }
    }
}
