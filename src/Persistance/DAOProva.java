package Persistance;

import Business.Prova;

import java.util.ArrayList;

public interface DAOProva {
    void guardaProves(ArrayList<Prova> proves);
    ArrayList<Prova> llegeixProves();
}
