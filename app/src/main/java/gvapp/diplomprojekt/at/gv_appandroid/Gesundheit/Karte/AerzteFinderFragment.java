package gvapp.diplomprojekt.at.gv_appandroid.Gesundheit.Karte;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Dennis on 17.01.2016.
 */
public class AerzteFinderFragment extends MapFragment implements OnMapReadyCallback {
    private LatLng mPosFija;

    public AerzteFinderFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        View v = super.onCreateView(arg0, arg1, arg2);
        initMap();
        return v;
    }

    private void initMap() {
        Log.e("Hallo", "Map initializing");
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("Hallo", "Map ready");
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(48.209206, 16.372778))
                .title("Marker"));
    }
}
