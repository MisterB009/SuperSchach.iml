//import GUI.MouseHover;
import GUI.Brett;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JFrame frame;
    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        frame = this;
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WilkommenScreen start = new WilkommenScreen();
        add(start);
        setVisible(true);
    }
    public class WilkommenScreen extends JPanel{
        private JButton Einzelspieler;
        private JButton Multiplayer;
        private JButton Einstellungen;

        public WilkommenScreen(){
            setLayout(new GridLayout(1,1));
            Einzelspieler = new JButton("Einzelspieler");
            Multiplayer = new JButton("Multiplayer");
            Einstellungen = new JButton("Einstellungen");
            Einzelspieler.addActionListener(e -> {
                Container c = frame.getContentPane();
                c.removeAll();
                Brett board = new Brett();
                c.add(board, BorderLayout.CENTER);
                c.revalidate();
                c.repaint();
            });
            add(Einzelspieler);
            add(Multiplayer);
            add(Einstellungen);
        }
    }
}
