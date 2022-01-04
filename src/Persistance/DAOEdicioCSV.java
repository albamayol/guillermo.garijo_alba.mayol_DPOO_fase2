package Persistance;

import Business.Edicio;
import Business.Prova;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOEdicioCSV {

    private final DAOJugadorCSV daojugador;
    private final DAOProvaCSV daoProvaCSV;
    private Path path;

    public DAOEdicioCSV(DAOJugadorCSV daojugador, DAOProvaCSV daoProvaCSV, String path) {
        this.daojugador = daojugador;
        this.daoProvaCSV = daoProvaCSV;
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


    public ArrayList<Edicio> leerEdiciones() {
        ArrayList<Edicio> ediciones = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line : fileContent) {
                String[] tmp = line.split(",");
                String[] jugadores = tmp[4].replace("]", "").replace("[", "").split(";");
                String[] pruebas = tmp[3].replace("]", "").replace("[", "").split(";");
                ediciones.add(new Edicio(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), daojugador.getJugadoresPorID(jugadores), daoProvaCSV.getPruebasPorIDs(pruebas), Integer.parseInt(tmp[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ediciones;
    }

    public void guardarEdiciones(ArrayList<Edicio> ediciones){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Edicio e:ediciones) {
            StringBuilder linea = new StringBuilder(e.getAny() + "," + e.getNumJugadors() + "," + e.getNumProves() + ",[");
            for (Prova p:e.getProves()) {
                linea.append(p.getNomProva()).append(";");
            }
            linea.append("],[");
            for (String s :daojugador.guardarJugadores(e.getJugadors())) {
                linea.append(s).append(";");
            }
            linea.append("],").append(e.getUltimaProva()).append("\n");
            try {
                Files.writeString(path, linea.toString(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
