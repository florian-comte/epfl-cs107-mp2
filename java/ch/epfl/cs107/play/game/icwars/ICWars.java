package ch.epfl.cs107.play.game.icwars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.icwars.actor.other.Helikopter;
import ch.epfl.cs107.play.game.icwars.actor.players.AIPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer.PlayerState;
import ch.epfl.cs107.play.game.icwars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.icwars.actor.units.ICWarsUnit;
import ch.epfl.cs107.play.game.icwars.actor.units.cat.NormalCat;
import ch.epfl.cs107.play.game.icwars.actor.units.cat.Ours;
import ch.epfl.cs107.play.game.icwars.actor.units.cat.TankCat;
import ch.epfl.cs107.play.game.icwars.actor.units.military.RPG;
import ch.epfl.cs107.play.game.icwars.actor.units.military.Tank;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.area.levels.Level0;
import ch.epfl.cs107.play.game.icwars.area.levels.Level1;
import ch.epfl.cs107.play.game.icwars.area.other.BetTeam;
import ch.epfl.cs107.play.game.icwars.area.other.ChooseTheme;
import ch.epfl.cs107.play.game.icwars.area.other.GameOver;
import ch.epfl.cs107.play.game.icwars.area.other.MainMenu;
import ch.epfl.cs107.play.game.icwars.area.other.Pause;
import ch.epfl.cs107.play.game.icwars.area.other.Results;
import ch.epfl.cs107.play.game.icwars.utils.Faction;
import ch.epfl.cs107.play.game.icwars.utils.SoundsManager;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICWars extends AreaGame {
	public final static float CAMERA_SCALE_FACTOR = 10.f;
	private final String[] areas = { "icwars/MainMenu", "icwars/Map1", "icwars/Map2", "icwars/GameOver" };
	private int areaIndex;

	private ICWarsPlayer currentPlayer;
	private List<ICWarsPlayer> currentWaitingPlayers;
	private List<ICWarsPlayer> haveToPlacePlayers;
	private static List<ICWarsPlayer> players;

	private GameState gameState = GameState.MAIN_MENU;

	private boolean isPlayer1Ai;
	private boolean isPlayer2Ai;
	private int playerPoints1;
	private int playerPoints2;
	private ICWarsPlayer player1;

	private int gameMode;
	private int theme = 0;
	private int bet = 0;

	private boolean music = true;
	private boolean sound = true;
	private boolean gameIsPaused;
	private SoundsManager soundsManager;
	private Helikopter helikopter;

	private float counter = 0.f;
	private boolean counting = false;

	private enum GameState {
		MAIN_MENU, BET_TEAM, CHOOSE_THEME, INIT, SETUP_UNIT, CHOOSE_PLAYER, START_PLAYER_TURN, PLAYER_TURN,
		END_PLAYER_TURN, END_TURN, HELIKOPTER, END, GAME_OVER, RESULTS, PAUSE;
	}

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public void end() {
		System.out.println("Game Over");
		System.exit(0);
	}

	@Override
	public void update(float deltaTime) {
		// handler of pause
		gamePause();

		super.update(deltaTime);
		Keyboard keyboard = getCurrentArea().getKeyboard();
		// play button sound
		pressedButton(keyboard);

		// next area or end
		if (keyboard.get(Keyboard.N).isPressed()) {
			if (gameState == GameState.GAME_OVER || gameIsPaused) {
				end();
			}
			nextArea();
		}

		// reset
		if (keyboard.get(Keyboard.R).isPressed()) {
			reset();
		}

//		if (keyboard.get(Keyboard.U).isReleased()) {
//			((RealPlayer) currentPlayer).selectUnit(1);
//		}

		switch (gameState) {
		case INIT:
			// player vs player
			if (gameMode == 1) {
				isPlayer1Ai = false;
				isPlayer2Ai = false;
				// player vs ai
			} else if (gameMode == 2) {
				isPlayer1Ai = false;
				isPlayer2Ai = true;
				// ai vs ai
			} else if (gameMode == 3) {
				isPlayer1Ai = true;
				isPlayer2Ai = true;
			}
			nextArea();
			break;
		case SETUP_UNIT: {
			// all players placed all units
			if (haveToPlacePlayers.size() <= 0) {
				gameState = GameState.CHOOSE_PLAYER;
			} else {
				// place units of the player
				currentPlayer = haveToPlacePlayers.get(0);
				currentPlayer.setPlayerState(PlayerState.PLACE_UNIT);
				currentPlayer.centerCamera();
				// if all places
				if (((RealPlayer) currentPlayer).hasPlacedUnits()) {
					currentPlayer.setPlayerState(PlayerState.IDLE);
					haveToPlacePlayers.remove(currentPlayer);
				}
			}
			break;
		}
		case CHOOSE_PLAYER: {
			// No more waiting players
			if (currentWaitingPlayers.size() <= 0) {
				gameState = GameState.END_TURN;
				// Set current player playing
			} else {
				currentPlayer = currentWaitingPlayers.get(0);
				getCurrentArea().setViewCandidate(currentPlayer);
				if (currentPlayer.isDefeated()) {
					removePlayer(currentPlayer);
					gameState = GameState.END_PLAYER_TURN;
					break;
				}

				currentWaitingPlayers.remove(0);
				gameState = GameState.START_PLAYER_TURN;
			}
			break;
		}
		case START_PLAYER_TURN: {
			currentPlayer.startTurn();
			gameState = GameState.PLAYER_TURN;
			break;
		}

		case PLAYER_TURN: {
			// player end his turn
			if (currentPlayer.getPlayerState() == PlayerState.IDLE) {
				gameState = GameState.END_PLAYER_TURN;
			} else {
				break;
			}
		}

		case END_PLAYER_TURN: {
			if (players.size() <= 1) {
				gameState = GameState.END;
			}

			currentPlayer.setUnitsAvailable();
			if (currentWaitingPlayers.size() <= 0) {
				gameState = GameState.END_TURN;
			} else {
				gameState = GameState.CHOOSE_PLAYER;
			}

			break;
		}

		case END_TURN: {
			// remove deads players
			((ICWarsArea) getCurrentArea()).checkUnitsCities();
			List<ICWarsPlayer> deadPlayers = new ArrayList<ICWarsPlayer>();
			for (ICWarsPlayer player : players) {
				if (player.isDefeated()) {
					deadPlayers.add(player);
				}
			}

			for (ICWarsPlayer player : deadPlayers) {
				players.remove(player);
			}

			// check if one left
			if (players.size() <= 1) {
				gameState = GameState.END;
			} else {
				currentWaitingPlayers.clear();
				currentWaitingPlayers.addAll(players);
				haveToPlacePlayers.clear();
				gameState = GameState.CHOOSE_PLAYER;

			}
			break;
		}

		case HELIKOPTER:
			// danse units and change orientation 0.6sec
			if (waitFor(0.6f, deltaTime)) {
				((ICWarsArea) getCurrentArea()).danseUnits();
			}
			// skip anim
			if (keyboard.get(Keyboard.ENTER).isPressed()
					|| helikopter.getPosition().x >= getCurrentArea().getWidth() - 1) {
				getCurrentArea().unregisterActor(helikopter);
				helikopter = null;
				nextArea();
				soundsManager.stop("helikopter");
				if (music) {
					startMusic();
				}

			}

			break;
		case END: {
			if (players.size() == 1) {
				if (players.get(0).equals(player1)) {
					playerPoints1++;
				} else {
					playerPoints2++;
				}
			}
			// launch animation helikopter if military theme
			if (theme == 1) {
				helikopter = new Helikopter(getCurrentArea(), new DiscreteCoordinates(0,
						ThreadLocalRandom.current().nextInt(0, getCurrentArea().getHeight())));
				getCurrentArea().registerActor(helikopter);
				stopMusic();
				gameState = GameState.HELIKOPTER;
			} else {
				nextArea();
			}
			break;
		}

		case MAIN_MENU:
			if (keyboard.get(Keyboard.A).isPressed()) {
				gameMode = 1;
			}
			if (keyboard.get(Keyboard.B).isPressed()) {
				gameMode = 2;
			}
			if (keyboard.get(Keyboard.C).isPressed()) {
				gameMode = 3;
			}
			if (gameMode != 0) {
				if (gameMode == 3) {
					setCurrentArea("icwars/BetTeam", true);
					gameState = GameState.BET_TEAM;

				} else {
					setCurrentArea("icwars/ChooseTheme", true);
					gameState = GameState.CHOOSE_THEME;

				}
			}
			break;

		case CHOOSE_THEME:
			if (keyboard.get(Keyboard.A).isPressed()) {
				theme = 1;
			}
			if (keyboard.get(Keyboard.B).isPressed()) {
				theme = 2;
			}
			if (theme != 0) {
				gameState = GameState.INIT;
			}
			break;

		case BET_TEAM:
			if (keyboard.get(Keyboard.A).isPressed()) {
				bet = 1;
			}
			if (keyboard.get(Keyboard.B).isPressed()) {
				bet = 2;
			}
			if (bet != 0) {
				setCurrentArea("icwars/ChooseTheme", true);
				gameState = GameState.CHOOSE_THEME;
			}
			break;

		case GAME_OVER:
			if (keyboard.get(Keyboard.P).isPressed()) {
				setCurrentArea("icwars/Results", true);
				// send results to results screen
				((Results) getCurrentArea()).setPlayersResult(playerPoints1, playerPoints2);
				// if ai vs ai, set bet resutl to results screen
				if (bet != 0) {
					((Results) getCurrentArea()).setBetResult(
							(bet == 1 && playerPoints1 > playerPoints2) || (bet == 2 && playerPoints2 > playerPoints1));
				}
				gameState = GameState.RESULTS;
			}
			break;

		case RESULTS:
			if (((Results) getCurrentArea()).isFinish()) {
				setCurrentArea("icwars/GameOver", true);
				gameState = GameState.GAME_OVER;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public String getTitle() {
		return "ICWars";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			setupMusic(fileSystem);

			createAreas();
			areaIndex = 0;
			setCurrentArea("icwars/MainMenu", true);
			startMusic();
			initArea(areas[areaIndex]);
			return true;
		}
		return false;
	}

	/*
	 * USEFUL METHODS
	 */

	// Init an area by his key
	private void initArea(String areaKey) {
		if (areaIndex == 1 || areaIndex == 2) {
			// Init lists
			currentWaitingPlayers = new ArrayList<ICWarsPlayer>();
			haveToPlacePlayers = new ArrayList<ICWarsPlayer>();
			players = new ArrayList<ICWarsPlayer>();

			ICWarsArea area = (ICWarsArea) setCurrentArea(areaKey, true);

			List<ICWarsUnit> unitsPlayer = new ArrayList<>();
			List<DiscreteCoordinates> randomCoords = getRandomCoords(14, area);

			// If cat theme
			if (theme == 2) {
				ICWarsUnit unit1 = new NormalCat(area, randomCoords.get(0), Faction.ALLY);
				ICWarsUnit unit2 = new TankCat(area, randomCoords.get(1), Faction.ALLY);
				ICWarsUnit unit3 = new Ours(area, randomCoords.get(2), Faction.ALLY);

				unitsPlayer.add(unit1);
				unitsPlayer.add(unit2);
				unitsPlayer.add(unit3);

				// If military theme
			} else {
				ICWarsUnit unit1 = new Tank(area, randomCoords.get(3), Faction.ALLY);
				ICWarsUnit unit2 = new Tank(area, randomCoords.get(4), Faction.ALLY);
				ICWarsUnit unit3 = new RPG(area, randomCoords.get(5), Faction.ALLY);
				ICWarsUnit unit4 = new RPG(area, randomCoords.get(6), Faction.ALLY);

				unitsPlayer.add(unit1);
				unitsPlayer.add(unit2);
				unitsPlayer.add(unit3);
				unitsPlayer.add(unit4);

			}

			List<ICWarsUnit> unitsEnemy = new ArrayList<>();
			// If cat theme

			if (theme == 2) {

				ICWarsUnit unit1 = new NormalCat(area, randomCoords.get(7), Faction.ENEMY);
				ICWarsUnit unit2 = new TankCat(area, randomCoords.get(8), Faction.ENEMY);
				ICWarsUnit unit3 = new Ours(area, randomCoords.get(9), Faction.ENEMY);

				unitsEnemy.add(unit1);
				unitsEnemy.add(unit2);
				unitsEnemy.add(unit3);

				// If military theme
			} else {
				ICWarsUnit unit1 = new RPG(area, randomCoords.get(10), Faction.ENEMY);
				ICWarsUnit unit2 = new Tank(area, randomCoords.get(11), Faction.ENEMY);
				ICWarsUnit unit3 = new Tank(area, randomCoords.get(12), Faction.ENEMY);
				ICWarsUnit unit4 = new RPG(area, randomCoords.get(13), Faction.ENEMY);

				unitsEnemy.add(unit1);
				unitsEnemy.add(unit2);
				unitsEnemy.add(unit3);
				unitsEnemy.add(unit4);

			}

			player1 = initPlayer(area, unitsPlayer, Faction.ALLY, isPlayer1Ai);
			initPlayer(area, unitsEnemy, Faction.ENEMY, isPlayer2Ai);
			// if level 2, swap the two players for equality (not the same player start)
			if (areaIndex == 2) {
				Collections.swap(currentWaitingPlayers, 0, 1);
			}
			gameState = GameState.SETUP_UNIT;
		}
	}

	// Init a player
	private ICWarsPlayer initPlayer(ICWarsArea area, List<ICWarsUnit> unitsPlayer, Faction faction, boolean ai) {
		DiscreteCoordinates coords = (faction == Faction.ALLY) ? area.getPlayerSpawnPosition()
				: area.getEnemySpawnPosition();
		ICWarsPlayer player;
		if (ai) {
			player = new AIPlayer(area, coords, faction, unitsPlayer);
		} else {
			player = new RealPlayer(area, coords, faction, unitsPlayer);
			haveToPlacePlayers.add(player);

		}
		currentWaitingPlayers.add(player);
		players.add(player);

		player.enterArea(area, coords);
		return player;
	}

	// Next area
	private void nextArea() {
		if (gameMode == 0) {
			isPlayer1Ai = false;
			isPlayer2Ai = true;
		}

		if (areas.length > areaIndex + 1) {
			areaIndex++;
			setCurrentArea(areas[areaIndex], true);
			// If next is a level
			if (areaIndex != areas.length - 1) {
				if (areaIndex == 1 || areaIndex == 2) {
					initArea(areas[areaIndex]);
					gameState = GameState.SETUP_UNIT;
					return;
				}
			}
			// Launch game over
			stopMusic();
			soundsManager.play("gameover", false);
		}
		gameState = GameState.GAME_OVER;
	}

	// Reset the game
	private void reset() {
		if (currentPlayer != null) {
			currentPlayer.leaveArea();
		}
		areaIndex = 0;
		setCurrentArea(areas[areaIndex], true);
		gameMode = 0;
		theme = 0;
		bet = 0;
		gameState = GameState.MAIN_MENU;
		playerPoints1 = 0;
		playerPoints2 = 0;
		soundsManager.stop("helikopter");
	}

	// Get number random coords in the area and return in a list
	private List<DiscreteCoordinates> getRandomCoords(int number, Area area) {
		List<DiscreteCoordinates> coords = new ArrayList<DiscreteCoordinates>();
		List<DiscreteCoordinates> result = new ArrayList<DiscreteCoordinates>();
		for (int x = 0; x < area.getWidth(); x++) {
			for (int y = 0; y < area.getHeight(); y++) {
				coords.add(new DiscreteCoordinates(x, y));
			}
		}
		for (int i = 0; i < number; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, (area.getHeight() - 1) * (area.getWidth() - 1));

			DiscreteCoordinates randomCoords = coords.get(randomNum);
			coords.remove(randomNum);
			result.add(randomCoords);
		}
		return result;
	}

	// Check when a button is pressed and make a song
	private void pressedButton(Keyboard keyboard) {
		if (!sound) {
			return;
		}

		if (keyboard.get(Keyboard.A).isPressed() || keyboard.get(Keyboard.B).isPressed()
				|| keyboard.get(Keyboard.C).isPressed() || keyboard.get(Keyboard.N).isPressed()
				|| keyboard.get(Keyboard.R).isPressed() || keyboard.get(Keyboard.ENTER).isPressed()
				|| keyboard.get(Keyboard.TAB).isPressed() || keyboard.get(Keyboard.P).isPressed()
				|| keyboard.get(Keyboard.SPACE).isPressed() || keyboard.get(Keyboard.M).isPressed()
				|| keyboard.get(Keyboard.S).isPressed()) {
			playButtonSound();
		}
	}

	/**
	 * Ensures that value time elapsed before returning true
	 * 
	 * @param dt    elapsed time
	 * @param value waiting time (in seconds )
	 * @return true if value seconds has elapsed , false otherwise
	 */
	private boolean waitFor(float value, float dt) {
		if (counting) {
			counter += dt;
			if (counter > value) {
				counting = false;
				return true;
			}
		} else {
			counter = 0f;
			counting = true;
		}
		return false;
	}

	// Stop the music
	private void stopMusic() {
		soundsManager.stop("music");
	}

	// Start the music
	private void startMusic() {
		soundsManager.play("music", true);
	}

	// Play a sound of button
	private void playButtonSound() {
		soundsManager.play("button", false);
	}

	// Remove player of the game
	private void removePlayer(ICWarsPlayer player) {
		if (currentWaitingPlayers.contains(player)) {
			currentWaitingPlayers.remove(player);
		}

		if (haveToPlacePlayers.contains(player)) {
			haveToPlacePlayers.remove(player);
		}

		if (players.contains(player)) {
			players.remove(player);
		}
	}

	/**
	 * Method how manage the pause of the game
	 */
	private void gamePause() {
		Keyboard keyboard = getWindow().getKeyboard();
		Button spaceButton = keyboard.get(Keyboard.SPACE);

		// Set pause if in level
		if (spaceButton.isPressed() && (areaIndex == 1 || areaIndex == 2)) {
			if (!gameIsPaused) {
				setCurrentArea("icwars/Pause", true);
				((Pause) getCurrentArea()).setMusic(music);
				((Pause) getCurrentArea()).setSound(sound);
			} else {
				setCurrentArea(areas[areaIndex], false);
			}
			gameIsPaused = !gameIsPaused;
		}

		// If in pause
		if (gameIsPaused) {
			// Switch music
			if (keyboard.get(Keyboard.M).isPressed()) {
				if (music) {
					((Pause) getCurrentArea()).setMusic(music ? false : true);
					music = !music;
					stopMusic();
				} else {
					((Pause) getCurrentArea()).setMusic(music ? false : true);
					music = !music;
					startMusic();
				}
			}
			// Switch sounds
			if (keyboard.get(Keyboard.S).isPressed()) {
				((Pause) getCurrentArea()).setSound(sound ? false : true);
				sound = !sound;
			}
		}
	}

	/**
	 * Add all the areas
	 */
	private void createAreas() {
		addArea(new MainMenu());
		addArea(new BetTeam());
		addArea(new ChooseTheme());
		addArea(new Level0(soundsManager));
		addArea(new Level1(soundsManager));
		addArea(new Results());
		addArea(new GameOver());
		addArea(new Pause());
	}

	// Load all musics
	private void setupMusic(FileSystem fileSystem) {
		soundsManager = new SoundsManager();
		soundsManager.load("music", fileSystem);
		soundsManager.load("miaw", fileSystem);
		soundsManager.load("gameover", fileSystem);
		soundsManager.load("button", fileSystem);
		soundsManager.load("explosion", fileSystem);
		soundsManager.load("meteor", fileSystem);
		soundsManager.load("helikopter", fileSystem);
	}
}