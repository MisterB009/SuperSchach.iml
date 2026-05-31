package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Turm2 extends Figur {

    public Turm2(int farbe, int zeile, int spalte) {
        super(farbe, zeile, spalte);

        if (farbe == 0){ // schwarz
            try {
                bild = ImageIO.read(new File("img/RookB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1){ // weiß
            try {
                bild = ImageIO.read(new File("img/RookW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean istGueltigerZug(int zielZeile, int zielSpalte, Figur[][] felder) {

        // gleiche Position -> kein Zug
        if (zielZeile == zeile && zielSpalte == spalte) {
            System.out.println("gleiches Feld gewählt");
            return false;

        }

        if(zielZeile == zeile ||zielSpalte == spalte ){ // bleibt in Startzeile/-Spalte
            System.out.println("legaler Zug");
            return true;
        }
        System.out.println("illegaler Zug");
        return false;

    }
}
