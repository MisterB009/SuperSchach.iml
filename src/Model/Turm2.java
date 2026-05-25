package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Turm2 extends Figur{

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

//    @Override
//    public boolean istGueltigerZug(int ){
//        return true;
//    }

    // @Override
//    public boolean istGueltigerZug(int zielRow, int zielCol) {
//        return zielRow == row || zielCol == col; // wenn der TUrm in der Zeile oder Spalte bleibt ist es true
//    }
//    Aufgabenzettel 7
}
