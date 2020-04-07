package limitless.android.androiddevelopment.Dialog;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class DialogUpdate extends DialogFragment implements View.OnClickListener {

    private String title, des, version, button, url;
    private Bitmap bitmap;
    private AppCompatTextView tvTitle, tvDes, tvVersion;
    private AppCompatImageView ivHeader;
    private AppCompatButton btnUpdate;

    public DialogUpdate(String title, String des, String version, Bitmap bitmap, String button, String url){
        this.title = title;
        this.des = des;
        this.version = version;
        this.bitmap = bitmap;
        this.button = button;
        this.url = url;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_update, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init(view);
        return dialog;
    }

    private void init(View view) {
        btnUpdate = view.findViewById(R.id.button_update);
        tvTitle = view.findViewById(R.id.textView_title);
        tvDes = view.findViewById(R.id.textView_description);
        tvVersion = view.findViewById(R.id.textView_version);
        ivHeader = view.findViewById(R.id.imageView_header);

        btnUpdate.setOnClickListener(this);
        if (title != null)
            tvTitle.setText(title);
        if (des != null)
            tvDes.setText(des);
        if (version != null)
            tvVersion.setText(version);
        if (button != null)
            btnUpdate.setText(button);
        if (bitmap != null)
            ivHeader.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (url != null)
            Tools.openUrl(getContext(), url);
        dismiss();
    }
}
