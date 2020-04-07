package limitless.android.androiddevelopment;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import limitless.android.androiddevelopment.Other.Constant;
import limitless.android.androiddevelopment.Other.Tools;

public class ApplicationLoader extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // To Initialize Realm
        try {
            Realm.init(this);
            RealmConfiguration rfc = new RealmConfiguration.Builder()
                    .name(Constant.realmDataBaseTest)
                    .schemaVersion(1)
                    .build();
            Realm.setDefaultConfiguration(rfc);
        } catch (Exception e) {
            Tools.error(e);
        }
    }
}
