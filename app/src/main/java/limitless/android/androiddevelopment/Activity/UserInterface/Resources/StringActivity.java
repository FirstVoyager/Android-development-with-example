package limitless.android.androiddevelopment.Activity.UserInterface.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;

public class StringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // read string
        AppCompatTextView tvString = findViewById(R.id.textView_string);
        tvString.setText(R.string.example_from_res);

        // read string array
        AppCompatTextView tvArray = findViewById(R.id.textView_stringArray);
        String[] array = getResources().getStringArray(R.array.sample_array);
        StringBuilder sb = new StringBuilder();
        for (String s : array){
            sb.append(s).append(System.lineSeparator());
        }
        tvArray.setText(sb.toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
