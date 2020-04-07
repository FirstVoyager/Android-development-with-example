package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.material.textview.MaterialTextView;

public class AudioManagerActivity extends BaseActivity implements View.OnClickListener {

    private MaterialTextView tvMode;
    private AudioManager audioManager;
    private NotificationManager notificationManager;
    private AppCompatSeekBar sbRingtone, sbMedia, sbAlarms, sbCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        tvMode = findViewById(R.id.textView_mode);
        sbRingtone = findViewById(R.id.seekBar_ringtone);
        sbMedia = findViewById(R.id.seekBar_media);
        sbAlarms = findViewById(R.id.seekBar_alarms);
        sbCalls = findViewById(R.id.seekBar_calls);
        findViewById(R.id.fab_normalMode).setOnClickListener(this);
        findViewById(R.id.fab_silentMode).setOnClickListener(this);
        findViewById(R.id.fab_vibrateMode).setOnClickListener(this);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ! notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent( android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }
        // set max values
        sbRingtone.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
        sbMedia.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        sbAlarms.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
        sbCalls.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
        // set current values
        sbRingtone.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION));
        sbMedia.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        sbAlarms.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
        sbCalls.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
        // set listeners
        sbRingtone.setOnSeekBarChangeListener(seekBarChangeListener);
        sbMedia.setOnSeekBarChangeListener(seekBarChangeListener);
        sbAlarms.setOnSeekBarChangeListener(seekBarChangeListener);
        sbCalls.setOnSeekBarChangeListener(seekBarChangeListener);

        tvMode.setText(getMode());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private String getMode() {
        int n = audioManager.getRingerMode();
        if (n == AudioManager.RINGER_MODE_NORMAL)
            return "Mode Normal";
        else if (n == AudioManager.RINGER_MODE_SILENT)
            return "Mode Silent";
        else if (n == AudioManager.RINGER_MODE_VIBRATE)
            return "Mode Vibrate";
        return null;
    }

    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ! notificationManager.isNotificationPolicyAccessGranted()) {
            return;
        }
        if (v.getId() == R.id.fab_normalMode){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }else if (v.getId() == R.id.fab_silentMode){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else if (v.getId() == R.id.fab_vibrateMode){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        tvMode.setText(getMode());
    }

    private AppCompatSeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (! fromUser)
                return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (! notificationManager.isNotificationPolicyAccessGranted())
                    return;
            }
            switch (seekBar.getId()){
                case R.id.seekBar_ringtone:
                    audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, progress, AudioManager.ADJUST_SAME);
                    break;
                case R.id.seekBar_media:
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.ADJUST_SAME);
                    break;
                case R.id.seekBar_alarms:
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, AudioManager.ADJUST_SAME);
                    break;
                case R.id.seekBar_calls:
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, AudioManager.ADJUST_SAME);
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
