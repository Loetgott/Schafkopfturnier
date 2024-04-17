import javax.swing.*;
import java.awt.*;

public class JTisch extends JComponent {
    Color backgroundColor = new Color(170, 170, 178);
    Color linesColor = new Color(42, 42, 42);
    int width = 100;
    int height = 100;
    public JTisch() {

    }
    @Override
    public void paint(Graphics g){
        g.setColor(backgroundColor);
        g.drawRect(0,0,width,height);
    }
}
