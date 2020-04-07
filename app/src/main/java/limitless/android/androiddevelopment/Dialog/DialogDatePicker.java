package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.Interface.DateListener;
import limitless.android.androiddevelopment.R;

public class DialogDatePicker extends DialogFragment implements View.OnClickListener {

    private AppCompatButton btnOk, btnCancel;
    private DatePicker datePicker;
    private String[] names;
    private DateListener dateListener;

    public DialogDatePicker(@NonNull String[] names, DateListener dateListener){
        this.names = names;
        this.dateListener = dateListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_date_picker, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init(view);
        return dialog;
    }

    private void init(View view) {
        datePicker = view.findViewById(R.id.datePicker);
        btnOk = view.findViewById(R.id.button_ok);
        btnCancel = view.findViewById(R.id.button_cancel);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setText(names[0]);
        btnCancel.setText(names[1]);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_ok){
            if (dateListener != null){
                dateListener.date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            }
            dismiss();
        }else if (v.getId() == R.id.button_cancel){
            dismiss();
        }
    }
}
