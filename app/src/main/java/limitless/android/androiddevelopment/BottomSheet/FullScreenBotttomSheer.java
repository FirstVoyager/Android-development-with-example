package limitless.android.androiddevelopment.BottomSheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import limitless.android.androiddevelopment.R;

public class FullScreenBotttomSheer extends BottomSheetDialogFragment implements View.OnClickListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.bottom_sheet_full_screen, null);
        dialog.setContentView(view);
        ((MaterialToolbar) view.findViewById(R.id.toolbar)).setNavigationOnClickListener(this);
        BottomSheetBehavior.from(((View) view.getParent())).addBottomSheetCallback(callBack);
        return dialog;
    }

    private BottomSheetBehavior.BottomSheetCallback callBack = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN){
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
