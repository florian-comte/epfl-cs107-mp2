package ch.epfl.cs107.play.game.icwars.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.other.City;
import ch.epfl.cs107.play.game.icwars.actor.other.Meteorite;
import ch.epfl.cs107.play.game.icwars.actor.other.Missile;
import ch.epfl.cs107.play.game.icwars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCell;

public interface ICWarInteractionVisitor extends AreaInteractionVisitor {
	default void interactWith(ICWarsUnit unit) {
	}

	default void interactWith(City city) {
	}

	default void interactWith(RealPlayer player) {
	}

	default void interactWith(ICWarsCell cell) {
	}

	default void interactWith(Missile missile) {
	}

	default void interactWith(Meteorite meteorite) {
	}
}