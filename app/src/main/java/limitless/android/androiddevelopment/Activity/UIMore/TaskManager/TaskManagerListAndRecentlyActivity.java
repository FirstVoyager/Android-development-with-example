package limitless.android.androiddevelopment.Activity.UIMore.TaskManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

public class TaskManagerListAndRecentlyActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager_list_and_recently);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_black_24dp);
        DrawableCompat.setTint(drawable, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        findViewById(R.id.fab_add).setOnClickListener(this);
        findViewById(R.id.linearLayout_works).setOnClickListener(this);
        findViewById(R.id.linearLayout_music).setOnClickListener(this);
        findViewById(R.id.linearLayout_travel).setOnClickListener(this);
        findViewById(R.id.linearLayout_fitness).setOnClickListener(this);
        findViewById(R.id.linearLayout_foods).setOnClickListener(this);
        findViewById(R.id.linearLayout_develop).setOnClickListener(this);
        findViewById(R.id.linearLayout_london).setOnClickListener(this);
        findViewById(R.id.linearLayout_foods1).setOnClickListener(this);
        findViewById(R.id.linearLayout_develop1).setOnClickListener(this);
        findViewById(R.id.linearLayout_gitar1).setOnClickListener(this);
        findViewById(R.id.linearLayout_london1).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem search = menu.add("Search");
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        search.setIcon(R.drawable.ic_search_black_24dp);
        DrawableCompat.setTint(search.getIcon(), Color.WHITE);

        MenuItem account = menu.add("Account");
        account.setIcon(R.drawable.account_circle_outline);
        account.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        DrawableCompat.setTint(account.getIcon(), Color.WHITE);

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
        if (v.getId() == R.id.fab_add){
            Tools.toast(this, "Add Todo");
        }else {
            Tools.toast(this, "Item clicked");
        }
    }
}
