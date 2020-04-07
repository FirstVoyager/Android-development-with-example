package limitless.android.androiddevelopment.Interface;

import java.util.List;

import limitless.android.androiddevelopment.Model.Retrofit.RetrofitAlbumModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitCommentsModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitPhotoModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitPostsModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitTodoModel;
import limitless.android.androiddevelopment.Model.Retrofit.RetrofitUserModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitTestService {

    @GET("posts")
    Call<List<RetrofitPostsModel>> getPosts();

    @GET("comments")
    Call<List<RetrofitCommentsModel>> getComments();

    @GET("albums")
    Call<List<RetrofitAlbumModel>> getAlbums();

    @GET("photos")
    Call<List<RetrofitPhotoModel>> getPhotos();

    @GET("todos")
    Call<List<RetrofitTodoModel>> getTodos();

    @GET("users")
    Call<List<RetrofitUserModel>> getUsers();

}
