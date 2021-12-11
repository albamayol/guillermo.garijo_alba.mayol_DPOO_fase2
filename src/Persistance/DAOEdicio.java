package Persistance;

import Business.Edicio;

import java.beans.PersistenceDelegate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /*public  ArrayList<Edicio> leerEdiciones(){

    }*/

    public void guardarEdiciones(ArrayList<Edicio> ediciones){

    }

    public void eliminarEdicion(int año){

    }


}
