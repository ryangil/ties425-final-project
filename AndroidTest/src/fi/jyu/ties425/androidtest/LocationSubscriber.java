package fi.jyu.ties425.androidtest;

import android.location.Location;

public interface LocationSubscriber {
	public void onLocationChanged(Location location);
}
