package limitless.android.androiddevelopment.Activity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Data.SharePref;
import limitless.android.androiddevelopment.Other.Ads;
import limitless.android.androiddevelopment.R;

public abstract class BaseActivity extends AppCompatActivity {

    private SharePref sharePref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePref = new SharePref(this);

        sharePref.put(SharePref.LOAD_ACTIVITY_COUNT, sharePref.get(SharePref.LOAD_ACTIVITY_COUNT, 0) + 1);
        long l = (5 * 60 * 1000);
        long n = new Date().getTime() - sharePref.get(SharePref.LAST_SHOW_ADS, l);
        int loadCount = sharePref.get(SharePref.LOAD_ACTIVITY_COUNT, 0);
        if (n >= (5 * 60 * 1000) && loadCount >= 5){
            InterstitialAd ad = new InterstitialAd(this);
            ad.setAdUnitId(getString(R.string.ads_interstitial));
            ad.loadAd(Ads.adRequest());
            ad.setAdListener(new AdListener(){
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    ad.show();
                    sharePref.put(SharePref.LAST_SHOW_ADS, new Date().getTime());
                    sharePref.put(SharePref.LOAD_ACTIVITY_COUNT, 0);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
