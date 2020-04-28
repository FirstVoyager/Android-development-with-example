package limitless.android.androiddevelopment.Activity.Basic.Database;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import io.realm.Realm;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Model.RealmTestModel;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class RealmTestActivity extends BaseActivity implements View.OnClickListener {

    private RealmControllerTest controllerTest;
    private AppCompatEditText etName, etNumber, etAddress, etIdRead, etIdDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_test);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.title_realm_database), getString(R.string.info_realm_database)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setIcon(R.drawable.ic_info_outline_white_24dp);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        controllerTest = new RealmControllerTest();
        etName = findViewById(R.id.editText_nameInsert);
        etAddress = findViewById(R.id.editText_addressInsert);
        etNumber = findViewById(R.id.editText_numberInsert);
        etIdRead = findViewById(R.id.editText_idRead);
        etIdDelete = findViewById(R.id.editText_idDelete);

        findViewById(R.id.button_insert).setOnClickListener(this);
        findViewById(R.id.button_read).setOnClickListener(this);
        findViewById(R.id.button_delete).setOnClickListener(this);
        findViewById(R.id.button_readAll).setOnClickListener(this);
        findViewById(R.id.button_deleteAll).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (controllerTest != null){
            controllerTest.close();
        }
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
            case R.id.button_readAll:
                readAll();
                break;
            case R.id.button_deleteAll:
                deleteAll();
                break;
        }
    }

    private void deleteAll() {
        controllerTest.clearAll();
        Tools.toast(this, "Clear all");
    }

    private void readAll() {
        List<RealmTestModel> models = controllerTest.getAllModels();
        if (models == null || models.size() <= 0){
            Tools.toast(this, "No data");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (RealmTestModel rtm : models) {
            sb.append("Id : ").append(rtm.getId()).append(System.lineSeparator());
            sb.append("Name : ").append(rtm.getName()).append(System.lineSeparator());
            sb.append("Number : ").append(rtm.getNumber()).append(System.lineSeparator());
            sb.append("Address : ").append(rtm.getAddress()).append(System.lineSeparator());
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        }
        Tools.showInfoDialog(getSupportFragmentManager(), "All data", sb.toString());
    }

    private void delete() {
        if (Tools.isEmpty(etIdRead.getText().toString())){
            etIdDelete.setError(getString(R.string.t_empty));
            return;
        }
        controllerTest.delete(Integer.valueOf(etIdRead.getText().toString()));
        Tools.toast(this, "Delete");
    }

    private void read() {
        if (Tools.isEmpty(etIdRead.getText().toString())){
            etIdRead.setError(getString(R.string.t_empty));
            return;
        }
        RealmTestModel model = controllerTest.getModel(Integer.valueOf(etIdRead.getText().toString()));
        if (model == null){
            Tools.toast(this, "not found");
            return;
        }
        Tools.showInfoDialog(
                getSupportFragmentManager(),
                etIdRead.getText().toString(),
                "Id : " + model.getId() + "\n" +
                        "Name : " + model.getName() + "\n" +
                        "Number : " + model.getNumber() + "\n" +
                        "Address : " + model.getAddress()
        );
    }

    private void insert() {
        if (Tools.isEmpty(etName.getText().toString())) {
            etName.setError(getString(R.string.t_empty));
            return;
        }
        if (Tools.isEmpty(etNumber.getText().toString())){
            etNumber.setError(getString(R.string.t_empty));
            return;
        }
        if (Tools.isEmpty(etAddress.getText().toString())){
            etAddress.setError(getString(R.string.t_empty));
            return;
        }
        controllerTest.insert(etName.getText().toString(), etNumber.getText().toString(), etAddress.getText().toString());
        Tools.toast(this, "insert");
    }

    private class RealmControllerTest {

        private Realm realm;

        public RealmControllerTest(){
            Realm.init(RealmTestActivity.this);
            realm = Realm.getDefaultInstance();
        }

        public void close() {
            if (realm != null)
                realm.close();
        }

        public Realm getRealm(){
            return realm;
        }

        public void clearAll(){
            realm.beginTransaction();
            realm.delete(RealmTestModel.class);
            realm.commitTransaction();
        }

        public List<RealmTestModel> getAllModels(){
            return realm.where(RealmTestModel.class).findAll();
        }

        public RealmTestModel getModel(int id){
            return realm.where(RealmTestModel.class).equalTo("id", id).findFirst();
        }

        public boolean hasModel(int id){
            RealmTestModel model = realm.where(RealmTestModel.class).equalTo("id", id).findFirst();
            return model != null;
        }

        public void insert(String name, String number, String address) {
            Number num = realm.where(RealmTestModel.class).max("id");
            int nextId;
            if (num == null)
                nextId = 1;
            else
                nextId = num.intValue() + 1;

            realm.beginTransaction();
            realm.copyToRealm(new RealmTestModel(nextId, name, number, address));
            realm.commitTransaction();
        }

        public void delete(int id) {
            realm.beginTransaction();
            RealmTestModel realmTestModel = realm.where(RealmTestModel.class).equalTo("id", id).findFirst();
            if (realmTestModel != null)
                realmTestModel.deleteFromRealm();
            realm.commitTransaction();
        }
    }

}
