package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitAddressModel {
    @SerializedName("street")
    private String street;
    @SerializedName("suite")
    private String suite;
    @SerializedName("city")
    private String city;
    @SerializedName("zipcode")
    private String zipcode;
    @SerializedName("geo")
    private RetrofitGeoModel geo;

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("street : ").append(street).append(System.lineSeparator());
            sb.append("suite : ").append(suite).append(System.lineSeparator());
            sb.append("city : ").append(city).append(System.lineSeparator());
            sb.append("zipcode : ").append(zipcode).append(System.lineSeparator());
            sb.append("geo : ").append(System.lineSeparator()).append(geo.toString()).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }

    public RetrofitAddressModel(String street, String suite, String city, String zipcode, RetrofitGeoModel geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    public RetrofitAddressModel() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public RetrofitGeoModel getGeo() {
        return geo;
    }

    public void setGeo(RetrofitGeoModel geo) {
        this.geo = geo;
    }
}
