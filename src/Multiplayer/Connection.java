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
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    Main.WilkommenScreen main;
    MPLobby.ipFenster ipFenster;
    Timer timer;
    MPBrett mpBrett;

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
                mpBrett.repaint();
            }
        });
    }

    private void updateBrett(){

    }

    private void lobbyoeffnen(Socket verbindung, boolean istHost) {
        SwingUtilities.invokeLater(() -> {
            Saves saves = new Saves();
            Spielelogik logik = new Spielelogik();
            GeschlagenePanel panel =
                    new GeschlagenePanel();
            MPBrett lobby = new MPBrett(verbindung, istHost, panel, logik);
            main.Brettanzeigen(lobby);
        });
    }
}
