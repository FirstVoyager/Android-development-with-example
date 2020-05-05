package limitless.android.androiddevelopment.Activity;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Model.ProjectModel;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textview.MaterialTextView;

public class ProjectViewActivity extends BaseActivity implements View.OnClickListener {

    public static final String EXTRA_PROJECT = "extra_project";

    private ProjectModel project;
    private ImageView ivHeader;
    private MaterialTextView tvTitle, tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivHeader = findViewById(R.id.imageView_header);
        tvTitle = findViewById(R.id.textView_title);
        tvDes = findViewById(R.id.textView_description);
        project = getIntent().getParcelableExtra(EXTRA_PROJECT);
        if (project == null){
            finish();
            return;
        }
        Tools.loadImage(this, project.image, ivHeader);
        setTitle(project.title);
        tvDes.setText(project.caption);
        findViewById(R.id.button_googlePlay).setOnClickListener(this);
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
        if (v.getId() == R.id.button_googlePlay){
            Tools.openUrl(this, project.googlePlay);
        }
    }
}
