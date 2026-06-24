package Multiplayer;

import Datenverwaltung.Saves;
import GUI.GeschlagenePanel;
import Model.Spielelogik;
import Start.MPLobby;
import Start.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    Main.WilkommenScreen main;
    MPLobby.ipFenster ipFenster;
    Timer timer;
    MPBrett mpBrett;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private boolean istHost;

    public Connection(Main.WilkommenScreen main, MPLobby.ipFenster ipFenster) {
        this.main = main;
        this.ipFenster = ipFenster;

        new Thread(() -> {
            try {
                Socket verbindugzumHost = new Socket(ipFenster.getIpadress().getText(),49152);
                lobbyoeffnen(verbindugzumHost,false);
            } catch (IOException ex) {
                SwingUtilities.invokeLater(()-> {
                    System.out.println("client tod");
                });
                ex.printStackTrace();
            }
        }).start();
    }

    public Connection(Main.WilkommenScreen main) {
        this.main = main;

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
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mpBrett != null) {
                    mpBrett.repaint();
                }
            }
        });
        timer.start();
    }

    private void lobbyoeffnen(Socket verbindung, boolean istHost) {
        SwingUtilities.invokeLater(() -> {
            try {
                setupStreams(verbindung);
                this.istHost = istHost;

                Saves saves = new Saves();
                Spielelogik logik = new Spielelogik();
                GeschlagenePanel panel = new GeschlagenePanel();

                mpBrett = new MPBrett(verbindung, istHost, panel, logik, this);

                listenForMoves(mpBrett);

                main.Brettanzeigen(mpBrett);
            } catch (IOException e) {
                System.err.println("Error setting up streams: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void setupStreams(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMove(int startZeile, int startSpalte, int zielZeile, int zielSpalte) {
        try {
            Move move = new Move(startZeile,startSpalte,zielZeile,zielSpalte);
            out.writeObject(move);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForMoves(MPBrett mpBrett) {
        new Thread(() -> {
            try {
                while (true) {
                    Move move = (Move) in.readObject();
                    SwingUtilities.invokeLater(() -> {
                        boolean success = mpBrett.getLogik().bewegeFigur(
                                move.startZeile, move.startSpalte,
                                move.zielZeile, move.zielSpalte
                        );
                        if (success) {
                            mpBrett.repaint();
                            GeschlagenePanel panel = mpBrett.getPanel();
                            if (panel != null) {
                                panel.aktualisieren(mpBrett.getLogik());
                            }
                        }
                    });
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Connection lost");
            }
        }).start();
    }
    public boolean istHost() {
        return istHost;
    }
}
