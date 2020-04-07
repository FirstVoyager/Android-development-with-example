package limitless.android.androiddevelopment.Model;

public class ModelUser {
    public String name;
    public String bio;
    public String username;
    public String lastMessage;
    public String number;
    public long id;
    public long date;
    public String imageUrl;
    public int notif;
    public boolean blocked;

    public ModelUser() {

    }

    public ModelUser(String name, String bio, String username, String lastMessage, String number, long id, long date, String imageUrl, int notif, boolean blocked) {
        this.name = name;
        this.bio = bio;
        this.username = username;
        this.lastMessage = lastMessage;
        this.number = number;
        this.id = id;
        this.date = date;
        this.imageUrl = imageUrl;
        this.notif = notif;
        this.blocked = blocked;
    }

}
