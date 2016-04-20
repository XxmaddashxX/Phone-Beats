package net.driftingcolossus.phonebeats.framework.user.sht.layouts;

import net.driftingcolossus.phonebeats.framework.user.sht.HudLayout;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudImage;

public class HudFillLayout extends HudLayout {

	
	
	public HudFillLayout(String style) {
		
	}

	@Override
	public void onWidgetAdded(HudWidget widget) {
		widget.setWidth(getComposite().getShell().getClientArea().width);
		widget.setHeight(getComposite().getShell().getClientArea().height);
	}
	
	

}
