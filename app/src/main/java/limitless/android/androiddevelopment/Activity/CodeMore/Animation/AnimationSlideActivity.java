package limitless.android.androiddevelopment.Activity.CodeMore.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.AnimationManager;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AnimationSlideActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView ivAnim;
    private int duration = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_slide);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivAnim = findViewById(R.id.imageView_animation);
        findViewById(R.id.button_slideInTop).setOnClickListener(this);
        findViewById(R.id.button_slideInBottom).setOnClickListener(this);
        findViewById(R.id.button_slideOutTop).setOnClickListener(this);
        findViewById(R.id.button_slideOutBottom).setOnClickListener(this);
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
            case R.id.button_slideInTop:
                AnimationManager.slideInTop(ivAnim, duration);
                break;
            case R.id.button_slideInBottom:
                AnimationManager.slideInBottom(ivAnim, duration);
                break;
            case R.id.button_slideOutTop:
                AnimationManager.slideOutTop(ivAnim, duration);
                break;
            case R.id.button_slideOutBottom:
                AnimationManager.slideOutBottom(ivAnim, duration);
                break;
        }
    }
}
