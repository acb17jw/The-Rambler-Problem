
/**
 * Carta.java
 *
 * a map for searching
 * called Carta to avoid confusion with Interface map
 *
 *
 * @author <a href="mailto: "Phil Green</a>
 * 2013 version
 */

import sheffield.*;
import java.util.*;

public class Carta {
	private ArrayList<RamblersLink> links; // all the links
	private HashSet coords; // all the coords

	// accessors
	public ArrayList<RamblersLink> getAllLinks() {
		return links;
	}

	public HashSet getcoords() {
		return coords;
	}

	// constructor - empty map

	public Carta() {
		links = new ArrayList<RamblersLink>();
	}

	/**
	 * addLink adds a link to a map
	 * 
	 * @param start coords 1
	 * @param end coords 2
	 * @param c cost of the link
	 */
	public void addLink(Coords start, Coords end, int c) {
		RamblersLink ml = new RamblersLink(start, end, c);
		links.add(ml);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("MAP WITH LINKS\n");
		for (RamblersLink lnk : links) {
			String lstr = lnk.toString();
			buf.append(lstr + "\n");
		}
		return buf.toString();
	}

	/**
	 * getLinks returns all links to/from a given coord
	 * 
	 * @param coord - the coords
	 * @return ArrayList of links
	 */
	public ArrayList<RamblersLink> getLinks(Coords coord) {
		ArrayList<RamblersLink> clinks = new ArrayList<RamblersLink>();
		for (RamblersLink l : links) {
			if ((coord.equals(l.getStartCoords())) || (coord.equals(l.getEndCoords())))
				clinks.add(l);
		}

		return clinks;
	}

	/**
	 * costbetween returns cost between 2 coords
	 * 
	 * @param start coords 1
	 * @param end coords 2
	 */

	public int costbetween(Coords start, Coords end) {
		ArrayList<RamblersLink> startLinks = getLinks(start);
		int ans = -1;
		Iterator i = startLinks.iterator();
		while (i.hasNext() && (ans < 0)) {
			RamblersLink l = (RamblersLink) i.next();
			if (end.equals(l.getStartCoords()) || end.equals(l.getEndCoords())) {
				ans = l.getCost();
			}
		}
		return ans;
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
	 * mapFromFile reads a terrain map from file
	 * 
	 * @param fname - the file name
	 */
	public void mapFromFile(String fname) {

		TerrainMap tm = new TerrainMap(fname);

		int tmap[][] = tm.getTmap();
		int width = tm.getWidth();
		int depht = tm.getDepth();
		int height = tm.getHeight();

		// links coords horizontal
		for (int i = 0; i < depht; i++) {
			for (int j = 0; j < width - 1; j++) {
				links.add(new RamblersLink(new Coords(i, j), new Coords(i, j + 1),
						localCost(tmap[i][j], tmap[i][j + 1])));
				
				links.add(new RamblersLink(new Coords(i, j+1), new Coords(i, j),
						localCost(tmap[i][j+1], tmap[i][j])));
			}
		}
		// links coords vertical

		for (int j = 0; j < width; j++) {
			for (int i = 0; i < depht - 1; i++) {
				links.add(new RamblersLink(new Coords(i, j), new Coords(i + 1, j),
						localCost(tmap[i][j], tmap[i + 1][j])));
				links.add(new RamblersLink(new Coords(i+1, j), new Coords(i, j),
						localCost(tmap[i+1][j], tmap[i][j])));
			}
		}
		findcoords();
	}
	// find all coords on the map

	private void findcoords() {
		coords = new HashSet();
		for (RamblersLink l : links) {
			coords.add(l.getStartCoords());
			coords.add(l.getEndCoords());
		}
	}
}
