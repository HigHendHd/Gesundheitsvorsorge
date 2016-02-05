package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Liste;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Basisklassen.ListenEintrag;

/**
 * Created by Dennis on 13.12.2015.
 */
public class RestaurantListenParser {
    // We don't use namespaces
    private static final String ns = null;

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    public List parse(InputStream in) throws XmlPullParserException, IOException {
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

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "restaurants");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("restaurant")) {
                entries.add(readRezept(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private ListenEintrag readRezept(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "restaurant");
        String name = null;
        String bildUrl = null;
        String url = null;
        String info = null;
        String adresse = null;
        int id = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            if (tag.equals("name")) {
                name = readTag(parser, tag);
            } else if (tag.equals("bild")) {
                bildUrl = readTag(parser, tag);
            } else if (tag.equals("link")) {
                url = readTag(parser, tag);
            } else if (tag.equals("id")) {
                id = Integer.parseInt(readTag(parser, tag));
            } else if (tag.equals("ort")) {
                info = readTag(parser, tag);
            } else if (tag.equals("adresse")) {
                adresse = readAdresse(parser);
            } else {
                skip(parser);
            }
        }
        ListenEintrag aerzteListenEintrag = new ListenEintrag(name, info, url, id, bildUrl);
        aerzteListenEintrag.setAdresse(adresse);
        return aerzteListenEintrag;
    }

    private String readAdresse(XmlPullParser parser) throws XmlPullParserException, IOException {
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
            } else if (name.equals("plz")) {
                postleitzahl = readTag(parser, name);
            } else if (name.equals("ort")) {
                stadt = readTag(parser, name);
            } else {
                skip(parser);
            }
        }
        return strasse + " " + nummer + ", " + postleitzahl + " " + stadt;
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
}
