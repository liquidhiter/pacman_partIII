package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

public class ArrivalPortal {
	
	private Square square;
	
	Set<Wormhole> wormholes = new HashSet<>();
	
	public Square getSquare() {
		return this.square;
	}

	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
	
	public ArrivalPortal(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		this.square = square;
	}
}
