package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.AnimationManager;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;

public class AnimationRotateActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView ivAnim;
    private int duration = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_rotate);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_90).setOnClickListener(this);
        findViewById(R.id.button_180).setOnClickListener(this);
        findViewById(R.id.button_360).setOnClickListener(this);
        findViewById(R.id.button_180b).setOnClickListener(this);
        ivAnim = findViewById(R.id.imageView_animation);
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
            case R.id.button_90:
                AnimationManager.rotate(ivAnim, 90, duration);
                break;
            case R.id.button_180:
                AnimationManager.rotate(ivAnim, 180, duration);
                break;
            case R.id.button_360:
                AnimationManager.rotate(ivAnim, 360, duration);
                break;
            case R.id.button_180b:
                AnimationManager.rotate(ivAnim, -180, duration);
                break;
        }
    }
}
