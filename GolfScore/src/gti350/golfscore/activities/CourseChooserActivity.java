package gti350.golfscore.activities;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.domain.Course;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.ThumbnailTextItemView;
import gti350.golfscore.views.listeners.OnThumbnailTextItemClickListener;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CourseChooserActivity extends Activity
	implements OnThumbnailTextItemClickListener, OnKeyListener {

	private LinearLayout llCourses;
	private EditText etSearch;
	private ArrayList<Course> courses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_chooser);
		
		llCourses = (LinearLayout)findViewById(R.id.llCourses);
		etSearch = (EditText)findViewById(R.id.etSearch);
		
		etSearch.setOnKeyListener(this);
		
		courses = CourseData.getAll();
		
		TextClearButtonHelper.set(etSearch, getResources().getDrawable(R.drawable.delete), this);
		
		fill();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.course_chooser, menu);
		return true;
	}
	
	@Override
	public boolean onKey(View v, int arg1, KeyEvent arg2) {
		if (v == etSearch) {
			filter(etSearch.getText().toString());
		}
		
		return false;
	}

	@Override
	public void onThumbnailTextItemClick(View view, int id) {
		Intent i = new Intent(getApplicationContext(), AddPlayersActivity.class);
		i.putExtra("course_id", id);
		startActivity(i);
	}
	
	private void fill() {
		filter("");
	}
	
	/**
	 * Only displays the courses containing the filter word.
	 * 
	 * @param games
	 * @param filterWord
	 */
	private void filter(String filterWord) {
		Course c;
		
		llCourses.removeAllViews();
		
		for (int i = 0; i < courses.size(); i++) {
			c = courses.get(i);
			
			if (c.toString().toUpperCase(Locale.CANADA).contains(filterWord.toUpperCase(Locale.CANADA))) {
				ThumbnailTextItemView ttiv = new ThumbnailTextItemView(this.getBaseContext(), c.getId(), getResources().getDrawable(c.getDrawableId()), c.toString());
				ttiv.setOnThumbnailTextItemClickListener(this);
				llCourses.addView(ttiv);
			}
		}
	}
	
}
