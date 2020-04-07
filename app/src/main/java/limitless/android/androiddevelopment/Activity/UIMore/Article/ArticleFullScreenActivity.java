package limitless.android.androiddevelopment.Activity.UIMore.Article;

import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.View;

public class ArticleFullScreenActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_full_screen);
        init();
    }

    private void init() {
        findViewById(R.id.imageButton_back).setOnClickListener(this);
        findViewById(R.id.imageButton_bookmark).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton_back){
            finish();
        }else if (v.getId() == R.id.imageButton_bookmark){
            Tools.toast(this, "Bookmark");
        }
    }
}
