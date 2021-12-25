package Business;

import Persistance.DAOProva;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

public class ProvesManager {
    private ArrayList<Prova> proves;
    private DAOProva daoProva;

    public ProvesManager(DAOProva daoProva) {
        this.daoProva=daoProva;
        this.proves=daoProva.leerProva();
    }

    public ProvesManager(ArrayList<Prova> proves) {
        this.proves = proves;
        daoProva=null;
    }

    public void setToUse(int i){
        proves.get(i).usada();
    }
    public boolean noHayPruebas(){
        return proves.isEmpty();
    }

    public Prova getProva(int i){
        return proves.get(i);
    }

    public boolean existeixProva(String nom){
        Iterator<Prova> it = proves.iterator();
        while (it.hasNext()){
            if(it.next().equals(nom)){
                return true;
            }
        }
        return false;
    }
    public boolean comprobaQuartil(String quartil){
        return quartil.equals("Q1") || quartil.equals("Q2") || quartil.equals("Q3") || quartil.equals("Q4");
    }
    public boolean comprobaProb(int prob){
        return prob<=100 && prob>=0;
    }

    //metodo para guardar pruebas al csv
    public void guardarPruebas(){
        daoProva.guardarPruebas(proves);
    }

    public void creaProva(String trialName, String journalName, String journalQuartile, int acceptanceProb, int revisionProb, int rejectionProb, String tipus) {
        proves.add(new Prova(trialName, journalName, journalQuartile, acceptanceProb, rejectionProb, rejectionProb, tipus, false));
    }

    public ArrayList<Prova> llistaProves() {
        return proves;
    }

    public void eliminaProva(int i) {
        if(!proves.get(i).isUs()){
            proves.remove(i);
        }
    }

    public ArrayList<String> nomsProves(){
        ArrayList<String> r=new ArrayList<>();
        for (Prova p:proves) {
            r.add(p.getNomProva());
        }
        return r;
    }

    public ProvesManager copia() {
        return new ProvesManager(this.proves);
    }

    public void addProva(Prova p) {
        p.usada();
        proves.add(p);
    }

    public ArrayList<Prova> getProves() {
        return proves;
    }
    public String getNomProvaActual(int ultimaProva) {
        return proves.get(ultimaProva).getNomProva();
    }
    public boolean isInUse(int i) {
        return proves.get(i).isUs();
    }
    public String getNomProva(int i) {
        return proves.get(i).getNomProva();
    }
    public String getTipusProva(int i) {
        return proves.get(i).getTipus();
    }
}
