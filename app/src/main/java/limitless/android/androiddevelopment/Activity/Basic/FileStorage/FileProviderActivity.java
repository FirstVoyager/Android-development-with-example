package limitless.android.androiddevelopment.Activity.Basic.FileStorage;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.BottomSheet.SelectBottomSheet;
import limitless.android.androiddevelopment.Interface.Listener;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;

public class FileProviderActivity extends BaseActivity implements View.OnClickListener {

    private int requestStorage = 5001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_photo).setOnClickListener(this);
        findViewById(R.id.button_audio).setOnClickListener(this);
        findViewById(R.id.button_video).setOnClickListener(this);
        findViewById(R.id.button_file).setOnClickListener(this); // add in next version
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_info_outline_white_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.infoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.text_file_provider),
                    getString(R.string.info_file_provider)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == requestStorage){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Tools.toast(this, "Try again !");
            }else {
                Tools.toast(this, "Storage permission denied !");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_photo){
            select(0);
        }else if (v.getId() == R.id.button_audio){
            select(1);
        }else if (v.getId() == R.id.button_video){
            select(2);
        }else if (v.getId() == R.id.button_file){
            select(3);
        }
    }

    private void select(int i) {
        if (! Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, requestStorage);
            return;
        }
        SelectBottomSheet sheet = new SelectBottomSheet(new Listener<Uri>() {
            @Override
            public void data(Uri uri) {
                String s = uri.getPath();
                Tools.log(s);
                String type;
                if (s == null)
                    type = "*/*";
                else if (s.contains("/images/media/"))
                    type = "image/*";
                else if (s.contains("/audio/media/"))
                    type = "audio/*";
                else if (s.contains("/video/media/"))
                    type = "video/*";
                else
                    type = "*/*";

//                Uri data = FileProvider.getUriForFile(FileProviderActivity.this, "limitless.android.fileprovider", new File(s));
                grantUriPermission(getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setDataAndType(uri, type)
                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            }
        }, i);
        sheet.show(getSupportFragmentManager(), null);
    }
}
