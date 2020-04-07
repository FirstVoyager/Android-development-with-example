package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleListItem;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private static final int simpleRequestCode = 10001;
    private String[] normarlPermissions = new String[]{
            "ACCESS_LOCATION_EXTRA_COMMANDS",
            "ACCESS_NETWORK_STATE",
            "ACCESS_NOTIFICATION_POLICY",
            "ACCESS_WIFI_STATE",
            "BLUETOOTH",
            "BLUETOOTH_ADMIN",
            "BROADCAST_STICKY",
            "CHANGE_NETWORK_STATE",
            "CHANGE_WIFI_MULTICAST_STATE",
            "CHANGE_WIFI_STATE",
            "DISABLE_KEYGUARD",
            "EXPAND_STATUS_BAR",
            "GET_PACKAGE_SIZE",
            "INSTALL_SHORTCUT",
            "INTERNET",
            "KILL_BACKGROUND_PROCESSES",
            "MODIFY_AUDIO_SETTINGS",
            "NFC",
            "READ_SYNC_SETTINGS",
            "READ_SYNC_STATS",
            "RECEIVE_BOOT_COMPLETED",
            "REORDER_TASKS",
            "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS",
            "REQUEST_INSTALL_PACKAGES",
            "SET_ALARM",
            "SET_TIME_ZONE",
            "SET_WALLPAPER",
            "SET_WALLPAPER_HINTS",
            "TRANSMIT_IR",
            "UNINSTALL_SHORTCUT",
            "USE_FINGERPRINT",
            "VIBRATE",
            "WAKE_LOCK",
            "WRITE_SYNC_SETTINGS"
    };
    private String[] dangerousePermissions = new String[]{
            "READ_CALENDAR",
            "WRITE_CALENDAR",
            "CAMERA",
            "READ_CONTACTS",
            "WRITE_CONTACTS",
            "GET_ACCOUNTS",
            "ACCESS_FINE_LOCATION",
            "ACCESS_COARSE_LOCATION",
            "RECORD_AUDIO",
            "READ_PHONE_STATE",
            "READ_PHONE_NUMBERS",
            "CALL_PHONE",
//            "ANSWER_PHONE_CALLS",
            "READ_CALL_LOG",
            "WRITE_CALL_LOG",
//            "ADD_VOICEMAIL",
            "USE_SIP",
            "PROCESS_OUTGOING_CALLS",
            "BODY_SENSORS",
            "SEND_SMS",
            "RECEIVE_SMS",
            "READ_SMS",
            "RECEIVE_WAP_PUSH",
            "RECEIVE_MMS",
            "READ_EXTERNAL_STORAGE",
            "WRITE_EXTERNAL_STORAGE"
    };
    private RecyclerView rvDangerous, rvNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        init();
    }

    private void init(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fab);
        rvDangerous = findViewById(R.id.recyclerView_dangerous);
        rvNormal = findViewById(R.id.recyclerView_normal);

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(dangerousePermissions));
        rvDangerous.setLayoutManager(new LinearLayoutManager(this));
        rvNormal.setLayoutManager(new LinearLayoutManager(this));
        rvDangerous.setAdapter(new AdapterSimpleListItem(this, list, dangerousListener));
        list = new ArrayList<>();
        list.addAll(Arrays.asList(normarlPermissions));
        rvNormal.setAdapter(new AdapterSimpleListItem(this, list, normalListener));
        fab.setOnClickListener(this);
        findViewById(R.id.ibtn_infoDangerous).setOnClickListener(this);
        findViewById(R.id.ibtn_infoNormal).setOnClickListener(this);
    }

    private AdapterSimpleListItem.StringListener dangerousListener = new AdapterSimpleListItem.StringListener() {
        @Override
        public void string(String s) {
            getPermission(s);
        }
    };
    private AdapterSimpleListItem.StringListener normalListener = new AdapterSimpleListItem.StringListener() {
        @Override
        public void string(String s) {
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    s, getString(R.string.info_normal_permissions_how_add) + "\n\n" + "android.permission." + s
            );
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem info = menu.add(getString(R.string.title_info));
        info.setIcon(R.drawable.ic_info_outline_black_24dp);
        info.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.title_permissions),
                    getString(R.string.info_permissions)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == simpleRequestCode){
            String e;
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                e = " granted !";
            else
                e = " denied !";
            String p = permissions[0];
            p = p.substring(p.lastIndexOf(".") + 1);
            Tools.customToast(this, p + e);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            Tools.openUrl(this, getString(R.string.codecanyon_url));
        }else if (v.getId() == R.id.ibtn_infoDangerous){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.title_dangerous_permissions),
                    getString(R.string.info_dangerous_permissions)
            );
        }else if (v.getId() == R.id.ibtn_infoNormal){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.title_normal_permissions),
                    getString(R.string.info_normal_permissions)
            );
        }
    }


    private void getPermission(String p) {
        if (Tools.permissionGranted(this, "android.permission." + p)){
            Tools.customToast(this, p + " Granted!");
        }else {
            Tools.requestPermission(this, "android.permission." + p, simpleRequestCode);
        }
    }
}
