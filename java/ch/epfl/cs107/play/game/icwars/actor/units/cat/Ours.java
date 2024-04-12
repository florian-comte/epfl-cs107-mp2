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

public class Ours extends Cat {
	public Ours(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "Joe");
		setHp(getMaxHp());
		
		setAttackSprites(new RPGSprite[4]);
		setAttackSpritesLeft(new RPGSprite[4]);
		setAttackSpritesRight(new RPGSprite[4]);
		setAttackSpritesUp(new RPGSprite[4]);
		setAttackSpritesDown(new RPGSprite[4]);

		setWalkLeft(new RPGSprite[6]);
		setWalkRight(new RPGSprite[6]);
		setWalkUp(new RPGSprite[6]);
		setWalkDown(new RPGSprite[6]);
		
		// Init animation of unit by his faction/orientation

		if (faction == Faction.ALLY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2195, 59, 88, 146)));
			setSpriteDown(new Sprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2195, 59, 88, 146)));
			setSpriteRight(new Sprite("other/AllyOursRight", 1.5f, 3f, this, new RegionOfInterest(1890, 59, 88, 146)));
			setSpriteUp(new Sprite("other/AllyOursRight", 1.5f, 3f, this, new RegionOfInterest(1890, 59, 88, 146)));

			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(2197, 433, 92, 202));
			getAttackSpritesLeft()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(2098, 433, 97, 202));
			getAttackSpritesLeft()[2] = new RPGSprite("other/AllyOursLeft", 2.5f, 3.5f, this,
					new RegionOfInterest(1938, 443, 159, 196));
			getAttackSpritesLeft()[3] = new RPGSprite("other/AllyOursLeft", 2.5f, 3f, this,
					new RegionOfInterest(1784, 504, 127, 131));
			
			getAttackSpritesDown()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(2197, 433, 92, 202));
			getAttackSpritesDown()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(2098, 433, 97, 202));
			getAttackSpritesDown()[2] = new RPGSprite("other/AllyOursLeft", 2.5f, 3.5f, this,
					new RegionOfInterest(1938, 443, 159, 196));
			getAttackSpritesDown()[3] = new RPGSprite("other/AllyOursLeft", 2.5f, 3f, this,
					new RegionOfInterest(1784, 504, 127, 131));

			getAttackSpritesRight()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(1884, 433, 92, 202));
			getAttackSpritesRight()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(1978, 433, 97, 202));
			getAttackSpritesRight()[2] = new RPGSprite("other/AllyOursRight", 2.5f, 3.5f, this,
					new RegionOfInterest(2076, 443, 159, 196));
			getAttackSpritesRight()[3] = new RPGSprite("other/AllyOursRight", 2.5f, 3f, this,
					new RegionOfInterest(2262, 504, 127, 131));
			
			getAttackSpritesUp()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(1884, 433, 92, 202));
			getAttackSpritesUp()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(1978, 433, 97, 202));
			getAttackSpritesUp()[2] = new RPGSprite("other/AllyOursRight", 2.5f, 3.5f, this,
					new RegionOfInterest(2076, 443, 159, 196));
			getAttackSpritesUp()[3] = new RPGSprite("other/AllyOursRight", 2.5f, 3f, this,
					new RegionOfInterest(2262, 504, 127, 131));


			// WALKING SPRITES/ANIMATION L/R


			getWalkLeft()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2195, 250, 88, 145));
			getWalkLeft()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2095, 250, 88, 145));
			getWalkLeft()[2] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1995, 249, 88, 146));
			getWalkLeft()[3] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1895, 251, 88, 144));
			getWalkLeft()[4] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1795, 250, 88, 145));
			getWalkLeft()[5] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1695, 249, 88, 146));
			
			getWalkDown()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2195, 250, 88, 145));
			getWalkDown()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2095, 250, 88, 145));
			getWalkDown()[2] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1995, 249, 88, 146));
			getWalkDown()[3] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1895, 251, 88, 144));
			getWalkDown()[4] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1795, 250, 88, 145));
			getWalkDown()[5] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(1695, 249, 88, 146));

			getWalkRight()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1890, 250, 88, 145));
			getWalkRight()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1990, 250, 88, 145));
			getWalkRight()[2] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2090, 249, 88, 146));
			getWalkRight()[3] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2190, 251, 88, 144));
			getWalkRight()[4] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2290, 250, 88, 145));
			getWalkRight()[5] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2390, 249, 88, 146));
			
			getWalkUp()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1890, 250, 88, 145));
			getWalkUp()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1990, 250, 88, 145));
			getWalkUp()[2] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2090, 249, 88, 146));
			getWalkUp()[3] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2190, 251, 88, 144));
			getWalkUp()[4] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2290, 250, 88, 145));
			getWalkUp()[5] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(2390, 249, 88, 146));
			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.RIGHT);

		} else if (faction == Faction.ENEMY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3435, 60, 88, 144)));
			setSpriteRight(new Sprite("other/AllyOursRight", 1.5f, 3f, this, new RegionOfInterest(650, 60, 88, 144)));
			setSpriteDown(new Sprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3435, 60, 88, 144)));
			setSpriteUp(new Sprite("other/AllyOursRight", 1.5f, 3f, this, new RegionOfInterest(650, 60, 88, 144)));

			// ATTACK SPRITES/ANIMATION L/R
			getAttackSpritesLeft()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(3337, 435, 95, 200));
			getAttackSpritesLeft()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(3242, 435, 95, 200));
			getAttackSpritesLeft()[2] = new RPGSprite("other/AllyOursLeft", 2.5f, 3.5f, this,
					new RegionOfInterest(3172, 451, 154, 183));
			getAttackSpritesLeft()[3] = new RPGSprite("other/AllyOursLeft", 2.5f, 3f, this,
					new RegionOfInterest(3023, 504, 127, 130));
			
			getAttackSpritesDown()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(3337, 435, 95, 200));
			getAttackSpritesDown()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 4.5f, this,
					new RegionOfInterest(3242, 435, 95, 200));
			getAttackSpritesDown()[2] = new RPGSprite("other/AllyOursLeft", 2.5f, 3.5f, this,
					new RegionOfInterest(3172, 451, 154, 183));
			getAttackSpritesDown()[3] = new RPGSprite("other/AllyOursLeft", 2.5f, 3f, this,
					new RegionOfInterest(3023, 504, 127, 130));

			getAttackSpritesRight()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(645, 435, 95, 200));
			getAttackSpritesRight()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(740, 435, 95, 200));
			getAttackSpritesRight()[2] = new RPGSprite("other/AllyOursRight", 2.5f, 3.5f, this,
					new RegionOfInterest(847, 451, 154, 183));
			getAttackSpritesRight()[3] = new RPGSprite("other/AllyOursRight", 2.5f, 3f, this,
					new RegionOfInterest(1023, 504, 127, 130));
			
			getAttackSpritesUp()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(645, 435, 95, 200));
			getAttackSpritesUp()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 4.5f, this,
					new RegionOfInterest(740, 435, 95, 200));
			getAttackSpritesUp()[2] = new RPGSprite("other/AllyOursRight", 2.5f, 3.5f, this,
					new RegionOfInterest(847, 451, 154, 183));
			getAttackSpritesUp()[3] = new RPGSprite("other/AllyOursRight", 2.5f, 3f, this,
					new RegionOfInterest(1023, 504, 127, 130));

			// WALKING SPRITES/ANIMATION L/R

			getWalkLeft()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3435, 251, 88, 143));
			getWalkLeft()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3335, 250, 88, 144));
			getWalkLeft()[2] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3235, 249, 88, 145));
			getWalkLeft()[3] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3135, 251, 88, 143));
			getWalkLeft()[4] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3035, 250, 88, 144));
			getWalkLeft()[5] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2935, 249, 88, 145));

			getWalkDown()[0] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3435, 251, 88, 143));
			getWalkDown()[1] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3335, 250, 88, 144));
			getWalkDown()[2] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3235, 249, 88, 145));
			getWalkDown()[3] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3135, 251, 88, 143));
			getWalkDown()[4] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(3035, 250, 88, 144));
			getWalkDown()[5] = new RPGSprite("other/AllyOursLeft", 1.5f, 3f, this, new RegionOfInterest(2935, 249, 88, 145));

			
			getWalkRight()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(650, 251, 88, 143));
			getWalkRight()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(750, 250, 88, 144));
			getWalkRight()[2] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(850, 249, 88, 145));
			getWalkRight()[3] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(950, 251, 88, 143));
			getWalkRight()[4] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1050, 250, 88, 144));
			getWalkRight()[5] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1150, 249, 88, 145));

			getWalkUp()[0] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(650, 251, 88, 143));
			getWalkUp()[1] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(750, 250, 88, 144));
			getWalkUp()[2] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(850, 249, 88, 145));
			getWalkUp()[3] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(950, 251, 88, 143));
			getWalkUp()[4] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1050, 250, 88, 144));
			getWalkUp()[5] = new RPGSprite("other/AllyOursRight", 1.5f, 3f, this,
					new RegionOfInterest(1150, 249, 88, 145));
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.LEFT);
		}
	}
	
	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public int getRadius() {
		return 4;
	}

	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public int getMaxHp() {
		return 8;
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