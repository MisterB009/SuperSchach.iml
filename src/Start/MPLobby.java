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
        setSize(2000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        Dimension btngroesse = new Dimension(250,45);

        infotext = new JLabel("Wählen sie ihre Rolle aus");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.insets = new Insets(0,0,20,0);
        c.gridy = 0;
        add(infotext,c);
        host = new JButton("Spiel erstellen");
        host.setPreferredSize(btngroesse);
        host.addActionListener(e -> {
            new Thread(() -> {
                try {
                    ServerSocket serverSocket = new ServerSocket(49152);
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
        c.insets = new Insets(0,0,20,0);
        c.gridy = 1;
        add(host);

        client = new JButton("Spiel beitreten");
        client.setPreferredSize(btngroesse);
        client.addActionListener(e -> {
            getContentPane();
            removeAll();
            this.setLayout(new BorderLayout());
            ipFenster ipFenster = new ipFenster();
            add(ipFenster);
            revalidate();
            repaint();
        });
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        add(client);
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
            text = new JLabel("Hier die IP-Adresse bitte eingeben: ");
            ipadress = new JTextField();
            add(text);
            add(ipadress);
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
