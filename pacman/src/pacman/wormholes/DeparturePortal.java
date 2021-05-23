package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * Each instance of this class represents an ArricalPortal belongs to a wormhole in the entity-relationship of portals and wormholes
 * One arrivalportal can belong to multiple wormholes, while the wormhole can only have one arrivalportal
 * 
 * @invar | getWormholes() == null || getWormholes().stream().allMatch(a -> a.getDeparturePortal() == this)
 * @invar | getSquare() != null
 */
public class DeparturePortal {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @representationObject
	 * @peerObjects
	 * @invar | wormholes != null // phase 1 representation invariant
	 * @invar | wormholes.stream().allMatch(a -> a != null && a.getDeparturePortal() == this) // phase 2 representation invariant
	 */
	Set<Wormhole> wormholes = new HashSet<>();
	
	
	/**
	 * returns the square binded to the arrivalportal
	 */
	public Square getSquare() {
		return this.square;
	}
	
	
	/**
	 * Return this arrival portal's set of wormholes
	 * 
	 * @creates | result
	 * @peerObjects
	 * @basic
	 * @post | result != null
	 */
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
	
	/**
	 * Initializes this object as representing an arrival portal
	 * @mutates | this
	 * @throws IllegalArgumentException | square == null
	 * @post | getSquare() == square
	 * @post This arrivalportal has non associated wormholes intially 
	 * 		| getWormholes().isEmpty()
	 */
	public DeparturePortal(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		this.square = square;
	}
	
}
