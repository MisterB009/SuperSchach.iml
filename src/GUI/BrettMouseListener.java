package GUI;

import Model.Figur;
import Model.Spielelogik;
import Multiplayer.MPBrett;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BrettMouseListener extends MouseAdapter {
    private Brett brett;
    private MPBrett mpBrett;
    private Spielelogik logik;

    private Figur ausgewaehlteFigur = null;

    private int startZeile;
    private int startSpalte;

    private GeschlagenePanel panel;


    public BrettMouseListener(Brett brett, Spielelogik logik, GeschlagenePanel panel) {
        this.brett = brett;
        this.logik = logik;
        this.panel = panel;
    }
    public BrettMouseListener(MPBrett mpBrett, Spielelogik logik, GeschlagenePanel panel) {
        this.mpBrett = mpBrett;
        this.logik = logik;
        this.panel = panel;
    }


    public void setLogik(Spielelogik logik) {
        this.logik = logik;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

//        int zeile =  (e.getY()) / 80 -1 ;
//        int spalte = e.getX() / 80 -1 ;
//
//
//        // außerhalb
//        if (spalte < 0 || spalte > 8 || zeile < 0 || zeile > 8) {
//            return;
//        }
        int zeile = (e.getY() - 80) / 80;
        int spalte = (e.getX() - 80) / 80;

        // Outside board bounds
        if (spalte < 0 || spalte > 7 || zeile < 0 || zeile > 7) {
            return;
        }

        Figur[][] felder = logik.getAufstellung();

        // ===== ERSTER KLICK ===== Figur wählen
        if (ausgewaehlteFigur == null) {

            Figur figur = felder[zeile][spalte];
            if (figur != null) {

                ausgewaehlteFigur = figur;

                startZeile = zeile;
                startSpalte = spalte;
                System.out.println(
                        "Klick: Zeile=" + zeile
                                + " Spalte=" + spalte
                );

                logik.setLetzteStartPosition(zeile, spalte);

                if (brett != null) {
                    brett.repaint();
                } else if (mpBrett != null) {
                    mpBrett.repaint();
                }
            }
        }

        // ===== ZWEITER KLICK ===== bewegen
        else {
            // gleiche Position wieder anklicken = Reset
            if (zeile == startZeile && spalte == startSpalte) {
                ausgewaehlteFigur = null;
                logik.resetMarkierung();
                if (brett != null) {
                    brett.repaint();
                } else if (mpBrett != null) {
                    mpBrett.repaint();
                }
                return;
            }

            if (mpBrett != null && !mpBrett.canMove()) {
                ausgewaehlteFigur = null;
                logik.resetMarkierung();
                if (mpBrett != null) {
                    mpBrett.repaint();
                }
                JOptionPane.showMessageDialog(null, "Es ist nicht dein Zug!");
                return;
            }


            // legaler Zug
            boolean erfolgreich = logik.bewegeFigur(startZeile,startSpalte,zeile, spalte );
            if (!erfolgreich) {
                ausgewaehlteFigur = null;
                logik.resetMarkierung();
                if (brett != null) {
                    brett.repaint();
                } else if (mpBrett != null) {
                    mpBrett.repaint();
                }
                return;
            }
            ausgewaehlteFigur = null;

            if (mpBrett != null && mpBrett.getConnection() != null) {
                mpBrett.getConnection().sendMove(startZeile, startSpalte, zeile, spalte);
            }

            if (brett != null) {
                brett.repaint();
            } else if (mpBrett != null) {
                mpBrett.repaint();
            }

            if(panel != null){
                System.out.println("Update aufgerufen#######################");
                panel.aktualisieren(logik);
            }

        }
    }


    public Figur getAusgewaehlteFigur() {
        return ausgewaehlteFigur;
    }

    public int getStartSpalte() {
        return startSpalte;
    }

    public int getStartZeile() {
        return startZeile;
    }

}
