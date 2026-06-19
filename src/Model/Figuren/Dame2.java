package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Dame2 extends Figur {

    public Dame2(int farbe, int stil) {
        super(farbe,  stil);
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
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] aufstellung, Spielelogik logik) {
        // gleiche Position -> kein Zug
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            return false;
        }
        // -- horizontaler Zug
        if (zielZeile == startZeile) {
            int richtung = (zielSpalte > startSpalte) ? 1 : -1; // ist Zielspalte größer als Startspalte -> richtung 1 = wird größer

        // Zwischenfelder prüfen
            for (int spalte = startSpalte + richtung; spalte != zielSpalte; spalte += richtung) {
                if (aufstellung[startZeile][spalte] != null) {
                    System.out.println("Figur blockiert den Weg");
                    return false;
                }
            }
        // Zielfeld prüfen
            Figur figurAmZiel = aufstellung[zielZeile][zielSpalte];

            if (figurAmZiel != null) {
                if (figurAmZiel.getFarbe() == this.getFarbe()) {
                    System.out.println("Eigene Figur steht dort");
                    return false;
                }

                System.out.println("Gegnerische Figur schlagen");
                return true;
            }

            System.out.println("Freies Feld");
            return true;
        }
        // || vertikal
        if (zielSpalte == startSpalte) {

            int richtung = (zielZeile > startZeile) ? 1 : -1;

            // Zwischenfelder prüfen
            for (int zeile = startZeile + richtung; zeile != zielZeile; zeile += richtung) {

                if (aufstellung[zeile][startSpalte] != null) {
                    System.out.println("Figur blockiert den Weg");
                    return false;
                }
            }

            // Zielfeld prüfen
            Figur figurAmZiel = aufstellung[zielZeile][zielSpalte];

            if (figurAmZiel != null) {

                if (figurAmZiel.getFarbe() == this.getFarbe()) {
                    System.out.println("Eigene Figur steht dort");
                    return false;
                }

                System.out.println("Gegnerische Figur schlagen");
                return true;
            }

            System.out.println("Freies Feld");
            return true;
        }
        int horizontaleStrecke = Math.abs(startZeile - zielZeile);
        int vertikaleStrecke = Math.abs(startSpalte - zielSpalte);


        if ((horizontaleStrecke == vertikaleStrecke)) { // zwischenfelder Prüfen
            int zeilenRichtung = (zielZeile > startZeile) ? 1 : -1;
            int spaltenRichtung = (zielSpalte > startSpalte) ? 1 : -1;

            int zeile = startZeile + zeilenRichtung;
            int spalte = startSpalte + spaltenRichtung;

            while (zeile != zielZeile && spalte != zielSpalte) {

                if (aufstellung[zeile][spalte] != null) {
                    System.out.println("Figur blockiert den Weg");
                    return false;
                }

                zeile += zeilenRichtung;
                spalte += spaltenRichtung;
            }
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
        }
        return false;



    }
}
