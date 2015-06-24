package gti350.golfscore.activities;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.domain.Course;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewCourseActivity extends Activity implements OnClickListener {

	private Course course;
	private Button btnEdit;
	private Button btnRemove;
	private TextView tvName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_course);
		
		btnEdit = (Button)findViewById(R.id.btnEdit);
		btnRemove = (Button)findViewById(R.id.btnRemove);
		tvName = (TextView)findViewById(R.id.textViewViewCourseCourseName);
		
		btnEdit.setOnClickListener(this);
		btnRemove.setOnClickListener(this);
		
		course = CourseData.get(getIntent().getIntExtra("course_id", -1));
		tvName.setText(course.getName());
		tvName.setCompoundDrawablesWithIntrinsicBounds(course.getDrawableId(), 0, 0, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view_course, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnEdit) {
			Intent i = new Intent(getApplicationContext(), CreateEditCourseActivity.class);
			i.putExtra("course_id", course.getId());
			startActivity(i);
		}
		else if (v == btnRemove) {
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        if (which == DialogInterface.BUTTON_POSITIVE) {
			        	CourseData.delete(course.getId());
			        	Intent i = new Intent(getApplicationContext(), CourseListActivity.class);
						startActivity(i);
			        }
			    }
			};
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete this course?")
				.setPositiveButton("Yes", dialogClickListener)
			    .setNegativeButton("No", dialogClickListener).show();
		}
	}
}
