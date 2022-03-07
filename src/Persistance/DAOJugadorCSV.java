package Persistance;

import Business.Jugador.Doctor;
import Business.Jugador.Enginyer;
import Business.Jugador.Jugador;
import Business.Jugador.Master;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

/**
 *
 */
public class DAOJugadorCSV {

    private Path path;

    public DAOJugadorCSV(String path) {
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

    public ArrayList<String> guardarJugadores(ArrayList<Jugador> jugadores){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> keys = new ArrayList<>();
        for (Jugador j:jugadores) {
            keys.add(String.valueOf(j.getNom()));
            String line =j.getNom() + "," + j.getPI() + "," + j.getTipusJugador() + "\n";
            try {
                Files.writeString(path, line, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return keys;
    }


    public ArrayList<Jugador> getJugadores(){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for(String l:fileContent){
                String[] tmp = l.split(",");
                switch (tmp[2]) {
                    case "Enginyer" -> jugadores.add(new Enginyer(tmp[0], Integer.parseInt(tmp[1])));
                    case "Master" -> jugadores.add(new Master(tmp[0], Integer.parseInt(tmp[1])));
                    case "Doctor" -> jugadores.add(new Doctor(tmp[0], Integer.parseInt(tmp[1])));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
