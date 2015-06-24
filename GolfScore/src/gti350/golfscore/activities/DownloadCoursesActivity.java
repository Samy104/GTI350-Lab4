package gti350.golfscore.activities;

import java.util.ArrayList;
import java.util.Locale;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.data.DownloadableCourseData;
import gti350.golfscore.domain.Course;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.ThumbnailTextItemView;
import gti350.golfscore.views.listeners.OnThumbnailTextItemClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DownloadCoursesActivity extends Activity
	implements OnKeyListener, OnThumbnailTextItemClickListener {

	private LinearLayout llDownloadableCourses;
	private EditText etSearch;
	private ArrayList<Course> downloadableCourses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_courses);
		
		llDownloadableCourses = (LinearLayout)findViewById(R.id.llDownloadableCourses);
		etSearch = (EditText)findViewById(R.id.etSearch);
		
		etSearch.setOnKeyListener(this);
		
		TextClearButtonHelper.set(etSearch, getResources().getDrawable(R.drawable.delete), this);
		
		downloadableCourses = DownloadableCourseData.getAll();
		
		fill();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.download_courses, menu);
		return true;
	}

	@Override
	public boolean onKey(View v, int arg1, KeyEvent arg2) {
		if (v == etSearch) {
			filter(etSearch.getText().toString());
		}
		
		return false;
	}
	
	/**
	 * Simulates a download by removing the course from Everything.downloadableCourses
	 * list and adding it to Everything.courses list.
	 */
	@Override
	public void onThumbnailTextItemClick(View view, final int id) {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        if (which == DialogInterface.BUTTON_POSITIVE) {
		        	Course course = DownloadableCourseData.get(id);
		        	
		        	CourseData.add(course.getName(), course.getPars(), course.getDrawableId());
		        	DownloadableCourseData.delete(id);
		        	downloadableCourses = DownloadableCourseData.getAll();
		        	
		        	fill();
		        }
		    }
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to download this course?")
			.setPositiveButton("Yes", dialogClickListener)
		    .setNegativeButton("No", dialogClickListener).show();
	}

	/**
	 * Fills up the ScrollView with the 'games' list.
	 */
	private void fill() {
		filter("");
	}
	
	/**
	 * Only displays the games containing the filter word.
	 * 
	 * @param games
	 * @param filterWord
	 */
	private void filter(String filterWord) {
		Course c;
		
		llDownloadableCourses.removeAllViews();
		
		for (int i = 0; i < downloadableCourses.size(); i++) {
			c = downloadableCourses.get(i);
			
			if (c.toString().toUpperCase(Locale.CANADA).contains(filterWord.toUpperCase(Locale.CANADA))) {
				ThumbnailTextItemView ttiv = new ThumbnailTextItemView(this.getBaseContext(), c.getId(), getResources().getDrawable(c.getDrawableId()), c.toString());
				ttiv.setOnThumbnailTextItemClickListener(this);
				llDownloadableCourses.addView(ttiv);
			}
		}
	}
	
}
