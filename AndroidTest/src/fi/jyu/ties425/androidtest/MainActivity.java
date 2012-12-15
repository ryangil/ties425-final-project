package fi.jyu.ties425.androidtest;

import java.util.HashMap;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements LocationWait {

	private final static HashMap<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();
	private static FragmentManager manager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        manager = getSupportFragmentManager();

        fragments.put(R.id.splashFragment, manager.findFragmentById(R.id.splashFragment));
        fragments.put(R.id.listFragment, manager.findFragmentById(R.id.listFragment));
        fragments.put(R.id.mainFragment, manager.findFragmentById(R.id.mainFragment));
        
        MainActivity.showFragment(R.id.splashFragment);

        GpsTracker tracker = new GpsTracker(this.getApplicationContext());
        tracker.notifyWhenReady(this, tracker);
        GpsTracker.addLocationSubscriber((ListFragment)fragments.get(R.id.listFragment));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override  
    public void onBackPressed() {
    	MainActivity.showFragment(R.id.mainFragment);
    }
    
	public void notifyLocationAvailable(Location location) {
		System.out.println("location: " + location);
		MainActivity.showFragment(R.id.mainFragment);
	}
	
	public static void showFragment(int fragmentId)
	{
		FragmentTransaction tx = manager.beginTransaction();
		for(int key : MainActivity.fragments.keySet())
		{
			if(key == fragmentId)
			{
				tx.show(MainActivity.fragments.get(key));
			}
			else
			{
				tx.hide(MainActivity.fragments.get(key));
			}
		}
		tx.commit();
	}
}
