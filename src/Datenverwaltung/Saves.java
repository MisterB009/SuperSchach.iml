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
        // Falls deine Figuren vor dem Speichern noch spezielle Anpassungen brauchen,
        // könntest du hier (wie im Beispiel) eine Vorbereitungsmethode aufrufen.
        return gson.toJson(logik);
    }
}
