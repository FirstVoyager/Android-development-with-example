package limitless.android.androiddevelopment.Model;

public class PhotoModel {
    public int id;
    public int height;
    public int width;
    public long size;
    public long dateAdded;
    public String title;
    public String data;
    public String des;
    public String latitude;
    public String longitude;

    public PhotoModel(int id, int height, int width, long size, long dateAdded, String title, String data, String des, String latitude, String longitude) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.size = size;
        this.dateAdded = dateAdded;
        this.title = title;
        this.data = data;
        this.des = des;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PhotoModel() {
        this.id = -1;
        this.height = -1;
        this.width = -1;
        this.size = -1;
        this.dateAdded = -1;
        this.title = "";
        this.data = "";
        this.des = "";
        this.latitude = "";
        this.longitude = "";
    }

}
