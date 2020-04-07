package limitless.android.androiddevelopment.Activity.UIMore.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textview.MaterialTextView;


public class ProfileBigHeaderActivity extends BaseActivity implements View.OnClickListener {

    private MaterialTextView tvAbout, tvMoreState;
    private boolean moreState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_big_header);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvAbout = findViewById(R.id.textView_about);
        tvMoreState = findViewById(R.id.textView_more);
        tvMoreState.setOnClickListener(this);
        setTitle(null);
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
        if (v.getId() == R.id.textView_more){
            if (moreState){
                tvAbout.setMaxLines(4560);
                tvMoreState.setText("Read less");
            }else {
                tvAbout.setMaxLines(4);
                tvMoreState.setText("Read more");
            }
            moreState = ! moreState;
        }
    }
}
