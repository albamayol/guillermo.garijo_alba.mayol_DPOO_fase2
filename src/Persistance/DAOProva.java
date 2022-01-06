package Persistance;

import Business.Proves.Prova;

import java.util.ArrayList;

public interface DAOProva {
    void guardaProves(ArrayList<Prova> proves);
    ArrayList<Prova> llegeixProves();
}
