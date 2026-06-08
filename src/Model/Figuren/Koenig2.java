package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Koenig2 extends Figur {
    public Koenig2(int farbe) {
        super(farbe);


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
    }

    @Override
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte,Figur[][] aufstellung) {
        int horizontaleStrecke = Math.abs(startZeile - zielZeile);
        int vertikaleStrecke = Math.abs(startSpalte - zielSpalte);

        if(horizontaleStrecke == 1 || vertikaleStrecke == 1 ||
                (horizontaleStrecke == 1 &&  vertikaleStrecke  == 1) ){

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
        System.out.println("Könige gehen so nicht");
        return false;
    }
}

//rochade - bewegt schalter + turm

// ins schach gestellt
