package limitless.android.androiddevelopment.Activity.Basic.FileStorage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.SongsAdapter;
import limitless.android.androiddevelopment.Dialog.DialogSort;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.SongModel;
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

public class ReadSongsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int requestStorage = 2004;
    private String sortSongs = MediaStore.Audio.AudioColumns.DATE_ADDED + " DESC";
    private RecyclerView rv;
    private SongsAdapter songsAdapter;
    private SwipeRefreshLayout sfl;
    private String[] sorts = new String[]{"Date added", "Name", "Artist", "Album", "Path", "Size", "Duration"};
    private String[] column = new String[]{
            MediaStore.Audio.AudioColumns.DATE_ADDED,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ARTIST,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.SIZE,
            MediaStore.Audio.AudioColumns.DURATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_songs);
        init();
    }

    private void init() {
        rv = findViewById(R.id.recyclerView);
        songsAdapter = new SongsAdapter(this, new ArrayList<SongModel>(), null);
        sfl = findViewById(R.id.swipeRefreshLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(songsAdapter);
        sfl.setOnRefreshListener(this);
        if (! Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, requestStorage);
            return;
        }
        getData();
    }

    private void getData() {
        sfl.setRefreshing(true);
        songsAdapter.clearAll();
        new GetSongs().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.text_sort_by));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_sort_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.text_sort_by))){
            DialogSort sort = new DialogSort(sorts, column,"Sort songs", new StringListener() {
                @Override
                public void string(String s) {
                    sortSongs = s;
                    getData();
                }
            });
            sort.show(getSupportFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == requestStorage){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getData();
            }else {
                Tools.toast(this, "Storage permission denied !");
                onBackPressed();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private class GetSongs extends AsyncTask<Void, Void, List<SongModel>>{

        @Override
        protected List<SongModel> doInBackground(Void... voids) {
            return Data.getAllSongs(ReadSongsActivity.this, sortSongs);
        }

        @Override
        protected void onPostExecute(List<SongModel> songModels) {
            super.onPostExecute(songModels);
            sfl.setRefreshing(false);
            songsAdapter.setData(songModels);
        }
    }

}
