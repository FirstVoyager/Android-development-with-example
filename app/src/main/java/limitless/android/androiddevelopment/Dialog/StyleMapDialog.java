package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.Interface.Listener;
import limitless.android.androiddevelopment.R;

public class StyleMapDialog extends DialogFragment implements View.OnClickListener {

    private Listener<Integer> styledListener;

    public StyleMapDialog(Listener<Integer> styledListener) {
        this.styledListener = styledListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_styled_map, null);
        dialog.setContentView(view);
        dialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        view.findViewById(R.id.cardView_standard).setOnClickListener(this);
        view.findViewById(R.id.cardView_silver).setOnClickListener(this);
        view.findViewById(R.id.cardView_retro).setOnClickListener(this);
        view.findViewById(R.id.cardView_dark).setOnClickListener(this);
        view.findViewById(R.id.cardView_night).setOnClickListener(this);
        view.findViewById(R.id.cardView_aubergine).setOnClickListener(this);
        view.findViewById(R.id.fab_close).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_close){
            dismiss();
            return;
        }
        if (styledListener != null){
            switch (v.getId()){
                case R.id.cardView_standard:
                    styledListener.data(0);
                    break;
                case R.id.cardView_silver:
                    styledListener.data(1);
                    break;
                case R.id.cardView_retro:
                    styledListener.data(2);
                    break;
                case R.id.cardView_dark:
                    styledListener.data(3);
                    break;
                case R.id.cardView_night:
                    styledListener.data(4);
                    break;
                case R.id.cardView_aubergine:
                    styledListener.data(5);
                    break;
            }
        }
        dismiss();
    }
}
