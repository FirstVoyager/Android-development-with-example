package limitless.android.androiddevelopment.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmTestModel extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String number;
    private String address;

    public RealmTestModel(){

    }

    public RealmTestModel(int id, String name, String number, String address) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
