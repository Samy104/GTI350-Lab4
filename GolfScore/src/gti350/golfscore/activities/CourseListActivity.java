package gti350.golfscore.activities;

import java.util.ArrayList;
import java.util.Locale;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.domain.Course;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.ThumbnailTextItemView;
import gti350.golfscore.views.listeners.OnThumbnailTextItemClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CourseListActivity extends Activity
	implements OnClickListener, OnThumbnailTextItemClickListener, OnKeyListener {

	private Button btnCreate;
	private Button btnDownload;
	private LinearLayout llCourses;
	private EditText etSearch;
	private ArrayList<Course> courses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list);
		
		btnCreate = (Button)findViewById(R.id.btnCreateNewCourse);
		btnDownload = (Button)findViewById(R.id.btnDownloadCourse);
		llCourses = (LinearLayout)findViewById(R.id.llCourses);
		etSearch = (EditText)findViewById(R.id.etSearch);
		
		btnCreate.setOnClickListener(this);
		btnDownload.setOnClickListener(this);
		etSearch.setOnKeyListener(this);
		
		courses = CourseData.getAll();
		
		System.out.println("COURSE NUMBER : " + courses.size());
		
		TextClearButtonHelper.set(etSearch, getResources().getDrawable(R.drawable.delete), this);
		
		fill();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		courses = CourseData.getAll();
		System.out.println(courses.size());
		fill();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.course_list, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}
	
	@Override
	public void onClick(View v) {
		if (v == btnCreate) {
			Intent i = new Intent(getApplicationContext(), CreateEditCourseActivity.class);
			startActivity(i);
		}
		else if (v == btnDownload) {
			Intent i = new Intent(getApplicationContext(), DownloadCoursesActivity.class);
			startActivity(i);
		}
	}
	
	@Override
	public boolean onKey(View v, int arg1, KeyEvent arg2) {
		if (v == etSearch) {
			filter(etSearch.getText().toString());
		}
		
		return false;
	}

	/**
	 * Fills up the ScrollView with the 'Everything.courses' list.
	 */
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

	@Override
	public void onThumbnailTextItemClick(View view, int id) {
		Intent i = new Intent(getApplicationContext(), ViewCourseActivity.class);
		i.putExtra("course_id", id);
		startActivity(i);
	}

}
