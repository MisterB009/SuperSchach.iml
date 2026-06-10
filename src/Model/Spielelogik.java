package Model;

import Model.Figuren.*;

public class Spielelogik {
    private Figur[][] aufstellung; // feld anlegen
    private Figur letzteGezogeneFigur;

    private boolean enPassantMoeglich = false;
    private int enPassantZeile;
    private int enPassantSpalte;

    private int letzteStartZeile = -1;
    private int letzteStartSpalte = -1;
    private int letzteZielZeile = -1;
    private int letzteZielSpalte = -1;

    public Spielelogik() {
        this.aufstellung = new Figur[8][8]; // ##
        initialisiereAufstellung();
    }

    private void initialisiereAufstellung() {
        // --- Schwarze Figuren (oben) ---
        aufstellung[0][0] = new Turm2(Figur.SCHWARZ);
        aufstellung[0][1] = new Springer2(Figur.SCHWARZ);
        aufstellung[0][2] = new Laeufer2(Figur.SCHWARZ);
        aufstellung[0][3] = new Dame2(Figur.SCHWARZ);
        aufstellung[0][4] = new Koenig2(Figur.SCHWARZ);
        aufstellung[0][5] = new Laeufer2(Figur.SCHWARZ);
        aufstellung[0][6] = new Springer2(Figur.SCHWARZ);
        aufstellung[0][7] = new Turm2(Figur.SCHWARZ);

        for (int i = 0; i < 8; i++) {
            aufstellung[1][i] = new Bauer2(Figur.SCHWARZ);
        }

        // --- Weiße Figuren (unten) ---
        for (int i = 0; i < 8; i++) {
            aufstellung[6][i] = new Bauer2(Figur.WEISS);
        }

        aufstellung[7][0] = new Turm2(Figur.WEISS);
        aufstellung[7][1] = new Springer2(Figur.WEISS);
        aufstellung[7][2] = new Laeufer2(Figur.WEISS);
        aufstellung[7][3] = new Dame2(Figur.WEISS);
        aufstellung[7][4] = new Koenig2(Figur.WEISS);
        aufstellung[7][5] = new Laeufer2(Figur.WEISS);
        aufstellung[7][6] = new Springer2(Figur.WEISS);
        aufstellung[7][7] = new Turm2(Figur.WEISS);
    }



    // abwechseldes Ziehen (Figur 0/ 1)

    public boolean bewegeFigur(int startZeile, int startSpalte, int zielZeile, int zielSpalte) {

        Figur figur = aufstellung[startZeile][startSpalte];

        if (figur == null) { // keine Figur
            return false;
        }

        boolean enPassant = istEnPassantZug(
                figur,
                startZeile,
                startSpalte,
                zielZeile,
                zielSpalte);

        if (!enPassant &&
                !figur.istGueltigerZug(startZeile, startSpalte, zielZeile, zielSpalte, aufstellung, this)) {
            return false;
        }

        if (enPassant) { // Gegnerischen Bauern entfernen
            aufstellung[startZeile][zielSpalte] = null;
        } // enpassentzeiel / enpassentspalte

        aufstellung[zielZeile][zielSpalte] = figur;
        aufstellung[startZeile][startSpalte] = null;

        // letzte Bewegte Figur merken
        enPassantMoeglich = false;
        if (figur instanceof Bauer2) {

            if (Math.abs(startZeile - zielZeile) == 2) {
                enPassantMoeglich = true;
                enPassantZeile = zielZeile;
                enPassantSpalte = zielSpalte;
            }
        }
        letzteGezogeneFigur = figur;
        System.out.println("letzte Bewegung: " +figur);

        // Zug ausführen
        setLetzteStartPosition(startZeile, startSpalte);
        setLetzteZielPosition(zielZeile, zielSpalte);
        // bauernumwandlung prüfen
        pruefeBauernumwandlung(aufstellung, zielZeile, zielSpalte);

        return true;
    }

    public void resetMarkierung() {
        letzteStartZeile = -1;
        letzteStartSpalte = -1;
        letzteZielZeile = -1;
        letzteZielSpalte = -1;
    }


    // BAUERNUMWANDLUNG
    public void pruefeBauernumwandlung (Figur[][]aufstellung,int zeile, int spalte){

        Figur figur = aufstellung[zeile][spalte];

        System.out.println("bauernumwandlung");
        if (!(figur instanceof Bauer2)) { // ist es ein Bauer
            System.out.println("Bauerntest");
            return;
        }

        Bauer2 bauer = (Bauer2) figur;
        System.out.println("Bauerntest1111");

        if (!bauer.istAufLetzterZeile(zeile)) {
            System.out.println("kein bauer auf letzter Zeile");
            return;
        }

        System.out.println("Bauerntest222");

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
                    aufstellung[zeile][spalte] = new Dame2(bauer.getFarbe());
                    break;

                case "Turm":
                    aufstellung[zeile][spalte] = new Turm2(bauer.getFarbe());
                    break;

                case "Läufer":
                    aufstellung[zeile][spalte] = new Laeufer2(bauer.getFarbe());
                    break;

                case "Springer":
                    aufstellung[zeile][spalte] = new Springer2(bauer.getFarbe());
                    break;
            }

            dialog.dispose(); // Fenster schließen
        });

        dialog.setVisible(true);
    }


    // en Passent
    private boolean istEnPassantZug(
            Figur figur,
            int startZeile,
            int startSpalte,
            int zielZeile,
            int zielSpalte) {

        if (!(figur instanceof Bauer2)) { // kein Bauer
            return false;
        }

        if (!enPassantMoeglich) { // nur genau nach dem Zug
            return false;
        }

        Bauer2 bauer = (Bauer2) figur;

        int richtung;

        if (bauer.getFarbe() == Figur.WEISS) {
            richtung = -1;
        } else {
            richtung = 1;
        }

        // ''''''''
        return Math.abs(zielSpalte - startSpalte) == 1
                && zielZeile == startZeile + richtung
                && aufstellung[zielZeile][zielSpalte] == null
                && enPassantZeile == startZeile
                && enPassantSpalte == zielSpalte;
    }

//    // aufstellung ansehen
//    public void printBrett() {
//        for (int zeile = 0; zeile < 8; zeile++) {
//
//            for (int spalte = 0; spalte < 8; spalte++) {
//
//                Figur figur = aufstellung[zeile][spalte];
//
//                if (figur == null) {
//                    System.out.print(".. ");
//                } else {
//
//                    String symbol = "";
//
//                    if (figur instanceof Turm2) symbol = "T";
//                    else if (figur instanceof Springer2) symbol = "S";
//                    else if (figur instanceof Laeufer2) symbol = "L";
//                    else if (figur instanceof Dame2) symbol = "D";
//                    else if (figur instanceof Koenig2) symbol = "K";
//                    else if (figur instanceof Bauer2) symbol = "B";
//
//                    if (figur.getFarbe() == 1) {
//                        System.out.print("W" + symbol + " ");
//                    } else {
//                        System.out.print("S" + symbol + " ");
//                    }
//                }
//            }
//
//            System.out.println();
//        }
//    }

    // Getter  &&  Setter
    public Figur getLetzteGezogeneFigur() {
        return letzteGezogeneFigur;
    }

    public int getEnPassantZeile() {
        return enPassantZeile;
    }

    public void setEnPassantZeile(int enPassantZeile) {
        this.enPassantZeile = enPassantZeile;
    }

    public int getEnPassantSpalte() {
        return enPassantSpalte;
    }

    public void setEnPassantSpalte(int enPassantSpalte) {
        this.enPassantSpalte = enPassantSpalte;
    }

    public boolean isEnPassantMoeglich() {
        return enPassantMoeglich;
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
    // Getter & Setter
    public Figur getFigur(int zeile, int spalte) {
        if (zeile < 1 || zeile > 8 || spalte < 1 || spalte > 8) {
            return null;
        }
        return aufstellung[zeile][spalte];
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
}
