package gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Rezepte;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 04.12.2015.
 */
public class RezepteParser {
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
            Log.v("Hallo", "1");
            XmlPullParser parser = Xml.newPullParser();
            Log.v("Hallo", "2");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            Log.v("Hallo", "3");
            parser.setInput(in, null);
            Log.v("Hallo", "4");
            parser.nextTag();
            Log.v("Hallo", "5");
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "rezepte");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("rezept")) {
                entries.add(readRezept(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Rezept readRezept(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "rezept");
        String name = null;
        String bildUrl = null;
        String url = null;
        int id = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            if (tag.equals("name")) {
                name = readName(parser);
            } else if (tag.equals("bild")) {
                bildUrl = readBild(parser);
            } else if (tag.equals("link")) {
                url = readLink(parser);
            } else if (tag.equals("id")) {
                id = readId(parser);
            } else {
                skip(parser);
            }
        }
        Rezept rezept = new Rezept(name, "Rezept", url);
        rezept.setBildUrl(bildUrl);
        rezept.setId(id);
        return rezept;
    }

    // Processes name tags in the feed.
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return title;
    }

    private String readBild(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "bild");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "bild");
        return title;
    }

    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return title;
    }

    private int readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "id");
        int title = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, "id");
        return title;
    }

    // Reads the text in the tags
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
