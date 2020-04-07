package limitless.android.androiddevelopment.Activity.UIMore.Article;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ArticleShortTextActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_short_text);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.imageButton_share).setOnClickListener(this);
        findViewById(R.id.imageButton_bookmark).setOnClickListener(this);
        findViewById(R.id.imageButton_favorite).setOnClickListener(this);
        findViewById(R.id.imageButton_reply).setOnClickListener(this);
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
        String msg = null;
        switch (v.getId()){
            case R.id.imageButton_share:
                msg = "Share";
                break;
            case R.id.imageButton_bookmark:
                msg = "Bookmark";
                break;
            case R.id.imageButton_favorite:
                msg = "Favorite";
                break;
            case R.id.imageButton_reply:
                msg = "Reply";
                break;
        }
        Tools.toast(this, msg);
    }
}
