package mg.yra.lib.trackingring;

import android.graphics.drawable.Drawable;

/**
 * Created by Yvan on 31/07/15.
 */
public class DataEntry {

    private float mProgress;
    private Drawable mDrawable;
    private String mText;
    private int mFillColor;
    private int mEmptyColor;

    /**
     * @param progress   : progress value in percentage (%)
     * @param drawable   : drawable to draw over the progress bar
     * @param fillColor  : progression color
     * @param emptyColor : empty progression color
     */
    public DataEntry(float progress, Drawable drawable, int fillColor, int emptyColor) {
        mProgress = progress;
        mDrawable = drawable;
        mFillColor = fillColor;
        mEmptyColor = emptyColor;
        mText = null;
    }

    /**
     * @param progress   : progress value in percentage (%)
     * @param text       : text to draw over the progress bar
     * @param fillColor  : progression color
     * @param emptyColor : empty progression color
     */
    public DataEntry(float progress, String text, int fillColor, int emptyColor) {
        mProgress = progress;
        mText = text;
        mFillColor = fillColor;
        mEmptyColor = emptyColor;
        mDrawable = null;
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        mText = null;
    }

    public int getFillColor() {
        return mFillColor;
    }

    public void setFillColor(int fillColor) {
        mFillColor = fillColor;
    }

    public int getEmptyColor() {
        return mEmptyColor;
    }

    public void setEmptyColor(int emptyColor) {
        mEmptyColor = emptyColor;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        mDrawable = null;
    }

}
