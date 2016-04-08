package net.driftingcolossus.phonebeats.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;
import net.driftingcolossus.phonebeats.PhoneBeats;
import net.driftingcolossus.phonebeats.framework.DeviceType;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new PhoneBeats(DeviceType.Android), config);
	}
}
