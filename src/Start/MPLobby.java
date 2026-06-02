package Start;

import GUI.Brett;
import Multiplayer.MPBrett;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MPLobby extends JFrame {
    JLabel infotext;
    JButton host;
    JButton client;
    Main.WilkommenScreen main;
    Color dG = new Color(110, 110,110);
    Color dGHover = new Color(140, 140, 140);

    public MPLobby(Main.WilkommenScreen main) {
        super();
        this.main = main;
        setTitle("Multiplayer Lobby");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        Dimension btngroesse = new Dimension(200,40);
        MouseListener mouseListener = new MouseStart(this);
        this.getContentPane().setBackground(Color.darkGray);


        infotext = new JLabel("Wählen sie ihre Rolle aus");
        infotext.setForeground(Color.white);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,50,0);
        Font font = new Font("Arial", Font.BOLD, 30);
        infotext.setFont(font);
        add(infotext,c);


        host = new JButton("Spiel erstellen"){
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
        host.setFocusPainted(false);
        host.setBorderPainted(false);
        host.setContentAreaFilled(false);
        host.addMouseListener(mouseListener);
        host.setForeground(Color.WHITE);
        host.setPreferredSize(btngroesse);
        host.addActionListener(e -> {
            new Thread(() -> {
                try {
                    ServerSocket serverSocket = new ServerSocket(49152);
                    String lokaleIP = java.net.InetAddress.getLocalHost().getHostAddress();


                    JPanel warte = new JPanel();
                    warte.setLayout(new GridBagLayout());
                    JLabel wtext = new JLabel("Warte auf Gegner");
                    JLabel infotext1 = new JLabel("Der Gegner muss ihre IP-Adresse eingeben:");
                    JLabel iptext = new JLabel(lokaleIP);
                    Font font1 = new Font("Arial", Font.BOLD, 60);
                    wtext.setFont(font1);
                    Font font2 = new Font("Arial", Font.BOLD, 30);
                    GridBagConstraints c1 = new GridBagConstraints();
                    c1.gridx = 0;
                    c1.gridy = 0;
                    c1.insets = new Insets(0,0,20,0);
                    warte.add(wtext,c1);
                    c1 = new  GridBagConstraints();
                    c1.gridx = 0;
                    c1.gridy = 1;
                    c1.insets = new Insets(0,0,20,0);
                    infotext1.setFont(font2);
                    warte.add(infotext1,c1);
                    c1 = new GridBagConstraints();
                    c1.gridx = 0;
                    c1.gridy = 2;
                    iptext.setFont(font2);
                    warte.add(iptext,c1);
                    Container cont = getContentPane();
                    cont.removeAll();
                    cont.setLayout(new BorderLayout());
                    cont.add(warte);
                    revalidate();
                    repaint();


                    Socket verbindungHalten = serverSocket.accept();
                    lobbyoeffnen(verbindungHalten, true);
                    serverSocket.close();
                } catch (IOException es) {
                    System.out.println("TOD");
                }
            }).start();
        });
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,20,0);
        add(host,c);

        client = new JButton("Spiel beitreten"){
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
        client.setFocusPainted(false);
        client.setBorderPainted(false);
        client.setContentAreaFilled(false);
        client.addMouseListener(mouseListener);
        client.setForeground(Color.WHITE);
        client.setPreferredSize(btngroesse);
        client.addActionListener(e -> {
            Container co = getContentPane();
            co.removeAll();
            co.setLayout(new BorderLayout());
            ipFenster ipFenster = new ipFenster();
            co.add(ipFenster);
            revalidate();
            repaint();
        });
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        add(client,c);
    }

    private void lobbyoeffnen(Socket verbindung, boolean istHost) {
        SwingUtilities.invokeLater(() -> {
            MPBrett lobby = new MPBrett(verbindung, istHost);
            main.Brettanzeigen(lobby);
        });
    }
    public class ipFenster extends JPanel implements KeyListener {
        private JLabel text;
        private JTextField ipadress;
        private JButton verbinden;


        public ipFenster(){
            setLayout(new GridBagLayout());
            Dimension ipgroesse = new Dimension(100,30);
            MouseStart mouseStart = new MouseStart(MPLobby.this);
            this.setBackground(Color.darkGray);

            text = new JLabel("Hier die IP-Adresse bitte eingeben: ");
            text.setForeground(Color.WHITE);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0,0,10,0);
            Font font = new Font("Arial", Font.BOLD, 30);
            text.setFont(font);
            add(text,c);


            ipadress = new JTextField();
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1;
            ipadress.setPreferredSize(ipgroesse);
            add(ipadress,c);


            verbinden = new JButton("Verbinden"){
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
            verbinden.setFocusPainted(false);
            verbinden.setBorderPainted(false);
            verbinden.setContentAreaFilled(false);
            verbinden.setForeground(Color.WHITE);
            verbinden.addMouseListener(mouseStart);
            verbinden.addActionListener(e -> {
                new Thread(() -> {
                    try {
                        Socket verbindugzumHost = new Socket(ipadress.getText(),49152);
                        lobbyoeffnen(verbindugzumHost,false);
                    } catch (IOException ex) {
                        SwingUtilities.invokeLater(()-> {
                            System.out.println("client tod");
                        });
                        ex.printStackTrace();
                    }
                }).start();
            });
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 2;
            add(verbinden,c);

            setVisible(true);

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                new Thread(() -> {
                    try {
                        Socket verbindugzumHost = new Socket(ipadress.getText(),49152);
                        lobbyoeffnen(verbindugzumHost,false);
                    } catch (IOException ex) {
                        SwingUtilities.invokeLater(()-> {
                            System.out.println("client tod");
                        });
                        ex.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
