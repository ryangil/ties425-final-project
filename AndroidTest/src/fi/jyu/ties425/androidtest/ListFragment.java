package fi.jyu.ties425.androidtest;

import java.util.ArrayList;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListFragment extends Fragment implements LocationSubscriber {

	private ListView listView;
	private ArrayList<Location> locations = new ArrayList<Location>();
	private ListItemAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_fragment, container, false);
		listView = (ListView)view.findViewById(R.id.listView);
		adapter = new ListItemAdapter(getActivity(), getId(), locations);
		listView.setAdapter(adapter);
		return view;
	}

	public void onLocationChanged(Location location) {
		locations.add(location);
		adapter.notifyDataSetChanged();
	}
}
