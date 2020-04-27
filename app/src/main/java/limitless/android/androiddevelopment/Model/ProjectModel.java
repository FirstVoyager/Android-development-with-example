package limitless.android.androiddevelopment.Model;

public class ProjectModel {
    public String title;
    public String caption;
    public String imageUrl;
    public String storeUrl;
    public String buyUrl;
    public int price;
    public int image;

    public ProjectModel() {

    }

    public ProjectModel(String title, String caption, String imageUrl, String storeUrl, String buyUrl, int price, int image) {
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.storeUrl = storeUrl;
        this.buyUrl = buyUrl;
        this.price = price;
        this.image = image;
    }
}
