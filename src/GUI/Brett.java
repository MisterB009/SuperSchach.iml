package GUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Brett extends JPanel implements MouseListener {
    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;

    private int Yfarbeanders = 0;//Schalter falls die Farbe des Feldes schon geändert wurde
    private ArrayList<Integer> positionenFigurenX;
    private ArrayList<Integer> positionenFigurenY;
    private int HilfsX = -1;
    private int HilfsY = -1;

    // Brett aus Figuren
    private Figur[][] aufstellung; // Variable erzeugen

    private Figur ausgewaehlteFigur =null;
    private int startZeile;
    private int startSpalte;



    // A: Koordinatensystem Methode - Aufstellung Methode
    public Brett(){ // attribute befüülen mit eigenschaften
        addMouseListener(this);

        // übersichtlicher:
        // Türme
        aufstellung = new Figur[9][9]; // 8 reihen & Spalten erzeugen
        aufstellung[1][1] = new Turm2(1,1,1);
        aufstellung[1][2] = new Springer2(1,1,2);
        aufstellung[1][3] = new Laeufer2(1,1,3);
        aufstellung[1][4] = new Dame2(1,1,4);
        aufstellung[1][5] = new Koenig2(1,1,5);
        aufstellung[1][6] = new Laeufer2(1,1,6);
        aufstellung[1][7] = new Springer2(1,1,7);
        aufstellung[1][8] = new Turm2(1,1,8);

        // ggf. schleife
        aufstellung[2][1] = new Bauer2(1,2,1);
        aufstellung[2][2] = new Bauer2(1,2,2);
        aufstellung[2][3] = new Bauer2(1,2,3);
        aufstellung[2][4] = new Bauer2(1,2,4);
        aufstellung[2][5] = new Bauer2(1,2,5);
        aufstellung[2][6] = new Bauer2(1,2,6);
        aufstellung[2][7] = new Bauer2(1,2,7);
        aufstellung[2][8] = new Bauer2(1,2,8);

        // ggf. schleife
        aufstellung[7][1] = new Bauer2(0,7,1);
        aufstellung[7][2] = new Bauer2(0,7,2);
        aufstellung[7][3] = new Bauer2(0,7,3);
        aufstellung[7][4] = new Bauer2(0,7,4);
        aufstellung[7][5] = new Bauer2(0,7,5);
        aufstellung[7][6] = new Bauer2(0,7,6);
        aufstellung[7][7] = new Bauer2(0,7,7);
        aufstellung[7][8] = new Bauer2(0,7,8);

        aufstellung[8][1] = new Turm2(0,8,1);
        aufstellung[8][2] = new Springer2(0,8,2);
        aufstellung[8][3] = new Laeufer2(0,8,3);
        aufstellung[8][4] = new Dame2(0,8,4);
        aufstellung[8][5] = new Koenig2(0,8,5);
        aufstellung[8][6] = new Laeufer2(0,8,6);
        aufstellung[8][7] = new Springer2(0,8,7);
        aufstellung[8][8] = new Turm2(0,8,8);

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

        int x2 = 80;
        for (int i = 0; i < 8; i++) { // Zeilen
            int y2;
            y2 = 80;
            for (int j = 0; j < 8; j++) {// Spalte
                if (j == 0 && i > 0) { // Color immer abwechseln
                    if (color.equals(hell)) {
                        color = dunkel;
                    } else {
                        color = hell;
                    }
                }

                // Markierung Startfeld
                if (i == letzteStartZeile && j == letzteStartSpalte) {
                    g.setColor(hover);
                } else if (i == letzteZielSpalte && j == letzteZielZeile) {
                    g.setColor(hover);
                } else {
                    g.setColor(color);
                }

                // Markierung Zielfeld
                if (i == letzteStartZeile && j == letzteStartSpalte) {
                    g.setColor(hover);
                }


                g.fillRect(y2, x2, 80, 80); // füllen
                y2 = y2 + 80; // alle weiteren Reihen

                if (color.equals(hell)) {
                    color = dunkel;
                } else {
                    color = hell;
                }
            }
            x2 = x2 + 80;
        }
        int xstart = 80;
        int ystart = 80;

        //Startaufstellung
//        this.zeichneStartaufstellung(g);

        // Seitenränder -- Könnte man auch in Aufstellung machen?
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
            g.drawString(text, sx, 760);
            xstart = xstart + 80;
            charwertbuchstabe++;

            char asciizahl = (char) charwertzahl;
            String zahl = String.valueOf(asciizahl);
            int hoehe = metrics.getAscent();
            int sy = ystart + 40 + (hoehe/2);
            g.drawString(zahl, 40, sy);
            ystart = ystart + 80;
            charwertzahl--;
        }

        for (int zeile = 0; zeile < 9; zeile++) { // Zeile auf 0 ändern

            for (int spalte = 0; spalte < 9; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur != null) {

                    int VarX = spalte * 80;
                    int VarY = (9 - zeile) * 80;

                    g.drawImage(figur.getBild(), VarX, VarY, 80, 80, this);
                }
            }
        }
    }



@Override
public void mouseClicked(MouseEvent e) {
    int spalte = e.getX() / 80;
    int zeile = 9 - (e.getY() / 80);

    // außerhalb des Bretts
    if (spalte < 1 || spalte > 8 || zeile < 1 || zeile > 8) {
        return;
    }

    // ===== ERSTER KLICK =====
    if (ausgewaehlteFigur == null) {

        Figur figur = aufstellung[zeile][spalte];

        // steht dort eine Figur?
        if (figur != null) {

            ausgewaehlteFigur = figur;
            letzteStartZeile = 8 - zeile;
            letzteStartSpalte = spalte - 1;

            startZeile = zeile;
            startSpalte = spalte;

            System.out.println("Figur ausgewählt");
            repaint();
        }

    }

    // Zweiter Klick, Figur ausgewählt
    else {

        if (zeile == startZeile && spalte == startSpalte) {
            // auswahl abbrechen
            ausgewaehlteFigur = null;
            letzteStartSpalte = -1;
            letzteStartZeile = -1;
            repaint();
            return;
        } else {
            // Figur bewegen
            aufstellung[zeile][spalte] = ausgewaehlteFigur;

            // altes Feld leeren
            aufstellung[startZeile][startSpalte] = null;

            // neue Position speichern
            ausgewaehlteFigur.setZeile(zeile);
            ausgewaehlteFigur.setSpalte(spalte);

            //anmalen
            letzteZielSpalte = 8 - zeile;
            letzteZielZeile = spalte -1;

            // Auswahl zurücksetzen
            ausgewaehlteFigur = null;

            repaint();
        }
    }
}



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        hoverCol = -1;
//        hoverRow = -1;
//        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

//
