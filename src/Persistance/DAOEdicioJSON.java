package Persistance;

import Business.*;
import Business.Proves.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase para gestionar la persistencia en JSON
 */
public class DAOEdicioJSON implements DAOEdicio{
    private Gson gson;
    private Path path;

    /**
     * Constructor del dao de ediciones JSON
     * @param path Ruta donde se guardara la informacion de las ediciones
     */
    public DAOEdicioJSON(String path) {
        RuntimeTypeAdapterFactory<Prova> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Prova.class, "tipus")
                .registerSubtype(EstudiMaster.class, "EstudiMaster")
                .registerSubtype(PublicacioArticle.class, "Publication")
                .registerSubtype(SolicitudPressupost.class, "Pressupost")
                .registerSubtype(TesiDoctoral.class, "Tesis");
        RuntimeTypeAdapterFactory<Jugador> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory
                .of(Jugador.class, "tipusJugador")
                .registerSubtype(Enginyer.class, "Enginyer")
                .registerSubtype(Master.class, "Master")
                .registerSubtype(Doctor.class, "Doctor");

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(runtimeTypeAdapterFactory);
        builder.registerTypeAdapterFactory(runtimeTypeAdapterFactory1);

        gson = builder.create();
        try{
            Path p = Paths.get(path);
            if(!Files.exists(p)){
                Files.createFile(p);
            }
            this.path=p;
        }catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }
    }

    public void guardaEdicions(ArrayList<Edicio> ediciones) {
        Gson gson = new Gson();
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String linea = gson.toJson(ediciones);
        try {
            Files.writeString(path, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Edicio> llegeixEdicions() {
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            Type listType = new TypeToken<List<Edicio>>(){}.getType();
            ArrayList<Edicio> fromJson = gson.fromJson(reader.readLine(), listType);
            if(fromJson==null){
                return new ArrayList<>();
            }
            for (Edicio e:fromJson) {
                for (Prova p: e.getProves()) {
                    p.setTipus(p.getTipus());
                }
                for (Jugador j: e.getJugadors()) {
                    j.setTipusJugdaor(j.getTipusJugador());
                }
            }

            return fromJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
