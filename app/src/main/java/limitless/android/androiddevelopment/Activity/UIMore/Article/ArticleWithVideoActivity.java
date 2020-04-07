package limitless.android.androiddevelopment.Activity.UIMore.Article;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.google.android.material.textview.MaterialTextView;

public class ArticleWithVideoActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout llControls, llProgress;
    private AppCompatSeekBar seekbar;
    private AppCompatImageView ivFullScreen;
    private AppCompatImageButton ibtnPrevious, ibtnplay, ibtnNext, ibtnReply;
    private MaterialTextView tvStart, tvEnd;
    private VideoView videoView;
    private int delayTime = 100;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (videoView.isPlaying()){
                tvStart.setText(Tools.convertLongToTime(videoView.getCurrentPosition(), false, true, true, false));
                tvEnd.setText(Tools.convertLongToTime(videoView.getDuration(), false, true, true, false));
                seekbar.setProgress(videoView.getCurrentPosition());
                handler.postDelayed(this, delayTime);
            }
        }
    };
    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            playVideo();
        }
    };

    private void playVideo() {
        videoView.start();
        handler.postDelayed(runnable, delayTime);
        hide(true);
    }

    private MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }
    };
    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            handler.postDelayed(runnable, delayTime);
            seekbar.setMax(mp.getDuration());
            ibtnplay.setImageResource(R.drawable.ic_pause_black_24dp);
            hide(true);
        }
    };
    private AppCompatSeekBar.OnSeekBarChangeListener seekChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser){
                videoView.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_with_video);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()){
            pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView.isPlaying()){
            videoView.resume();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videoView = findViewById(R.id.videoView);
        seekbar = findViewById(R.id.seekBar);
        ibtnPrevious = findViewById(R.id.imageButton_previous);
        ibtnplay = findViewById(R.id.imageButton_play);
        ibtnNext = findViewById(R.id.imageButton_next);
        ivFullScreen = findViewById(R.id.imageView_fullScreen);
        tvStart = findViewById(R.id.textView_timeStart);
        tvEnd = findViewById(R.id.textView_timeEnd);
        ibtnReply = findViewById(R.id.imageButton_reply);
        llControls = findViewById(R.id.linearLayout_controls);
        llProgress = findViewById(R.id.linearLayout_progress);

        videoView.setVideoURI(Tools.rawVideoUri(this, R.raw.test_video));
        videoView.setOnCompletionListener(completeListener);
        videoView.setOnErrorListener(errorListener);
        videoView.setOnPreparedListener(preparedListener);
        videoView.setOnTouchListener(this);
        playVideo();
        ibtnplay.setOnClickListener(this);
        ivFullScreen.setOnClickListener(this);
        ibtnPrevious.setOnClickListener(this);
        ibtnNext.setOnClickListener(this);
        ibtnReply.setOnClickListener(this);
        seekbar.setOnSeekBarChangeListener(seekChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem bookmark = menu.add("Bookmark");
        bookmark.setIcon(R.drawable.ic_bookmark_border_black_24dp);
        bookmark.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        DrawableCompat.setTint(bookmark.getIcon(), Color.WHITE);

        MenuItem favorite = menu.add("Favorite");
        favorite.setIcon(R.drawable.ic_favorite_border_black_24dp);
        favorite.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        DrawableCompat.setTint(favorite.getIcon(), Color.WHITE);

        return super.onCreateOptionsMenu(menu);
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
            case R.id.imageButton_play:
                if (videoView.isPlaying()){
                    ibtnplay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    pause();
                }else {
                    ibtnplay.setImageResource(R.drawable.ic_pause_black_24dp);
                    playVideo();
                }
                break;
            case R.id.imageButton_next:
                Tools.toast(this, "Next");
                break;
            case R.id.imageButton_previous:
                Tools.toast(this, "Previous");
                break;
            case R.id.imageView_fullScreen:
                Tools.toast(this, "Full Screen");
                break;
            case R.id.imageButton_reply:
                Tools.toast(this, "Reply Send");
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.videoView){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                if (llControls.getVisibility() == View.VISIBLE){
                    hide(true);
                }else {
                    hide(false);
                }
            }
        }
        return false;
    }

    private void pause() {
        videoView.pause();
        handler.removeCallbacks(runnable);
        hide(false);
    }

    private void hide(boolean b) {
        if (b){
            llControls.setVisibility(View.INVISIBLE);
            llProgress.setVisibility(View.INVISIBLE);
        }else {
            llControls.setVisibility(View.VISIBLE);
            llProgress.setVisibility(View.VISIBLE);
        }
    }
}
