package gti350.golfscore.data;

import java.util.ArrayList;

import gti350.golfscore.domain.Player;

public class PlayerData {

	private static ArrayList<Player> players = new ArrayList<Player>();
	private static int nextId = 1;
	
	public static Player get(int id) {
		for (Player p: players) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Player> getAll(int gameId) {
		ArrayList<Player> list = new ArrayList<Player>();
		
		for (Player p: players) {
			if (p.getGameId() == gameId) {
				list.add(p);
			}
		}
		
		return list;
	}
	
	public static int add(String name, int gameId) {
		Player player = new Player(nextId, name, new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, gameId);
		players.add(player);
		nextId++;
		return player.getId();
	}
	
	public static void delete(int id) {
		int indexToDelete = -1;
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId() == id) {
				indexToDelete = i;
				break;
			}
		}
		
		if (indexToDelete != -1) {
			players.remove(indexToDelete);
		}
	}
	
	public static void update(Player player) {
		if (players.size() == 0) return;
		
		Player real = null;
		
		for (Player p: players) {
			if (p.getId() == player.getId()) {
				real = p;
				break;
			}
		}
		
		if (real != null) {
			real.setName(player.getName());
			real.setScore(1, player.getScore(1));
			real.setScore(2, player.getScore(2));
			real.setScore(3, player.getScore(3));
			real.setScore(4, player.getScore(4));
			real.setScore(5, player.getScore(5));
			real.setScore(6, player.getScore(6));
			real.setScore(7, player.getScore(7));
			real.setScore(8, player.getScore(8));
			real.setScore(9, player.getScore(9));
			real.setScore(10, player.getScore(10));
			real.setScore(11, player.getScore(11));
			real.setScore(12, player.getScore(12));
			real.setScore(13, player.getScore(13));
			real.setScore(14, player.getScore(14));
			real.setScore(15, player.getScore(15));
			real.setScore(16, player.getScore(16));
			real.setScore(17, player.getScore(17));
			real.setScore(18, player.getScore(18));
			real.setGameId(player.getGameId());
		}
	}
	
}
