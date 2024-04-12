package ch.epfl.cs107.play.game.icwars.area.other;

import java.awt.Color;

import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.game.icwars.area.MenuArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Results extends MenuArea {
	private Text playerResult1;
	private Text playerResult2;
	private Text betResult;
	private Text timer;

	private float initTimer = 10 * 24;

	/*
	 * OVERRIDE METHODS
	 */

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			playerResult1 = new Text("1", new DiscreteCoordinates(5, 5), this, false, 0.8f, Color.WHITE);
			playerResult2 = new Text("1", new DiscreteCoordinates(5, 3), this, false, 0.8f, Color.WHITE);
			betResult = new Text("", new DiscreteCoordinates(2, 1), this, false, 0.8f, Color.WHITE);
			timer = new Text("10", new DiscreteCoordinates(7, 7), this, false, 0.8f, Color.WHITE);

			registerActor(playerResult1);
			registerActor(playerResult2);
			registerActor(betResult);
			registerActor(timer);
			return true;
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		initTimer--;
		timer.setText((int) initTimer / 24 + "");
	}

	@Override
	public String getTitle() {
		return "icwars/Results";
	}

	@Override
	public void draw(Canvas canvas) {
		playerResult1.draw(canvas);
		playerResult2.draw(canvas);
		betResult.draw(canvas);
		timer.draw(canvas);
	}

	/*
	 * USEFUL METHODS
	 */
	
	// Set the bet result on the area
	public void setBetResult(boolean bet) {
		betResult.setText(bet ? "You win your bet !" : "You lose your bet !");
	}
	
	// Set the player result on the area
	public void setPlayersResult(int p1, int p2) {
		playerResult1.setText(p1 + "");
		playerResult2.setText(p2 + "");

		initTimer = 10 * 24;
	}

	//Check if the timer is finish
	public boolean isFinish() {
		return initTimer <= 0;
	}
}