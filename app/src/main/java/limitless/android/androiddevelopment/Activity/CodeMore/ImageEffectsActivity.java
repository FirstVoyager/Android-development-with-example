package limitless.android.androiddevelopment.Activity.CodeMore;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import limitless.android.androiddevelopment.Activity.BaseActivity;
import limitless.android.androiddevelopment.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ImageEffectsActivity extends BaseActivity implements View.OnClickListener {

    private MaterialTextView tvTitle;
    private AppCompatImageView imageView;
    private ProgressBar progressBar;
    private MaterialButton btnApply;
    private AppCompatSeekBar seekBar;
    private int witch;
    private Bitmap mainBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_effects);
        init();
    }

    private void init() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_test_2);
        mainBitmap = Bitmap.createScaledBitmap(mainBitmap, (int) (mainBitmap.getWidth() / 2.4), (int) (mainBitmap.getHeight() / 2.5), true);
        tvTitle = findViewById(R.id.textView_title);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        btnApply = findViewById(R.id.button_apply);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        imageView.setImageBitmap(mainBitmap);
        btnApply.setOnClickListener(this);
        witch = getIntent().getIntExtra(Intent.EXTRA_TEXT, 0);
        switch (witch){
            case 0:
                tvTitle.setText(R.string.grey_filter);
                break;
            case 1:
                tvTitle.setText(R.string.sepia_filter);
                break;
            case 2:
                tvTitle.setText(R.string.black_and_white);
                break;
            case 3:
                progressBar.setVisibility(View.GONE);
                tvTitle.setText(R.string.brightness_filter);
                btnApply.setVisibility(View.INVISIBLE);
                seekBar.setVisibility(View.VISIBLE);
                break;
        }
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
        if (v.getId() == R.id.button_apply){
            progressBar.setVisibility(View.VISIBLE);
            btnApply.setVisibility(View.INVISIBLE);
            new Apply(0).execute();
        }
    }

    private AppCompatSeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            new Apply(progress - 255).execute();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    /**
     * Apply Grey filter
     * @return
     */
    private Bitmap applyGrey() {
        Bitmap newBitmap = mainBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(newBitmap);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(newBitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * Apply sepia filter
     * @return
     */
    private Bitmap applySepia() {
        Bitmap newBitmap = mainBitmap.copy(Bitmap.Config.ARGB_8888, true);
        ColorMatrix matrixA = new ColorMatrix();
        matrixA.setSaturation(0);

        ColorMatrix matrixB = new ColorMatrix();
        matrixB.setScale(1f, .95f, .82f, 1.0f);
        matrixA.setConcat(matrixB, matrixA);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrixA);
        Paint paint = new Paint();
        paint.setColorFilter(filter);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(newBitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * Apply Black & White filter
     * @return a changed bitmap
     */
    private Bitmap applyBlackAndWhiteColor() {
        Bitmap newBitmap = mainBitmap.copy(Bitmap.Config.ARGB_8888, true);
        ColorMatrix matrixA = new ColorMatrix();
        matrixA.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrixA);
        Paint paint = new Paint();
        paint.setColorFilter(filter);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(newBitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * Change bitmap brightness
     * @param value min : -255, max = 255, default : 0
     * @return
     */
    private Bitmap applyBrightness(int value) {
        Bitmap newBitmap = mainBitmap.copy(Bitmap.Config.ARGB_8888, true);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, value,
                0, 1, 0, 0, value,
                0, 0, 1, 0, value,
                0, 0, 0, 1, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(newBitmap, 0,0, paint);
        return newBitmap;
    }

    private class Apply extends AsyncTask<Void, Void, Bitmap>{

        private int value;

        public Apply(int value) {
            this.value = value;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            switch (witch){
                case 0:
                default:
                    return applyGrey();
                case 1:
                    return applySepia();
                case 2:
                    return applyBlackAndWhiteColor();
                case 3:
                    return applyBrightness(value);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            if (witch == 3)
                return;
            progressBar.setVisibility(View.INVISIBLE);
            btnApply.setVisibility(View.VISIBLE);
        }
    }

}
