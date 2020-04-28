package limitless.android.androiddevelopment.Activity.UserInterface.AppbarTop;

import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AppbarTopActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_top);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                startActivity(new Intent(this, AppbarTopRegularActivity.class));
                break;
            case R.id.button_2:
                startActivity(new Intent(this, AppbarTopActionModeActivity.class));
                break;
            case R.id.button_3:
                startActivity(new Intent(this, AppbarTopCollapseImageActivity.class));
                break;
        }
    }
}
