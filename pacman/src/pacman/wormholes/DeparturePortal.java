package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * Each instance of this class represents an ArricalPortal belongs to a wormhole in the entity-relationship of portals and wormholes
 * One arrivalportal can belong to multiple wormholes, while the wormhole can only have one arrivalportal
 * 
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(a -> a.getDeparturePortal().equals(this))
 * @invar | getSquare() != null
 */
public class DeparturePortal {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @representationObject
	 * @invar | wormholes != null // phase 1 representation invariant
	 * @invar | wormholes.stream().allMatch(a -> a != null) // phase 2 representation invariant
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
	 * 		  |		w.getDeparturePortalInternal().equals(this))
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
	
	/**
	 * Add the given wormhole to this arrivalPortal's set of wormholes
	 * @throws IllegalArgumentException | newWormhole == null
	 * @mutates | this
	 * @post After adding the newWormhole, the size of the set is increased by one
	 * 		 | getWormholesInternal().size() == old(getWormholesInternal()).size() + 1
	 * @post After adding the newWormhole, the set of wormholes equals to the old set plus with the newly added wormhole
	 * 	     | getWormholesInternal().stream().allMatch(w -> w == newWormhole ? getWormholesInternal().contains(w) :
	 * 		 | 													old(getWormholesInternal()).stream().allMatch(o -> o.equals(w)))
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
	 * @post After removing the newWormhole, the set of wormholes equals to the old set minus the newly added wormhole
	 * 		    | getWormholesInternal().stream().allMatch(w -> w == newWormhole ? !getWormholesInternal().contains(w) :
	 * 			|												old(getWormholesInternal()).stream().allMatch(o -> o.equals(w)))
	 */
	void removeWormhole(Wormhole newWormhole) {
		if (newWormhole == null) {
			throw new IllegalArgumentException("`wormhole` is null");
		}
		wormholes.remove(newWormhole);
	}
}
