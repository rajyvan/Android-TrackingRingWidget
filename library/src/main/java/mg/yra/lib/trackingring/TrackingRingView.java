package mg.yra.lib.trackingring;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Yvan on 18/08/15.
 */
public class TrackingRingView extends ImageView {

    private DataSet mDataSet;
    private OverlayedCircularProgressDrawable mDrawable;

    public TrackingRingView(Context context) {
        super(context);
    }

    public TrackingRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrackingRingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DataSet getDataSet() {
        return mDataSet;
    }

    public void setDataSet(DataSet dataSet) {
        mDataSet = dataSet;
        mDrawable = new OverlayedCircularProgressDrawable(mDataSet, 0.325f);
        setImageDrawable(mDrawable);
    }

}
