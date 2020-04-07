package limitless.android.androiddevelopment.Activity.UserInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.Dialog.DialogCameraPermission;
import limitless.android.androiddevelopment.Dialog.DialogCookies;
import limitless.android.androiddevelopment.Dialog.DialogDatePicker;
import limitless.android.androiddevelopment.Dialog.DialogUpdate;
import limitless.android.androiddevelopment.Interface.DateListener;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toolbar;

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_10).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                basic();
                break;
            case R.id.button_2:
                basic2();
                break;
            case R.id.button_3:
                singleChoiceDialog();
                break;
            case R.id.button_4:
                multiChoiceDialog();
                break;
            case R.id.button_5:
                datePickerDialog();
                break;
            case R.id.button_6:
                customDatePicker();
                break;
            case R.id.button_7:
                timePickerDialog();
                break;
            case R.id.button_8:
                updateDialog();
                break;
            case R.id.button_9:
                cookieDialog();
                break;
            case R.id.button_10:
                cameraPermissionFullScreen();
                break;
        }
    }

    private void cameraPermissionFullScreen() {
        DialogCameraPermission permission = new DialogCameraPermission();
        permission.show(getSupportFragmentManager(), null);
    }

    private void cookieDialog() {
        DialogCookies cookies = new DialogCookies();
        cookies.show(getSupportFragmentManager(), null);
    }

    private void updateDialog() {
        DialogUpdate update = new DialogUpdate(
                null,
                null,
                null,
                null,
                null,
                null
        );
        update.show(getSupportFragmentManager(), null);
    }

    private void timePickerDialog() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Tools.toast(DialogActivity.this, hourOfDay + ":" + minute);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, listener, 12, 30, true);
        timePickerDialog.show();
    }

    private void customDatePicker() {
        String[] names = new String[]{getString(R.string.text_ok), getString(R.string.text_cancel)};
        DialogDatePicker datePicker = new DialogDatePicker(names, new DateListener() {
            @Override
            public void date(int year, int mouth, int dayOfMonth) {
                Tools.toast(DialogActivity.this, year + "/" + mouth + "/" + year);
            }
        });
        datePicker.show(getSupportFragmentManager(), null);
    }

    private void datePickerDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this);
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Tools.toast(DialogActivity.this, year + "/" + month + "/" + dayOfMonth);
                }
            });
            datePickerDialog.show();
        }else {
            Tools.toast(DialogActivity.this, "SDK_INT >= N");
        }
    }

    private void multiChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.text_multi_choice_dialog));
        final String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        boolean[] checked = new boolean[]{true, false, false, true, false, false};
        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Tools.toast(DialogActivity.this, items[which] + " " + isChecked);
            }
        });
        builder.setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void singleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.text_single_choice_dialog));
        final String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Tools.toast(DialogActivity.this, items[which]);
            }
        });
        builder.setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setIcon(R.drawable.ic_info_outline_black_24dp);
        builder.show();
    }

    private void basic2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.text_basic_dialog));
        builder.setMessage(getString(R.string.info_dialog));
        builder.setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton(getString(R.string.text_learn_more), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Tools.openUrl(DialogActivity.this, getString(R.string.url_android_dev_dialog));
            }
        });
        builder.setIcon(R.drawable.ic_wallpaper_black_24dp);
        builder.show();
    }

    private void basic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Basic dialog");
        builder.setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
