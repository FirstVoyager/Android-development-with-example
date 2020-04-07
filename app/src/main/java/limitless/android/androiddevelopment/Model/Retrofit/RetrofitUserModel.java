package limitless.android.androiddevelopment.Model.Retrofit;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Other.Tools;

public class RetrofitUserModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private RetrofitAddressModel address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("website")
    private String webSite;
    @SerializedName("company")
    private RetrofitCompanyModel company;

    @NonNull
    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("id : ").append(id).append(System.lineSeparator());
            sb.append("name : ").append(name).append(System.lineSeparator());
            sb.append("username : ").append(username).append(System.lineSeparator());
            sb.append("email : ").append(email).append(System.lineSeparator());
            sb.append("address : ").append(address).append(System.lineSeparator());
            sb.append("phone : ").append(phone).append(System.lineSeparator());
            sb.append("website : ").append(webSite).append(System.lineSeparator());
            return sb.toString();
        }catch (Exception e){
            Tools.error(e);
        }
        return super.toString();
    }

    public RetrofitUserModel(int id, String name, String username, String email, RetrofitAddressModel address, String phone, String webSite, RetrofitCompanyModel company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.webSite = webSite;
        this.company = company;
    }

    public RetrofitUserModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RetrofitAddressModel getAddress() {
        return address;
    }

    public void setAddress(RetrofitAddressModel address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public RetrofitCompanyModel getCompany() {
        return company;
    }

    public void setCompany(RetrofitCompanyModel company) {
        this.company = company;
    }
}
