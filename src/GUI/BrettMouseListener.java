package GUI;

import Model.Figur;
import Model.Spielelogik;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BrettMouseListener extends MouseAdapter {
    private Brett brett;
    private Spielelogik logik;

    private Figur ausgewaehlteFigur = null;

    private int startZeile;
    private int startSpalte;


    public BrettMouseListener(Brett brett, Spielelogik logik) {
        this.brett = brett;
        this.logik = logik;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        int spalte = ( e.getX() - 80) / 80 ;
        int zeile = (e.getY() - 80) / 80  ;

        // außerhalb
        if (spalte < 0 || spalte > 7 || zeile < 0 || zeile > 7) {
            return;
        }

        Figur[][] felder = logik.getFelder();

        // ===== ERSTER KLICK ===== Figur wählen
        if (ausgewaehlteFigur == null) {

            Figur figur = felder[zeile][spalte];
            if (figur != null) {

                ausgewaehlteFigur = figur;

                startZeile = zeile;
                startSpalte = spalte;
                System.out.println("Start Zeile: " + startZeile);
                System.out.println("Start Spalte: "+ startSpalte);

                logik.setLetzteStartPosition(zeile, spalte);

                brett.repaint();
            }
        }

        // ===== ZWEITER KLICK ===== bewegen
        else {
            // gleiche Position wieder anklicken = Reset
            if (zeile == startZeile && spalte == startSpalte) {
                ausgewaehlteFigur = null;
                logik.resetMarkierung();
                brett.repaint();
                return;
            }

            // legaler Zug
            boolean erfolgreich = logik.bewegeFigur(startZeile,startSpalte,zeile, spalte );
            if (!erfolgreich) {
                ausgewaehlteFigur = null;
                logik.resetMarkierung();
                brett.repaint();
                return;
            }
            ausgewaehlteFigur = null;
            brett.repaint();
        }
    }


    public Figur getAusgewaehlteFigur() {
        return ausgewaehlteFigur;
    }

    public int getStartSpalte() {
        return startSpalte;
    }

    public int getStartZeile() {
        return startZeile;
    }
}
