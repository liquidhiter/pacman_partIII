package pacman.wormholes;

public class Wormhole {

	private DeparturePortal departurePortal;
	
	private ArrivalPortal arrivalPortal;
	
//	private Set<Wormhole> wormholes = new HashSet<>();
	
//	public Set<Wormhole> getWormholes() {
//		return Set.copyOf(wormholes);
//	}
	
	public DeparturePortal getDeparturePortal() {
		return this.departurePortal;
	}
	
	public ArrivalPortal getArrivalPortal() {
		return this.arrivalPortal;
	}
	
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

	public void setArrivalPortal(ArrivalPortal newArrivalPortal) {
		if (newArrivalPortal == null) {
			throw new IllegalArgumentException("`ArrivalPortal` is null");
		}
//		if (arrivalPortal != null) {
//			throw new IllegalArgumentException("there is already one arrivalportal");
//		}
//		if (newArrivalPortal == this.arrivalPortal) {
//			return;
//		}
		arrivalPortal.wormholes.remove(this);
		this.arrivalPortal = newArrivalPortal;
		arrivalPortal.wormholes.add(this);
	}
	
	public void setDeparturePortal(DeparturePortal newDeparturePortal) {
		if (newDeparturePortal == null) {
			throw new IllegalArgumentException("`DeparturePortal` is null");
		}
//		if (departurePortal != null) {
//			throw new IllegalArgumentException("there is already one departurePortal");
//		}
//		if (newDeparturePortal == this.departurePortal) {
//			return;
//		}
		departurePortal.wormholes.remove(this);
		this.departurePortal = newDeparturePortal;
		departurePortal.wormholes.add(this);
		
	}	
}
