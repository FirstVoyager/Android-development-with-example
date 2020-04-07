package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitAlbumModel {
    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;

    public RetrofitAlbumModel(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public RetrofitAlbumModel() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("userId : ").append(userId).append(System.lineSeparator());
            sb.append("id : ").append(id).append(System.lineSeparator());
            sb.append("title : ").append(title).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }

}
