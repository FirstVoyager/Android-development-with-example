package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitTodoModel {
    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("completed")
    private boolean completed;

    public RetrofitTodoModel(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public RetrofitTodoModel() {

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("userId : ").append(userId).append(System.lineSeparator());
            builder.append("id : ").append(id).append(System.lineSeparator());
            builder.append("title : ").append(title).append(System.lineSeparator());
            builder.append("completed : ").append(completed).append(System.lineSeparator());
            return builder.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }
}
