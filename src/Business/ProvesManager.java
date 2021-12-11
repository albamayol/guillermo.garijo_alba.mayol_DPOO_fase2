package Business;

import Persistance.DAOProva;

import java.util.ArrayList;
import java.util.Objects;

public class ProvesManager {
    private ArrayList<Prova> proves;

    private DAOProva daoProva;

    public ProvesManager() {
        this.proves = new ArrayList<>();
        this.daoProva=new DAOProva("añadir path");
    }

    public DAOProva getDaoProva() {
        return daoProva;
    }

    //metodo para guardar pruebas al csv
    public void guardarPruebas(){
        ArrayList<String> pruebas=new ArrayList<>();
        for (Prova p:proves) {
            pruebas.add(p.pruebaToCSV());
        }
        daoProva.guardarPruebas(pruebas);
    }

    public boolean creaProva(String trialName, String journalName, String journalQuartile, int acceptanceProb, int revisionProb, int rejectionProb) {
        boolean error = false;
        boolean repetit = false;
        Prova prova = new Prova();
        for (Prova prove : proves) {
            if (Objects.equals(prove.getNomProva(), trialName)) {
                repetit = true;
                error = true;
                break;
            }
        }
        if (!repetit) {
            prova.setNomProva(trialName);
            prova.setNomRevista(journalName);
            if (journalQuartile.equals("Q1") || journalQuartile.equals("Q2") || journalQuartile.equals("Q3") || journalQuartile.equals("Q4")) {
                prova.setQuartil(journalQuartile);
                if (acceptanceProb >= 0 && acceptanceProb <= 100) {
                    prova.setProbabilitatAccepta(acceptanceProb);
                    if ((revisionProb >= 0 && revisionProb <= 100) && (acceptanceProb + revisionProb <= 100)) {
                        prova.setProbabilitatRevisions(revisionProb);
                        if ((rejectionProb >= 0 && rejectionProb <= 100) && (acceptanceProb + revisionProb + rejectionProb == 100)) {
                            prova.setProbabilitatRebutja(rejectionProb);
                            //una vegada es comproven tots el paràmetres i són correctes, afegim la prova al array
                            proves.add(prova);
                        } else {
                            error = true;
                        }
                    } else {
                        error = true;
                    }
                } else {
                    error = true;
                }
            } else {
                error = true;
            }
        }
        return error;
    }

    public ArrayList<Prova> llistaProves() {
        return proves;
    }
}
