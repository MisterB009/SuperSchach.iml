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
    public boolean istGueltigerZug(int zielZeile, int zielSpalte, Figur[][] aufstellung) {

        // gleiche Position -> kein Zug
        if (zielZeile == StartZeile && zielSpalte == StartSpalte) {
            System.out.println("gleiches Feld gewählt");
            return false;
        }
        // horizontaler Zug
        if (zielZeile == StartZeile) {

            int richtung = (zielSpalte > StartSpalte) ? 1 : -1; // ist Zielspalte größer als Startspalte -> richtung 1 = wird größer

            for (int spalte = StartSpalte + richtung; spalte != zielSpalte; spalte += richtung) { // zwischenfelder durchgehen

                if (aufstellung[StartZeile][spalte] != null) { // eine Figur auf dem Weg
                    System.out.println("Figur blockiert den Weg");
                    System.out.println("Zielzeile: " + zielZeile);
                    System.out.println("Zielspalte: " + zielSpalte);
                    return false;
                    }
                }
            Figur figurImWeg = aufstellung[zielZeile][zielSpalte];      // was ist das für eine Figur?
                if(figurImWeg.getFarbe() == this.getFarbe()){
                    System.out.println("Figur gehört dir");
                    return false;
                }
                if(figurImWeg.getFarbe() != this.getFarbe()){
                    System.out.println("Figur schlagen.");
                    return true;
                }
            System.out.println("keiner dazwischen");
            return true;
        }


        // vertikal
        if (zielSpalte == StartSpalte){

            int richtung = (zielZeile > StartZeile) ? 1 : -1; // hier ggf. tauschen?

            for (int zeile = StartZeile + richtung; zeile != zielSpalte; zeile += richtung) { // zwischenfelder durchgehen

                if (aufstellung[zeile][StartSpalte] != null) { // eine Figur auf dem Weg
                    System.out.println("Figur blockiert den Weg");
                    System.out.println("Zielzeile: " + zielZeile);
                    System.out.println("Zielspalte: " + zielSpalte);
                    return false;
                }
            }
            Figur figurImWeg = aufstellung[zielZeile][zielSpalte];      // was ist das für eine Figur?
            if(figurImWeg.getFarbe() == this.getFarbe()){
                System.out.println("Figur gehört dir");
                return false;
            }
            if(figurImWeg.getFarbe() != this.getFarbe()){
                System.out.println("Figur schlagen.");
                return true;
            }
            System.out.println("keiner dazwischen");
            return true;
        }


        System.out.println("illegaler Zug");
        return false;

    }
}
