package limitless.android.androiddevelopment.Activity.UserInterface.AppbarBottom;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.UserMessageAdapter;
import limitless.android.androiddevelopment.BottomSheet.SelectBottomSheet;
import limitless.android.androiddevelopment.Data.DataGenerator;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppbarBottomHideOnScrollActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    private UserMessageAdapter userMessageAdapter;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private int storageCode = 2301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_bottom_hide_on_scroll);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        userMessageAdapter = new UserMessageAdapter(this, DataGenerator.generateUser(15));
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userMessageAdapter);
        bottomAppBar.setNavigationOnClickListener(this);
        bottomAppBar.setOnMenuItemClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab){
            Tools.toast(this, "Floating action button");
            return;
        }
        if (Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            SelectBottomSheet sheet = new SelectBottomSheet(null, 0);
            sheet.show(getSupportFragmentManager(), null);
        }else {
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, storageCode);
        }
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
                SelectBottomSheet sheet = new SelectBottomSheet(null, 0);
                sheet.show(getSupportFragmentManager(), null);
            }else {
                Tools.toast(this, "permission denied !");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_exit){
            finish();
        }
        Tools.toast(this, item.getTitle().toString());
        return true;
    }
}
