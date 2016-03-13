package gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dennis on 06.12.2015.
 */
// Uses AsyncTask to create a task away from the main UI thread. This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class DownloadXmlTask extends AsyncTask<String, Void, InputStream> {

    private XmlDownloader xmlDownloader;

    public DownloadXmlTask(XmlDownloader xml) {
        xmlDownloader = xml;
    }

    @Override
    protected InputStream doInBackground(String... urls) {
        InputStream is = null;
        // params comes from the execute() call: params[0] is the url.
        try {
            is = downloadUrl(urls[0]);
            xmlDownloader.xmlDownloaded(is);
            return is;
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(InputStream result) {
        xmlDownloader.fillData();
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private InputStream downloadUrl(String myurl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            return is;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {

        }
        //return null;
    }

    public interface XmlDownloader {
        void xmlDownloaded(InputStream result);

        void fillData();
    }
}
