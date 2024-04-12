package ch.epfl.cs107.play.game.icwars.actor.units.actions;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.icwars.actor.other.Missile;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer.PlayerState;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsAnimatedUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.military.RPG;
import ch.epfl.cs107.play.game.icwars.actor.units.military.Soldat;
import ch.epfl.cs107.play.game.icwars.actor.units.military.Tank;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Attack extends Action {
	private List<Integer> attaquable;
	private int index;

	public Attack(ICWarsUnit unit, Area area) {
		super(unit, area);

		attaquable = new ArrayList<Integer>();
		index = 0;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void draw(Canvas canvas) {
		Integer attaquedUnit = selectedUnit();
		// If selected unit exist
		if (attaquedUnit != null) {
			((ICWarsArea) getArea()).centerCamera(attaquedUnit);
			ImageGraphics cursor = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
					new RegionOfInterest(4 * 18, 26 * 18, 16, 16));
			cursor.setAnchor(canvas.getPosition().add(1, 0));
			cursor.draw(canvas);
		}
	}

	@Override
	public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
		// List of attaquable units
		attaquable = getAttaquable();

		if (!attaquable.isEmpty()) {
			// Choose the unit to be attaqued
			if (keyboard.get(Keyboard.LEFT).isPressed()) {
				leftIndex();
			} else if (keyboard.get(Keyboard.RIGHT).isPressed()) {
				rightIndex();
			}

			// Attack unit
			if (keyboard.get(Keyboard.ENTER).isPressed()) {
				if (performAttack(player)) {
					player.setPlayerState(PlayerState.NORMAL);
				}
			}
		} else {
			player.setPlayerState(PlayerState.ACTION_SELECTION);
		}
	}

	@Override
	public void doAutoAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
		attaquable = getAttaquable();
		if (!attaquable.isEmpty()) {
			performAttack(player);
		} else {
			getUnit().setAvailable(false);
			// player.setPlayerState(PlayerState.ACTION_SELECTION);
		}
	}

	@Override
	public int getKey() {
		return Keyboard.A;
	}

	@Override
	public String getName() {
		return "(A)ttack";
	}

	/*
	 * USEFUL METHODS
	 */

	// Attack the selectioned unit
	private boolean performAttack(ICWarsPlayer player) {
		Integer attaquedUnit = selectedUnit();
		List<DiscreteCoordinates> unitsPos = ((ICWarsArea) getArea()).getUnitsPositions();

		checkOrientation(unitsPos.get(attaquedUnit));
		// if unit can launch missile
		if (getUnit() instanceof Tank || getUnit() instanceof RPG) {
			// launch missile
			getArea().registerActor(new Missile(getArea(), missileOrientation(unitsPos.get(attaquedUnit)),
					getUnit().getPos(), unitsPos.get(attaquedUnit), getUnit().getDamage()));
		} else {
			((ICWarsArea) getArea()).attackUnit(attaquedUnit, getUnit().getDamage());
		}
		getUnit().setAvailable(false);
		player.centerCamera();

		// if the unit can be animated
		if (getUnit() instanceof ICWarsAnimatedUnit) {
			((ICWarsAnimatedUnit) getUnit()).startAnimation();
		}
		return true;
	}

	// Set selected unit to before unit
	private void leftIndex() {
		if (index - 1 >= 0) {
			index--;
		} else {
			index = attaquable.size() - 1;
		}
	}

	// Set selected unit to next unit
	private void rightIndex() {
		if (attaquable.size() > index + 1) {
			index++;
		} else {
			index = 0;
		}
	}

	// Get the selectedUnit which
	private Integer selectedUnit() {
		if (attaquable != null && attaquable.size() > index && attaquable.get(index) != null) {
			return attaquable.get(index);
		}
		return null;
	}

	// Get the orientation of the missile who will be launch
	private Orientation missileOrientation(DiscreteCoordinates pos) {
		if (getUnit().getPos().x > pos.x) {
			return Orientation.LEFT;
		} else if (getUnit().getPos().x < pos.x) {
			return Orientation.RIGHT;
		} else if (getUnit().getPos().y > pos.y) {
			return Orientation.DOWN;
		} else if (getUnit().getPos().y > pos.y) {
			return Orientation.UP;
		}
		return Orientation.UP;
	}

	// Set orientation of the unit in direction of the pos
	private void checkOrientation(DiscreteCoordinates pos) {
		if (pos.x > getUnit().getPos().x) {
			getUnit().setOrientation(Orientation.RIGHT);
		} else if (pos.x < getUnit().getPos().x) {
			getUnit().setOrientation(Orientation.LEFT);
		} else if (pos.y > getUnit().getPos().y) {
			getUnit().setOrientation(Orientation.UP);
		} else if (pos.y < getUnit().getPos().y) {
			getUnit().setOrientation(Orientation.DOWN);
		}
	}

	// Get the list of units can be attaqued
	private List<Integer> getAttaquable() {
		List<Integer> result = new ArrayList<Integer>();
		List<DiscreteCoordinates> unitsPos = ((ICWarsArea) getArea()).getUnitsPositions();
		for (int i = 0; i < unitsPos.size(); i++) {
			// If tank or missile, just units on same line
			if (getUnit() instanceof Tank || getUnit() instanceof RPG) {
				if (((ICWarsArea) getArea()).isAttaquable(i, getUnit(), 4)) {
					if (sameLine(unitsPos.get(i))) {
						result.add(i);
					}
				}
				// If soldat in his range
			} else if (getUnit() instanceof Soldat) {
				if (getUnit().isInRange(i) && ((ICWarsArea) getArea()).isAttaquable(i, getUnit(),
						(int) Math.pow(getUnit().getRadius(), 2))) {
					result.add(i);
				}

				// else just check if attaquable
			} else {
				if (((ICWarsArea) getArea()).isAttaquable(i, getUnit(), 1)) {
					result.add(i);
				}
			}
		}
		return result;
	}
}