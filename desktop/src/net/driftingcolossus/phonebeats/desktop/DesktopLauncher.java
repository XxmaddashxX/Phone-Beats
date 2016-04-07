package net.driftingcolossus.phonebeats.desktop;

import org.eclipse.swt.widgets.Display;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.driftingcolossus.phonebeats.PhoneBeats;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Display display = new Display();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1000;
		config.height = 800;
		new LwjglApplication(new PhoneBeats(), config);
	}
}
