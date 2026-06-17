package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Turm2 extends Figur {
    public boolean turmBewegt = false;

    public Turm2(int farbe, int stil) {
        super(farbe, stil);
        System.out.println("turm: "+stil);
        if (stil == 1) {
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
        }else if (stil == 2){
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/TurnS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/TurnW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] aufstellung, Spielelogik logik) {

        // gleiche Position -> kein Zug
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            System.out.println("gleiches Feld gewählt");
            return false;
        }
        // -- horizontaler Zug
        if (zielZeile == startZeile) {
            System.out.println("hi1");

            int richtung = (zielSpalte > startSpalte) ? 1 : -1; // ist Zielspalte größer als Startspalte -> richtung 1 = wird größer

        // Zwischenfelder prüfen
            for (int spalte = startSpalte + richtung; spalte != zielSpalte; spalte += richtung) {
                if (aufstellung[startZeile][spalte] != null) {
                    System.out.println("Figur blockiert den Weg-h");
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
                turmBewegt = true;
                System.out.println("Gegnerische Figur schlagen");
                return true;
            }
            turmBewegt = true;
            System.out.println("Freies Feld");
            return true;
        }
        // || vertikal
        if (zielSpalte == startSpalte) {
            int richtung = (zielZeile > startZeile) ? 1 : -1; // hier ggf. tauschen?

            // Zwischenfelder prüfen
            for (int zeile = startZeile + richtung; zeile != zielZeile; zeile += richtung) { // zwischenfelder durchgehen
                if (aufstellung[zeile][startSpalte] != null) { // eine Figur auf dem Weg
                    System.out.println("Figur blockiert den Weg-v");
                    return false;
                }
            }

            // Zielfeld prüfen
            System.out.println("du darfst gehen- v");
            Figur figurImWeg = aufstellung[zielZeile][zielSpalte];
            if (figurImWeg != null) { // es ist eine figur auf dem Zielfeld
                if (figurImWeg.getFarbe() == this.getFarbe()) {
                    System.out.println("Figur gehört dir");
                    return false;
                }
                if (figurImWeg.getFarbe() != this.getFarbe()) {
                    System.out.println("Figur schlagen.");
                    turmBewegt = true;
                    return true;
                }
            }
            // keine FIgur auf dem Zielfeld
            turmBewegt = true;
            return true;
        }
        System.out.println("illegaler Zug");
        return false;
    }

    public boolean isTurmBewegt() {
        return turmBewegt;
    }

    public void kurzeRochade(Figur[][] aufstellung, int zeile) {

        aufstellung[zeile][5] = aufstellung[zeile][7];
        aufstellung[zeile][7] = null;

        turmBewegt = true;
    }
    public void langeRochade(Figur[][] aufstellung, int zeile) {

        aufstellung[zeile][3] = aufstellung[zeile][0];
        aufstellung[zeile][0] = null;

        turmBewegt = true;
    }
}
