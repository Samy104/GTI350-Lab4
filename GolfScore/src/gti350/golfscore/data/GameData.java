package gti350.golfscore.data;

import gti350.golfscore.comparators.GameDateComparator;
import gti350.golfscore.domain.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class GameData {
	
	private static int nextId = 1;
	
	/**
	 * Games that show up in the Game List Activity.
	 * 
	 * The most recent game is considered the current game.
	 */
	private static ArrayList<Game> games = new ArrayList<Game>();
	
	public static Game get(int id) {
		for (Game g: games) {
			if (g.getId() == id) {
				return g;
			}
		}
		
		return null;
	}
	
	public static Game getMostRecent() {
		if (games.size() == 0) return null;
		
		Game game = games.get(0);
		
		for (Game g: games) {
			if (g.getDate().after(game.getDate())) {
				game = g;
			}
		}
		
		return game;
	}
	
	public static ArrayList<Game> getAll() {
		Collections.sort(games, new GameDateComparator());
		return games;
	}
	
	public static int add(Date date, int courseId) {
		Game game = new Game(nextId, date, courseId, 1);
		games.add(game);
		nextId++;
		return game.getId();
	}
	
	public static void delete(int id) {
		int indexToDelete = -1;
		
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getId() == id) {
				indexToDelete = i;
				break;
			}
		}
		
		if (indexToDelete != -1) {
			games.remove(indexToDelete);
		}
	}
	
	public static void update(Game game) {
		if (games.size() == 0) return;
		
		Game real = null;
		
		for (Game g: games) {
			if (g.getId() == game.getId()) {
				real = g;
				break;
			}
		}
		
		if (real != null) {
			real.setCourseId(game.getCourseId());
			real.setDate(game.getDate());
		}
	}
	
}
