package limitless.android.androiddevelopment.Activity.UserInterface.Layouts;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleText;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class FrameLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rv = findViewById(R.id.recyclerView);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("Simple text " + i);
        }
        rv.setAdapter(new AdapterSimpleText(this, strings));
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
            Tools.infoDialog(getSupportFragmentManager(), getString(R.string.t_frame_layout), getString(R.string.info_frame_layout));
        }
        return super.onOptionsItemSelected(item);
    }
}
