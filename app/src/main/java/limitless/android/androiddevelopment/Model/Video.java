package limitless.android.androiddevelopment.Model;

import android.net.Uri;

public class Video {
    public String title;
    public Uri uri;
    public String album;
    public String artist;
    public String category;
    public String resolution;
    public int id;
    public int width;
    public int height;
    public int duration;
    public long size;
    public long dateAdded;

    public Video(String title, Uri uri, String album, String artist, String category, String resolution, int id, int width, int height, int duration, long size, long dateAdded) {
        this.title = title;
        this.uri = uri;
        this.album = album;
        this.artist = artist;
        this.category = category;
        this.resolution = resolution;
        this.id = id;
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.size = size;
        this.dateAdded = dateAdded;
    }

    public Video(){
        this.title = "";
        this.album = "";
        this.category = "";
        this.resolution = "";
        this.id = -1;
        this.width = -1;
        this.height = -1;
        this.duration = -1;
        this.dateAdded = -1;
    }

}
