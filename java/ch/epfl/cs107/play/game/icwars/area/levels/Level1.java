package ch.epfl.cs107.play.game.icwars.area.levels;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.icwars.actor.other.City;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.utils.SoundsManager;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends ICWarsArea{
	public Level1(SoundsManager soundsManager) {
		super(soundsManager);
	}
	
	/*
	 * OVERRIDE METHODS
	 */
	
	@Override
	public String getTitle() {
		return "icwars/Map2";
	}

	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return new DiscreteCoordinates(2, 5);
	}
		
	@Override
	public DiscreteCoordinates getEnemySpawnPosition() {
		return new DiscreteCoordinates(17, 5);
	}

	@Override
	public List<DiscreteCoordinates> getCityPosition() {
		List<DiscreteCoordinates> liste = new ArrayList<>();
		for(City city : getCities()) {
			liste.add(city.getPos());
		}
		return liste;
	}	
}