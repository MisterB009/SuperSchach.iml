package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Laeufer2 extends Figur {
    public Laeufer2(int farbe, int stil) {
        super(farbe, stil);

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
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] aufstellung, Spielelogik logik) {
        // gleiches Feld
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            return false;
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
