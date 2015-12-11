package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

import java.util.List;

/**
 * Created by Dennis on 11.12.2015.
 */
public class Arzt {
    private int id;
    private String bild;
    private String name;
    private String geschlecht;
    private String fachgebiet;
    private List<String> diplome;
    private List<String> angebote;
    private List<String> krankenkassen;
    private List<Ordinationszeit> ordinationszeiten;
    private List<Terminvereinbarung> terminvereinbarungen;
    private List<String> fremdsprachen;
    private Adresse adresse;

}
