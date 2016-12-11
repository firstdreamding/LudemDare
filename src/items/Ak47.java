package items;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import main.Main;
import main.SoundPlayer;

public class Ak47 extends Weapon {
	public Ak47(int id) {
		super(id, "Ak47", "A modern technology of goodness");
		damage = 5;
		cooldown = 15;
		clipSize = 20;
		knock = 4;
		reload = 180;
		reloadSound = new SoundPlayer("reload.wav");
		reloadSound.setVolume(0.3);
		clip = clipSize;
		isReloading = false;
		playerHoldingState = 2;
	}

	public void use(Entity entity, int xvel, int yvel) {
		if (!isReloading || Main.getInstance().tick - startReload > reload) {
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
		}
	}
}
