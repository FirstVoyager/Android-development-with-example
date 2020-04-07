package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class ClipboardManagerActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText etTitle, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboar_manager);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTitle = findViewById(R.id.editText_title);
        etDescription = findViewById(R.id.editText_description);
        findViewById(R.id.button_copy).setOnClickListener(this);
        findViewById(R.id.button_paste).setOnClickListener(this);
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
        if (v.getId() == R.id.button_copy){
            if (Tools.isEmpty(etDescription.getText().toString())){
                etDescription.setError(getString(R.string.t_empty));
            }else {
                if (Tools.copyToClipboard(this, etTitle.getText().toString(), etDescription.getText().toString())){
                    Tools.toast(this, "Copied");
                }else {
                    Tools.toast(this, "Error");
                }
            }
        }else if (v.getId() == R.id.button_paste){
            try {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData cd = cm.getPrimaryClip();
                String title = cd.getDescription().getLabel().toString();
                String description = cd.getItemAt(0).getText().toString();
                etTitle.setText(title);
                etDescription.setText(description);
            }catch (Exception e){
                Tools.error(e);
            }
        }
    }
}
