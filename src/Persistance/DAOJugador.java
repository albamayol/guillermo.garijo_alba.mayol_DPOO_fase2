package Persistance;

import Business.Jugador;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOJugador {

    private Path path;

    public DAOJugador(String path) {
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

    public void guardarJugadores(ArrayList<String> jugadores){
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String e:jugadores) {
            try {
                Files.writeString(path, e + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Jugador> leerJugadores(){
        ArrayList<Jugador> lectura=new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                    lectura.add(new Jugador(tmp[1], Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectura;
    }

    public ArrayList<Jugador> getJugadoresPorID(String[] a){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                for (String s: a) {
                    if(s.equals(tmp[0])){
                        jugadores.add(new Jugador(tmp[1], Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])));
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
