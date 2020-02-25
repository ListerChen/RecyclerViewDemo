
package com.lister.recyclerviewdemo.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SideBar extends View {

    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private OnTextPositionChangedListener mOnTextPositionChangedListener;
    private OnTouchingLetterChangedListener mOnTouchLetterChangedListener;
    private List<String> mLetterList;
    private int mChoose = -1;
    private Paint mPaint = new Paint();

    private int mTextNormalColor;
    private int mTextSelectedColor;

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mLetterList = Arrays.asList(INDEX_STRING);
        mTextNormalColor = Color.parseColor("#cc000000");
        mTextSelectedColor = Color.parseColor("#0097ff");
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / mLetterList.size(); // 获取每一个字母的高度
        for (int i = 0; i < mLetterList.size(); i++) {
            mPaint.setColor(mTextNormalColor);
            mPaint.setTypeface(Typeface.DEFAULT);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(32);
            // 选中的状态
            if (i == mChoose) {
                mPaint.setColor(mTextSelectedColor);
                mPaint.setFakeBoldText(true);
            }
            // x 坐标等于中间-字符串宽度的一半
            float xPos = width / 2.0f - mPaint.measureText(mLetterList.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight / 2.0f;
            canvas.drawText(mLetterList.get(i), xPos, yPos, mPaint);
            mPaint.reset(); // 重置画笔
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY(); // 点击 y 坐标
        // 点击 y 坐标所占总高度的比例*b数组的长度就等于点击 b 中的个数
        final int c = (int) (y / getHeight() * mLetterList.size());

        switch (action) {
            case MotionEvent.ACTION_UP:
                if (mOnTextPositionChangedListener != null) {
                    mOnTextPositionChangedListener.onActionUp();
                }
                mChoose = -1;
                invalidate();
                break;
            default:
                if (mChoose != c) {
                    if (c >= 0 && c < mLetterList.size()) {
                        if (mOnTouchLetterChangedListener != null) {
                            mOnTouchLetterChangedListener.onTouchingLetterChanged(mLetterList.get(c));
                        }
                        mChoose = c;
                        invalidate();
                    }
                }
                if (mOnTextPositionChangedListener != null && y >= 0 && y <= getHeight()) {
                    mOnTextPositionChangedListener.onTextPositionChanged(mLetterList.get(mChoose), (int) y);
                }
                break;
        }
        return true;
    }

    public void setIndexText(ArrayList<String> indexStrings) {
        this.mLetterList = indexStrings;
        invalidate();
    }

    public void setOnTextPositionChangedListener(OnTextPositionChangedListener listener) {
        this.mOnTextPositionChangedListener = listener;
    }

    public interface OnTextPositionChangedListener {
        void onTextPositionChanged(String c, int y);

        void onActionUp();
    }

    public void setOnTouchLetterChangedListener(
            OnTouchingLetterChangedListener listener) {
        this.mOnTouchLetterChangedListener = listener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }

}
