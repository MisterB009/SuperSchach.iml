package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Springer2 extends Figur {
    public Springer2(int farbe, int spalten, int zeilen) {
        super(farbe, spalten, zeilen);
        if (farbe == 0){ // schwarz
            try {
                bild = ImageIO.read(new File("img/KnightB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1) { // weiß
            try {
                bild = ImageIO.read(new File("img/KnightW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    // @Override
//    public boolean istGueltigerZug(int zielRow, int zielCol) {
//        x +/- 2 && y +/-1  ||
    //    x +/- 1 && y +/-2
//    }

//    @Override
//    public boolean istGueltigerZug(){
//        return true;
//    }
}
