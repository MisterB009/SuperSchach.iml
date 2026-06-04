package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Laeufer2 extends Figur {
    public Laeufer2(int farbe) {
        super(farbe);

        if (farbe == 0){ // schwarz
            try {
                bild = ImageIO.read(new File("img/BishopB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1){ // weiß
            try {
                bild = ImageIO.read(new File("img/BishopW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean istGueltigerZug( int startZeile, int startSpalte, int zielZeile, int zielSpalte,Figur[][] felder) {
        // gleiches Feld
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            System.out.println("gleiches Feld gewählt");
            return false;
        }
        int horizontaleStrecke = Math.abs(startZeile - zielZeile);
        int vertikaleStrecke = Math.abs(startSpalte - zielSpalte);
        System.out.println("Läufer test");
        System.out.println("horizont" +horizontaleStrecke);
        System.out.println("vertikal" + vertikaleStrecke);
        if(vertikaleStrecke != 0){
            if((horizontaleStrecke/vertikaleStrecke == 1)){
                System.out.println("alles supi -Läufer");
                return true;
            }
        }
        System.out.println("falsche Bewegung");
        return false;
    }
}
