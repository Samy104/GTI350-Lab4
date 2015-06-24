package gti350.golfscore.activities;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.domain.Course;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.PlusMinusItemView;
import gti350.golfscore.views.listeners.OnPlusMinusItemValueChangedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreateEditCourseActivity extends Activity
	implements OnClickListener, OnPlusMinusItemValueChangedListener, OnKeyListener {

	private boolean editMode;
	private Course course;
	private TextView tvCourseName;
	private Button btnNext;
	private Button btnBack;
	private Button btnFinish;
	private RelativeLayout lytName;
	private RelativeLayout lytPar;
	private LinearLayout llHoles;
	private EditText etCourseName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_edit_course);
		tvCourseName = (TextView)findViewById(R.id.textViewCourseName);
		
		btnNext = (Button)findViewById(R.id.btnNext);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnFinish = (Button)findViewById(R.id.btnFinish);
		lytName = (RelativeLayout)findViewById(R.id.lytName);
		lytPar = (RelativeLayout)findViewById(R.id.lytPar);
		etCourseName = (EditText)findViewById(R.id.editTextCourseName);
		llHoles = (LinearLayout)findViewById(R.id.llHoles);
		
		TextClearButtonHelper.set(etCourseName, getResources().getDrawable(R.drawable.delete));
		
		etCourseName.setOnKeyListener(this);
		etCourseName.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		btnNext.setOnClickListener(this);
		btnFinish.setOnClickListener(this);
		
		course = CourseData.get(getIntent().getIntExtra("course_id", -1));
		
		if (course != null) {
			etCourseName.setText(course.getName());
			editMode = true;
		}
		else {
			course = new Course(-1, "", new int[] {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}, R.drawable.default_course);
			editMode = false;
		}
		
		for (int i = 1; i <= 18; i++) {
			PlusMinusItemView view = new PlusMinusItemView(this.getBaseContext(), i, "Hole #" + i, course.getPar(i), 0, 99);
			view.setOnValueChangedListener(this);
			llHoles.addView(view);
		}
		
		btnNext.setEnabled(!etCourseName.getText().toString().trim().equals(""));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_edit_course, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnNext) {
			course.setName(etCourseName.getText().toString().trim());
			tvCourseName.setText(course.getName());
			CourseData.update(course);
			lytName.setVisibility(View.INVISIBLE);
			lytPar.setVisibility(View.VISIBLE);
		}
		else if (v == btnBack) {
			etCourseName.setText(course.getName());
			lytName.setVisibility(View.VISIBLE);
			lytPar.setVisibility(View.INVISIBLE);
		}
		else if (v == btnFinish) {
			if (editMode) {
				CourseData.update(course);
			}
			else {
				CourseData.add(course.getName(), course.getPars(), R.drawable.default_course);
			}
			
			Intent i = new Intent(getApplicationContext(), CourseListActivity.class);
			startActivity(i);
		}
		else if (v == etCourseName) {
			btnNext.setEnabled(!etCourseName.getText().toString().trim().equals(""));
		}
	}

	@Override
	public void onPlusMinusItemValueChanged(View view, int id, int newValue) {
		course.setPar(id, newValue);
		CourseData.update(course);
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (v == etCourseName) {
			btnNext.setEnabled(!etCourseName.getText().toString().trim().equals(""));
			
			// hide keyboard on enter pressed
			if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
					(keyCode == KeyEvent.KEYCODE_ENTER)) {
				
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(etCourseName.getWindowToken(), 0);
			}
		}
		return false;
	}

}
