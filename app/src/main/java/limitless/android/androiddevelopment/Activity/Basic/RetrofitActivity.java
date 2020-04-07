package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Adapter.AdapterSimpleText;
import limitless.android.androiddevelopment.Interface.RetrofitTestService;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitAlbumModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitCommentsModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitPhotoModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitPostsModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitTodoModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitUserModel;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RetrofitActivity extends BaseActivity implements View.OnClickListener {

    private RetrofitTestService testService;
    private RecyclerView recyclerView;
    private AdapterSimpleText adapterSimpleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterSimpleText = new AdapterSimpleText(this, new ArrayList<String>());
        setUpRetrofit();

        findViewById(R.id.button_posts).setOnClickListener(this);
        findViewById(R.id.button_comments).setOnClickListener(this);
        findViewById(R.id.button_albums).setOnClickListener(this);
        findViewById(R.id.button_photos).setOnClickListener(this);
        findViewById(R.id.button_todos).setOnClickListener(this);
        findViewById(R.id.button_users).setOnClickListener(this);
        recyclerView.setAdapter(adapterSimpleText);


    }

    private void setUpRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        testService = retrofit.create(RetrofitTestService.class);
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
        adapterSimpleText.clear();
        switch (v.getId()){
            case R.id.button_posts:
                getPosts();
                break;
            case R.id.button_comments:
                getComments();
                break;
            case R.id.button_albums:
                getAlbums();
                break;
            case R.id.button_photos:
                getPhotos();
                break;
            case R.id.button_todos:
                getTodos();
                break;
            case R.id.button_users:
                getUsers();
                break;
        }
    }

    private void getUsers() {
        Call<List<RetrofitUserModel>> call = testService.getUsers();
        call.enqueue(new Callback<List<RetrofitUserModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitUserModel>> call, Response<List<RetrofitUserModel>> response) {
                if (response.body() != null){
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitUserModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null){
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
                }
            }
        });
    }

    private void getPhotos() {
        Call<List<RetrofitPhotoModel>> call = testService.getPhotos();
        call.enqueue(new Callback<List<RetrofitPhotoModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitPhotoModel>> call, Response<List<RetrofitPhotoModel>> response) {
                if (response.body() != null){
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitPhotoModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null){
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
                }
            }
        });
    }

    private void getComments() {
        Call<List<RetrofitCommentsModel>> call = testService.getComments();
        call.enqueue(new Callback<List<RetrofitCommentsModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitCommentsModel>> call, Response<List<RetrofitCommentsModel>> response) {
                if (response.body() != null){
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitCommentsModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null){
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
                }
            }
        });
    }

    private void getAlbums() {
        Call<List<RetrofitAlbumModel>> call = testService.getAlbums();
        call.enqueue(new Callback<List<RetrofitAlbumModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitAlbumModel>> call, Response<List<RetrofitAlbumModel>> response) {
                if (response.body() != null){
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitAlbumModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null)
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<RetrofitPostsModel>> call = testService.getPosts();
        call.enqueue(new Callback<List<RetrofitPostsModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitPostsModel>> call, Response<List<RetrofitPostsModel>> response) {
                if (response.body() != null){
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitPostsModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null)
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
            }
        });
    }

    private void getTodos() {
        Call<List<RetrofitTodoModel>> call = testService.getTodos();
        call.enqueue(new Callback<List<RetrofitTodoModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitTodoModel>> call, Response<List<RetrofitTodoModel>> response) {
                if (response.body() != null) {
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        strings.add(response.body().get(i).toString());
                    }
                    adapterSimpleText.setData(strings);
                }

            }

            @Override
            public void onFailure(Call<List<RetrofitTodoModel>> call, Throwable t) {
                if (t.getLocalizedMessage() != null){
                    Tools.toast(RetrofitActivity.this, t.getLocalizedMessage());
                }

            }
        });
    }

}


