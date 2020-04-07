package limitless.android.androiddevelopment.Other;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * a class for mange animations
 */
public class AnimationManager {

    /**
     * zoom in animation from center
     * @param view want to zoom in
     * @param duration time for animate
     */
    public static void zoomIn(View view, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1,
                5,
                1,
                5,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(false);
        view.startAnimation(scaleAnimation);

    }

    /**
     * zoom out animation from center
     * @param view want to zoom out
     * @param duration time for zoom out
     */
    public static void zoomOut(View view, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1,
                0.5f,
                1,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(false);
        view.startAnimation(scaleAnimation);
    }

    /**
     * animate view for slide
     * @param view want to slide in bottom
     * @param duration time for animation
     */
    public static void slideInBottom(View view, int duration) {
        TranslateAnimation ta = new TranslateAnimation(
                0,
                0,
                - view.getHeight(),
                0);
        ta.setDuration(duration);
        ta.setFillAfter(true);
        view.startAnimation(ta);
    }

    /**
     * animate view for slide
     * @param view want to slide in top
     * @param duration time for animation
     */
    public static void slideInTop(View view, int duration) {
        TranslateAnimation ta = new TranslateAnimation(
                0,
                0,
                view.getHeight(),
                0);
        ta.setDuration(duration);
        ta.setFillAfter(true);
        view.startAnimation(ta);
    }

    /**
     * animate view for slide
     * @param view want to slide out top
     * @param duration time for animation
     */
    public static void slideOutTop(View view, int duration) {
        TranslateAnimation ta = new TranslateAnimation(
                0,
                0,
                0,
                - view.getHeight());
        ta.setDuration(duration);
        ta.setFillAfter(true);
        view.startAnimation(ta);
    }


    /**
     * animate view for slide
     * @param view want to slide out bottom
     * @param duration time for animation
     */
    public static void slideOutBottom(View view, int duration) {
        TranslateAnimation ta = new TranslateAnimation(
                0,
                0,
                0,
                view.getHeight());
        ta.setDuration(duration);
        ta.setFillAfter(true);
        view.startAnimation(ta);
    }

    /**
     * simple method for rotate animation
     * @param view want to rotate
     * @param angle to degrees
     * @param duration time for animation
     */
    public static void rotate(View view, int angle, int duration) {
        RotateAnimation ra = new RotateAnimation(
                0,
                angle,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f);
        ra.setDuration(duration);
        ra.setFillAfter(true);
        view.startAnimation(ra);
    }

    /**
     * fade in
     * @param view want to fade
     * @param duration time for animation
     * @see #fadeOut(View, int)
     */
    public static void fadeIn(View view, int duration) {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(duration);
        aa.setFillAfter(true);
        view.startAnimation(aa);
    }

    /**
     * fade out
     * @param view want to fade out
     * @param duration time for animation
     * @see #fadeIn(View, int)
     */
    public static void fadeOut(View view, int duration) {
        AlphaAnimation aa = new AlphaAnimation(1, 0);
        aa.setDuration(duration);
        aa.setFillAfter(true);
        view.startAnimation(aa);
    }
}
