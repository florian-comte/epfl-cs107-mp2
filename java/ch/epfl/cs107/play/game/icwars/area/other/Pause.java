package ch.epfl.cs107.play.game.icwars.area.other;

import java.awt.Color;

import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.game.icwars.area.MenuArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Pause extends MenuArea {
	private Text music;
	private Text sound;

	/*
	 * OVERRIDE METHODS
	 */
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			music = new Text("ON", new DiscreteCoordinates(7, 2), this, false, 0.5f, Color.GREEN);
			sound = new Text("ON", new DiscreteCoordinates(7, 1), this, false, 0.5f, Color.GREEN);

			registerActor(music);
			registerActor(sound);

			return true;
		}
		return false;
	}

	@Override
	public String getTitle() {
		return "icwars/Pause";
	}

	@Override
	public void draw(Canvas canvas) {
		music.draw(canvas);
		sound.draw(canvas);
	}

	/*
	 * USEFUL METHODS
	 */
	
	// Set the text of the sound of the pause menu
	public void setSound(boolean soundBool) {
		if (soundBool) {
			sound.setText("ON");
			sound.setColor(Color.GREEN);
		} else {
			sound.setText("OFF");
			sound.setColor(Color.RED);
		}
	}
	
	// Set the text of the music of the pause menu
	public void setMusic(boolean musicBool) {
		if (musicBool) {
			music.setText("ON");
			music.setColor(Color.GREEN);
		} else {
			music.setText("OFF");
			music.setColor(Color.RED);
		}
	}
}