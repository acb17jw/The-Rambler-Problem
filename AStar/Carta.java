
/**
 * Carta.java
 *
 * a map for searching
 * called Carta to avoid confusion with Interface map
 * Created: Mon Dec  4 16:45:12 2000
 *
 * @author <a href="mailto: "Phil Green</a>
 * 2013 version
 * allow estimates for A*
 * in same way as links, can have estimates of remaining cost between any 2 cities
 */

import sheffield.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Carta {
	private ArrayList<MapLink> links; // all the links
	private ArrayList<MapLink> ests; // all the estimates
	private HashSet coords; // all the coords

	// accessors
	public ArrayList<MapLink> getAllLinks() {
		return links;
	}

	public ArrayList<MapLink> getAllEsts() {
		return ests;
	}

	public HashSet getCoords() {
		return coords;
	}

	TerrainMap tm;
	// constructor - empty map

	public Carta() {
		links = new ArrayList<MapLink>();
		ests = new ArrayList<MapLink>();
	}

	/**
	 * addLink adds a link to a map
	 * 
	 * @param start coords 1
	 * @param end coords 2
	 * @param c cost of the link
	 */
	public void addLink(Coords start, Coords end, int c) {
		MapLink ml = new MapLink(start, end, c);
		links.add(ml);
	}

	public void add_Est(Coords start, Coords end, int c) {
		MapLink ml = new MapLink(start, end, c);
		ests.add(ml);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("MAP WITH LINKS\n");
		for (MapLink lnk : links) {
			String lstr = lnk.toString();
			buf.append(lstr + "\n");
		}
		return buf.toString();
	}

	/**
	 * getLinks returns all links to/from a given coords
	 * 
	 * @param coord the coords
	 * @return ArrayList of links
	 */
	public ArrayList<MapLink> getLinks(Coords coord) {
		ArrayList<MapLink> clinks = new ArrayList<MapLink>();
		for (MapLink l : links) {
			if ((coord.equals(l.getStartCoords())) || (coord.equals(l.getEndCoords())))
				clinks.add(l);
		}

		return clinks;
	}

	/**
	 * localCost count cost between two neighboring coords
	 * 
	 * @param hstart height of the first coords
	 * @param hend height of the second coords
	 */

	public int localCost(int hstart, int hend) {
		if (hend <= hstart) {
			return 1;
		} else {
			return 1 + Math.abs(hend - hstart);
		}
	}

	/**
	 * costbetween returns cost between 2 coords
	 * 
	 * @param start coords 1
	 * @param end coords 2
	 */

	public int costBetween(Coords start, Coords end) {
		int[][] map = tm.getTmap();
		return localCost(map[start.gety()][start.getx()], map[end.gety()][end.getx()]);
	}

	/**
	 * estbetween returns est cost between 2 coords
	 * 
	 * @param coord coords 1
	 * @param goal final coords
	 */

	public int estbetween(Coords coord, Coords goal, String estimation) {
		int ans = 0;
		int[][] tmap = tm.getTmap();
		int p = Math.abs(coord.getx() - goal.getx());
		int q = Math.abs(coord.gety() - goal.gety());
		int hstart = tmap[coord.gety()][coord.getx()];
		int hend = tmap[goal.gety()][goal.getx()];

		if (estimation == "Manhattan") { // Manhattan distance

			ans = p + q;
		} else if (estimation == "Euclidean") { // Euclidean distance

			ans = (int) Math.sqrt(p * p + q * q);
		} else if (estimation == "Height") { // Height difference
			if (hend <= hstart) {
				ans = 1;
			} else {
				ans = 1 + Math.abs(hend - hstart);
			}
		} else { // Mixed Manhattan distance and Height difference
 
			int hCost;

			if (hend <= hstart) {
				hCost = 1;
			} else {
				hCost = 1 + Math.abs(hend - hstart);
			}
			ans = p + q + hCost;

		}

		return ans;
	}

	/**
	 * mapFromFile reads a map from file
	 * 
	 * @param fname - the file name
	 */

	public void mapFromFile(String fname) {

		tm = new TerrainMap(fname);

		int tmap[][] = tm.getTmap();
		int width = tm.getWidth();
		int depht = tm.getDepth();
		int height = tm.getHeight();

		// links coords horizontal
		for (int i = 0; i < depht; i++) {
			for (int j = 0; j < width - 1; j++) {
				links.add(new MapLink(new Coords(i, j), new Coords(i, j + 1), localCost(tmap[i][j], tmap[i][j + 1])));

				links.add(new MapLink(new Coords(i, j + 1), new Coords(i, j), localCost(tmap[i][j + 1], tmap[i][j])));
			}
		}
		// links coords vertical

		for (int j = 0; j < width; j++) {
			for (int i = 0; i < depht - 1; i++) {
				links.add(new MapLink(new Coords(i, j), new Coords(i + 1, j), localCost(tmap[i][j], tmap[i + 1][j])));
				links.add(new MapLink(new Coords(i + 1, j), new Coords(i, j), localCost(tmap[i + 1][j], tmap[i][j])));
			}
		}
		findcities();
	}
	// find all cities on the map

	private void findcities() {
		coords = new HashSet();
		int width = tm.getWidth();
		int depht = tm.getDepth();

		for (int i = 0; i < depht; i++) {
			for (int j = 0; j < width; j++) {
				coords.add(new Coords(i, j));
			}

		}
	}

}
