package GUI;

import Funktion.turm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class Brett extends JPanel implements MouseMotionListener {
    private int hoverRow = -1;
    private int hoverCol = -1;

    //Weiße Figuren:
    private turm rookwl;
    private turm rookwr;
    private Image knightw;
    private Image bishopw;
    private Image queenw;
    private Image kingw;
    private Image pawnw;

    //Schwarze Figuren:
    private turm rookbl;
    private turm rookbr;
    private Image knightb;
    private Image bishopb;
    private Image queenb;
    private Image kingb;
    private Image pawnb;


    // A: Koordinatensystem Methode
    public Brett(){ // attribute befüülen mit eigenschaften
        addMouseMotionListener(this);
        rookwl = new turm(1, 80, 40+(7*80));// farbe und Position
        rookwr = new turm(1, 80+(7*80), 40+(7*80));
        rookbl = new turm(0, 80, 40);
        rookbr = new turm(0, 80+(7*80), 40);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        //Startaufstellung
        this.zeichneStartaufstellung(g);

        // Seitenränder
        int charwertbuchstabe = 65;
        int charwertzahl = 56;
        g.setColor(Color.white);
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
            charwertzahl--;
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

    public void zeichneStartaufstellung(Graphics g){
        g.drawImage(rookwl.getZeichnen(), rookwl.getX()+rookwl.getKorrekturx(), rookwl.getY()+rookwl.getKorrektury(), 80, 80, null);
        g.drawImage(rookwr.getZeichnen(), rookwr.getX()+rookwr.getKorrekturx(), rookwr.getY()+rookwr.getKorrektury(), 80, 80, null);
        g.drawImage(rookbl.getZeichnen(), rookbl.getX()+rookbl.getKorrekturx(), rookbl.getY()+rookbl.getKorrektury(), 80, 80, null);
        g.drawImage(rookbr.getZeichnen(), rookbr.getX()+rookbr.getKorrekturx(), rookbr.getY()+rookbr.getKorrektury(), 80, 80, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}

//
