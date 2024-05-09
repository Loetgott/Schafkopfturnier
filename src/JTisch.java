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
    JTable table = new JTable(4,3);
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

        g.setColor(backgroundColor);
        g.fillRoundRect(0, 0, width, height,20,20);
        g.setColor(linesColor);
        g.drawRoundRect(0,0,width - 1,height - 1,30,30);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
        g.setColor(linesColor);
        if(!(name == null)){
            FontMetrics fm = g.getFontMetrics();
            int stringWidth = fm.stringWidth("Tisch " + name);
            int x = (width - stringWidth) / 2;
            g.drawString("Tisch " + name, x, 30);
        }
        add(table);
        table.setBounds(15, 50, 300, 140);
        table.setBackground(backgroundColor);
        table.setForeground(linesColor);
        table.setFont(table.getFont().deriveFont(Font.BOLD, 26));
        table.getColumnModel().getColumn(1).setPreferredWidth(220);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.setRowHeight(30);
        for(int i = 0; i < playerList.size(); i ++){
            //table.setValueAt(i + 1 , i,0);
            table.setValueAt(playerList.get(i).getName()[0] + " " + playerList.get(i).getNachname().charAt(0) + ".", i,1);
            table.setValueAt(playerList.get(i).getOldPoints(),i ,2);
        }
    }
    public void setPlayer(Player player, int position){
        if (playerList.size() > position && !playerList.isEmpty()) {
            playerList.set(position,player);
        } else {
            playerList.add(position,player);
        }
        repaint();
    }
}
