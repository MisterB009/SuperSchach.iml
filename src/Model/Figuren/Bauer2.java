package Model.Figuren;

import Model.Figur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Bauer2 extends Figur {

    public Bauer2(int farbe) {
        super(farbe);
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


    @Override
    public boolean istGueltigerZug(int startZeile, int startSpalte, int zielZeile, int zielSpalte, Figur[][] aufstellung) {
        // gleiche Position -> kein Zug
        if (zielZeile == startZeile && zielSpalte == startSpalte) {
            System.out.println("gleiches Feld gewählt");
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
                System.out.println("legaler Zug");
                return true;
            }
            if (aufstellung[2][startSpalte] == null
                    && startZeile == 1 && zielZeile == 3
                    && zielSpalte == startSpalte) { // doppelzug

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

        System.out.println("illegaler Zug: Bauer");
        System.out.println("Zielzeile: " + zielSpalte);
        System.out.println("Zielzeile: " + zielZeile);
        return false;
    }

        public boolean istAufLetzterZeile ( int zeile){

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


//        public void pruefeBauernumwandlung (Figur[][]aufstellung,int zeile, int spalte){
//
//            Figur figur = aufstellung[zeile][spalte];
//
//            System.out.println("bauernumwandlung");
//            if (!(figur instanceof Bauer2)) { // ist es ein Bauer
//                System.out.println("Bauerntest");
//                return;
//            }
//
//            Bauer2 bauer = (Bauer2) figur;
//            System.out.println("Bauerntest1111");
//
//            if (!bauer.istAufLetzterZeile(zeile)) {
//                System.out.println("kein bauer auf letzter Zeile");
//                return;
//            }
//
//            System.out.println("Bauerntest222");
//
//            javax.swing.JDialog dialog = new javax.swing.JDialog();
//            dialog.setTitle("Bauernumwandlung");
//            dialog.setSize(300, 150);
//            dialog.setLocationRelativeTo(null);
//            dialog.setModal(true);
//
//            String[] optionen = {"Dame", "Turm", "Läufer", "Springer"};
//            javax.swing.JComboBox<String> comboBox = new javax.swing.JComboBox<>(optionen);
//
//            javax.swing.JButton okButton = new javax.swing.JButton("OK");
//
//            javax.swing.JPanel panel = new javax.swing.JPanel();
//            panel.add(comboBox);
//            panel.add(okButton);
//
//            dialog.add(panel);
//
//            okButton.addActionListener(e -> {
//
//                String auswahl = (String) comboBox.getSelectedItem();
//
//                switch (auswahl) {
//
//                    case "Dame":
//                        aufstellung[zeile][spalte] = new Dame2(bauer.getFarbe());
//                        break;
//
//                    case "Turm":
//                        aufstellung[zeile][spalte] = new Turm2(bauer.getFarbe());
//                        break;
//
//                    case "Läufer":
//                        aufstellung[zeile][spalte] = new Laeufer2(bauer.getFarbe());
//                        break;
//
//                    case "Springer":
//                        aufstellung[zeile][spalte] = new Springer2(bauer.getFarbe());
//                        break;
//                }
//
//                dialog.dispose(); // Fenster schließen
//            });
//
//            dialog.setVisible(true);
//        }


    }

