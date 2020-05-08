package limitless.android.androiddevelopment.Model;

import android.net.Uri;

public class Photo {
    public int id;
    public int height;
    public int width;
    public long size;
    public long dateAdded;
    public String title;
    public Uri uri;
    public String des;
    public String latitude;
    public String longitude;

    public Photo(int id, int height, int width, long size, long dateAdded, String title, Uri uri, String des, String latitude, String longitude) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.size = size;
        this.dateAdded = dateAdded;
        this.title = title;
        this.uri = uri;
        this.des = des;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Photo() {
        this.id = -1;
        this.height = -1;
        this.width = -1;
        this.size = -1;
        this.dateAdded = -1;
        this.title = "";
        this.des = "";
        this.latitude = "";
        this.longitude = "";
    }

}
