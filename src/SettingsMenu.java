import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel implements ActionListener {
    JFrame frame;
    RendererPauseMenu renderer;
    JLabel ballSpeed;
    JLabel scoreLimit;
    JLabel playerSpeed;
    JLabel botSpeed;
    JButton okButton;
    JButton defaultSettingsButton;
    JTextField scoreLimitText;
    JTextField ballSpeedText;
    JTextField playerSpeedText;
    JTextField botSpeedText;


    public SettingsMenu() {
        frame = new JFrame("SETTINGS");
        frame.setSize(600, 450);
        renderer = new RendererPauseMenu();
        renderer.setLayout(new GridLayout(5, 1));
        frame.add(renderer);

        scoreLimit = new JLabel(new ImageIcon("Icons/scoretowin.png"));
        renderer.add(scoreLimit);

        scoreLimitText = new JTextField();
        scoreLimitText.setBounds(0, 225, 100, 30);
        renderer.add(scoreLimitText);


        ballSpeed = new JLabel(new ImageIcon("Icons/ballspeed.png"));
        renderer.add(ballSpeed);

        ballSpeedText = new JTextField();
        ballSpeedText.setBounds(120, 225, 100, 30);
        renderer.add(ballSpeedText);


        playerSpeed = new JLabel(new ImageIcon("Icons/playerspeed.png"));
        renderer.add(playerSpeed);

        playerSpeedText = new JTextField();
        playerSpeedText.setBounds(240, 225, 100, 30);
        renderer.add(playerSpeedText);

        botSpeed = new JLabel(new ImageIcon("Icons/botspeed.png"));
        renderer.add(botSpeed);

        botSpeedText = new JTextField();
        botSpeedText.setBounds(360, 225, 100, 30);
        renderer.add(botSpeedText);

        okButton = new JButton(new ImageIcon("Icons/ok.png"));
        okButton.setSize(165, 40);
        renderer.add(okButton);
        okButton.setOpaque(false);
        okButton.setContentAreaFilled(false);
        okButton.setBorderPainted(false);
        okButton.addActionListener(this);
        okButton.setActionCommand("OK");

        defaultSettingsButton = new JButton(new ImageIcon("Icons/defaultsettings.png"));
        defaultSettingsButton.setSize(165, 40);
        renderer.add(defaultSettingsButton);
        defaultSettingsButton.setOpaque(false);
        defaultSettingsButton.setContentAreaFilled(false);
        defaultSettingsButton.setBorderPainted(false);
        defaultSettingsButton.addActionListener(this);
        defaultSettingsButton.setActionCommand("DEFAULTSETTINGSBUTTON");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DEFAULTSETTINGSBUTTON")) {
            Pong.scoreLimit = 5;
            Pong.ballSpeed = 10;
            Pong.playerSpeed = 14;
            Pong.botSpeed = 14;

            frame.dispose();
        }
        if (e.getActionCommand().equals("OK")) {
            if (isNumber(scoreLimitText.getText())) {
                Pong.scoreLimit = Integer.parseInt(scoreLimitText.getText());
            }
            if (isNumber(ballSpeedText.getText())) {
                Pong.ballSpeed = Integer.parseInt(ballSpeedText.getText());
            }
            if (isNumber(playerSpeedText.getText())) {
                Pong.playerSpeed = Integer.parseInt(playerSpeedText.getText());
            }
            if (isNumber(botSpeedText.getText())) {
                Pong.botSpeed = Integer.parseInt(botSpeedText.getText());
            }
            frame.dispose();
        }
    }

    public boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
