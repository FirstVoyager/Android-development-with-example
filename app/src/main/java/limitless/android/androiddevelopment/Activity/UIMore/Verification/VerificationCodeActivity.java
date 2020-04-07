package limitless.android.androiddevelopment.Activity.UIMore.Verification;

import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;

public class VerificationCodeActivity extends BaseActivity {

    private TextInputEditText et1, et2, et3, et4, et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        init();
    }

    private void init() {
        et1 = findViewById(R.id.editText_1);
        et2 = findViewById(R.id.editText_2);
        et3 = findViewById(R.id.editText_3);
        et4 = findViewById(R.id.editText_4);
        et5 = findViewById(R.id.editText_5);

        et1.addTextChangedListener(tw1);
        et2.addTextChangedListener(tw2);
        et3.addTextChangedListener(tw3);
        et4.addTextChangedListener(tw4);
        et5.addTextChangedListener(tw5);
    }

    private TextWatcher tw1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                et2.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher tw2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                et3.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher tw3 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                et4.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher tw4 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                et5.requestFocus();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher tw5 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0)
                Tools.hideKeyboard(VerificationCodeActivity.this, et5);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
