package limitless.android.androiddevelopment.Activity.Basic.Sensors.Network;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView ivTest;
    private AppCompatTextView tvTest;
    private RequestQueue requestQueue;
    private String tag = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivTest = findViewById(R.id.imageView_test);
        tvTest = findViewById(R.id.textView_test);
        requestQueue = Volley.newRequestQueue(this);

        findViewById(R.id.button_downloadString).setOnClickListener(this);
        findViewById(R.id.button_downloadImage).setOnClickListener(this);
        findViewById(R.id.button_downloadArray).setOnClickListener(this);
        findViewById(R.id.button_downloadObject).setOnClickListener(this);

        tvTest.setMovementMethod(new ScrollingMovementMethod());
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
        requestQueue.cancelAll(tag);
        Tools.toast(this, getString(R.string.text_start_downloading));
        if (v.getId() == R.id.button_downloadImage){
            getImage();
        }else if (v.getId() == R.id.button_downloadString){
            getString();
        }else if (v.getId() == R.id.button_downloadArray){
            getArray();
        }else if (v.getId() == R.id.button_downloadObject){
            getObject();
        }
    }

    private void getObject() {
        tvTest.setText(null);
        ivTest.setImageBitmap(null);

        JsonObjectRequest request = new JsonObjectRequest(
                "https://jsonplaceholder.typicode.com/todos/10",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tvTest.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvTest.setText(error.getLocalizedMessage());
                        Tools.error(error);
                    }
                }
        );

        requestQueue.add(request).setTag(tag);
    }

    private void getArray(){
        tvTest.setText(null);
        ivTest.setImageBitmap(null);

        JsonArrayRequest request = new JsonArrayRequest(
                "https://jsonplaceholder.typicode.com/posts",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tvTest.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Tools.error(error);
                        tvTest.setText(error.getLocalizedMessage());
                    }
                }
        );

        requestQueue.add(request).setTag(tag);
    }

    private void getString(){
        tvTest.setText(null);
        ivTest.setImageBitmap(null);
        StringRequest request = new StringRequest(
                "https://www.w3.org/TR/PNG/iso_8859-1.txt",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvTest.setText(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Tools.error(error);
                        tvTest.setText(error.getLocalizedMessage());
                    }
                }

        );
        requestQueue.add(request).setTag(tag);
    }

    private void getImage() {
        tvTest.setText(null);
        ivTest.setImageBitmap(null);

        ImageRequest request = new ImageRequest(
                "http://homepages.cae.wisc.edu/~ece533/images/girl.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ivTest.setImageBitmap(response);
                    }
                },
                1080,
                1080,
                null,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Tools.error(error);
                        tvTest.setText(error.getLocalizedMessage());
                    }
                });

        requestQueue.add(request).setTag(tag);
    }
}

