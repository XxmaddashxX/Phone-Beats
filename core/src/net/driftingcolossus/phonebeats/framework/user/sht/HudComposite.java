package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.driftingcolossus.phonebeats.framework.user.sht.layouts.HudAbsoluteLayout;

public class HudComposite extends HudResource{
	
	private HudShell composite_hudShell;
	
	private HudLayout composite_hudLayout;
	
	private ArrayList<HudWidget> composite_widgets;
	
	public HudComposite(HudShell shell, String style){
		composite_hudShell = shell;
		composite_widgets = new ArrayList<HudWidget>();
		composite_hudLayout = new HudAbsoluteLayout();
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
	protected final HudShell getShell(){
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
	

}