package limitless.android.androiddevelopment.Activity.UIMore.Article;

import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class CircleAvatarActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_avatar);
        Tools.setSystemBarLight(this);
        init();
    }

    private void init() {
        findViewById(R.id.fab_back).setOnClickListener(this);
        findViewById(R.id.fab_share).setOnClickListener(this);
        findViewById(R.id.fab_favorite).setOnClickListener(this);
        findViewById(R.id.fab_twitter).setOnClickListener(this);
        findViewById(R.id.fab_facebook).setOnClickListener(this);
        findViewById(R.id.fab_googleplus).setOnClickListener(this);
        findViewById(R.id.fab_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = null;
        switch (v.getId()){
            case R.id.fab_back:
                finish();
                break;
            case R.id.fab_share:
                s = "Share your article";
                break;
            case R.id.fab_favorite:
                s = "Favorite";
                break;
            case R.id.fab_twitter:
                s = "Twitter";
                break;
            case R.id.fab_facebook:
                s = "Facebook";
                break;
            case R.id.fab_googleplus:
                s = "Google plus";
                break;
            case R.id.fab_send:
                s = "Send your comment";
                break;
        }
        Tools.toast(this, s);
    }
}
