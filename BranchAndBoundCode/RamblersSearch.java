
import java.util.*;

public class RamblersSearch extends Search {

	private Carta map; // map we're searching
	private Coords goal; // goal coords

	// accessors
	public Carta getMap() {
		return map;
	}

	public Coords getGoal() {
		return goal;
	}

	public RamblersSearch(Carta m, Coords g) {
		map = m;
		goal = g;
	}
}