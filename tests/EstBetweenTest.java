import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class EstBetweenTest {
	Carta test1;
	Coords coord;
	Coords goal;
	
	
	@Test
	//test if the method works correctly for "Manhattan Distance"
	public void ManhattanTest() {
		coord = new Coords(1,1);
		goal = new Coords(5,5);
		Carta test1 = new Carta();
		test1.mapFromFile("tmc.pgm");
		int result = test1.estbetween(coord, goal,"Manhattan");
		//check if estimated cost is counted correctly for given accuracy
		assertEquals(8, result); 
	}
	@Test
	//test if the method works correctly for "Euclidean Distance"
	public void EuclideanTest() {
		coord = new Coords(1,1);
		goal = new Coords(3,3);
		test1 = new Carta();
		test1.mapFromFile("tmc.pgm");
		int result = test1.estbetween(coord, goal,"Euclidean");
		//check if estimated cost is counted correctly for given accuracy
		assertEquals(2, result); 
	}
	@Test
	//test if the method works correctly for "Height difference"
	public void HeightTest() {
		
		test1 = new Carta();
		coord = new Coords(0,0);
		goal = new Coords(1,1);
		test1.mapFromFile("tmc.pgm"); 
		int result = test1.estbetween(coord, goal,"Height");
		//check if estimated cost is counted correctly for given accuracy
		assertEquals(21, result);
	}
	@Test
	//test if the method works correctly for:
	// Mixed Manhattan distance and Height difference
	
	public void CombinationtTest() {
		
		test1 = new Carta();
		coord = new Coords(0,0);
		goal = new Coords(1,1);
		test1.mapFromFile("tmc.pgm"); 
		int result = test1.estbetween(coord, goal,"Whatever");
		//check if estimated cost is counted correctly for given accuracy
		assertEquals(23, result);
	}
	

}
