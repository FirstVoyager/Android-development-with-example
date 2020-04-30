package limitless.android.androiddevelopment.Activity.Basic.FileStorage;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class RWCDFileActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText etName, etBody;
    private File lastFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rwcdfile);
        init();
    }

    @Override
    protected void onDestroy() {
        if (lastFile != null){
            lastFile.delete();
        }
        super.onDestroy();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etName = findViewById(R.id.editText_name);
        etBody = findViewById(R.id.editText_body);
        findViewById(R.id.button_create).setOnClickListener(this);
        findViewById(R.id.button_write).setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.button_create:
                createFile();
                break;
            case R.id.button_write:
                writeFile();
                break;
            case R.id.button_read:
                readFile();
                break;
            case R.id.button_delete:
                deleteFile();
                break;
        }
    }

    private void deleteFile() {
        if (lastFile == null){
            Tools.toast(this, "plz create file");
            return;
        }
        if (lastFile.delete()){
            Tools.toast(this, "successfully deleted \"" + lastFile.getName() + "\"");
            lastFile = null;
            etBody.setText("");
            etName.setText("");
        }else {
            Tools.toast(this, "cant delete file !");
        }
    }

    private void readFile() {
        if (lastFile == null) {
            Tools.toast(this, "plz create file !");
            return;
        }
        try {
            FileReader fr = new FileReader(lastFile);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            do {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            } while (line != null);
            etBody.setText(sb.toString());
            Tools.toast(this, "successfully read file");
        } catch (IOException e) {
            Tools.error(e);
            Tools.toast(this, e.getMessage());
        }
    }

    private void writeFile() {
        if (lastFile == null){
            Tools.toast(this, "plz create file !");
            return;
        }
        if (Tools.isEmpty(etBody.getText().toString())){
            etBody.setError(getString(R.string.empty));
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(lastFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Writer writer = new BufferedWriter(osw);
            writer.write(etBody.getText().toString());
            writer.close();
            etBody.setText("");
            Tools.toast(this, "successfully write file");
        } catch (IOException e) {
            Tools.error(e);
            Tools.toast(this, e.getMessage());
        }
    }

    private void createFile() {
        if (Tools.isEmpty(etName.getText().toString())){
            etName.setError(getString(R.string.empty));
            return;
        }
        lastFile = new File(getCacheDir().getAbsolutePath() + "/" + etName.getText().toString());
        if (lastFile.exists()){
            etName.setError("File exist. plz select another name !");
        }
        try {
            if (lastFile.createNewFile()){
                Tools.toast(this, "create file \"" + lastFile.getName() + "\"");
            }else {
                Tools.toast(this, "cant create file \"" + lastFile.getName() + "\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
