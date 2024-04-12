package ch.epfl.cs107.play.game.icwars.actor.units.military;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Attack;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Capture;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Wait;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Soldat extends ICWarsUnit {
	public Soldat(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "Soldat");

		if (faction == Faction.ALLY) {
			setSprite(new Sprite("icwars/friendlySoldier", 1.5f, 1.5f, this, null, new Vector(-0.25f, -0.25f)));
		} else if (faction == Faction.ENEMY) {
			setSprite(new Sprite("icwars/enemySoldier", 1.5f, 1.5f, this, null, new Vector(-0.25f, -0.25f)));
		}

		setupRange(area, position);

		setHp(getMaxHp());
	}
	
	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public int getRadius() {
		return 2;
	}

	@Override
	public int getDamage() {
		return 2;
	}

	@Override
	public int getMaxHp() {
		return 5;
	}

	@Override
	public List<Action> getActions() {
		return Arrays.asList(new Wait(this, getOwnerArea()), new Attack(this, getOwnerArea()),
				new Capture(this, getOwnerArea()));
	}

	@Override
	public int getSpeed() {
		return 12;
	}
}