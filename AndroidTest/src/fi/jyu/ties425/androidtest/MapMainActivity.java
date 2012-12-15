package fi.jyu.ties425.androidtest;

import java.util.Date;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MapMainActivity extends MapActivity implements LocationSubscriber{
	
	private MapView mapView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.map_activity);
		mapView = (MapView)findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		for(Location location : GpsTracker.getLocations())
		{
			addLocation(location);
		}
		GpsTracker.addLocationSubscriber(this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLocationChanged(Location location) {
		addLocation(location);
	}

	
	private void addLocation(Location location)
	{
		List overlays = mapView.getOverlays();
		int lat = (int) (location.getLatitude() * 1E6);
	    int lng = (int) (location.getLongitude() * 1E6);
	    GeoPoint point = new GeoPoint(lat, lng);
		OverlayItem overlayitem = new OverlayItem(point, "coords: " + point, "timestamp: " + android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date(location.getTime())));
		Drawable drawable = this.getResources().getDrawable(R.drawable.ic_action_search);
		MyItemizedOverlay itemizedOverlay = new MyItemizedOverlay(drawable, this);
		itemizedOverlay.addOverlay(overlayitem);
		overlays.add(itemizedOverlay);
		mapView.getController().setCenter(point);
		mapView.refreshDrawableState();
	}
}
