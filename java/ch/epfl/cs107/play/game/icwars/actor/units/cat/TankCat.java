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
import ch.epfl.cs107.play.math.Vector;

public class TankCat extends Cat {
	public TankCat(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "Tanky");
		setHp(getMaxHp());

		setAttackSprites(new RPGSprite[6]);
		setAttackSpritesLeft(new RPGSprite[6]);
		setAttackSpritesRight(new RPGSprite[6]);
		setAttackSpritesUp(new RPGSprite[6]);
		setAttackSpritesDown(new RPGSprite[6]);

		setWalkLeft(new RPGSprite[3]);
		setWalkRight(new RPGSprite[3]);
		setWalkUp(new RPGSprite[3]);
		setWalkDown(new RPGSprite[3]);

		// Init animation of unit by his faction/orientation

		if (faction == Faction.ALLY) {
			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/AllyTankCatLeft", 1f, 1.6f, this, new RegionOfInterest(289, 44, 66, 114)));
			setSpriteRight(
					new Sprite("other/AllyTankCatRight", 1f, 1.6f, this, new RegionOfInterest(1011, 44, 66, 114)));
			setSpriteUp(new Sprite("other/AllyTankCatRight", 1f, 1.6f, this, new RegionOfInterest(1011, 44, 66, 114)));
			setSpriteDown(new Sprite("other/AllyTankCatLeft", 1f, 1.6f, this, new RegionOfInterest(289, 44, 66, 114)));

			// ATTACK SPRITES/ANIMATION L/R
			getAttackSpritesLeft()[0] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(289, 178, 72, 116), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[1] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(364, 178, 67, 113), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[2] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(434, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[3] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(508, 221, 147, 97), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[4] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(652, 271, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[5] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(805, 272, 150, 46), new Vector(-1.5f, 0f));

			getAttackSpritesDown()[0] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(289, 178, 72, 116), new Vector(0f, 0.5f));
			getAttackSpritesDown()[1] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(364, 178, 67, 113), new Vector(0f, 0.5f));
			getAttackSpritesDown()[2] = new RPGSprite("other/AllyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(434, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesDown()[3] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(508, 221, 147, 97), new Vector(-1.5f, 0f));
			getAttackSpritesDown()[4] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(652, 271, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesDown()[5] = new RPGSprite("other/AllyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(805, 272, 150, 46), new Vector(-1.5f, 0f));

			getAttackSpritesRight()[0] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(1005, 178, 72, 116), new Vector(0f, 0.5f));
			getAttackSpritesRight()[1] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(935, 178, 67, 113), new Vector(0f, 0.5f));
			getAttackSpritesRight()[2] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(861, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesRight()[3] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(717, 221, 147, 97), new Vector(1f, 0f));
			getAttackSpritesRight()[4] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(564, 271, 150, 47), new Vector(1f, 0f));
			getAttackSpritesRight()[5] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(411, 272, 150, 46), new Vector(1f, 0f));

			getAttackSpritesUp()[0] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(1005, 178, 72, 116), new Vector(0f, 0.5f));
			getAttackSpritesUp()[1] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(935, 178, 67, 113), new Vector(0f, 0.5f));
			getAttackSpritesUp()[2] = new RPGSprite("other/AllyTankCatRight", 1f, 2f, this,
					new RegionOfInterest(861, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesUp()[3] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(717, 221, 147, 97), new Vector(1f, 0f));
			getAttackSpritesUp()[4] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(564, 271, 150, 47), new Vector(1f, 0f));
			getAttackSpritesUp()[5] = new RPGSprite("other/AllyTankCatRight", 2f, 0.7f, this,
					new RegionOfInterest(411, 272, 150, 46), new Vector(1f, 0f));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(289, 44, 66, 114));
			getWalkLeft()[1] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(358, 44, 66, 114));
			getWalkLeft()[2] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(427, 44, 67, 114));

			getWalkDown()[0] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(289, 44, 66, 114));
			getWalkDown()[1] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(358, 44, 66, 114));
			getWalkDown()[2] = new RPGSprite("other/AllyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(427, 44, 67, 114));

			getWalkRight()[0] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1011, 44, 66, 114));
			getWalkRight()[1] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(942, 44, 66, 114));
			getWalkRight()[2] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(872, 44, 67, 114));

			getWalkUp()[0] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1011, 44, 66, 114));
			getWalkUp()[1] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(942, 44, 66, 114));
			getWalkUp()[2] = new RPGSprite("other/AllyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(872, 44, 67, 114));

			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.RIGHT);

		} else if (faction == Faction.ENEMY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/EnemyTankCatLeft", 1f, 1.6f, this, new RegionOfInterest(305, 43, 70, 115)));
			setSpriteRight(new Sprite("other/EnemyTankCatRight", 1f, 1.6f, this, new RegionOfInterest(1569, 43, 70, 115)));
			setSpriteDown(new Sprite("other/EnemyTankCatLeft", 1f, 1.6f, this, new RegionOfInterest(305, 43, 70, 115)));
			setSpriteUp(new Sprite("other/EnemyTankCatRight", 1f, 1.6f, this, new RegionOfInterest(1569, 43, 70, 115)));
			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(304, 178, 75, 115), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[1] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(382, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[2] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(456, 178, 72, 115), new Vector(0f, 0.5f));
			getAttackSpritesLeft()[3] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(531, 221, 141, 98), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[4] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(675, 272, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[5] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(828, 273, 150, 46), new Vector(-1.5f, 0f));
			
			getAttackSpritesDown()[0] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(304, 178, 75, 115), new Vector(0f, 0.5f));
			getAttackSpritesDown()[1] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(382, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesDown()[2] = new RPGSprite("other/EnemyTankCatLeft", 1f, 2f, this,
					new RegionOfInterest(456, 178, 72, 115), new Vector(0f, 0.5f));
			getAttackSpritesDown()[3] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(531, 221, 141, 98), new Vector(-1.5f, 0f));
			getAttackSpritesDown()[4] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(675, 272, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesDown()[5] = new RPGSprite("other/EnemyTankCatLeft", 2f, 0.7f, this,
					new RegionOfInterest(828, 273, 150, 46), new Vector(-1.5f, 0f));

			getAttackSpritesRight()[0] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1565, 178, 75, 115), new Vector(0f, 0.5f));
			getAttackSpritesRight()[1] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1491, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesRight()[2] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1416, 178, 72, 115), new Vector(0f, 0.5f));
			getAttackSpritesRight()[3] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(1272, 221, 141, 98), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[4] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(1119, 272, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[5] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(966, 273, 150, 46), new Vector(-1.5f, 0f));

			getAttackSpritesUp()[0] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1565, 178, 75, 115), new Vector(0f, 0.5f));
			getAttackSpritesUp()[1] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1491, 178, 71, 114), new Vector(0f, 0.5f));
			getAttackSpritesUp()[2] = new RPGSprite("other/EnemyTankCatRight", -1f, 2f, this,
					new RegionOfInterest(1416, 178, 72, 115), new Vector(0f, 0.5f));
			getAttackSpritesUp()[3] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(1272, 221, 141, 98), new Vector(-1.5f, 0f));
			getAttackSpritesUp()[4] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(1119, 272, 150, 47), new Vector(-1.5f, 0f));
			getAttackSpritesUp()[5] = new RPGSprite("other/EnemyTankCatRight", -2f, 0.7f, this,
					new RegionOfInterest(966, 273, 150, 46), new Vector(-1.5f, 0f));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(305, 43, 70, 115));
			getWalkLeft()[1] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(378, 45, 71, 113));
			getWalkLeft()[2] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(452, 45, 71, 113));
			
			getWalkDown()[0] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(305, 43, 70, 115));
			getWalkDown()[1] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(378, 45, 71, 113));
			getWalkDown()[2] = new RPGSprite("other/EnemyTankCatLeft", 1f, 1.6f, this,
					new RegionOfInterest(452, 45, 71, 113));

			getWalkRight()[0] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1569, 43, 70, 115));
			getWalkRight()[1] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1495, 45, 71, 113));
			getWalkRight()[2] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1421, 45, 71, 113));
			
			getWalkUp()[0] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1569, 43, 70, 115));
			getWalkUp()[1] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1495, 45, 71, 113));
			getWalkUp()[2] = new RPGSprite("other/EnemyTankCatRight", 1f, 1.6f, this,
					new RegionOfInterest(1421, 45, 71, 113));

			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.LEFT);

		}
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
		return 6;
	}

	@Override
	public int getMaxHp() {
		return 12;
	}

	@Override
	public List<Action> getActions() {
		return Arrays.asList(new Wait(this, getOwnerArea()), new Attack(this, getOwnerArea()),
				new Capture(this, getOwnerArea()));
	}

	@Override
	public int getSpeed() {
		return 10;
	}
}