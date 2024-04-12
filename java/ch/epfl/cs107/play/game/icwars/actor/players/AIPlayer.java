package ch.epfl.cs107.play.game.icwars.actor.players;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Attack;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class AIPlayer extends ICWarsPlayer {
	private Action act;

	private float counter;
	private boolean counting;

	public AIPlayer(Area area, DiscreteCoordinates position, Faction faction, List<ICWarsUnit> units) {
		super(area, position, faction, units);

		if (faction == Faction.ALLY) {
			setSprite(new Sprite("icwars/allyCursor", 1.f, 1.f, this));
		} else if (faction == Faction.ENEMY) {
			setSprite(new Sprite("icwars/enemyCursor", 1.f, 1.f, this));
		}

		counter = 0.f;
		counting = false;

		for (ICWarsUnit unit : units) {
			((ICWarsArea) getOwnerArea()).registerUnit(unit);
		}
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void draw(Canvas canvas) {
		if (getPlayerState() != PlayerState.IDLE) {
			super.draw(canvas);
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
			if (waitFor(1f, deltaTime)) {
				// If has available units, go to SELECT_CELL state
				if (hasAvailableUnits()) {
					setPlayerState(PlayerState.SELECT_CELL);
					break;
				}

				// Else, go to idle
				setPlayerState(PlayerState.IDLE);
			}
			break;
		case SELECT_CELL:
			// Get an available unit
			for (ICWarsUnit unit : getPlayerUnits()) {
				if (unit.isAvailable()) {
					setSelectedUnit(unit);
				}
			}

			if (waitFor(2f, deltaTime)) {
				setPlayerState(PlayerState.MOVE_UNIT);
				changePosition(nextPosition(((ICWarsArea) getOwnerArea()).getNearestEnemyUnit(getSelectedUnit())));
			}
			break;
		case MOVE_UNIT:
			moveUnit();
			// If selected unit has finish his move, ai can select an action
			if (!getSelectedUnit().isMoving()) {
				setPlayerState(PlayerState.ACTION_SELECTION);
			}

			break;
		case ACTION_SELECTION:
			if (waitFor(1f, deltaTime)) {
//				// If already test to attack
//				if (act != null) {
//					act = new Wait(getSelectedUnit(), getOwnerArea());
//				}
				// Try to attack
				act = new Attack(getSelectedUnit(), getOwnerArea());
				getSelectedUnit().setAvailable(false);
				setPlayerState(PlayerState.ACTION);
			}
			break;
		case ACTION:
			// Launch action and set to normal state
			act.doAutoAction(deltaTime, this, keyboard);
			setPlayerState(PlayerState.NORMAL);
			break;
		default:
			break;
		}
	}

	@Override
	public void interactWith(Interactable other) {
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor handler) {
	}

	/*
	 * USEFUL METHODS
	 */

	/**
	 * Ensures that value time elapsed before returning true
	 * 
	 * @param dt    elapsed time
	 * @param value waiting time (in seconds )
	 * @return true if value seconds has elapsed , false otherwise
	 */
	private boolean waitFor(float value, float dt) {
		if (counting) {
			counter += dt;
			if (counter > value) {
				counting = false;
				return true;
			}
		} else {
			counter = 0f;
			counting = true;
		}
		return false;
	}

	/*
	 * Get new position of the ai player
	 */

	private DiscreteCoordinates nextPosition(DiscreteCoordinates nearestPosition) {
		return getSelectedUnit().nextPosition(nearestPosition);
	}
}