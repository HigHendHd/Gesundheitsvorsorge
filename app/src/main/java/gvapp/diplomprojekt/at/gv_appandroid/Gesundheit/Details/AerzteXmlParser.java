package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dennis on 11.12.2015.
 */
public class AerzteXmlParser {
    private static final String ns = null;

    public Arzt parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Arzt readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Arzt arzt = new Arzt();

        parser.require(XmlPullParser.START_TAG, ns, "arzt");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("bild")) {
                arzt.setBild(readTag(parser, name));
            } else if (name.equals("name")) {
                arzt.setName(readTag(parser, name));
            } else if (name.equals("fachgebiet")) {
                arzt.setFachgebiet(readTag(parser, name));
            } else if (name.equals("diplom")) {
                arzt.addDiplom(readTag(parser, name));
            } else if (name.equals("schwierigkeitsgrad")) {
                arzt.addAngebot(readTag(parser, name));
            } else if (name.equals("ordinationszeit")) {
                arzt.addOrdinationszeit(readOrdinationszeit(parser));
            } else if (name.equals("terminvereinbarung")) {
                arzt.addTerminvereinbarung(readTerminvereinbarung(parser));
            } else if (name.equals("fremdsprache")) {
                arzt.addFremdsprache(readTag(parser, name));
            } else if (name.equals("adresse")) {
                arzt.setAdresse(readAdresse(parser));
            } else if (name.equals("erreichbarkeit")) {
                arzt.setErreichbarkeit(readErreichbarkeit(parser));
            } else if (name.equals("krankenkassa")) {
                arzt.addKrankenkassa(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return arzt;
    }

    private Erreichbarkeit readErreichbarkeit(XmlPullParser parser) throws XmlPullParserException, IOException {
        Erreichbarkeit erreichbarkeit = new Erreichbarkeit();
        parser.require(XmlPullParser.START_TAG, ns, "erreichbarkeit");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("telefon")) {
                erreichbarkeit.setTelefon(readTag(parser, name));
            } else if (name.equals("mobil")) {
                erreichbarkeit.setMobil(readTag(parser, name));
            } else if (name.equals("email")) {
                erreichbarkeit.setEmail(readTag(parser, name));
            } else if (name.equals("internet")) {
                erreichbarkeit.setInternet(readTag(parser, name));
            } else if (name.equals("fax")) {
                erreichbarkeit.setFax(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return erreichbarkeit;
    }

    private Adresse readAdresse(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "adresse");
        String strasse = null;
        String nummer = null;
        String postleitzahl = null;
        String stadt = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("strasse")) {
                strasse = readTag(parser, name);
            } else if (name.equals("nummer")) {
                nummer = readTag(parser, name);
            } else if (name.equals("postleitzahl")) {
                postleitzahl = readTag(parser, name);
            } else if (name.equals("stadt")) {
                stadt = readTag(parser, name);
            } else {
                skip(parser);
            }
        }
        return new Adresse(nummer, postleitzahl, stadt, strasse);
    }

    private Ordinationszeit readOrdinationszeit(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ordinationszeit");
        String tag = null;
        String start = null;
        String ende = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("tag")) {
                tag = readTag(parser, name);
            } else if (name.equals("start")) {
                start = readTag(parser, name);
            } else if (name.equals("ende")) {
                ende = readTag(parser, name);
            } else {
                skip(parser);
            }
        }
        return new Ordinationszeit(ende, start, tag);
    }

    private Terminvereinbarung readTerminvereinbarung(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "terminvereinbarung");
        String tag = null;
        String start = null;
        String ende = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("tag")) {
                tag = readTag(parser, name);
            } else if (name.equals("start")) {
                start = readTag(parser, name);
            } else if (name.equals("ende")) {
                ende = readTag(parser, name);
            } else {
                skip(parser);
            }
        }
        return new Terminvereinbarung(ende, start, tag);
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
