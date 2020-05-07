package limitless.android.androiddevelopment.Activity.Basic.Network;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;

public class GsonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
