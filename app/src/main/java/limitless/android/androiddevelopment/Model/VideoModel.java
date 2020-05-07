package limitless.android.androiddevelopment.Model;

import android.net.Uri;

public class VideoModel {
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

    public VideoModel(String title, Uri uri, String album, String artist, String category, String resolution, int id, int width, int height, int duration, long size, long dateAdded) {
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

    public VideoModel(){

    }

}
