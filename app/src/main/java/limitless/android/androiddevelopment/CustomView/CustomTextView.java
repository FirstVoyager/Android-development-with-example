package limitless.android.androiddevelopment.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import limitless.android.androiddevelopment.R;

public class CustomTextView extends AppCompatTextView {

    private boolean mShowStroke;
    private float mStrokeWidth;
    private int mStrokeColor;
    private Paint mPaint = new Paint();

    public CustomTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if (attrs == null){
            mShowStroke = false;
            mStrokeWidth = 0;
            mStrokeColor = Color.WHITE;
        }else {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0,0);
            try {
                mShowStroke = a.getBoolean(R.styleable.CustomTextView_showStroke, false);
                mStrokeWidth = a.getDimension(R.styleable.CustomTextView_strokeWidth, 0);
                mStrokeColor = a.getColor(R.styleable.CustomTextView_strokeColor, Color.WHITE);
            }finally {
                a.recycle();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mShowStroke){
            if (mStrokeWidth <= 0){
                mStrokeWidth = 2;
            }
            mPaint.setColor(mStrokeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            canvas.drawPaint(mPaint);
        }
        super.onDraw(canvas);
    }

    public void setShowStroke(boolean showStroke){
        mShowStroke = showStroke;
        invalidate();
        requestLayout();
    }

    public void setStrokeWidth(float strokeWidth){
        mStrokeWidth = strokeWidth;
        invalidate();
        requestLayout();
    }

    public void setStrokeColor(int strokeColor){
        mStrokeColor = strokeColor;
        invalidate();
        requestLayout();
    }

}
