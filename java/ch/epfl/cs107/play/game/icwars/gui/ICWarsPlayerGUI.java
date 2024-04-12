package ch.epfl.cs107.play.game.icwars.gui;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer.PlayerState;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class ICWarsPlayerGUI implements Graphics {
	public final static int FONT_SIZE = 20;

	private ICWarsPlayer player;
	private ICWarsActionsPanel actionPanel;
	private ICWarsInfoPanel infoPanel;
	private ICWarsUnit selectedUnit;

	public ICWarsPlayerGUI(float cameraScaleFactor, ICWarsPlayer player) {
		this.actionPanel = new ICWarsActionsPanel(cameraScaleFactor);
		this.infoPanel = new ICWarsInfoPanel(cameraScaleFactor);
		this.player = player;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void draw(Canvas canvas) {
		if (selectedUnit != null) {
			if (player.getPlayerState() == PlayerState.ACTION_SELECTION) {
				actionPanel.draw(canvas);
			}

			if (player.getPlayerState() == PlayerState.MOVE_UNIT) {
				selectedUnit.drawRangeAndPathTo(
						new DiscreteCoordinates((int) player.getPosition().getX(), (int) player.getPosition().getY()),
						canvas);
			}
		}

		if (player.getPlayerState() == PlayerState.NORMAL || player.getPlayerState() == PlayerState.SELECT_CELL) {
			infoPanel.draw(canvas);
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */
	
	
	// Set selected unit
	public void setSelectedUnit(ICWarsUnit unit) {
		selectedUnit = unit;
	}

	// Set info of the unit
	public void setInfoUnit(ICWarsUnit unit) {
		infoPanel.setUnit(unit);
	}

	// Set info of the type
	public void setInfoType(ICWarsCellType type) {
		infoPanel.setCurrentCell(type);
	}

	// Set the info of actions
	public void setInfoActions(List<Action> actions) {
		actionPanel.setActions(actions);
	}
}