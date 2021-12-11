package Persistance;

import Business.Edicio;
import Business.Jugador;
import Business.Prova;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOProva {

    private Path path;

    public DAOProva(String path) {
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

    public void guardarPruebas(ArrayList<String> a){
        for (String e:a) {
            try {
                Files.writeString(path, e, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Prova> leerProva(){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                pruebas.add(new Prova(tmp[0], tmp[1], tmp[2], Integer.valueOf(tmp[3]), Integer.valueOf(tmp[4]), Integer.valueOf(tmp[5]), Boolean.valueOf(tmp[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pruebas;
    }

    public ArrayList<Prova> getPruebasPorIDs(String[] a){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                for (String s: a) {
                    if(a.equals(tmp[0])){
                        pruebas.add(new Prova());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pruebas;
    }
}
