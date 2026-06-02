package Start;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Settings extends JFrame {
    private JLabel ueberschrift;

    public Settings() {
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
    }
}
