package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterUserMessage;
import limitless.android.androiddevelopment.Data.DataGenerator;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AppbarBottomOverlappingFABActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private AdapterUserMessage adapterUserMessage;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_bottom_overlapping_fab);
        init();
    }

    private void init() {
        drawerLayout = findViewById(R.id.drawerLayout);
        recyclerView = findViewById(R.id.recyclerView);
        adapterUserMessage = new AdapterUserMessage(this, DataGenerator.generateUser(10));
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterUserMessage);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
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
