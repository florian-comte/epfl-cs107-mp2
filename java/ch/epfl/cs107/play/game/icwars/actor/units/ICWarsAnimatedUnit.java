package ch.epfl.cs107.play.game.icwars.actor.units;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class ICWarsAnimatedUnit extends ICWarsUnit {
	private boolean startAnimation;
	private Sprite spriteLeft;
	private Sprite spriteRight;
	private Sprite spriteUp;
	private Sprite spriteDown;

	private RPGSprite[] attackSprites;
	private RPGSprite[] attackSpritesRight;
	private RPGSprite[] attackSpritesLeft;
	private RPGSprite[] attackSpritesUp;
	private RPGSprite[] attackSpritesDown;
	private Animation attackAnimation;

	private RPGSprite[] walkLeft;
	private RPGSprite[] walkRight;
	private RPGSprite[] walkUp;
	private RPGSprite[] walkDown;
	private RPGSprite[] walkingSprites;
	private Animation walkingAnimation;

	public ICWarsAnimatedUnit(Area area, DiscreteCoordinates position, Faction faction, String name) {
		super(area, position, faction, name);
		setupRange(area, position);
		startAnimation = false;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		// If unit animation has start
		if (startAnimation) {
			attackAnimation.update(deltaTime);
		}
		// If unit is moving
		if (isDisplacementOccurs()) {
			walkingAnimation.update(deltaTime);
		}

	}

	@Override
	public void draw(Canvas canvas) {
		// if unit not moving and not attacking
		if ((!startAnimation || attackAnimation.isCompleted()) && !isDisplacementOccurs()) {
			getSprite().draw(canvas);
		// if unit is moving
		} else if (isDisplacementOccurs()) {
			walkingAnimation.draw(canvas);
		// if unit is attacking
		} else {
			attackAnimation.draw(canvas);
		}

	}

	@Override
	public void removeHp(int points) {
		super.removeHp(points);
	}

	@Override
	public boolean orientate(Orientation orientation) {
		if (orientation == Orientation.RIGHT) {
			walkingSprites = walkRight;
			attackSprites = attackSpritesRight;
			setSprite(spriteRight);
		} else if (orientation == Orientation.LEFT){
			walkingSprites = walkLeft;
			attackSprites = attackSpritesLeft;
			setSprite(spriteLeft);
		} else if (orientation == Orientation.UP){
			walkingSprites = walkUp;
			attackSprites = attackSpritesUp;
			setSprite(spriteUp);
		} else if (orientation == Orientation.DOWN){
			walkingSprites = walkDown;
			attackSprites = attackSpritesDown;
			setSprite(spriteDown);
		}

		attackAnimation = new Animation(5, attackSprites, false);
		walkingAnimation = new Animation(5, walkingSprites, true);

		return super.orientate(orientation);
	}

	/*
	 * GETTERS AND SETTERS OF ANIMATIONS
	 */

	protected void setSpriteLeft(Sprite spriteLeft) {
		this.spriteLeft = spriteLeft;
	}

	protected void setSpriteRight(Sprite spriteRight) {
		this.spriteRight = spriteRight;
	}
	
	protected void setSpriteUp(Sprite spriteUp) {
		this.spriteUp = spriteUp;
	}
	
	protected void setSpriteDown(Sprite spriteDown) {
		this.spriteDown = spriteDown;
	}

	protected void setAttackSprites(RPGSprite[] attackSprites) {
		this.attackSprites = attackSprites;
	}

	protected RPGSprite[] getAttackSpritesRight() {
		return attackSpritesRight;
	}

	protected void setAttackSpritesRight(RPGSprite[] attackSpritesRight) {
		this.attackSpritesRight = attackSpritesRight;
	}

	protected RPGSprite[] getAttackSpritesLeft() {
		return attackSpritesLeft;
	}

	protected void setAttackSpritesLeft(RPGSprite[] attackSpritesLeft) {
		this.attackSpritesLeft = attackSpritesLeft;
	}
	
	protected RPGSprite[] getAttackSpritesUp() {
		return attackSpritesUp;
	}

	protected void setAttackSpritesUp(RPGSprite[] attackSpritesUp) {
		this.attackSpritesUp = attackSpritesUp;
	}
	
	protected RPGSprite[] getAttackSpritesDown() {
		return attackSpritesDown;
	}

	protected void setAttackSpritesDown(RPGSprite[] attackSpritesDown) {
		this.attackSpritesDown = attackSpritesDown;
	}

	protected RPGSprite[] getWalkLeft() {
		return walkLeft;
	}

	protected void setWalkLeft(RPGSprite[] walkLeft) {
		this.walkLeft = walkLeft;
	}

	protected RPGSprite[] getWalkRight() {
		return walkRight;
	}

	protected void setWalkRight(RPGSprite[] walkRight) {
		this.walkRight = walkRight;
	}
	
	protected RPGSprite[] getWalkUp() {
		return walkUp;
	}

	protected void setWalkUp(RPGSprite[] walkUp) {
		this.walkUp = walkUp;
	}
	
	protected RPGSprite[] getWalkDown() {
		return walkDown;
	}

	protected void setWalkDown(RPGSprite[] walkDown) {
		this.walkDown = walkDown;
	}

	/*
	 * USEFUL METHODS
	 */

	public void startAnimation() {
		attackAnimation = new Animation(6, attackSprites, false);
		startAnimation = true;
	}
}