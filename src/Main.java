import GUI.Brett;
import GUI.GeschlagenePanel;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Schach");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Brett board = new Brett();
        GeschlagenePanel panel = new GeschlagenePanel();

//        board.setGeschlagenePanel(panel); ########################

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                board,
                panel
        );

        split.setDividerLocation(850);

        add(split);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
// Richtig