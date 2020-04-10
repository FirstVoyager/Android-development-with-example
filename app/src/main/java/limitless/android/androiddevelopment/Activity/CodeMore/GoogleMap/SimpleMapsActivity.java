package limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SimpleMapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_simple);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // location for new york city
        LatLng newyork = new LatLng(40.773441704175674, -73.97169564529764);
        // marker option (title, icon etc)
        MarkerOptions mo = new MarkerOptions();
        mo.position(newyork);
        mo.title(getString(R.string.newyork));
        mo.icon(Tools.bitmapDescriptor(R.drawable.ic_map_marker_64dp));
        // add maker to map
        mMap.addMarker(mo);
        // zoom and move camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newyork, 15));
    }
}
