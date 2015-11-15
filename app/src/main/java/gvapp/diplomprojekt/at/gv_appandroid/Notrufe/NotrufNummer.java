package gvapp.diplomprojekt.at.gv_appandroid.Notrufe;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 14.11.2015.
 */
public class NotrufNummer extends ListenEintrag {

    public NotrufNummer() {

    }

    public static NotrufNummer createNotrufNummern() {
        NotrufNummer retNummer = new NotrufNummer();

        retNummer.addItem("Feuerwehr", "122");
        retNummer.addItem("Polizei", "133");
        retNummer.addItem("Rettung", "144");
        retNummer.addItem("Euronotruf", "112");
        retNummer.addItem("Bergrettung", "140");
        retNummer.addItem("Notruf für Gehörlose", "0800 133 133");
        retNummer.addItem("Ärztefunktdienst", "141");
        retNummer.addItem("ORF-Kinderservice (Rat auf Draht)", "147");
        retNummer.addItem("Ärzteflugambulanz", "40 144");
        retNummer.addItem("Frauenhelpline", "0800 222 555");
        retNummer.addItem("Frauennotruf", "01/71 71 9");
        retNummer.addItem("Telefonseelsorge", "142");
        retNummer.addItem("Gasgebrechen", "128");
        retNummer.addItem("ARBÖ", "122");
        retNummer.addItem("ÖAMTC", "123");
        retNummer.addItem("Kinder- und Jugendanwalt des Bundes", "0800-240264");
        retNummer.addItem("Vergiftungsinformation", "01/406 43 43");
        retNummer.addItem("Servicenummer der Polizei", "059 133");
        retNummer.addItem("Feuerwehr", "122");

        return retNummer;
    }
}
