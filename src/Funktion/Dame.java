package Funktion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Dame {
    public static final int SCHWARZ = 0;
    public static final int WEIß = 1;
    private int farbe;
    private final int korrekturx = 0;
    private final int korrektury = 0;

    private int x;
    private int y;

    private Image zeichnen;
    public Dame(int farbe, int x, int y){
        this.setFarbe(farbe);
        this.x = x;
        this.y = y;
        if (farbe == 0){ // schwarz
            try {
                zeichnen = ImageIO.read(new File("img/QueenB.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (farbe == 1){ // weiß
            try {
                zeichnen = ImageIO.read(new File("img/QueenW.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getFarbe() {
        return farbe;
    }

    public void setFarbe(int farbe) {
        if (farbe == SCHWARZ || farbe == WEIß) {
            this.farbe = farbe;
        } else {
            System.out.println("Es kann nur die Farbe 1 oder 2 gewählt werden.");
        }
    }

    public int getKorrekturx() {
        return korrekturx;
    }

    public int getKorrektury() {
        return korrektury;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getZeichnen() {
        return zeichnen;
    }

    public void setZeichnen(Image zeichnen) {
        this.zeichnen = zeichnen;
    }
}
