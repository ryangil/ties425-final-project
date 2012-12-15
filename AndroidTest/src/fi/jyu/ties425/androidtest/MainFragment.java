package fi.jyu.ties425.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		final Button btnList = (Button)view.findViewById(R.id.button_list);
		btnList.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				MainActivity.showFragment(R.id.listFragment);
			}
		});
		final Button btnMap = (Button)view.findViewById(R.id.button_map);
		btnMap.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(getActivity().getBaseContext(), MapMainActivity.class);
                startActivityForResult(myIntent, 0);
			}
		});
		final Button btnExit = (Button)view.findViewById(R.id.button_exit);
		btnExit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.exit(0);
			}
		});
		return view;
	}

}
