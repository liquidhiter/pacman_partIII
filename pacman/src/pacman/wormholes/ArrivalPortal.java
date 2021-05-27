package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * Each instance of this class represents an ArricalPortal belongs to a wormhole in the entity-relationship of portals and wormholes
 * One arrivalportal can belong to multiple wormholes, while the wormhole can only have one arrivalportal
 * 
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(a -> a != null)
 * @invar | getSquare() != null
 */
public class ArrivalPortal {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @representationObject
	 * @invar | wormholes != null // phase 1 representation invariant
	 * @invar | wormholes.stream().allMatch(a -> a != null) // phase 2 representation invariant
	 * @peerObjects
	 */
	private Set<Wormhole> wormholes = new HashSet<>();
	
	/**
	 * returns the square binded to the arrivalportal
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 * Returns this arrivalportal's set of wormholes
	 * 
	 * @invar | getWormholesInternal().stream().allMatch(w ->
	 * 		  |		w.getArrivalPortalInternal().equals(this))
	 * 
	 * @post | result != null && result.stream().allMatch(w -> w != null)
	 * @peerObjects
	 */
	Set<Wormhole> getWormholesInternal() {
		return Set.copyOf(wormholes);
	}

	/**
	 * Return this arrival portal's set of wormholes
	 * 
	 * @creates | result
	 * @peerObjects
	 * @basic
	 * @post | result != null && result.stream().allMatch(w -> w != null)
	 */
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
	
	/**
	 * Initializes this object as representing an arrival portal
	 * @mutates | this
	 * @throws IllegalArgumentException | square == null
	 * @post | getSquare().equals(square)
	 * @post This arrivalportal has non associated wormholes intially 
	 * 		| getWormholes().isEmpty()
	 */
	public ArrivalPortal(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		this.square = square;
	}
	
	/**
	 * Add the given wormhole to this arrivalPortal's set of wormholes
	 * @throws IllegalArgumentException | newWormhole == null
	 * @mutates | this
	 * @post After adding the newWormhole, the size of the set is increased by one
	 * 		 | getWormholesInternal().size() == old(getWormholesInternal()).size() + 1
	 * 
	 * @post | getWormholesInternal().containsAll(old(getWormholesInternal()))
	 * @post | getWormholesInternal().contains(newWormhole)
	 * @post | getWormholesInternal().stream().allMatch(e -> old(getWormholesInternal()).contains(e) || e == newWormhole)
	 * 
	 */
	void addWormhole(Wormhole newWormhole) {
		if (newWormhole == null) {
			throw new IllegalArgumentException("`wormhole` is null");
		}
		wormholes.add(newWormhole);
	}
	
	/**
	 * Remove the given wormhole from the arrivalPortal's set of wormholes
	 * @throws IllegalArgumentException | newWormhole == null
	 * @mutates | this
	 * @post After removing the newWormhole, the size of the set is decreased by one
	 * 			| getWormholesInternal().size() == old(getWormholesInternal()).size() - 1
	 * 
	 * @post | old(getWormholesInternal()).stream().allMatch(e -> e == newWormhole || getWormholesInternal().contains(e))
	 * @post | old(getWormholesInternal()).containsAll(getWormholesInternal())
	 * @post | !getWormholesInternal().contains(newWormhole)
	 */
	void removeWormhole(Wormhole newWormhole) {
		if (newWormhole == null) {
			throw new IllegalArgumentException("`wormhole` is null");
		}
		wormholes.remove(newWormhole);
	}
}
