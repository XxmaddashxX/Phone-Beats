package net.driftingcolossus.phonebeats.framework.user.sht.composites;

import java.util.ArrayList;

import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.layouts.HudAbsoluteLayout;
import net.driftingcolossus.phonebeats.framework.user.sht.layouts.HudLayout;

public class HudComposite {
	
	private HudShell composite_hudShell;
	
	private HudLayout composite_hudLayout;
	
	private ArrayList<HudWidget> composite_widgets;
	
	public HudComposite(HudShell shell, String style){
		composite_hudShell = shell;
		composite_widgets = new ArrayList<HudWidget>();
		composite_hudLayout = new HudAbsoluteLayout();
	}
	
	
	public HudLayout getLayout(){
		return composite_hudLayout;
	}
	public void setLayout(HudLayout layout){
		composite_hudLayout = layout;
	}
	

}
