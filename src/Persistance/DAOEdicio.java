package Persistance;

import Business.Edicio;

import java.util.ArrayList;

public interface DAOEdicio {
    void guardaEdicions(ArrayList<Edicio> ediciones);
    ArrayList<Edicio> llegeixEdicions();
}
