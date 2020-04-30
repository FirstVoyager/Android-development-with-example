package limitless.android.androiddevelopment.Activity.Basic.Database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SQLiteActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_createTable).setOnClickListener(this);
        findViewById(R.id.button_insert).setOnClickListener(this);
        findViewById(R.id.button_read).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
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

    }
}
