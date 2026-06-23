package Multiplayer;

import java.io.Serializable;

public class Move implements Serializable {
    public int startZeile, startSpalte, zielZeile, zielSpalte;
    public static final long serialVersionUID = 1L;

    public Move(int startZeile, int startSpalte, int zielZeile, int zielSpalte) {
        this.startZeile = startZeile;
        this.startSpalte = startSpalte;
        this.zielZeile = zielZeile;
        this.zielSpalte = zielSpalte;
    }
}
