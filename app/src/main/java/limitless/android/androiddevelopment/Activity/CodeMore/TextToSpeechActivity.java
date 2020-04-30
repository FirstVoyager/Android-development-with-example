package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class TextToSpeechActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText editText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.stop();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = findViewById(R.id.editText);
        findViewById(R.id.button_tts).setOnClickListener(this);
        tts = new TextToSpeech(this, listener);
        tts.setLanguage(Locale.US);
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
        if (v.getId() == R.id.button_tts){
            if (Tools.isEmpty(editText.getText().toString())){
                editText.setError(getString(R.string.empty));
            }else {
                tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }

    private TextToSpeech.OnInitListener listener = new OnInitListener() {
        @Override
        public void onInit(int status) {
            switch (status){
                case TextToSpeech.SUCCESS:

                    break;
                case TextToSpeech.ERROR:

                    break;
            }
        }
    };

}
