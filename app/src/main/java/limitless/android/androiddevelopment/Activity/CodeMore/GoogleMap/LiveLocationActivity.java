package limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

public class LiveLocationActivity extends BaseActivity implements OnMapReadyCallback {

    private final int locationCode = 121;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_location);
        init();
    }

    private void init() {
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Places.initialize(this, getString(R.string.map_key));
        PlacesClient placesClient = Places.createClient(this);
//        placesClient.findAutocompletePredictions(new )

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        supportMapFragment.getMapAsync(this);
        if (! Tools.permissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION) || ! Tools.permissionGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
            Tools.requestPermission(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationCode);
            return;
        }
        setUp();
    }

    private void setUp() {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                moveCamera(location);
            }
        });
        fusedLocationProviderClient.requestLocationUpdates(
                new LocationRequest(),
                locationCallback,
                null
        );
    }

    private void moveCamera(Location location) {
        if (googleMap == null)
            return;
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions mo = new MarkerOptions();
        mo.position(ll);
        mo.title(getString(R.string.newyork));
        mo.icon(Tools.bitmapDescriptor(R.drawable.ic_map_marker_64dp));
        googleMap.addMarker(mo);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15f));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == locationCode){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setUp();
            }else {
                Tools.toast(this, getString(R.string.permission_denied));
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private LocationCallback locationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            for (Location l : locationResult.getLocations()) {
                moveCamera(l);
            }
        }
    };

}
