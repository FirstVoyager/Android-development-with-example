package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitPhotoModel {
    @SerializedName("albumId")
    private int albumId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public RetrofitPhotoModel(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public RetrofitPhotoModel() {

    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("albumId : ").append(albumId).append(System.lineSeparator());
            sb.append("id : ").append(id).append(System.lineSeparator());
            sb.append("title : ").append(title).append(System.lineSeparator());
            sb.append("url : ").append(url).append(System.lineSeparator());
            sb.append("thumbnailUrl : ").append(thumbnailUrl).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }

}
