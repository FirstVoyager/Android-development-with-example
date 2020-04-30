package limitless.android.androiddevelopment.Activity.UserInterface.CustomComponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;
import limitless.android.androiddevelopment.CustomView.CustomTextView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CustomTextViewActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CustomTextView ctv;
    private AppCompatSeekBar seekBar;
    private SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_view);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ctv = findViewById(R.id.customTextView);
        seekBar = findViewById(R.id.seekBar);
        switchCompat = findViewById(R.id.switch_show);
        seekBar.setOnSeekBarChangeListener(changeListener);
        seekBar.setProgress(6);
        switchCompat.setOnCheckedChangeListener(this);
        findViewById(R.id.view_red).setOnClickListener(this);
        findViewById(R.id.view_blue).setOnClickListener(this);
        findViewById(R.id.view_green).setOnClickListener(this);
        findViewById(R.id.view_black).setOnClickListener(this);
        findViewById(R.id.view_yellow).setOnClickListener(this);
        findViewById(R.id.view_orange).setOnClickListener(this);
        findViewById(R.id.view_purple).setOnClickListener(this);
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
            case R.id.view_red:
                ctv.setStrokeColor(Color.RED);
                break;
            case R.id.view_blue:
                ctv.setStrokeColor(Color.BLUE);
                break;
            case R.id.view_green:
                ctv.setStrokeColor(Color.GREEN);
                break;
            case R.id.view_black:
                ctv.setStrokeColor(Color.BLACK);
                break;
            case R.id.view_yellow:
                ctv.setStrokeColor(Color.YELLOW);
                break;
            case R.id.view_orange:
                ctv.setStrokeColor(Color.parseColor("#ffff8800"));
                break;
            case R.id.view_purple:
                ctv.setStrokeColor(Color.parseColor("#ffaa66cc"));
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ctv.setShowStroke(isChecked);
    }

    private AppCompatSeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            ctv.setStrokeWidth(Tools.convertDipToPx(getResources().getDisplayMetrics(), progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
