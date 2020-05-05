package limitless.android.androiddevelopment.Activity.UserInterface.AppbarTop;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.UserMessageAdapter;
import limitless.android.androiddevelopment.Data.DataGenerator;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AppbarTopRegularActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private UserMessageAdapter userMessageAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_top_regular);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        userMessageAdapter = new UserMessageAdapter(this, DataGenerator.generateUser(12));
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userMessageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem search = menu.add(getString(R.string.text_search));
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        search.setIcon(R.drawable.ic_search_black_24dp);

        MenuItem wallpaper = menu.add(getString(R.string.text_wallpaper));
        wallpaper.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        wallpaper.setIcon(R.drawable.ic_wallpaper_black_24dp);

        MenuItem more = menu.add(getString(R.string.text_more));
        more.setIcon(R.drawable.ic_more_vert_black_24dp);
//        more.setOnMenuItemClickListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else {
            Tools.toast(this, item.getTitle().toString());
        }
        return super.onOptionsItemSelected(item);
    }
}
