package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Objekte extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        try {
//           Image board = ImageIO.read(new File("img/Board.png"));
//           Image rookwl = ImageIO.read(new File("img/RookW.png"));
//           Image rookwr = ImageIO.read(new File("img/RookW.png"));
//           g.drawImage(board, 0, 0, null);
//           g.drawImage(rookwl, 5, 5,70,70, null);
//           g.drawImage(rookwr, 556, 5, 70, 70, null);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Color color = new Color(222, 227, 230);
        int y = 40;
        for (int i = 0; i < 8; i++) {
            int x;
            x = 80;
            for (int j = 0; j < 8; j++) {
                if (j == 0 && i > 0) {
                    if (color.equals(new Color(222, 227, 230))) {
                        color = new Color(140, 162, 173);
                    } else {
                        color = new Color(222, 227, 230);
                    }
                }
                g.setColor(color);
                g.fillRect(x, y, 80, 80);
                x = x + 80;
                if (color.equals(new Color(222, 227, 230))) {
                    color = new Color(140, 162, 173);
                } else {
                    color = new Color(222, 227, 230);
                }
            }
            y = y + 80;
        }
        int xstart = 80;
        int ystart = 40;
        int charwertbuchstabe = 65;
        int charwertzahl = 49;
        for (int i = 0; i < 8; i++) {
            char asciibuchstabe = (char) charwertbuchstabe;
            String text = String.valueOf(asciibuchstabe);
            Font koordinaten = new Font("ARIAL", Font.BOLD, 30);
            FontMetrics metrics = g.getFontMetrics(koordinaten);
            int breite = metrics.stringWidth(text);
            int sx = xstart + 40 - (breite/2);
            g.setFont(new Font("ARIAL", Font.BOLD, 30));
            g.drawString(text, sx, 720);
            xstart = xstart + 80;
            charwertbuchstabe++;

            char asciizahl = (char) charwertzahl;
            String zahl = String.valueOf(asciizahl);
            int hoehe = metrics.getAscent();
            int sy = ystart +40 + (hoehe/2);
            g.drawString(zahl, 40, sy);
            ystart = ystart + 80;
            charwertzahl++;
        }
    }
}
