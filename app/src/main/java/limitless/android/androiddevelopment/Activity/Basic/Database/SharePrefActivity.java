package limitless.android.androiddevelopment.Activity.Basic.Database;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SharePrefActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private String sharePrefName = "sharePreferences.test";
    private AppCompatEditText etKeyInsert, etTextInsert, etKeyRead, etKeyDelete, etKeyUpate, etTextUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_pref);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(sharePrefName, MODE_PRIVATE);
        etKeyInsert = findViewById(R.id.editText_keyInsert);
        etTextInsert = findViewById(R.id.editText_textInsert);
        etKeyRead = findViewById(R.id.editText_keyRead);
        etKeyDelete = findViewById(R.id.editText_keyDelete);
        etKeyUpate = findViewById(R.id.editText_keyUpdate);
        etTextUpdate = findViewById(R.id.editText_textUpdate);

        findViewById(R.id.button_insert).setOnClickListener(this);
        findViewById(R.id.button_read).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.button_clearAll).setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.button_insert:
                insert();
                break;
            case R.id.button_read:
                read();
                break;
            case R.id.button_delete:
                delete();
                break;
            case R.id.button_update:
                update();
                break;
            case R.id.button_clearAll:
                clearAll();
                break;
        }
    }

    private void clearAll() {
        sharedPreferences.edit().clear().apply();
        toast("Clear all data");
    }

    private void update() {
        if (Tools.isEmpty(etKeyUpate.getText().toString())){
            etKeyUpate.setError(getString(R.string.empty));
            return;
        }
        if (Tools.isEmpty(etTextUpdate.getText().toString())){
            etTextUpdate.setError(getString(R.string.empty));
            return;
        }
        sharedPreferences.edit().putString(etKeyUpate.getText().toString(), etTextUpdate.getText().toString()).apply();
        toast(etKeyUpate.getText().toString() + " update");
    }

    private void delete() {
        if (Tools.isEmpty(etKeyDelete.getText().toString())){
            etKeyDelete.setError(getString(R.string.empty));
            return;
        }
        sharedPreferences.edit().remove(etKeyDelete.getText().toString()).apply();
        toast(etKeyDelete.getText().toString() + " remove !");
    }

    private void read() {
        String key = etKeyRead.getText().toString();
        if (Tools.isEmpty(key)){
            etKeyRead.setError(getString(R.string.empty));
            return;
        }
        String s = sharedPreferences.getString(key, null);
        if (s == null){
            toast(etKeyRead.getText().toString() + " is null");
            return;
        }
        Tools.showInfoDialog(
                getSupportFragmentManager(),
                key,
                s
        );
    }

    private void insert() {
        if (Tools.isEmpty(etKeyInsert.getText().toString())){
            etKeyInsert.setError(getString(R.string.empty));
            return;
        }
        if (Tools.isEmpty(etTextInsert.getText().toString())){
            etTextInsert.setError(getString(R.string.empty));
            return;
        }
        sharedPreferences.edit().putString(
                etKeyInsert.getText().toString(),
                etTextInsert.getText().toString()
        ).apply();
        toast(etKeyInsert.getText().toString() + " inserted");
    }


    private void toast(String s) {
        Tools.toast(this, s);
    }

}
