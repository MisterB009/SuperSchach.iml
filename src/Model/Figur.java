package Model;

import java.awt.*;

public class Figur {
    protected static final int SCHWARZ = 0;
    protected static final int WEIß = 1;
    protected int farbe;
    protected Image bild;
    protected int zeile;
    protected int spalte;

    // Construktor
    public Figur(int farbe, int spalten, int zeilen) {
        this.farbe = farbe;
        this.zeile = spalten;
        this.spalte = zeilen;
    }

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

    public int getZeile() {
        return zeile;
    }

    public void setZeile(int zeile) {
        this.zeile = zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }
}
