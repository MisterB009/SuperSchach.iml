package Model;

import Model.Figuren.*;

public class Spielelogik {
    private Figur[][] aufstellung; // feld anlegen

    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;

    public Spielelogik() {
        this.aufstellung = new Figur[9][9]; // ##
        initialisiereAufstellung();
    }

    private void initialisiereAufstellung() {
        // weiß
        aufstellung[1][1] = new Turm2(1, 1, 1);
        aufstellung[1][2] = new Springer2(1, 1, 2);
        aufstellung[1][3] = new Laeufer2(1, 1, 3);
        aufstellung[1][4] = new Dame2(1, 1, 4);
        aufstellung[1][5] = new Koenig2(1, 1, 5);
        aufstellung[1][6] = new Laeufer2(1, 1, 6);
        aufstellung[1][7] = new Springer2(1, 1, 7);
        aufstellung[1][8] = new Turm2(1, 1, 8);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[2][spalte] = new Bauer2(1, 2, spalte);

        }

        // schwarz
        aufstellung[8][1] = new Turm2(0, 8, 1);
        aufstellung[8][2] = new Springer2(0, 8, 2);
        aufstellung[8][3] = new Laeufer2(0, 8, 3);
        aufstellung[8][4] = new Dame2(0, 8, 4);
        aufstellung[8][5] = new Koenig2(0, 8, 5);
        aufstellung[8][6] = new Laeufer2(0, 8, 6);
        aufstellung[8][7] = new Springer2(0, 8, 7);
        aufstellung[8][8] = new Turm2(0, 8, 8);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[7][spalte] = new Bauer2(0, 7, spalte); // Weiße Bauern
        }

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
