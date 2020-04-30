package limitless.android.androiddevelopment.Activity.Basic.Database;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.TestSQLite.TestSQLite;
import limitless.android.androiddevelopment.Other.TestSQLite.UserTest;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SQLiteActivity extends BaseActivity implements View.OnClickListener {

    private TestSQLite sqLite;
    private TextInputEditText etName, etDescription, etJob, etNameDelete, etNameRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        init();
    }

    private void init() {
        sqLite = new TestSQLite(this);
        etName = findViewById(R.id.editText_name);
        etDescription = findViewById(R.id.editText_description);
        etJob = findViewById(R.id.editText_job);
        etNameDelete = findViewById(R.id.editText_nameDelete);
        etNameRead = findViewById(R.id.editText_nameRead);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_insert).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
        findViewById(R.id.button_read).setOnClickListener(this);
        findViewById(R.id.button_readAll).setOnClickListener(this);
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
            case R.id.button_delete:
                delete();
                break;
            case R.id.button_read:
                read();
                break;
            case R.id.button_readAll:
                readAll();
                break;
        }
    }

    private void readAll() {
        List<UserTest> users = sqLite.getAllUsers();
        if (users == null || users.size() <= 0){
            Tools.toast(this, R.string.empty);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (UserTest ut : users) {
            String info = "Id : " + ut.uuid + "\n" +
                    "Name : " + ut.name +
                    "Description : " + ut.description +
                    "Job : " + ut.job;
            sb.append(info).append("\n******\n");
        }
        Tools.infoDialog(getSupportFragmentManager(), "Read All", sb.toString());
    }

    private void read() {
        if (etNameRead.getText().toString().isEmpty()){
            etNameRead.setError(getString(R.string.error));
            return;
        }
        UserTest ut = sqLite.getUser(etNameRead.getText().toString());
        if (ut == null)
            Tools.toast(this, R.string.not_found);
        else {
            String info = "Id : " + ut.uuid + "\n" +
                    "Name : " + ut.name + "\n" +
                    "Description : " + ut.description + "\n" +
                    "Job : " + ut.job;
            Tools.infoDialog(getSupportFragmentManager(), "Read",info);
        }
    }

    private void delete() {
        if (etNameDelete.getText().toString().isEmpty()){
            etNameDelete.setError(getString(R.string.error));
            return;
        }
        if (sqLite.deleteUser(etNameDelete.getText().toString()))
            Tools.toast(this, R.string.deleted);
        else
            Tools.toast(this, R.string.error);
    }

    private void insert() {
        if (etName.getText().toString().isEmpty()){
            etName.setError(getString(R.string.error));
            return;
        }
        if (etDescription.getText().toString().isEmpty()){
            etDescription.setError(getString(R.string.error));
            return;
        }
        if (etJob.getText().toString().isEmpty()){
            etJob.setError(getString(R.string.error));
            return;
        }
        UserTest us = new UserTest();
        us.name = etName.getText().toString();
        us.description = etDescription.getText().toString();
        us.job = etJob.getText().toString();
        if (sqLite.insertUser(us))
            Tools.toast(this, R.string.insert);
        else
            Tools.toast(this, R.string.error);
    }
}
