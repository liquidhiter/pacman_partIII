package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class ArrivalPortalTest {

	@Test
	void test() {

	 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
	 			true, false, true, true,
	 			true, true, false, true,
	 			false, true, true, true
	 	 	});
	    Square square1 = Square.of(mazeMap, 0, 2);
	    Square square2 = Square.of(mazeMap, 1, 1);
	    
		ArrivalPortal portal1 = new ArrivalPortal(square1);
		assertEquals(square1, portal1.getSquare());
		
		DeparturePortal  portal2 = new DeparturePortal(square2);
		assertEquals(square2, portal2.getSquare());
		
		Wormhole wormhole1 = new Wormhole(portal2, portal1);
		
		assertEquals(Set.of(wormhole1), portal1.getWormholes());
		
	}

}
