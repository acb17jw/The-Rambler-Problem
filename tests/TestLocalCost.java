import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestLocalCost {
	int height1;
	int height2;
	Carta test;
	
	
	@Test
	//test if method works when second parameter is bigger
	void testHeightBigger() {
		test = new Carta();
		height1=10;
		height2 = 5;
		int result = test.localCost(height1, height2);
		System.out.println(result);
		assertEquals(1, result); //returns true for result equals 1
	}
	
	@Test
	//test if the method works when second parameter is smaller
	void testHeightSmaller() { 
		test = new Carta();
		height1=5;
		height2 = 10;
		int result = test.localCost(height1, height2);
		System.out.println(result);
		assertEquals(6, result); //returns true for result equals 6
	}

}
