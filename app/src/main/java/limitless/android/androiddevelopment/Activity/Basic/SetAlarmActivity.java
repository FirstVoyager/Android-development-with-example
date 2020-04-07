package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SetAlarmActivity extends BaseActivity implements View.OnClickListener {

    public static final String ACTION_ALARM = "action_alarm";
    public static final int ALARM_REQUEST_CODE = 1002;
    private AppCompatEditText etSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etSecond = findViewById(R.id.editText_second);
        findViewById(R.id.button_cancel).setOnClickListener(this);
        findViewById(R.id.button_start).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_start){
            if (Tools.isEmpty(etSecond.getText().toString())){
                etSecond.setError(getString(R.string.t_empty));
                return;
            }
            setAlarm();
        }else if (v.getId() == R.id.button_cancel){
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            assert am != null;
            Intent intent = new Intent(this, SimpleAlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, intent, 0);
            am.cancel(pi);
        }
    }

    private void setAlarm() {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert am != null;
        Intent intent = new Intent(this, SimpleAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (Integer.valueOf(etSecond.getText().toString().trim()) * 1000), pi);
        Tools.customToast(this, String.format(getString(R.string._set_alarm), etSecond.getText().toString()));

    }

    public static class SimpleAlarmReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Tools.customToast(context, "WoooW alarm :)");
        }
    }

}
