package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.driftingcolossus.phonebeats.framework.user.sht.layouts.HudAbsoluteLayout;

public class HudComposite extends HudResource{
	
	private HudShell composite_hudShell;
	
	private HudLayout composite_hudLayout;
	
	private ArrayList<HudWidget> composite_widgets;
	
	public HudComposite(HudShell shell, String style){
		super(style);
		composite_hudShell = shell;
		composite_widgets = new ArrayList<HudWidget>();
		composite_hudLayout = new HudAbsoluteLayout(this);
		composite_hudShell.setComposite(this);
	}
	protected void renderWidgets(SpriteBatch batch){
		for(HudWidget widget: composite_widgets){
			widget.render(batch);
		}
	}
	protected final void add(HudWidget widget){
		if(composite_hudLayout != null){
			composite_hudLayout.onWidgetAdded(widget);
		}
		composite_widgets.add(widget);
	}
	public final HudShell getShell(){
		return composite_hudShell;
	}
	public HudLayout getLayout(){
		return composite_hudLayout;
	}
	public void setLayout(HudLayout layout){
		composite_hudLayout = layout;
	}
	public void translateAll(float x, float y){
		for(HudWidget widget: composite_widgets){
			widget.translate(x, y);
		}
	}
	@Override
	public void onDispose() {
		
	}
	public HudWidget[] getWidgets(){
		HudWidget[] comps = new HudWidget[composite_widgets.size()];
		for(int i = 0; i < composite_widgets.size(); i++){
			comps[i] = composite_widgets.get(i);
		}
		return comps;
	}
	
	

}
