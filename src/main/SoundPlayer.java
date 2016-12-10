package main;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {

	static {
		new JFXPanel();
	}
	
	private MediaPlayer sound;
	private String path;
	
	public SoundPlayer(String path) {
		this.path = path;
		init(path);
	}
	
	private void init(String path){
		File soundFile = new File(path);
		if (!soundFile.exists()) {
			System.err.println("Could not load '" + path + "' sound!");
			return;
		}
		try {
		sound = new MediaPlayer(new Media(soundFile.toURI().toString()));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void play() {
		new Thread() {
			public void run() {
				sound.stop();
				sound.setCycleCount(0);
				sound.play();
			}
		}.start();
	}
	
	public void loop() {
		new Thread() {
			public void run() {
				sound.stop();
				sound.setCycleCount(MediaPlayer.INDEFINITE);
				sound.play();
			}
		}.start();
	}
	
	public void stop() {
		sound.stop();
	}
	
	public void setVolume(double value) {
		sound.setVolume(value);
	}
	
}