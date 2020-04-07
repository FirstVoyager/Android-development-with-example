package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MenuItem;

public class CallReceiverActivity extends BaseActivity {

    private AppCompatTextView tvMessage;
    private static int CALL_REQUEST_CODE = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_receiver);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMessage = findViewById(R.id.textView_message);

        if (! Tools.permissionGranted(this, Manifest.permission.CALL_PHONE)){
            Tools.requestPermission(this, Manifest.permission.CALL_PHONE, CALL_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CALL_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Tools.customToast(this, "granted !");
            }else {
                Tools.customToast(this, "denied !");
                onBackPressed();
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

    public class CallReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle == null)
                return;
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            tvMessage.setText("State : ");
            tvMessage.append(state);
            tvMessage.append("\nNumber : ");
            tvMessage.append(number);
            tvMessage.append("\n" + bundle.toString());
        }
    }

}
