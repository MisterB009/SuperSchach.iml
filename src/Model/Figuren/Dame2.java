package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Dame2 extends Figur {

    public Dame2(int farbe, int spalten, int zeilen, int stil) {
        super(farbe, spalten, zeilen, stil);
        if (stil == 1) {
            if (farbe == 0) { // schwarz
                try {
                    bild = ImageIO.read(new File("img/QueenB.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1) { // weiß
                try {
                    bild = ImageIO.read(new File("img/QueenW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (stil == 2) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/DameS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/DameW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    @Override
//    public boolean istGueltigerZug(){
//        return true;
//    }
    //    public boolean istGueltigerZug(int zielRow, int zielCol) {
//        return zielRow == row || zielCol == col // // wenn die Dame in der Zeile oder Spalte bleibt ist es true
//        ||  ;  // Differenz ZielfeldX zu StartfeldX = Diff ZielfeldY zu StartfeldY
//
//    }

    @Override
    public boolean istGueltigerZug(int zielZeile, int zielSpalte, Figur[][] felder) {
        // Später die Bauernlogik
        return true;
    }
}
