package net.driftingcolossus.phonebeats.framework.user.hud;

import java.util.ArrayList;

public class HudRegistry {

	private static final ArrayList<HudComponent> registry_components = new ArrayList<HudComponent>();

    protected static void registerHudComponent(HudComponent component) {
        registry_components.add(component);
    }
	
}
