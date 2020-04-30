package limitless.android.androiddevelopment.Other;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import limitless.android.androiddevelopment.BuildConfig;

public class Ads {

    public static AdRequest adRequest(){
        return new AdRequest.Builder().build();
    }

}
