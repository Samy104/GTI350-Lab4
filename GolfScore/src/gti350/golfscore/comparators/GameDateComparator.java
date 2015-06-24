package gti350.golfscore.comparators;

import gti350.golfscore.domain.Game;

import java.util.Comparator;

/**
 * Used to sort games by newest to oldest.
 * 
 * @author Simon RG
 */
public class GameDateComparator implements Comparator<Game> {

	@Override
	public int compare(Game g0, Game g1) {
		if (g0.getDate().before(g1.getDate())) {
			return 1;
		}
		else if (g0.getDate().after(g1.getDate())) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
