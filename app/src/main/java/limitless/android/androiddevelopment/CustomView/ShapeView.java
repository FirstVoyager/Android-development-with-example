package limitless.android.androiddevelopment.CustomView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.Nullable;
import limitless.android.androiddevelopment.R;

public class ShapeView extends View {

    public static enum TYPE {
        triangle,
        square,
        rectangle,
        parallelogram,
        octagon,
        circle,
        crescent,
        line
    }
    private TYPE mShape;
    private Paint mPaint = new Paint();
    private Paint mShadowPaint = new Paint();
    private Paint mStrokePaint = new Paint();
    private int mBackgroundColor;
    private boolean mShowStroke;
    private int mStrokeColor;
    private int mStrokeWidth;
    private boolean mStrokeDashed;
    private int mStrokeDashedHeight;
    private int mElevation;
    private long mRotationalSpeed;
    private int mShadowRadius;
    private int mShadowColor;
    private int mViewSize;
    private int mViewWidth, mViewHeight;
    private int mLineWidth, mLineColor;
    private int mFromDegrees = 0;
    private int mToDegrees = 45;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mRotationalSpeed > 0){
                RotateAnimation rotate = new RotateAnimation(
                        mFromDegrees,
                        mToDegrees);
                rotate.setRepeatCount(4);
                rotate.setDuration(mRotationalSpeed);
                rotate.setInterpolator(new LinearInterpolator());
                startAnimation(rotate);
            }
        }
    };

    public ShapeView(Context context) {
        super(context);
        init(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet set) {
        if (context == null)
            return;
        TypedArray ta = context.getTheme().obtainStyledAttributes(set, R.styleable.ShapeView, 0, 0);
        try {
            mShape = TYPE.values()[ta.getInt(R.styleable.ShapeView_shape_type, 0)];
            mBackgroundColor = ta.getColor(R.styleable.ShapeView_shape_backgroundColor, Color.WHITE);
            mShowStroke = ta.getBoolean(R.styleable.ShapeView_shape_stroke, false);
            mStrokeColor = ta.getColor(R.styleable.ShapeView_shape_strokeColor, Color.RED);
            mStrokeWidth = ta.getDimensionPixelSize(R.styleable.ShapeView_shape_strokeWidth, 0);
            mStrokeDashed = ta.getBoolean(R.styleable.ShapeView_shape_strokeDashed, false);
            mStrokeDashedHeight = ta.getDimensionPixelSize(R.styleable.ShapeView_shape_strokeDashedHeight, 0);
            mElevation = ta.getDimensionPixelSize(R.styleable.ShapeView_shape_elevation, 0);
            mRotationalSpeed = (long) ta.getFloat(R.styleable.ShapeView_shape_rotationalSpeed, 0);
            mShadowRadius = ta.getDimensionPixelSize(R.styleable.ShapeView_shape_shadowRadius, 0);
            mShadowColor = ta.getColor(R.styleable.ShapeView_shape_shadowColor, Color.WHITE);
            mLineColor = ta.getColor(R.styleable.ShapeView_shape_lineColor, Color.WHITE);
            mLineWidth = ta.getDimensionPixelSize(R.styleable.ShapeView_shape_lineWidth, 1);
        }finally {
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = resolveSize(20, widthMeasureSpec);
        mViewHeight = resolveSize(20, heightMeasureSpec);
        mViewWidth -= getPaddingRight() + getPaddingLeft();
        mViewHeight -= getPaddingTop() + getPaddingBottom();
        mViewSize = Math.min(mViewWidth, mViewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mShape){
            case triangle:
                drawTriangle(canvas);
                break;
            case square:
                drawSquare(canvas);
                break;
            case rectangle:
                drawRectangle(canvas);
                break;
            case parallelogram:
                // TODO: 2/1/20 add in other version
                break;
            case octagon:
                // TODO: 2/1/20 add in other version
                break;
            case circle:
                drawCircle(canvas);
                break;
            case crescent:
                // TODO: 2/1/20 add in other version
                break;
            case line:
                drawLine(canvas);
                break;
        }
        super.onDraw(canvas);

        // TODO: 2/1/20 add other version
//        if (mRotationalSpeed > 0){
//            handler.postDelayed(runnable, mRotationalSpeed);
//        }
    }

    private void drawTriangle(Canvas canvas) {

        // draw triangle
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mBackgroundColor);
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        int x1 = getWidth() / 2;
        int y1 = getPaddingTop();
        int x2 = getPaddingLeft();
        int y2 = mViewHeight;
        int x3 = x2 + mViewWidth;
        int y3 = y2;

        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x1, y1);
        path.close();

        canvas.drawPath(path, mPaint);
    }

    private void drawLine(Canvas canvas) {
        if (mLineWidth > 0){
            mPaint.setColor(mLineColor);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(mLineWidth);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            int x1 = getPaddingRight();
            int y1 = (int) (getX() + (mViewHeight / 2));
            int x2 = mViewWidth + getPaddingRight();
            int y2 = y1;
            Path path = new Path();
            path.moveTo(x1, y1);
            path.lineTo(x2, y2);
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawRectangle(Canvas canvas) {
        int canvasW = getWidth();
        int canvasH = getHeight();
        Point point = new Point(canvasW / 2, canvasH / 2);

        // draw shadow for rectangle
        if (mShadowRadius > 0){
            int dx = 0, dy = 0;
            mShadowPaint.setShadowLayer(mShadowRadius, dx, dy, mShadowColor);
            int rectW = mViewWidth;
            int rectH = mViewHeight;
            int left = point.x - (rectW / 2);
            int top = point.y - (rectH / 2);
            int right = point.x + (rectW / 2);
            int bottom = point.y + (rectH / 2);
            canvas.drawRect(
                    left, top, right, bottom, mShadowPaint
            );
            setLayerType(LAYER_TYPE_SOFTWARE, mShadowPaint);
        }

        // draw stroke for rectangle
        if (mShowStroke){
            mStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setStrokeWidth(mStrokeWidth);
            mStrokePaint.setColor(mStrokeColor);
            int rectW = (mViewWidth - mShadowRadius);
            int rectH = (mViewHeight - mShadowRadius);
            int left = point.x - (rectW / 2);
            int top = point.y - (rectH / 2);
            int right = point.x + (rectW / 2);
            int bottom = point.y + (rectH / 2);
            canvas.drawRect(
                    left, top, right, bottom, mStrokePaint
            );
        }

        // draw rectangle
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mBackgroundColor);
        int rectW = (mViewWidth - mStrokeWidth - mShadowRadius);
        int rectH = (mViewHeight - mStrokeWidth - mShadowRadius);
        int left = point.x - (rectW / 2);
        int top = point.y - (rectH / 2);
        int right = point.x + (rectW / 2);
        int bottom = point.y + (rectH / 2);
        canvas.drawRect(
                left, top, right, bottom, mPaint
        );

    }

    private void drawSquare(Canvas canvas) {
        int canvasW = getWidth();
        int canvasH = getHeight();
        Point centerOfCanvas = new Point(canvasW / 2, canvasH / 2);
        if (mShadowRadius > 0){
            int dx = 0, dy = 0;
            mShadowPaint.setShadowLayer(mShadowRadius, dx, dy, mShadowColor);
            int rectW = mViewSize;
            int rectH = mViewSize;
            int left = centerOfCanvas.x - (rectW / 2);
            int top = centerOfCanvas.y - (rectH / 2);
            int right = centerOfCanvas.x + (rectW / 2);
            int bottom = centerOfCanvas.y + (rectH / 2);
            canvas.drawRect(
                    left, top, right, bottom, mShadowPaint
            );
            setLayerType(LAYER_TYPE_SOFTWARE, mShadowPaint);
        }
        if (mShowStroke){
            mStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setStrokeWidth(mStrokeWidth);
            mStrokePaint.setColor(mStrokeColor);
            int rectW = (mViewSize - mShadowRadius);
            int rectH = (mViewSize - mShadowRadius);
            int left = centerOfCanvas.x - (rectW / 2);
            int top = centerOfCanvas.y - (rectH / 2);
            int right = centerOfCanvas.x + (rectW / 2);
            int bottom = centerOfCanvas.y + (rectH / 2);
            canvas.drawRect(
                    left, top, right, bottom, mStrokePaint
            );
        }
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mBackgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
        int rectW = (mViewSize - mShadowRadius - mStrokeWidth);
        int rectH = (mViewSize - mShadowRadius - mStrokeWidth);
        int left = centerOfCanvas.x - (rectW / 2);
        int top = centerOfCanvas.y - (rectH / 2);
        int right = centerOfCanvas.x + (rectW / 2);
        int bottom = centerOfCanvas.y + (rectH / 2);
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        if (mShadowRadius > 0){
            int dx = 0, dy = 0;
            mShadowPaint.setShadowLayer(mShadowRadius, dx, dy, mShadowColor);
            canvas.drawCircle(
                    getPivotX(),
                    getPivotY(),
                    (mViewSize - mShadowRadius) / 2,
                    mShadowPaint
            );
            setLayerType(LAYER_TYPE_SOFTWARE, mShadowPaint);
        }
        if (mShowStroke){
            mStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setStrokeWidth(mStrokeWidth);
            mStrokePaint.setColor(mStrokeColor);
            canvas.drawCircle(getPivotX(), getPivotY(), (mViewSize - mShadowRadius) / 2, mStrokePaint);
        }
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mBackgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getPivotX(), getPivotY(), (mViewSize - mStrokeWidth - mShadowRadius) / 2, mPaint);
    }
}
