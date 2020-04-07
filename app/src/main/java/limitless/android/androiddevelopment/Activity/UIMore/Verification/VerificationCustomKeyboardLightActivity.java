package limitless.android.androiddevelopment.Activity.UIMore.Verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;

public class VerificationCustomKeyboardLightActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private TextInputEditText et1, et2, et3, et4, et5;
    private MaterialTextView tvTime;
    private int time = 120000;
    private int delayTime = 1000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time <= 120000){
                time -= 1000;
                tvTime.setText(Tools.convertLongToTime(time, false, true, true, false));
                handler.postDelayed(this, delayTime);
            }else {
                tvTime.setText("Try again");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_custom_keyboard_light);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        Tools.setSystemBarLight(this);
        et1 = findViewById(R.id.editText_1);
        et2 = findViewById(R.id.editText_2);
        et3 = findViewById(R.id.editText_3);
        et4 = findViewById(R.id.editText_4);
        et5 = findViewById(R.id.editText_5);
        tvTime = findViewById(R.id.textView_time);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_0).setOnClickListener(this);
        findViewById(R.id.imageButton_delete).setOnClickListener(this);

        activeEditText(et1);
        et1.setOnTouchListener(this);
        et2.setOnTouchListener(this);
        et3.setOnTouchListener(this);
        et4.setOnTouchListener(this);
        et5.setOnTouchListener(this);

        et1.setInputType(InputType.TYPE_NULL);
        et2.setInputType(InputType.TYPE_NULL);
        et3.setInputType(InputType.TYPE_NULL);
        et4.setInputType(InputType.TYPE_NULL);
        et5.setInputType(InputType.TYPE_NULL);

        handler.postDelayed(runnable, delayTime);
    }

    private void activeEditText(TextInputEditText editText) {
        editText.requestFocus();
        editText.setBackgroundResource(R.drawable.background_edit_text_active);
        if (editText.equals(et1)){
            et2.setBackgroundResource(R.drawable.background_edit_text_verify);
            et3.setBackgroundResource(R.drawable.background_edit_text_verify);
            et4.setBackgroundResource(R.drawable.background_edit_text_verify);
            et5.setBackgroundResource(R.drawable.background_edit_text_verify);
        }else if (editText.equals(et2)){
            et1.setBackgroundResource(R.drawable.background_edit_text_verify);
            et3.setBackgroundResource(R.drawable.background_edit_text_verify);
            et4.setBackgroundResource(R.drawable.background_edit_text_verify);
            et5.setBackgroundResource(R.drawable.background_edit_text_verify);
        }else if (editText.equals(et3)){
            et1.setBackgroundResource(R.drawable.background_edit_text_verify);
            et2.setBackgroundResource(R.drawable.background_edit_text_verify);
            et4.setBackgroundResource(R.drawable.background_edit_text_verify);
            et5.setBackgroundResource(R.drawable.background_edit_text_verify);
        }else if (editText.equals(et4)){
            et1.setBackgroundResource(R.drawable.background_edit_text_verify);
            et2.setBackgroundResource(R.drawable.background_edit_text_verify);
            et3.setBackgroundResource(R.drawable.background_edit_text_verify);
            et5.setBackgroundResource(R.drawable.background_edit_text_verify);
        }else if (editText.equals(et5)){
            et1.setBackgroundResource(R.drawable.background_edit_text_verify);
            et2.setBackgroundResource(R.drawable.background_edit_text_verify);
            et3.setBackgroundResource(R.drawable.background_edit_text_verify);
            et4.setBackgroundResource(R.drawable.background_edit_text_verify);
        }
        Tools.hideKeyboard(this, editText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton_delete){
            if (et1.hasFocus()){
                et1.setText(null);
                activeEditText(et1);
            }else if (et2.hasFocus()){
                et2.setText(null);
                activeEditText(et1);
            }else if (et3.hasFocus()) {
                et3.setText(null);
                activeEditText(et2);
            }else if (et4.hasFocus()){
                et4.setText(null);
                activeEditText(et3);
            }else if (et5.hasFocus()){
                et5.setText(null);
                activeEditText(et4);
            }
        }else if (v instanceof MaterialButton){
            String s = ((MaterialButton) v).getText().toString();
            if (et1.hasFocus()){
                et1.setText(s);
                activeEditText(et2);
            }else if (et2.hasFocus()){
                et2.setText(s);
                activeEditText(et3);
            }else if (et3.hasFocus()){
                et3.setText(s);
                activeEditText(et4);
            }else if (et4.hasFocus()){
                et4.setText(s);
                activeEditText(et5);
            }else if (et5.hasFocus()){
                et5.setText(s);
                activeEditText(et1);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (v instanceof TextInputEditText){
                activeEditText((TextInputEditText) v);
            }
            return true;
        }
        return false;
    }
}
