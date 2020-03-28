
package com.lister.recyclerviewdemo.turntable;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class TurntableLayoutManager extends RecyclerView.LayoutManager {

    private static int VISIBLE_ANGLE = 50;

    private int mRadius;
    private int mEachAngle;

    private int mItemWidth;
    private int mItemHeight;
    private int mCircleMidX;
    private int mCircleMidY;
    private float mMovedAngle = 0;

    public TurntableLayoutManager(int radius, int eachAngle) {
        this.mRadius = radius;
        this.mEachAngle = eachAngle;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 假设每个Item大小相等, 得到item的大小
        initItemSize(recycler);
        // 根据屏幕中心得到计算坐标时的圆心
        int screenMidX = getWidth() / 2;
        int screenMidY = getHeight() / 2;
        mCircleMidX = screenMidX;
        mCircleMidY = screenMidY + mRadius;

        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < getItemCount(); i++) {
            if (Math.abs(i * mEachAngle) < VISIBLE_ANGLE) {
                layoutViewByIndex(recycler, i);
            }
        }
    }

    private void initItemSize(RecyclerView.Recycler recycler) {
        View view = recycler.getViewForPosition(0);
        addView(view);
        measureChildWithMargins(view, 0, 0);
        mItemWidth = getDecoratedMeasuredWidth(view);
        mItemHeight = getDecoratedMeasuredHeight(view);
        removeAndRecycleView(view, recycler);
    }

    private void layoutViewByIndex(RecyclerView.Recycler recycler, int index) {
        float curAngle = index * mEachAngle - mMovedAngle;
        int xToAdd = (int) (Math.sin(2 * Math.PI / 360 * curAngle) * mRadius);
        int yToMinus = (int) (Math.cos(2 * Math.PI / 360 * curAngle) * mRadius);
        int x = mCircleMidX + xToAdd;
        int y = mCircleMidY - yToMinus;

        View viewForPosition = recycler.getViewForPosition(index);
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, 0, 0);
        layoutDecorated(viewForPosition, x - mItemWidth / 2, y - mItemHeight / 2,
                x + mItemWidth / 2, y + mItemHeight / 2);
        viewForPosition.setRotation(curAngle);
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        float moveAngle = convertDxToAngle(dx);
        int actualDx = dx;
        if (mMovedAngle + moveAngle > getMaxScrollAngle()) {
            moveAngle = getMaxScrollAngle() - mMovedAngle;
            actualDx = convertAngleToDx(moveAngle);
        } else if (mMovedAngle + moveAngle < 0) {
            moveAngle = -mMovedAngle;
            actualDx = convertAngleToDx(moveAngle);
        }
        mMovedAngle += moveAngle;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view != null) {
                int position = getPosition(view);
                float curAngle = position * mEachAngle + mMovedAngle;
                if (Math.abs(curAngle) >= 50) {
                    removeAndRecycleView(view, recycler);
                }
            }
        }
        // 回收当前屏幕上的所有ItemView
        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < getItemCount(); i++) {
            float curAngle = i * mEachAngle - mMovedAngle;
            if (Math.abs(curAngle) < VISIBLE_ANGLE) {
                layoutViewByIndex(recycler, i);
            }
        }
        return actualDx;
    }

    private int getMaxScrollAngle() {
        return (getItemCount() - 1) * mEachAngle;
    }

    private float convertDxToAngle(int dx) {
        return (float) (360 * dx / (2 * Math.PI * mRadius));
    }

    private int convertAngleToDx(float angle) {
        return (int) (2 * Math.PI * mRadius * angle / 360);
    }
}
