package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Figur extends JPanel implements MouseListener {

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            Image rookwl = ImageIO.read(new File("img/RookW.png"));
            g.drawImage(rookwl, 900, 900, 100, 100, null);
        }catch (IOException e){
            e.printStackTrace();
        }
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
