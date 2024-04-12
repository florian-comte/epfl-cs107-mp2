package ch.epfl.cs107.play.game.icwars.area;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icwars.ICWars;
import ch.epfl.cs107.play.game.icwars.actor.other.City;
import ch.epfl.cs107.play.game.icwars.actor.other.Meteorite;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.game.icwars.utils.SoundsManager;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class ICWarsArea extends Area {
	private List<ICWarsUnit> units;
	private ICWarsBehavior behavior;
	private List<City> cities;
	private SoundsManager soundsManager;
	private final int MAX_METEORITES = 10;
	private int meteorites = 0;

	public ICWarsArea(SoundsManager soundsManager) {
		this.soundsManager = soundsManager;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public final float getCameraScaleFactor() {
		return ICWars.CAMERA_SCALE_FACTOR;
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			units = new ArrayList<>();
			// Set the behavior map
			behavior = new ICWarsBehavior(window, getTitle());
			setBehavior(behavior);
			createArea();
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (MAX_METEORITES > meteorites) {
			if (ThreadLocalRandom.current().nextInt(0, 60 * 24) == 0) {
				launchMeteorite();
			}
		}
	}

	/*
	 * ABSTRACT METHODS
	 */
	
	// Get player spawn position
	public abstract DiscreteCoordinates getPlayerSpawnPosition();

	// Get enemy spawn position
	public abstract DiscreteCoordinates getEnemySpawnPosition();

	// Get positions of the cities
	public abstract List<DiscreteCoordinates> getCityPosition();

	/*
	 * GETTERS AND SETTERS
	 */
	
	// Get the city by his coordinates
	private City getCity(DiscreteCoordinates position) {
		if (cities != null) {
			for (City city : cities) {
				if (city.getPosition().x == position.x && city.getPosition().y == position.y) {
					return city;
				}
			}
		}
		return null;
	}
	
	// Get all cities
	protected List<City> getCities(){
		return cities;
	}

	// Get all positions of the units
	public List<DiscreteCoordinates> getUnitsPositions() {
		List<DiscreteCoordinates> result = new ArrayList<DiscreteCoordinates>();
		for (ICWarsUnit unit : units) {
			result.add(unit.getPos());
		}
		return result;
	}
	
	// Get the nearest enemy unit
	public DiscreteCoordinates getNearestEnemyUnit(ICWarsUnit currentUnit) {
		DiscreteCoordinates result = currentUnit.getPos();
		float shortestDistance = -1;
		for (ICWarsUnit units : units) {
			float distance = DiscreteCoordinates.distanceBetween(units.getPos(), currentUnit.getPos());
			if ((shortestDistance == -1 || shortestDistance > distance)
					&& (units.getFaction() != currentUnit.getFaction())) {
				result = units.getPos();
				shortestDistance = distance;
			}
		}
		return result;
	}

	/*
	 * USEFUL METHODS
	 */

	/**
	 * Create the area by adding it all actors called by begin method Note it set
	 * the Behavior as needed !
	 */
	private void createArea() {
		registerActor(new Background(this));
		cities = new ArrayList<>();
		behavior.registerActors(this);
	}
	
	// Add a city to the list
	protected void addCity(City city) {
		cities.add(city);
	}
	
	// Set a city to a faction
	public void replaceCity(DiscreteCoordinates pos, Faction faction) {
		City city = getCity(pos);
		if (city != null) {
			if (cities.contains(city)) {
				int i = cities.indexOf(city);
				cities.get(i).setFaction(faction);
			}
		}
	}
	
	// Check if units are on cities and heal them
	public void checkUnitsCities() {
		List<ICWarsUnit> addHp = new ArrayList<ICWarsUnit>();
		for (ICWarsUnit unit : units) {
			for (City cities : cities) {
				if (unit.getPos().equals(cities.getPos()) && unit.getFaction() == cities.getFaction()) {
					addHp.add(unit);
				}
			}
		}

		for (ICWarsUnit unit : addHp) {
			unit.addHp(1);
		}
	}
	
	// Check if unit is attaquable
	public boolean isAttaquable(int index, ICWarsUnit unit, int radius) {
		return DiscreteCoordinates.distanceBetween(getUnitsPositions().get(index), unit.getPos()) <= radius
				&& units.get(index).getFaction() != unit.getFaction();
	}

	// Register unit to area
	public void registerUnit(ICWarsUnit unit) {
		units.add(unit);
		registerActor(unit);
	}

	// Unregister unit to area
	public void unregisterUnit(ICWarsUnit unit) {
		if (units.contains(unit)) {
			units.remove(unit);
		}
		unregisterActor(unit);
	}
	
	// Attack an unit by his index
	public void attackUnit(Integer index, int damage) {
		ICWarsUnit attaquedUnit = units.get(index);

		int defence = attaquedUnit.getCellDefense();

		if (attaquedUnit.getHp() - damage + defence < attaquedUnit.getHp()) {
			attaquedUnit.removeHp(damage - defence);
		}
	}
	// Attack an unit 
	public void attackUnit(ICWarsUnit unit, int damage) {
		for (int i = 0; i < units.size(); i++) {
			if (units.get(i) == unit) {
				attackUnit(i, damage);
			}
		}
	}

	// Center the camera 
	public void centerCamera(int index) {
		units.get(index).centerCamera();
	}

	public void playSound(String sound, boolean repeat) {
		soundsManager.play(sound, repeat);
	}

	private DiscreteCoordinates getRandomPosition() {
		return new DiscreteCoordinates(ThreadLocalRandom.current().nextInt(0, (this.getWidth())), getHeight() - 1);
	}

	public void launchMeteorite() {
		Meteorite meteorite = new Meteorite(this, Orientation.DOWN, getRandomPosition());
		registerActor(meteorite);
		meteorites++;
	}

	public void danseUnits() {
		for (ICWarsUnit unit : units) {
			unit.dance();
		}
	}
}