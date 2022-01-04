package Persistance;

import Business.Prova;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOProvaJSON implements DAOProva{
    private Path path;
    private Gson gson;


    public DAOProvaJSON(String path) {
        gson=new Gson();
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
        return new ArrayList<>();
    }
}
