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
    private Erreichbarkeit erreichbarkeit;

    public void addDiplom(String diplom) {
        diplome.add(diplom);
    }

    public void addAngebot(String angebot) {
        angebote.add(angebot);
    }

    public void addKrankenkassa(String krankenkassa) {
        krankenkassen.add(krankenkassa);
    }

    public void addOrdinationszeit(Ordinationszeit ordinationszeit) {
        ordinationszeiten.add(ordinationszeit);
    }

    public void addTerminvereinbarung(Terminvereinbarung terminvereinbarung) {
        terminvereinbarungen.add(terminvereinbarung);
    }

    public void addFremdsprache(String fremdsprache) {
        fremdsprachen.add(fremdsprache);
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<String> getAngebote() {
        return angebote;
    }

    public void setAngebote(List<String> angebote) {
        this.angebote = angebote;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public List<String> getDiplome() {
        return diplome;
    }

    public void setDiplome(List<String> diplome) {
        this.diplome = diplome;
    }

    public Erreichbarkeit getErreichbarkeit() {
        return erreichbarkeit;
    }

    public void setErreichbarkeit(Erreichbarkeit erreichbarkeit) {
        this.erreichbarkeit = erreichbarkeit;
    }

    public String getFachgebiet() {
        return fachgebiet;
    }

    public void setFachgebiet(String fachgebiet) {
        this.fachgebiet = fachgebiet;
    }

    public List<String> getFremdsprachen() {
        return fremdsprachen;
    }

    public void setFremdsprachen(List<String> fremdsprachen) {
        this.fremdsprachen = fremdsprachen;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getKrankenkassen() {
        return krankenkassen;
    }

    public void setKrankenkassen(List<String> krankenkassen) {
        this.krankenkassen = krankenkassen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ordinationszeit> getOrdinationszeiten() {
        return ordinationszeiten;
    }

    public void setOrdinationszeiten(List<Ordinationszeit> ordinationszeiten) {
        this.ordinationszeiten = ordinationszeiten;
    }

    public List<Terminvereinbarung> getTerminvereinbarungen() {
        return terminvereinbarungen;
    }

    public void setTerminvereinbarungen(List<Terminvereinbarung> terminvereinbarungen) {
        this.terminvereinbarungen = terminvereinbarungen;
    }
}
