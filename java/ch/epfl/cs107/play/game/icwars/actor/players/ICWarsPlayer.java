package ch.epfl.cs107.play.game.icwars.actor.players;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.ICWarsActor;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public abstract class ICWarsPlayer extends ICWarsActor implements Interactor {
	private List<ICWarsUnit> units;

	private RPGSprite[] spriteSelector;
	private Animation selectorAnimation;

	private Sprite sprite;
	private PlayerState state;
	private ICWarsUnit selectedUnit;

	public enum PlayerState {
		PLACE_UNIT, IDLE, NORMAL, SELECT_CELL, MOVE_UNIT, ACTION_SELECTION, ACTION;
	}

	public ICWarsPlayer(Area area, DiscreteCoordinates position, Faction faction, List<ICWarsUnit> units) {
		super(area, position, faction);
		this.units = new ArrayList<>();
		this.spriteSelector = new RPGSprite[4];
		this.state = PlayerState.IDLE;
		this.selectorAnimation = new Animation(4, spriteSelector, true);

		for (ICWarsUnit unit : units) {
			this.units.add(unit);
		}

		for (int i = 0; i < 4; ++i) {
			this.spriteSelector[i] = new RPGSprite("icwars/selector", 1, 1, this,
					new RegionOfInterest(i * 32, 0, 32, 32));
			spriteSelector[i].setDepth(1);
		}

		resetMotion();
	}

	/*
	 * OVERRIDE METHODS
	 */

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
	}

	@Override
	public void draw(Canvas canvas) {
		if (getFaction() == Faction.ENEMY) {
			sprite.draw(canvas);
		} else {
			selectorAnimation.draw(canvas);
		}
	}

	@Override
	public void update(float deltaTime) {
		selectorAnimation.update(deltaTime);
		super.update(deltaTime);

		checkUnits(false);
	}

	@Override
	public void leaveArea() {
		checkUnits(true);
		super.leaveArea();
	}

	@Override
	public void onLeaving(List<DiscreteCoordinates> coordinates) {
		super.onLeaving(coordinates);
		if (state == PlayerState.SELECT_CELL) {
			state = PlayerState.NORMAL;
		}
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
	}

	/*
	 * GETTERS AND SETTERS
	 */

	/*
	 * Get the units of the player
	 */

	protected List<ICWarsUnit> getPlayerUnits() {
		return units;
	}

	/*
	 * Get the selected unit
	 */

	protected ICWarsUnit getSelectedUnit() {
		return selectedUnit;
	}

	/*
	 * Get actually player state
	 */

	public PlayerState getPlayerState() {
		return state;
	}

	/*
	 * Set the sprite of the player
	 */

	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/*
	 * Set the selected unit of the player
	 */

	protected void setSelectedUnit(ICWarsUnit unit) {
		if (units.contains(unit)) {
			selectedUnit = unit;
		}
	}

	/*
	 * Set the state of the player
	 */

	public void setPlayerState(PlayerState state) {
		this.state = state;
	}

	/*
	 * USEFUL METHODS
	 */

	/*
	 * Select unit by his index
	 */

	protected void selectUnit(int i) {
		if (units.size() > i) {
			setSelectedUnit(units.get(i));
		}
	}

	/*
	 * Check if the player has avalaible unit
	 */

	protected boolean hasAvailableUnits() {
		for (ICWarsUnit unit : units) {
			if (unit.isAvailable()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Check if player has dead unit, if its the case remove it. If force = true,
	 * all units are forced removed
	 */

	private void checkUnits(boolean force) {
		List<ICWarsUnit> deadUnits = new ArrayList<>();
		for (ICWarsUnit unit : units) {
			if (unit.isDead() || force) {
				deadUnits.add(unit);
			}
		}

		for (ICWarsUnit dead : deadUnits) {
			removeUnit(dead);
		}
	}

	/*
	 * Move unit to the cursor of the player
	 */

	protected boolean moveUnit() {
		ICWarsUnit unit = getSelectedUnit();

		if (unit != null) {
			if (unit.isAvailable()) {
				// Check if there is no player on the cursor
				List<DiscreteCoordinates> units = ((ICWarsArea) getOwnerArea()).getUnitsPositions();
				for (int i = 0; i < units.size(); i++) {
					if (!units.get(i).equals(unit.getPos()) && units.get(i).equals(getCurrentMainCellCoordinates())) {
						return false;
					}
				}
				// Check if the position is in the range of the unit
				if (unit.isInRange(getCurrentMainCellCoordinates())) {
					// Set the path the unit will have to take
					unit.setPath(getOwnerArea(), getCurrentMainCellCoordinates());
					unit.setupRange(getOwnerArea(), getCurrentMainCellCoordinates());
					unit.setAvailable(false);
					return true;

				}
			}
		}
		return false;
	}

	/*
	 * Remove unit of the list of the player
	 */
	private void removeUnit(ICWarsUnit unit) {
		if (units.contains(unit)) {
			units.remove(unit);
		}
	}

	/*
	 * Set all units of the player available
	 */
	public void setUnitsAvailable() {
		for (ICWarsUnit unit : units) {
			unit.setAvailable(true);
		}
	}

	/*
	 * Check if the player is defeated
	 */
	public boolean isDefeated() {
		return units.size() <= 0;
	}
	
	/*
	 * Start the turn of the player
	 */
	public void startTurn() {
		state = PlayerState.NORMAL;
		centerCamera();
		setUnitsAvailable();
	}

	/*
	 * Center the camera on the actor
	 */
	public void centerCamera() {
		getOwnerArea().setViewCandidate(this);
	}
}