package limitless.android.androiddevelopment.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class ValueSelector extends CardView implements View.OnClickListener {

    private AppCompatImageButton ibtnIncrease, ibtnDescrease;
    private AppCompatTextView textView;
    private ChangeListener mChangeListener;
    private MediaPlayer mediaPlayer;

    private int mCurrentValue;
    private int mMaxValue;
    private int mMinValue;
    private int mTextColor;
    private int mBackgroundColor;
    private int mIncreaseImage;
    private int mDecreaseImage;
    private int mIncreaseTint;
    private int mDecreaseTint;
    private boolean mSoundPress;
    private enum SOUND {
        TYPE1, TYPE2, TYPE3
    }

    public ValueSelector(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ValueSelector(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ValueSelector(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if (context == null)
            return;
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ValueSelector,  0, 0);
        try {
            View view = View.inflate(context, R.layout.value_selector, null);
            ibtnIncrease = view.findViewById(R.id.imageButton_increase);
            ibtnDescrease = view.findViewById(R.id.imageButton_decrease);
            textView = view.findViewById(R.id.textView_value);

            mCurrentValue = ta.getInt(R.styleable.ValueSelector_vs_currentValue, 10);
            mMaxValue = ta.getInt(R.styleable.ValueSelector_vs_maxValue, 100);
            mMinValue = ta.getInt(R.styleable.ValueSelector_vs_minValue, 0);
            mTextColor = ta.getColor(R.styleable.ValueSelector_vs_textColor, Color.BLACK);
            mBackgroundColor = ta.getColor(R.styleable.ValueSelector_vs_backgroundColor, Color.WHITE);
            mIncreaseImage = ta.getResourceId(R.styleable.ValueSelector_vs_increaseDrawable, R.drawable.ic_add_black_24dp);
            mDecreaseImage = ta.getResourceId(R.styleable.ValueSelector_vs_decreaseDrawable, R.drawable.ic_minus_24dp);
            mSoundPress = ta.getBoolean(R.styleable.ValueSelector_vs_soundPress, true);
            mIncreaseTint = ta.getColor(R.styleable.ValueSelector_vs_increaseTint, Color.BLACK);
            mDecreaseTint = ta.getColor(R.styleable.ValueSelector_vs_decreaseTint, Color.BLACK);
            switch (SOUND.values()[ta.getInt(R.styleable.ValueSelector_vs_soundType, 0)]){
                case TYPE1:
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_button_press_plastic);
                    break;
                case TYPE2:
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_button_press_multimedia);
                    break;
                case TYPE3:
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_ex_machina_buttons);
                    break;
                default:
                    mediaPlayer = MediaPlayer.create(context, R.raw.sound_button_press_plastic);
            }
            ibtnIncrease.setColorFilter(mIncreaseTint);
            ibtnDescrease.setColorFilter(mDecreaseTint);
            textView.setTextColor(mTextColor);
            ((CardView) view).setCardBackgroundColor(mBackgroundColor);
            ibtnIncrease.setImageResource(mIncreaseImage);
            ibtnDescrease.setImageResource(mDecreaseImage);
            textView.setText(String.valueOf(mCurrentValue));
            ibtnIncrease.setOnClickListener(this);
            ibtnDescrease.setOnClickListener(this);
            textView.setOnClickListener(this);

            addView(view);
        }finally {
            ta.recycle();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton_increase){
            sound();
            if (mCurrentValue < mMaxValue){
                mCurrentValue += 1;
                textView.setText(String.valueOf(mCurrentValue));
                if (mChangeListener != null)
                    mChangeListener.listener(mCurrentValue);
            }
        }else if (v.getId() == R.id.imageButton_decrease){
            sound();
            if (mCurrentValue > mMinValue){
                mCurrentValue -= 1;
                textView.setText(String.valueOf(mCurrentValue));
                if (mChangeListener != null)
                    mChangeListener.listener(mCurrentValue);
            }
        }else if (v.getId() == R.id.textView_value){

        }
    }

    private void sound() {
        if (mSoundPress){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            try {
                mediaPlayer.start();
            } catch (Exception e) {
                Tools.error(e);
            }
        }
    }

    public void setChangeListener(ChangeListener mChangeListener) {
        this.mChangeListener = mChangeListener;
    }

    public static interface ChangeListener {
        void listener(int value);
    }

}
