import java.util.*;

public class RamblersState extends SearchState {
	/**
	 * State in a map search 2013 version
	 */

	// coords for this state
	private Coords coord;

	// constructor
	public RamblersState(Coords c1, int lc, int rc) {
		coord = c1;
		localCost = lc;
		estRemCost = rc;
	}

	// accessor
	public Coords getCoords() {
		return coord;
	}

	// goalP
	public boolean goalP(Search searcher) {
		RamblersSearch msearcher = (RamblersSearch) searcher;
		Coords tar = msearcher.getGoal(); // get target coords
		return (coord.equals(tar));
	}

	// getSuccessors
	public ArrayList<SearchState> getSuccessors(Search searcher) {
		RamblersSearch msearcher = (RamblersSearch) searcher;
		Carta map = msearcher.getMap();
		ArrayList<MapLink> links = map.getLinks(coord);
		ArrayList<SearchState> succs = new ArrayList<SearchState>();

		for (MapLink l : links) {
			Coords scoord;
			if (coord.equals(l.getStartCoords())) {
				scoord = l.getEndCoords();
			} else {
				scoord = l.getStartCoords();
			}
			;
			succs.add(new RamblersState(scoord, l.getCost(), map.estbetween(scoord, msearcher.getGoal(), "H")));
		}
		return succs;
	}

	// sameState

	public boolean sameState(SearchState s2) {
		RamblersState ms2 = (RamblersState) s2;
		return (coord.equals(ms2.getCoords()));
	}

	// toString
	public String toString() {
		return ("Map State: " + coord);
	}

}
