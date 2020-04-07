package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_info_outline_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.showInfoDialog(getSupportFragmentManager(), getString(R.string.text_basic_service), getString(R.string.info_service));
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvStatus = findViewById(R.id.textView_status);

        findViewById(R.id.button_start).setOnClickListener(this);
        findViewById(R.id.button_stop).setOnClickListener(this);

        if (Tools.serviceIsRunnig(this, SimpleService.class))
            tvStatus.setText(getString(R.string.text_service_is_running));
        else
            tvStatus.setText(getString(R.string.text_service_is_stopping));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_stop){
            if (! Tools.serviceIsRunnig(this, SimpleService.class))
                return;
            Intent intent = new Intent(this, SimpleService.class);
            stopService(intent);
            tvStatus.setText(getString(R.string.text_service_is_stopping));
        }else if (v.getId() == R.id.button_start){
            if (Tools.serviceIsRunnig(this, SimpleService.class))
                return;
            Intent intent = new Intent(this, SimpleService.class);
            startService(intent);
            tvStatus.setText(getString(R.string.text_service_is_running));
        }
    }

    public static class SimpleService extends Service{

        public SimpleService() {

        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            Tools.customToast(SimpleService.this, "Simple service start");
            super.onCreate();
        }

        @Override
        public void onDestroy() {
            Tools.customToast(SimpleService.this, "Simple service stop");
            super.onDestroy();
        }
    }

}
