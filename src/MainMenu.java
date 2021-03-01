import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    Image img = Toolkit.getDefaultToolkit().getImage("Backgrounds/11.jpg");
    JFrame frame;
    RendererMenu renderer;
    JButton button;
    public static Music menuMusic;

    public MainMenu() {
        menuMusic = new Music(4);
        frame = new JFrame("PONG");
        frame.setSize(1000, 700);

        renderer = new RendererMenu();
        renderer.setLayout(new GridLayout(1, 0));
        frame.add(renderer);

        button = new JButton(new ImageIcon("Icons/BUTTON.png"));
        button.setSize(100, 100);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        renderer.add(button);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new SecondMenu();
    }
}
