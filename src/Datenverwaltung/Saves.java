package Datenverwaltung;

import GUI.Brett;
import Model.Spielelogik;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Saves {
    private Brett brett;

    private String spielToJson(Spielelogik logik) {
        Gson gson = new Gson();
        return gson.toJson(logik);
    }

    private Spielelogik spielFromJson(String spielAsJson){
        Gson gson = new Gson();
        Spielelogik logik = gson.fromJson(spielAsJson, Spielelogik.class);
        return logik;
    }

    public void speicherSpiel(Spielelogik aktuell){
        try {
            Files.writeString(Path.of("spiel.json"), spielToJson(aktuell));
            System.out.println("speicher");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Spielelogik laden(){
        try {
            String jsonText = Files.readString(Path.of("spiel.json"));
            Spielelogik geladen = spielFromJson(jsonText);
            System.out.println("laden");
            return geladen;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
