package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BasicBroadcastReceiver extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText etMessage;
    private AppCompatTextView tvMessage;

    private static final String MESSAGE_ACTION = "MESSAGE_ACTION";
    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MESSAGE_ACTION)){
                if (intent.hasExtra(Intent.EXTRA_TEXT)){
                    tvMessage.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_broadcast_receiver);
        init();
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_ACTION);
        registerReceiver(messageReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(messageReceiver);
        super.onDestroy();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etMessage = findViewById(R.id.editText_message);
        tvMessage = findViewById(R.id.textView_message);
        findViewById(R.id.button_send).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mi.setIcon(R.drawable.ic_info_outline_black_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.title_broadcast_receiver),
                    getString(R.string.info_broadcast_receiver)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_send){
            if (Tools.isEmpty(etMessage.getText().toString())){
                etMessage.setError(getString(R.string.title_empty));
                return;
            }
            sendMessage();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent(MESSAGE_ACTION);
        intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
        sendBroadcast(intent);
    }


}
