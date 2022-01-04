package Persistance;

import Business.Edicio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOEdicioJSON implements DAOEdicio{
    private Gson gson;
    private Path path;

    public DAOEdicioJSON(String path) {
        this.gson = new Gson();
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
    public void guardaEdicions(ArrayList<Edicio> ediciones) {
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

    @Override
    public ArrayList<Edicio> llegeixEdicions() {
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            Type collectionType = new TypeToken<Collection<Edicio>>(){}.getType();
            return gson.fromJson(reader.readLine(), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
