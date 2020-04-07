package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.BuildConfig;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class NotificationStylingActivity extends BaseActivity implements View.OnClickListener {

    private NotificationManager manager;
    private int mediaId = 3001;
    private int messageId = 3002;
    private int pictureId = 3003;
    private int textId = 3004;
    private int inboxId = 3005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_styling);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_mediaStyle).setOnClickListener(this);
        findViewById(R.id.button_messagingStyle).setOnClickListener(this);
        findViewById(R.id.button_bigPictureStyle).setOnClickListener(this);
        findViewById(R.id.button_bigTextStyle).setOnClickListener(this);
        findViewById(R.id.button_inboxStyle).setOnClickListener(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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
            case R.id.button_mediaStyle:
                mediaStyle();
                break;
            case R.id.button_messagingStyle:
                messagingStyle();
                break;
            case R.id.button_bigPictureStyle:
                bigPictureStyle();
                break;
            case R.id.button_bigTextStyle:
                bigTextStyle();
                break;
            case R.id.button_inboxStyle:
                inboxStyle();
                break;
        }
    }

    private void inboxStyle() {
        if (manager == null)
            return;
        Notification.InboxStyle style = new Notification.InboxStyle();
        style.addLine("Line 1 : your message");
        style.addLine("Line 2 : your message");
        style.addLine("Line 3 : your message");
        style.addLine("Line 4 : your message");
        style.addLine("Line 5 : your message");
        style.setBigContentTitle("5 new message");
        style.setSummaryText("+100 more message");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentTitle("Mail app");
        builder.setContentText("you have new message tab to see more!");
        builder.setStyle(style);
        manager.notify(inboxId, builder.build());
    }

    private void bigTextStyle() {
        if (manager == null)
            return;
        Notification.BigTextStyle style = new Notification.BigTextStyle();
        style.bigText(getString(R.string.info_normal_permissions));
        style.setBigContentTitle("Normal permissions");
        style.setSummaryText("What is normal permission?");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setContentTitle("Normal permissions");
        builder.setContentText("Tab to see more");
        builder.setStyle(style);
        manager.notify(textId, builder.build());
    }

    private void bigPictureStyle() {
        if (manager == null)
            return;
        Notification.BigPictureStyle style = new Notification.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.image_code_header));
        style.setBigContentTitle("Big image");
        style.setSummaryText("you see image in big style");

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Big picture style");
        builder.setContentText("Tab to see more");
        builder.setSmallIcon(R.drawable.icon_main);
        builder.setStyle(style);
        manager.notify(pictureId, builder.build());
    }

    private void messagingStyle() {
        if (manager == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            long time = System.currentTimeMillis();
            Notification.MessagingStyle ms = new Notification.MessagingStyle("");
            ms.setConversationTitle("Conversation title");
            ms.addMessage("Message from person one", time, "Person 1");
            ms.addMessage("Message from person two", time, "Person 2");
            ms.addMessage("Message from person tree", time, "Person 3");
            ms.addMessage("Message from person four", time, "Person 4");

            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.icon_main);
            builder.setStyle(ms);
            manager.notify(messageId, builder.build());
        }else {
            Tools.toast(this, "SDK >= N");
        }
    }

    private void mediaStyle() {
        if (manager == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Notification.Action action1 = new Notification.Action(R.drawable.ic_skip_next_black_24dp, "Next", null);
            Notification.Action action2 = new Notification.Action(R.drawable.ic_play_arrow_black_24dp, "Play", null);
            Notification.Action action3 = new Notification.Action(R.drawable.ic_skip_previous_black_24dp, "Previous", null);

            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.icon_main);
            builder.setContentText("Your music name and etc");
            builder.setContentTitle("Singer name");
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_microphone_512dp));
            builder.addAction(action1);
            builder.addAction(action2);
            builder.addAction(action3);
            builder.setStyle(new Notification.MediaStyle().setShowActionsInCompactView(0, 1));
            manager.notify(mediaId, builder.build());
        }else {
            Tools.toast(this, "SDK >= LOLLIPOP");
        }
    }
}
