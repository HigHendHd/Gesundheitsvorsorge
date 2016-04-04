package gvapp.diplomprojekt.at.gv_appandroid.Sport.Uebungen.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dennis on 04.04.2016.
 */
public class UebungsDetailParser {
    private static final String ns = null;

    public Uebung parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Uebung readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
        Uebung uebung = new Uebung();

        parser.require(XmlPullParser.START_TAG, ns, "sportuebung");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("name")) {
                uebung.setName(readTag(parser, name));
            } else if (name.equals("muskelgruppe")) {
                uebung.setMuskelgruppe(readTag(parser, name));
            } else if (name.equals("bild")) {
                uebung.setBildUrl(readTag(parser, name));
            } else if (name.equals("beschreibung")) {
                uebung.setBeschreibung(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return uebung;
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
