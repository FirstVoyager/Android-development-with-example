package limitless.android.androiddevelopment.Activity.Basic.BroadCast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Data.SharePref;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Date;

public class SMSReceiverActivity extends BaseActivity {

    private static boolean isRunnig = false;
    private static AppCompatTextView tvMessage;
    private static final int receiver_sms = 3001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsreceiver);
        init();
        Tools.serviceIsRunning(this, SMSReceiverActivity.class);
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvMessage = findViewById(R.id.textView_message);
        tvMessage.setText(new SharePref(this).get(SharePref.LAST_SMS_RECEIVED_DATE, "last date"));
        if (! Tools.permissionGranted(this, Manifest.permission.RECEIVE_MMS)){
            Tools.requestPermission(this, Manifest.permission.RECEIVE_SMS, receiver_sms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == receiver_sms){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Tools.customToast(this, "granted !");
            }else {
                Tools.customToast(this, "denied !");
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunnig = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunnig = false;
    }

    public static class SMSReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String date = Tools.convertDate(new Date().getTime());
            new SharePref(context).put(SharePref.LAST_SMS_RECEIVED_DATE, date);
            if (isRunnig){
                tvMessage.setText(date);
            }
        }
    }

}
