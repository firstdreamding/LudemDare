package menu;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import graphics.Texture;
import main.Main;

public class CreditMenu extends Menu{
	Texture bg;
	int selected;
	public CreditMenu(){
		bg = new Texture("/res/sprites/credits.png", 960, 640);
		selected = 0;
	}
	public void update(){
		if (InputHandler.isKeyTyped(KeyEvent.VK_A)){
			Main.getInstance().start = new StartMenu(false);
		} 
	}
	

	public void render(Screen screen){
		screen.drawTexture(0,0,bg);
		screen.drawString("Press 'A' Key to go back", 600, 300);
	}
}
