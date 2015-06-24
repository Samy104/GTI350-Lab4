package gti350.golfscore.activities;

import gti350.golfscore.R;
import gti350.golfscore.activities.swiping.SwipeActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;

public class StatisticsGlobalActivity extends SwipeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics_global);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.statistics_global, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	@Override
	protected void previous() {
	}

	@Override
	protected void next() {
		Intent i = new Intent(this.getBaseContext(), StatisticsCourseActivity.class);    
        startActivityForResult(i, 0);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);  
	}

}
