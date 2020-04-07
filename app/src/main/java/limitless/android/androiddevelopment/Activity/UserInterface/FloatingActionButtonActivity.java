package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingActionButtonActivity extends BaseActivity implements View.OnClickListener {

    // TODO: 12/24/19 add extend floating action button in next version

    private View vContainer, vClick, vGroup, vContact, vChannel, vSecretChat;
    private FloatingActionButton fab;
    private boolean show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fab);
        vContainer = findViewById(R.id.container);
        vClick = findViewById(R.id.view_click);
        vGroup = findViewById(R.id.linearLayout_newGroup);
        vContact = findViewById(R.id.linearLayout_newContact);
        vChannel = findViewById(R.id.linearLayout_newChannel);
        vSecretChat = findViewById(R.id.linearLayout_newSecretChat);

        fab.setOnClickListener(this);
        vClick.setOnClickListener(this);
        vGroup.setOnClickListener(this);
        vContact.setOnClickListener(this);
        vChannel.setOnClickListener(this);
        vSecretChat.setOnClickListener(this);
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
            case R.id.fab:
                menuAction();
                break;
            case R.id.linearLayout_newGroup:
                Tools.toast(this, getString(R.string.text_new_group));
                menuAction();
                break;
            case R.id.linearLayout_newContact:
                Tools.toast(this, getString(R.string.text_new_contact));
                menuAction();
                break;
            case R.id.linearLayout_newChannel:
                Tools.toast(this, getString(R.string.text_new_channel));
                menuAction();
                break;
            case R.id.linearLayout_newSecretChat:
                Tools.toast(this, getString(R.string.text_new_secret_chat));
                menuAction();
                break;
            case R.id.view_click:
                menuAction();
                break;
        }
    }

    private void menuAction() {
        if (show){
            vClick.setVisibility(View.GONE);
            Tools.toggleArrow(true, fab);
            vContainer.animate().alpha(0).setDuration(300).start();
        }else {
            vClick.setVisibility(View.VISIBLE);
            Tools.toggleArrow(false, fab);
            vContainer.animate().alphaBy(1).setDuration(300).start();
        }
        show = ! show;
    }
}
