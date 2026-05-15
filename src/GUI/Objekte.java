package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Objekte extends JPanel {
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
           Image board = ImageIO.read(new File("img/Board.png"));
           Image rookwl = ImageIO.read(new File("img/RookW.png"));
           Image rookwr = ImageIO.read(new File("img/RookW.png"));
           g.drawImage(board, 0, 0, null);
           g.drawImage(rookwl, 5, 5,70,70, null);
           g.drawImage(rookwr, 556, 5, 70, 70, null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
