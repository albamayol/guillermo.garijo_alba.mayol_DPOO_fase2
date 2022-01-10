package Business;

import Business.Proves.*;
import Persistance.DAOProva;

import java.util.ArrayList;
import java.util.Iterator;

public class ProvesManager {
    private ArrayList<Prova> proves;
    private DAOProva daoProva;

    public ProvesManager(DAOProva daoProva) {
        this.daoProva = daoProva;
        this.proves= daoProva.llegeixProves();
    }
    //constructor para el manager de pruebas de la edicion (sin dao)
    public ProvesManager(ArrayList<Prova> proves) {
        this.proves = proves;
        daoProva =null;
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
        daoProva.guardaProves(proves);
    }

    public void creaPaperPubli(String trialName, String journalName, String journalQuartile, int acceptanceProb, int revisionProb, int rejectionProb) {
        proves.add(new PublicacioArticle(trialName, journalName, journalQuartile, acceptanceProb, rejectionProb, rejectionProb, "Publication", false));
    }
    public void creaMaster(String trialName, String masterName, int numCred, int probAprova){
        proves.add(new EstudiMaster(trialName, "EstudiMaster", masterName, numCred, probAprova));
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

    public void creaTesis(String trialName, String field, int diff) {
        proves.add(new TesiDoctoral(trialName, "Tesis", field, diff));
    }

    public void creaPressupost(String trialName, String entity, int budget) {
        proves.add(new SolicitudPressupost(trialName, "Pressupost", entity, budget));
    }
}
