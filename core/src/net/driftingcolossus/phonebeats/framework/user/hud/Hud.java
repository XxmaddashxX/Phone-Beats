package net.driftingcolossus.phonebeats.framework.user.hud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Hud {

	private final ArrayList<HudGroup> hud_hudgroups = new ArrayList<HudGroup>();
    private HudGroup hud_active_group;
    public HudComponent hud_focused_component;
    public HudNode hud_focused_component_held_node;
    private ArrayList<HudGroup> hud_active_groups = new ArrayList<HudGroup>();
    private ArrayList<HudGroup> hud_disabled_groups = new ArrayList<HudGroup>();

    public final void addComponentGroup(HudGroup group) {
        this.hud_hudgroups.add(group);
        this.hud_disabled_groups.add(group);
    }

    public final void removeComponentGroup(HudGroup group) {
        if (this.hud_disabled_groups.contains(group)) {
            this.hud_disabled_groups.remove(group);
        }
        if (this.hud_active_groups.contains(group)) {
            this.hud_active_groups.remove(group);
        }
        this.hud_hudgroups.remove(group);
    }

    public final void render(SpriteBatch batch, ShapeRenderer componentfillrenderer, ShapeRenderer componentlinerenderer) {
        if (this.getActiveComponents() == null) {
            return;
        }
        HudComponent[] arrhudComponent = this.getActiveComponents();
        int n = arrhudComponent.length;
        int n2 = 0;
        while (n2 < n) {
            HudComponent comp = arrhudComponent[n2];
            comp.render(batch, componentfillrenderer, componentlinerenderer);
            ++n2;
        }
    }

    public final void renderControlPoints(ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
        if (this.getActiveResizeComponents() == null) {
            return;
        }
        HudComponent[] arrhudComponent = this.getActiveResizeComponents();
        int n = arrhudComponent.length;
        int n2 = 0;
        while (n2 < n) {
            HudComponent comp = arrhudComponent[n2];
            if (comp.isVisible()) {
                comp.renderControlPoints(fillrenderer, linerenderer);
            }
            ++n2;
        }
    }

    public final void update() {
        if (this.getActiveComponents() == null) {
            return;
        }
        HudComponent[] arrhudComponent = this.getActiveComponents();
        int n = arrhudComponent.length;
        int n2 = 0;
        while (n2 < n) {
            HudComponent comp = arrhudComponent[n2];
            comp.onUpdateEvent();
            ++n2;
        }
    }

    public final HudComponent getFocusedComponent() {
        return this.hud_focused_component;
    }

    public final void setFocusedComponent(HudComponent component) {
        this.hud_focused_component = component;
    }

    public final HudComponent[] getActiveComponents() {
    	ArrayList<HudComponent> components = new ArrayList<HudComponent>(); 
    	for(HudGroup group : hud_active_groups){
    		for(HudComponent com: group.getComponents()){
    			if(com.isVisible()){
    				components.add(com);
    			}
    		}
    	}
    	HudComponent[] arr = new HudComponent[components.size()];
    	for(int i = 0; i < components.size(); i++){
    		arr[i] = components.get(i);
    	}
        return arr;
    }

    public final HudComponent[] getActiveResizeComponents() {
        HudComponent[] arr = getActiveComponents();
        ArrayList<HudComponent> components = new ArrayList<HudComponent>(); 
        for(HudComponent component: arr){
        	if(component.isInResizeState()){
        		components.add(component);
        	}
        }
        HudComponent[] comps = new HudComponent[components.size()];
    	for(int i = 0; i < components.size(); i++){
    		comps[i] = components.get(i);
    	}
        return comps;
    }

    public final void show(HudGroup group) {
        if (group != null) {
            this.hud_disabled_groups.remove(group);
            this.hud_active_groups.add(group);
        }
    }

    public final void hide(HudGroup group) {
        if (group != null) {
            this.hud_active_groups.remove(group);
            this.hud_disabled_groups.add(group);
        }
    }
	
}
