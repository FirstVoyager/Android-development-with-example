package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.BottomSheet.FloatingBottomSheet;
import limitless.android.androiddevelopment.BottomSheet.FullScreenBotttomSheer;
import limitless.android.androiddevelopment.BottomSheet.SelectBottomSheet;
import limitless.android.androiddevelopment.BottomSheet.SimpleBottomSheet;
import limitless.android.androiddevelopment.BottomSheet.SimpleListBottomSheet;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class BottomSheetActivity extends BaseActivity implements View.OnClickListener {

    private int storageCode = 21321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storageCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                new SelectBottomSheet(null, 0).show(getSupportFragmentManager(), null);
            }else {
                Tools.toast(this, "Permission denied");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                new SimpleBottomSheet().show(getSupportFragmentManager(), null);
                break;
            case R.id.button_2:
                new SimpleListBottomSheet().show(getSupportFragmentManager(), null);
                break;
            case R.id.button_3:
                new FullScreenBotttomSheer().show(getSupportFragmentManager(), null);
                break;
            case R.id.button_4:
                new FloatingBottomSheet().show(getSupportFragmentManager(), null);
                break;
            case R.id.button_5:
                if (Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    new SelectBottomSheet(null, 0).show(getSupportFragmentManager(), null);
                }else {
                    Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, storageCode);
                }
                break;
        }
    }
}
