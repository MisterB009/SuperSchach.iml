//import GUI.MouseHover;
import GUI.Brett;
import GUI.Figur;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new Main(); // Brett
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    public Main(){
        Brett board = new Brett();
        add(board);
    }
}
