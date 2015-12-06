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
                rezept.setName(readName(parser));
            } else if (name.equals("bild")) {
                rezept.setBildUrl(readBild(parser));
            } else {
                skip(parser);
            }
        }
        return rezept;
    }

    private String readBild(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "bild");
        String bild = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "bild");
        return bild;
    }

    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String name = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return name;
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
