package Model;

import Model.Figuren.*;
// import Start.Settings;

import java.util.ArrayList;

public class Spielelogik {
    private Figur[][] aufstellung; // feld anlegen
    private Figur letzteGezogeneFigur;
    private boolean weissAmZug = true;

    ArrayList<Figur> geschlageneFigurenWeiss = new ArrayList<>();
    ArrayList<Figur> geschlageneFigurenSchwarz = new ArrayList<>();


    private boolean enPassantMoeglich = false;
    private int enPassantZeile;
    private int enPassantSpalte;

    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;
    private int stil = 1; // ggf. irgendwo in den einstellungen setzten

    public Spielelogik() {
        this.aufstellung = new Figur[8][8]; // ##
        initialisiereAufstellung();
    }


    private void initialisiereAufstellung() {
        // --- Schwarze Figuren (oben) ---
        aufstellung[0][0] = new Turm(Figur.SCHWARZ, stil);
        aufstellung[0][1] = new Springer(Figur.SCHWARZ, stil);
        aufstellung[0][2] = new Laeufer(Figur.SCHWARZ, stil );
        aufstellung[0][3] = new Dame(Figur.SCHWARZ, stil);
        aufstellung[0][4] = new Koenig(Figur.SCHWARZ, stil);
        aufstellung[0][5] = new Laeufer(Figur.SCHWARZ, stil);
        aufstellung[0][6] = new Springer(Figur.SCHWARZ, stil);
        aufstellung[0][7] = new Turm(Figur.SCHWARZ, stil);

        for (int i = 0; i < 8; i++) {
            aufstellung[1][i] = new Bauer(Figur.SCHWARZ, stil);
        }

        // --- Weiße Figuren (unten) ---
        for (int i = 0; i < 8; i++) {
            aufstellung[6][i] = new Bauer(Figur.WEISS, stil);
        }



        aufstellung[7][0] = new Turm(Figur.WEISS,stil);
        aufstellung[7][1] = new Springer(Figur.WEISS, stil);
        aufstellung[7][2] = new Laeufer(Figur.WEISS, stil);
        aufstellung[7][3] = new Dame(Figur.WEISS, stil);
        aufstellung[7][4] = new Koenig(Figur.WEISS, stil);
        aufstellung[7][5] = new Laeufer(Figur.WEISS, stil);
        aufstellung[7][6] = new Springer(Figur.WEISS, stil);
        aufstellung[7][7] = new Turm(Figur.WEISS,stil);
    }

    // Figur ziehen
    public boolean bewegeFigur(int startZeile, int startSpalte, int zielZeile, int zielSpalte) {

        Figur figur = aufstellung[startZeile][startSpalte];

        if (figur == null) { // keine Figur
            return false;
        }
        if (figur.getFarbe() == 1 && !weissAmZug || figur.getFarbe() == 0 && weissAmZug  ){// figur weiß, aber nicht am zug; figur schwarz weiß am zug
            System.out.println("Du bist nicht am Zug");
            return false;
        }

        boolean enPassant = istEnPassantZug(figur, startZeile, startSpalte, zielZeile, zielSpalte);

        if (!enPassant &&
                !figur.istGueltigerZug(startZeile, startSpalte, zielZeile, zielSpalte, aufstellung, this)) {
            return false;
        }

        if (enPassant) { // Gegnerischen Bauern entfernen
            aufstellung[startZeile][zielSpalte] = null;
        }

        Figur geschlageneFigur = aufstellung[zielZeile][zielSpalte]; // FIgur merken

        aufstellung[zielZeile][zielSpalte] = figur;
        aufstellung[startZeile][startSpalte] = null;

        if (istKoenigImSchach(figur.getFarbe())) {   // Zug rückgängig machen
            aufstellung[startZeile][startSpalte] = figur;
            aufstellung[zielZeile][zielSpalte] = geschlageneFigur;
            System.out.println("König steht im Schach!");
            return false;
        }

        System.out.println("Geschlagene Liste: " + geschlageneFigurenSchwarz + "weiß: " + geschlageneFigurenWeiss);

        // letzte Bewegte Figur merken
        enPassantMoeglich = false;
        if (figur instanceof Bauer) {

            if (Math.abs(startZeile - zielZeile) == 2) {
                enPassantMoeglich = true;
                enPassantZeile = zielZeile;
                enPassantSpalte = zielSpalte;
            }
        }
        letzteGezogeneFigur = figur;

        // Zug ausführen
        setLetzteStartPosition(startZeile, startSpalte);
        setLetzteZielPosition(zielZeile, zielSpalte);

        pruefeBauernumwandlung(aufstellung, zielZeile, zielSpalte);
        if (geschlageneFigur != null) { // liste hinzufügen
            if (geschlageneFigur.getFarbe() == 1) {
                geschlageneFigurenWeiss.add(geschlageneFigur);
            }
            if (geschlageneFigur.getFarbe() == 0) {
                geschlageneFigurenSchwarz.add(geschlageneFigur);
            }
        }
        System.out.println("Geschlagen jetzt: " + geschlageneFigur);
        System.out.println("Weiss Liste: " + geschlageneFigurenWeiss.size());
        System.out.println("Schwarz Liste: " + geschlageneFigurenSchwarz.size());
        weissAmZug = !weissAmZug; // Farbwechsel
        System.out.println("weissAmZug" + weissAmZug);
        return true;
    }

    public void resetMarkierung() {
        letzteStartZeile = -1;
        letzteStartSpalte = -1;
        letzteZielZeile = -1;
        letzteZielSpalte = -1;
    }


    //     BAUERNUMWANDLUNG
    public void pruefeBauernumwandlung(Figur[][] aufstellung, int zeile, int spalte) {

        Figur figur = aufstellung[zeile][spalte];

        if (!(figur instanceof Bauer)) { // ist es ein Bauer
            return;
        }

        Bauer bauer = (Bauer) figur;

        if (!bauer.istAufLetzterZeile(zeile)) {
            return;
        }
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Bauernumwandlung");
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        String[] optionen = {"Dame", "Turm", "Läufer", "Springer"};
        javax.swing.JComboBox<String> comboBox = new javax.swing.JComboBox<>(optionen);

        javax.swing.JButton okButton = new javax.swing.JButton("OK");

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.add(comboBox);
        panel.add(okButton);

        dialog.add(panel);

        okButton.addActionListener(e -> {

            String auswahl = (String) comboBox.getSelectedItem();

            switch (auswahl) {

                case "Dame":
                    aufstellung[zeile][spalte] = new Dame(bauer.getFarbe(), stil);
                    break;

                case "Turm":
                    aufstellung[zeile][spalte] = new Turm(bauer.getFarbe(), stil);
                    break;

                case "Läufer":
                    aufstellung[zeile][spalte] = new Laeufer(bauer.getFarbe(),stil);
                    break;

                case "Springer":
                    aufstellung[zeile][spalte] = new Springer(bauer.getFarbe(),stil);
                    break;
            }
            dialog.dispose(); // Fenster schließen
        });
        dialog.setVisible(true);
    }


    //  en Passent
    private boolean istEnPassantZug(Figur figur, int startZeile, int startSpalte, int zielZeile,
                                    int zielSpalte) {

        if (!(figur instanceof Bauer)) { // ist es ein Bauer?
            return false;
        }
        if (!enPassantMoeglich) { // nur genau nach dem Zug
            return false;
        }
        Bauer bauer = (Bauer) figur;
        int richtung;
        if (bauer.getFarbe() == Figur.WEISS) {
            richtung = -1;
        } else {
            richtung = 1;
        }
        return Math.abs(zielSpalte - startSpalte) == 1 // einen nach Vorne
                && zielZeile == startZeile + richtung  // nach rechts - links gegangen
                && aufstellung[zielZeile][zielSpalte] == null // leeres Feld
                && enPassantZeile == startZeile
                && enPassantSpalte == zielSpalte;
    }


    //       SCHACH PRÜFEN!!
    public int[] findeKoenig(int farbe) {

        for (int zeile = 0; zeile < 8; zeile++) {
            for (int spalte = 0; spalte < 8; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur instanceof Koenig &&
                        figur.getFarbe() == farbe) {

                    return new int[]{zeile, spalte};
                }
            }
        }

        return null;
    }

    public boolean istKoenigImSchach(int farbe) {

        int[] koenigPos = findeKoenig(farbe);

        if (koenigPos == null) {
            return false;
        }

        int koenigZeile = koenigPos[0];
        int koenigSpalte = koenigPos[1];

        for (int zeile = 0; zeile < 8; zeile++) {
            for (int spalte = 0; spalte < 8; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur == null) {
                    continue;
                }

                if (figur.getFarbe() == farbe) {
                    continue;
                }

                if (figur.istGueltigerZug(zeile, spalte, koenigZeile, koenigSpalte, aufstellung, this)) {
                    return true; // landet die Figur mit ihrem Bewegmuster auf dem Feld des Königs?
                }
            }
        }
        return false;
    }

    // Getter  &&  Setter
    public Figur getLetzteGezogeneFigur() {
        return letzteGezogeneFigur;
    }


    public void setEnPassantZeile(int enPassantZeile) {
        this.enPassantZeile = enPassantZeile;
    }


    public void setEnPassantSpalte(int enPassantSpalte) {
        this.enPassantSpalte = enPassantSpalte;
    }


    public void setEnPassantMoeglich(boolean enPassantMoeglich) {
        this.enPassantMoeglich = enPassantMoeglich;
    }

    public int getLetzteStartZeile() {
        return letzteStartZeile;
    }

    public int getLetzteStartSpalte() {
        return letzteStartSpalte;
    }

    public int getLetzteZielSpalte() {
        return letzteZielSpalte;
    }

    public int getLetzteZielZeile() {
        return letzteZielZeile;
    }

    public Figur[][] getAufstellung() {
        return aufstellung;
    }


    public void setzeFigur(Figur figur, int zeile, int spalte) {
        aufstellung[zeile][spalte] = figur;
    }


    public void setLetzteStartPosition(int zeile, int spalte) {
        letzteStartZeile = zeile;
        letzteStartSpalte = spalte;
    }

    public void setLetzteZielPosition(int zeile, int spalte) {
        letzteZielZeile = zeile;
        letzteZielSpalte = spalte;
    }

         public ArrayList<Figur> getGeschlageneFigurenSchwarz() {
        return geschlageneFigurenSchwarz;
    }

     public ArrayList<Figur> getGeschlageneFigurenWeiss() {
        return geschlageneFigurenWeiss;
     }

    public int getStil() {
        return stil;
    }

    public void setStil(int stil) {
        this.stil = stil;
    }

    public boolean isWeissAmZug() {
        return weissAmZug;
    }
}
