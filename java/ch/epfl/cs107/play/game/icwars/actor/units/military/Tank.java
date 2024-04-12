package ch.epfl.cs107.play.game.icwars.actor.units.military;

import java.util.ArrayList;
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

public class Tank extends ICWarsAnimatedUnit {
	private DiscreteCoordinates position;

	public Tank(Area area, DiscreteCoordinates position, Faction faction) {
		super(area, position, faction, "Tank");

		this.position = position;

		setupRange(area, position);
		setHp(getMaxHp());

		setAttackSprites(new RPGSprite[8]);
		setAttackSpritesLeft(new RPGSprite[8]);
		setAttackSpritesRight(new RPGSprite[8]);
		setAttackSpritesUp(new RPGSprite[8]);
		setAttackSpritesDown(new RPGSprite[8]);

		setWalkLeft(new RPGSprite[1]);
		setWalkRight(new RPGSprite[1]);
		setWalkUp(new RPGSprite[1]);
		setWalkDown(new RPGSprite[1]);

		// Init animation of unit by his faction/orientation

		if (faction == Faction.ALLY) {

			// NORMAL SPRITE L/R
			setSpriteLeft(new Sprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(113, 55, 56, 57),new Vector(-0.15f, 0f)));
			setSpriteRight(new Sprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(2, 55, 56, 57),new Vector(-0.15f, 0f)));
			setSpriteUp(new Sprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(169, 57, 56, 54),new Vector(-0.15f, 0f)));
			setSpriteDown(new Sprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(57, 55, 56, 57),new Vector(-0.15f, 0f)));

			// ATTACK SPRITES/ANIMATION L/R

			getAttackSpritesLeft()[0] = new RPGSprite("other/TankBleu", 1.7f, 1.3f, this,
					new RegionOfInterest(315, 43, 73, 55));
			getAttackSpritesLeft()[1] = new RPGSprite("other/TankBleu", 1.75f, 1.3f, this,
					new RegionOfInterest(311, 185, 79, 57));
			getAttackSpritesLeft()[2] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(308, 329, 82, 57));
			getAttackSpritesLeft()[3] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(306, 471, 85, 61));
			getAttackSpritesLeft()[4] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(304, 612, 87, 65));
			getAttackSpritesLeft()[5] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(303, 755, 87, 63));
			getAttackSpritesLeft()[6] = new RPGSprite("other/TankBleu", 1.9f, 1.3f, this,
					new RegionOfInterest(299, 898, 88, 64));
			getAttackSpritesLeft()[7] = new RPGSprite("other/TankBleu", 2f, 1.3f, this,
					new RegionOfInterest(298, 1042, 90, 64));

			getAttackSpritesRight()[0] = new RPGSprite("other/TankBleu", 1.7f, 1.3f, this,
					new RegionOfInterest(46, 43, 73, 55), new Vector(-0.7f, 0f));
			getAttackSpritesRight()[1] = new RPGSprite("other/TankBleu", 1.75f, 1.3f, this,
					new RegionOfInterest(45, 185, 79, 57), new Vector(-0.75f, 0f));
			getAttackSpritesRight()[2] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(44, 329, 82, 57), new Vector(-0.8f, 0f));
			getAttackSpritesRight()[3] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(43, 471, 85, 61), new Vector(-0.8f, 0f));
			getAttackSpritesRight()[4] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(43, 612, 87, 65), new Vector(-0.8f, 0f));
			getAttackSpritesRight()[5] = new RPGSprite("other/TankBleu", 1.8f, 1.3f, this,
					new RegionOfInterest(44, 755, 87, 63), new Vector(-0.8f, 0f));
			getAttackSpritesRight()[6] = new RPGSprite("other/TankBleu", 1.9f, 1.3f, this,
					new RegionOfInterest(46, 898, 88, 64), new Vector(-0.9f, 0f));
			getAttackSpritesRight()[7] = new RPGSprite("other/TankBleu", 2f, 1.3f, this,
					new RegionOfInterest(46, 1042, 90, 64), new Vector(-1f, 0f));


			getAttackSpritesUp()[0] = new RPGSprite("other/TankBleu", 1.3f, 1.55f, this,
					new RegionOfInterest(477, 32, 56, 67), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[1] = new RPGSprite("other/TankBleu", 1.3f, 1.65f, this,
					new RegionOfInterest(477, 172, 56, 72), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[2] = new RPGSprite("other/TankBleu", 1.3f, 1.8f, this,
					new RegionOfInterest(477, 311, 56, 78), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[3] = new RPGSprite("other/TankBleu", 1.3f, 1.8f, this,
					new RegionOfInterest(477, 455, 56, 79), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[4] = new RPGSprite("other/TankBleu", 1.3f, 1.85f, this,
					new RegionOfInterest(477, 595, 56, 83), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[5] = new RPGSprite("other/TankBleu", 1.3f, 1.85f, this,
					new RegionOfInterest(477, 737, 56, 84), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[6] = new RPGSprite("other/TankBleu", 1.3f, 1.9f, this,
					new RegionOfInterest(477, 880, 56, 84), new Vector(-0.15f, 0f));
			getAttackSpritesUp()[7] = new RPGSprite("other/TankBleu", 1.3f, 1.9f, this,
					new RegionOfInterest(477, 1021, 56, 86), new Vector(-0.15f, 0f));


			getAttackSpritesDown()[0] = new RPGSprite("other/TankBleu", 1.3f, 1.55f, this,
					new RegionOfInterest(189, 44, 56, 56), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[1] = new RPGSprite("other/TankBleu", 1.3f, 1.65f, this,
					new RegionOfInterest(189, 187, 56, 57), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[2] = new RPGSprite("other/TankBleu", 1.3f, 1.8f, this,
					new RegionOfInterest(189, 330, 56, 59), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[3] = new RPGSprite("other/TankBleu", 1.3f, 1.8f, this,
					new RegionOfInterest(188, 473, 58, 63), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[4] = new RPGSprite("other/TankBleu", 1.3f, 1.85f, this,
					new RegionOfInterest(187, 617, 60, 66), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[5] = new RPGSprite("other/TankBleu", 1.3f, 1.85f, this,
					new RegionOfInterest(189, 762, 56, 68), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[6] = new RPGSprite("other/TankBleu", 1.3f, 1.9f, this,
					new RegionOfInterest(189, 907, 56, 70), new Vector(-0.15f, 0f));
			getAttackSpritesDown()[7] = new RPGSprite("other/TankBleu", 1.3f, 1.9f, this,
					new RegionOfInterest(189, 1052, 56, 71), new Vector(-0.15f, 0f));


			// WALKING SPRITES/ANIMATION L/R
			
			getWalkLeft()[0] = new RPGSprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(113, 55, 56, 57),new Vector(-0.15f, 0f));

			getWalkRight()[0] = new RPGSprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(2, 55, 56, 57),new Vector(-0.15f, 0f));
			
			getWalkUp()[0] = new RPGSprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(169, 57, 56, 54),new Vector(-0.15f, 0f));
			
			getWalkDown()[0] = new RPGSprite("other/TankBleuMvt", 1.3f, 1.3f, this, new RegionOfInterest(57, 55, 56, 57),new Vector(-0.15f, 0f));

			

			////////////////////////////////////////////////////////////////////////////////////////////////

			this.orientate(Orientation.RIGHT);

		} else if (faction == Faction.ENEMY) {

			// NORMAL SPRITE L/R
						setSpriteLeft(new Sprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(113, 55, 56, 57),new Vector(-0.15f, 0f)));
						setSpriteRight(new Sprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(2, 55, 56, 57),new Vector(-0.15f, 0f)));
						setSpriteUp(new Sprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(169, 57, 56, 54),new Vector(-0.15f, 0f)));
						setSpriteDown(new Sprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(57, 55, 56, 57),new Vector(-0.15f, 0f)));

						// ATTACK SPRITES/ANIMATION L/R

						getAttackSpritesLeft()[0] = new RPGSprite("other/TankRouge", 1.7f, 1.3f, this,
								new RegionOfInterest(315, 43, 73, 55));
						getAttackSpritesLeft()[1] = new RPGSprite("other/TankRouge", 1.75f, 1.3f, this,
								new RegionOfInterest(311, 185, 79, 57));
						getAttackSpritesLeft()[2] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(308, 329, 82, 57));
						getAttackSpritesLeft()[3] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(306, 471, 85, 61));
						getAttackSpritesLeft()[4] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(304, 612, 87, 65));
						getAttackSpritesLeft()[5] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(303, 755, 87, 63));
						getAttackSpritesLeft()[6] = new RPGSprite("other/TankRouge", 1.9f, 1.3f, this,
								new RegionOfInterest(299, 898, 88, 64));
						getAttackSpritesLeft()[7] = new RPGSprite("other/TankRouge", 2f, 1.3f, this,
								new RegionOfInterest(298, 1042, 90, 64));

						getAttackSpritesRight()[0] = new RPGSprite("other/TankRouge", 1.7f, 1.3f, this,
								new RegionOfInterest(46, 43, 73, 55), new Vector(-0.7f, 0f));
						getAttackSpritesRight()[1] = new RPGSprite("other/TankRouge", 1.75f, 1.3f, this,
								new RegionOfInterest(45, 185, 79, 57), new Vector(-0.75f, 0f));
						getAttackSpritesRight()[2] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(44, 329, 82, 57), new Vector(-0.8f, 0f));
						getAttackSpritesRight()[3] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(43, 471, 85, 61), new Vector(-0.8f, 0f));
						getAttackSpritesRight()[4] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(43, 612, 87, 65), new Vector(-0.8f, 0f));
						getAttackSpritesRight()[5] = new RPGSprite("other/TankRouge", 1.8f, 1.3f, this,
								new RegionOfInterest(44, 755, 87, 63), new Vector(-0.8f, 0f));
						getAttackSpritesRight()[6] = new RPGSprite("other/TankRouge", 1.9f, 1.3f, this,
								new RegionOfInterest(46, 898, 88, 64), new Vector(-0.9f, 0f));
						getAttackSpritesRight()[7] = new RPGSprite("other/TankRouge", 2f, 1.3f, this,
								new RegionOfInterest(46, 1042, 90, 64), new Vector(-1f, 0f));


						getAttackSpritesUp()[0] = new RPGSprite("other/TankRouge", 1.3f, 1.55f, this,
								new RegionOfInterest(477, 32, 56, 67), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[1] = new RPGSprite("other/TankRouge", 1.3f, 1.65f, this,
								new RegionOfInterest(477, 172, 56, 72), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[2] = new RPGSprite("other/TankRouge", 1.3f, 1.8f, this,
								new RegionOfInterest(477, 311, 56, 78), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[3] = new RPGSprite("other/TankRouge", 1.3f, 1.8f, this,
								new RegionOfInterest(477, 455, 56, 79), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[4] = new RPGSprite("other/TankRouge", 1.3f, 1.85f, this,
								new RegionOfInterest(477, 595, 56, 83), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[5] = new RPGSprite("other/TankRouge", 1.3f, 1.85f, this,
								new RegionOfInterest(477, 737, 56, 84), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[6] = new RPGSprite("other/TankRouge", 1.3f, 1.9f, this,
								new RegionOfInterest(477, 880, 56, 84), new Vector(-0.15f, 0f));
						getAttackSpritesUp()[7] = new RPGSprite("other/TankRouge", 1.3f, 1.9f, this,
								new RegionOfInterest(477, 1021, 56, 86), new Vector(-0.15f, 0f));


						getAttackSpritesDown()[0] = new RPGSprite("other/TankRouge", 1.3f, 1.55f, this,
								new RegionOfInterest(189, 44, 56, 56), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[1] = new RPGSprite("other/TankRouge", 1.3f, 1.65f, this,
								new RegionOfInterest(189, 187, 56, 57), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[2] = new RPGSprite("other/TankRouge", 1.3f, 1.8f, this,
								new RegionOfInterest(189, 330, 56, 59), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[3] = new RPGSprite("other/TankRouge", 1.3f, 1.8f, this,
								new RegionOfInterest(188, 473, 58, 63), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[4] = new RPGSprite("other/TankRouge", 1.3f, 1.85f, this,
								new RegionOfInterest(187, 617, 60, 66), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[5] = new RPGSprite("other/TankRouge", 1.3f, 1.85f, this,
								new RegionOfInterest(189, 762, 56, 68), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[6] = new RPGSprite("other/TankRouge", 1.3f, 1.9f, this,
								new RegionOfInterest(189, 907, 56, 70), new Vector(-0.15f, 0f));
						getAttackSpritesDown()[7] = new RPGSprite("other/TankRouge", 1.3f, 1.9f, this,
								new RegionOfInterest(189, 1052, 56, 71), new Vector(-0.15f, 0f));


						// WALKING SPRITES/ANIMATION L/R
						
						getWalkLeft()[0] = new RPGSprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(113, 55, 56, 57),new Vector(-0.15f, 0f));

						getWalkRight()[0] = new RPGSprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(2, 55, 56, 57),new Vector(-0.15f, 0f));
						
						getWalkUp()[0] = new RPGSprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(169, 57, 56, 54),new Vector(-0.15f, 0f));
						
						getWalkDown()[0] = new RPGSprite("other/TankRougeMvt", 1.3f, 1.3f, this, new RegionOfInterest(57, 55, 56, 57),new Vector(-0.15f, 0f));

						

						////////////////////////////////////////////////////////////////////////////////////////////////

						this.orientate(Orientation.LEFT);
		}
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> liste = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			if (!OOBx(position.x + 1)) {
				liste.add(new DiscreteCoordinates(position.x + 1, position.y));
			}
			if (!OOBx(position.x - 1)) {
				liste.add(new DiscreteCoordinates(position.x - 1, position.y));
			}
			if (!OOBy(position.y + 1)) {
				liste.add(new DiscreteCoordinates(position.x, position.y + 1));
			}
			if (!OOBy(position.y - 1)) {
				liste.add(new DiscreteCoordinates(position.x, position.y - 1));
			}
		}
		return liste;
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
		return 7;
	}

	@Override
	public int getMaxHp() {
		return 10;
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
