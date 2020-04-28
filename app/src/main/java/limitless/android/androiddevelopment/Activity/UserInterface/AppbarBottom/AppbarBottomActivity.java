package limitless.android.androiddevelopment.Activity.UserInterface.AppbarBottom;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AppbarBottomActivity extends BaseActivity implements View.OnClickListener {

    // TODO: 12/24/19 add badge layout to bottom bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_bottom);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.button_1:
                startActivity(new Intent(this, AppbarBottomLabeledActivity.class));
                break;
            case R.id.button_2:
                startActivity(new Intent(this, AppbarBottomShiftingActivity.class));
                break;
            case R.id.button_3:
                startActivity(new Intent(this, AppbarBottomUnlabeledActivity.class));
                break;
            case R.id.button_4:
                startActivity(new Intent(this, AppbarBottomOverlappingFABActivity.class));
                break;
            case R.id.button_5:
                startActivity(new Intent(this, AppbarBottomInsetFABActivity.class));
                break;
            case R.id.button_6:
                startActivity(new Intent(this, AppbarBottomHideOnScrollActivity.class));
                break;
        }
    }


}
