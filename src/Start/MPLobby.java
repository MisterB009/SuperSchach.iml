package Start;

import GUI.Brett;
import Multiplayer.MPBrett;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MPLobby extends JFrame {
    JLabel infotext;
    JButton host;
    JButton client;
    Main.WilkommenScreen main;

    public MPLobby(Main.WilkommenScreen main) {
        super();
        this.main = main;
        setTitle("Multiplayer Lobby");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        Dimension btngroesse = new Dimension(200,40);


        infotext = new JLabel("Wählen sie ihre Rolle aus");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,50,0);
        Font font = new Font("Arial", Font.BOLD, 30);
        infotext.setFont(font);
        add(infotext,c);


        host = new JButton("Spiel erstellen");
        host.setPreferredSize(btngroesse);
        host.addActionListener(e -> {
            new Thread(() -> {
                try {
                    System.out.println("geht?");
                    ServerSocket serverSocket = new ServerSocket(49152);


                    JPanel warte = new JPanel();
                    warte.setLayout(new GridBagLayout());
                    JLabel wtext = new JLabel("Warte auf Gegner");
                    Font font1 = new Font("Arial", Font.BOLD, 60);
                    wtext.setFont(font1);
                    GridBagConstraints c1 = new GridBagConstraints();
                    c1.gridx = 0;
                    c1.gridy = 0;
                    warte.add(wtext,c1);
                    Container cont = getContentPane();
                    cont.removeAll();
                    cont.setLayout(new BorderLayout());
                    cont.add(warte);
                    revalidate();
                    repaint();


                    Socket verbindungHalten = serverSocket.accept();
                    System.out.println("hier");
                    lobbyoeffnen(verbindungHalten, true);
                    System.out.println("da");
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

        client = new JButton("Spiel beitreten");
        client.setPreferredSize(btngroesse);
        client.addActionListener(e -> {
            Container co = getContentPane();
            co.removeAll();
            co.setLayout(new BorderLayout());
            ipFenster ipFenster = new ipFenster();
            co.add(ipFenster);
            System.out.println("client");
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
        JLabel text;
        JTextField ipadress;

        public ipFenster(){
            setLayout(new GridBagLayout());
            Dimension ipgroesse = new Dimension(100,30);

            text = new JLabel("Hier die IP-Adresse bitte eingeben: ");
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
                            System.out.println("tod");
                        });
                        ex.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
