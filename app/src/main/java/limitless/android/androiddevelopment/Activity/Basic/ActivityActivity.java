package limitless.android.androiddevelopment.Activity.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityActivity extends BaseActivity {


    /*
    * onCreate --> 1
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    * onStart --> 2
    * */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
    * onResume --> 3
    * */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /*
    * onPause --> 4
    * */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /*
    * onDestroy --> 5
    * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(getString(R.string.title_info));
        mi.setIcon(R.drawable.ic_info_outline_black_24dp);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getTitle().equals(getString(R.string.title_info))){
            Tools.showInfoDialog(
                    getSupportFragmentManager(),
                    getString(R.string.text_activity),
                    getString(R.string.info_activity)
            );
        }
        return super.onOptionsItemSelected(item);
    }
}
