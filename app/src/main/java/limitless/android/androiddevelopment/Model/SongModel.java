package limitless.android.androiddevelopment.Model;

public class SongModel {
    public String title;
    public String data;
    public String album;
    public String artist;
    public int id;
    public int albumId;
    public int artistID;
    public int duration;
    public long size;
    public long dateAdded;

    public SongModel(String title, String data, String album, String artist, int id, int albumId, int artistID, int duration, long size, long dateAdded) {
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
        this.title = "";
        this.data = "";
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
