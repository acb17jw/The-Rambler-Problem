
/**
 * MapLink.java
 *
 * A link in a map
 *
 * @author <a href="mailto: "Phil Green</a> 2013 version
 */

public class MapLink {
	private Coords startCoorods;
	private Coords endCoords;
	private int cost;

	/**
	 * MapLink
	 * 
	 * @param start coords 1
	 * @param end coords 2
	 * @param c cost
	 */
	public MapLink(Coords start, Coords end, int c) {
		startCoorods = start;
		endCoords = end;
		cost = c;
	}

	// accessors
	public Coords getStartCoords() {
		return startCoorods;
	}

	public Coords getEndCoords() {
		return endCoords;
	}

	public int getCost() {
		return cost;
	}

	public String toString() {
		return startCoorods + " <--> " + endCoords + " " + cost;
	}

}
