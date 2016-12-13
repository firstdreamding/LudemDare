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
	Font waveFont;
	Font waveFontSmall;
	AnimationController animationController;
	int wave = 0;
	boolean musicOn;
	double width;
	public SoundPlayer soundplayer;
	public String waveText = "";

	public Level() {
		bg = new Texture("/res/sprites/bg.png", 960, 540);
		coin = new Texture("/res/sprites/coin.png", 40, 40);
		frame = new Texture("/res/sprites/frame.png", 100, 100);

		player = new Player(0, 150, 200, 60, 60, 1);

		ac = new AttackController(player);
		wh = new WaveHandler(player, ac, this);

		/*
		 * Projectile p = new Projectile(10, 70, 20, 20, 10, 0, 10, 2000,
		 * "coin", "Ak.wav"); pc.add(p, 0);
		 */
		width = 0;
		defFont = new Font("Garamond", 1, 50);
		waveFont = new Font("Garamond",1,40);
		musicOn = false;
		waveFontSmall = new Font("Garamond",1,15);
		ac.add(new Hurtbox(player), 0);
		// ac.add(new Hitbox(10, 0, 0, 60, 60, 0, 0, 1000, player, false), 0);
		animationController = new AnimationController();
		animationController.playerState = AnimationController.State.NONE;
		Entity placeHolder = new Entity(0, 0, 0, 0, 0, null);
		Hurtbox placeHolderBox = new Hurtbox(placeHolder, 0, 0, 0, 0);
		ac.add(placeHolderBox, 1);

		soundplayer = new SoundPlayer("bg.wav");
		soundplayer.setVolume(0.25);
	}

	public void render(Screen screen) {
		screen.drawTexture(0, 100, bg);
		screen.drawTexture(player.getX(), player.getY(), player.getTexture());
		ac.render(screen);
		wh.render(screen);
		gui(screen);
	}

	public void update(Screen screen) {
		if (!(musicOn)){
			soundplayer.loop();
			musicOn = true;
		}
		player.update();
		ac.update();
		wh.update();
		animationController.update(player.sheet, player);
		render(screen);
	}

	private int healthBegin = 735;

	public void gui(Screen screen) {
		screen.drawTexture(10, 20, coin);
		screen.drawString(String.valueOf(player.gold), 60, 55, defFont, Color.RED);
		screen.drawTexture(400, 0, frame);
		screen.drawTexture(407, 15, player.weapon.texture);
		screen.fillRect(520, 25, 200, 40, 0xff0000);
		screen.drawRect(520, 25, 200, 40, 0x000000);
		if (!(player.weapon.isReloading)) {
			screen.drawString(player.weapon.clip + "/" + player.weapon.clipSize, 595, 60);
		} else {
			width = (double) ((Main.getInstance().tick - player.weapon.startReload) * (200.0 / player.weapon.reload));
			if (width > 200) {
				width = 200;
			}
			screen.drawString("RELOADING", 545, 60);
			screen.fillRect(520, 25, (int) width, 40, 0x0000ff);
		}
		for (int i = 0; i < 5; i++) {
			screen.drawTexture(healthBegin + (i * 45), 25, player.healthBar.get(i));
		}
		screen.drawString(waveText, 165, 55, waveFont, Color.red);
		if (waveText.equals("Wave Done")){
			screen.drawString("Space for next wave", 165, 85, waveFontSmall, Color.red);
			screen.drawString("B to go to shop", 530, 90, waveFontSmall, Color.red);
		}

	}

	public Player getPlayer() {
		return player;
	}

}
