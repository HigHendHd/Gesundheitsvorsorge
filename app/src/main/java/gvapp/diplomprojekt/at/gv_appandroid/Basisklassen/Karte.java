package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

/**
 * Created by deathkid535 on 1/27/16.
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.Daten.Constants;
import gvapp.diplomprojekt.at.gv_appandroid.Daten.LocationConverter;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.Ernaehrung.Restaurants.Liste.RestaurantListenParser;
import gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Liste.AerzteListenParser;
import gvapp.diplomprojekt.at.gv_appandroid.R;
import gvapp.diplomprojekt.at.gv_appandroid.Sport.Sportstaetten.Liste.SportstaettenListenParser;

public class Karte extends MapFragment implements LocationListener, OnMapReadyCallback, DownloadXmlTask.XmlDownloader, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private static String dataUrl;
    private static int DATA_TYPE;
    private GoogleMap myMap;
    private GoogleApiClient mGoogleApiClient;
    private List<ListenEintrag> eintraege = new ArrayList<>();
    private LocationManager locationManager;

    public Karte() {
        super();
    }

    public static Karte newInstance(String url, int DATA_TYPE) {
        Karte frag = new Karte();
        dataUrl = url;
        Karte.DATA_TYPE = DATA_TYPE;
        return frag;
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();

        super.onStop();
    }

    @Override
    public void onStart() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

        super.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //return;
        }
        myMap.setMyLocationEnabled(true);


        //googleMap.setMyLocationEnabled(true);

        /*myMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.209206, 16.372778))
                .title("Marker"));*/

        createData();
    }

    private void createData() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new DownloadXmlTask(this).execute(dataUrl);

        } else {
            Snackbar.make(getView(), getActivity().getString(R.string.keininternet),
                    Snackbar.LENGTH_INDEFINITE).setAction(getActivity().getResources()
                    .getString(R.string.nochmalversuchen), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createData();
                }
            }).show();
        }
    }

    @Override
    public void xmlDownloaded(InputStream result) {
        if (result != null) {
            eintraege.clear();
            try {
                switch (DATA_TYPE) {
                    case Constants.DATA_TYPE_AERZTE:
                        eintraege.addAll(new AerzteListenParser().parse(result));
                        break;

                    case Constants.DATA_TYPE_RESTAURANTS:
                        eintraege.addAll(new RestaurantListenParser().parse(result));
                        break;
                    case Constants.DATA_TYPE_SPORTSTAETTEN:
                        eintraege.addAll(new SportstaettenListenParser().parse(result));
                        break;
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (ListenEintrag le : eintraege) {
                if (LocationConverter.getLocationFromAddress(getActivity(),
                        le.getAdresse()) != null) {
                    myMap.addMarker(new MarkerOptions()
                            .position(LocationConverter.getLocationFromAddress(getActivity(),
                                    le.getAdresse())));
                }
            }
        } else {
            Snackbar.make(getView(), "Fehler im Download", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        myMap.animateCamera(cameraUpdate);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}