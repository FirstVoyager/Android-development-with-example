package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitGeoModel {
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public RetrofitGeoModel(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public RetrofitGeoModel() {

    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("lat  : ").append(lat).append(System.lineSeparator());
            sb.append("lng  : ").append(lng).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }
}
