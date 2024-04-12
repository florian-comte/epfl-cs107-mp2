
package ch.epfl.cs107.play.game.icwars.actor.players;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCell;
import ch.epfl.cs107.play.game.icwars.gui.ICWarsPlayerGUI;
import ch.epfl.cs107.play.game.icwars.handler.ICWarInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class RealPlayer extends ICWarsPlayer {
	/// Animation duration in frame number
	private final static int MOVE_DURATION = 8;

	private ICWarsPlayerGUI playerGUI;
	private Action act;
	private boolean placedUnit;
	private ICWarsUnit currentPlacingUnit;
	private int counter;

	private final ICWarsPlayerInteractionHandler handler;

	public RealPlayer(Area area, DiscreteCoordinates position, Faction faction, List<ICWarsUnit> units) {
		super(area, position, faction, units);
		if (faction == Faction.ALLY) {
			setSprite(new Sprite("icwars/allyCursor", 1.f, 1.f, this));
		} else if (faction == Faction.ENEMY) {
			setSprite(new Sprite("icwars/enemyCursor", 1.f, 1.f, this));
		}

		this.handler = new ICWarsPlayerInteractionHandler();
		this.playerGUI = new ICWarsPlayerGUI(area.getCameraScaleFactor(), this);
		this.placedUnit = false;
		this.counter = 0;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void setSelectedUnit(ICWarsUnit unit) {
		super.setSelectedUnit(unit);
		playerGUI.setSelectedUnit(unit);
	}

	@Override
	public void draw(Canvas canvas) {
		if (getPlayerState() != PlayerState.IDLE) {
			super.draw(canvas);
		}
		playerGUI.draw(canvas);
		if (getPlayerState() == PlayerState.ACTION && act != null) {
			act.draw(canvas);
		}
		if (getPlayerState() == PlayerState.PLACE_UNIT) {
			currentPlacingUnit.draw(canvas);
		}
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = getOwnerArea().getKeyboard();

		switch (getPlayerState()) {
		case IDLE:
			break;
		case NORMAL:
			if (keyboard.get(Keyboard.ENTER).isPressed()) {
				setPlayerState(PlayerState.SELECT_CELL);
			}
			if (keyboard.get(Keyboard.TAB).isPressed()) {
				setPlayerState(PlayerState.IDLE);
			}
			keyboard();
			break;
		case SELECT_CELL:
			if (keyboard.get(Keyboard.TAB).isPressed()) {
				setPlayerState(PlayerState.IDLE);
			}
			keyboard();
			break;
		case MOVE_UNIT:
			if (keyboard.get(Keyboard.ENTER).isPressed()) {
				// If unit can be moved, go to action selection
				if (moveUnit()) {
					playerGUI.setInfoActions(getSelectedUnit().getActions());
					setPlayerState(PlayerState.ACTION_SELECTION);
				} else {
					setPlayerState(PlayerState.SELECT_CELL);
				}
			}
			keyboard();
			break;
		case ACTION_SELECTION:
			// Get the action the player want
				for (Action action : getSelectedUnit().getActions()) {
					if (keyboard.get(action.getKey()).isPressed()) {
						setPlayerState(PlayerState.ACTION);
						act = action;
						break;
					}
				}
			break;
		case ACTION:
			act.doAction(deltaTime, this, keyboard);
			break;

		// At the begin of the round, the real player can place their units
		case PLACE_UNIT:
			currentPlacingUnit = getPlayerUnits().get(counter);
			currentPlacingUnit.setSpriteParent(this);
			// Check if unit exist on the cursor
			if (keyboard.get(Keyboard.ENTER).isPressed() && !unitExists()) {
				currentPlacingUnit.setSpriteParent(currentPlacingUnit);
				currentPlacingUnit.changePosition(getCurrentMainCellCoordinates());
				currentPlacingUnit.setupRange(getOwnerArea(), getCurrentMainCellCoordinates());
				((ICWarsArea) getOwnerArea()).registerUnit(currentPlacingUnit);
				nextUnit();
			}
			keyboard();
			break;
		default:
			break;
		}
	}

	@Override
	public void interactWith(Interactable other) {
		if (!isDisplacementOccurs()) {
			other.acceptInteraction(handler);
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor handler) {
		((ICWarInteractionVisitor) handler).interactWith(this);
	}

	/*
	 * USEFUL METHODS
	 */

	/*
	 * Get the next unit which have to be placed
	 */
	private void nextUnit() {
		if (counter < getPlayerUnits().size() - 1) {
			counter++;
		} else {
			placedUnit = true;
			setPlayerState(PlayerState.IDLE);
		}
	}

	/*
	 * Check if units have to be placed
	 */
	public boolean hasPlacedUnits() {
		return placedUnit;
	}

	/*
	 * Check if already have units on the cursor
	 */
	private boolean unitExists() {
		for (DiscreteCoordinates position : ((ICWarsArea) getOwnerArea()).getUnitsPositions()) {
			if (getCurrentMainCellCoordinates().equals(position)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Move the player with keyboard
	 */

	private void keyboard() {
		Keyboard keyboard = getOwnerArea().getKeyboard();
		moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
		moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
		moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
		moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
	}

	/**
	 * Orientate and Move this player in the given orientation if the given button
	 * is down
	 * 
	 * @param orientation (Orientation): given orientation, not null
	 * @param b           (Button): button corresponding to the given orientation,
	 *                    not null
	 */
	private void moveIfPressed(Orientation orientation, Button b) {
		if (b.isDown()) {
			if (!isDisplacementOccurs()) {
				orientate(orientation);
				move(MOVE_DURATION);
			}
		}
	}

	private class ICWarsPlayerInteractionHandler implements ICWarInteractionVisitor {

		/*
		 * Handler of the interaction with unit
		 */
		@Override
		public void interactWith(ICWarsUnit unit) {
			playerGUI.setInfoUnit(unit);
			if (getPlayerState() == PlayerState.SELECT_CELL && getFaction() == unit.getFaction()
					&& unit.isAvailable()) {
				setPlayerState(PlayerState.MOVE_UNIT);
				setSelectedUnit(unit);
			}
		}

		/*
		 * Handler of the interaction with a cell
		 */
		@Override
		public void interactWith(ICWarsCell cell) {
			playerGUI.setInfoType(cell.getType());
			playerGUI.setInfoUnit(null);
		}
	}
}