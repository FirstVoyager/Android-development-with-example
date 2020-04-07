package limitless.android.androiddevelopment.Model;

public class VideoModel {
    public String title;
    public String data;
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

    public VideoModel(String title, String data, String album, String artist, String category, String resolution, int id, int width, int height, int duration, long size, long dateAdded) {
        this.title = title;
        this.data = data;
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
        this.title = "";
        this.data = "";
        this.album = "";
        this.artist = "";
        this.category = "";
        this.resolution = "";
        this.id = -1;
        this.width = -1;
        this.height = -1;
        this.duration = -1;
        this.size = -1;
        this.dateAdded = -1;
    }

}
