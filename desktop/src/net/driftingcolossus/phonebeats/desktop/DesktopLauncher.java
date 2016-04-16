package net.driftingcolossus.phonebeats.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.driftingcolossus.phonebeats.PhoneBeats;
import net.driftingcolossus.phonebeats.framework.DeviceType;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Display display = new Display();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1000;
		config.height = 800;
		if(arg.length > 0 && arg[0].equals("devMode")){
			new LwjglApplication(new PhoneBeats(DeviceType.Development), config);
		}
		else{
			new LwjglApplication(new PhoneBeats(DeviceType.Desktop), config);
		}
	}
}
