package Model;

import java.awt.*;

public abstract class Figur {
    protected static final int SCHWARZ = 0;
    protected static final int WEISS = 1;
    protected int farbe;
    protected Image bild;
//    protected int StartZeile;
//    protected int StartSpalte;

    // Construktor
    public Figur(int farbe) { // int zeilen1, int spalten1
        this.farbe = farbe;
//        this.StartZeile = zeilen1;
//        this.StartSpalte = spalten1;
    }

    public abstract boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] felder);

    //  steht etwas im Weg?

    // Getter & Setter
    public int getFarbe() {
        return farbe;
    }

    public void setFarbe(int farbe) {
        this.farbe = farbe;
    }

    public Image getBild() {
        return bild;
    }

    public void setBild(Image bild) {
        this.bild = bild;
    }

//    public int getStartZeile() {
//        return StartZeile;
//    }
//
//    public void setStartZeile(int startZeile) {
//        this.StartZeile = startZeile;
//    }
//
//    public int getStartSpalte() {
//        return StartSpalte;
//    }
//
//    public void setStartSpalte(int startSpalte) {
//        this.StartSpalte = startSpalte;
//    }
}
