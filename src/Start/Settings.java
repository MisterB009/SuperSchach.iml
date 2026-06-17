package Start;

import Model.Spielelogik;

import javax.swing.*;
import java.awt.*;

//sound
//farbe
//figuren

public class Settings extends JFrame {
    private JLabel ueberschrift;
    private JButton standard;
    private JButton FLspezial;
    int yStil;

    public Settings() {
//        if (yStil != 2) {
//            yStil = 0;
//        }
//        if (yStil == 0) {
//            yStil = 1;
//        }
        Spielelogik logik  = new Spielelogik();
        System.out.println("Settings: "+yStil);
        setTitle("Einstellungen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0);
        ueberschrift = new JLabel("Einstellungen");
        Font font = new Font("Arial", Font.BOLD, 30);
        ueberschrift.setFont(font);
        add(ueberschrift,c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 20, 0);
        standard = new JButton("Standard");
        standard.addActionListener(e -> {
            setyStil(1);
            logik.setStil(yStil);
        });
        add(standard,c);
        c.gridx = 0;
        c.gridy = 2;
        FLspezial = new JButton("FLspezial");
        FLspezial.addActionListener(e -> {
            setyStil(2);
            logik.setStil(yStil);
            System.out.println("fl: "+yStil);
        });
        add(FLspezial,c);
    }

    public int getyStil() {
        return yStil;
    }

    public void setyStil(int yStil) {
        this.yStil = yStil;
    }
}
