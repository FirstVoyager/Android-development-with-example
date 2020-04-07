package limitless.android.androiddevelopment.Activity.UIMore.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ProfileTelegramActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_telegram);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(null);

        findViewById(R.id.linearLayout_phone).setOnClickListener(this);
        findViewById(R.id.linearLayout_bio).setOnClickListener(this);
        findViewById(R.id.linearLayout_notification).setOnClickListener(this);
        findViewById(R.id.linearLayout_photos).setOnClickListener(this);
        findViewById(R.id.linearLayout_links).setOnClickListener(this);
        findViewById(R.id.linearLayout_audio).setOnClickListener(this);
        findViewById(R.id.linearLayout_voice).setOnClickListener(this);
        findViewById(R.id.textView_secret).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem call = menu.add("Call");
        call.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        call.setIcon(R.drawable.ic_call_black_24dp);
        DrawableCompat.setTint(call.getIcon(), Color.WHITE);

        MenuItem more = menu.add("More");
        more.setIcon(R.drawable.ic_more_vert_black_24dp);
        more.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        DrawableCompat.setTint(more.getIcon(), Color.WHITE);

        return super.onCreateOptionsMenu(menu);
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

    }
}
