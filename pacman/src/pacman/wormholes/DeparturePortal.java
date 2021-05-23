package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

public class DeparturePortal {
	
	private Square square;
	
	Set<Wormhole> wormholes = new HashSet<>();
	
	public Square getSquare() {
		return this.square;
	}
	
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
	
	public DeparturePortal(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		this.square = square;
	}
	
}
