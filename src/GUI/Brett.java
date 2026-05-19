package GUI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Brett extends JPanel implements MouseListener {
    private int hoverRow = -1;
    private int hoverCol = -1;
    private int Yfarbeanders = 0;//Schalter falls die Farbe des Feldes schon geändert wurde
    private ArrayList<Integer> positionenFigurenX;
    private ArrayList<Integer> positionenFigurenY;
    private int HilfsX = -1;
    private int HilfsY = -1;

    // Brett aus Figuren
    private Figur[][] aufstellung; // Variable erzeugen

    //Weiße Figuren:
    private Turm2 rookwl2;
    private Turm rookwl;
    private Turm rookwr;
    private Springer knightwl;
    private Springer knightwr;
    private Laeufer bishopwl;
    private Laeufer bishopwr;
    private Dame queenw;
    private Koenig kingw;
    private Bauer pawnw;
    private ArrayList<Bauer>pawnsw;

    //Schwarze Figuren:
    private Turm rookbl;
    private Turm rookbr;
    private Springer knightbl;
    private Springer knightbr;
    private Laeufer bishopbl;
    private Laeufer bishopbr;
    private Dame queenb;
    private Koenig kingb;
    private Bauer pawnb;
    private ArrayList<Bauer>pawnsb;


    // A: Koordinatensystem Methode - Aufstellung Methode
    public Brett(){ // attribute befüülen mit eigenschaften
        addMouseListener(this);

        // übersichtlicher:
        // Türme
        aufstellung = new Figur[9][9]; // 8 reihen & Spalten erzeugen
        aufstellung[1][1] = new Turm2(0,1,1);
        aufstellung[1][2] = new Turm2(0,1,2);
        aufstellung[1][3] = new Turm2(0,1,3);
        aufstellung[1][4] = new Turm2(0,1,4);
        aufstellung[1][5] = new Turm2(0,1,5);
        aufstellung[1][6] = new Turm2(0,1,6);
        aufstellung[1][7] = new Turm2(0,1,7);
        aufstellung[1][8] = new Turm2(0,1,8);

        aufstellung[2][1] = new Turm2(0,2,1);
        aufstellung[2][2] = new Turm2(0,2,2);
        aufstellung[2][3] = new Turm2(0,2,3);
        aufstellung[2][4] = new Turm2(0,2,4);
        aufstellung[2][5] = new Turm2(0,2,5);
        aufstellung[2][6] = new Turm2(0,2,6);
        aufstellung[2][7] = new Turm2(0,2,7);
        aufstellung[2][8] = new Turm2(0,2,8);


        aufstellung[7][1] = new Turm2(0,7,1);
        aufstellung[7][2] = new Turm2(0,7,2);
        aufstellung[7][3] = new Turm2(0,7,3);
        aufstellung[7][4] = new Turm2(0,7,4);
        aufstellung[7][5] = new Turm2(0,7,5);
        aufstellung[7][6] = new Turm2(0,7,6);
        aufstellung[7][7] = new Turm2(0,7,7);
        aufstellung[7][8] = new Turm2(0,7,8);

        aufstellung[8][1] = new Turm2(0,8,1);
        aufstellung[8][2] = new Turm2(0,8,2);
        aufstellung[8][3] = new Turm2(0,8,3);
        aufstellung[8][4] = new Turm2(0,8,4);
        aufstellung[8][5] = new Turm2(0,8,5);
        aufstellung[8][6] = new Turm2(0,8,6);
        aufstellung[8][7] = new Turm2(0,8,7);
        aufstellung[8][8] = new Turm2(0,8,8);


        //Springer



//        positionenFigurenX = new ArrayList<>();
//        positionenFigurenY = new ArrayList<>();
////        rookwl = new Turm(1, 80, 8*80);// farbe und Position
////        positionenFigurenX.add(rookwl.getX());
////        positionenFigurenY.add(rookwl.getY());
//        rookwr = new Turm(1, 8*80, 8*80);//Felder = Zeile/ Spalte * Feldgröße
//        positionenFigurenX.add(rookwr.getX());
//        positionenFigurenY.add(rookwr.getY());
//        rookbl = new Turm(0, 80, 80);
//        positionenFigurenX.add(rookbl.getX());
//        positionenFigurenY.add(rookbl.getY());
//        rookbr = new Turm(0, 8*80, 80);
//        positionenFigurenX.add(rookbr.getX());
//        positionenFigurenY.add(rookbr.getY());

//        knightwl = new Springer(1, 2*80, 8*80);
//        positionenFigurenX.add(knightwl.getX());
//        positionenFigurenY.add(knightwl.getY());
//        knightwr = new Springer(1, 7*80, 8*80);
//        positionenFigurenX.add(knightwr.getX());
//        positionenFigurenY.add(knightwr.getY());
//        knightbl = new Springer(0, 2*80, 80);
//        positionenFigurenX.add(knightbl.getX());
//        positionenFigurenY.add(knightbl.getY());
//        knightbr = new Springer(0, 7*80, 80);
//        positionenFigurenX.add(knightbr.getX());
//        positionenFigurenY.add(knightbr.getY());
//        bishopwl = new Laeufer(1, 3*80, 8*80);
//        positionenFigurenX.add(bishopwl.getX());
//        positionenFigurenY.add(bishopwl.getY());
//        bishopwr = new Laeufer(1, 6*80, 8*80);
//        positionenFigurenX.add(bishopwr.getX());
//        positionenFigurenY.add(bishopwr.getY());
//        bishopbl = new Laeufer(0, 3*80, 80);
//        positionenFigurenX.add(bishopbl.getX());
//        positionenFigurenY.add(bishopbl.getY());
//        bishopbr = new Laeufer(0, 6*80, 80);
//        positionenFigurenX.add(bishopbr.getX());
//        positionenFigurenY.add(bishopbr.getY());
//        queenw = new Dame(1, 4*80, 8*80);
//        positionenFigurenX.add(queenw.getX());
//        positionenFigurenY.add(queenw.getY());
//        queenb = new Dame(0, 4*80, 80);
//        positionenFigurenX.add(queenb.getX());
//        positionenFigurenY.add(queenb.getY());
//        kingw = new Koenig(1, 5*80, 8*80);
//        positionenFigurenX.add(kingw.getX());
//        positionenFigurenY.add(kingw.getY());
//        kingb = new Koenig(0, 5*80, 80);
//        positionenFigurenX.add(kingb.getX());
//        positionenFigurenY.add(kingb.getY());
//        pawnsw = new ArrayList<>();
//        pawnsb = new ArrayList<>();
//        int px = 80;
//        for (int i = 0; i < 8; i++) {
//            pawnw = new Bauer(1, px, 7*80);
//            pawnsw.add(pawnw);
//            pawnb = new Bauer(0, px, 2*80);
//            pawnsb.add(pawnb);
//            px = px + 80;
//        }
//        for (int i = 0; i < 8; i++) {
//            positionenFigurenX.add(pawnsw.get(i).getX());
//            positionenFigurenX.add(pawnsb.get(i).getX());
//            positionenFigurenY.add(pawnsw.get(i).getY());
//            positionenFigurenY.add(pawnsb.get(i).getY());
//        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color hell = new Color(222, 227, 230);
        Color dunkel = new Color(140, 162, 173);
        Color hover = new Color(200, 100, 100);

        g.setColor(Color.darkGray); // Hintergrund
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Color color = hell;

        int x2 = 80;
        for (int i = 0; i < 8; i++) { // Zeilen
            int y2;
            y2 = 80;
            for (int j = 0; j < 8; j++) {// Spalte
                if (j == 0 && i > 0) { // Color immer abwechseln
                    if (color.equals(hell)) {
                        color = dunkel;
                    } else {
                        color = hell;
                    }
                }

                // HOVER hinzufügen
                if (i == hoverRow && j == hoverCol) {
                    g.setColor(hover);
                } else {
                    g.setColor(color);
                }

                g.fillRect(y2, x2, 80, 80); // füllen
                y2 = y2 + 80; // alle weiteren Reihen

                if (color.equals(hell)) {
                    color = dunkel;
                } else {
                    color = hell;
                }
            }
            x2 = x2 + 80;
        }
        int xstart = 80;
        int ystart = 80;

        //Startaufstellung
//        this.zeichneStartaufstellung(g);

        // Seitenränder
        int charwertbuchstabe = 65;
        int charwertzahl = 56;
        g.setColor(Color.white);
        for (int i = 0; i < 8; i++) {
            char asciibuchstabe = (char) charwertbuchstabe;
            String text = String.valueOf(asciibuchstabe);
            Font koordinaten = new Font("ARIAL", Font.BOLD, 30);
            FontMetrics metrics = g.getFontMetrics(koordinaten);
            int breite = metrics.stringWidth(text);
            int sx = xstart + 40 - (breite/2);
            g.setFont(new Font("ARIAL", Font.BOLD, 30));
            g.drawString(text, sx, 760);
            xstart = xstart + 80;
            charwertbuchstabe++;

            char asciizahl = (char) charwertzahl;
            String zahl = String.valueOf(asciizahl);
            int hoehe = metrics.getAscent();
            int sy = ystart + 40 + (hoehe/2);
            g.drawString(zahl, 40, sy);
            ystart = ystart + 80;
            charwertzahl--;
        }

        for (int zeile = 0; zeile < 9; zeile++) { // Zeile auf 0 ändern

            for (int spalte = 0; spalte < 9; spalte++) {

                Figur figur = aufstellung[zeile][spalte];

                if (figur != null) {

                    int VarX = spalte * 80;
                    int VarY = (9 - zeile) * 80;

                    g.drawImage(figur.getBild(), VarX, VarY, 80, 80, this);
                }
            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (Yfarbeanders >= 1&&e.getX() >= HilfsX&&e.getX() < HilfsX+80&& // wurde auf das Bild geklickt & / wurde auf ein anderes Feld gegklickt?
                    e.getY() >= HilfsY&&e.getY() < HilfsY+80){
            hoverCol = -1;
            hoverRow = -1;
            HilfsX = -1;
            HilfsY = -1;
            Yfarbeanders = 0;
            repaint();
        }else {
            int startX = 80;
            int startY = 80;
            int feldGroesse = 80;
            int YFigur = 0; //Schalter ober Maus über Figur ist

            int x = e.getX();
            int y = e.getY();

            for (int i = 0; i < positionenFigurenX.size(); i++) { // Ist das Angeklickte Feld, auf der Eine Figur draufsteht?
                if (x >= positionenFigurenX.get(i)&&x < positionenFigurenX.get(i)+80&&
                        y >= positionenFigurenY.get(i)&&y < positionenFigurenY.get(i)+80){
                    HilfsX = positionenFigurenX.get(i);
                    HilfsY = positionenFigurenY.get(i);
                    YFigur++;
                    break;
                }
            }

            // Prüfen ob Maus auf dem Brett ist
            if (x >= startX && x < startX + 8 * feldGroesse &&
                    y >= startY && y < startY + 8 * feldGroesse) {

                if (YFigur >= 1){
                    hoverCol = (x - startX) / feldGroesse; // In welcher Zeile/ Spalte bin ich?
                    hoverRow = (y - startY) / feldGroesse;
                    Yfarbeanders = 1;
                }else {
                    hoverRow = -1;
                    hoverCol = -1;
                    Yfarbeanders = 0;
                    HilfsX = -1;
                    HilfsY = -1;
                }

            } else {
                hoverRow = -1;
                hoverCol = -1;
                HilfsX = -1;
                HilfsY = -1;
            }
            YFigur = 0;
            repaint();
        }
    }

//    public void zeichneStartaufstellung(Graphics g){
////        g.drawImage(rookwl.getZeichnen(), rookwl.getX()+rookwl.getKorrekturx(), rookwl.getY()+rookwl.getKorrektury(), 80, 80, null);
//        g.drawImage(rookwr.getZeichnen(), rookwr.getX()+rookwr.getKorrekturx(), rookwr.getY()+rookwr.getKorrektury(), 80, 80, null);
//        g.drawImage(rookbl.getZeichnen(), rookbl.getX()+rookbl.getKorrekturx(), rookbl.getY()+rookbl.getKorrektury(), 80, 80, null);
//        g.drawImage(rookbr.getZeichnen(), rookbr.getX()+rookbr.getKorrekturx(), rookbr.getY()+rookbr.getKorrektury(), 80, 80, null);
//        g.drawImage(knightwl.getZeichnen(), knightwl.getX(),knightwl.getY(), 80, 80, null);
//        g.drawImage(knightwr.getZeichnen(), knightwr.getX(), knightwr.getY(), 80, 80, null);
//        g.drawImage(knightbl.getZeichnen(), knightbl.getX(), knightbl.getY(), 80, 80, null);
//        g.drawImage(knightbr.getZeichnen(), knightbr.getX(), knightbr.getY(), 80, 80, null);
//        g.drawImage(bishopwl.getZeichnen(), bishopwl.getX()+bishopwl.getKorrekturx(), bishopwl.getY()+bishopwl.getKorrektury(), 80, 80, null);
//        g.drawImage(bishopwr.getZeichnen(), bishopwr.getX()+ bishopwr.getKorrekturx(), bishopwr.getY()+ bishopwr.getKorrektury(), 80, 80, null);
//        g.drawImage(bishopbl.getZeichnen(), bishopbl.getX()+ bishopbl.getKorrekturx(), bishopbl.getY()+ bishopbl.getKorrektury(), 80, 80, null);
//        g.drawImage(bishopbr.getZeichnen(), bishopbr.getX()+ bishopbr.getKorrekturx(), bishopbr.getY()+ bishopbr.getKorrektury(), 80, 80, null);
//        g.drawImage(queenw.getZeichnen(), queenw.getX(), queenw.getY(), 80, 80, null);
//        g.drawImage(queenb.getZeichnen(), queenb.getX(), queenb.getY(), 80, 80, null);
//        g.drawImage(kingw.getZeichnen(), kingw.getX(), kingw.getY(), 80, 80, null);
//        g.drawImage(kingb.getZeichnen(), kingb.getX(), kingb.getY(), 80, 80, null);
//        for (int i = 0; i < 8; i++) {
//            g.drawImage(pawnsw.get(i).getZeichnen(), pawnsw.get(i).getX()+pawnsw.get(i).getKorrekturx(), pawnsw.get(i).getY()+pawnsw.get(i).getKorrektury(), 80, 80, null);
//            g.drawImage(pawnsb.get(i).getZeichnen(), pawnsb.get(i).getX()+pawnsb.get(i).getKorrekturx(), pawnsb.get(i).getY()+pawnsb.get(i).getKorrektury(), 80, 80, null);
//        }
//    }

    @Override
    public void mousePressed(MouseEvent e) {
//        int startX = 80;
//        int startY = 80;
//        int feldGroesse = 80;
//        int YFigur = 0;//Schalter ober Maus über Figur ist
//
//        int x = e.getX();
//        int y = e.getY();
//
//        for (int i = 0; i < positionenFigurenX.size(); i++) {
//            if (x >= positionenFigurenX.get(i)&&x < positionenFigurenX.get(i)+80&&
//                    y >= positionenFigurenY.get(i)&&y < positionenFigurenY.get(i)+80){
//                YFigur++;
//                break;
//            }
//        }
//
//        // Prüfen ob Maus auf dem Brett ist
//        if (x >= startX && x < startX + 8 * feldGroesse &&
//                y >= startY && y < startY + 8 * feldGroesse) {
//
//            if (YFigur >= 1){
//                hoverCol = (x - startX) / feldGroesse; // In welcher Zeile/ Spalte bin ich?
//                hoverRow = (y - startY) / feldGroesse;
//            }else {
//                hoverRow = -1;
//                hoverCol = -1;
//            }
//
//        } else {
//            hoverRow = -1;
//            hoverCol = -1;
//        }
//        YFigur = 0;
//        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        hoverCol = -1;
//        hoverRow = -1;
//        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

//
