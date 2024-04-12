package ch.epfl.cs107.play.game.icwars.actor.units;

import java.util.List;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.ICWarsActor;
import ch.epfl.cs107.play.game.icwars.actor.other.Missile;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCell;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.game.icwars.handler.ICWarInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public abstract class ICWarsUnit extends ICWarsActor implements Interactor {
	private String name;
	private int hp;
	private int cellDefense;

	private final ICWarsUnitInteractionHandler handler;

	private Queue<Orientation> path;
	private ICWarsRange range;
	private boolean available;

	private Sprite sprite;

	public ICWarsUnit(Area area, DiscreteCoordinates position, Faction faction, String name) {
		super(area, position, faction);
		this.name = name;

		this.available = true;
		this.handler = new ICWarsUnitInteractionHandler();
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		if (path != null && path.size() > 0) {
			Orientation nextOrientation;
			// If unit moving
			if (!isDisplacementOccurs()) {
				nextOrientation = getNextOrientation();

				if (nextOrientation == getOrientation()) {
					move(getSpeed());
				} else {
					orientate(nextOrientation);
					move(getSpeed());
				}
			}
		}
	}

	@Override
	public boolean takeCellSpace() {
		return true;
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
	public void acceptInteraction(AreaInteractionVisitor handler) {
		((ICWarInteractionVisitor) handler).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		if (isAvailable() || isDisplacementOccurs()) {
			getSprite().setAlpha(1f);
		} else {
			getSprite().setAlpha(0.5f);
		}
		getSprite().draw(canvas);
	}

	@Override
	public boolean changePosition(DiscreteCoordinates newPosition) {
		if (!super.changePosition(newPosition) || range.nodeExists(newPosition)) {
			setCurrentPosition(new Vector(newPosition.x, newPosition.y));
			return false;
		}
		return true;
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	@Override
	public void leaveArea() {
		((ICWarsArea) getOwnerArea()).unregisterUnit(this);
	}

	/*
	 * ABSTRACT METHODS
	 */
	
	// Get the radius of the unit
	public abstract int getRadius();

	// Get the damage of the unit
	public abstract int getDamage();

	// Get the max hp of the unit
	public abstract int getMaxHp();

	// Get the actions of the unit
	public abstract List<Action> getActions();

	// Get the speed of the unit
	public abstract int getSpeed();

	/*
	 * GETTERS AND SETTERS
	 */

	// Set sprite of the unit
	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
		sprite.setDepth(-10f);
	}

	// Get the sprite of the unit
	protected Sprite getSprite() {
		return sprite;
	}
	
	// Set the sprite parente of the unit
	public void setSpriteParent(Positionable parent) {
		sprite.setParent(parent);
	}
	
	// Get the defense of the cell 
	public int getCellDefense() {
		return cellDefense;
	}

	// Get the name of the unit
	public String getName() {
		return name;
	}

	// Get the hp of the unit
	public int getHp() {
		return hp;
	}

	// Check if unit is available
	public boolean isAvailable() {
		return available;
	}

	// Set the unit if available
	public void setAvailable(boolean available) {
		// If not available, little transparent
		if (available) {
			getSprite().setAlpha(1);
		} else {
			getSprite().setAlpha(0.5f);
		}
		this.available = available;
	}

	// Set hp of the player
	protected void setHp(int hp) {
		if(hp < 0) {
			return;
		}
		this.hp = hp;
	}

	// Set the orientation of the unit
	public void setOrientation(Orientation orientation) {
		orientate(orientation);
	}

	// Get next orientation of the unit
	private Orientation getNextOrientation() {
		return path.poll();
	}

	/*
	 * USEFUL METHODS
	 */
	
	// Check if a position is in the range of the unit
	public boolean isInRange(DiscreteCoordinates position) {
		return range.nodeExists(position);
	}
	
	// Add nodes to the range 
	public void setupRange(Area area, DiscreteCoordinates position) {
		range = new ICWarsRange();
		for (int x = -getRadius(); x <= getRadius(); x++) {
			if (!OOBx(x + position.x)) {
				for (int y = -getRadius(); y <= getRadius(); y++) {
					if (!OOBy(y + position.y)) {
						range.addNode(new DiscreteCoordinates(x + position.x, y + position.y),
								x > -getRadius() && position.x + x > 0,
								y < getRadius() && y + position.y < area.getHeight() - 1,
								x < getRadius() && x + position.x < area.getWidth() - 1,
								y > -getRadius() && y + position.y > 0);
					}
				}
			}
		}
	}
	
	// Check if the y coord is out of bound
	protected boolean OOBy(int y) {
		return y < 0 || y >= getOwnerArea().getHeight();
	}

	// Check if the x coord is out of bound
	protected boolean OOBx(int x) {
		return x < 0 || x >= getOwnerArea().getWidth();
	}

	// Check if the position is out of bound
	protected boolean OOB(DiscreteCoordinates position) {
		int x = position.x;
		int y = position.y;
		return (x < 0 || x >= getOwnerArea().getWidth()) && (y < 0 || y >= getOwnerArea().getHeight());
	}

	// Check if the unit is dead
	public boolean isDead() {
		return hp <= 0;
	}

	// Remove hp of unit and if he is dead, remove it from area
	public void removeHp(int points) {
		hp -= points;
		if (isDead()) {
			hp = 0;
			leaveArea();
		}
	}
	
	// Add hp to the unit
	public void addHp(int points) {
		if (hp + points > getMaxHp()) {
			hp = getMaxHp();
		} else {
			hp += points;
		}
	}

	/**
	 * Draw the unit's range and a path from the unit position to destination
	 * 
	 * @param destination path destination
	 * @param canvas      canvas
	 */
	public void drawRangeAndPathTo(DiscreteCoordinates destination, Canvas canvas) {
		range.draw(canvas);
		Queue<Orientation> path = range.shortestPath(getCurrentMainCellCoordinates(), destination);
		// Draw path only if it exists (destination inside the range)
		if (path != null) {
			new Path(getCurrentMainCellCoordinates().toVector(), path).draw(canvas);
		}
	}

	/**
	 * sets the path of the unit when moving
	 * @param area : the current area
	 * @param destination : new position of the unit
	 */
	public void setPath(Area area, DiscreteCoordinates destination) {
		ICWarsRange range = new ICWarsRange();
		range = setupMoveRange(area, destination, range);
		this.path = range.shortestPath(getCurrentMainCellCoordinates(), destination);
	}

	/**
	 * new range with no nodes in positions where there are units
	 * @param area : the current area
	 * @param position : the current position
	 * @param range : the original range
	 * @return the modified range
	 */
	private ICWarsRange setupMoveRange(Area area, DiscreteCoordinates position, ICWarsRange range) {
		for (int x = -getRadius(); x <= getRadius(); x++) {
			if (!OOBx(x + position.x)) {
				for (int y = -getRadius(); y <= getRadius(); y++) {
					if (!OOBy(y + position.y)) {
						range.addNode(new DiscreteCoordinates(x + position.x, y + position.y),
								x > -getRadius() && position.x + x > 0
										&& checkNeighbour(new DiscreteCoordinates(x + position.x - 1, y + position.y)),
								y < getRadius() && y + position.y < area.getHeight() - 1
										&& checkNeighbour(new DiscreteCoordinates(x + position.x, y + position.y + 1)),
								x < getRadius() && x + position.x < area.getWidth() - 1
										&& checkNeighbour(new DiscreteCoordinates(x + position.x + 1, y + position.y)),
								y > -getRadius() && y + position.y > 0
										&& checkNeighbour(new DiscreteCoordinates(x + position.x, y + position.y - 1)));
					}
				}
			}
		}
		return range;
	}
	
	// Get the next position to move for the unit
	public DiscreteCoordinates nextPosition(DiscreteCoordinates nearestPosition) {
		DiscreteCoordinates position = getCurrentMainCellCoordinates();
		ICWarsRange range = new ICWarsRange();
		range = setupMoveRange(getOwnerArea(), getCurrentMainCellCoordinates(), range);
		DiscreteCoordinates nextPosition = position;
		float distance;
		float shortestDistance = -1;
		for (int x = -getRadius(); x <= getRadius(); x++) {
			for (int y = -getRadius(); y <= getRadius(); y++) {
				DiscreteCoordinates coord = new DiscreteCoordinates((x + position.x), (y + position.y));
				if (range.nodeExists(coord) && !unitExists(coord)) {
					distance = DiscreteCoordinates.distanceBetween(nearestPosition, coord);
					if (distance <= shortestDistance || shortestDistance == -1) {
						shortestDistance = distance;
						nextPosition = coord;
					}
				}
			}
		}
		return nextPosition;
	}
	
	// Check if the unit is moving
	public boolean isMoving() {
		if (path != null) {
			return path.size() != 0;
		}
		return false;
	}
	
	// If there is an unit on the coord position
	private boolean unitExists(DiscreteCoordinates coord) {
		for (DiscreteCoordinates position : ((ICWarsArea) getOwnerArea()).getUnitsPositions()) {
			if (coord.equals(position)) {
				return true;
			}
		}
		return false;
	}
	
	// Check a position to all units position and check if has same position
	private boolean checkNeighbour(DiscreteCoordinates position) {
		if (((ICWarsArea) getOwnerArea()).getUnitsPositions() != null && !OOB(position)) {
			for (DiscreteCoordinates unit : ((ICWarsArea) getOwnerArea()).getUnitsPositions()) {
				if (unit.equals(position)) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Check if unit is in range by his index
	public boolean isInRange(int index) {
		DiscreteCoordinates unitCoords = ((ICWarsArea) getOwnerArea()).getUnitsPositions().get(index);
		if (range.nodeExists(unitCoords)) {
			return true;
		}
		return false;
	}

	// Get actually unit 
	private ICWarsUnit getUnit() {
		return this;
	}

	private class ICWarsUnitInteractionHandler implements ICWarInteractionVisitor {
		@Override
		public void interactWith(ICWarsCell cell) {
			// Get the cell defense when interact
			cellDefense = cell.getType().getDefenseStar();
		}

		@Override
		public void interactWith(Missile missile) {
			// check to not destroy the launcher of the missile
			if (!missile.getInitialPosition().equals(getPos())) {
				missile.explose(getPos());
				((ICWarsArea) getOwnerArea()).attackUnit(getUnit(), missile.getDamage());
			}
		}
	}
	
	// Let's the unit dance !
	public void dance() {
		if(getOrientation() == Orientation.UP || getOrientation() == Orientation.DOWN) {
			orientate(Orientation.RIGHT);
		}
		else if (getOrientation() == Orientation.LEFT) {
			orientate(Orientation.RIGHT);
		}
		else if (getOrientation() == Orientation.RIGHT) {
			orientate(Orientation.LEFT);
		}
	}
	
	/*
	 * Center the camera on the actor
	 */
	public void centerCamera() {
		getOwnerArea().setViewCandidate(this);
	}
}