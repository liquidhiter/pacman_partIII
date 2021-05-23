package pacman;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	
//	private DeparturePortal[] departurePortals;
//	private ArrivalPortal[] arrivalPortals;
//	
//	private Wormhole[] wormholes;
	
	private List<DeparturePortal> departurePortals = new ArrayList<>();
	private List<ArrivalPortal> arrivalPortals = new ArrayList<>();
	private List<Wormhole> wormholes = new ArrayList<>();
	
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public DeparturePortal[] getDeparturePortals() {
		return (DeparturePortal[]) departurePortals.toArray(new DeparturePortal[departurePortals.size()]);
	}
	
	public ArrivalPortal[] getArrivalPortals() {
		return (ArrivalPortal[]) arrivalPortals.toArray(new ArrivalPortal[arrivalPortals.size()]);
	}
	
	public Wormhole[] getWormholes() {
		return (Wormhole[]) wormholes.toArray(new Wormhole[wormholes.size()]);
	}
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, 
			DeparturePortal[] departurePortals, ArrivalPortal[] arrivalPortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.arrivalPortals = Arrays.asList(arrivalPortals.clone());
		this.departurePortals = Arrays.asList(departurePortals.clone());
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			if (checkDeparturePortal(newSquare) != null &&  checkDeparturePortal(newSquare).length != 0) {
				int index = random.nextInt(checkDeparturePortal(newSquare).length);
				newSquare = checkDeparturePortal(newSquare)[index].getArrivalPortal().getSquare();
				checkPacManDamage();
			}
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
		}
	}
	
	public Wormhole[] checkDeparturePortal(Square square) {
		for (DeparturePortal element : departurePortals) {
			if (square.equals(element.getSquare())) {
				return (Wormhole[]) element.getWormholes().toArray(new Wormhole[element.getWormholes().size()]);
			}
		}
		return null;
	}
	
	// so there are different phases for pre conditions
	public void addWormhole(Wormhole newWormhole) {
		if (!departurePortals.contains(newWormhole.getDeparturePortal())) {
			throw new IllegalArgumentException("the departureportal is not consistent with that of wormhole");
		}
		if (!arrivalPortals.contains(newWormhole.getArrivalPortal())) {
			throw new IllegalArgumentException("the arrivalportal is not consistent with that of wormhole");
		}
		
		wormholes.add(newWormhole);
	}
	
}
