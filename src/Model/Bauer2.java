package Model;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bauer2 extends Figur {

    public Bauer2(int farbe, int zeile, int spalte) {
        super(farbe, zeile, spalte);
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
    }

//    @Override
//    public boolean istGueltigerZug(){
//        return true;
//    }


}
