package ch.epfl.cs107.play.game.icwars.actor.other;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Acoustics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.handler.ICWarInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Missile extends MovableAreaEntity implements Acoustics {
	private final int SPEED = 2;

	private Sprite[] sprites = new Sprite[4];
	private DiscreteCoordinates initCoords;
	private int damage;

	public Missile(Area area, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates attaquedUnit,
			int damage) {
		super(area, orientation, position);

		for (int i = 0; i < 4; ++i) {
			sprites[i] = new RPGSprite("icwars/missile", 1, 1, this, new RegionOfInterest(i * 32, 0, 32, 32));
			sprites[i].setDepth(0f);
		}

		((ICWarsArea) getOwnerArea()).playSound("explosion", false);

		this.initCoords = position;
		this.damage = damage;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((ICWarInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void update(float deltaTime) {
		move(SPEED);
		super.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		sprites[getOrientation().ordinal()].draw(canvas);
	}

	/*
	 * GETTERS AND SETTERS
	 */

	 /**
     * Getter of the initial position of the missile
     * @return (DiscreteCoordinates): the initial position
     */
	public DiscreteCoordinates getInitialPosition() {
		return initCoords;
	}
	
	 /**
     * Getter for the damage of the missile
     * @return (int): the damage of the missile
     */
	public int getDamage() {
		return damage;
	}

	/*
	 * USEFUL METHODS
	 */
	
	 /**
     * Method called when the missile explode
     */
	public void explose(DiscreteCoordinates pos) {
		// Remove missile from area
		getOwnerArea().unregisterActor(this);
		// Add a boom actor, for animation of explosion
		((ICWarsArea) getOwnerArea()).registerActor(new Boom(getOwnerArea(), Orientation.DOWN, pos));
	}
}
