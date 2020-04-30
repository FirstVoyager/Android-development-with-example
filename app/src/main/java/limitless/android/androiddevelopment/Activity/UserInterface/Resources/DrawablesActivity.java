package limitless.android.androiddevelopment.Activity.UserInterface.Resources;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;

import java.util.List;

public class DrawablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);
        init();
    }

    private void init() {
        // read drawable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppCompatImageView iv = findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.avatar_maid);

        // get all drawable names
        new DrawNames().execute();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawNames extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            List<String> strings = Tools.getDrawNameList(getResources());
            if (strings != null) {
                StringBuilder sb = new StringBuilder();
                for (String s : strings) {
                    sb.append(s).append(System.lineSeparator()).append(System.lineSeparator());
                }
                return sb.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String strings) {
            AppCompatTextView tv = findViewById(R.id.textView_all);
            tv.setText(strings);
            super.onPostExecute(strings);
        }
    }

}
