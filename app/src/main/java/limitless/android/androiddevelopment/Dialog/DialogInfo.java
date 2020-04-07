package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.R;

public class DialogInfo extends DialogFragment {

    private AppCompatTextView tvTitle, tvBody;
    public static String title = "title", body = "body";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_info, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        init(view);
        return dialog;
    }

    private void init(View view) {
        tvTitle = view.findViewById(R.id.textView_title);
        tvBody = view.findViewById(R.id.textView_body);

        tvBody.setMovementMethod(new ScrollingMovementMethod());
        tvBody.setText(getArguments().getString(body, "Body"));
        tvTitle.setText(getArguments().getString(title, "Title"));
        view.findViewById(R.id.ibtn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
