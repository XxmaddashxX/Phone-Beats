package net.driftingcolossus.phonebeats.framework.graphics;

import net.driftingcolossus.phonebeats.framework.user.hud.HudGroup;
import net.driftingcolossus.phonebeats.framework.user.hud.HudTexturePane;

public class UI {

	public static HudGroup newMainMenuScene(){
		HudGroup group = new HudGroup("Main Menu Scene");
		group.addComponent(new HudTexturePane("Logo", 402, 466.5f,368,107, Textures.getTexture("placeholder_logo"), null));
		group.addComponent(new HudTexturePane("Play", 165, 238, Textures.getTexture("placeholder_play"), null));
		group.addComponent(new HudTexturePane("Store", 165, 378, Textures.getTexture("placeholder_store"), null));
		group.addComponent(new HudTexturePane("Settings", 165, 113, Textures.getTexture("placeholder_settings"), null));
		return group;
	}
	
}
