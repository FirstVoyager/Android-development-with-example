package limitless.android.androiddevelopment.Activity.UserInterface.Tablayout;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Tablayout.CustomTabLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Tablayout.HideToolbarTabLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Tablayout.SimpleTabLayoutActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class TablayoutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
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
        findViewById(R.id.button_7).setOnClickListener(this);
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
        Intent intent = new Intent(this, SimpleTabLayoutActivity.class);
        Bundle bundle =new Bundle();
        switch (v.getId()){
            case R.id.button_1:
                bundle.putInt(Intent.EXTRA_TEXT, 0);
                intent.putExtras(bundle);
                Tools.startActivity(this, intent);
                break;
            case R.id.button_2:
                bundle.putInt(Intent.EXTRA_TEXT, 1);
                intent.putExtras(bundle);
                Tools.startActivity(this, intent);
                break;
            case R.id.button_3:
                bundle.putInt(Intent.EXTRA_TEXT, 2);
                intent.putExtras(bundle);
                Tools.startActivity(this, intent);
                break;
            case R.id.button_4:
                bundle.putInt(Intent.EXTRA_TEXT, 3);
                intent.putExtras(bundle);
                Tools.startActivity(this, intent);
                break;
            case R.id.button_5:
                bundle.putInt(Intent.EXTRA_TEXT, 4);
                intent.putExtras(bundle);
                Tools.startActivity(this, intent);
                break;
            case R.id.button_6:
                Tools.startActivity(this, CustomTabLayoutActivity.class);
                break;
            case R.id.button_7:
                Tools.startActivity(this, HideToolbarTabLayoutActivity.class);
                break;
        }
    }
}
