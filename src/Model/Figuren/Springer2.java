package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Springer2 extends Figur {
    public Springer2(int farbe, int spalten, int zeilen, int stil) {
        super(farbe, spalten, zeilen, stil);
        if (stil == 1) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/KnightB.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1) { // weiß
                try {
//                bild = ImageIO.read(new File("img/KnightW.png"));
                    bild = ImageIO.read(new File("img/KnightW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (stil == 2) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/SpringerS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1) { // weiß
                try {
//                bild = ImageIO.read(new File("img/KnightW.png"));
                    bild = ImageIO.read(new File("img/SuperFiguren/SpringerW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    @Override
    public boolean istGueltigerZug(int zielZeile, int zielSpalte,Figur[][] felder) {
        // Später die Bauernlogik
        return true;
    }
}
