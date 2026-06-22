package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Springer extends Figur {
    public Springer(int farbe, int stil) {
        super(farbe,  stil);
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
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte,Figur[][] aufstellung, Spielelogik logik) {
        int zeilenDifferenz = Math.abs(zielZeile - startZeile);
        int spaltenDifferenz = Math.abs(zielSpalte - startSpalte);

        if ((zeilenDifferenz == 2 && spaltenDifferenz == 1) ||
                (zeilenDifferenz == 1 && spaltenDifferenz == 2)) {
            // Zielfeld prüfen
            Figur figurImWeg = aufstellung[zielZeile][zielSpalte];
            if (figurImWeg != null) {
                if (figurImWeg.getFarbe() == this.getFarbe()) {
                    System.out.println("Eigene Figur");
                    return false;
                }
                if (figurImWeg.getFarbe() != this.getFarbe()) {
                    System.out.println("Figur schlagen.");
                    return true;
                }
            }
            System.out.println("freies Feld");
            return true;

        } else {
            System.out.println("falsche Bewegung");
            return false;
        }

    }
}
