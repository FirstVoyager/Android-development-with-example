package limitless.android.androiddevelopment.Model;

import android.net.Uri;

public class Song {
    public String title;
    public Uri uri;
    public String album;
    public String artist;
    public int id;
    public int albumId;
    public int artistID;
    public int duration;
    public long size;
    public long dateAdded;

    public Song(String title, Uri uri, String album, String artist, int id, int albumId, int artistID, int duration, long size, long dateAdded) {
        this.title = title;
        this.uri = uri;
        this.album = album;
        this.artist = artist;
        this.id = id;
        this.albumId = albumId;
        this.artistID = artistID;
        this.duration = duration;
        this.size = size;
        this.dateAdded = dateAdded;
    }

    public Song(){
        this.title = "";
        this.album = "";
        this.artist = "";
        this.id = -1;
        this.albumId = -1;
        this.artistID = -1;
        this.duration = -1;
        this.size = -1;
        this.dateAdded = -1;
    }

}
