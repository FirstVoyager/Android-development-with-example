package limitless.android.androiddevelopment.Activity.UserInterface;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterUserMessage;
import limitless.android.androiddevelopment.Data.DataGenerator;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AppbarTopCollapseImageActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AdapterUserMessage adapterUserMessage;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_top_collapse_image);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else {
            Tools.toast(this, item.getTitle().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        adapterUserMessage = new AdapterUserMessage(this, DataGenerator.generateUser(10));
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.fab).setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterUserMessage);

    }

    @Override
    public void onClick(View v) {
        Tools.toast(this, "Floating action button");
    }
}
