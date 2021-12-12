package Persistance;

import Business.Edicio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOEdicio {

    private final DAOJugador daojugador;
    private final DAOProva daoProva;
    private Path path;

    public DAOEdicio(DAOJugador daojugador, DAOProva daoProva, String path) {
        this.daojugador = daojugador;
        this.daoProva = daoProva;
        try{
            Path p = Paths.get(path);
            if(!Files.exists(p)){
                Files.createFile(p);
            }
            this.path=p;
        }catch (InvalidPathException | IOException e){
            System.err.println("ERROR leyendo o escribiendo");
        }
    }


    public ArrayList<Edicio> leerEdiciones() {
        ArrayList<Edicio> ediciones = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line : fileContent) {
                String[] tmp = line.split(",");
                String[] jugadores = tmp[3].replace("]", "").replace("[", "").split(";");
                String[] pruebas = tmp[4].replace("]", "").replace("[", "").split(";");
                ediciones.add(new Edicio(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), daojugador.getJugadoresPorID(jugadores), daoProva.getPruebasPorIDs(pruebas), Integer.parseInt(tmp[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ediciones;
    }

    public void guardarEdiciones(ArrayList<String> ediciones){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String e:ediciones) {
            try {
                Files.writeString(path, e, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
