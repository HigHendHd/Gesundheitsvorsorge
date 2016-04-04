package gvapp.diplomprojekt.at.gv_appandroid.Daten;

/**
 * Created by Dennis on 04.12.2015.
 */
public abstract class Constants {
    public static final String URL_BASE = "http://gvapp.eig14830.webspace.spengergasse.at/data";

    public static final String URL_REZEPTE_LISTE = "/rezepteliste.xml";
    public static final String URL_REZEPTE_BASE = "/rezepte";

    public static final String URL_AERZTE_BASE = "/aerzte";
    public static final String URL_AERZTE_LISTE = "/aerzteliste.xml";

    public static final String URL_DIAETEN_BASE = "/diaeten";
    public static final String URL_DIAETEN_LISTE = "/diaetenliste.xml";

    public static final String URL_NEWS_BASE = "/newsfeed";
    public static final String URL_NEWS_LISTE = "/newsfeedliste.xml";

    public static final String URL_RESTAURANT_BASE = "/restaurants";
    public static final String URL_RESTAURANT_LISTE = "/restaurantliste.xml";

    public static final String URL_SPORTSTAETTEN_BASE = "/sportstaetten";
    public static final String URL_SPORTSTAETTEN_LISTE = "/sportstaettenliste.xml";

    public static final String URL_UEBUNGEN_BASE = "/sportuebungen";
    public static final String URL_UEBUNGEN_LISTE = "/sportuebungenliste.xml";

    public static final String URL_WORKOUT_BASE = "/trainingsplaene";
    public static final String URL_WORKOUT_LISTE = "/trainingsplaeneliste.xml";


    public static final String INTENT_REZEPTE_URL = "RezepteUrl";
    public static final String INTENT_RESTAURANT_URL = "RestaurantUrl";
    public static final String INTENT_AERZTE_URL = "AerzteUrl";
    public static final String INTENT_DIAETEN_URL = "DiaetenUrl";
    public static final String INTENT_NEWS_URL = "DiaetenUrl";
    public static final String INTENT_SPORTSTAETTEN_URL = "SportstaettenUrl";
    public static final String INTENT_TRAININGSPLAENE_URL = "TrainingsplaeneUrl";
    public static final String INTENT_UEBUNGEN_URL = "UebungenUrl";

    public static final int DATA_TYPE_AERZTE = 1;
    public static final int DATA_TYPE_RESTAURANTS = 2;
    public static final int DATA_TYPE_SPORTSTAETTEN = 3;

    public static final String TRINKERINNERUNG_FILENAME = "TrinkErinnerungConfig.csv";
    public static final String FILE_SEPERATOR = ";";
}
