package ch.epfl.cs107.play.game.icwars.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class ICWarsActor extends MovableAreaEntity {
	private Faction faction;

	public ICWarsActor(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, Orientation.UP, position);
		this.faction = faction;
	}
	
	/*
	 * OVERRIDE METHODS
	 */
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	/*
	 * GETTERS AND SETTER
	 */
		
	// Get the faction of the actor
	public Faction getFaction() {
		return faction;
	}
	
	// Set the faction of the actor
	protected void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	// Get the position of the actor
	public DiscreteCoordinates getPos() {
		return new DiscreteCoordinates((int) super.getPosition().getX(), (int) super.getPosition().getY());
	}
	
	/*
	 * USEFUL METHODS
	 */

	/**
	 * Leave an area by unregister this player
	 */
	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
		
	}

	/**
	 * @param area     (Area): initial area, not null
	 * @param position (DiscreteCoordinates): initial position, not null
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		setOwnerArea(area);
		setCurrentPosition(position.toVector());
	}
}