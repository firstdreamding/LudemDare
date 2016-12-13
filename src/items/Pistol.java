package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;
import main.SoundPlayer;

public class Pistol extends Weapon {
	public Pistol(int id, boolean isGolden) {
		super(id, "Pistol", "A modern technology of goodness");
		damage = 4;
		cooldown = 30;
		clipSize = 8;
		knock = 1;
		playerHoldingState = 1;
		reload = 60;
		cost = 40;
		clip = clipSize;
		reloadSound = new SoundPlayer("reload.wav");
		reloadSound.setVolume(0.5);
		isReloading = false;
		if(isGolden){
			this.isGolden = true;
			damage = 50;
			cost = 175;
			cooldown = 60;
			playerHoldingState = 2;
			
		}
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!isReloading) {
			if (!(entity instanceof Player))
				return;
			Projectile p = new Projectile(Main.getInstance().level.player.x, Main.getInstance().level.player.y, 20, 20,
					20 * xvel, 20 * yvel, damage, 2000,knock, "bullet", "Ak.wav");
			Main.getInstance().level.ac.add(p, 0);
			clip--;
			if (clip == 0) {
				startReload = Main.getInstance().tick;
				isReloading = true;
				clip = clipSize;
			}
		} else if (Main.getInstance().tick - startReload > reload) {
			reloadSound.play();
			isReloading = false;
		}
	}
}
