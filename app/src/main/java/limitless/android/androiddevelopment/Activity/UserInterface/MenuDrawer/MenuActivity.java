package limitless.android.androiddevelopment.Activity.UserInterface.MenuDrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.button_1:
                popup(v);
                break;
            case R.id.button_2:
                popupIcon(v);
                break;
            case R.id.button_3:
                Tools.startActivity(this, SimpleDrawerActivity.class);
                break;
            case R.id.button_4:
                Tools.startActivity(this, DrawerGmailActivity.class);
                break;
            case R.id.button_5:
                Tools.startActivity(this, DrawerAdminActivity.class);
                break;
        }
    }

    @SuppressLint("RestrictedApi")
    private void popupIcon(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_adapter_song);
        MenuPopupHelper helper = new MenuPopupHelper(this, (MenuBuilder) popupMenu.getMenu(), v);
        helper.setForceShowIcon(true);
        helper.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Tools.toast(MenuActivity.this, item.getTitle().toString());
                return true;
            }
        });
    }

    private void popup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_adapter_song);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Tools.toast(MenuActivity.this, item.getTitle().toString());
                return true;
            }
        });
    }
}
