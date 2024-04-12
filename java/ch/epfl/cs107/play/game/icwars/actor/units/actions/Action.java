package ch.epfl.cs107.play.game.icwars.actor.units.actions;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;

public abstract class Action implements Graphics {
	private ICWarsUnit unit;
	private Area area;

	public Action(ICWarsUnit unit, Area area) {
		this.area = area;
		this.unit = unit;
	}
	
	/*
	 * ABSTRACT METHODS
	 */

	/*
	 * Get the key of the action
	 */
	public abstract int getKey();

	/*
	 * Get the name of the action
	 */
	public abstract String getName();

	/*
	 * Do action for AI
	 */
	public abstract void doAutoAction(float dt, ICWarsPlayer player, Keyboard keyboard);

	/*
	 * Do action for real player
	 */
	public abstract void doAction(float dt, ICWarsPlayer player, Keyboard keyboard);
	
	/*
	 * GETTERS AND SETTERS
	 */
	
	/*
	 * Get the area
	 */
	protected Area getArea() {
		return area;
	}
	
	/*
	 * Get unit of the action
	 */
	protected ICWarsUnit getUnit() {
		return unit;
	}
	
	/*
	 * USEFUL METHODS
	 */
	
	/*
	 * Check if the unit has the same x or same y of the coords
	 */

	protected boolean sameLine(DiscreteCoordinates coords) {
		if (unit.getPos().x == coords.x) {
			return true;
		}
		if (unit.getPos().y == coords.y) {
			return true;
		}
		return false;
	}

}