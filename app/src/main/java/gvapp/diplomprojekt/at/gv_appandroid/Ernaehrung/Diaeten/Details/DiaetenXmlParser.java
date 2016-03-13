package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Diaeten.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dennis on 06.12.2015.
 */
public class DiaetenXmlParser {
    // We don't use namespaces
    private static final String ns = null;

    public Diaet parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Diaet readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Diaet diaet = new Diaet();

        parser.require(XmlPullParser.START_TAG, ns, "dieat");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("name")) {
                diaet.setName(readTag(parser, name));
            } else if (name.equals("bild")) {
                diaet.setBildUrl(readTag(parser, name));
            } else if (name.equals("info")) {
                diaet.setInfo(readTag(parser, name));
            } else if (name.equals("erklaerung")) {
                diaet.setErklaerung(readTag(parser, name));
            } else if (name.equals("vorteile")) {
                diaet.setVorteile(readTag(parser, name));
            } else if (name.equals("nachteile")) {
                diaet.setNachteile(readTag(parser, name));
            } else if (name.equals("fazit")) {
                diaet.setFazit(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return diaet;
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
