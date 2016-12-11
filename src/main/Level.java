package main;

import java.awt.Color;
import java.awt.Font;

import controller.AnimationController;
import controller.AttackController;
import controller.WaveHandler;
import entities.Entity;
import entities.Hitbox;
import entities.Hurtbox;
import entities.Player;
import graphics.Screen;
import graphics.SpriteSheet;
import graphics.Texture;

public class Level {
	public Player player;
	Texture bg, coin, frame;
	public AttackController ac;
	public WaveHandler wh;
	Font defFont;
	AnimationController animationController;
	int wave = 0;
	SoundPlayer soundplayer;
	public SpriteSheet playerSprites = new SpriteSheet(new Texture("/res/sprites/player.png", 240, 120), 60, 60);

	public Level() {
		bg = new Texture("/res/sprites/bg.png", 960, 540);
		coin = new Texture("/res/sprites/coin.png", 40, 40);
		frame = new Texture("/res/sprites/frame.png", 80, 80);
		player = new Player(0, 150, 200, 60, 60, 1, playerSprites.getTexture(0, 0));

		ac = new AttackController();
		wh = new WaveHandler(player, ac);

		/*
		 * Projectile p = new Projectile(10, 70, 20, 20, 10, 0, 10, 2000,
		 * "coin", "Ak.wav"); pc.add(p, 0);
		 */

		defFont = new Font("Garamond", 1, 50);
		ac.add(new Hurtbox(player), 0);
		ac.add(new Hitbox(10, 0, 0, 60, 60, 0, 0, 1000, player, false), 0);
		animationController = new AnimationController();
		animationController.playerState = AnimationController.State.NONE;
		Entity placeHolder = new Entity(0, 0, 0, 0, 0, null);
		Hurtbox placeHolderBox = new Hurtbox(placeHolder, 0, 0, 0, 0);
		ac.add(placeHolderBox, 1);

		soundplayer = new SoundPlayer("bg.wav");
		soundplayer.setVolume(0.1);
		soundplayer.play();
	}

	public void render(Screen screen) {
		screen.drawTexture(0, 100, bg);
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		ac.render(screen);
		wh.render(screen);
		gui(screen);
	}

	public void update(Screen screen) {
		player.update();
		ac.update();
		wh.update();
		animationController.update(playerSprites, player);
		render(screen);
	}

	public void gui(Screen screen) {
		screen.drawTexture(10, 20, coin);
		screen.drawTexture(400, 8, frame);
		screen.drawString(String.valueOf(player.gold), 60, 55, defFont, Color.RED);
		screen.drawTexture(406, 14, player.weapon.texture);

	}

	public Player getPlayer() {
		return player;
	}

}
