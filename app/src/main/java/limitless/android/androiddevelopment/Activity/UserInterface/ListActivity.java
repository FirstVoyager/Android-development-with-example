package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ListActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
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
        int n;
        switch (v.getId()){
            case R.id.button_1:
            default:
                n = 0;
                break;
            case R.id.button_2:
                n = 1;
                break;
            case R.id.button_3:
                n = 2;
                break;
            case R.id.button_4:
                n = 3;
                break;
            case R.id.button_5:
                n = 4;
                break;
            case R.id.button_6:
                n = 5;
                break;

        }
        Intent intent = new Intent(this, List2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Intent.EXTRA_TEXT, n);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}


