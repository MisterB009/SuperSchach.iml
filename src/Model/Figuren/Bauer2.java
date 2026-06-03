package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bauer2 extends Figur {

    public Bauer2(int farbe) {
        super(farbe);
        if (farbe == 0){ // schwarz
            try {
                bild = ImageIO.read(new File("img/PawnB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1){ // weiß
            try {
                bild = ImageIO.read(new File("img/PawnW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] felder) {
        // gleiche Position -> kein Zug
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            System.out.println("gleiches Feld gewählt");
            return false;
        }
        if (farbe == WEISS) {
            // eine Zeile nach vorne, gleiche Spalte
            if (zielZeile == startZeile - 1
                    && zielSpalte == startSpalte
                    && felder[zielZeile][zielSpalte] == null) {
                return true;
            }
            if (felder[5][startSpalte] == null
                    && startZeile == 6 && zielZeile == 4
                    && zielSpalte == startSpalte) { // doppelzug
                return true;
            }
            if (zielZeile == startZeile + 1
                    && Math.abs(zielSpalte - startSpalte) == 1) {

                Figur zielFigur = felder[zielZeile][zielSpalte];

                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {
                    return true;
                }
            } // schräg schlagen
            if (zielZeile == startZeile - 1
                    && Math.abs(startSpalte - zielSpalte) == 1) {

                Figur zielFigur = felder[zielZeile][zielSpalte];
                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {
                    return true;

                }
            }
        }

        // Schwarz
        if (farbe == SCHWARZ) {
            // eine Zeile nach vorne (aus schwarzer Sicht)
            if (zielZeile == startZeile + 1 && zielSpalte == startSpalte) {
                System.out.println("legaler Zug");
                return true;
            }
            if (felder[2][startSpalte] == null
                    && startZeile == 1 && zielZeile == 3
                    && zielSpalte == startSpalte) { // doppelzug
                return true;
            }
            if (zielZeile == startZeile + 1
                    && Math.abs(startSpalte - zielSpalte) == 1) {

                Figur zielFigur = felder[zielZeile][zielSpalte];
                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {
                    return true;

                }
            }
        }
        // en passent (speicherung des letzten Zuges; wege)
        // umwandlung

        System.out.println("illegaler Zug: Bauer");
        System.out.println("Zielzeile: " + zielSpalte);
        System.out.println("Zielzeile: " + zielZeile);
        return false;
    }
}
