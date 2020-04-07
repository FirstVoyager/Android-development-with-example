package limitless.android.androiddevelopment.Activity.Basic;

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
import android.view.MenuItem;

public class EnvironmentActivity extends BaseActivity {

    private SensorManager sensorManager;
    private AppCompatTextView tvAmbient, tvLight, tvPressure, tvRelative, tvTemperature;
    private Sensor sAmbient, sLight, sPressure, sRelative, sTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tvAmbient = findViewById(R.id.textView_ambient);
        tvLight = findViewById(R.id.textView_light);
        tvPressure = findViewById(R.id.textView_pressure);
        tvRelative = findViewById(R.id.textView_relativeHumidity);
        tvTemperature = findViewById(R.id.textView_temperature);

        sAmbient = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sAmbient == null)
            tvAmbient.setText(getString(R.string.text_not_found));
        sLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sLight == null)
            tvLight.setText(getString(R.string.text_not_found));
        sPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (sPressure == null)
            tvPressure.setText(getString(R.string.text_not_found));
        sRelative = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (sRelative == null)
            tvRelative.setText(getString(R.string.text_not_found));
        sTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        if (sTemperature == null)
            tvTemperature.setText(getString(R.string.text_not_found));
    }

    @Override
    protected void onStart() {
        super.onStart();
        int delay = SensorManager.SENSOR_DELAY_NORMAL;
        if (sAmbient != null)
            sensorManager.registerListener(listener, sAmbient, delay);
        if (sLight != null)
            sensorManager.registerListener(listener, sLight, delay);
        if (sPressure != null)
            sensorManager.registerListener(listener, sPressure, delay);
        if (sRelative != null)
            sensorManager.registerListener(listener, sRelative, delay);
        if (sTemperature != null)
            sensorManager.registerListener(listener, sTemperature, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sAmbient != null)
            sensorManager.unregisterListener(listener, sAmbient);
        if (sLight != null)
            sensorManager.unregisterListener(listener, sLight);
        if (sPressure != null)
            sensorManager.unregisterListener(listener, sPressure);
        if (sRelative != null)
            sensorManager.unregisterListener(listener, sRelative);
        if (sTemperature != null)
            sensorManager.unregisterListener(listener, sTemperature);
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
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    ambient(event);
                    break;
                case Sensor.TYPE_LIGHT:
                    light(event);
                    break;
                case Sensor.TYPE_PRESSURE:
                    pressure(event);
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    relative(event);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    temperature(event);
                    break;
            }
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void temperature(SensorEvent event) {
        String s = "value : " + event.values[0] + " °C";
        tvTemperature.setText(s);
    }

    private void relative(SensorEvent event) {
        String s = "value : " + event.values[0] + " %";
        tvRelative.setText(s);
    }

    private void pressure(SensorEvent event) {
        String s = "value : " + event.values[0] + " hPa or mbar";
        tvPressure.setText(s);
    }

    private void light(SensorEvent event) {
        String s = "value : " + event.values[0] + " lx";
        tvLight.setText(s);
    }

    private void ambient(SensorEvent event) {
        String s = "value : " + event.values[0] + " °C";
        tvAmbient.setText(s);
    }

}
