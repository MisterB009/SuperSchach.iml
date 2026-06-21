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
