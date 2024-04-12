package ch.epfl.cs107.play.game.icwars.actor.units.actions;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer.PlayerState;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Wait extends Action {
	public Wait(ICWarsUnit unit, Area area) {
		super(unit, area);
	}
	
	/*
	 * OVERRIDE METHODS
	 */
	
	@Override
	public void draw(Canvas canvas) {
	}

	@Override
	public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
		getUnit().setAvailable(false);
		player.setPlayerState(PlayerState.NORMAL);
	}

	@Override
	public void doAutoAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
		getUnit().setAvailable(false);
		player.setPlayerState(PlayerState.NORMAL);
	}

	@Override
	public int getKey() {
		return Keyboard.W;
	}

	@Override
	public String getName() {
		return "(W)ait";
	}
}