package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Bauer2 extends Figur {

    public Bauer2(int farbe, int stil) {
        super(farbe, stil);
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
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] aufstellung,
                                   Spielelogik logik) {
        // gleiche Position -> kein Zug
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            return false;
        }
        if (farbe == WEISS) {
            // eine Zeile nach vorne, gleiche Spalte
            if (zielZeile == startZeile - 1
                    && zielSpalte == startSpalte
                    && aufstellung[zielZeile][zielSpalte] == null) {

                return true;
            }
            if (aufstellung[5][startSpalte] == null
                    && startZeile == 6 && zielZeile == 4
                    && zielSpalte == startSpalte) { // doppelzug
                logik.setEnPassantZeile(zielZeile);
                logik.setEnPassantSpalte(zielSpalte);
                logik.setEnPassantMoeglich(true);
                System.out.println("en passant möglich");
                return true;
            }
            if (zielZeile == startZeile + 1
                    && Math.abs(zielSpalte - startSpalte) == 1) {

                Figur zielFigur = aufstellung[zielZeile][zielSpalte];

                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {

                    return true;
                }
            } // schräg schlagen
            if (zielZeile == startZeile - 1
                    && Math.abs(startSpalte - zielSpalte) == 1) {

                Figur zielFigur = aufstellung[zielZeile][zielSpalte];
                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {

                    return true;

                }
            }
        }

        // Schwarz
        if (farbe == SCHWARZ) {

            // eine Zeile nach vorne (aus schwarzer Sicht)
            if (zielZeile == startZeile + 1 && zielSpalte == startSpalte) {
                return true;
            }
            if (aufstellung[2][startSpalte] == null // Feld dazwischen Frei
                    && aufstellung[3][startSpalte] == null // Zielfeld frei
                    && startZeile == 1 && zielZeile == 3
                    && zielSpalte == startSpalte) { // doppelzug

                logik.setEnPassantZeile(zielZeile);
                logik.setEnPassantSpalte(zielSpalte);
                logik.setEnPassantMoeglich(true);
                return true;
            }
            if (zielZeile == startZeile + 1
                    && Math.abs(startSpalte - zielSpalte) == 1) {

                Figur zielFigur = aufstellung[zielZeile][zielSpalte];
                if (zielFigur != null
                        && zielFigur.getFarbe() != this.getFarbe()) {

                    return true;

                }
            }
        }
        // en passent (speicherung des letzten Zuges; wege)
        return false;
    }

    public boolean istAufLetzterZeile(int zeile) {

        // Weiß erreicht Zeile 0
        if (this.getFarbe() == 1 && zeile == 0) { // zeilen getauscht?
            return true;
        }

        // Schwarz erreicht Zeile 7
        if (this.getFarbe() == 0 && zeile == 7) {
            return true;
        }
        return false;

    }


}
