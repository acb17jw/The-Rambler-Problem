
/**
 * TestGoalP.java
 * 
 * Tests GoalP method in RamblersState.java 
 */

import org.junit.*;
import static org.junit.Assert.*;

public class TestGoalP {

	Carta test1;
	Coords goal;
	Coords coord;
	RamblersSearch rSearch;
	RamblersState rState;

	@Before
	public void setup() {
		test1= new Carta();
		goal = new Coords(1, 2);
		
	}

	@Test
	public void GoalTest() {
		coord = new Coords(1,2);
		
		rState = new RamblersState(coord, 0);
		rSearch = new RamblersSearch(test1, goal);
		
		Boolean result = rState.goalP(rSearch);

		assertEquals(true, result); //returns true if state is a goal state
	}

	@Test
	public void NotGoalTest() {
		
		coord = new Coords(2,1);
		
		rState = new RamblersState(coord, 0);
		rSearch = new RamblersSearch(test1, goal);
		Boolean result = rState.goalP(rSearch); // returns false if state is not a goal state

		assertEquals(false, result);
	}

}
