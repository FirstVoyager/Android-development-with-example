package limitless.android.androiddevelopment.Data;


import java.util.ArrayList;
import java.util.List;

import limitless.android.androiddevelopment.Model.ModelUser;
import limitless.android.androiddevelopment.Other.Tools;

public class DataGenerator {


    public static List<ModelUser> generateUser(int count){
        List<ModelUser> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ModelUser u = new ModelUser();
            u.name = "Name " + i;
            u.username = "username_" + i;
            u.lastMessage = "This a simple message for you and other " + i;
            u.date = Tools.randomDate();
            u.bio = "simple bio " + i;
            u.blocked = false;
            u.id = Tools.randomNumber();
            u.notif = Tools.randomNumber(0, 10);
            u.imageUrl = "https://s3.amazonaws.com/uifaces/faces/twitter/mahdif/128.jpg";
            u.number = "091097434156461";
            users.add(u);
        }
        return users;
    }

}
