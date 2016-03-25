package gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by deathkid535 on 3/25/16.
 */
public class SportstaettenDetailParser {
    private static final String ns = null;

    public Sportstaette parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Sportstaette readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        Sportstaette sportstaette = new Sportstaette();

        parser.require(XmlPullParser.START_TAG, ns, "sportstaette");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("name")) {
                sportstaette.setName(readTag(parser, name));
            } else if (name.equals("kategorie")) {
                sportstaette.setKategorie(readTag(parser, name));
            } else if (name.equals("bewertung")) {
                sportstaette.setBewertung(Integer.parseInt(readTag(parser, name)));
            } else if (name.equals("bild")) {
                sportstaette.setBildUrl(readTag(parser, name));
            } else if (name.equals("info")) {
                sportstaette.setInfo(readTag(parser, name));
            } else if (name.equals("telefonnr")) {
                sportstaette.setTelefonnummer(readTag(parser, name));
            } else if (name.equals("oeffnungszeiten")) {
                sportstaette.setOeffnungszeiten(readTag(parser, name));
            } else if (name.equals("adresse")) {
                sportstaette.setAdresse(readAdresse(parser));
            } else if (name.equals("weitereinfos")) {
                sportstaette.setWeitereinfos(readTag(parser, name));
            } else if (name.equals("id")) {
                sportstaette.setId(Integer.parseInt(readTag(parser, name)));
            } else {
                skip(parser);
            }
        }
        return sportstaette;
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
        return new Adresse(strasse, nummer, plz, ort);
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
