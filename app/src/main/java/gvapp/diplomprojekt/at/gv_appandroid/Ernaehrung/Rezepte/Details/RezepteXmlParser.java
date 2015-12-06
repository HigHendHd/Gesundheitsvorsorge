package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dennis on 06.12.2015.
 */
public class RezepteXmlParser {
    // We don't use namespaces
    private static final String ns = null;

    public Rezept parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Rezept readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Rezept rezept = new Rezept();

        parser.require(XmlPullParser.START_TAG, ns, "rezept");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("name")) {
                rezept.setName(readTag(parser, "name"));
            } else if (name.equals("bild")) {
                rezept.setBildUrl(readTag(parser, "bild"));
            } else if (name.equals("anzahlportionen")) {
                rezept.setAnzahlportionen(Integer.parseInt(readTag(parser, "anzahlportionen")));
            } else if (name.equals("kochdauer")) {
                rezept.setKochdauer(readTag(parser, "kochdauer"));
            } else if (name.equals("schwierigkeitsgrad")) {
                rezept.setSchwierigkeitsgrad(readTag(parser, "schwierigkeitsgrad"));
            } else if (name.equals("id")) {
                rezept.setId(Integer.parseInt(readTag(parser, "id")));
            } else if (name.equals("zutat")) {
                rezept.addZutat(readZutat(parser));
            } else if (name.equals("schritt")) {
                rezept.addSchritt(readSchritt(parser));
            } else if (name.equals("tipp")) {
                rezept.setTipp(readTag(parser, "tipp"));
            } else {
                skip(parser);
            }
        }
        return rezept;
    }

    private Schritt readSchritt(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "schritt");
        int nummer = 0;
        String text = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("nummer")) {
                nummer = Integer.parseInt(readTag(parser, "nummer"));
            } else if (name.equals("text")) {
                text = readTag(parser, "text");
            } else {
                skip(parser);
            }
        }
        return new Schritt(nummer, text);
    }

    private Zutat readZutat(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "zutat");
        double anzahl = 0;
        String einheit = null;
        String zname = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("anzahl")) {
                anzahl = Double.parseDouble(readTag(parser, "anzahl"));
            } else if (name.equals("einheit")) {
                einheit = readTag(parser, "einheit");
            } else if (name.equals("zname")) {
                zname = readTag(parser, "zname");
            } else {
                skip(parser);
            }
        }
        return new Zutat(anzahl, einheit, zname);
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
