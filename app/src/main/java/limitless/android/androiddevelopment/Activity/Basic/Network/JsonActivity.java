package limitless.android.androiddevelopment.Activity.Basic.Network;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView tv;
    private int indentSpaces = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv = findViewById(R.id.textView_json);

        tv.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.button_jsonObject).setOnClickListener(this);
        findViewById(R.id.button_jsonArray).setOnClickListener(this);
        findViewById(R.id.button_readJsonArray).setOnClickListener(this);
        findViewById(R.id.button_readJsonObject).setOnClickListener(this);
        findViewById(R.id.button_gson).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_jsonObject:
                tv.setText(jsonObject());
                break;
            case R.id.button_jsonArray:
                tv.setText(jsonArray());
                break;
            case R.id.button_readJsonObject:
                readJsonObject();
                break;
            case R.id.button_readJsonArray:
                readJsonArray();
                break;
            case R.id.button_gson:
                startActivity(new Intent(this, GsonActivity.class));
                break;
        }
    }

    private void readJsonArray() {
        try {
            JSONArray ja = new JSONArray(jsonArray());
            tv.setText(ja.toString(indentSpaces));
        }catch (JSONException e){
            Tools.error(e);
            tv.setText(e.getLocalizedMessage());
        }
    }

    private void readJsonObject() {
        try {
            JSONObject object = new JSONObject(jsonObject());
            tv.setText(object.toString(indentSpaces));
        } catch (JSONException e) {
            Tools.error(e);
            tv.setText(e.getLocalizedMessage());
        }
    }

    private String jsonArray() {
        try{
            JSONArray ja = new JSONArray();
            for (int i = 0; i < 10; i++) {
                JSONObject jo = new JSONObject();
                jo.put("name", "Milad");
                jo.put("family", "Sohrabi");
                jo.put("location", "new york");
                jo.put("age", 20);
                jo.put("job", "Android developer");
                jo.put("height", "170 cm");
                jo.put("email", "xxxxxxxxxxxxx@gmail.com");

                ja.put(jo);
            }
            return ja.toString(indentSpaces);
        }catch (JSONException e){
            Tools.error(e);
            return e.getLocalizedMessage();
        }
    }

    private String jsonObject() {
        try {
            JSONObject jo = new JSONObject();
            jo.put("name", "Milad");
            jo.put("family", "Sohrabi");
            jo.put("location", "new york");
            jo.put("age", 20);
            jo.put("job", "Android developer");
            jo.put("height", "170 cm");
            jo.put("email", "xxxxxxxxxxxxx@gmail.com");
            return jo.toString(indentSpaces);
        } catch (JSONException e) {
            Tools.error(e);
            return e.getLocalizedMessage();
        }

    }
}
