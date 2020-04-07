package limitless.android.androiddevelopment.Activity.UIMore.Article;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;

public class ArticleCodingActivity extends BaseActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private MaterialButton btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_coding);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.progressbar);
        btnRun = findViewById(R.id.button_run);

        btnRun.setOnClickListener(this);
        progressBar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem search = menu.add("Search");
        search.setIcon(R.drawable.ic_search_black_24dp);

        MenuItem share = menu.add("Share");
        share.setIcon(R.drawable.ic_share_black_24dp);

        MenuItem open = menu.add("Open in...");
        open.setIcon(R.drawable.ic_open_in_new_black_24dp);

        MenuItem setting = menu.add("Setting");
        setting.setIcon(R.drawable.ic_settings_outline_24dp);

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
        if (v.getId() == R.id.button_run || v.getId() == R.id.progressbar){
            if (progressBar.getVisibility() == View.VISIBLE){
                progressBar.setVisibility(View.INVISIBLE);
                btnRun.setVisibility(View.VISIBLE);
            }else {
                progressBar.setVisibility(View.VISIBLE);
                btnRun.setVisibility(View.INVISIBLE);
            }
        }
    }
}
