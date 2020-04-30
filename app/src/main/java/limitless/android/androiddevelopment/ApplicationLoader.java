package limitless.android.androiddevelopment;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import limitless.android.androiddevelopment.Other.Constant;
import limitless.android.androiddevelopment.Other.Tools;

public class ApplicationLoader extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // init ads
        if (BuildConfig.DEBUG){
            List<String> list = new ArrayList<>(Arrays.asList("848A1F9D7BA440BD4EB8D4B832E01D61"));
            RequestConfiguration rc = new RequestConfiguration
                    .Builder()
                    .setTestDeviceIds(list)
                    .build();
            MobileAds.setRequestConfiguration(rc);
        }
        MobileAds.initialize(this);
        // To Initialize Realm
        try {
            Realm.init(this);
            RealmConfiguration rfc = new RealmConfiguration
                    .Builder()
                    .name(Constant.realmDataBaseTest)
                    .schemaVersion(1)
                    .build();
            Realm.setDefaultConfiguration(rfc);
        } catch (Exception e) {
            Tools.error(e);
        }
    }
}
