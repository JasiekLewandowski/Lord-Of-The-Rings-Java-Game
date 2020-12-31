import javax.swing.*;
import java.awt.*;

public class RendererPauseMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(PauseMenu.img, 0, 0, 600, 450,  this);
    }
}
