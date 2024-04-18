import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JTisch extends JComponent {
    Color backgroundColor = new Color(218, 218, 218, 255);
    Color linesColor = new Color(42, 42, 42);
    int width = 320;
    int height = 200;
    String name;
    ArrayList<Player> playerList = new ArrayList<>();
    public JTisch() {
        setPreferredSize(new Dimension(width, height));
    }

    public JTisch(Dimension dimension) {
        this.width = dimension.width;
        this.height = dimension.height;
        setPreferredSize(new Dimension(width, height));
    }
    public void setName(String nName){
        this.name = nName;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Zeichne den Hintergrund
        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, width, height,20,20);

        // Zeichne die Linien oder weitere Inhalte hier
        // Beispiel:
        g.setColor(linesColor);
        g.drawRoundRect(0,0,width - 1,height - 1,20,20);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
        g.setColor(linesColor);
        if(!(name == null)){
            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth("Tisch " + name);
            int x = (width - stringWidth) / 2;
            g.drawString("Tisch " + name, x, 30); // Vertikale Position in der Mitte der Höhe
        }
    }
    public void setPlayer(Player player, int position){
        playerList.set(position,player);
        
    }
}
