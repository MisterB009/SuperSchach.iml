package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Koenig2 extends Figur {
    public Koenig2(int farbe, int spalten, int zeilen) {
        super(farbe, spalten, zeilen);


            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/KingB.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/KingW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @Override
    public boolean istGueltigerZug(int zielZeile, int zielSpalte,Figur[][] felder) {
        // Später die König
        return true;
    }
}
