package ch.epfl.cs107.play.game.icwars.actor.other;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.ICWarsActor;
import ch.epfl.cs107.play.game.icwars.handler.ICWarInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class City extends ICWarsActor {
	private Sprite friendlyCity;
	private Sprite enemyCity;
	private Sprite neutralCity;

	public City(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction);
	
		friendlyCity = new Sprite("icwars/friendlyBuilding", 1.f, 1.f, this, null);
		friendlyCity.setDepth(-11);
		enemyCity = new Sprite("icwars/enemyBuilding", 1.f, 1.f, this, null);
		enemyCity.setDepth(-11);
		neutralCity = new Sprite("icwars/neutralBuilding", 1.f, 1.f, this, null);
		neutralCity.setDepth(-11);
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
	public void acceptInteraction(AreaInteractionVisitor handler) {
		((ICWarInteractionVisitor) handler).interactWith(this);
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void draw(Canvas canvas) {
		if (getFaction() == Faction.NEUTRAL) {
			neutralCity.draw(canvas);
		} else if (getFaction() == Faction.ALLY) {
			friendlyCity.draw(canvas);
		} else if (getFaction() == Faction.ENEMY) {
			enemyCity.draw(canvas);
		}
	}

	@Override
	public void setFaction(Faction faction) {
		super.setFaction(faction);
	}
}