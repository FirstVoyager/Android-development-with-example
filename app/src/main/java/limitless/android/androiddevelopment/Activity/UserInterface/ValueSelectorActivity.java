package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.CustomView.ValueSelector;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;

public class ValueSelectorActivity extends AppCompatActivity {

    private ValueSelector vs1, vs2, vs3, vs4;
    private AppCompatTextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_selector);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vs1 = findViewById(R.id.valueSelector1);
        vs2 = findViewById(R.id.valueSelector2);
        vs3 = findViewById(R.id.valueSelector3);
        vs4 = findViewById(R.id.valueSelector4);
        tvValue = findViewById(R.id.textView_resulat);

        vs1.setChangeListener(changeListener);
        vs2.setChangeListener(changeListener);
        vs3.setChangeListener(changeListener);
        vs4.setChangeListener(changeListener);
    }

    private ValueSelector.ChangeListener changeListener = new ValueSelector.ChangeListener() {
        @Override
        public void listener(int value) {
            tvValue.setText("Value = ");
            tvValue.append(String.valueOf(value));
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}