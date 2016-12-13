package menu;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import graphics.Texture;
import main.Main;
import main.SoundPlayer;

public class DeathMenu extends Menu{
	Texture bg;
	SoundPlayer soundPlayer;
	public DeathMenu(){
		bg = new Texture("/res/sprites/ded.png", 960, 640);
		soundPlayer = new SoundPlayer("dealth.wav");
		soundPlayer.loop();
	}
	
	public void render(Screen screen){
		screen.drawTexture(0, 0, bg);
		
	}
	public void update(){
		if (InputHandler.isKeyTyped(KeyEvent.VK_B)){
			soundPlayer.stop();
			Main.getInstance().init();
			
		} 
	}
}
