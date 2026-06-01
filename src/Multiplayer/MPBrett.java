package Multiplayer;

import GUI.BrettMouseListener;
import Model.Figur;
import Model.Spielelogik;
import Start.MPLobby;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class MPBrett extends JPanel {
    private Socket socket;
    private boolean istHost;

    private Spielelogik logik; // Referenz auf die Spielelogik

    private Figur ausgewaehlteFigur = null;
    private int startZeile;
    private int startSpalte;
    int hx,hy;

    public MPBrett(Socket verbindung, boolean istHost){
        this.socket = verbindung;
        this.istHost = istHost;
        this.logik = new Spielelogik(); // Spielelogik erzeugen
        addMouseListener(new BrettMouseListener(this, logik));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color hell = new Color(222, 227, 230);
        Color dunkel = new Color(140, 162, 173);
        Color klick = new Color(80, 124, 101);
        Color zugauswahl = new Color(121, 155, 130);
        Color lzherkunft = new Color(146, 177, 102);
        Color lzziel = new Color(195, 216, 135);

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

                // Markierung Figur
                if (i == logik.getLetzteStartZeile() && j == logik.getLetzteStartSpalte()) {
                    g.setColor(klick);
                    hx =  x2;
                    hy = y2;
                } else if (i == logik.getLetzteZielSpalte() && j == logik.getLetzteZielZeile()) {
                    g.setColor(lzherkunft);
                    g.fillRect(hx, hy, 80, 80);
                    repaint();
                    g.setColor(lzziel);//A: nach einem zug das Herkunftsfeld mit lzherkunft färben und das Zielfeld mit lzziel
                } else {
                    g.setColor(color);
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
        Figur[][] aufstellung = logik.getFelder();

        for (int zeile = 0; zeile < 9; zeile++) {

            for (int spalte = 0; spalte < 9; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur != null) {

                    int x = (spalte - 1) * 80 + 80;
                    int y = (9 - zeile) * 80;
                    g.drawImage(figur.getBild(), x, y, 80, 80, this);
                }
            }
        }
    }
}
