package limitless.android.androiddevelopment.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectModel implements Parcelable {

    public String title;
    public String caption;
    public String imageUrl;
    public String googlePlay;
    public String buyUrl;
    public int price;
    public int image;

    public ProjectModel() {

    }

    public ProjectModel(String title, String caption, String imageUrl, String googlePlay, String buyUrl, int price, int image) {
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.googlePlay = googlePlay;
        this.buyUrl = buyUrl;
        this.price = price;
        this.image = image;
    }

    protected ProjectModel(Parcel in) {
        title = in.readString();
        caption = in.readString();
        imageUrl = in.readString();
        googlePlay = in.readString();
        buyUrl = in.readString();
        price = in.readInt();
        image = in.readInt();
    }

    public static final Creator<ProjectModel> CREATOR = new Creator<ProjectModel>() {
        @Override
        public ProjectModel createFromParcel(Parcel in) {
            return new ProjectModel(in);
        }

        @Override
        public ProjectModel[] newArray(int size) {
            return new ProjectModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(caption);
        dest.writeString(imageUrl);
        dest.writeString(googlePlay);
        dest.writeString(buyUrl);
        dest.writeInt(price);
        dest.writeInt(image);
    }
}
