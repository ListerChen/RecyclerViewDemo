
package com.lister.recyclerviewdemo.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CityItemDecoration extends RecyclerView.ItemDecoration {

    private static final int GROUP_ITEM_TOP = 80;
    private static final int TEXT_SIZE = 40;
    private static final int TEXT_PADDING_LEFT = 30;

    private List<CityInfo> mCityInfoList;
    private Paint mBackGroundPaint;
    private Paint mTextPaint;
    private float mTextCenterY;

    public CityItemDecoration(List<CityInfo> cityInfoList) {
        this.mCityInfoList = cityInfoList;
        mBackGroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackGroundPaint.setColor(Color.parseColor("#BDBDBD"));
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#828282"));
        mTextPaint.setTextSize(TEXT_SIZE);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextCenterY = (fontMetrics.top + fontMetrics.bottom) / 2;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            CityInfo cityInfo = mCityInfoList.get(position);
            if (cityInfo.isFirstInGroup()) {
                int left = parent.getPaddingLeft();
                int top = view.getTop() - GROUP_ITEM_TOP;
                int right = parent.getRight() - parent.getPaddingRight();
                int bottom = view.getTop();
                c.drawRect(left, top, right, bottom, mBackGroundPaint);

                drawText(c, left, top, bottom, cityInfo.getGroup());
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        View view = parent.getChildAt(0);
        int position = parent.getChildAdapterPosition(view);
        CityInfo cityInfo = mCityInfoList.get(position);
        if (cityInfo.isLastInGroup() && view.getBottom() < GROUP_ITEM_TOP) {
            int left = parent.getPaddingLeft();
            int top = view.getBottom() - GROUP_ITEM_TOP;
            int right = parent.getRight() - parent.getPaddingRight();
            int bottom = view.getBottom();
            c.drawRect(left, top, right, bottom, mBackGroundPaint);

            drawText(c, left, top, bottom, cityInfo.getGroup());
        } else {
            int left = parent.getPaddingLeft();
            int top = parent.getPaddingTop();
            int right = parent.getRight() - parent.getPaddingRight();
            int bottom = top + GROUP_ITEM_TOP;
            c.drawRect(left, top, right, bottom, mBackGroundPaint);

            drawText(c, left, top, bottom, cityInfo.getGroup());
        }
    }

    private void drawText(Canvas c, int left, int top, int bottom, String text) {
        float textX = left + TEXT_PADDING_LEFT;
        float textY = (top + bottom) / 2f - mTextCenterY;
        c.drawText(text, textX, textY, mTextPaint);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        CityInfo cityInfo = mCityInfoList.get(position);
        if (cityInfo.isFirstInGroup()) {
            outRect.top = GROUP_ITEM_TOP;
        }
    }
}
