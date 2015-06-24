package gti350.golfscore.activities;

import gti350.golfscore.R;
import gti350.golfscore.data.GameData;
import gti350.golfscore.data.PlayerData;
import gti350.golfscore.utils.TextClearButtonHelper;
import gti350.golfscore.views.ThumbnailTextDeletableItemView;
import gti350.golfscore.views.ThumbnailTextItemView;
import gti350.golfscore.views.listeners.OnThumbnailTextDeletableItemDeleteClickListener;

import java.util.Date;

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

public class AddPlayersActivity extends Activity
	implements OnClickListener, OnThumbnailTextDeletableItemDeleteClickListener, OnKeyListener {

	private LinearLayout llPlayers;
	private Button btnAddPlayer;
	private Button btnStartGame;
	private EditText etName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_players);
		
		llPlayers = (LinearLayout)findViewById(R.id.llPlayers);
		btnAddPlayer = (Button)findViewById(R.id.btnAddPlayer);
		btnStartGame = (Button)findViewById(R.id.btnStartGame);
		etName = (EditText)findViewById(R.id.etName);

		TextClearButtonHelper.set(etName, getResources().getDrawable(R.drawable.delete), this);
		
		btnAddPlayer.setEnabled(false);
		
		btnAddPlayer.setOnClickListener(this);
		btnStartGame.setOnClickListener(this);
		etName.setOnKeyListener(this);
		
		ThumbnailTextItemView view = new ThumbnailTextItemView(
			getBaseContext(), llPlayers.getChildCount(),
			getResources().getDrawable(R.drawable.golfer), "Me");
			
		llPlayers.addView(view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_players, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		if (v == btnAddPlayer) {
			if (!etName.getText().toString().trim().equals("")) {
				addView(etName.getText().toString().trim());
				etName.setText("");
				btnAddPlayer.setEnabled(false);
			}
		}
		else if (v == btnStartGame) {
			int gameId = GameData.add(new Date(), getIntent().getIntExtra("course_id", -1));
			
			PlayerData.add("Me", gameId);
			
			for (int i = 1; i < llPlayers.getChildCount(); i++) {
		        ThumbnailTextDeletableItemView view = (ThumbnailTextDeletableItemView)llPlayers.getChildAt(i);
		        PlayerData.add(view.getText(), gameId);
		    }

			Intent i = new Intent(getApplicationContext(), GameActivity.class);
			i.putExtra("game_id", gameId);
			startActivity(i);
		}
	}

	@Override
	public boolean onKey(View v, int arg1, KeyEvent arg2) {
		if (v == etName) {
			btnAddPlayer.setEnabled(!etName.getText().toString().trim().equals(""));
		}
		return false;
	}

	@Override
	public void onThumbnailTextDeletableItemDeleteClick(View view, int id) {
		llPlayers.removeView(view);
	}

	private void addView(String text) {
		ThumbnailTextDeletableItemView view = new ThumbnailTextDeletableItemView(
			getBaseContext(),
			llPlayers.getChildCount(),
			getResources().getDrawable(R.drawable.golfer),
			text,
			getResources().getDrawable(R.drawable.delete));
		
		view.setOnThumbnailTextDeletableItemDeleteClickListener(this);
		llPlayers.addView(view);
	}
	
}
