package main;

import graphics.Screen;

public class Level {
	public void render(Screen screen){
		
	}
	
	public void update(Screen screen){
		render(screen);
		Main.getInstance().player.update();
	}
	
}
