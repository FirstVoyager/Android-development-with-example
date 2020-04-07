package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.BottomSheet.SelectBottomSheet;
import limitless.android.androiddevelopment.Data.Data;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.material.textview.MaterialTextView;

import java.io.File;
import java.io.IOException;

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView ivCover;
    private MaterialTextView tvStart, tvEnd, tvName;
    private AppCompatSeekBar seekBar;
    private AppCompatImageButton ibtnPrevious, ibtnPlay, ibtnNext;
    private int storageCode = 1999;
    private int delayTime = 100;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                tvStart.setText(Tools.convertLongToTime(mediaPlayer.getCurrentPosition(), false, true, true, false));
                handler.postDelayed(this, delayTime);
            }
        }
    };
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            ibtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    };
    private MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            switch (what){
                case MediaPlayer.MEDIA_ERROR_IO:

                    break;
                case MediaPlayer.MEDIA_ERROR_TIMED_OUT:

                    break;
                case MediaPlayer.MEDIA_ERROR_UNKNOWN:

                    break;
                case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:

                    break;
                case MediaPlayer.MEDIA_ERROR_SERVER_DIED:

                    break;
            }
            return false;
        }
    };
    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            seekBar.setMax(mp.getDuration());
            tvEnd.setText(Tools.convertLongToTime(mp.getDuration(), false, true, true, false));
            handler.postDelayed(runnable, delayTime);
        }
    };
    private AppCompatSeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser && mediaPlayer != null){
                mediaPlayer.seekTo(progress);
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
        setContentView(R.layout.activity_media_player);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivCover = findViewById(R.id.imageView_cover);
        tvStart = findViewById(R.id.textView_start);
        tvEnd = findViewById(R.id.textView_end);
        tvName = findViewById(R.id.textView_name);
        seekBar = findViewById(R.id.seekBar);
        ibtnPrevious = findViewById(R.id.imageButton_previous);
        ibtnPlay = findViewById(R.id.imageButton_play);
        ibtnNext = findViewById(R.id.imageButton_next);

        findViewById(R.id.imageButton_playList).setOnClickListener(this);
        ibtnPrevious.setOnClickListener(this);
        ibtnPlay.setOnClickListener(this);
        ibtnNext.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        if (Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            selectMusic();
        }else {
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, storageCode);
        }
    }

    private void selectMusic() {
        SelectBottomSheet sheet = new SelectBottomSheet(new StringListener() {
            @Override
            public void string(String s) {
                playMedia(s);
            }
        }, 1);
        sheet.show(getSupportFragmentManager(), null);
    }

    private void playMedia(String s) {
        try {
            if (mediaPlayer != null){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(preparedListener);
            mediaPlayer.setOnErrorListener(errorListener);
            mediaPlayer.setOnCompletionListener(completeListener);
            mediaPlayer.setDataSource(s);
            mediaPlayer.prepare();
            mediaPlayer.start();
            File file = new File(s);
            tvName.setText(file.getName());
            ibtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
            Bitmap cover = Data.getAudioCoverPhoto(s);
            if (cover == null)
                ivCover.setImageResource(R.drawable.ic_music_512dp);
            else
                ivCover.setImageBitmap(cover);
        } catch (IOException e) {
            Tools.error(e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storageCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectMusic();
            }else {
                Tools.toast(this, "Permission denied");
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        if (v.getId() == R.id.imageButton_play){
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                ibtnPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }else {
                if (mediaPlayer != null){
                    mediaPlayer.start();
                    handler.postDelayed(runnable, delayTime);
                }
                ibtnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
            }
        }else if (v.getId() == R.id.imageButton_previous){
            if (mediaPlayer != null){
                int n = mediaPlayer.getCurrentPosition() - 10000;
                if (n >= 0)
                    mediaPlayer.seekTo(n);
            }
        }else if (v.getId() == R.id.imageButton_next){
            if (mediaPlayer != null){
                int n = mediaPlayer.getCurrentPosition() + 10000;
                if (n <= mediaPlayer.getDuration())
                    mediaPlayer.seekTo(n);
            }
        }else if (v.getId() == R.id.imageButton_playList){
            selectMusic();
        }
    }
}
