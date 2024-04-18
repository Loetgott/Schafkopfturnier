import javax.swing.*;
import java.awt.*;

public class JTisch extends JComponent {
    Color backgroundColor = new Color(217, 217, 217);
    Color linesColor = new Color(42, 42, 42);
    int width = 320;
    int height = 200;
    public JTisch() {
        setPreferredSize(new Dimension(width, height));
    }

    public JTisch(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Zeichne den Hintergrund
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);

        // Zeichne die Linien oder weitere Inhalte hier
        // Beispiel:
        g.setColor(linesColor);
        g.drawRect(0,0,width - 1,height - 1);
    }
}
