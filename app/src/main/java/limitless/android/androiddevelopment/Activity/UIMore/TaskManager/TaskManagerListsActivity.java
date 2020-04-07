package limitless.android.androiddevelopment.Activity.UIMore.TaskManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TaskManagerListsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager_lists);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Tools.setSystemBarLight(this);

        findViewById(R.id.linearLayout_works).setOnClickListener(this);
        findViewById(R.id.linearLayout_music).setOnClickListener(this);
        findViewById(R.id.linearLayout_travel).setOnClickListener(this);
        findViewById(R.id.linearLayout_fitness).setOnClickListener(this);
        findViewById(R.id.linearLayout_money).setOnClickListener(this);
        findViewById(R.id.linearLayout_friends).setOnClickListener(this);
        findViewById(R.id.linearLayout_security).setOnClickListener(this);
        findViewById(R.id.linearLayout_foods).setOnClickListener(this);
        findViewById(R.id.linearLayout_school).setOnClickListener(this);
        findViewById(R.id.linearLayout_books).setOnClickListener(this);
        findViewById(R.id.linearLayout_shopping).setOnClickListener(this);
        findViewById(R.id.linearLayout_coding).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem view = menu.add("View");
        view.setIcon(R.drawable.ic_view_compact_black_24dp);
        view.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem search = menu.add("Search");
        search.setIcon(R.drawable.ic_search_black_24dp);
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
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

    }
}
