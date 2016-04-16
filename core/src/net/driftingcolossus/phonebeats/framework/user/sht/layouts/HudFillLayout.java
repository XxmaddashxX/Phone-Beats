package net.driftingcolossus.phonebeats.framework.user.sht.layouts;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudLayout;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;

public class HudFillLayout extends HudLayout {

	
	
	public HudFillLayout(HudComposite composite, String style) {
		super(composite);
	}

	@Override
	public void onWidgetAdded(HudWidget widget) {
		HudWidget[] widgets = getComposite().getWidgets();
		if(widgets == null || widgets.length < 1){

		}
	}
	
	

}
