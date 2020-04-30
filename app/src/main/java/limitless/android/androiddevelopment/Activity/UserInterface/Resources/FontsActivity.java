package limitless.android.androiddevelopment.Activity.UserInterface.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.R;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

public class FontsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fonts);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppCompatTextView tv = findViewById(R.id.textView);
        AppCompatTextView tvTitle = findViewById(R.id.textView_title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv.setTypeface(getResources().getFont(R.font.pacifico));
            tvTitle.append(" : from res/font");
        }else {
            Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/pacifico.ttf");
            tv.setTypeface(typeface);
            tvTitle.append(" : from main/assets/fonts");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
