package Persistance;

import Business.Proves.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DAOProvaCSV implements DAOProva{

    private Path pathMaster;
    private Path pathTesis;
    private Path pathPublicacio;
    private Path pathPressupost;

    /**
     * Constructor para el dao de pruebas csv
     * @param pathMaster Direccion donde se guardaran las pruebas de estudios de master
     * @param pathTesis Direccion donde se guardaran las pruebas de tesis
     * @param pathPublicacio Direccion donde se guardaran las pruebas de publicaciones
     * @param pathPressupust Direccion donde se guardaran las pruebas de solicitud de presupuesto
     */
    public DAOProvaCSV(String pathMaster, String pathTesis, String pathPublicacio, String pathPressupust) {
        this.pathMaster=auxConstructor(pathMaster);
        this.pathTesis=auxConstructor(pathTesis);
        this.pathPublicacio=auxConstructor(pathPublicacio);
        this.pathPressupost=auxConstructor(pathPressupust);
    }

    private static Path auxConstructor(String s){
        try{
            Path p = Paths.get(s);
            if(!Files.exists(p)){
                Files.createFile(p);
            }
            return p;
        }catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void auxGuardaProves(Path p){
        try {
            BufferedWriter writer = Files.newBufferedWriter(p);
            writer.write("");
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void guardaProves(ArrayList<Prova> proves){
        auxGuardaProves(pathMaster);
        auxGuardaProves(pathTesis);
        auxGuardaProves(pathPublicacio);
        auxGuardaProves(pathPressupost);
        try{
            for (Prova p:proves) {
                String linea = p.getNomProva()  + "," + p.isUs() + "," + p.getTipus();
                switch (p.getTipus()) {
                    case "Publication" -> {
                        linea += "," + p.getNomRevista() + "," + p.getQuartil() + "," + p.getProbabilitatAccepta() + "," + p.getProbabilitatRevisions() + "," + p.getProbabilitatRebutja() + "\n";
                        Files.writeString(pathPublicacio, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                    case "EstudiMaster" -> {
                        linea += "," + p.getNomMaster() + "," + p.getCredits() + "," + p.getProbabilitatMaster() + "\n";
                        Files.writeString(pathMaster, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                    case "Tesis" -> {
                        linea += "," + p.getCampEstudis() + "," + p.getDifficulty() + "\n";
                        Files.writeString(pathTesis, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                    case "Pressupost" -> {
                        linea += "," + p.getEntity() + "," + p.getBudget() + "\n";
                        Files.writeString(pathPressupost, linea, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ArrayList<Prova> auxLlegeixProves(Path p,int tipo){
        ArrayList<Prova> tmp = new ArrayList<>();
        String[] lineSplit;
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(p));
            for (String line:fileContent) {
                switch (tipo) {
                    case 0:
                        lineSplit = line.split(",");
                        tmp.add(new EstudiMaster(lineSplit[0], lineSplit[1], Boolean.parseBoolean(lineSplit[2]), lineSplit[3],Integer.parseInt(lineSplit[4]),Integer.parseInt( lineSplit[5])));
                        break;
                    case 1:
                        lineSplit = line.split(",");
                        tmp.add(new SolicitudPressupost(lineSplit[0], lineSplit[1], Boolean.parseBoolean(lineSplit[2]),lineSplit[3], Double.parseDouble(lineSplit[4])));
                        break;
                    case 2:
                        lineSplit = line.split(",");
                        tmp.add(new PublicacioArticle(lineSplit[0], lineSplit[1], Boolean.parseBoolean(lineSplit[2]),lineSplit[3], lineSplit[4], Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[6]), Integer.parseInt(lineSplit[7])));
                        break;
                    case 3:
                        lineSplit = line.split(",");
                        tmp.add(new TesiDoctoral(lineSplit[0], lineSplit[1], Boolean.parseBoolean(lineSplit[2]),lineSplit[3], Integer.parseInt(lineSplit[4])));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public ArrayList<Prova> llegeixProves(){
        ArrayList<Prova> pruebas = new ArrayList<>();
        pruebas.addAll(auxLlegeixProves(pathMaster, 0));
        pruebas.addAll(auxLlegeixProves(pathPressupost, 1));
        pruebas.addAll(auxLlegeixProves(pathPublicacio, 2));
        pruebas.addAll(auxLlegeixProves(pathTesis, 3));
        return pruebas;
    }

    /**
     * Metodo para actualizar las pruebas.
     * @param proves
     * @return Array de strings (ids de las pruebas) que han sido actualizadas
     */
    public ArrayList<String> actualitzaProves(ArrayList<Prova> proves){
        ArrayList<Prova> lectura = new ArrayList<>();
        lectura.addAll(auxLlegeixProves(pathMaster, 0));
        lectura.addAll(auxLlegeixProves(pathPressupost, 1));
        lectura.addAll(auxLlegeixProves(pathPublicacio, 2));
        lectura.addAll(auxLlegeixProves(pathTesis, 3));
        for (Prova p:proves) {
            for (int i = 0; i < lectura.size(); i++) {
                if(lectura.get(i).getNomProva().equals(p.getNomProva())){
                    lectura.remove(i);
                    lectura.add(p);
                    break;
                }
            }
        }
        guardaProves(lectura);
        ArrayList<String> ids = new ArrayList<>();
        for (Prova p:proves) {
            ids.add(p.getNomProva());
        }
        return ids;
    }

    /**
     * Metodo para recuperar una prueba en funcion de su id
     * @param a Array de strings con las ids de las pruebas a recuperar
     * @return Array de pruebas con las pruebas recuperadas
     */
    public ArrayList<Prova> getPruebasPorIDs(String[] a){
        ArrayList<Prova> pruebas = new ArrayList<>();
        try{
            ArrayList<Prova> leidas= llegeixProves();
            for (String s:a) {
                for (Prova p:leidas) {
                    if(s.equals(p.getNomProva())){
                        pruebas.add(p);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pruebas;
    }
}
