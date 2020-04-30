package limitless.android.androiddevelopment.Activity.Basic.Notification;

import androidx.annotation.NonNull;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Activity.MainActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BasicNotificationActivity extends BaseActivity implements View.OnClickListener {

    private int simpleId = 2001;
    private int urlId = 2002;
    private int soundId = 2003;
    private int openAppId = 2004;
    private int expandId = 2005;
    private int replyId = 2006;
    private int actionId = 2007;
    private int progressId = 2008;
    public static String replyKey = "replyKeyTest";
    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_basic);
        init();
    }

    private void init() {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_notification).setOnClickListener(this);
        findViewById(R.id.button_openUrl).setOnClickListener(this);
        findViewById(R.id.button_playSound).setOnClickListener(this);
        findViewById(R.id.button_openApp).setOnClickListener(this);
        findViewById(R.id.button_expandable).setOnClickListener(this);
        findViewById(R.id.button_actionButton).setOnClickListener(this);
        findViewById(R.id.button_reply).setOnClickListener(this);
        findViewById(R.id.button_downloadProgress).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_info_outline_white_24dp);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.infoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.t_notification),
                    getString(R.string.info_notification));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_notification:
                simple();
                break;
            case R.id.button_openUrl:
                openUrl();
                break;
            case R.id.button_playSound:
                playSound();
                break;
            case R.id.button_openApp:
                openApp();
                break;
            case R.id.button_expandable:
                expandable();
                break;
            case R.id.button_actionButton:
                actionButton();
                break;
            case R.id.button_reply:
                reply();
                break;
            case R.id.button_downloadProgress:
                progress();
                break;
        }
    }

    private void progress() {
        if (manager == null)
            return;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentText("Download progressbar");
        builder.setContentTitle("Progress");
        builder.setProgress(100, 0, true);
        manager.notify(progressId, builder.build());
    }

    private void reply() {
        if (manager == null)
            return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            RemoteInput.Builder inputBuilder = new RemoteInput.Builder(replyKey);
            inputBuilder.setLabel("Enter your message");
            Intent intent = new Intent(this, InputReceiver.class);
            PendingIntent inputReceiver = PendingIntent.getBroadcast(this, 0, intent, 0);
            Notification.Action.Builder actionBuilder = new Notification.Action.Builder(-1, "Input", inputReceiver);
            actionBuilder.addRemoteInput(inputBuilder.build());

            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.icon_main);
            builder.setContentTitle("Simple title for you");
            builder.setContentText("Please send your message");
            builder.addAction(actionBuilder.build());
            manager.notify(replyId, builder.build());
        }else {
            Tools.toast(this, "SDK >= KITKAT WATCH");
        }
    }

    private void actionButton() {
        if (manager == null)
            return;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentText("Select action button to response");
        builder.setContentTitle("Action button");
        builder.setSmallIcon(R.drawable.icon_main);

        Intent intent = new Intent(this, BroadcastReceiverAction.class);
        intent.putExtra(Intent.EXTRA_TEXT, actionId);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.addAction(R.drawable.ic_play_arrow_black_24dp, "Dismiss", dismissIntent);
        manager.notify(actionId, builder.build());


    }

    private void expandable() {
        if (manager == null)
            return;
        Notification.Builder b = new Notification.Builder(this);
        b.setSmallIcon(R.drawable.icon_main);
        b.setContentTitle(getString(R.string.text_expandable_notification));
        b.setContentText("Tab to notification to show big notification !");

        Notification.BigPictureStyle bps = new Notification.BigPictureStyle(b);
        bps.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.image_code_header));
        bps.setSummaryText("Code background");
        manager.notify(expandId, bps.build());
    }

    private void openApp() {
        if (manager == null)
            return;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder b = new Notification.Builder(this);
        b.setContentTitle("Open app");
        b.setContentText("Tap to notification to open app");
        b.setContentIntent(pendingIntent);
        b.setSmallIcon(R.drawable.icon_main);
        manager.notify(openAppId, b.build());
    }

    private void playSound() {
        if (manager == null)
            return;
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentText("Swipe right or left to remove notification");
        builder.setContentTitle("Play sound");
        builder.setSound(soundUri);
        manager.notify(soundId, builder.build());
    }

    private void openUrl() {
        if (manager == null)
            return;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.codecanyon_url)));
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentTitle("Open url");
        builder.setContentText("Tab to notification to open url");
        builder.setContentIntent(pi);
        manager.notify(urlId, builder.build());
    }

    private void simple() {
        if (manager == null)
            return;
        Notification n = new Notification.Builder(this)
                .setContentTitle("Sample title")
                .setContentText("This is a simple text for content text")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon_main).build();
        manager.notify(simpleId, n);
    }

    public static class BroadcastReceiverAction extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null){
                nm.cancel(intent.getIntExtra(Intent.EXTRA_TEXT, 0));
            }
        }
    }

    public static class InputReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (manager != null)
                    manager.cancelAll();

                Bundle bundle = RemoteInput.getResultsFromIntent(intent);
                String s = bundle.getString(replyKey);
                Tools.toast(context, s);
            }else {
                Tools.toast(context, "SDK >= KITKAT WATCH");
            }
        }
    }

}
