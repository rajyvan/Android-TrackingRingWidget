package mg.yra.trackingringwidget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import mg.yra.lib.trackingring.DataEntry;
import mg.yra.lib.trackingring.DataSet;
import mg.yra.lib.trackingring.OverlayedCircularProgressDrawable;
import mg.yra.lib.trackingring.TrackingRingView;

public class MainActivity extends Activity {

    private TrackingRingView mTrackingRingView1;
    private TrackingRingView mTrackingRingView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrackingRingView1 = (TrackingRingView) findViewById(R.id.tracking_ring1);
        mTrackingRingView2 = (TrackingRingView) findViewById(R.id.tracking_ring2);
        configureRingView1();
        configureRingView2();
    }

    private void configureRingView1() {
        final List<DataEntry> entries = new ArrayList<>();
        entries.add(new DataEntry(35, getResources().getDrawable(android.R.drawable.ic_menu_mapmode), Color.BLUE, Color.LTGRAY));
        entries.add(new DataEntry(50, getResources().getDrawable(android.R.drawable.ic_menu_camera), Color.MAGENTA, Color.WHITE));
        entries.add(new DataEntry(60, getResources().getDrawable(android.R.drawable.ic_menu_compass), Color.GREEN, Color.LTGRAY));
        final DataSet dataset = new DataSet(entries);
        mTrackingRingView1.setDataSet(dataset);
    }

    private void configureRingView2() {
        final List<DataEntry> entries = new ArrayList<>();

        entries.add(new DataEntry(62, "62", Color.BLUE, Color.LTGRAY));
        entries.add(new DataEntry(87, "87", Color.CYAN, Color.WHITE));
        entries.add(new DataEntry(45, "45", Color.RED, Color.LTGRAY));
        final DataSet dataset = new DataSet(entries);
        mTrackingRingView2.setDataSet(dataset);
    }

}
