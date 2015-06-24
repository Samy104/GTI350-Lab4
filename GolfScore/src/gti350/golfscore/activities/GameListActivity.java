package gti350.golfscore.activities;

import java.util.ArrayList;
import java.util.Locale;

import gti350.golfscore.R;
import gti350.golfscore.data.GameData;
import gti350.golfscore.domain.Game;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.ThumbnailTextItemView;
import gti350.golfscore.views.listeners.OnThumbnailTextItemClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class GameListActivity extends Activity
	implements OnClickListener, OnKeyListener, OnThumbnailTextItemClickListener {

	private Button btnStartNewGame;
	private EditText etSearch;
	private LinearLayout llGames;
	private ArrayList<Game> games;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		
		btnStartNewGame = (Button)findViewById(R.id.btnStartNewGame);
		etSearch = (EditText)findViewById(R.id.etSearch);
		llGames = (LinearLayout)findViewById(R.id.llGames);
		
		btnStartNewGame.setOnClickListener(this);
		etSearch.setOnKeyListener(this);

		games = GameData.getAll();
		
		TextClearButtonHelper.set(etSearch, getResources().getDrawable(R.drawable.delete), this);
		
		fill();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnStartNewGame) {
			Intent i = new Intent(getApplicationContext(), CourseChooserActivity.class);
			startActivity(i);
		}
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
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
		Game game = GameData.get(id);
		
		Intent i = new Intent(getApplicationContext(), GameActivity.class);
		i.putExtra("game_id", id);
		i.putExtra("page", game.getCurrentPage());
		startActivity(i);
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
		Drawable defaultThumbnail = getResources().getDrawable(R.drawable.default_course);
		Game g;
		
		llGames.removeAllViews();
		
		for (int i = 0; i < games.size(); i++) {
			g = games.get(i);
			
			if (g.toString().toUpperCase(Locale.CANADA).contains(filterWord.toUpperCase(Locale.CANADA))) {
				ThumbnailTextItemView ttiv = new ThumbnailTextItemView(this.getBaseContext(), g.getId(),
						defaultThumbnail, g.toString());
				ttiv.setOnThumbnailTextItemClickListener(this);
				llGames.addView(ttiv);
			}
		}
	}

}
