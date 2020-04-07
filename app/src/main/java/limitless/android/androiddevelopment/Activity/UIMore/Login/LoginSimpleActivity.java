package limitless.android.androiddevelopment.Activity.UIMore.Login;

import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;

public class LoginSimpleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_simple);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
        init();
    }

    private void init() {

    }
}
