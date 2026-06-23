package Model.Figuren;

import Model.Figur;
import Model.Spielelogik;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Koenig extends Figur {
    public boolean koenigBewegt = false;

    public Koenig(int farbe, int stil) {
        super(farbe, stil);

        if (stil == 1) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/KingB.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/KingW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if (stil == 2) {
            if (farbe == 0){ // schwarz
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/KoenigS.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (farbe == 1){ // weiß
                try {
                    bild = ImageIO.read(new File("img/SuperFiguren/KoenigW.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte,Figur[][] aufstellung, Spielelogik logik) {
        int horizontaleStrecke = Math.abs(startZeile - zielZeile);
        int vertikaleStrecke = Math.abs(startSpalte - zielSpalte);

        //Rochade
        if (koenigBewegt == false
                && startZeile == zielZeile
                && vertikaleStrecke == 2) {

            // kurze Rochade (nach rechts)
            if (zielSpalte > startSpalte) {

                Figur figur = aufstellung[startZeile][7];

                if (figur instanceof Turm) {

                    Turm turm = (Turm) figur;

                    if (turm.isTurmBewegt() == false
                            && aufstellung[startZeile][5] == null
                            && aufstellung[startZeile][6] == null) {
                        turm.kurzeRochade(aufstellung, startZeile);
//                        System.out.println("Kurze Rochade erlaubt");
                        return true;
                    }
                }
            }

            // lange Rochade (nach links)
            if (zielSpalte < startSpalte) {

                Figur figur = aufstellung[startZeile][0];

                if (figur instanceof Turm) {

                    Turm turm = (Turm) figur;

                    if (turm.isTurmBewegt() == false
                            && aufstellung[startZeile][1] == null
                            && aufstellung[startZeile][2] == null
                            && aufstellung[startZeile][3] == null) {
                        turm.langeRochade(aufstellung, startZeile);
//                        System.out.println("Lange Rochade erlaubt");
                        return true;
                    }
                }
            }
        }


        if(horizontaleStrecke == 1 || vertikaleStrecke == 1 ||
                (horizontaleStrecke == 1 &&  vertikaleStrecke  == 1) ){

            Figur figurImWeg = aufstellung[zielZeile][zielSpalte];
            if (figurImWeg != null) {
                if (figurImWeg.getFarbe() == this.getFarbe()) {
//                    System.out.println("Eigene Figur");
                    return false;
                }
                if (figurImWeg.getFarbe() != this.getFarbe()) {
//                    System.out.println("Figur schlagen.");
                    koenigBewegt =true;
                    return true;
                }
            }

            koenigBewegt =true;
            return true;
        }
        return false;
    }
}
