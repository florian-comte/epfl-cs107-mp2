package ch.epfl.cs107.play.game.icwars.actor.units.cat;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsAnimatedUnit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Cat extends ICWarsAnimatedUnit {
	public Cat(Area area, DiscreteCoordinates position, Faction faction, String name) {
		super(area, position, faction, name);
	}
	
	/*
	 * OVERRIDE METHODS
	 */
	
	@Override
	public void removeHp(int points) {
		super.removeHp(points);
		((ICWarsArea) getOwnerArea()).playSound("miaw", false);
	}

}
