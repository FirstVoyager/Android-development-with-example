package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class DialogSort extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private String[] sorts, column;
    private String title;
    private int activeButtonId;
    private StringListener stringListener;
    private RadioGroup radioGroup;

    public DialogSort(String[] sorts, String[] column, String title, StringListener stringListener) {
        this.sorts = sorts;
        this.column = column;
        this.title = title;
        this.stringListener = stringListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_sort, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        init(view);
        return dialog;
    }

    private void init(View view) {
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        view.findViewById(R.id.button_asc).setOnClickListener(this);
        view.findViewById(R.id.button_desc).setOnClickListener(this);
        ((AppCompatTextView) view.findViewById(R.id.textView_title)).setText(title);
        createRadioButtons();
    }

    private void createRadioButtons() {
        if (sorts == null || sorts.length <= 0){
            dismiss();
            return;
        }
        int padding = (int) Tools.convertDipToPx(getResources().getDisplayMetrics(), 8);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -1);
        for (int i = 0; i < sorts.length; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setPadding(padding, padding, padding, padding);
            rb.setText(sorts[i]);
            rb.setId(i);
            rb.setLayoutParams(lp);
            radioGroup.addView(rb);
        }
        radioGroup.check(0);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_asc){
            if (stringListener != null)
                stringListener.string(column[activeButtonId] + " ASC");
        }else if (v.getId() == R.id.button_desc){
            if (stringListener != null)
                stringListener.string(column[activeButtonId] + " DESC");
        }
        dismiss();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        activeButtonId = checkedId;
    }
}
