package gti350.golfscore.activities;

import java.util.ArrayList;

import gti350.golfscore.R;
import gti350.golfscore.activities.swiping.SwipeActivity;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.domain.Course;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StatisticsCourseActivity extends SwipeActivity {

	private Spinner spnrCourses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics_course);
		
		spnrCourses = (Spinner)findViewById(R.id.spnrCourses);
		ArrayList<Course> courses = CourseData.getAll();
		ArrayList<String> strings = new ArrayList<String>();
		
		for (Course c: courses) {
			strings.add(c.toString());
		}
		
	    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strings);
	    spnrCourses.setAdapter(spinnerArrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.statistics_course, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	@Override
	protected void previous() {
		Intent i = new Intent(this.getBaseContext(), StatisticsGlobalActivity.class);    
        startActivityForResult(i, 0);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right); 
	}

	@Override
	protected void next() {
		Intent i = new Intent(this.getBaseContext(), StatisticsGraphActivity.class);    
        startActivityForResult(i, 0);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right); 
	}

}
