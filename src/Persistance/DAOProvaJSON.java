package Persistance;

import Business.Proves.*;
import Business.ProvesManager;
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

public class DAOProvaJSON implements DAOProva{
    private Path path;
    private Gson gson;


    public DAOProvaJSON(String path) {
        RuntimeTypeAdapterFactory<Prova> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Prova.class, "tipus")
                .registerSubtype(EstudiMaster.class, "EstudiMaster")
                .registerSubtype(PublicacioArticle.class, "Publication")
                .registerSubtype(SolicitudPressupost.class, "Pressupost")
                .registerSubtype(TesiDoctoral.class, "Tesis");
        gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
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

    @Override
    public void guardaProves(ArrayList<Prova> proves) {
        Gson gson = new Gson();
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String linea = gson.toJson(proves);
        try {
            Files.writeString(path, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Prova> llegeixProves() {
        try {
            BufferedReader reader = Files.newBufferedReader(path);

            Type listType = new TypeToken<List<Prova>>(){}.getType();
            ArrayList<Prova> fromJson = gson.fromJson(reader.readLine(), listType);
            if(fromJson==null){
                return new ArrayList<>();
            }
            for (Prova p : fromJson) {
                p.setTipus(p.getTipus());
            }
            return fromJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
