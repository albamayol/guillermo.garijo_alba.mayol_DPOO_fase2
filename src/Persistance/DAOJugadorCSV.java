package Persistance;

import Business.Jugador;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

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
            keys.add(String.valueOf(j.getId()));
            String line = j.getId() + "," + j.getNom() + "," + j.getPI() + "\n";
            try {
                Files.writeString(path, line, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return keys;
    }

    public ArrayList<Jugador> leerJugadores(){
        ArrayList<Jugador> lectura=new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                    //lectura.add(new Jugador(tmp[1], Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectura;
    }

    public ArrayList<Jugador> getJugadoresPorID(String[] a){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            for (String s:a) {
                ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
                for(String l:fileContent){
                    String[] tmp = l.split(",");
                    if(tmp[0].equals(s)){
                        //jugadores.add(new Jugador(tmp[1], Integer.parseInt(tmp[2]), Integer.parseInt(tmp[0])));
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
