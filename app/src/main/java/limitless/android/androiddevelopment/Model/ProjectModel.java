package limitless.android.androiddevelopment.Model;

public class ProjectModel {
    public String title;
    public String caption;
    public String imageUrl;
    public int price;
    public int image;

    public ProjectModel() {

    }

    public ProjectModel(String title, String caption, String imageUrl, int price, int image) {
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.price = price;
        this.image = image;
    }
}
