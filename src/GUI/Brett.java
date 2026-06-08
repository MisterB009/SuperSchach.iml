package GUI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class Brett extends JPanel {
    private static final int FELDGROESSE = 80;
    private static final int BRETT_GROESSE = 8 * FELDGROESSE;

    private Spielelogik logik; // Referenz auf die Spielelogik

    private Figur ausgewaehlteFigur = null;
    private int startZeile;
    private int startSpalte;
    int hx,hy;

    public Brett() {
        this.logik = new Spielelogik(); // Spielelogik erzeugen
        addMouseListener(new BrettMouseListener(this, logik));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // immer mittig
//        int offsetX = (getWidth() - BRETT_GROESSE) / 2;
//        int offsetY = (getHeight() - BRETT_GROESSE) / 2;

        Color dunkel = new Color(140, 162, 173);
        Color hell = new Color(222, 227, 230);
        Color klick = new Color(80, 124, 101);
        Color zugauswahl = new Color(121, 155, 130);
        Color lzherkunft = new Color(146, 177, 102);
        Color lzziel = new Color(195, 216, 135);

        g.setColor(Color.darkGray); // Hintergrund
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Color color = dunkel;

        int y3 = 80;

        for (int i = 0; i < 8; i++) { // Zeilen
            int x3;
            x3 = 80;
            for (int j = 0; j < 8; j++) {// Spalte
                if (j == 0 && i > 0) { // Color immer abwechseln
                    if (color.equals(dunkel)) {
                        color = hell;
                    } else {
                        color = dunkel;
                    }
                }

                // Markierung Figur
                if (i == logik.getLetzteStartZeile()  && j == logik.getLetzteStartSpalte()) {
                    g.setColor(lzherkunft);
                    hx =  x3 + 80;
                    hy =  y3 - 160;
//                    System.out.println(
//                            "Markierung: Zeile=" + logik.getLetzteStartZeile()
//                                    + " Spalte=" + logik.getLetzteStartSpalte()
//                    );
                } else if (i == logik.getLetzteZielZeile() && j == logik.getLetzteZielSpalte()) {
                    g.setColor(color);
                    g.fillRect(hx , hy , 80, 80);
//                    repaint();
                    g.setColor(lzziel);//A: nach einem zug das Herkunftsfeld mit lzherkunft färben und das Zielfeld mit lzziel
                } else {
                    g.setColor(color);
                }
                g.fillRect(x3, y3, 80, 80); // füllen
                x3 = x3 + 80; // alle weiteren Reihen

                if (color.equals(dunkel)) {
                    color = hell;
                } else {
                    color = dunkel;
                }
            }
            y3 = y3 + 80;
        }

        int xstart = 80;
        int ystart = 80;

        // Seitenränder -
        int charwertbuchstabe = 65;
        int charwertzahl = 56;
        g.setColor(Color.white);
        for (int i = 0; i < 8; i++) {
            char asciibuchstabe = (char) charwertbuchstabe;
            String text = String.valueOf(asciibuchstabe);
            Font koordinaten = new Font("ARIAL", Font.BOLD, 30);
            FontMetrics metrics = g.getFontMetrics(koordinaten);
            int breite = metrics.stringWidth(text);
            int sx = xstart + 40 - (breite / 2);
            g.setFont(new Font("ARIAL", Font.BOLD, 30));
            g.drawString(text, sx, 760);
            xstart = xstart + 80;
            charwertbuchstabe++;

            char asciizahl = (char) charwertzahl;
            String zahl = String.valueOf(asciizahl);
            int hoehe = metrics.getAscent();
            int sy = ystart + 40 + (hoehe / 2);
            g.drawString(zahl, 40, sy);
            ystart = ystart + 80;
            charwertzahl--;
        }

        // Figuren aufstellen
        Figur[][] aufstellung = logik.getAufstellung();

        for (int zeile = 0; zeile < 8; zeile++) {

            for (int spalte = 0; spalte < 8; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur != null) {

                    int x = spalte * 80 + 80;// werden verschoben, s.d. sie auf den Feldern dargestellt werden
                    int y = zeile * 80 + 80;
                    g.drawImage(figur.getBild(), x, y, 80, 80, this);
                }
            }
        }
    }
}

