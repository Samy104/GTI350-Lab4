package gti350.golfscore.activities;

import gti350.golfscore.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This activity allows the user to quickly jump to a hole
 * during a game, instead of swiping.
 * 
 * @author Simon RG
 */
public class HoleQuickAccessActivity extends Activity implements OnClickListener {

	private Button btnHole1;
	private Button btnHole2;
	private Button btnHole3;
	private Button btnHole4;
	private Button btnHole5;
	private Button btnHole6;
	private Button btnHole7;
	private Button btnHole8;
	private Button btnHole9;
	private Button btnHole10;
	private Button btnHole11;
	private Button btnHole12;
	private Button btnHole13;
	private Button btnHole14;
	private Button btnHole15;
	private Button btnHole16;
	private Button btnHole17;
	private Button btnHole18;
	private Button btnSummary9Hole;
	private Button btnSummary18Hole;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_quick_access);

		btnHole1 = (Button)findViewById(R.id.btnHole1);
		btnHole2 = (Button)findViewById(R.id.btnHole2);
		btnHole3 = (Button)findViewById(R.id.btnHole3);
		btnHole4 = (Button)findViewById(R.id.btnHole4);
		btnHole5 = (Button)findViewById(R.id.btnHole5);
		btnHole6 = (Button)findViewById(R.id.btnHole6);
		btnHole7 = (Button)findViewById(R.id.btnHole7);
		btnHole8 = (Button)findViewById(R.id.btnHole8);
		btnHole9 = (Button)findViewById(R.id.btnHole9);
		btnHole10 = (Button)findViewById(R.id.btnHole10);
		btnHole11 = (Button)findViewById(R.id.btnHole11);
		btnHole12 = (Button)findViewById(R.id.btnHole12);
		btnHole13 = (Button)findViewById(R.id.btnHole13);
		btnHole14 = (Button)findViewById(R.id.btnHole14);
		btnHole15 = (Button)findViewById(R.id.btnHole15);
		btnHole16 = (Button)findViewById(R.id.btnHole16);
		btnHole17 = (Button)findViewById(R.id.btnHole17);
		btnHole18 = (Button)findViewById(R.id.btnHole18);
		btnSummary9Hole = (Button)findViewById(R.id.btnSummary9Hole);
		btnSummary18Hole = (Button)findViewById(R.id.btnSummary18Hole);
		
		btnHole1.setOnClickListener(this);
		btnHole2.setOnClickListener(this);
		btnHole3.setOnClickListener(this);
		btnHole4.setOnClickListener(this);
		btnHole5.setOnClickListener(this);
		btnHole6.setOnClickListener(this);
		btnHole7.setOnClickListener(this);
		btnHole8.setOnClickListener(this);
		btnHole9.setOnClickListener(this);
		btnHole10.setOnClickListener(this);
		btnHole11.setOnClickListener(this);
		btnHole12.setOnClickListener(this);
		btnHole13.setOnClickListener(this);
		btnHole14.setOnClickListener(this);
		btnHole15.setOnClickListener(this);
		btnHole16.setOnClickListener(this);
		btnHole17.setOnClickListener(this);
		btnHole18.setOnClickListener(this);
		btnSummary9Hole.setOnClickListener(this);
		btnSummary18Hole.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hole_quick_access, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), GameActivity.class);
		i.putExtra("game_id", getIntent().getIntExtra("game_id", -1));
		i.putExtra("page", getIntent().getIntExtra("page", 1));
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(), GameActivity.class);
		
		i.putExtra("game_id", getIntent().getIntExtra("game_id", -1));
		
		if (v == btnHole1) {
			i.putExtra("page", 1);
		}
		else if (v == btnHole2) {
			i.putExtra("page", 2);
		}
		else if (v == btnHole3) {
			i.putExtra("page", 3);
		}
		else if (v == btnHole4) {
			i.putExtra("page", 4);
		}
		else if (v == btnHole5) {
			i.putExtra("page", 5);
		}
		else if (v == btnHole6) {
			i.putExtra("page", 6);
		}
		else if (v == btnHole7) {
			i.putExtra("page", 7);
		}
		else if (v == btnHole8) {
			i.putExtra("page", 8);
		}
		else if (v == btnHole9) {
			i.putExtra("page", 9);
		}
		else if (v == btnHole10) {
			i.putExtra("page", 11);
		}
		else if (v == btnHole11) {
			i.putExtra("page", 12);
		}
		else if (v == btnHole12) {
			i.putExtra("page", 13);
		}
		else if (v == btnHole13) {
			i.putExtra("page", 14);
		}
		else if (v == btnHole14) {
			i.putExtra("page", 15);
		}
		else if (v == btnHole15) {
			i.putExtra("page", 16);
		}
		else if (v == btnHole16) {
			i.putExtra("page", 17);
		}
		else if (v == btnHole17) {
			i.putExtra("page", 18);
		}
		else if (v == btnHole18) {
			i.putExtra("page", 19);
		}
		else if (v == btnSummary9Hole) {
			i.putExtra("page", 10);
		}
		else if (v == btnSummary18Hole) {
			i.putExtra("page", 20);
		}
		else {
			i.putExtra("page", 1);
		}
		
		startActivity(i);
	}
	
}
