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
import android.view.MenuItem;

public class MotionActivity extends BaseActivity {

    private SensorManager sensorManager;
    private AppCompatTextView tvAccelerometer, tvAccelerometerUncalibrated, tvGravity,
            tvGyroscope, tvGyroscopeUncalibrated, tvLinearAcceleration,
            tvRotationVector, tvStepCounter;
    private Sensor sAccelerometer, sAccelerometerUncalibrated, sGravity,
            sGyroscope, sGyroscopeUncalibrated, sLinearAcceleration,
            sRotationVector, sStepCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tvAccelerometer = findViewById(R.id.textView_accelerometer);
        tvAccelerometerUncalibrated = findViewById(R.id.textView_accelerometerUncalibrated);
        tvGravity = findViewById(R.id.textView_gravity);
        tvGyroscope = findViewById(R.id.textView_gyroscope);
        tvGyroscopeUncalibrated = findViewById(R.id.textView_gyroscopeUncalibrated);
        tvLinearAcceleration = findViewById(R.id.textView_linearAcceleration);
        tvRotationVector = findViewById(R.id.textView_rotationVector);
        tvStepCounter = findViewById(R.id.textView_stepCounter);

        sAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sAccelerometer == null)
            tvAccelerometer.setText(getString(R.string.text_not_found));
        sAccelerometerUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED);
        if (sAccelerometerUncalibrated == null)
            tvAccelerometerUncalibrated.setText(getString(R.string.text_not_found));
        sGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (sGravity == null)
            tvGravity.setText(getString(R.string.text_not_found));
        sGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sGyroscope == null)
            tvGyroscope.setText(getString(R.string.text_not_found));
        sGyroscopeUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        if (sGyroscopeUncalibrated == null)
            tvGyroscopeUncalibrated.setText(getString(R.string.text_not_found));
        sLinearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (sLinearAcceleration == null)
            tvLinearAcceleration.setText(getString(R.string.text_not_found));
        sRotationVector = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (sRotationVector == null)
            tvRotationVector.setText(getString(R.string.text_not_found));
        sStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sStepCounter == null)
            tvStepCounter.setText(getString(R.string.text_not_found));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sAccelerometer != null)
            sensorManager.registerListener(listener, sAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        if (sAccelerometerUncalibrated != null)
            sensorManager.registerListener(listener, sAccelerometerUncalibrated, SensorManager.SENSOR_DELAY_NORMAL);
        if (sGravity != null)
            sensorManager.registerListener(listener, sGravity, SensorManager.SENSOR_DELAY_NORMAL);
        if (sGyroscope != null)
            sensorManager.registerListener(listener, sGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        if (sGyroscopeUncalibrated != null)
            sensorManager.registerListener(listener, sGyroscopeUncalibrated, SensorManager.SENSOR_DELAY_NORMAL);
        if (sLinearAcceleration != null)
            sensorManager.registerListener(listener, sLinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
        if (sRotationVector != null)
            sensorManager.registerListener(listener, sRotationVector, SensorManager.SENSOR_DELAY_NORMAL);
        if (sStepCounter != null)
            sensorManager.registerListener(listener, sStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sAccelerometer != null)
            sensorManager.unregisterListener(listener, sAccelerometer);
        if (sAccelerometerUncalibrated != null)
            sensorManager.unregisterListener(listener, sAccelerometerUncalibrated);
        if (sGravity != null)
            sensorManager.unregisterListener(listener, sGravity);
        if (sGyroscope != null)
            sensorManager.unregisterListener(listener, sGyroscope);
        if (sGyroscopeUncalibrated != null)
            sensorManager.unregisterListener(listener, sGyroscopeUncalibrated);
        if (sLinearAcceleration != null)
            sensorManager.unregisterListener(listener, sLinearAcceleration);
        if (sRotationVector != null)
            sensorManager.unregisterListener(listener, sRotationVector);
        if (sStepCounter != null)
            sensorManager.unregisterListener(listener, sStepCounter);
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
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                accelerometer(event);
            }else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER_UNCALIBRATED){
                accelerometerUncalibrated(event);
            }else if (event.sensor.getType() == Sensor.TYPE_GRAVITY){
                gravity(event);
            }else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                gyroscope(event);
            }else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
                linearAcceleration(event);
            }else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
                rotationVector(event);
            }else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
                stepCounter(event);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void stepCounter(SensorEvent event) {
        float n = event.values[0];

        String s = "steps : " + n;
        tvStepCounter.setText(s);
    }

    private void rotationVector(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float rotation = event.values[3];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z + "\nrotation : " + rotation;
        tvRotationVector.setText(s);
    }

    private void linearAcceleration(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z;
        tvLinearAcceleration.setText(s);
    }

    private void gyroscopeUncalibrated(SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float x1 = event.values[3];
        float y1 = event.values[4];
        float z1 = event.values[5];

        String s = "\nx : " + x1 + "\ny" + y1 + "\nz : " + z1 +
                "x : " + x + "\ny : " + y + "\nz : " + z +
                        "without drift compensation\n";
        tvGyroscopeUncalibrated.setText(s);
    }

    private void gyroscope(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z;
        tvGyroscope.setText(s);
    }

    private void gravity(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z;
        tvGravity.setText(s);
    }

    private void accelerometerUncalibrated(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float x1 = event.values[3];
        float y1 = event.values[4];
        float z1 = event.values[5];

        String s = "x : " + x + "\ny : " + y + "\nz : " + z + "\nwith estimated bias compensation\n" + "x : " + x1 + "\ny : " + y1 + "\nz : " + z1;
        tvAccelerometerUncalibrated.setText(s);
    }

    private void accelerometer(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        String s = "x axis : " + x + "\ny axis : " + y + "\nz axis : " + z;
        tvAccelerometer.setText(s);
    }

}
