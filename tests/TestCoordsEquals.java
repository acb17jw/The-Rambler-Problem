import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestCoordsEquals {

	Coords coord1 = new Coords(1, 1);
	Coords coord2;
	Object object1;
	

	@Before
	public void setup() {
		
	}
	
	//returns true if objects references are the same
	@Test
	public void testSameReferences() {
		coord2=coord1;
		boolean result = coord1.equals(coord2);
		assertEquals(true, result);
	}
	//returns false if object is null
	@Test
	public void testNull() {
		coord2 = null;
		boolean result = coord1.equals(coord2);
		assertEquals(false, result);
	}
	//returns false if object is not a Coords class
	@Test
	public void testSameClass() {
		boolean result = coord1.equals(object1);
		assertEquals(false, result);
	}
	//returns true if objects of the Coords class have the same instance variables
	@Test
	public void testInstanceVariables() {
		coord2 = new Coords(1, 1);
		boolean result = coord1.equals(coord2);
		assertEquals(true, result);
	}	

}
