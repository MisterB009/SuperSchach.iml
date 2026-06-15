package GUI;

import Model.Figur;
import Model.Spielelogik;

import javax.swing.*;
import java.awt.*;

public class GeschlagenePanel extends JPanel {

    private JTextArea area;

    public GeschlagenePanel() {

        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        add(new JScrollPane(area), BorderLayout.CENTER);
    }

    public void aktualisieren(Spielelogik logik) {

        area.setText("");

        area.append("Schwarz geschlagen:\n");
        for (Figur f : logik.getGeschlageneFigurenSchwarz()) {
            area.append(f.getClass().getSimpleName() + "\n");
        }

        area.append("\nWeiß geschlagen:\n");
        for (Figur f : logik.getGeschlageneFigurenWeiss()) {
            area.append(f.getClass().getSimpleName() + "\n");
        }
    }

}