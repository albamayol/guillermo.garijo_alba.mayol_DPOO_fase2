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
        }catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarPruebas(ArrayList<Prova> proves){
        try{
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
            for (Prova p:proves) {
                String linea = p.getNomProva() + "," + p.getNomRevista()+ "," + p.getQuartil()+ "," + p.getProbabilitatAccepta()+ "," + p.getProbabilitatRevisions()+ "," + p.getProbabilitatRebutja()+ ","+ p.getTipus() + "," + p.isUs() + "\n";
                try {
                    Files.writeString(path, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Prova> leerProva(){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
            for (String line:fileContent) {
                String[] tmp = line.split(",");
                //pruebas.add(new Prova(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), Integer.parseInt(tmp[5]), tmp[6], Boolean.parseBoolean(tmp[7])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pruebas;
    }

    public ArrayList<Prova> getPruebasPorIDs(String[] a){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try {
            for (String s:a) {
                ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path));
                for(String l:fileContent){
                    String[] tmp = l.split(",");
                    if(tmp[0].equals(s)){
                        //pruebas.add(new Prova(tmp[0], tmp[1], tmp[2], Integer.parseInt(tmp[3]), Integer.parseInt(tmp[4]), Integer.parseInt(tmp[5]), tmp[6], Boolean.parseBoolean(tmp[7])));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pruebas;
    }
}
