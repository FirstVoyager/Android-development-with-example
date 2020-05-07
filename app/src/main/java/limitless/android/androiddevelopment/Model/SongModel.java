package limitless.android.androiddevelopment.Model;

import android.net.Uri;

public class SongModel {
    public String title;
    public Uri data;
    public String album;
    public String artist;
    public int id;
    public int albumId;
    public int artistID;
    public int duration;
    public long size;
    public long dateAdded;

    public SongModel(String title, Uri data, String album, String artist, int id, int albumId, int artistID, int duration, long size, long dateAdded) {
        this.title = title;
        this.data = data;
        this.album = album;
        this.artist = artist;
        this.id = id;
        this.albumId = albumId;
        this.artistID = artistID;
        this.duration = duration;
        this.size = size;
        this.dateAdded = dateAdded;
    }

    public SongModel(){

    }

}
