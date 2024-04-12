package ch.epfl.cs107.play.game.icwars.area.levels;

import java.util.List;

import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.utils.SoundsManager;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends ICWarsArea {
	public Level0(SoundsManager soundsManager) {
		super(soundsManager);
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public String getTitle() {
		return "icwars/Map1";
	}

	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return new DiscreteCoordinates(3, 5);
	}

	@Override
	public DiscreteCoordinates getEnemySpawnPosition() {
		return new DiscreteCoordinates(7, 5);
	}

	@Override
	public List<DiscreteCoordinates> getCityPosition() {
		return null;
	}
}