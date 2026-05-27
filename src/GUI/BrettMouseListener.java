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

        int spalte = e.getX() / 80;
        int zeile = 9 - (e.getY() / 80);

        // außerhalb
        if (spalte < 1 || spalte > 8 || zeile < 1 || zeile > 8) {
            return;
        }

        Figur[][] felder = logik.getFelder();

        // ===== ERSTER KLICK =====
        if (ausgewaehlteFigur == null) {

            Figur figur = felder[zeile][spalte];

            if (figur != null) {

                ausgewaehlteFigur = figur;

                startZeile = zeile;
                startSpalte = spalte;

                logik.setLetzteStartPosition(zeile, spalte);

                brett.repaint();
            }
        }

        // ===== ZWEITER KLICK =====
        else {

            // gleiche Figur wieder anklicken
            if (zeile == startZeile && spalte == startSpalte) {

                ausgewaehlteFigur = null;

                logik.resetMarkierung();

                brett.repaint();
                return;
            }

            // Figur bewegen
            felder[zeile][spalte] = ausgewaehlteFigur;

            felder[startZeile][startSpalte] = null;

            ausgewaehlteFigur.setZeile(zeile);
            ausgewaehlteFigur.setSpalte(spalte);

            logik.setLetzteZielPosition(zeile, spalte);

            ausgewaehlteFigur = null;

            brett.repaint();
        }
    }
//    @Override
//    public void mouseClicked(MouseEvent e) {
//    int spalte = e.getX() / 80;
//    int zeile = 9 - (e.getY() / 80);
//
//    // außerhalb des Bretts
//    if (spalte < 1 || spalte > 8 || zeile < 1 || zeile > 8) {
//        return;
//    }
//
//    // ===== ERSTER KLICK =====
//    if (ausgewaehlteFigur == null) {
//
//        Figur[][] aufstellung = logik.getFelder();
//        Figur figur = aufstellung[zeile][spalte];
//
//        // steht dort eine Figur?
//        if (figur != null) {
//
//            ausgewaehlteFigur = figur;
//            letzteStartZeile = 8 - zeile;
//            letzteStartSpalte = spalte - 1;
//
//            startZeile = zeile;
//            startSpalte = spalte;
//
//            System.out.println("Figur ausgewählt");
//            repaint();
//        }
//
//    }
//
//    // Zweiter Klick, Figur ausgewählt
//    else {
//
//        if (zeile == startZeile && spalte == startSpalte) {
//            // auswahl abbrechen
//            ausgewaehlteFigur = null;
//            letzteStartSpalte = -1;
//            letzteStartZeile = -1;
//            repaint();
//            return;
//        } else {
//            // Figur bewegen
//            aufstellung[zeile][spalte] = ausgewaehlteFigur;
//
//            // altes Feld leeren
//            aufstellung[startZeile][startSpalte] = null;
//
//            // neue Position speichern
//            ausgewaehlteFigur.setZeile(zeile);
//            ausgewaehlteFigur.setSpalte(spalte);
//
//            //anmalen
//            letzteZielSpalte = 8 - zeile;
//            letzteZielZeile = spalte -1;
//
//            // Auswahl zurücksetzen
//            ausgewaehlteFigur = null;
//
//            repaint();
//        }
//    }
//}
}
