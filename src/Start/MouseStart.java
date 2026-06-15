package Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseStart implements MouseListener {
    Main.WilkommenScreen func;
    MPLobby lobby;
    Color dG = new Color(110, 110,110);
    Color dGHover = new Color(140, 140, 140);
    public MouseStart(Main.WilkommenScreen func){
        super();
        this.func = func;
    }
    public MouseStart(MPLobby lobby){
        super();
        this.lobby = lobby;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton hilfBtn = (JButton)e.getSource();
        hilfBtn.setBackground(dGHover);
        hilfBtn.setForeground(Color.BLUE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton hilfBtn = (JButton)e.getSource();
        hilfBtn.setBackground(dG);
        hilfBtn.setForeground(Color.WHITE);
    }
}
//commit
