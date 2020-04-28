package limitless.android.androiddevelopment.Activity.Basic.Notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;

public class CustomNotificationActivity extends BaseActivity implements View.OnClickListener {

    private NotificationManager manager;
    private int musicId = 101;
    private int progressId = 102;
    private int listId = 104;
    private static int actionID = 105;
    private static int imageId = 103;
    private static int lastImage = -1;
    private static final String closeAction = "closeAction";
    private static final String playAction = "playAction";
    private static final String nextAction = "nextAction";
    private static final String previousAction = "previousAction";
    private static final String favoriteAction = "favoriteAction";
    private static final String previousActionImage = "previousActionImage";
    private static final String nextActionImage = "nextActionImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_notification);
        init();
    }

    private void init() {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_musicPlayer).setOnClickListener(this);
        findViewById(R.id.button_imageSwitcher).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_musicPlayer:
                musicPlayer();
                break;
            case R.id.button_imageSwitcher:
                imageSwitcher(this);
                break;
        }
    }

    private PendingIntent pendingIntent(String s) {
        Intent intent = new Intent(this, CustomNotificationListener.class);
        intent.setAction(s);
        return PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    private void musicPlayer() {
        if (manager == null)
            return;
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_music_player);
        remoteViews.setImageViewBitmap(R.id.imageView_cover, BitmapFactory.decodeResource(getResources(), R.drawable.image_test_1));
        remoteViews.setImageViewBitmap(R.id.imageView_icon, BitmapFactory.decodeResource(getResources(), R.drawable.icon_main));
        remoteViews.setTextViewText(R.id.textView_name, getString(R.string.app_name));
        remoteViews.setTextViewText(R.id.textView_musicName, "Simple name for music");
        remoteViews.setOnClickPendingIntent(R.id.imageView_close, pendingIntent(closeAction));
        remoteViews.setOnClickPendingIntent(R.id.imageButton_next, pendingIntent(nextAction));
        remoteViews.setOnClickPendingIntent(R.id.imageButton_play, pendingIntent(playAction));
        remoteViews.setOnClickPendingIntent(R.id.imageButton_previous, pendingIntent(previousAction));
        remoteViews.setOnClickPendingIntent(R.id.imageButton_favorite, pendingIntent(favoriteAction));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setCustomContentView(remoteViews);
        builder.setSmallIcon(R.drawable.icon_main);
        manager.notify(musicId, builder.build());
    }

    private static void imageSwitcher(Context context) {
        int res;
        if (lastImage == R.drawable.image_code_header)
            res = R.drawable.image_test_1;
        else
            res = R.drawable.image_code_header;
        NotificationManager m = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (m == null)
            return;
        Intent intent = new Intent(context, CustomNotificationListener.class);
        intent.setAction(nextActionImage);
        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, intent, 0);
        intent = new Intent(context, CustomNotificationListener.class);
        intent.setAction(previousActionImage);
        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, intent, 0);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notifiaction_image_switcher);
        remoteViews.setImageViewBitmap(R.id.imageView, BitmapFactory.decodeResource(context.getResources(), res));
        remoteViews.setOnClickPendingIntent(R.id.imageButton_next, pNext);
        remoteViews.setOnClickPendingIntent(R.id.imageButton_previous, pPrevious);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentTitle("Image switcher");
        builder.setCustomBigContentView(remoteViews);
        m.notify(imageId, builder.build());

        lastImage = res;
    }

    public static class CustomNotificationListener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager == null)
                return;
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.icon_main);
            builder.setContentTitle("Action click");
            String s = intent.getAction();
            switch (s){
                case closeAction:
                    manager.cancelAll();
                    builder.setContentText("Close action");
                    break;
                case playAction:
                    builder.setContentText("Play action");
                    break;
                case nextAction:
                    builder.setContentText("Next action");
                    break;
                case previousAction:
                    builder.setContentText("Previous action");
                    break;
                case favoriteAction:
                    builder.setContentText("Favorite action");
                    break;
                case previousActionImage:
                    imageSwitcher(context);
                    builder.setContentText("Next image action");
                    break;
                case nextActionImage:
                    imageSwitcher(context);
                    builder.setContentText("Previous image action");
                    break;
            }
            manager.notify(actionID, builder.build());
        }
    }

}
