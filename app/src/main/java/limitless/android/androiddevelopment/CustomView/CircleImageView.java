package limitless.android.androiddevelopment.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class CircleImageView extends AppCompatImageView {

    private Paint mPaint = new Paint();
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;
    private int mViewSize;
    private int mViewWidth;
    private int mViewHeight;
    private int canvasSize;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = resolveSize(20, widthMeasureSpec) - getPaddingRight() - getPaddingLeft();
        mViewHeight = resolveSize(20, heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        mViewSize = Math.min(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null)
            return;
        canvasSize = getWidth() < getHeight() ? getWidth() : getHeight();
        updateBitmapShader();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(
                getPivotX(),
                getPivotY(),
                mViewSize / 2,
                mPaint
        );
//        super.onDraw(canvas);
    }


    private void initBitmap() {
        mBitmap = getBitmapFromDrawable(getDrawable());
        if (mBitmap == null){
            invalidate();
            return;
        }
        updateBitmapShader();
        invalidate();
    }

    private void updateBitmapShader() {
        if (mBitmap == null)
            return;
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        if (canvasSize != getWidth() || canvasSize != getHeight()){
            float scale = canvasSize / getWidth();
            matrix.setScale(scale, scale);
            mBitmapShader.setLocalMatrix(matrix);
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null)
            return null;
        if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable)
                bitmap = Bitmap.createBitmap(2, 2, Config.ARGB_8888);
            else
                bitmap = Bitmap.createBitmap(2, 2, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }catch (Exception e){
            Tools.error(e);
            return null;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        initBitmap();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        initBitmap();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        initBitmap();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        initBitmap();
    }

}
