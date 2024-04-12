package ch.epfl.cs107.play.game.icwars.actor.other;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Meteorite extends MovableAreaEntity {
	private final int SPEED = 2;
	private Sprite[] sprites = new Sprite[7];
	private int i = 0;

	public Meteorite(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);

		sprites[0] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(26, 137, 74, 71));
		sprites[1] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(123, 33, 109, 178));
		sprites[2] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(266, 16, 98, 225));
		sprites[3] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(413, 27, 99, 222));
		sprites[4] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(541, 24, 94, 221));
		sprites[5] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(658, 101, 58, 146));
		sprites[6] = new RPGSprite("other/meteorite", 1, 2, this, new RegionOfInterest(765, 110, 53, 118));

		((ICWarsArea) getOwnerArea()).playSound("meteor", false);
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
		return true;
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
		if(i > 6) {
			sprites[6].draw(canvas);
		} else {
			sprites[i].draw(canvas);
		}
		i++;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (getPosition().y <= 0) {
			getOwnerArea().unregisterActor(this);
		}
		move(SPEED);
	}
}