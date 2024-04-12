package ch.epfl.cs107.play.game.icwars.area;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class MenuArea extends Area implements Graphics {
	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public final float getCameraScaleFactor() {
		return 8;
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			setBehavior(new ICWarsBehavior(window, "icwars/Menu"));
			registerActor(new Background(this));
			return true;
		}
		return false;
	}
}