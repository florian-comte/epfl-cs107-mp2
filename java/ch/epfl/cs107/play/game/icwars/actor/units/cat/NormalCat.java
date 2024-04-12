package ch.epfl.cs107.play.game.icwars.actor.units.cat;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Attack;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Capture;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Wait;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;

public class NormalCat extends Cat {
	public NormalCat(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "Billy");
		setHp(getMaxHp());
		
		setAttackSprites(new RPGSprite[4]);
		setAttackSpritesLeft(new RPGSprite[4]);
		setAttackSpritesRight(new RPGSprite[4]);
		setAttackSpritesUp(new RPGSprite[4]);
		setAttackSpritesDown(new RPGSprite[4]);

		setWalkLeft(new RPGSprite[3]);
		setWalkRight(new RPGSprite[3]);
		setWalkUp(new RPGSprite[3]);
		setWalkDown(new RPGSprite[3]);

		// Init animation of unit by his faction/orientation
		if (faction == Faction.ALLY) {
			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(222, 42, 55, 50)));
			setSpriteRight(new Sprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(882, 42, 55, 50)));
			setSpriteDown(new Sprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(222, 42, 55, 50)));
			setSpriteUp(new Sprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(882, 42, 55, 50)));

			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(222, 112, 64, 56));
			getAttackSpritesLeft()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(289, 112, 64, 56));
			getAttackSpritesLeft()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(356, 121, 67, 47));
			getAttackSpritesLeft()[3] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(426, 121, 65, 47));
			
			getAttackSpritesDown()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(222, 112, 64, 56));
			getAttackSpritesDown()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(289, 112, 64, 56));
			getAttackSpritesDown()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(356, 121, 67, 47));
			getAttackSpritesDown()[3] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(426, 121, 65, 47));


			getAttackSpritesRight()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(873, 112, 64, 56));
			getAttackSpritesRight()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(806, 112, 64, 56));
			getAttackSpritesRight()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(736, 121, 67, 47));
			getAttackSpritesRight()[3] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(668, 121, 65, 47));
			
			getAttackSpritesUp()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(873, 112, 64, 56));
			getAttackSpritesUp()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(806, 112, 64, 56));
			getAttackSpritesUp()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(736, 121, 67, 47));
			getAttackSpritesUp()[3] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(668, 121, 65, 47));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(222, 42, 55, 50));
			getWalkLeft()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(280, 43, 66, 49));
			getWalkLeft()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(349, 43, 49, 49));
			
			getWalkDown()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(222, 42, 55, 50));
			getWalkDown()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(280, 43, 66, 49));
			getWalkDown()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(349, 43, 49, 49));

			getWalkRight()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(882, 42, 55, 50));
			getWalkRight()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(813, 43, 66, 49));
			getWalkRight()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(761, 43, 49, 49));

			
			getWalkUp()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(882, 42, 55, 50));
			getWalkUp()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(813, 43, 66, 49));
			getWalkUp()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(761, 43, 49, 49));

			this.orientate(Orientation.RIGHT);

		} else if (faction == Faction.ENEMY) {

			// NORMAL SPRITE L/R
			setSpriteDown(new Sprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(523, 42, 74, 78)));
			setSpriteUp(new Sprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(562, 42, 74, 78)));
			setSpriteLeft(new Sprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(523, 42, 74, 78)));
			setSpriteRight(new Sprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(562, 42, 74, 78)));

			// ATTACK SPRITES/ANIMATION L/R
			getAttackSpritesLeft()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(523, 140, 87, 85));
			getAttackSpritesLeft()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(702, 141, 86, 84));
			getAttackSpritesLeft()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(881, 156, 91, 69));
			getAttackSpritesLeft()[3] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(1069, 154, 86, 71));
			
			getAttackSpritesDown()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(523, 140, 87, 85));
			getAttackSpritesDown()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(702, 141, 86, 84));
			getAttackSpritesDown()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(881, 156, 91, 69));
			getAttackSpritesDown()[3] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this,
					new RegionOfInterest(1069, 154, 86, 71));

			getAttackSpritesRight()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(549, 140, 87, 85));
			getAttackSpritesRight()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(371, 141, 86, 84));
			getAttackSpritesRight()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(187, 156, 91, 69));
			getAttackSpritesRight()[3] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(4, 154, 86, 71));

			getAttackSpritesUp()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(549, 140, 87, 85));
			getAttackSpritesUp()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(371, 141, 86, 84));
			getAttackSpritesUp()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(187, 156, 91, 69));
			getAttackSpritesUp()[3] = new RPGSprite("other/NormalCatRight", 1f, 1f, this,
					new RegionOfInterest(4, 154, 86, 71));

			
			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(523, 42, 74, 78));
			getWalkLeft()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(599, 43, 90, 77));
			getWalkLeft()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(691, 43, 67, 77));
			
			getWalkDown()[0] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(523, 42, 74, 78));
			getWalkDown()[1] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(599, 43, 90, 77));
			getWalkDown()[2] = new RPGSprite("other/NormalCatLeft", 1f, 1f, this, new RegionOfInterest(691, 43, 67, 77));

			getWalkRight()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(562, 42, 74, 78));
			getWalkRight()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(470, 43, 90, 77));
			getWalkRight()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(401, 43, 67, 77));

			
			getWalkUp()[0] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(562, 42, 74, 78));
			getWalkUp()[1] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(470, 43, 90, 77));
			getWalkUp()[2] = new RPGSprite("other/NormalCatRight", 1f, 1f, this, new RegionOfInterest(401, 43, 67, 77));

			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.LEFT);

		}
	}
	
	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public int getRadius() {
		return 3;
	}

	@Override
	public int getDamage() {
		return 3;
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