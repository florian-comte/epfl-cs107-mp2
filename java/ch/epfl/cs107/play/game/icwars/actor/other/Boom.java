package ch.epfl.cs107.play.game.icwars.actor.other;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Boom extends AreaEntity {
	private RPGSprite[] spriteExplosions;
	private Animation explosionAnimation;

	public Boom(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);

		this.spriteExplosions = new RPGSprite[7];

		for (int i = 0; i < 7; ++i) {
			this.spriteExplosions[i] = new RPGSprite("other/Explosion_" + i, 5, 5, this, null, new Vector(-2.25f, -2f));
		}

		this.explosionAnimation = new Animation(8, spriteExplosions, false);
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void update(float deltaTime) {
		explosionAnimation.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		explosionAnimation.draw(canvas);

		if (explosionAnimation.isCompleted()) {
			this.getOwnerArea().unregisterActor(this);
		}
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
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
}