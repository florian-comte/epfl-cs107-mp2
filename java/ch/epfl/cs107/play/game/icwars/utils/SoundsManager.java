package ch.epfl.cs107.play.game.icwars.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.io.FileSystem;

public class SoundsManager {
	private HashMap<String, Clip> clips;

	public SoundsManager() {
		clips = new HashMap<String, Clip>();
	}

	// Check if the sound exist
	private boolean existSound(String name) {
		return clips.get(name) != null;
	}

	// Load a sound
	public void load(String name, FileSystem fileSystem) {
		if (existSound(name)) {
			return;
		}

		try {
			InputStream stream = fileSystem.read(ResourcePath.getSound(name));

			// Indicate the input stream is an AudioInputStream
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(stream);
			// Save the format
			AudioFormat audioFormat = audioInputStream.getFormat();
			// Compute and save the byte size of the stream
			int size = (int) (audioFormat.getFrameSize() * audioInputStream.getFrameLength());
			// Create the audio array
			byte[] audio = new byte[size];
			// Compute and save the information
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat, size);

			// Copy the audio stream into the audio array and throw exception on error
			int i = audioInputStream.read(audio, 0, size);
			if (i == -1)
				throw new IOException("AudioInputStream cannot be read");

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioFormat, audio, 0, size);
			clips.put(name, clip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Play a sound by his name
	public void play(String sound, boolean loop) {
		if (!existSound(sound)) {
			return;
		}

		Clip clip = clips.get(sound);
		if (clip == null) {
			return;
		}

		if (clip.isRunning()) {
			clip.stop();
		}

		clip.setFramePosition(0);
		if (loop) {
			clip.setLoopPoints(0, clip.getFrameLength() - 1);
			clip.loop(-1);
		}

		clip.start();
	}

	// Stop a sound by his name
	public void stop(String sound) {
		if (!existSound(sound)) {
			return;
		}
		Clip clip = clips.get(sound);
		if (clip.isRunning()) {
			clip.stop();
		}
	}

	// Resume a sound by his name
	public void resume(String sound) {
		if (!existSound(sound)) {
			return;
		}
		Clip clip = clips.get(sound);
		if (!clip.isRunning()) {
			clip.start();
		}
	}
}