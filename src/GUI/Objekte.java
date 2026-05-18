package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class Objekte extends JPanel implements MouseMotionListener {
    private int hoverRow = -1;
    private int hoverCol = -1;

    public Objekte(){
        addMouseMotionListener(this);
    }


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


        Color hell = new Color(222, 227, 230);
        Color dunkel = new Color(140, 162, 173);
        Color hover = new Color(200, 100, 100);

        g.setColor(Color.darkGray); // Hintergrund
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Color color = hell;

        int y = 40;
        for (int i = 0; i < 8; i++) { // Zeilen
            int x;
            x = 80;
            for (int j = 0; j < 8; j++) {// Spalte
                if (j == 0 && i > 0) { // Color immer abwechseln
                    if (color.equals(hell)) {
                        color = dunkel;
                    } else {
                        color = hell;
                    }
                }

                // HOVER hinzufügen
                if (i == hoverRow && j == hoverCol) {
                    g.setColor(hover);
                } else {
                    g.setColor(color);
                }




                g.fillRect(x, y, 80, 80); // füllen
                x = x + 80; // alle weiteren Reihen
                if (color.equals(hell)) {
                    color = dunkel;
                } else {
                    color = hell;
                }
            }
            y = y + 80;
        }
        int xstart = 80;
        int ystart = 40;

        // Seitenränder
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
    @Override
    public void mouseMoved(MouseEvent e) {

        int startX = 80;
        int startY = 40;
        int feldGroesse = 80;

        int x = e.getX();
        int y = e.getY();

        // Prüfen ob Maus auf dem Brett ist
        if (x >= startX && x < startX + 8 * feldGroesse &&
                y >= startY && y < startY + 8 * feldGroesse) {

            hoverCol = (x - startX) / feldGroesse; // In welcher Zeile/ Spalte bin ich?
            hoverRow = (y - startY) / feldGroesse;

        } else {
            hoverRow = -1;
            hoverCol = -1;
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
