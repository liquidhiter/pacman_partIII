package pacman.wormholes;

/**
 * Each instance of this class represents a wormhole consists of a departurePortal and an arrivalPortal
 * 
 * @invar | getDeparturePortal() != null
 * @invar | getArrivalPortal() != null
 * @invar | getDeparturePortal().getWormholes().contains(this)
 * @invar | getArrivalPortal().getWormholes().contains(this)
 */
public class Wormhole {

	private DeparturePortal departurePortal;
	
	private ArrivalPortal arrivalPortal;
	
	/**
	 * @invar | getDeparturePortalInternal() != null
	 * @invar | getDeparturePortalInternal().getWormholesInternal().contains(this)
	 * @peerObject
	 */
	DeparturePortal getDeparturePortalInternal() {
		return this.departurePortal;
	}
	
	/**
	 * Returns the wormhole's departureportal 
	 * @basic
	 * @peerObject
	 */
	public DeparturePortal getDeparturePortal() {
		return this.departurePortal;
	}
	
	/**
	 * @invar | getArrivalPortalInternal() != null
	 * @invar | getArrivalPortalInternal().getWormholesInternal().contains(this)
	 * @peerObject
	 */
	ArrivalPortal getArrivalPortalInternal() {
		return this.arrivalPortal;
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
		arrivalPortal.addWormhole(this);;
		departurePortal.addWormhole(this);;
		
	}

	/**
	 * Link new {@code ArrivalPortal} to the wormhole
	 * 
	 * @throws IllegalArgumentException | newArrivalPortal == null
	 * 
	 * @mutates_properties | this.getArrivalPortal(), newArrivalPortal.getWormholes()
	 * 
	 * @post If the new arrival portal equals to the current one then its associated wormholes doesn't change
	 * 		 otherwise, the associated wormhole of the old arrivalportal should be removed and the new arrivalportal should be associated
	 * 
	 * 		 | newArrivalPortal == old(getArrivalPortal()) ? getArrivalPortal().getWormholes().contains(this) : 
	 * 		 |		(!old(getArrivalPortal()).getWormholes().contains(this) && getArrivalPortal().getWormholes().contains(this))
	 * 
	 * @post If the new arrival portal equals to the current one then the field of arrivalportal is assumed to not change
	 * 		 otherwise, the arrivalportal is changed to the new one
	 * 
	 * 		 | newArrivalPortal == old(getArrivalPortal()) ? getArrivalPortal() == old(getArrivalPortal()) : (getArrivalPortal() == newArrivalPortal)	 										
	 */
	public void setArrivalPortal(ArrivalPortal newArrivalPortal) {
		if (newArrivalPortal == null) {
			throw new IllegalArgumentException("`ArrivalPortal` is null");
		}
		if (newArrivalPortal == this.arrivalPortal) {
			return;
		}
		arrivalPortal.removeWormhole(this);;
		this.arrivalPortal = newArrivalPortal;
		arrivalPortal.addWormhole(this);;
	}
	
	/**
	 * Link new {@code DeparturePortal} to the wormhole
	 * 
	 * @throws IllegalArgumentException | newDeparturePortal == null
	 * 
	 * @mutates_properties | this.getDeparturePortal(), newDeparturePortal.getWormholes()
	 * 
	 * @post If the new departure portal equals to the current one then its associated wormholes doesn't change
	 * 		 otherwise, the associated wormhole of the old arrivalportal should be removed and the new departur eportal should be associated
	 * 
	 * 		 | newDeparturePortal == old(getDeparturePortal()) ? getDeparturePortal().getWormholes().contains(this) : 
	 * 		 |		(!old(getDeparturePortal()).getWormholes().contains(this) && getDeparturePortal().getWormholes().contains(this))
	 * 
	 * @post If the new departure portal equals to the current one then the field of departure portal is assumed to not change
	 * 		 otherwise, the departureportal is changed to the new one
	 *
	 * 		 | newDeparturePortal == old(getDeparturePortal()) ? getDeparturePortal() == old(getDeparturePortal()) : (getDeparturePortal() == newDeparturePortal)	 	
	 */
	public void setDeparturePortal(DeparturePortal newDeparturePortal) {
		if (newDeparturePortal == null) {
			throw new IllegalArgumentException("`DeparturePortal` is null");
		}
		if (newDeparturePortal == this.departurePortal) {
			return;
		}
		departurePortal.removeWormhole(this);;
		this.departurePortal = newDeparturePortal;
		departurePortal.addWormhole(this);;
		
	}	
}
