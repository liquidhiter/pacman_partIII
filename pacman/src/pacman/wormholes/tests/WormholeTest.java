package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholeTest {

	@Test
	void test() {
//		fail("Not yet implemented");
	 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
	 			true, false, true, true,
	 			true, true, false, true,
	 			false, true, true, true
	 	 	});
	    Square square1 = Square.of(mazeMap, 0, 2);
	    Square square2 = Square.of(mazeMap, 1, 1);
	    Square square3 = Square.of(mazeMap, 2, 1);
	    Square square4 = Square.of(mazeMap, 2, 3);
	    
		ArrivalPortal portal1 = new ArrivalPortal(square1);
		assertEquals(square1, portal1.getSquare());
		
		DeparturePortal  portal2 = new DeparturePortal(square2);
		assertEquals(square2, portal2.getSquare());
		
		Wormhole wormhole1 = new Wormhole(portal2, portal1);
		
		assertEquals(portal1, wormhole1.getArrivalPortal());
		assertEquals(portal2, wormhole1.getDeparturePortal());
		
		wormhole1.setArrivalPortal(portal1);
		assertEquals(portal1, wormhole1.getArrivalPortal());
		assertEquals(portal2, wormhole1.getDeparturePortal());
		
		ArrivalPortal portal3 = new ArrivalPortal(square3);
		wormhole1.setArrivalPortal(portal3);
		assertEquals(portal3, wormhole1.getArrivalPortal());
		assertEquals(portal2, wormhole1.getDeparturePortal());
		
		wormhole1.setDeparturePortal(portal2);
		assertEquals(portal3, wormhole1.getArrivalPortal());
		assertEquals(portal2, wormhole1.getDeparturePortal());
		
		DeparturePortal portal4 = new DeparturePortal(square4);
		wormhole1.setDeparturePortal(portal4);
		assertEquals(portal3, wormhole1.getArrivalPortal());
		assertEquals(portal4, wormhole1.getDeparturePortal());
		
	}

}
