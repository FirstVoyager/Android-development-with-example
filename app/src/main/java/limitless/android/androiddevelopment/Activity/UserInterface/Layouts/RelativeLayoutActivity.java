package limitless.android.androiddevelopment.Activity.UserInterface.Layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.SimpleTextAdapter;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RelativeLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = findViewById(R.id.recyclerView);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("Simple text " + i);
        }
        rv.setAdapter(new SimpleTextAdapter(this, strings));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_about));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_info_outline_white_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle() != null && item.getTitle().equals(getString(R.string.title_about))){
            Tools.infoDialog(getSupportFragmentManager(), getString(R.string.t_relative_layout), getString(R.string.info_relative_layout));
        }
        return super.onOptionsItemSelected(item);
    }
}
