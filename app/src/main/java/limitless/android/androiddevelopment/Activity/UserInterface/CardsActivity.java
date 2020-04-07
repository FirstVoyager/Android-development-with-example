package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.view.View;

public class CardsActivity extends BaseActivity implements View.OnClickListener {

    private View cv1, cv2, cv3, cv4, cv5, cv6, cv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cv1 = findViewById(R.id.cardView_1);
        cv2 = findViewById(R.id.cardView_2);
        cv3 = findViewById(R.id.cardView_3);
        cv4 = findViewById(R.id.cardView_4);
        cv5 = findViewById(R.id.cardView_5);
        cv6 = findViewById(R.id.cardView_6);
        cv7 = findViewById(R.id.cardView_7);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                actionView(cv1);
                break;
            case R.id.button_2:
                actionView(cv2);
                break;
            case R.id.button_3:
                actionView(cv3);
                break;
            case R.id.button_4:
                actionView(cv4);
                break;
            case R.id.button_5:
                actionView(cv5);
                break;
            case R.id.button_6:
                actionView(cv6);
                break;
            case R.id.button_7:
                actionView(cv7);
                break;
        }
    }

    private void actionView(View cardView) {
        if (cardView.getVisibility() == View.GONE){
            cardView.setVisibility(View.VISIBLE);
        }else {
            cardView.setVisibility(View.GONE);
        }
    }
}
