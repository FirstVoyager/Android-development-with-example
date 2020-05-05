package limitless.android.androiddevelopment.Activity.Basic.FileStorage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.VideoAdapter;
import limitless.android.androiddevelopment.Dialog.DialogSort;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.VideoModel;
import limitless.android.androiddevelopment.Data.Data;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ReadVideosActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private int requestStorage = 4001;
    private String sort = MediaStore.Video.VideoColumns.DATE_ADDED + " DESC";
    private String[] sorts = new String[]{"Date added", "Name", "Path", "Size", "Artist", "Album", "Resolution", "Width", "Height", "Duration"};
    private String[] columns = new String[]{
            MediaStore.Video.VideoColumns.DATE_ADDED,
            MediaStore.Video.VideoColumns.TITLE,
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.VideoColumns.SIZE,
            MediaStore.Video.VideoColumns.ARTIST,
            MediaStore.Video.VideoColumns.ALBUM,
            MediaStore.Video.VideoColumns.RESOLUTION,
            MediaStore.Video.VideoColumns.WIDTH,
            MediaStore.Video.VideoColumns.HEIGHT,
            MediaStore.Video.VideoColumns.DURATION
    };
    private SwipeRefreshLayout sfl;
    private RecyclerView rv;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_videos);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sfl = findViewById(R.id.swipeRefreshLayout);
        rv = findViewById(R.id.recyclerView);
        videoAdapter = new VideoAdapter(this, new ArrayList<VideoModel>(), null);
        rv.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        rv.setAdapter(videoAdapter);
        sfl.setOnRefreshListener(this);
        if (! Tools.permissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Tools.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, requestStorage);
            return;
        }
        getData();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.text_sort_by));
        mi.setIcon(R.drawable.ic_sort_white_24dp);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.text_sort_by))){
            DialogSort dialogSort = new DialogSort(sorts, columns, "Sort Videos", new StringListener() {
                @Override
                public void string(String s) {
                    sort = s;
                    getData();
                }
            });
            dialogSort.show(getSupportFragmentManager(), null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        sfl.setRefreshing(true);
        videoAdapter.clearAll();
        new GetVideos(sort).execute(getContentResolver());
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private class GetVideos extends AsyncTask<ContentResolver, Void, List<VideoModel>>{

        private String sort;

        public GetVideos(String sort) {
            this.sort = sort;
        }

        @Override
        protected List<VideoModel> doInBackground(ContentResolver... contentResolvers) {
            return Data.getAllVideos(contentResolvers[0], sort);
        }

        @Override
        protected void onPostExecute(List<VideoModel> videoModels) {
            super.onPostExecute(videoModels);
            sfl.setRefreshing(false);
            videoAdapter.setData(videoModels);
        }
    }

}
