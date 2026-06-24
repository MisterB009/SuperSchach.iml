package Multiplayer;

import Datenverwaltung.Saves;
import GUI.BrettMouseListener;
import Model.Spielelogik;

import javax.swing.*;
import java.awt.*;

public class takeBack extends JDialog {
    private boolean approved = false;
    private JButton approveButton;
    private JButton denyButton;
    private JLabel messageLabel;
    private Saves saves;
    private MPBrett mpbrett;

    // Fenster für den Spieler, der den Zug zurück nimmt
    public takeBack(JFrame parent, boolean isRequester, Saves saves, MPBrett mpbrett) {
        super(parent, "Zug zurück", true);
        this.saves = saves;
        this.mpbrett = mpbrett;
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        if (isRequester) {
            // Fenster für den Spieler, der den Zug zurück fordert (Wartezustand)
            messageLabel = new JLabel("Warte auf Genehmigung des Gegners...");
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(messageLabel, gbc);

            JButton cancelButton = new JButton("Abbrechen");
            cancelButton.addActionListener(e -> {
                approved = false;
                dispose();
            });
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            add(cancelButton, gbc);
        } else {
            // Fenster für den Gegner (Entscheidungsfenster)
            messageLabel = new JLabel("Gegner fordert Zug zurück. Erlauben?");
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(messageLabel, gbc);

            approveButton = new JButton("Erlauben");
            approveButton.addActionListener(e -> {
                approved = true;
                Spielelogik geladen = saves.loadCache();

                if (geladen != null){
                    mpbrett.setLogik(geladen);
                    BrettMouseListener listener = mpbrett.getMouseListener();
                    if (listener != null) {
                        listener.setLogik(geladen);
                    }
                    mpbrett.repaint();
                }
                dispose();
            });
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(approveButton, gbc);

            denyButton = new JButton("Ablehnen");
            denyButton.addActionListener(e -> {
                approved = false;
                dispose();
            });
            gbc.gridx = 1;
            gbc.gridy = 1;
            add(denyButton, gbc);
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public boolean isApproved() {
        return approved;
    }
}
