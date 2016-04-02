package net.driftingcolossus.phonebeats.framework.user.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HudComponent {

	 public static final int COMPONENT_RESIZE_NODE_SIZE = 10;
	    private static final int DEFAULT = 10;
	    private static final int NO_RESIZE = 11;
	    private static final int NO_MOVE = 12;
	    private static final int NO_ROTATE = 13;
	    private static final int NO_CHANGES = 14;
	    private boolean component_inResizeState;
	    private boolean component_isFocused;
	    private boolean component_isVisible;
	    private int component_resizeMode;
	    private float component_x;
	    private float component_y;
	    private float component_width;
	    private float component_height;
	    private HudNode[] component_nodes;
	    
	    private String component_name;
	    private String component_id;

	    public HudComponent(String idName, String componentName) {
	        this.component_x = 0.0f;
	        this.component_y = 0.0f;
	        this.component_inResizeState = true;
	        component_id = idName;
	        component_name = componentName;
	        this.initiailize();
	    }

	    public HudComponent(String idName, String componentName, float x, float y) {
	        this.component_x = x;
	        this.component_y = y;
	        this.component_inResizeState = false;
	        component_id = idName;
	        component_name = componentName;
	        this.initiailize();
	    }

	    public HudComponent(String idName, String componentName, float x, float y, float width, float height) {
	        this.component_x = x;
	        this.component_y = y;
	        this.component_width = width;
	        this.component_height = height;
	        this.component_inResizeState = false;
	        component_id = idName;
	        component_name = componentName;
	        this.initiailize();
	    }

	    private void initiailize() {
	        this.component_isVisible = true;
	        this.component_resizeMode = DEFAULT;
	        this.component_nodes = new HudNode[9];
	        this.component_nodes[0] = new HudNode(this, 0);
	        this.component_nodes[1] = new HudNode(this, 1);
	        this.component_nodes[2] = new HudNode(this, 2);
	        this.component_nodes[3] = new HudNode(this, 3);
	        this.component_nodes[4] = new HudNode(this, 4);
	        this.component_nodes[5] = new HudNode(this, 5);
	        this.component_nodes[6] = new HudNode(this, 6);
	        this.component_nodes[7] = new HudNode(this, 7);
	        this.component_nodes[8] = new HudNode(this, 8);
	        HudRegistry.registerHudComponent(this);
	    }

	    public final void render(SpriteBatch spritebatch, ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
	        this.onRenderEvent(spritebatch, fillrenderer, linerenderer);
	    }

	    public final void renderControlPoints(ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
	        if (this.component_inResizeState) {
	            HudNode[] arrhudNode = this.component_nodes;
	            int n = arrhudNode.length;
	            int n2 = 0;
	            while (n2 < n) {
	                HudNode n3 = arrhudNode[n2];
	                if (n3 != null) {
	                    fillrenderer.setColor(Color.RED);
	                    fillrenderer.rect(n3.getX(), n3.getY(), 10.0f, 10.0f);
	                }
	                ++n2;
	            }
	        }
	    }

	    public void onUpdateEvent() {
	    }

	    public void onRenderEvent(SpriteBatch spritebatch, ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
	    }

	    public void onDisposeEvent() {
	   
	    }
	    public void onMouseOver(int componentX, int componentY) {
	   
	    }
	    public void onMouseOff(){	
	   
	    }
	    public void onMouseClick(int button, int componentX, int componentY) {
	    }

	    public void onMouseWheelScroll(int wheelDirection) {
	    }

	    public void onKeyDown(int keyCode) {
	    }

	    public void onKeyHeld(int keyCode) {
	    }

	    public void onKeyUp(int keyCode) {
	    }

	    public void onKeyTyped(char character) {
	    }

	    public void onComponentHiden() {
	    }

	    public void onComponentShown() {
	    }

	    public void onComponentLayered() {
	    }

	    public void onComponentResized(float oldWidth, float oldHeight) {
	    }

	    public void onComponentMoved(float oldX, float oldY) {
	    }

	    public final float getX() {
	        return this.component_x;
	    }

	    public final float getY() {
	        return this.component_y;
	    }

	    public final float getWidth() {
	        return this.component_width;
	    }

	    public final void setWidth(float width) {
	        this.component_width = width;
	    }

	    public final float getHeight() {
	        return this.component_height;
	    }

	    public final void setHeight(float height) {
	        this.component_height = height;
	    }

	    public final boolean isVisible() {
	        return this.component_isVisible;
	    }

	    public final HudNode[] getNodes() {
	        return this.component_nodes;
	    }

	    public final void set(float x, float y) {
	        this.component_x = x;
	        this.component_y = y;
	    }

	    public final void size(float width, float height) {
	        this.component_width = width;
	        this.component_height = height;
	    }

	    public final void show() {
	        this.component_isVisible = true;
	        onComponentShown();
	    }

	    public final void hide() {
	        this.component_isVisible = false;
	        onComponentHiden();
	    }

	    public final boolean isInResizeState() {
	        return this.component_inResizeState;
	    }

	    public final void switchResizeState(boolean inState) {
	        this.component_inResizeState = inState;
	    }
	
	
}
