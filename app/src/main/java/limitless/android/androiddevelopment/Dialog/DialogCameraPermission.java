package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class DialogCameraPermission extends DialogFragment implements View.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_camera_permission, null);
        Window window = dialog.getWindow();
        window.getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        window.getAttributes().height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(view);
        init(view);
        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    private void init(View view) {
        view.findViewById(R.id.imageButton_close).setOnClickListener(this);
        view.findViewById(R.id.button_continue).setOnClickListener(this);
        view.findViewById(R.id.button_notNow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_continue){
            Tools.toast(getContext(), getString(R.string.text_continue));
        }else if (v.getId() == R.id.imageButton_close){
            Tools.toast(getContext(), getString(R.string.text_close));
        }else if (v.getId() == R.id.button_notNow){
            Tools.toast(getContext(), getString(R.string.text_not_now));
        }
        dismiss();
    }
}
