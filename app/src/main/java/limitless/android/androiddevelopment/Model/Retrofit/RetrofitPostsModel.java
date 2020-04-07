package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitPostsModel {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public RetrofitPostsModel(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public RetrofitPostsModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("id : ").append(id).append(System.lineSeparator());
            sb.append("userId : ").append(userId).append(System.lineSeparator());
            sb.append("title : ").append(title).append(System.lineSeparator());
            sb.append("body : ").append(body).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }

}
