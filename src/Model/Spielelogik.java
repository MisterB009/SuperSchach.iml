package Model;

import Model.Figuren.*;

public class Spielelogik {
    private Figur[][] felder; // feld anlegen

    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;

    public Spielelogik() {
        this.felder = new Figur[8][8]; // ##
        initialisiereAufstellung();
    }

    private void initialisiereAufstellung() {
        // --- Schwarze Figuren (oben) ---
        felder[0][0] = new Turm2(Figur.SCHWARZ);
        felder[0][1] = new Springer2(Figur.SCHWARZ);
        felder[0][2] = new Laeufer2(Figur.SCHWARZ);
        felder[0][3] = new Dame2(Figur.SCHWARZ);
        felder[0][4] = new Koenig2(Figur.SCHWARZ);
        felder[0][5] = new Laeufer2(Figur.SCHWARZ);
        felder[0][6] = new Springer2(Figur.SCHWARZ);
        felder[0][7] = new Turm2(Figur.SCHWARZ);

        for (int i = 0; i < 8; i++) {
            felder[1][i] = new Bauer2(Figur.SCHWARZ);
        }

        // --- Weiße Figuren (unten) ---
        for (int i = 0; i < 8; i++) {
            felder[6][i] = new Bauer2(Figur.WEISS);
        }

        felder[7][0] = new Turm2(Figur.WEISS);
        felder[7][1] = new Springer2(Figur.WEISS);
        felder[7][2] = new Laeufer2(Figur.WEISS);
        felder[7][3] = new Dame2(Figur.WEISS);
        felder[7][4] = new Koenig2(Figur.WEISS);
        felder[7][5] = new Laeufer2(Figur.WEISS);
        felder[7][6] = new Springer2(Figur.WEISS);
        felder[7][7] = new Turm2(Figur.WEISS);
    }



    // abwechseldes Ziehen (Figur 0/ 1)

    public boolean bewegeFigur(int startZeile, int startSpalte, int zielZeile, int zielSpalte) {

        Figur figur = felder[startZeile][startSpalte];

        if (figur == null) { // keine Figur
            return false;
        }

        if (!figur.istGueltigerZug(startZeile, startSpalte, zielZeile, zielSpalte, felder)) { // macht die Figur legalen Zug?
            System.out.println("Illegale Bewegung");
            Figur figurNochDa = felder[zielZeile][zielSpalte];
            System.out.println("Ist hier was?! " + figurNochDa);
//            printBrett();
            return false;
        }

        felder[zielZeile][zielSpalte] = figur;
        felder[startZeile][startSpalte] = null;

//        figur.setStartZeile(zielZeile);
//        figur.setStartSpalte(zielSpalte);

        setLetzteStartPosition(startZeile, startSpalte);
        setLetzteZielPosition(zielZeile, zielSpalte);

        return true;
    }

    // Getter & Setter
    public Figur getFigur(int zeile, int spalte) {
        if (zeile < 1 || zeile > 8 || spalte < 1 || spalte > 8) {
            return null;
        }
        return felder[zeile][spalte];
    }

    public void setzeFigur(Figur figur, int zeile, int spalte) {
        felder[zeile][spalte] = figur;
    }


    public void setLetzteStartPosition(int zeile, int spalte) {
        letzteStartZeile = zeile;
        letzteStartSpalte = spalte;
    }

    public void setLetzteZielPosition(int zeile, int spalte) {
        letzteZielZeile = zeile;
        letzteZielSpalte = spalte;
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

    public Figur[][] getFelder() {
        return felder;
    }

    // aufstellung ansehen
    public void printBrett() {
        for (int zeile = 0; zeile < 8; zeile++) {

            for (int spalte = 0; spalte < 8; spalte++) {

                Figur figur = felder[zeile][spalte];

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
