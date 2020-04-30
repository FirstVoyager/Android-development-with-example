package limitless.android.androiddevelopment.Activity.Basic.Database;

import androidx.annotation.NonNull;
import androidx.room.Room;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Room.RoomModel;
import limitless.android.androiddevelopment.Other.Room.TestDatabase;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class RoomActivity extends BaseActivity implements View.OnClickListener {

    private TestDatabase testDatabase;
    private TextInputEditText etName, etDescription, etJob, etNameDelete, etNameRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        init();
    }

    private void init(){
        testDatabase = Room
                .databaseBuilder(getApplicationContext(), TestDatabase.class, "room-test")
                .allowMainThreadQueries()
                .build();
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
        List<RoomModel> rms = testDatabase.testDataDao().getAll();
        if (rms == null || rms.size() <= 0){
            Tools.toast(this, R.string.empty);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (RoomModel rm : rms) {
            String info = "Id : " + rm.uuid + "\n"
                    + "Name : " + rm.name + "\n"
                    + "Description : " + rm.description + "\n"
                    + "Job : " + rm.job;
            sb.append(info).append("\n*********\n");
        }
        Tools.infoDialog(getSupportFragmentManager(), "Read all", sb.toString());
    }

    private void read() {
        if (etNameRead.getText().toString().isEmpty()){
            etNameRead.setError(getString(R.string.error));
            return;
        }
        RoomModel rm = testDatabase.testDataDao().findByName(etNameRead.getText().toString());
        if (rm == null)
            Tools.toast(this, R.string.not_found);
        else {
            String info = "Id : " + rm.uuid + "\n"
                                + "Name : " + rm.name + "\n"
                                + "Description : " + rm.description + "\n"
                                + "Job : " + rm.job;
            Tools.infoDialog(getSupportFragmentManager(), "Read", info);
        }
    }

    private void delete() {
        if (etNameDelete.getText().toString().isEmpty()){
            etNameDelete.setError(getString(R.string.error));
            return;
        }
        testDatabase.testDataDao().delete(etNameDelete.getText().toString());
        Tools.toast(this, R.string.deleted);
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
        RoomModel rm = new RoomModel();
        rm.name = etName.getText().toString();
        rm.description = etDescription.getText().toString();
        rm.job = etJob.getText().toString();
        testDatabase.testDataDao().insert(rm);
        Tools.toast(this, R.string.insert);
    }
}
