package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.BluetoothUtil;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class BluetoothActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private BluetoothUtil bluetoothUtil;
    private SwitchMaterial sBluetooth;
    private MaterialTextView tvDevices, tvPaired;
    private TextInputEditText etName;
    private List<BluetoothDevice> deviceList = new ArrayList<>();
    private int location = 1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        init();
    }

    private void init() {
        bluetoothUtil = new BluetoothUtil(this);
        sBluetooth = findViewById(R.id.switch_bluetooth);
        tvDevices = findViewById(R.id.textView_devices);
        etName = findViewById(R.id.editText_name);
        tvPaired = findViewById(R.id.textView_paired);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvPaired.setOnClickListener(this);
        sBluetooth.setChecked(bluetoothUtil.isEnabled());
        sBluetooth.setOnCheckedChangeListener(this);
        tvDevices.setOnClickListener(this);
        etName.setText(bluetoothUtil.getName());
        findViewById(R.id.button_change).setOnClickListener(this);
        if (! bluetoothUtil.supportBluetooth()){
            Tools.toast(this, getString(R.string.dont_support_bluetooth));
            finish();
        }
        if (! Tools.permissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Tools.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, location);
        }
    }

    private void findDevices(final ProgressDialog progressDialog) {
        bluetoothUtil.startScan(new BluetoothUtil.BluetoothListener() {
            @Override
            public void found(BluetoothDevice device) {
                deviceList.add(device);
            }

            @Override
            public void started() {

            }

            @Override
            public void finished() {
                progressDialog.dismiss();
                if (deviceList.size() <= 0){
                    Tools.toast(BluetoothActivity.this, getString(R.string.not_found));
                    return;
                }
                String[] names = new String[deviceList.size()];
                for (int i = 0; i < deviceList.size(); i++) {
                    names[i] = deviceList.get(i).getName();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothActivity.this);
                builder.setTitle(R.string.select_device);
                builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                Tools.toast(BluetoothActivity.this, BluetoothActivity.this.getString(R.string.finished));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == location){
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                Tools.toast(this, getString(R.string.permission_denied));
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothUtil.onDestroy();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        bluetoothUtil.setBluetooth(isChecked);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView_devices){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.searching));
            progressDialog.show();
            progressDialog.setCancelable(false);
            findDevices(progressDialog);
        }else if (v.getId() == R.id.button_change){
            if (Tools.isEmpty(etName.getText().toString())){
                etName.setError(getString(R.string.error));
            }else {
                if (bluetoothUtil.setName(etName.getText().toString().trim())){
                    Tools.toast(this, getString(R.string.successfully));
                }else {
                    Tools.toast(this, getString(R.string.failed));
                }
            }
        }else if (v.getId() == R.id.textView_paired){
            if (! bluetoothUtil.isEnabled()){
                Tools.toast(this, getString(R.string.bluetooth_is_off));
                return;
            }
            List<BluetoothDevice> list = bluetoothUtil.getPairDevices();
            if (list.size() <= 0){
                Tools.toast(this, getString(R.string.no_paired_devices));
            }else {
                StringBuilder sb = new StringBuilder();
                for (BluetoothDevice bd : list) {
                    sb
                            .append("Name : ")
                            .append(bd.getName())
                            .append(System.lineSeparator())
                            .append("Address : ")
                            .append(bd.getAddress())
                            .append(System.lineSeparator())
                            .append("--------")
                            .append(System.lineSeparator());
                }
                Tools.showInfoDialog(
                        getSupportFragmentManager(),
                        getString(R.string.paired_devices),
                        sb.toString()
                );
            }
        }
    }

}
