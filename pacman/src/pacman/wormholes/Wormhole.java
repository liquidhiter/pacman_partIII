package pacman.wormholes;

/**
 * Each instance of this class represents a wormhole consists of a departurePortal and an arrivalPortal
 * 
 * @invar | getArrivalPortal() != null
 * @invar | getArrivalPortal().getWormholes().contains(this)
 * @invar | getDeparturePortal() != null
 * @invar | getDeparturePortal().getWormholes().contains(this)
 */
public class Wormhole {

	/**
	 * @peerObject
	 * @invar | departurePortal != null
	 * @invar | departurePortal.wormholes.contains(this)
	 */
	private DeparturePortal departurePortal;
	
	/**
	 * @peerObject
	 * @invar | arrivalPortal != null
	 * @invar | arrivalPortal.wormholes.contains(this)
	 */
	private ArrivalPortal arrivalPortal;
	
//	private Set<Wormhole> wormholes = new HashSet<>();
	
//	public Set<Wormhole> getWormholes() {
//		return Set.copyOf(wormholes);
//	}
	
	/**
	 * Returns the wormhole's departureportal 
	 * @basic
	 * @peerObject
	 */
	public DeparturePortal getDeparturePortal() {
		return this.departurePortal;
	}
	
	
	/**
	 * Returns the wormhole's arrivalportal
	 * @basic
	 * @peerObject
	 */
	public ArrivalPortal getArrivalPortal() {
		return this.arrivalPortal;
	}
	
	
	/**
	 * Initializes this object as representing a wormhole associated with a departuralportal and an arrivalportal
	 * 
	 * @mutates | this
	 * @throws IllegalArgumentException | departurePortal == null
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * 
	 * @post | getDeparturePortal() == departurePortal
	 * @post | getArrivalPortal() == arrivalPortal
	 * @post | arrivalPortal.getWormholes().contains(this)
	 * @post | departurePortal.getWormholes().contains(this)
	 */
	public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
		if (departurePortal == null) {
			throw new IllegalArgumentException("`DeparturePortal` is null");
		}
		if (arrivalPortal == null) {
			throw new IllegalArgumentException("`ArrivalPortal` is null");
		}
		this.arrivalPortal = arrivalPortal;
		this.departurePortal = departurePortal;
		arrivalPortal.wormholes.add(this);
		departurePortal.wormholes.add(this);
		
	}

	/**
	 * Link new {@code ArrivalPortal} to the wormhole
	 * 
	 * @throws IllegalArgumentException | newArrivalPortal == null
	 * 
	 * @mutates_properties | this.getArrivalPortal(), this.getArrivalPortal().getWormholes()
	 * 
	 * @post If the new arrival portal equals to the current one then its associated wormholes doesn't change
	 * 		 otherwise, the associated wormhole of the old arrivalportal should be removed and the new arrivalportal should be associated
	 * 
	 * 		 | old(getArrivalPortal()) == newArrivalPortal ? old(arrivalPortal.getWormholes()).contains(this) : 
	 * 		 |												 (!old(getArrivalPortal()).getWormholes().contains(this) && getArrivalPortal().getWormholes().contains(this))
	 * 
	 * @post If the new arrival portal equals to the current one then the field of arrivalportal is assumed to not change
	 * 		 otherwise, the arrivalportal is changed to the new one
	 * 
	 * 		 | old(getArrivalPortal()) == newArrivalPortal ? getArrivalPortal() == old(getArrivalPortal()) : (getArrivalPortal() == newArrivalPortal)	 										
	 */
	public void setArrivalPortal(ArrivalPortal newArrivalPortal) {
		if (newArrivalPortal == null) {
			throw new IllegalArgumentException("`ArrivalPortal` is null");
		}
//		if (arrivalPortal != null) {
//			throw new IllegalArgumentException("there is already one arrivalportal");
//		}
		if (newArrivalPortal == this.arrivalPortal) {
			return;
		}
		arrivalPortal.wormholes.remove(this);
		this.arrivalPortal = newArrivalPortal;
		arrivalPortal.wormholes.add(this);
	}
	
	/**
	 * Link new {@code DeparturePortal} to the wormhole
	 * 
	 * @throws IllegalArgumentException | newDeparturePortal == null
	 * 
	 * @mutates_properties | this.getDeparturePortal(), this.getDeparturePortal().getWormholes()
	 * 
	 * @post If the new departure portal equals to the current one then its associated wormholes doesn't change
	 * 		 otherwise, the associated wormhole of the old arrivalportal should be removed and the new departur eportal should be associated
	 * 
	 * 		 | old(getDeparturePortal()) == newDeparturePortal ? old(departurePortal.getWormholes()).contains(this) : 
	 * 		 |												 (!old(getDeparturePortal()).getWormholes().contains(this) && getDeparturePortal().getWormholes().contains(this))
	 * 
	 * @post If the new departure portal equals to the current one then the field of departure portal is assumed to not change
	 * 		 otherwise, the departureportal is changed to the new one
	 * 
	 * 		 | old(getDeparturePortal()) == newDeparturePortal ? getDeparturePortal() == old(getDeparturePortal()) : (getDeparturePortal() == newDeparturePortal)	 	
	 */
	public void setDeparturePortal(DeparturePortal newDeparturePortal) {
		if (newDeparturePortal == null) {
			throw new IllegalArgumentException("`DeparturePortal` is null");
		}
//		if (departurePortal != null) {
//			throw new IllegalArgumentException("there is already one departurePortal");
//		}
		if (newDeparturePortal == this.departurePortal) {
			return;
		}
		departurePortal.wormholes.remove(this);
		this.departurePortal = newDeparturePortal;
		departurePortal.wormholes.add(this);
		
	}	
}
