package ch.epfl.cs107.play.game.icwars.actor.units.military;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.RPGSprite;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsAnimatedUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Action;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Attack;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Capture;
import ch.epfl.cs107.play.game.icwars.actor.units.actions.Wait;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public class RPG extends ICWarsAnimatedUnit {
	public RPG(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "RPG");

		setHp(getMaxHp());

		setAttackSprites(new RPGSprite[10]);
		setAttackSpritesLeft(new RPGSprite[10]);
		setAttackSpritesRight(new RPGSprite[10]);
		setAttackSpritesUp(new RPGSprite[10]);
		setAttackSpritesDown(new RPGSprite[10]);

		setWalkLeft(new RPGSprite[4]);
		setWalkRight(new RPGSprite[4]);
		setWalkUp(new RPGSprite[4]);
		setWalkDown(new RPGSprite[4]);
		
		// Init animation of unit by his faction/orientation
		if (faction == Faction.ALLY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(338, 52, 39, 35)));
			setSpriteRight(new Sprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(57, 52, 39, 35)));
			setSpriteUp(new Sprite("other/RPGBleu", 0.66f, 1f, this, new RegionOfInterest(495, 52, 21, 35), new Vector(0.2f, 0f)));
			setSpriteDown(new Sprite("other/RPGBleu", 0.66f, 1f, this, new RegionOfInterest(207, 53, 20, 36), new Vector(0.2f, 0f)));

			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/RPGBleu", 2f, 1f, this,
					new RegionOfInterest(321, 631, 66, 32), new Vector(-1f, 0f));
			getAttackSpritesLeft()[1] = new RPGSprite("other/RPGBleu", 2.5f, 1f, this,
					new RegionOfInterest(309, 775, 79, 32), new Vector(-1.25f, 0f));
			getAttackSpritesLeft()[2] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(295, 917, 100, 34), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[3] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(295, 1061, 103, 34), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[4] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(292, 1201, 103, 38), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[5] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(292, 1346, 103, 37), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[6] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(292, 1494, 103, 33), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[7] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(292, 1636, 103, 35), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[8] = new RPGSprite("other/RPGBleu", 1f, 1f, this,
					new RegionOfInterest(338, 1780, 39, 35), new Vector(0f, 0f));
			getAttackSpritesLeft()[9] = new RPGSprite("other/RPGBleu", 1f, 1f, this,
					new RegionOfInterest(338, 1924, 39, 35), new Vector(0f, 0f));

			getAttackSpritesRight()[0] = new RPGSprite("other/RPGBleu", 2f, 1f, this,
					new RegionOfInterest(47, 631, 66, 32), new Vector(-1f, 0f));
			getAttackSpritesRight()[1] = new RPGSprite("other/RPGBleu", 2.5f, 1f, this,
					new RegionOfInterest(46, 775, 79, 32), new Vector(-1.25f, 0f));
			getAttackSpritesRight()[2] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(39, 917, 100, 34), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[3] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(36, 1061, 103, 34), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[4] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(39, 1201, 103, 38), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[5] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(38, 1346, 103, 37), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[6] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(38, 1494, 103, 33), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[7] = new RPGSprite("other/RPGBleu", 3f, 1f, this,
					new RegionOfInterest(38, 1636, 103, 35), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[8] = new RPGSprite("other/RPGBleu", 1f, 1f, this,
					new RegionOfInterest(57, 1780, 39, 35), new Vector(0f, 0f));
			getAttackSpritesRight()[9] = new RPGSprite("other/RPGBleu", 1f, 1f, this,
					new RegionOfInterest(57, 1924, 39, 35), new Vector(0f, 0f));

			getAttackSpritesUp()[0] = new RPGSprite("other/RPGBleu", 1f, 2f, this,
					new RegionOfInterest(495, 621, 27, 42), new Vector(0f, 0f));
			getAttackSpritesUp()[1] = new RPGSprite("other/RPGBleu", 1f, 2f, this,
					new RegionOfInterest(495, 751, 28, 57), new Vector(0f, 0f));
			getAttackSpritesUp()[2] = new RPGSprite("other/RPGBleu", 1f, 2f, this,
					new RegionOfInterest(495, 881, 30, 74), new Vector(0f, 0f));
			getAttackSpritesUp()[3] = new RPGSprite("other/RPGBleu", 1f, 1.2f, this,
					new RegionOfInterest(495, 1023, 32, 77), new Vector(0f, 0f));
			getAttackSpritesUp()[4] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(495, 1163, 32, 83), new Vector(0f, -0.5f));
			getAttackSpritesUp()[5] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(495, 1350, 30, 83), new Vector(0f, -2.f));
			getAttackSpritesUp()[6] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(495, 1494, 30, 83), new Vector(0f, -1.6f));
			getAttackSpritesUp()[7] = new RPGSprite("other/RPGBleu", 1f, 1.2f, this,
					new RegionOfInterest(495, 1636, 30, 35), new Vector(0f, 0f));
			getAttackSpritesUp()[8] = new RPGSprite("other/RPGBleu", 0.66f, 1f, this,
					new RegionOfInterest(495, 1780, 21, 35), new Vector(0f, 0f));
			getAttackSpritesUp()[9] = new RPGSprite("other/RPGBleu", 0.66f, 1f, this,
					new RegionOfInterest(495, 1924, 21, 35), new Vector(0f, 0f));

			getAttackSpritesDown()[0] = new RPGSprite("other/RPGBleu", 1f, 2f, this,
					new RegionOfInterest(207, 632, 22, 33));
			getAttackSpritesDown()[1] = new RPGSprite("other/RPGBleu", 1, 2f, this,
					new RegionOfInterest(207, 776, 24, 41));
			getAttackSpritesDown()[2] = new RPGSprite("other/RPGBleu", 1f, 2f, this,
					new RegionOfInterest(207, 920, 25, 45));
			getAttackSpritesDown()[3] = new RPGSprite("other/RPGBleu", 1f, 1.2f, this,
					new RegionOfInterest(207, 1063, 26, 54));
			getAttackSpritesDown()[4] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(207, 1206, 26, 70), new Vector(0f, -0.7f));
			getAttackSpritesDown()[5] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(207, 1351, 25, 70), new Vector(0f, -2.f));
			getAttackSpritesDown()[6] = new RPGSprite("other/RPGBleu", 1f, 3f, this,
					new RegionOfInterest(207, 1493, 24, 70), new Vector(0f, -1.6f));
			getAttackSpritesDown()[7] = new RPGSprite("other/RPGBleu", 1f, 1.2f, this,
					new RegionOfInterest(207, 1637, 21, 36));
			getAttackSpritesDown()[8] = new RPGSprite("other/RPGBleu", 0.66f, 1f, this,
					new RegionOfInterest(207, 1781, 20, 36));
			getAttackSpritesDown()[9] = new RPGSprite("other/RPGBleu", 0.66f, 1f, this,
					new RegionOfInterest(207, 1925, 20, 36));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(338, 52, 39, 35));
			getWalkLeft()[1] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(337, 198, 39, 33));
			getWalkLeft()[2] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(337, 342, 39, 33));
			getWalkLeft()[3] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(335, 487, 39, 32));

			getWalkRight()[0] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(57, 52, 39, 35));
			getWalkRight()[1] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(58, 198, 39, 33));
			getWalkRight()[2] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(58, 342, 39, 33));
			getWalkRight()[3] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(60, 487, 39, 32));

			getWalkUp()[0] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(495, 52, 21, 35));
			getWalkUp()[1] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(495, 198, 21, 33));
			getWalkUp()[2] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(495, 342, 21, 33));
			getWalkUp()[3] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(495, 488, 22, 31));

			getWalkDown()[0] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(207, 53, 20, 36));
			getWalkDown()[1] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(207, 199, 20, 34));
			getWalkDown()[2] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(207, 343, 20, 34));
			getWalkDown()[3] = new RPGSprite("other/RPGBleu", 1f, 1f, this, new RegionOfInterest(207, 488, 20, 33));

			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.RIGHT);

		} else if (faction == Faction.ENEMY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(338, 52, 39, 35)));
			setSpriteRight(new Sprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(57, 52, 39, 35)));
			setSpriteUp(new Sprite("other/RPGRouge", 0.66f, 1f, this, new RegionOfInterest(495, 52, 21, 35), new Vector(0.2f, 0f)));
			setSpriteDown(new Sprite("other/RPGRouge", 0.66f, 1f, this, new RegionOfInterest(207, 53, 20, 36), new Vector(0.2f, 0f)));

			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/RPGRouge", 2f, 1f, this,
					new RegionOfInterest(321, 631, 66, 32), new Vector(-1f, 0f));
			getAttackSpritesLeft()[1] = new RPGSprite("other/RPGRouge", 2.5f, 1f, this,
					new RegionOfInterest(309, 775, 79, 32), new Vector(-1.25f, 0f));
			getAttackSpritesLeft()[2] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(295, 917, 100, 34), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[3] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(295, 1061, 103, 34), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[4] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(292, 1201, 103, 38), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[5] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(292, 1346, 103, 37), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[6] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(292, 1494, 103, 33), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[7] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(292, 1636, 103, 35), new Vector(-1.5f, 0f));
			getAttackSpritesLeft()[8] = new RPGSprite("other/RPGRouge", 1f, 1f, this,
					new RegionOfInterest(338, 1780, 39, 35), new Vector(0f, 0f));
			getAttackSpritesLeft()[9] = new RPGSprite("other/RPGRouge", 1f, 1f, this,
					new RegionOfInterest(338, 1924, 39, 35), new Vector(0f, 0f));

			getAttackSpritesRight()[0] = new RPGSprite("other/RPGRouge", 2f, 1f, this,
					new RegionOfInterest(47, 631, 66, 32), new Vector(-1f, 0f));
			getAttackSpritesRight()[1] = new RPGSprite("other/RPGRouge", 2.5f, 1f, this,
					new RegionOfInterest(46, 775, 79, 32), new Vector(-1.25f, 0f));
			getAttackSpritesRight()[2] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(39, 917, 100, 34), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[3] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(36, 1061, 103, 34), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[4] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(39, 1201, 103, 38), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[5] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(38, 1346, 103, 37), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[6] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(38, 1494, 103, 33), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[7] = new RPGSprite("other/RPGRouge", 3f, 1f, this,
					new RegionOfInterest(38, 1636, 103, 35), new Vector(-1.5f, 0f));
			getAttackSpritesRight()[8] = new RPGSprite("other/RPGRouge", 1f, 1f, this,
					new RegionOfInterest(57, 1780, 39, 35), new Vector(0f, 0f));
			getAttackSpritesRight()[9] = new RPGSprite("other/RPGRouge", 1f, 1f, this,
					new RegionOfInterest(57, 1924, 39, 35), new Vector(0f, 0f));

			getAttackSpritesUp()[0] = new RPGSprite("other/RPGRouge", 1f, 2f, this,
					new RegionOfInterest(495, 621, 27, 42), new Vector(0f, 0f));
			getAttackSpritesUp()[1] = new RPGSprite("other/RPGRouge", 1f, 2f, this,
					new RegionOfInterest(495, 751, 28, 57), new Vector(0f, 0f));
			getAttackSpritesUp()[2] = new RPGSprite("other/RPGRouge", 1f, 2f, this,
					new RegionOfInterest(495, 881, 30, 74), new Vector(0f, 0f));
			getAttackSpritesUp()[3] = new RPGSprite("other/RPGRouge", 1f, 1.2f, this,
					new RegionOfInterest(495, 1023, 32, 77), new Vector(0f, 0f));
			getAttackSpritesUp()[4] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(495, 1163, 32, 83), new Vector(0f, -0.5f));
			getAttackSpritesUp()[5] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(495, 1350, 30, 83), new Vector(0f, -2f));
			getAttackSpritesUp()[6] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(495, 1494, 30, 83), new Vector(0f, -1.6f));
			getAttackSpritesUp()[7] = new RPGSprite("other/RPGRouge", 1f, 1.2f, this,
					new RegionOfInterest(495, 1636, 30, 35), new Vector(0f, 0f));
			getAttackSpritesUp()[8] = new RPGSprite("other/RPGRouge", 0.66f, 1f, this,
					new RegionOfInterest(495, 1780, 21, 35), new Vector(0f, 0f));
			getAttackSpritesUp()[9] = new RPGSprite("other/RPGRouge", 0.66f, 1f, this,
					new RegionOfInterest(495, 1924, 21, 35), new Vector(0f, 0f));

			getAttackSpritesDown()[0] = new RPGSprite("other/RPGRouge", 1f, 2f, this,
					new RegionOfInterest(207, 632, 22, 33));
			getAttackSpritesDown()[1] = new RPGSprite("other/RPGRouge", 1, 2f, this,
					new RegionOfInterest(207, 776, 24, 41));
			getAttackSpritesDown()[2] = new RPGSprite("other/RPGRouge", 1f, 2f, this,
					new RegionOfInterest(207, 920, 25, 45));
			getAttackSpritesDown()[3] = new RPGSprite("other/RPGRouge", 1f, 1.2f, this,
					new RegionOfInterest(207, 1063, 26, 54));
			getAttackSpritesDown()[4] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(207, 1206, 26, 70), new Vector(0f, -0.7f));
			getAttackSpritesDown()[5] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(207, 1351, 25, 70), new Vector(0f, -2f));
			getAttackSpritesDown()[6] = new RPGSprite("other/RPGRouge", 1f, 3f, this,
					new RegionOfInterest(207, 1493, 24, 70), new Vector(0f, -1.6f));
			getAttackSpritesDown()[7] = new RPGSprite("other/RPGRouge", 1f, 1.2f, this,
					new RegionOfInterest(207, 1637, 21, 36));
			getAttackSpritesDown()[8] = new RPGSprite("other/RPGRouge", 0.66f, 1f, this,
					new RegionOfInterest(207, 1781, 20, 36));
			getAttackSpritesDown()[9] = new RPGSprite("other/RPGRouge", 0.66f, 1f, this,
					new RegionOfInterest(207, 1925, 20, 36));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(338, 52, 39, 35));
			getWalkLeft()[1] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(337, 198, 39, 33));
			getWalkLeft()[2] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(337, 342, 39, 33));
			getWalkLeft()[3] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(335, 487, 39, 32));

			getWalkRight()[0] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(57, 52, 39, 35));
			getWalkRight()[1] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(58, 198, 39, 33));
			getWalkRight()[2] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(58, 342, 39, 33));
			getWalkRight()[3] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(60, 487, 39, 32));

			getWalkUp()[0] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(495, 52, 21, 35));
			getWalkUp()[1] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(495, 198, 21, 33));
			getWalkUp()[2] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(495, 342, 21, 33));
			getWalkUp()[3] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(495, 488, 22, 31));

			getWalkDown()[0] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(207, 53, 20, 36));
			getWalkDown()[1] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(207, 199, 20, 34));
			getWalkDown()[2] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(207, 343, 20, 34));
			getWalkDown()[3] = new RPGSprite("other/RPGRouge", 1f, 1f, this, new RegionOfInterest(207, 488, 20, 33));

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
		return 12;
	}

	@Override
	public int getMaxHp() {
		return 6;
	}

	@Override
	public List<Action> getActions() {
		return Arrays.asList(new Wait(this, getOwnerArea()), new Attack(this, getOwnerArea()),
				new Capture(this, getOwnerArea()));
	}

	@Override
	public int getSpeed() {
		return 8;
	}
}