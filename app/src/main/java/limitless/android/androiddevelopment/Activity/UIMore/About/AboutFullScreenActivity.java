package limitless.android.androiddevelopment.Activity.UIMore.About;

import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.View;

public class AboutFullScreenActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_full_screen);
        init();
    }

    private void init() {
        findViewById(R.id.imageButton_close).setOnClickListener(this);
        findViewById(R.id.linearLayout_instagram).setOnClickListener(this);
        findViewById(R.id.linearLayout_googlePlus).setOnClickListener(this);
        findViewById(R.id.linearLayout_twitter).setOnClickListener(this);
        findViewById(R.id.linearLayout_facebook).setOnClickListener(this);
        findViewById(R.id.linearLayout_telegram).setOnClickListener(this);
        findViewById(R.id.linearLayout_website).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String msg = null;
        switch (v.getId()){
            case R.id.imageButton_close:
                finish();
                break;
            case R.id.linearLayout_instagram:
                msg = "Instagram";
                break;
            case R.id.linearLayout_googlePlus:
                msg = "Google plus";
                break;
            case R.id.linearLayout_twitter:
                msg = "Twitter";
                break;
            case R.id.linearLayout_facebook:
                msg = "Facebook";
                break;
            case R.id.linearLayout_telegram:
                msg = "Telegram";
                break;
            case R.id.linearLayout_website:
                msg = "Website";
                break;
        }
        if (msg != null){
            Tools.toast(this, msg + " Clicked");
        }
    }
}
