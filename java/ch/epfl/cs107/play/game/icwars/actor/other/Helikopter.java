package ch.epfl.cs107.play.game.icwars.actor.other;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Helikopter extends MovableAreaEntity {
	private Sprite sprite;
	private final int SPEED = 50;

	public Helikopter(Area area, DiscreteCoordinates position) {
		super(area, Orientation.RIGHT, position);

		sprite = new Sprite("other/Helicopter", 1.5f, 1.1f, this, new RegionOfInterest(131, 199, 61, 39));

		((ICWarsArea) getOwnerArea()).playSound("helikopter", true);
		getOwnerArea().setViewCandidate(this);
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
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
		sprite.draw(canvas);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		move(SPEED);
	}
}