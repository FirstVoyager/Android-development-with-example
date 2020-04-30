package limitless.android.androiddevelopment.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    public static final String shareName = "sharepref.name";
    public static final String SMS_BODY = "sms_body";
    public static final String LAST_SMS_RECEIVED_DATE = "last_sms_received_date";
    public static final String LAST_SHOW_ADS = "last_show_Ads";
    public static final String LOAD_ACTIVITY_COUNT = "load_activity_count";
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePref(Context context){
        sp = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
    }

    @SuppressLint("CommitPrefEdits")
    private void setEditor(){
        editor = sp.edit();
    }

    public void put(String key, String value){
        setEditor();
        editor.putString(key, value).commit();
    }

    public String get(String key, String def){
        return sp.getString(key, def);
    }

    public void put(String key, boolean value){
        setEditor();
        editor.putBoolean(key, value).commit();
    }

    public boolean get(String key, boolean def){
        return sp.getBoolean(key, def);
    }

    public void put(String key, int value){
        setEditor();
        editor.putInt(key, value).commit();
    }

    public int get(String key, int def){
        return sp.getInt(key, def);
    }

    public void put(String key, long value){
        setEditor();
        editor.putLong(key, value).commit();
    }

    public long get(String key, long def){
        return sp.getLong(key, def);
    }


}
