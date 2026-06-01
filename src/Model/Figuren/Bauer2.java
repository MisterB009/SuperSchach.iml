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
            if (zielZeile == startZeile + 1 && zielSpalte == startSpalte) {
                System.out.println("legaler Zug");
                return true;
            }
            if (startZeile == 2 && zielZeile == 4) {
                return true;
            }
        }

        // Schwarz
        if (farbe == SCHWARZ) {

            // eine Zeile nach vorne (aus schwarzer Sicht)
            if (zielZeile == startZeile - 1 && zielSpalte == startSpalte) {
                System.out.println("legaler Zug");
                return true;
            }
            if (startZeile == 7 && zielZeile == 5) {
                return true;
            }

        }
        // Schräg schlagen -Ist auf dem Feld eine andersfarbige Figur? -  Figur[][] felder nutzen

        System.out.println("illegaler Zug");
        System.out.println("Zielzeile: " + zielSpalte);
        System.out.println("Zielzeile: " + zielZeile);
        return false;

    }


}
