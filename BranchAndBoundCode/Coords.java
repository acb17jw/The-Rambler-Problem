/**
 * Coords.java
 *
 * @author <a href="mailto: "Phil Green</a> 2018 version a coordinate pair (y,x)
 *         for use in Ramblers problems to plot solution paths on TerrainMaps
 */

public class Coords {
	private int x;
	private int y;

	/**
	 * constructor, given y & x
	 */

	public Coords(int yin, int xin) {
		x = xin;
		y = yin;
	}

	/**
	 * equals compares objects
	 * overriden from Object
	 */
	public boolean equals(Object obj) {
		// check if identical objects
		if (this == obj) {
			return true;
		}
		// check if parametr is null
		if (obj == null) {
			return false;
		}
		// checks if objects have different classes
		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Coords i = (Coords) obj;

		return i.getx() == this.getx() && i.gety() == this.gety();
	}

	// accessors
	public int getx() {
		return x;
	};

	public int gety() {
		return y;
	};

}
