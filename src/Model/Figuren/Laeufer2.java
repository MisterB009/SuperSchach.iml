package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Laeufer2 extends Figur {
    public Laeufer2(int farbe, int spalten, int zeilen, int stil) {
        super(farbe, spalten, zeilen, stil);

        if (stil == 1) {
            if (farbe == 0) { // schwarz
                try {
                    bild = ImageIO.read(new File("img/BishopB.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1) { // weiß
                try {
                    bild = ImageIO.read(new File("img/BishopW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (stil == 2) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/LaueferS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/LaueferW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean istGueltigerZug(int zielZeile, int zielSpalte,Figur[][] felder) {
        // Später die Läufer
        return true;
    }
}
