package limitless.android.androiddevelopment.Activity.Basic.FileStorage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.PhotoAdapter;
import limitless.android.androiddevelopment.Dialog.DialogSort;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.PhotoModel;
import limitless.android.androiddevelopment.Data.Data;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ReadPhotosActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static int storagePermission = 2002;
    private String sortPhotos = MediaStore.Images.Media.DATE_ADDED + " DESC";
    private RecyclerView rv;
    private SwipeRefreshLayout sfl;
    private PhotoAdapter photoAdapter;
    private String[] sorts = new String[]{"Date added", "Name", "Path", "Size", "Height", "Width"};
    private String[] column = new String[]{
            MediaStore.Images.ImageColumns.DATE_ADDED,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.SIZE,
            MediaStore.Images.ImageColumns.HEIGHT,
            MediaStore.Images.ImageColumns.WIDTH
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_photos);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = findViewById(R.id.recyclerView);
        photoAdapter = new PhotoAdapter(this, new ArrayList<PhotoModel>(), null);
        sfl = findViewById(R.id.swipeRefreshLayout);

        rv.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        rv.setAdapter(photoAdapter);
        sfl.setOnRefreshListener(this);

        if (! Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, storagePermission);
            return;
        }
        getData();
    }

    private void getData() {
        sfl.setRefreshing(true);
        new GetPhotos().execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storagePermission){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getData();
            }else {
                Tools.toast(this, "storage permission denied !");
                onBackPressed();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.text_sort_by));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_sort_white_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.text_sort_by))){
            DialogSort sort = new DialogSort(sorts, column, "Sort photos", new StringListener() {
                @Override
                public void string(String s) {
                    sortPhotos = s;
                    getData();
                }
            });
            sort.show(getSupportFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        photoAdapter.clearAll();
        getData();
    }

    private class GetPhotos extends AsyncTask<Void, Void, List<PhotoModel>>{

        @Override
        protected List<PhotoModel> doInBackground(Void... voids) {
            return Data.getAllPhotos(ReadPhotosActivity.this, sortPhotos);
        }

        @Override
        protected void onPostExecute(List<PhotoModel> photoModels) {
            sfl.setRefreshing(false);
            photoAdapter.setData(photoModels);
            super.onPostExecute(photoModels);
        }
    }
}

