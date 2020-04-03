Android-TrackingRingWidget
==========================
It's a tracking activity ring widget like in Apple watch for Android.
<p> You have to populate it with a given dataset object as desribed below. You can customize ring colors, icons and text values as you like.</p>

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--TrackingRingWidget-green.svg?style=flat)](https://android-arsenal.com/details/1/2399)

<p> Feel free to contact me if you want some features or customization to be added.
<br>
<br>
![Let's see below](https://github.com/rajyvan/Android-TrackingRingWidget/tree/master/tracking.png)

Usage
==========================
For a simple implementation, take a look at the "sample" directory.

1. Include the library as a local library porject or add the dependency in your build.gradle.
       
        dependencies {
            compile 'com.github.rajyvan:TrackingRingWidget:1.0'
        }

2. Include the TrackingRingView in your layout. You can also instantiate it by code. Don't forget to give it a size (Will be improved on next release). 
      
             <mg.yra.lib.trackingring.TrackingRingView
              android:layout_height="256dp"
              android:layout_width="256dp" />

3. Create a dataset and set it to the TrackingRingView.
        
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

Changelog
==========================
Current version : 1.0

Developed by
==========================

Yvan RAJAONARIVONY
yvan.rajaonarivony@gmail.com
