package Persistance;

import Business.Edicio;
import Business.JugadorManager;
import Business.Proves.Prova;
import Business.ProvesManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Clase para gestionar la persistencia en CSV
 */
public class DAOEdicioCSV implements DAOEdicio{

    private final DAOJugadorCSV daojugador;
    private final DAOProvaCSV daoProvaCSV;
    private Path path;

    /**
     * Constructor del dao de ediciones (csv)
     * @param path Direccion donde se guardaran las ediciones
     * @param pathMaster Direccion donde se guardaran las pruebas de estudios de master
     * @param pathTesis Direccion donde se guardaran las pruebas de tesis
     * @param pathPublicacio Direccion donde se guardaran las pruebas de publicaciones
     * @param pathPressupost Direccion donde se guardaran las pruebas de solicitud de presupuesto
     * @param pathJugador Direccion donde se guardaran los jugadores
     */
    public DAOEdicioCSV( String path, String pathMaster, String pathTesis, String pathPublicacio, String pathPressupost, String pathJugador) {
        this.daojugador = new DAOJugadorCSV(pathJugador);
        this.daoProvaCSV = new DAOProvaCSV(pathMaster, pathTesis, pathPublicacio, pathPressupost);
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


    public ArrayList<Edicio> llegeixEdicions() {
        ArrayList<Edicio> ediciones = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line : fileContent) {
                String[] tmp = line.split(",");String[] pruebas = tmp[3].replace("]", "").replace("[", "").split(";");
                ediciones.add(new Edicio(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]),
                        new JugadorManager(daojugador.getJugadores()),
                        new ProvesManager(daoProvaCSV.getPruebasPorIDs(pruebas)), Integer.parseInt(tmp[5]))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ediciones;
    }


    public void guardaEdicions(ArrayList<Edicio> ediciones){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Edicio e:ediciones) {
            StringBuilder linea = new StringBuilder(e.getAny() + "," + e.getNumJugadors() + "," + e.getNumProves() + ",[");
            for (String p: daoProvaCSV.actualitzaProves(e.getProves())) {
                linea.append(p).append(";");
            }
            linea.append("],[");
            for (String j :daojugador.guardarJugadores(e.getJugadors())) {
                linea.append(j).append(";");
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
