package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class ProgressBarActivity extends BaseActivity {

    private int delay = 100;
    private ProgressBar pbLD, pbCD;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (pbLD.getProgress() < 100){
                pbCD.setProgress(pbCD.getProgress() + 1);
                pbLD.setProgress(pbLD.getProgress() + 1);
            }else {
                pbLD.setProgress(0);
                pbCD.setProgress(0);
            }
            handler.postDelayed(this, delay);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pbCD = findViewById(R.id.progress_cd);
        pbLD = findViewById(R.id.progress_ld);
        handler.postDelayed(runnable, delay);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
