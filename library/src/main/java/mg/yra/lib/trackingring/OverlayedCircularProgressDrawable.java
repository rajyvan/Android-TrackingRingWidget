package mg.yra.lib.trackingring;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Overlayed Circular Progress Drawable
 * <p>
 * This drawable provides a circular shape with a set of rings surrounding it.
 * Each ring can be empty, filled or partially filled according to a progress value.
 * <p>
 *
 * @author Yvan
 */
public class OverlayedCircularProgressDrawable extends Drawable {

    private final static int PI = 180;
    private final static int START_ANGLE = -90;
    private final static float RING_SPACE_RATIO = 1f / 8f;
    private final static float DRAWABLE_PADDING_RATIO = 1f / 8f;
    private final static float TEXT_SIZE_RATIO = 1f / 2f;

    private final static int ANIMATION_DURATION = 800;

    /**
     * Inner circle scale
     */
    private float mInnerCircleScale;
    /**
     * Inner circle color
     */
    private int mInnerColor;
    /**
     * Dat set
     */
    private DataSet mDataSet;

    private SparseArray<Float> mProgressValues;
    private RectF mArcElements;
    private Paint mPaint;
    private float mInnerRadius;
    private float mRingWidth;
    private float mRingSpace;
    private Animator mAnimator;

    public OverlayedCircularProgressDrawable(DataSet dataSet, float innerCircleScale, int innerColor) {
        mDataSet = dataSet;
        mInnerCircleScale = innerCircleScale;
        mInnerColor = innerColor;

        mArcElements = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        initValues();
    }

    private static float getAngleFromProgress(float progress) {
        return 2f * PI / 100f * progress;
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        final boolean changed = super.setVisible(visible, restart);
        if (restart) {
            if (mAnimator == null) {
                mAnimator = prepareShowAnimation();
            }
            mAnimator.cancel();
            mAnimator.start();
        }
        return changed;
    }

    public void animate() {
        setVisible(true, true);
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();

        // Different component sizes computation
        final int size = Math.min(bounds.height(), bounds.width());
        mInnerRadius = size * mInnerCircleScale / 2;

        // Overlayed rings
        if (mProgressValues != null) {
            mRingWidth = ((size - 2 * mInnerRadius) / mProgressValues.size()) / 2;
            mRingSpace = mRingWidth * RING_SPACE_RATIO;
            for (DataEntry entry : mDataSet.getEntries()) {
                drawRingForDataEntry(canvas, entry);
            }
        }

    }

    private void initValues() {
        if (mDataSet != null && mDataSet.getEntries() != null) {
            mProgressValues = new SparseArray<>();
            for (int i = 0; i < mDataSet.getEntries().size(); i++) {
                mProgressValues.put(i, 0.0f);
            }
        }
    }

    private void drawRingForDataEntry(Canvas canvas, DataEntry entry) {

        final int position = mDataSet.getEntries().indexOf(entry);
        final Rect bounds = getBounds();

        final float arcX0 = bounds.centerX() - mInnerRadius / 2 - (position + 1) * mRingWidth;
        final float arcY0 = bounds.centerY() - mInnerRadius / 2 - (position + 1) * mRingWidth;
        final float arcX = bounds.centerX() + mInnerRadius / 2 + (position + 1) * mRingWidth;
        final float arcY = bounds.centerY() + mInnerRadius / 2 + (position + 1) * mRingWidth;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mRingWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mArcElements.set(arcX0, arcY0, arcX, arcY);


        // Inner plain ring
        mPaint.setColor(entry.getEmptyColor());
        canvas.drawArc(mArcElements, 0, 2 * PI, false, mPaint);

        // Inner progress ring
        mPaint.setColor(entry.getFillColor());
        if (position > 0 && position % 2 != 0) {
            mPaint.setStrokeWidth(mRingWidth - mRingSpace);
        }
        canvas.drawArc(mArcElements, START_ANGLE, getAngleFromProgress(mProgressValues.get(position)), false, mPaint);

        // Drawable
        final Drawable drawable = entry.getDrawable();
        if (drawable != null) {
            final int left = (int) (bounds.centerX() - mRingWidth / 2 + mRingWidth * DRAWABLE_PADDING_RATIO);
            final int top = (int) (bounds.centerY() - mInnerRadius / 2 - (position + 1) * mRingWidth - mRingWidth / 2 + mRingWidth * DRAWABLE_PADDING_RATIO);
            final int right = (int) (left + mRingWidth - mRingWidth * DRAWABLE_PADDING_RATIO);
            final int bottom = (int) (top + mRingWidth - mRingWidth * DRAWABLE_PADDING_RATIO);
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }

        // Text
        if (!TextUtils.isEmpty(entry.getText())) {
            final int x = (int) (bounds.centerX() - mRingWidth / 2 + mRingWidth * DRAWABLE_PADDING_RATIO);
            final int y = (int) (bounds.centerY() - mInnerRadius / 2 - (position + 1) * mRingWidth - mRingWidth / 2 + mRingWidth * DRAWABLE_PADDING_RATIO + mRingWidth / 2);
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(mRingWidth * TEXT_SIZE_RATIO);
            canvas.drawText(entry.getText(), x, y, mPaint);
        }

    }

    private Animator prepareShowAnimation() {
        final AnimatorSet animatorSet = new AnimatorSet();
        final List<Animator> animators = new ArrayList<>();

        for (int i = 0; i < mProgressValues.size(); i++) {
            final int position = i;
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, mDataSet.getEntries().get(i).getProgress());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mProgressValues.put(position, (Float) animation.getAnimatedValue());
                    invalidateSelf();
                }
            });
            valueAnimator.setDuration(ANIMATION_DURATION);
            valueAnimator.setInterpolator(new AccelerateInterpolator());
            animators.add(valueAnimator);
        }

        animatorSet.playTogether(animators);

        return animatorSet;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return 1 - mPaint.getAlpha();
    }

    public DataSet getDataSet() {
        return mDataSet;
    }

}
