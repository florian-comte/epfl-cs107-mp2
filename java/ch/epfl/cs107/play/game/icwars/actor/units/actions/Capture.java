package ch.epfl.cs107.play.game.icwars.actor.units.actions;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer.PlayerState;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Capture extends Action {
	public Capture(ICWarsUnit unit, Area area) {
		super(unit, area);
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void draw(Canvas canvas) {
	}

	@Override
	public void doAutoAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
	}

	@Override
	public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
		if (((ICWarsArea) getArea()).getCityPosition() != null) {
			// Check if unit on a city
			for (DiscreteCoordinates cityPosition : ((ICWarsArea) getArea()).getCityPosition()) {
				if (cityPosition.equals(getUnit().getPos())) {
					((ICWarsArea) getArea()).replaceCity(getUnit().getPos(), getUnit().getFaction());
					getUnit().setAvailable(false);
					player.setPlayerState(PlayerState.NORMAL);
					return;
				}
			}
		}
		getUnit().setAvailable(false);
		player.setPlayerState(PlayerState.ACTION_SELECTION);
	}

	@Override
	public int getKey() {
		return Keyboard.C;
	}

	@Override
	public String getName() {
		return "(C)apture";
	}
}