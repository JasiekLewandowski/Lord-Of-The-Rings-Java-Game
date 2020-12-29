import javax.swing.*;
import java.awt.*;

public class RendererMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Pong.menu.img, 0, 0, 1000, 700,  this);
    }
}
