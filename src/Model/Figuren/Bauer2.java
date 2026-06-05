package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bauer2 extends Figur {

    public Bauer2(int farbe, int zeile, int spalte, int stil) {
        super(farbe, zeile, spalte, stil);
        if (stil == 1){
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
        } else if (stil == 2){
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/BauerS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/BauerW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        if (farbe == WEIß) {

            // eine Zeile nach vorne, gleiche Spalte
            if (zielZeile == zeile + 1 && zielSpalte == spalte) {
                System.out.println("legaler Zug");
                return true;
            }
            if (zeile == 2 && zielZeile == 4) {
                return true;
            }
        }

        // Schwarz
        if (farbe == SCHWARZ) {

            // eine Zeile nach vorne (aus schwarzer Sicht)
            if (zielZeile == zeile - 1 && zielSpalte == spalte) {
                System.out.println("legaler Zug");
                return true;
            }
            if (zeile == 7 && zielZeile == 5) {
                return true;
            }

        }
        // Schräg schlagen -Ist auf dem Feld eine andersfarbige Figur? -  Figur[][] felder nutzen

        System.out.println("illegaler Zug");
        return false;

    }


}
