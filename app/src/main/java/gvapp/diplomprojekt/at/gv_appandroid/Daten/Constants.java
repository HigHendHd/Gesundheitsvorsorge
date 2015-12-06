package gvapp.diplomprojekt.at.gv_appandroid.Daten;

import java.io.InputStream;

/**
 * Created by Dennis on 04.12.2015.
 */
public abstract class Constants {
    public static final String URL_REZEPTE_LISTE = "/rezepteliste.xml";
    public static final String URL_REZEPTE_BASE = "http://gvapp.eig14830.webspace.spengergasse.at/data/rezepte";
    public static String URL_REZEPT_AKTUELL = "";
    public static int selected_rezepte_id = 0;
    public static InputStream xmlStream;
}
