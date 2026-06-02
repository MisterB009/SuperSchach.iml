package Start;//import GUI.MouseHover;
import GUI.Brett;
import Multiplayer.MPBrett;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Main extends JFrame {
    JFrame frame;
    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        frame = this;
        //setSize(1000, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WilkommenScreen start = new WilkommenScreen();
        add(start);
        setVisible(true);
    }
    public class WilkommenScreen extends JPanel{
        private JLabel Start;
        private JButton Einzelspieler;
        private JButton Multiplayer;
        private JButton Einstellungen;
        private Image hintergrund;
        private MPLobby mpLobby;
        private Settings settings;
        Color dG = new Color(110, 110,110);
        Color dGHover = new Color(140, 140, 140);


        @Override
        public void paintComponent(Graphics g){
            try {
                hintergrund= ImageIO.read(new File("img/Start Hintergrund.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(hintergrund,0,0,this.getWidth(),this.getHeight(),null);
            super.paintComponent(g);
        }
        public void Brettanzeigen(MPBrett lobby){
            getContentPane();
            removeAll();
            setLayout(new BorderLayout());
            add(lobby);
            revalidate();
            repaint();
            mpLobby.dispose();
        }

        public WilkommenScreen(){
            setOpaque(false);
            MouseStart mouseStart = new MouseStart(this);
            this.setLayout(new GridBagLayout());
            Dimension btngroesse = new Dimension(250,45);


            Start = new JLabel("MegaSchach");
            Font groeseSchrift = new Font("Arial", Font.BOLD, 100);
            Start.setFont(groeseSchrift);
            Start.setForeground(Color.WHITE);
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(0,0,50,0);
            c.gridx = 0;
            c.gridy = 0;
            add(Start,c);


            Einzelspieler = new JButton("Einzelspieler"){
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (getModel().isRollover()) {
                        g2.setColor(dGHover);
                    } else {
                        g2.setColor(dG);
                    }
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2.dispose();
                    super.paintComponent(g);
                }
                //test
            };
            Einzelspieler.setFocusPainted(false);
            Einzelspieler.setBorderPainted(false);
            Einzelspieler.setContentAreaFilled(false);
            Einzelspieler.setForeground(Color.WHITE);
            Einzelspieler.addMouseListener(mouseStart);
            Einzelspieler.setPreferredSize(btngroesse);
            Einzelspieler.addActionListener(e -> {
                getContentPane();
                removeAll();
                this.setLayout(new BorderLayout());
                Brett board = new Brett();
                add(board);
                revalidate();
                repaint();
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.insets = new Insets(0,0,20,0);
            c.gridy = 1;
            add(Einzelspieler, c);


            Multiplayer = new JButton("Multiplayer"){
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (getModel().isRollover()) {
                        g2.setColor(dGHover);
                    } else {
                        g2.setColor(dG);
                    }
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            Multiplayer.setFocusPainted(false);
            Multiplayer.setBorderPainted(false);
            Multiplayer.setContentAreaFilled(false);
            Multiplayer.setForeground(Color.WHITE);
            Multiplayer.addMouseListener(mouseStart);
            Multiplayer.setPreferredSize(btngroesse);
            Multiplayer.addActionListener(e -> {
                mpLobby = new MPLobby(this);
                mpLobby.setVisible(true);
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.insets = new Insets(0,0,20,0);
            c.gridy = 2;
            add(Multiplayer, c);


            Einstellungen = new JButton("Einstellungen"){
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (getModel().isRollover()) {
                        g2.setColor(dGHover);
                    } else {
                        g2.setColor(dG);
                    }
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            Einstellungen.setFocusPainted(false);
            Einstellungen.setBorderPainted(false);
            Einstellungen.setContentAreaFilled(false);
            Einstellungen.setForeground(Color.WHITE);
            Einstellungen.addMouseListener(mouseStart);
            Einstellungen.setPreferredSize(btngroesse);
            Einstellungen.addActionListener(e -> {
                settings = new Settings();
                settings.setVisible(true);
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 3;
            add(Einstellungen, c);
        }
    }
}
