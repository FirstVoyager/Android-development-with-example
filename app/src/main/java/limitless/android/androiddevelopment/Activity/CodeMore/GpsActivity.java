package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

public class GpsActivity extends BaseActivity {

    private int locationCode = 2003;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (! Tools.permissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Tools.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, locationCode);
        }
        getData();
    }


    private void getData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == locationCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getData();
            }else {
                Tools.toast(this, getString(R.string.location_permission_denied));
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
