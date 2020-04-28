package limitless.android.androiddevelopment.Activity.Basic.Sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class PositionActivity extends BaseActivity {

    private SensorManager sensorManager;
    private AppCompatTextView tvGameRotation, tvGeomagneticRotaion, tvMagneticField,
                        tvMagneticFieldUncalibrated, tvOrientaion, tvProximity;
    private Sensor sGameRotation, sGeonmagneticRotation, sMagneticField,
                sMagneticFieldUncalibrated, sOrientaion, sProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        tvGameRotation = findViewById(R.id.textView_gameRotation);
        tvGeomagneticRotaion = findViewById(R.id.textView_geomagneticRotation);
        tvMagneticField = findViewById(R.id.textView_magneticField);
        tvMagneticFieldUncalibrated = findViewById(R.id.textView_magneticFieldUncalibrated);
        tvOrientaion = findViewById(R.id.textView_orientation);
        tvProximity = findViewById(R.id.textView_proximity);

        sGameRotation = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        if (sGameRotation == null)
            tvGameRotation.setText(getString(R.string.text_not_found));
        sGeonmagneticRotation = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        if (sGeonmagneticRotation == null)
            tvGeomagneticRotaion.setText(getString(R.string.text_not_found));
        sMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (sMagneticField == null)
            tvMagneticField.setText(getString(R.string.text_not_found));
        sMagneticFieldUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        if (sMagneticFieldUncalibrated == null)
            tvMagneticFieldUncalibrated.setText(getString(R.string.text_not_found));
        sOrientaion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sOrientaion == null)
            tvOrientaion.setText(getString(R.string.text_not_found));
        sProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (sProximity == null)
            tvProximity.setText(getString(R.string.text_not_found));

    }

    @Override
    protected void onStart() {
        super.onStart();
        int delay = SensorManager.SENSOR_DELAY_NORMAL;
        if (sGameRotation != null)
            sensorManager.registerListener(listener, sGameRotation, delay);
        if (sGeonmagneticRotation != null)
            sensorManager.registerListener(listener, sGeonmagneticRotation, delay);
        if (sMagneticField != null)
            sensorManager.registerListener(listener, sMagneticField, delay);
        if (sMagneticFieldUncalibrated != null)
            sensorManager.registerListener(listener, sMagneticFieldUncalibrated, delay);
        if (sOrientaion != null)
            sensorManager.registerListener(listener, sOrientaion, delay);
        if (sProximity != null)
            sensorManager.registerListener(listener, sProximity, delay);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sGameRotation != null)
            sensorManager.unregisterListener(listener, sGameRotation);
        if (sGeonmagneticRotation != null)
            sensorManager.unregisterListener(listener, sGeonmagneticRotation);
        if (sMagneticField != null)
            sensorManager.unregisterListener(listener, sMagneticField);
        if (sMagneticFieldUncalibrated != null)
            sensorManager.unregisterListener(listener, sMagneticFieldUncalibrated);
        if (sOrientaion != null)
            sensorManager.unregisterListener(listener, sOrientaion);
        if (sProximity != null)
            sensorManager.unregisterListener(listener, sProximity);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()){
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    gameRotation(event);
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    geomagnetic(event);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    magnetic(event);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    magneticUncalibrated(event);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    orientation(event);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    proximity(event);
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.i(sensor.getName(), accuracy + "");
        }
    };

    private void orientation(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + " Degrees\ny : " + y + " Degrees\nz : " + z + " Degrees";
        tvOrientaion.setText(s);
    }

    private void proximity(SensorEvent event) {
        float d = event.values[0];

        String s = "Distance from object : " + d + " cm";
        tvProximity.setText(s);
    }

    private void magneticUncalibrated(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float x1 = event.values[3];
        float y1 = event.values[4];
        float z1 = event.values[5];

        String s = "x : " + x + " μT\ny : " + y + " μT\nz : " + z + " μT\nwithout hard iron calibration\n" + "x : " + x1 + " μT\ny : " + y1 + " μT\nz : " + z1 + " μT";
        tvMagneticFieldUncalibrated.setText(s);
    }

    private void magnetic(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + " μT\ny : " + y + " μT\nz : " + z + " μT";
        tvMagneticField.setText(s);
    }

    private void geomagnetic(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z;
        tvGeomagneticRotaion.setText(s);
    }

    private void gameRotation(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z;
        tvGameRotation.setText(s);
    }

}
