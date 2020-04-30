package limitless.android.androiddevelopment.Activity.UserInterface.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Fragment.ManagerFragment;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;

import java.util.List;

public class FragmentManagerActivity extends BaseActivity {

    private static final double PERCENT_OF_SCROLL_OF_ACTIVITY_TO_FINISH = 1;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManagerFragment fragment = new ManagerFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment).commit();
        mView = getLayoutInflater().inflate(R.layout.activity_fragment_manager, null);
        setContentView(mView);
    }

    private boolean isDragging = false;
    int startX;
    int currentX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("sherif", isDragging?"YES":"NO" + ": " + event.getX());
        if(!isDragging) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX()<24) {
                isDragging = true;
                startX = (int) event.getX();
                currentX = 0;
                return true;
            }
            return super.onTouchEvent(event);
        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                currentX = (int) event.getX() - startX;
                LayoutParams params = (LayoutParams) mView.getLayoutParams();
                params.leftMargin = currentX;
                params.rightMargin = -1 * currentX;
                mView.requestLayout();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                double currentPercent1 = (double) currentX / mView.getWidth();
                float currentPercent = (float) currentPercent1;
                if(currentX > PERCENT_OF_SCROLL_OF_ACTIVITY_TO_FINISH * mView.getWidth()) {
                    AnimationSet animation = new AnimationSet(false);
                    Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f - currentPercent, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    anim.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setStartTime(AnimationUtils.currentAnimationTimeMillis());
                    animation.addAnimation(anim);
                    anim = new AlphaAnimation(1.0f, 0.5f);
                    anim.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setStartTime(AnimationUtils.currentAnimationTimeMillis());
                    animation.addAnimation(anim);
                    animation.setFillAfter(true);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            finish();
                        }
                    });
                    mView.startAnimation(animation);
                }
                else {
                    AnimationSet animation = new AnimationSet(false);
                    Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f -1 * currentPercent, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    anim.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setStartTime(AnimationUtils.currentAnimationTimeMillis());
                    animation.addAnimation(anim);
                    animation.setFillAfter(true);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}
                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            LayoutParams params = (LayoutParams) mView.getLayoutParams();
                            params.leftMargin = 0;
                            params.rightMargin = 0;
                            mView.requestLayout();
                            mView.clearAnimation();
                        }
                    });
                    mView.startAnimation(animation);
                }
                break;
        }
        return true;

    }

}
