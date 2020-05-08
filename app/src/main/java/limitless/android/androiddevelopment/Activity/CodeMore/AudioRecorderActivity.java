package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

public class AudioRecorderActivity extends BaseActivity implements View.OnClickListener {

    private MaterialButton btnStart, btnStop, btnPlay;
    private MaterialTextView tvPath;
    private String pathName;
    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;
    private int storagePermission = 20012, recordPermission = 4521, twoPermission = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnStart = findViewById(R.id.button_start);
        btnStop = findViewById(R.id.button_stop);
        btnPlay = findViewById(R.id.button_play);
        tvPath = findViewById(R.id.textView_path);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        mediaPlayer = new MediaPlayer();


        boolean storage = Tools.permissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean record = Tools.permissionGranted(this, Manifest.permission.RECORD_AUDIO);
        tvPath.setText(pathName);
        if (storage && record){
            initMediaPlayer();
            return;
        }
        if (! storage && ! record){
            Tools.requestPermission(
                    this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    twoPermission);
        }
        if (! storage){
            Tools.requestPermission(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, storagePermission);
        }
        if (! record){
            Tools.requestPermission(this, Manifest.permission.RECORD_AUDIO, recordPermission);
        }
    }

    private void initMediaPlayer() {
        if (Tools.permissionGranted(this, Manifest.permission.RECORD_AUDIO)){
            pathName = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/androidAudioDevTestFile.3gp";;
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setOutputFile(pathName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storagePermission){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               initMediaPlayer();
            }else {
                Tools.toast(this, "Permission denied");
                finish();
            }
        }else if (requestCode == recordPermission){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initMediaPlayer();
            }else {
                Tools.toast(this, "Permission denied");
                finish();
            }
        }else if (requestCode == twoPermission){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                initMediaPlayer();
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
        if (v.getId() == R.id.button_start){
            startRecording();
        }else if (v.getId() == R.id.button_stop){
            stopRecording();
        }else if (v.getId() == R.id.button_play){
            if (mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer = null;
                btnPlay.setText("Play");
                btnStart.setEnabled(true);
                btnStop.setEnabled(true);
            }else {
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(pathName);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.stop();
                            mediaPlayer = null;
                            btnPlay.setText("Play");
                            btnStart.setEnabled(true);
                            btnStop.setEnabled(false);
                        }
                    });
                    mediaPlayer.start();
                    btnPlay.setText("Stop");
                    btnStop.setEnabled(false);
                    btnStart.setEnabled(false);
                } catch (IOException e) {
                    Tools.error(e);
                }
            }
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            btnPlay.setEnabled(true);
            btnStop.setEnabled(false);
            btnStart.setEnabled(true);
        }
    }

    private void startRecording() {
        initMediaPlayer();
        btnStart.setEnabled(false);
        btnPlay.setEnabled(false);
        btnStop.setEnabled(true);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            Tools.error(e);
            Tools.toast(this, e.getLocalizedMessage());
        }
    }
}
