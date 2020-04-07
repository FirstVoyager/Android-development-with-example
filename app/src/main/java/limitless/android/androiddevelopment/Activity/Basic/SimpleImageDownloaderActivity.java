package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleImageDownloaderActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatImageView ivMain;
    private AppCompatEditText etUrl;
    private AppCompatButton btnDownload;
    private ProgressBar pb;

    public static final String IMAGE_DOWNLOAD_ACTION = "image_download_action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_image_downloader);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(imageDownloadBroadcast, new IntentFilter(IMAGE_DOWNLOAD_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(imageDownloadBroadcast);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.button_cancel){
            stopService();
        }else if (view.getId() == R.id.button_download){
            if (Tools.serviceIsRunnig(this, SimpleImageDownloaderService.class)){
                stopService(new Intent(this, SimpleImageDownloaderService.class));
            }
            if (Tools.isEmpty(etUrl.getText().toString())){
                etUrl.setError(getString(R.string.t_empty));
                return;
            }
            Intent intent = new Intent(this, SimpleImageDownloaderService.class);
            intent.putExtra(Intent.EXTRA_TEXT, etUrl.getText().toString());
            startService(intent);
            pb.setVisibility(View.VISIBLE);
            btnDownload.setVisibility(View.INVISIBLE);
        }
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivMain = findViewById(R.id.imageView_download);
        etUrl = findViewById(R.id.editText_url);
        btnDownload = findViewById(R.id.button_download);
        pb = findViewById(R.id.progressbar);

        findViewById(R.id.button_cancel).setOnClickListener(this);
        btnDownload.setOnClickListener(this);

        if (Tools.serviceIsRunnig(this, SimpleImageDownloaderService.class)){
            pb.setVisibility(View.VISIBLE);
            btnDownload.setVisibility(View.INVISIBLE);
        }
    }

    public void stopService(){
        pb.setVisibility(View.INVISIBLE);
        btnDownload.setVisibility(View.VISIBLE);
        stopService(new Intent(this, SimpleImageDownloaderService.class));
        Tools.toast(this, "Stop service");
    }

    public static class SimpleImageDownloaderService extends Service{

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if (intent.hasExtra(Intent.EXTRA_TEXT)){
                new Download().execute(intent.getStringExtra(Intent.EXTRA_TEXT));
            }else {
                stopSelf();
            }
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        private class Download extends AsyncTask<String, Void, byte[]>{

            @Override
            protected byte[] doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setDoOutput(true);
                    huc.connect();
                    InputStream is = huc.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[0xffff];
                    for (int len = is.read(bytes); len != -1; len = is.read(bytes)){
                        bos.write(bytes, 0, len);
                    }
                    return bos.toByteArray();
                } catch (IOException e) {
                    Tools.error(e);
                    stopSelf();
                }
                return null;
            }

            @Override
            protected void onPostExecute(byte[] bytes) {
                super.onPostExecute(bytes);
                Intent send = new Intent(IMAGE_DOWNLOAD_ACTION);
                send.putExtra(Intent.EXTRA_STREAM, bytes);
                sendBroadcast(send);
                stopSelf();
            }
        }


    }

    public BroadcastReceiver imageDownloadBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(IMAGE_DOWNLOAD_ACTION)){
                if (intent.hasExtra(Intent.EXTRA_STREAM)){
                    byte[] bytes = intent.getByteArrayExtra(Intent.EXTRA_STREAM);
                    if (bytes == null){
                        stopService();
                        return;
                    }
                    ivMain.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                }
                stopService();
            }
        }
    };

}
