package limitless.android.androiddevelopment.Activity.UserInterface.AppbarTop;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterUserMessage;
import limitless.android.androiddevelopment.Data.DataGenerator;
import limitless.android.androiddevelopment.Interface.ObjectListener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AppbarTopActionModeActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AdapterUserMessage adapterUserMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_top_action_mode);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        adapterUserMessage = new AdapterUserMessage(this, DataGenerator.generateUser(15));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterUserMessage);
        adapterUserMessage.setItemClickListener(objectListener);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

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

    private ObjectListener objectListener = new ObjectListener() {
        @Override
        public void object(Object o) {
            startActionMode(callback);
        }
    };

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_adapter_song, menu);
            mode.setTitle(getString(R.string.text_action_mode));
            mode.setSubtitle(getString(R.string.text_subtitle));
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Tools.toast(AppbarTopActionModeActivity.this, item.getTitle().toString());
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

}
