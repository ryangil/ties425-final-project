package fi.jyu.ties425.androidtest;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListItemAdapter extends ArrayAdapter<Location> {

	private Context appContext;
	private ArrayList<Location> locations;
	
	public ListItemAdapter(Context context, int resourceId, ArrayList<Location> locations) {
		super(context, resourceId, locations);
		this.appContext = context;
		this.locations = locations;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
		LayoutInflater vi = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.custom_list_adapter, null);
		}
		Location location = locations.get(position);
		if (location != null) {
			TextView coords = (TextView) v.findViewById(R.id.txtCoordinates);
			coords.setText("lat: " + location.getLatitude() + ", lng: " + location.getLongitude());
			TextView time = (TextView) v.findViewById(R.id.txtTime);
			time.setText("time: " + android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date(location.getTime())));
		}
		return v;		
	}
}
