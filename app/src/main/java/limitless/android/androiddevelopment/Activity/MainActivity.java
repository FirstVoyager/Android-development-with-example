package limitless.android.androiddevelopment.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import limitless.android.androiddevelopment.Activity.CodeMore.AnimationFadeActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AnimationRotateActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AnimationSlideActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AnimationZoomActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AudioManagerActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AudioRecorderActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.BluetoothActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.MediaPlayerActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.TextToSpeechActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Music.MusicSimpleActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerChartViewActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerNewActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.ChipsActivity;
import limitless.android.androiddevelopment.Fragment.MainFragment;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MainFragment componentsFragment, userInterfaceFragment, uiMoreFragment, codeMoreFragment;
    private int lastFragment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        startActivity(new Intent(this, BluetoothActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void init() {
        drawerLayout = findViewById(R.id.drawerLayout_main);
        navigationView = findViewById(R.id.navigation_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView_main);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        componentsFragment = new MainFragment();
        userInterfaceFragment = new MainFragment();
        uiMoreFragment = new MainFragment();
        codeMoreFragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MainFragment.fragmentId, R.id.menu_main_basic);
        componentsFragment.setArguments(bundle);
        bundle = new Bundle();
        bundle.putInt(MainFragment.fragmentId, R.id.menu_main_ui);
        userInterfaceFragment.setArguments(bundle);
        bundle = new Bundle();
        bundle.putInt(MainFragment.fragmentId, R.id.menu_main_ui_more);
        uiMoreFragment.setArguments(bundle);
        bundle = new Bundle();
        bundle.putInt(MainFragment.fragmentId, R.id.menu_main_code_more);
        codeMoreFragment.setArguments(bundle);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setFragment(componentsFragment, R.id.menu_main_basic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem code = menu.add("Source code");
        code.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        code.setIcon(R.drawable.ic_code_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.menu_nav_setting:

                Tools.closeDrawer(drawerLayout);
                return true;
            case R.id.menu_nav_about:

                Tools.closeDrawer(drawerLayout);
                return true;
            case R.id.menu_nav_share:

                Tools.closeDrawer(drawerLayout);
                return true;
            case R.id.menu_nav_rate:

                Tools.closeDrawer(drawerLayout);
                return true;
            case R.id.menu_nav_other_apps:

                Tools.closeDrawer(drawerLayout);
                return true;
            case R.id.menu_main_basic:
                setFragment(componentsFragment, id);
                return true;
            case R.id.menu_main_ui:
                setFragment(userInterfaceFragment, id);
                return true;
            case R.id.menu_main_ui_more:
                setFragment(uiMoreFragment, id);
                return true;
            case R.id.menu_main_code_more:
                setFragment(codeMoreFragment, id);
                return true;
        }
        return false;
    }

    private void setFragment(Fragment fragment, int id) {
        if (fragment == null)
            return;
        if (lastFragment == id)
            return;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded())
            ft.show(fragment);
        else
            ft.add(R.id.frameLayout_main, fragment);
        Tools.fragmentCommit(ft);
        ft = getSupportFragmentManager().beginTransaction();
        switch (lastFragment){
            case R.id.menu_main_basic:
                Tools.fragmentCommit(ft.hide(componentsFragment));
                break;
            case R.id.menu_main_ui:
                Tools.fragmentCommit(ft.hide(userInterfaceFragment));
                break;
            case R.id.menu_main_ui_more:
                Tools.fragmentCommit(ft.hide(uiMoreFragment));
                break;
            case R.id.menu_main_code_more:
                Tools.fragmentCommit(ft.hide(codeMoreFragment));
                break;

        }
        lastFragment = id;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
