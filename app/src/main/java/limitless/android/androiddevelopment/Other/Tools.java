package limitless.android.androiddevelopment.Other;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import limitless.android.androiddevelopment.Activity.CodeMore.ClipboardManagerActivity;
import limitless.android.androiddevelopment.Activity.MainActivity;
import limitless.android.androiddevelopment.BuildConfig;
import limitless.android.androiddevelopment.Dialog.DialogInfo;
import limitless.android.androiddevelopment.Dialog.StyleMapDialog;
import limitless.android.androiddevelopment.R;

public class Tools {

    public static Toast toast;

    /**
     * Show toast with string
     * @param context
     * @param s message want to show
     */
    public static void toast(Context context, String s) {
        if (context == null || s == null)
            return;
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void toast(Context context, @StringRes int id){
        if (context == null)
            return;
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(context, id, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void customToast(Context context, @StringRes int id){
        customToast(context, context.getString(id));
    }

    public static void customToast(Context context, String s){
        if (context == null || s == null)
            return;
        CardView cardView = new CardView(context);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        cardView.setRadius(convertDipToPx(context.getResources().getDisplayMetrics(), 8));
        cardView.setCardBackgroundColor(Color.BLACK);

        AppCompatTextView tv = new AppCompatTextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
        int margin = (int) convertDipToPx(context.getResources().getDisplayMetrics(), 12);
        lp.gravity = Gravity.CENTER;
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        lp.topMargin = margin;
        lp.bottomMargin = margin;
        tv.setText(s);
        if (Build.VERSION.SDK_INT < 23) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tv.setTextAppearance(context, android.R.style.TextAppearance_Material_Body1);
            }
        } else {
            tv.setTextAppearance(android.R.style.TextAppearance_Material_Body1);
        }
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setLines(1);
        tv.setSingleLine(true);
        tv.setTextColor(Color.WHITE);
        cardView.addView(tv);

        if (toast != null)
            toast.cancel();
        toast = new Toast(context);
        toast.setView(cardView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Check if is open close it
     * @param drawerLayout
     */
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void fragmentCommit(FragmentTransaction ft) {
        if (ft == null)
            return;
        try {
            ft.commit();
        }catch (Exception e){
            error(e);
            try {
                ft.commitNow();
            }catch (Exception e1){
                error(e1);
            }
        }
    }

    /**
     * Print error if is debug version
     * @param e
     */
    public static void error(Exception e){
        if (e == null)
            return;
        if (BuildConfig.DEBUG)
            e.printStackTrace();
    }

    /**
     * Start activity with context and intent
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        if (context == null || intent == null)
            return;
        try {
            context.startActivity(intent);
        }catch (Exception e){
            error(e);
        }
    }

    /**
     * Start activity with calss
     * @param context
     * @param aClass
     */
    public static void startActivity(Context context, Class<?> aClass) {
        if (context == null || aClass == null)
            return;
        startActivity(context, new Intent(context, aClass));
    }

    public static float convertDipToPx(DisplayMetrics dm, int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, dm);
    }

    /**
     * Open url
     * @param context
     * @param url
     */
    public static void openUrl(Context context, String url) {
        if (context == null || url == null)
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(context, intent);
    }

    /**
     * Check permission granted or no
     * @param context
     * @param s permission
     * @return granted result
     */
    public static boolean permissionGranted(Context context, String s) {
        if (context == null || s == null)
            return false;
        return ContextCompat.checkSelfPermission(context, s) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request new permission
     * @param activity return result to this activity
     * @param p want to get permission
     * @param code for check result
     */
    public static void requestPermission(Activity activity, String p, int code) {
        if (activity == null || code < 0)
            return;
        ActivityCompat.requestPermissions(activity, new String[]{p}, code);
    }

    /**
     * Request permission list
     * @param activity
     * @param permissions
     * @param code
     */
    public static void requestPermission(Activity activity, String[] permissions, int code){
        if (activity == null || permissions == null || code < 0)
            return;
        ActivityCompat.requestPermissions(activity, permissions, code);
    }

    public static void showInfoDialog(FragmentManager fm, String title, String body) {
        DialogInfo info = new DialogInfo();
        Bundle bundle = new Bundle();
        bundle.putString(DialogInfo.title, title);
        bundle.putString(DialogInfo.body, body);
        info.setArguments(bundle);
        info.show(fm, null);
    }

    /**
     * Check string is empty or no
     * @param s
     * @return is empty or no
     */
    public static boolean isEmpty(String s) {
        if (s == null)
            return true;
        return s.isEmpty();
    }

    public static String convertDate(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-E hh-mm-ss", Locale.getDefault());
        return df.format(time);
    }

    public static boolean serviceIsRunning(Context context, Class<?> aClass) {
        if (context == null || aClass == null)
            return false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null)
            return false;
        for (ActivityManager.RunningServiceInfo info : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (info.service.getClassName().equals(aClass.getName()))
                return true;
        }
        return false;
    }

    public static Bitmap roundBitmap(@NonNull Bitmap bitmap, float x, float y) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawRoundRect(rectF, x, y, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static String displayDuration(int duration) {
        if (duration <= 0)
            return "00:00";
        int h = duration / 3600000;
        int min = (duration / 60000) % 60000;
        int sec = duration % 60000 / 60000;
//        if (h <= 0){
//            return String.format(Locale.getDefault(), "%02d:%02d", min, sec);
//        }else if (min <= 0){
//            return String.format(Locale.getDefault(), "%02ds", sec);
//        }else if (sec <= 0){
//            return "0 second";
//        }
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", h, min, sec);
    }

    public static boolean isAudio(String s) {
        return s.endsWith(".3gp") ||
                s.endsWith(".flac") ||
                s.endsWith(".ogg") ||
                s.endsWith(".mp3");
    }

    public static boolean isVideo(String s){
        return s.endsWith(".mp4") ||
                s.endsWith(".webm") ||
                s.endsWith(".mkv");
    }

    /**
     * Check file is photo or no
     * @param s file name
     * @return true => is photo
     */
    public static boolean isPhoto(String s){
        return s.endsWith(".png") ||
                s.endsWith(".jpg") ||
                s.endsWith("jpeg") ||
                s.endsWith(".bmp") ||
                s.endsWith(".gif") ||
                s.endsWith(".webp");
    }

    public static long randomDate() {
        Date date = new Date();

        return date.getTime();
    }

    public static long randomNumber() {
        Random random = new Random();
        return random.nextInt(652200);
    }

    public static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static void toggleArrow(boolean b, View view) {
        if (b)
            view.animate().setDuration(150).rotation(90);
        else
            view.animate().setDuration(150).rotation(45);
    }

    public static List<String> getDrawNameList(Resources rs) {
        Field[] fields = R.drawable.class.getFields();
        List<String> names = new ArrayList<>();
        for (Field f : fields) {
            try {
                String s = rs.getResourceName(f.getInt(null));
                names.add(s.substring(s.lastIndexOf("/") + 1));
            } catch (IllegalAccessException e) {
                error(e);
            }
        }
        return names;
    }

    public static String convertInputStreamToString(InputStream is) {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            while ((s = br.readLine()) != null){
                sb.append(s).append(System.lineSeparator());
            }
        } catch (IOException e) {
            error(e);
        }
        return sb.toString();
    }

    public static Uri rawVideoUri(Context context, int rawPath) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + rawPath);
    }

    public static String convertLongToTime(long l, boolean h, boolean m, boolean s, boolean ms) {
        Date date = new Date(l);
        String pattern = null;
        if (h)
            pattern = "HH";
        if (m){
            if (h)
                pattern += ":mm";
            else
                pattern = "mm";
        }
        if (s){
            if (m)
                pattern += ":ss";
            else
                pattern += "ss";
        }
        if (ms){
            if (s)
                pattern += ".SS";
            else
                pattern += "SS";
        }
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static void setSystemBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void setSystemBarLight(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = activity.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void setSystemBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    /**
     * Show keyboard if can {@link InputMethodManager}
     * @param context
     * @param view
     */
    public static void showKeyboard(Context context, View view){
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null){
            manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Hide keyboard if can {@link InputMethodManager}
     * @param context
     * @param view
     */
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager manager =(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null){
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Copy message to clipboard with custom title
     * @param context
     * @param title for clipboard
     * @param description msq want to copy
     * @return copy or no
     */
    public static boolean copyToClipboard(Context context, String title, String description) {
        try {
            if (description == null)
                return false;
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cd = ClipData.newPlainText(title, description);
            cm.setPrimaryClip(cd);
            return true;
        }catch (Exception e){
            error(e);
        }
        return false;
    }

    public static HashMap<String, String> getClipboard(Context context) {
        if (context == null)
            return null;
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData cd = cm.getPrimaryClip();
        String title = cd.getDescription().getLabel().toString();
        String description = cd.getItemAt(0).getText().toString();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(title, description);
        return hashMap;
    }

    public static String getKeyValueHashMap(Set<Map.Entry<String, String>> entries) {
        for (Map.Entry<String, String> entry :entries){
            return entry.getKey();
        }
        return null;
    }

    /**
     * Load image and set from resources
     * @param context
     * @param res image resources id
     * @param iv want to set in it
     */
    public static void loadImage(Context context, @DrawableRes int res, ImageView iv) {
        if (context == null || iv == null)
            return;
        Glide.with(context).load(res).into(iv);
    }

    /**
     * Load image and set from url or path
     * @param context
     * @param url for image
     * @param iv want to set in it
     */
    public static void loadImage(Context context, String url, ImageView iv){
        if (context == null || url == null  || iv == null)
            return;
        Glide.with(context).load(url).into(iv);
    }

    /**
     * Load and set image from drawable
     * @param context
     * @param drawable
     * @param iv
     */
    public static void loadImage(Context context, Drawable drawable, ImageView iv){
        if (context == null || drawable == null || iv == null)
            return;
        Glide.with(context).load(drawable).into(iv);
    }

    /**
     * Convert resource drawable to {@link BitmapDescriptor} for use in {@link com.google.android.gms.maps.GoogleMap}
     * @param res id for drawable
     * @return
     */
    public static BitmapDescriptor bitmapDescriptor(@DrawableRes int res) {
         BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(res);
        return bd;
    }

    /**
     * Convert String files in asset folder to String
     * @param assets Asset manager
     * @param s file name
     * @return a string
     */
    public static String assetToString(AssetManager assets, String s) {
        try {
            InputStream inputStream = assets.open(s);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            error(e);
        }
        return null;
    }

    /**
     * Show Dialog if can
     * @param fm FragmentManagerb
     * @param df Dialog Fragment
     */
    public static void showDialog(FragmentManager fm, DialogFragment df) {
        if (fm == null || df == null)
            return;
        try {
            df.show(fm, null);
        } catch (Exception e) {
            error(e);
        }
    }

    public static void openEmail(Context context, String subject, String email) {
        if (context == null || email == null)
            return;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.fromParts("mailto", email, null));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Write your email");
        startActivity(context, intent);
    }

    public static void shareText(Context context, String msg) {
        if (context == null || msg == null)
            return;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(context, intent);
    }
}
