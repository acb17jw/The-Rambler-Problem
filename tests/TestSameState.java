
/**
 * TestSameState.java
 * 
 * Tests sameState method 
 */

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestSameState {
	
	Coords coord;
	
	@Before
	public void setup() {
		coord = new Coords(2,1);
	}
	
	@Test
	//returns true if states are the same
	public void sameStateTest() {

		Coords coord2 = new Coords(2,1);

		RamblersState rState1 = new RamblersState(coord, 0);
		RamblersState rState2 = new RamblersState(coord2, 0);

		Boolean result = rState1.sameState(rState2);

		assertEquals(true, result);
	}

	@Test
	//returns false if states are different
	public void notSameStateTest() {

		
		Coords coord2= new Coords(1, 2);

		RamblersState rState1 = new RamblersState(coord, 0);
		RamblersState rState2 = new RamblersState(coord2, 0);

		Boolean result = rState1.sameState(rState2);

		assertEquals(false, result);
	}
}
