package net.driftingcolossus.phonebeats.framework.user.sht;

public abstract class HudLayout {
	
	private HudComposite parent_composite;
	
	public HudLayout(){
	}
	
	public abstract void onWidgetAdded(HudWidget widget);

	public final HudComposite getComposite(){
		return parent_composite;
	}
	protected final void setComposite(HudComposite composite){
		parent_composite = composite;
	}
	
}
