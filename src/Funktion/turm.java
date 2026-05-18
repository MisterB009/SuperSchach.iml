package Funktion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class turm {
    public static final int SCHWARZ = 0;
    public static final int WEIß = 1;
    private int farbe;

    private int x;
    private int y;

    private Image zeichnen;

    public turm(int farbe, int x, int y) {
        this.setFarbe(farbe);
        this.x = x;
        this.y = y;
        if (farbe == 0){ // schwarz
            try {
                zeichnen = ImageIO.read(new File("img/RookB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1){ // weiß
            try {
                zeichnen = ImageIO.read(new File("img/RookW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Funktion - visuell wie darf er gezogen werden?
    }
    public Image getZeichnen(){
        return zeichnen;
    }


    public void setFarbe(int farbe) {
        if (farbe == SCHWARZ || farbe == WEIß) {
            this.farbe = farbe;
        } else {
            System.out.println("Es kann nur die Farbe 1 oder 2 gewählt werden.");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
