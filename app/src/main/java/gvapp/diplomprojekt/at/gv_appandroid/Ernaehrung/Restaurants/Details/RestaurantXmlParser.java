package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Details;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class RestaurantXmlParser {
    private static final String ns = null;

    public Restaurant parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private Restaurant readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        Restaurant restaurant = new Restaurant();

        parser.require(XmlPullParser.START_TAG, ns, "restaurant");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("bild")) {
                restaurant.setBild(readTag(parser, name));
            } else if (name.equals("name")) {
                restaurant.setName(readTag(parser, name));
            } else if (name.equals("kategorie")) {
                restaurant.setKategorie(readTag(parser, name));
            } else if (name.equals("oeffnungszeiten")) {
                restaurant.setOeffnungszeiten(readTag(parser, name));
            } else if (name.equals("telefonnr")) {
                restaurant.setTelefonnr(readTag(parser, name));
            } else if (name.equals("info")) {
                restaurant.setInfo(readTag(parser, name));
            } else if (name.equals("adresse")) {
                restaurant.setAdresse(readAdresse(parser));
            } else if (name.equals("weitereinfos")) {
                restaurant.setWeiterinfos(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return restaurant;
    }

    private Adresse readAdresse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "adresse");
        String strasse = null;
        String nummer = null;
        String plz = null;
        String ort = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("strasse")) {
                strasse = readTag(parser, name);
            } else if (name.equals("nummer")) {
                nummer = readTag(parser, name);
            } else if (name.equals("plz")) {
                plz = readTag(parser, name);
            } else if (name.equals("ort")) {
                ort = readTag(parser, name);
            } else {
                skip(parser);
            }
        }
        return new Adresse(nummer, plz, ort, strasse);
    }

    private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String text = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return text;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
