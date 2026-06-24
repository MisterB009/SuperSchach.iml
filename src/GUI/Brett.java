package GUI;

import Model.*;
import Start.Main;
// import Start.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Brett extends JPanel {

    private Spielelogik logik; // Referenz auf die Spielelogik

    private Figur ausgewaehlteFigur = null;

    private GeschlagenePanel panel;

    public Brett( Spielelogik logik, GeschlagenePanel panel) {

        this.logik = logik; // Spielelogik aufgreifen
        addMouseListener(new BrettMouseListener(this, logik, panel));
    }

    public Spielelogik getLogik(){
        return this.logik;
    }

    public void setLogik(Spielelogik neu){
        this.logik = neu;
    }

    public BrettMouseListener getMouseListener() {
        for (MouseListener ml : this.getMouseListeners()) {
            if (ml instanceof BrettMouseListener) {
                return (BrettMouseListener) ml;
            }
        }
        return null;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color dungelgrau = new Color(140, 162, 173);
        Color hellgrau =  new Color(222, 227, 230);
        Color klick = new Color(80, 124, 101);
        Color zugauswahl = new Color(121, 155, 130);
        Color lzherkunft = new Color(146, 177, 102);
        Color lzziel = new Color(195, 216, 135);
        Color schach = new Color(220, 80, 80);

        g.setColor(Color.darkGray); // Hintergrund
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Color color = hellgrau;

        int[] weisserKoenig = null;
        int[] schwarzerKoenig = null;

        if (logik.istKoenigImSchach(1)) {
            weisserKoenig = logik.findeKoenig(1);
        }

        if (logik.istKoenigImSchach(0)) {
            schwarzerKoenig = logik.findeKoenig(0);
        }

        int y3 = 80;
        for (int i = 0; i < 8; i++) { // Zeilen
            int x3;
            x3 = 80;
            for (int j = 0; j < 8; j++) {// Spalte
                if (j == 0 && i > 0) { // Color immer abwechseln
                    if (color.equals(hellgrau)) {
                        color = dungelgrau;
                    } else {
                        color = hellgrau;
                    }
                }

                // Markierung Figur
                if (i == logik.getLetzteStartZeile()  && j == logik.getLetzteStartSpalte()) {
                    g.setColor(lzherkunft);
                } else if (i == logik.getLetzteZielZeile() && j == logik.getLetzteZielSpalte()) {
                    g.setColor(lzziel);//A: nach einem zug das Herkunftsfeld mit lzherkunft färben und das Zielfeld mit lzziel
                } else {
                    g.setColor(color);
                }
                // SCHACH FARBE
                if (weisserKoenig != null && i == weisserKoenig[0] && j == weisserKoenig[1]) {
                    g.setColor(schach);
                } else if (schwarzerKoenig != null && i == schwarzerKoenig[0] && j == schwarzerKoenig[1]) {
                    g.setColor(schach);
                }
                g.fillRect(x3, y3, 80, 80); // füllen
                x3 = x3 + 80; // alle weiteren Reihen

                if (color.equals(hellgrau)) {
                    color = dungelgrau;
                } else {
                    color = hellgrau;
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

