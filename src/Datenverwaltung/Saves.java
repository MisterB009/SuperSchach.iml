package Datenverwaltung;

import GUI.Brett;
import Model.Figur;
import Model.Figuren.*;
import Model.Spielelogik;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class Saves {
    private Brett brett;

    private String spielToJson(Spielelogik logik) {
//        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
//        Gson gson = new Gson();
//        return gson.toJson(logik);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Figur.class, new JsonSerializer<Figur>() {
                    @Override
                    public JsonElement serialize(Figur src, Type typeOfSrc,
                                                 com.google.gson.JsonSerializationContext context) {
                        JsonElement elem = context.serialize(src, src.getClass());
                        elem.getAsJsonObject().addProperty("type", src.getClass().getSimpleName());
                        return elem;
                    }
                })
                .create();
        return gson.toJson(logik);
    }

    private Spielelogik spielFromJson(String spielAsJson){
//        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
//        Gson gson = new Gson();
//        Spielelogik logik = gson.fromJson(spielAsJson, Spielelogik.class);
//        return logik;
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Figur.class, (JsonDeserializer<Figur>) (json, typeOfT, context) -> {
                    JsonObject obj = json.getAsJsonObject();
                    String type = obj.get("type").getAsString();

                    switch(type) {
                        case "Bauer2": return context.deserialize(json, Bauer2.class);
                        case "Turm2": return context.deserialize(json, Turm2.class);
                        case "Springer2": return context.deserialize(json, Springer2.class);
                        case "Laeufer2": return context.deserialize(json, Laeufer2.class);
                        case "Dame2": return context.deserialize(json, Dame2.class);
                        case "Koenig2": return context.deserialize(json, Koenig2.class);
                        default: throw new JsonParseException("Unknown figure type: " + type);
                    }
                })
                .create();
        Spielelogik logik = gson.fromJson(spielAsJson, Spielelogik.class);
        return logik;
    }

    private void reinitializeImages(Spielelogik logik) {
        Figur[][] aufstellung = logik.getAufstellung();
        for (int zeile = 0; zeile < 8; zeile++) {
            for (int spalte = 0; spalte < 8; spalte++) {
                Figur figur = aufstellung[zeile][spalte];
                if (figur != null) {
                    Figur neuesFigur = recreateFigur(figur);
                    if (neuesFigur != null) {
                        aufstellung[zeile][spalte] = neuesFigur;
                    }
                }
            }
        }
    }

    private Figur recreateFigur(Figur alt) {
        int farbe = alt.getFarbe();
        int stil = alt.getStil();

        if (alt instanceof Bauer2) return new Bauer2(farbe, stil);
        if (alt instanceof Turm2) return new Turm2(farbe, stil);
        if (alt instanceof Springer2) return new Springer2(farbe, stil);
        if (alt instanceof Laeufer2) return new Laeufer2(farbe, stil);
        if (alt instanceof Dame2) return new Dame2(farbe, stil);
        if (alt instanceof Koenig2) return new Koenig2(farbe, stil);

        return null;
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
            reinitializeImages(geladen);
            System.out.println("laden");
            return geladen;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
