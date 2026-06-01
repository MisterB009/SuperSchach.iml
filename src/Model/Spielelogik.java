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
        aufstellung[1][1] = new Turm2(0, 1, 1);
        aufstellung[1][2] = new Springer2(0, 1, 2);
        aufstellung[1][3] = new Laeufer2(0, 1, 3);
        aufstellung[1][4] = new Dame2(0, 1, 4);
        aufstellung[1][5] = new Koenig2(0, 1, 5);
        aufstellung[1][6] = new Laeufer2(0, 1, 6);
        aufstellung[1][7] = new Springer2(0, 1, 7);
        aufstellung[1][8] = new Turm2(0, 1, 8);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[2][spalte] = new Bauer2(0, 2, spalte);

        }

        // schwarz
        aufstellung[8][1] = new Turm2(1, 8, 1);
        aufstellung[8][2] = new Springer2(1, 8, 2);
        aufstellung[8][3] = new Laeufer2(1, 8, 3);
        aufstellung[8][4] = new Dame2(1, 8, 4);
        aufstellung[8][5] = new Koenig2(1, 8, 5);
        aufstellung[8][6] = new Laeufer2(1, 8, 6);
        aufstellung[8][7] = new Springer2(1, 8, 7);
        aufstellung[8][8] = new Turm2(1, 8, 8);

        for (int spalte = 1; spalte <= 8; spalte++) {
            aufstellung[7][spalte] = new Bauer2(1, 7, spalte); // Weiße Bauern
        }

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
            Figur figurNochDa = aufstellung[zielZeile][zielSpalte];
            System.out.println("Ist hier was?! " + figurNochDa);
            printBrett();
            return false;
        }

        aufstellung[zielZeile][zielSpalte] = figur;
        aufstellung[startZeile][startSpalte] = null;

        figur.setStartZeile(zielZeile);
        figur.setStartSpalte(zielSpalte);

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

    // aufstellung ansehen
    public void printBrett() {
        for (int zeile = 0; zeile < 9; zeile++) {

            for (int spalte = 0; spalte < 9; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur == null) {
                    System.out.print(".. ");
                } else {

                    String symbol = "";

                    if (figur instanceof Turm2) symbol = "T";
                    else if (figur instanceof Springer2) symbol = "S";
                    else if (figur instanceof Laeufer2) symbol = "L";
                    else if (figur instanceof Dame2) symbol = "D";
                    else if (figur instanceof Koenig2) symbol = "K";
                    else if (figur instanceof Bauer2) symbol = "B";

                    if (figur.getFarbe() == 1) {
                        System.out.print("W" + symbol + " ");
                    } else {
                        System.out.print("S" + symbol + " ");
                    }
                }
            }

            System.out.println();
        }
    }
}
