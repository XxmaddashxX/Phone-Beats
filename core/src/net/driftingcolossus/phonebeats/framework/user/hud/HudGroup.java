package net.driftingcolossus.phonebeats.framework.user.hud;

import java.util.ArrayList;
import java.util.Iterator;

public class HudGroup {

	private final ArrayList<HudComponent> group_components = new ArrayList<HudComponent>();
	private boolean group_isVisible;

	private String group_name;

	public HudGroup(String name){
		group_name = name;
	}

	public final void addComponent(HudComponent component) {
		this.group_components.add(component);
	}

	public final void removeComponent(HudComponent component) {
		this.group_components.remove(component);
	}

	public final HudComponent[] getComponents() {
		HudComponent[] arr = new HudComponent[group_components.size()];
		for(int i = 0; i < group_components.size(); i++){
			arr[i] = group_components.get(i);
		}
		return arr;
	}

	public final void show() {
		for(HudComponent comp: group_components){
			comp.show();
		}
	}

	public final void hide() {
		for(HudComponent comp: group_components){
			comp.hide();
		}
	}
	public final String getGroupName(){
		return group_name;
	}
	public final void setAllResize(boolean resize){
		for(HudComponent component: group_components){
			component.switchResizeState(resize);
		}
	}

}
