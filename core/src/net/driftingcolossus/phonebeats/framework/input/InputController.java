package net.driftingcolossus.phonebeats.framework.input;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import net.driftingcolossus.phonebeats.PhoneBeats;
import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.user.hud.HudComponent;
import net.driftingcolossus.phonebeats.framework.user.hud.HudNode;

public class InputController implements InputProcessor {
    
	private final ArrayList<HudComponent> components_moused_over = new ArrayList<HudComponent>();
	
	@Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
    	if(keycode == Keys.S){
    		try {
				PhoneBeats.hud.writeToDesktop();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	if(keycode == Keys.R){
    		PhoneBeats.hud.switchAllToResize(true);
    	}
    	if(keycode == Keys.T){
    		PhoneBeats.hud.switchAllToResize(false);
    	}
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 vec = Screen.Viewport().unproject(new Vector2(screenX, screenY));
        screenX = (int)(vec.x + Screen.Camera_Main().position.x);
        screenY = (int)(vec.y + Screen.Camera_Main().position.y);
        HudComponent[] components = PhoneBeats.hud.getActiveResizeComponents();
        if (components != null) {
            HudComponent[] arrhudComponent = components;
            int n = arrhudComponent.length;
            int n2 = 0;
            while (n2 < n) {
                HudComponent comp = arrhudComponent[n2];
                HudNode[] arrhudNode = comp.getNodes();
                int n3 = arrhudNode.length;
                int n4 = 0;
                while (n4 < n3) {
                    HudNode node = arrhudNode[n4];
                    if (node != null && (float)screenX >= node.getX() + Screen.Camera_Main().position.x && (float)screenX <= node.getX() + Screen.Camera_Main().position.x + 10.0f && (float)screenY >= node.getY() + Screen.Camera_Main().position.y && (float)screenY <= node.getY() + Screen.Camera_Main().position.y + 10.0f) {
                        PhoneBeats.hud.hud_focused_component = comp;
                        PhoneBeats.hud.hud_focused_component_held_node = node;
                        return true;
                    }
                    ++n4;
                }
                ++n2;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        HudComponent[] visible;
        Vector2 vec = Screen.Viewport().unproject(new Vector2(screenX, screenY));
        screenX = (int)(vec.x + Screen.Camera_Main().position.x);
        screenY = (int)(vec.y + Screen.Camera_Main().position.y);
        if (PhoneBeats.hud.hud_focused_component_held_node != null) {
            PhoneBeats.hud.hud_focused_component_held_node = null;
        }
        if ((visible = PhoneBeats.hud.getActiveComponents()) != null) {
            HudComponent[] arrhudComponent = visible;
            int n = arrhudComponent.length;
            int n2 = 0;
            while (n2 < n) {
                HudComponent comp = arrhudComponent[n2];
                if (comp != null && (float)screenX >= comp.getX() + Screen.Camera_Main().position.x && (float)screenX <= comp.getX() + Screen.Camera_Main().position.x + comp.getWidth() && (float)screenY >= comp.getY() + Screen.Camera_Main().position.x && (float)screenY <= comp.getY() + Screen.Camera_Main().position.x + comp.getHeight()) {
                    comp.onMouseClick(pointer, (int)((float)screenX - comp.getX()), (int)((float)screenY - comp.getY()));  
                    return true;
                }
                ++n2;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 vec = Screen.Viewport().unproject(new Vector2(screenX, screenY));
        screenX = (int)vec.x;
        screenY = (int)vec.y;
        if (PhoneBeats.hud.hud_focused_component_held_node != null) {
            HudNode node = PhoneBeats.hud.hud_focused_component_held_node;
            float oldX = node.getComponent().getX();
            float oldY = node.getComponent().getY();
            float oldWidth = node.getComponent().getWidth();
            float oldHeight = node.getComponent().getHeight();
            node.set(screenX, screenY);
            if (node.getType() != 0) {
                node.getComponent().onComponentResized(oldWidth, oldHeight);
            }
            if (node.getType() == 0 || node.getType() == 1 || node.getType() == 4 || node.getType() == 6 || node.getType() == 7) {
                node.getComponent().onComponentMoved(oldX, oldY);
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 vec = Screen.Viewport().unproject(new Vector2(screenX, screenY));
        screenX = (int)vec.x;
        screenY = (int)vec.y;
        int i = 0;
        ArrayList<Integer> needToRemove = new ArrayList<Integer>();
        for(HudComponent component: components_moused_over){
        	if(!(component != null && (float)screenX >= component.getX() + Screen.Camera_Main().position.x && (float)screenX <= component.getX() + Screen.Camera_Main().position.x + component.getWidth() && (float)screenY >= component.getY() + Screen.Camera_Main().position.x && (float)screenY <= component.getY() + Screen.Camera_Main().position.x + component.getHeight())){
        		component.onMouseOff();
        		needToRemove.add(i);
        	}
        	i++;
        }
        for(Integer o: needToRemove){
        	components_moused_over.remove(Integer.valueOf(o));
        }
        HudComponent[] components = PhoneBeats.hud.getActiveComponents();
        if (components != null) {
            HudComponent[] arrhudComponent = components;
            int n = arrhudComponent.length;
            int n2 = 0;
            while (n2 < n) {
                HudComponent comp = arrhudComponent[n2];
                if ((float)screenX >= comp.getX() && (float)screenX <= comp.getX() + comp.getWidth() && (float)screenY >= comp.getY() && (float)screenY <= comp.getY() + comp.getHeight()) {
                    comp.onMouseOver((int)((float)screenX - comp.getX()), (int)((float)screenY - comp.getY()));
                    if(!components_moused_over.contains(comp)){
                    	components_moused_over.add(comp);
                    }   
                }
                ++n2;
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public static Vector2 convert(Vector2 input) {
        float x = input.x * (800.0f / (float)Gdx.graphics.getWidth());
        float y = input.y * (600.0f / (float)Gdx.graphics.getHeight());
        return new Vector2(x, y);
    }

}
