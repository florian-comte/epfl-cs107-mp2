package ch.epfl.cs107.play.game.icwars.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.other.City;
import ch.epfl.cs107.play.game.icwars.handler.ICWarInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class ICWarsBehavior extends AreaBehavior {
	public enum ICWarsCellType {
//		NONE(0, 0), ROAD(-16777216, 0), PLAIN(-14112955, 1), WOOD(-65536, 3), RIVER(-16776961, 0), MOUNTAIN(-256, 4),
//		CITY(-1, 2);
		
		NONE(0, 0),
		ROAD( -16645630, 0),
		PLAIN( -14576072, 1),
		WOOD( -7798784, 3),
		RIVER( -13158519, 0),
		MOUNTAIN( -7895256, 4),
		CITY(-10068643, 2);

		private final int type;
		private final int defense;

		ICWarsCellType(int type, int defense) {
			this.type = type;
			this.defense = defense;
		}
		
		/*
		 * GETTERS AND SETTERS
		 */
		
		public int getDefenseStar() {
			return defense;
		}
		
		/*
		 * USEFUL METHODS
		 */

		public String typeToString() {
			return name();
		}

		public static ICWarsCellType toType(int type) {
			for (ICWarsCellType ict : ICWarsCellType.values()) {
				if (ict.type == type)
					return ict;
			}
			return NONE;
		}
	}

	/**
	 * Default Tuto2Behavior Constructor
	 * 
	 * @param window (Window), not null
	 * @param name   (String): Name of the Behavior, not null
	 */
	public ICWarsBehavior(Window window, String name) {
		super(window, name);
		int height = getHeight();
		int width = getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				ICWarsCellType color = ICWarsCellType.toType(getRGB(height - 1 - y, x));
				setCell(x, y, new ICWarsCell(x, y, color));
			}
		}
	}
	
	/*
	 * USEFUL METHODS
	 */

	/*
	 * Register defaults actors in the area
	 */
	public void registerActors(Area area) {
		// Register all cities by browsing the area
		for (int y = 0; y < area.getHeight(); y++) {
			for (int x = 0; x < area.getWidth(); x++) {
				ICWarsCell cell = (ICWarsCell) getCell(x, y);
				if (cell.getType() == ICWarsCellType.CITY) {
					City city = new City(area, new DiscreteCoordinates(x, y), Faction.NEUTRAL);
					area.registerActor(city);
					
					((ICWarsArea) area).addCity(city);
				}
			}
		}
	}

	public class ICWarsCell extends AreaBehavior.Cell {
		/// Type of the cell following the enum
		private final ICWarsCellType type;

		/**
		 * ICWarsCell constructor
		 * 
		 * @param x    (int): x coordinate of the cell
		 * @param y    (int): y coordinate of the cell
		 * @param type (EnigmeCellType), not null
		 */
		public ICWarsCell(int x, int y, ICWarsCellType type) {
			super(x, y);
			this.type = type;
		}
		
		/*
		 * OVERRIDE METHODS
		 */

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			for (Interactable ent : super.entities) {
				if (ent.takeCellSpace() && entity.takeCellSpace())
					return false;
			}
			return true;
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
		
		/*
		 * GETTERS AND SETTERS
		 */

		public ICWarsCellType getType() {
			return type;
		}
	}
}