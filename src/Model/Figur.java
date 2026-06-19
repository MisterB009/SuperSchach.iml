package Model;

import java.awt.*;

public abstract class Figur {
    protected static final int SCHWARZ = 0;
    protected static final int WEISS = 1;
    protected int farbe;
    protected transient Image bild;
//    protected int StartZeile;
//    protected int StartSpalte;
    private int stil;

    // Construktor
    public Figur(int farbe, int stil) {
        this.farbe = farbe;
        this.stil = stil;
    }

    public abstract boolean istGueltigerZug(
            int startZeile,
            int startSpalte,
            int zielZeile,
            int zielSpalte,
            Figur[][] felder,
            Spielelogik logik);

        protected boolean istZielfeldLeer(Figur[][] aufstellung,
                                      int zielZeile,
                                      int zielSpalte) {

        return aufstellung[zielZeile][zielSpalte] == null;
    }

    protected boolean istEigeneFigur(Figur[][] aufstellung, int zielZeile, int zielSpalte) {

        Figur figurAufFeld = aufstellung[zielZeile][zielSpalte];

        return figurAufFeld != null &&
                figurAufFeld.getFarbe() == this.getFarbe();
    }

    protected boolean istGegnerischeFigur(Figur[][] aufstellung, int zielZeile, int zielSpalte) {

        Figur figurAufFeld = aufstellung[zielZeile][zielSpalte];

        return figurAufFeld != null && figurAufFeld.getFarbe() != this.getFarbe();
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
}
