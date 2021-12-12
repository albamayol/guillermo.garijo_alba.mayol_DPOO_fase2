package Persistance;

import Business.Prova;

import java.io.BufferedWriter;
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
        try{
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
            for (String e:a) {
                try {
                    Files.writeString(path, e + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e){
            System.err.println("caca");
        }

    }

    public ArrayList<Prova> leerProva(){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                pruebas.add(new Prova(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), Integer.parseInt(tmp[5]), Boolean.parseBoolean(tmp[6])));
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
            for (String k:a) {
                for (String line:fileContent) {
                    String[] tmp = line.split(",");
                    for (String s: a) {
                        if(s.equals(tmp[0])){
                            pruebas.add(new Prova(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), Integer.valueOf(tmp[4]), Integer.valueOf(tmp[5]), Boolean.valueOf(tmp[6])));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pruebas;
    }
}
