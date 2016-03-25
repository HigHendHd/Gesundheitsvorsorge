package gvapp.diplomprojekt.at.gv_appandroid.Neuigkeiten.Details;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by deathkid535 on 3/13/16.
 */
public class NeuigkeitenXmlParser {
    // We don't use namespaces
    private static final String ns = null;

    public Neuigkeit parse(InputStream in) throws XmlPullParserException, IOException {
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

    private Neuigkeit readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        Neuigkeit neuigkeit = new Neuigkeit();

        parser.require(XmlPullParser.START_TAG, ns, "artikel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag

            if (name.equals("name")) {
                neuigkeit.setName(readTag(parser, name));
            } else if (name.equals("bild")) {
                neuigkeit.setBildUrl(readTag(parser, name));
            } else if (name.equals("text")) {
                neuigkeit.setText(readTag(parser, name));
            } else {
                skip(parser);
            }
        }
        return neuigkeit;
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
