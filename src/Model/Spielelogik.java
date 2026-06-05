package Model;

import Model.Figuren.*;
import Start.Settings;

import java.util.Set;

public class Spielelogik {
    private Figur[][] aufstellung; // feld anlegen

    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;
    private int stil;

    public Spielelogik() {
        this.aufstellung = new Figur[9][9]; // ##
        initialisiereAufstellung();
    }

    private void initialisiereAufstellung() {
        // weiß
        if (stil != 2&&stil != 1) {
            stil = 0;
        }
        if (stil == 0){
            stil = 1;
        }
        aufstellung[1][1] = new Turm2(1, 1, 1, stil);
        aufstellung[1][2] = new Springer2(1, 1, 2, stil);
        aufstellung[1][3] = new Laeufer2(1, 1, 3, stil);
        aufstellung[1][4] = new Dame2(1, 1, 4, stil);
        aufstellung[1][5] = new Koenig2(1, 1, 5, stil);
        aufstellung[1][6] = new Laeufer2(1, 1, 6, stil);
        aufstellung[1][7] = new Springer2(1, 1, 7, stil);
        aufstellung[1][8] = new Turm2(1, 1, 8, stil);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[2][spalte] = new Bauer2(1, 2, spalte, stil);

        }

        // schwarz
        aufstellung[8][1] = new Turm2(0, 8, 1, stil);
        aufstellung[8][2] = new Springer2(0, 8, 2, stil);
        aufstellung[8][3] = new Laeufer2(0, 8, 3, stil);
        aufstellung[8][4] = new Dame2(0, 8, 4, stil);
        aufstellung[8][5] = new Koenig2(0, 8, 5, stil);
        aufstellung[8][6] = new Laeufer2(0, 8, 6, stil);
        aufstellung[8][7] = new Springer2(0, 8, 7, stil);
        aufstellung[8][8] = new Turm2(0, 8, 8, stil);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[7][spalte] = new Bauer2(0, 7, spalte, stil); // Weiße Bauern
        }

    }

    public int getStil() {
        return stil;
    }

    public void setStil(int stil) {
        this.stil = stil;
    }

    // abwechseldes Ziehen (Figur 0/ 1)

    public boolean bewegeFigur(int startZeile, int startSpalte,
                               int zielZeile, int zielSpalte) {

        Figur figur = aufstellung[startZeile][startSpalte];

        if (figur == null) { // keine Figur
            return false;
        }

        if (!figur.istGueltigerZug(zielZeile, zielSpalte, aufstellung)) { // macht die Figur legalen Zug?
            System.out.println("Illegale Bewegung");
            return false;
        }

        aufstellung[zielZeile][zielSpalte] = figur;
        aufstellung[startZeile][startSpalte] = null;

        figur.setZeile(zielZeile);
        figur.setSpalte(zielSpalte);

        setLetzteStartPosition(startZeile, startSpalte);
        setLetzteZielPosition(zielZeile, zielSpalte);

        return true;
    }

    // Getter & Setter
    public Figur getFigur(int zeile, int spalte) {
        if (zeile < 1 || zeile > 8 || spalte < 1 || spalte > 8) {
            return null;
        }
        return aufstellung[zeile][spalte];
    }

    public void setzeFigur(Figur figur, int zeile, int spalte) {
        aufstellung[zeile][spalte] = figur;
    }

    public Figur[][] getFelder() {
        return aufstellung;
    }

    public void setLetzteStartPosition(int zeile, int spalte) {
        letzteStartZeile = 8 - zeile;
        letzteStartSpalte = spalte - 1;
    }

    public void setLetzteZielPosition(int zeile, int spalte) {
        letzteZielZeile = spalte - 1;
        letzteZielSpalte = 8 - zeile;
    }

    public void resetMarkierung() {
        letzteStartZeile = -1;
        letzteStartSpalte = -1;
        letzteZielZeile = -1;
        letzteZielSpalte = -1;
    }

    public int getLetzteStartZeile() {
        return letzteStartZeile;
    }

    public int getLetzteStartSpalte() {
        return letzteStartSpalte;
    }

    public int getLetzteZielSpalte() {
        return letzteZielSpalte;
    }

    public int getLetzteZielZeile() {
        return letzteZielZeile;
    }

    public Figur[][] getAufstellung() {
        return aufstellung;
    }
}
