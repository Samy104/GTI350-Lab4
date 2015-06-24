package gti350.golfscore.activities;

import java.util.Date;

import gti350.golfscore.R;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.data.DownloadableCourseData;
import gti350.golfscore.data.GameData;
import gti350.golfscore.data.PlayerData;
import gti350.golfscore.domain.Game;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Game mostRecent = null;
	private Button btnCurrentGame;
	private Button btnGames;
	private Button btnCourses;
	private Button btnStats;
	
	static {
		// Add default datas
		CourseData.add("Golferie Lorraine", R.drawable.default_course);
		CourseData.add("Golf des moulins", R.drawable.default_course);
		CourseData.add("Le boisee", R.drawable.default_course);
		DownloadableCourseData.add("Golf 18", R.drawable.golf18);
		DownloadableCourseData.add("Le versant", R.drawable.le_versant);
		GameData.add(new Date(), 1); // Today
		GameData.add(new Date(0), 2); // 01-01-1970
		PlayerData.add("Me", 1);
		PlayerData.add("Jim", 1);
		PlayerData.add("Carmen", 1);
		PlayerData.add("Me", 2);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mostRecent = GameData.getMostRecent();
		
		btnCurrentGame = (Button)findViewById(R.id.btnCurrentGame);
		btnGames = (Button)findViewById(R.id.btnGames);
		btnCourses = (Button)findViewById(R.id.btnCourses);
		btnStats = (Button)findViewById(R.id.btnStats);
		
		btnCurrentGame.setOnClickListener(this);
		btnGames.setOnClickListener(this);
		btnCourses.setOnClickListener(this);
		btnStats.setOnClickListener(this);
	
		// Disable the button if there is no current game.
		btnCurrentGame.setEnabled(mostRecent != null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		if (v == btnCurrentGame) {
			if (mostRecent != null) {
				Intent i = new Intent(getApplicationContext(), GameActivity.class);
				i.putExtra("game_id", mostRecent.getId());
				i.putExtra("page", mostRecent.getCurrentPage());
				startActivity(i);
			}
		}
		else if (v == btnGames) {
			Intent i = new Intent(getApplicationContext(), GameListActivity.class);
			startActivity(i);
		}
		else if (v == btnCourses) {
			Intent i = new Intent(getApplicationContext(), CourseListActivity.class);
			startActivity(i);
		}
		else if (v == btnStats) {
			Intent i = new Intent(getApplicationContext(), StatisticsGlobalActivity.class);
			startActivity(i);
		}
	}

}
