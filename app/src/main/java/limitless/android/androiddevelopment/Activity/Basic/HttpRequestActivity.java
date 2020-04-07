package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestActivity extends BaseActivity implements View.OnClickListener {

    private static AppCompatTextView tvTest;
    private static AppCompatImageView ivTest;
    private AppCompatEditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivTest = findViewById(R.id.imageView_test);
        tvTest = findViewById(R.id.textView_test);
        etUrl = findViewById(R.id.editText_url);

        findViewById(R.id.button_downloadText).setOnClickListener(this);
        findViewById(R.id.button_downloadImage).setOnClickListener(this);
        findViewById(R.id.button_downloadWebsite).setOnClickListener(this);

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
        Tools.toast(this, getString(R.string.text_start_downloading));
        switch (v.getId()){
            case R.id.button_downloadText:
                getText();
                break;
            case R.id.button_downloadImage:
                getImage();
                break;
            case R.id.button_downloadWebsite:
                getWebsite();
                break;
        }
    }

    private void getWebsite() {
        if (Tools.isEmpty(etUrl.getText().toString())){
            etUrl.setError(getString(R.string.t_empty));
            return;
        }
        ivTest.setImageBitmap(null);
        tvTest.setText(null);
        new WebsiteDownloader().execute(etUrl.getText().toString());
    }

    private void getImage() {
        ivTest.setImageBitmap(null);
        tvTest.setText("");
        new ImageDownloader().execute();
    }

    private void getText() {
        ivTest.setImageBitmap(null);
        tvTest.setText("");
        new TextDownloader().execute();
    }

    private static class WebsiteDownloader extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setConnectTimeout(2000);
                urlConnection.connect();
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                Tools.error(e);
                return e.getLocalizedMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvTest.setText(s);
        }
    }

    private static class ImageDownloader extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                URL url = new URL("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.connect();
                InputStream is = huc.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (IOException e) {
                Tools.error(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivTest.setImageBitmap(bitmap);
        }
    }

    private static class TextDownloader extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://www.w3.org/TR/PNG/iso_8859-1.txt");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(2000);
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                Tools.error(e);
                return e.getLocalizedMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvTest.setText(s);
        }
    }

}
