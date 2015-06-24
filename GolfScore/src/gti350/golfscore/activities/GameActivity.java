package gti350.golfscore.activities;

import java.util.ArrayList;

import gti350.golfscore.R;
import gti350.golfscore.activities.swiping.SwipeActivity;
import gti350.golfscore.data.CourseData;
import gti350.golfscore.data.GameData;
import gti350.golfscore.data.PlayerData;
import gti350.golfscore.domain.Course;
import gti350.golfscore.domain.Game;
import gti350.golfscore.domain.Player;
import gti350.golfscore.views.PlusMinusWithSubtextItemView;
import gti350.golfscore.views.listeners.OnPlusMinusItemValueChangedListener;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends SwipeActivity
	implements OnClickListener, OnPlusMinusItemValueChangedListener {

	private Game game;
	private Course course;
	private ArrayList<Player> players;
	private int page;
	private ImageView ivNavigationDots;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		// Get the data used on this activity
		Game game = GameData.get(getIntent().getIntExtra("game_id", -1));
		Course course = CourseData.get(game.getCourseId());
		ArrayList<Player> players = PlayerData.getAll(game.getId());
		int page = getIntent().getIntExtra("page", 1);
		
		create(game, course, players, page);
	}
	
	private void create(Game game, Course course, ArrayList<Player> players, int page) {
		this.game = game;
		this.course = course;
		this.players = players;
		this.page = page;
		
		// Update the current page of the game.
		// When the user comes back, he'll be directed to this page.
		this.game.setCurrentPage(page);
		GameData.update(this.game);

		// Set course name at the top of the activity
		TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
		tvTitle.setText(course.getName());
		tvTitle.setCompoundDrawablesWithIntrinsicBounds(course.getDrawableId(), 0, 0, 0);
		
		RelativeLayout rlSummary = (RelativeLayout)findViewById(R.id.rlSummary);
		RelativeLayout rlHole = (RelativeLayout)findViewById(R.id.rlHole);
		
		if (page == 10) {
			rlHole.setVisibility(View.INVISIBLE);
			rlSummary.setVisibility(View.VISIBLE);
			create9HoleSummary();
		}
		else if (page == 20) {
			rlHole.setVisibility(View.INVISIBLE);
			rlSummary.setVisibility(View.VISIBLE);
			create18HoleSummary();
		}
		else {
			rlHole.setVisibility(View.VISIBLE);
			rlSummary.setVisibility(View.INVISIBLE);
			createHolePage();
		}
		
		handleNavigationDots();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.current_game, menu);
		return true;
	}
	
	/**
	 * Overrided to always go back to the main activity instead.
	 */
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
	}

	/**
	 * Swipes to the previous hole, if the current hole
	 * is not 1.
	 */
	@Override
	protected void previous() {
		if (page > 1) {
			create(game, course, players, page - 1);
		}
	}

	/**
	 * Swipes to the next hole, if the current hole
	 * is not 18.
	 */
	@Override
	protected void next() {
		if (page < 20) {
			create(game, course, players, page + 1);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == ivNavigationDots) {
			Intent i = new Intent(getApplicationContext(), HoleQuickAccessActivity.class);
			i.putExtra("game_id", game.getId());
			i.putExtra("page", page);
			startActivity(i);
		}
	}

	@Override
	public void onPlusMinusItemValueChanged(View view, int id, int newValue) {
		Player player = PlayerData.get(id);
		int hole = getHoleFromPageNumber();
		player.setScore(hole, newValue);
		((PlusMinusWithSubtextItemView)view).setSubtext(getScoreString(player, hole));
		PlayerData.update(player);
	}
	
	private void handleNavigationDots() {
		ivNavigationDots = (ImageView)findViewById(R.id.ivNavigationDots);
		
		switch (page) {
			case 1: ivNavigationDots.setImageResource(R.drawable.circles_1of20); break;
			case 2: ivNavigationDots.setImageResource(R.drawable.circles_2of20); break;
			case 3: ivNavigationDots.setImageResource(R.drawable.circles_3of20); break;
			case 4: ivNavigationDots.setImageResource(R.drawable.circles_4of20); break;
			case 5: ivNavigationDots.setImageResource(R.drawable.circles_5of20); break;
			case 6: ivNavigationDots.setImageResource(R.drawable.circles_6of20); break;
			case 7: ivNavigationDots.setImageResource(R.drawable.circles_7of20); break;
			case 8: ivNavigationDots.setImageResource(R.drawable.circles_8of20); break;
			case 9: ivNavigationDots.setImageResource(R.drawable.circles_9of20); break;
			case 10: ivNavigationDots.setImageResource(R.drawable.circles_10of20); break;
			case 11: ivNavigationDots.setImageResource(R.drawable.circles_11of20); break;
			case 12: ivNavigationDots.setImageResource(R.drawable.circles_12of20); break;
			case 13: ivNavigationDots.setImageResource(R.drawable.circles_13of20); break;
			case 14: ivNavigationDots.setImageResource(R.drawable.circles_14of20); break;
			case 15: ivNavigationDots.setImageResource(R.drawable.circles_15of20); break;
			case 16: ivNavigationDots.setImageResource(R.drawable.circles_16of20); break;
			case 17: ivNavigationDots.setImageResource(R.drawable.circles_17of20); break;
			case 18: ivNavigationDots.setImageResource(R.drawable.circles_18of20); break;
			case 19: ivNavigationDots.setImageResource(R.drawable.circles_19of20); break;
			case 20: ivNavigationDots.setImageResource(R.drawable.circles_20of20); break;
		}
		
		ivNavigationDots.setOnClickListener(this);
	}
	
	private void createHolePage() {
		int hole = getHoleFromPageNumber();
		
		((TextView)findViewById(R.id.tvPar)).setText("Par " + course.getPar(hole));
		((TextView)findViewById(R.id.tvHole)).setText("Hole #" + hole);
		LinearLayout llPlayers = (LinearLayout)findViewById(R.id.llPlayers);
		
		llPlayers.removeAllViews();
		
		// Fills the ScrollView with PlusMinusItemViews
		Player p;
		for (int i = 0; i < players.size(); i++) {
			p = players.get(i);
			PlusMinusWithSubtextItemView pmiv = new PlusMinusWithSubtextItemView(this.getBaseContext(), p.getId(), p.getName(), getScoreString(p, hole), p.getScore(hole), 0, 99);
			pmiv.setOnValueChangedListener(this);
			llPlayers.addView(pmiv);
		}
	}
	
	private void create9HoleSummary() {
		TableLayout tlPlayers = (TableLayout)findViewById(R.id.tlPlayers);
		
		tlPlayers.removeAllViews();
		
		// header
		TextView out = new TextView(this.getBaseContext());
		
		out.setText("OUT");
		out.setTextColor(Color.BLACK);
		out.setLayoutParams(new TableRow.LayoutParams(2));
		out.setPadding(0, 0, 0, 5);
		
		TableRow header = new TableRow(this.getBaseContext());

		header.addView(out);
		tlPlayers.addView(header);

        // players
		for (Player p: players) {
			TextView name = new TextView(this.getBaseContext());
			name.setText(p.getName());
			name.setTextColor(Color.BLACK);
			name.setLayoutParams(new TableRow.LayoutParams(0));
			
	        TextView score = new TextView(this.getBaseContext());
	        score.setText(Integer.toString(p.getOUT()));
	        score.setTextColor(Color.BLACK);
	        score.setLayoutParams(new TableRow.LayoutParams(2));

	        TableRow row = new TableRow(this.getBaseContext());

	        row.addView(name);
	        row.addView(score);

	        tlPlayers.addView(row);
		}
		
		// par
		TextView parName = new TextView(this.getBaseContext());
		parName.setText("PAR");
		parName.setTextColor(Color.BLACK);
		parName.setLayoutParams(new TableRow.LayoutParams(0));
		
        TextView par = new TextView(this.getBaseContext());
        par.setText(Integer.toString(course.getOUT()));
        par.setTextColor(Color.BLACK);
        par.setLayoutParams(new TableRow.LayoutParams(2));

        TableRow parRow = new TableRow(this.getBaseContext());
        parRow.setPadding(0, 5, 0, 0);

        parRow.addView(parName);
        parRow.addView(par);
        tlPlayers.addView(parRow);
	}
	
	private void create18HoleSummary() {
		TableLayout tlPlayers = (TableLayout)findViewById(R.id.tlPlayers);
		
		tlPlayers.removeAllViews();
		
		TextView out = new TextView(this.getBaseContext());
		TextView in = new TextView(this.getBaseContext());
		TextView tot = new TextView(this.getBaseContext());
		
		out.setText("OUT");
		out.setTextColor(Color.BLACK);
		out.setPadding(10, 0, 0, 0);
		out.setLayoutParams(new TableRow.LayoutParams(1));

		in.setText("IN");
		in.setTextColor(Color.BLACK);
		in.setPadding(10, 0, 0, 0);
		in.setLayoutParams(new TableRow.LayoutParams(2));

		tot.setText("TOT");
		tot.setTextColor(Color.BLACK);
		tot.setPadding(10, 0, 0, 0);
		tot.setLayoutParams(new TableRow.LayoutParams(3));
		
		TableRow header = new TableRow(this.getBaseContext());

		header.addView(out);
		header.addView(in);
		header.addView(tot);
		header.setPadding(0, 0, 0, 5);
		tlPlayers.addView(header);

		for (Player p: players) {
			TextView name = new TextView(this.getBaseContext());
			TextView scoreOUT = new TextView(this.getBaseContext());
			TextView scoreIN= new TextView(this.getBaseContext());
			TextView scoreTOT = new TextView(this.getBaseContext());
			
			name.setText(p.getName());
			name.setTextColor(Color.BLACK);
			name.setLayoutParams(new TableRow.LayoutParams(0));
			
			scoreOUT.setText(Integer.toString(p.getOUT()));
			scoreOUT.setTextColor(Color.BLACK);
			scoreOUT.setLayoutParams(new TableRow.LayoutParams(1));
			scoreOUT.setPadding(10, 0, 0, 0);
			
			scoreIN.setText(Integer.toString(p.getIN()));
			scoreIN.setTextColor(Color.BLACK);
			scoreIN.setLayoutParams(new TableRow.LayoutParams(2));
			scoreIN.setPadding(10, 0, 0, 0);
			
			scoreTOT.setText(Integer.toString(p.getTOT()));
			scoreTOT.setTextColor(Color.BLACK);
			scoreTOT.setLayoutParams(new TableRow.LayoutParams(3));
			scoreTOT.setPadding(10, 0, 0, 0);

	        TableRow row = new TableRow(this.getBaseContext());

	        row.addView(name);
	        row.addView(scoreOUT);
	        row.addView(scoreIN);
	        row.addView(scoreTOT);

	        tlPlayers.addView(row);
		}
		
		// par
		TableRow parRow = new TableRow(this.getBaseContext());
		TextView parName = new TextView(this.getBaseContext());
		TextView parOUT = new TextView(this.getBaseContext());
		TextView parIN = new TextView(this.getBaseContext());
		TextView parTOT = new TextView(this.getBaseContext());
		
		parName.setText("PAR");
		parName.setTextColor(Color.BLACK);
		parName.setLayoutParams(new TableRow.LayoutParams(0));
		
		parOUT.setText(Integer.toString(course.getOUT()));
		parOUT.setTextColor(Color.BLACK);
		parOUT.setLayoutParams(new TableRow.LayoutParams(1));
		parOUT.setPadding(10, 0, 0, 0);

		parIN.setText(Integer.toString(course.getIN()));
		parIN.setTextColor(Color.BLACK);
		parIN.setLayoutParams(new TableRow.LayoutParams(2));
		parIN.setPadding(10, 0, 0, 0);

		parTOT.setText(Integer.toString(course.getTOT()));
		parTOT.setTextColor(Color.BLACK);
		parTOT.setLayoutParams(new TableRow.LayoutParams(3));
		parTOT.setPadding(10, 0, 0, 0);
		
		parRow.addView(parName);
		parRow.addView(parOUT);
		parRow.addView(parIN);
		parRow.addView(parTOT);
		parRow.setPadding(0, 5, 0, 0);

		tlPlayers.addView(parRow);
	}
	
	private String getScoreString(Player player, int hole) {
		int score = player.getScore(hole);
		int par = course.getPar(hole);
		
		if (score == 0) return "";
		if (score == 1) return "Hole-in-one";
		if (score + 3 <= par) return "Albatross or Less";
		if (score + 2 == par) return "Eagle";
		if (score + 1 == par) return "Birdie";
		if (score == par) return "Par";
		if (score - 5 >= par) return "Quintuple or More";
		if (score - 4 == par) return "Quadruple";
		if (score - 3 == par) return "Triple Bogey";
		if (score - 2 == par) return "Double Bogey";
		if (score - 1 == par) return "Bogey";
		
		return "";
	}
	
	private int getHoleFromPageNumber() {
		if (page < 10) {
			return page;
		}
		else {
			return page - 1;
		}
	}

}
