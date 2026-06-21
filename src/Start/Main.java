package Start;//import GUI.MouseHover;
import Datenverwaltung.Saves;
import GUI.Brett;
import Model.Spielelogik;
import Multiplayer.MPBrett;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    JFrame frame;
    private Image icon;


    public static void main(String[] args) {
        new Main();
        Settings settings = new Settings();
        settings.setyStil(1);
    }
    public Main(){
        frame = this;
        //setSize(1000, 1000);
        setMinimumSize(new Dimension(1500,1000));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            icon = ImageIO.read(new File("img/Icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        setIconImage(icon);
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

        JMenuBar bar;
        JMenu files;
        JMenuItem save;
        JMenuItem load;

        private JPanel bretter;
        private JPanel uhr;
        private JPanel spieler1;
        private JPanel spieler2;
        private JPanel filler;

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
                Saves saves = new Saves();
                Brett board = new Brett();
//                this.setLayout(new BorderLayout());
//                Brett board = new Brett();
//                add(board);

                bar = new JMenuBar();
                files = new JMenu("Brett verwalten");
                save = new JMenuItem("Speichern");
                save.addActionListener(e1 -> {
                    saves.speicherSpiel(board.getLogik());
                });
                load = new JMenuItem("Laden");
                load.addActionListener(e1 -> {
                    Spielelogik geladen = saves.laden();

                    if (geladen != null){
                        board.setLogik(geladen);
                        board.repaint();
                    }
                });
                bar.add(files);
                files.add(save);
                files.add(load);
                setJMenuBar(bar);

                bretter = new JPanel(new BorderLayout());
                uhr = new JPanel();
                spieler1 = new JPanel();
                spieler2 = new JPanel();
                filler = new JPanel();
                Settings settings = new Settings();
                if (settings.getyStil() != 1&&settings.getyStil() != 2){
                    settings.setyStil(1);
                }

                bretter.add(board, BorderLayout.CENTER);
                this.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 0.0;
                spieler2.setBackground(Color.GREEN);
                spieler2.setPreferredSize(new Dimension(0, 60)); // Wunschhöhe 60px
                add(spieler2, gbc);

                JButton zurueck = new JButton("Zurück");
                zurueck.addActionListener(e1 -> {
                    getContentPane();
                    removeAll();
                    setLayout(new BorderLayout());
                    WilkommenScreen lobby = new WilkommenScreen();
                    add(lobby);
                    revalidate();
                    repaint();
                });
                filler.add(zurueck);
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 0.0;
                gbc.weighty = 1.0;
                filler.setBackground(Color.YELLOW);
                filler.setPreferredSize(new Dimension(80, 0)); // Wunschbreite 80px
                add(filler, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                add(bretter, gbc);

                gbc.gridx = 2;
                gbc.gridy = 1;
                gbc.weightx = 0.0;
                gbc.weighty = 1.0;
                uhr.setBackground(Color.RED);
                uhr.setPreferredSize(new Dimension(200, 0)); // Wunschbreite 200px
                add(uhr, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.weightx = 1.0;
                gbc.weighty = 0.0;
                spieler1.setBackground(Color.BLUE);
                spieler1.setPreferredSize(new Dimension(0, 60)); // Wunschhöhe 60px
                add(spieler1, gbc);

                revalidate();
                repaint();
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.insets = new Insets(0,0,20,0);
            c.gridy = 1;
            add(Einzelspieler, c);

            //Metavers, kiagenten, autonomes fahren


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
                Einzelspieler.setEnabled(false);
                Multiplayer.setEnabled(false);
                Einstellungen.setEnabled(false);

                mpLobby = new MPLobby(this);
                mpLobby.setVisible(true);

                mpLobby.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        Einzelspieler.setEnabled(true);
                        Multiplayer.setEnabled(true);
                        Einstellungen.setEnabled(true);
                    }
                });
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
                Einzelspieler.setEnabled(false);
                Multiplayer.setEnabled(false);
                Einstellungen.setEnabled(false);

                settings = new Settings();
                settings.setVisible(true);

                settings.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        Einzelspieler.setEnabled(true);
                        Multiplayer.setEnabled(true);
                        Einstellungen.setEnabled(true);
                    }
                });
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 3;
            add(Einstellungen, c);
        }
    }
}
