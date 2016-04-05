package net.driftingcolossus.phonebeats.framework.user.hud;

public class HudNode {

	public static final int NODE_CENTER = 0;
	public static final int NODE_TOP_LEFT = 1;
	public static final int NODE_TOP_CENTER = 2;
	public static final int NODE_TOP_RIGHT = 3;
	public static final int NODE_LEFT = 4;
	public static final int NODE_RIGHT = 5;
	public static final int NODE_BOTTOM_LEFT = 6;
	public static final int NODE_BOTTOM_CENTER = 7;
	public static final int NODE_BOTTOM_RIGHT = 8;
	private HudComponent node_component;
	private float node_x;
	private float node_y;
	private int node_type;

	public HudNode(HudComponent component, int nodeType) {
		this.node_component = component;
		this.node_type = nodeType;
		this.setPositions();
	}

	private void setPositions() {
		switch (this.node_type) {
		case 0: {
			this.node_x = this.node_component.getX() + (this.node_component.getWidth() / 2.0f - 5.0f);
			this.node_y = this.node_component.getHeight() / 2.0f - 5.0f + this.node_component.getY();
			break;
		}
		case 1: {
			this.node_x = this.node_component.getX() - 5.0f;
			this.node_y = this.node_component.getY() + (this.node_component.getHeight() - 5.0f);
			break;
		}
		case 2: {
			this.node_x = this.node_component.getX() + (this.node_component.getWidth() / 2.0f - 5.0f);
			this.node_y = this.node_component.getY() + (this.node_component.getHeight() - 5.0f);
			break;
		}
		case 3: {
			this.node_x = this.node_component.getX() + this.node_component.getWidth() - 5.0f;
			this.node_y = this.node_component.getY() + (this.node_component.getHeight() - 5.0f);
			break;
		}
		case 4: {
			this.node_x = this.node_component.getX() - 5.0f;
			this.node_y = this.node_component.getHeight() / 2.0f - 5.0f + this.node_component.getY();
			break;
		}
		case 5: {
			this.node_x = this.node_component.getX() + this.node_component.getWidth() - 5.0f;
			this.node_y = this.node_component.getHeight() / 2.0f - 5.0f + this.node_component.getY();
			break;
		}
		case 6: {
			this.node_x = this.node_component.getX() - 5.0f;
			this.node_y = this.node_component.getY() - 5.0f;
			break;
		}
		case 7: {
			this.node_x = this.node_component.getX() + (this.node_component.getWidth() / 2.0f - 5.0f);
			this.node_y = this.node_component.getY() - 5.0f;
			break;
		}
		case 8: {
			this.node_x = this.node_component.getX() + this.node_component.getWidth() - 5.0f;
			this.node_y = this.node_component.getY() - 5.0f;
		}
		}
	}

	public final void updatePosition() {
		this.setPositions();
	}

	public float getX() {
		return this.node_x;
	}

	public float getY() {
		return this.node_y;
	}

	public void set(float x, float y) {
		this.node_x = x;
		this.node_y = y;
		if (this.node_type == 0) {
			this.node_component.set(x - this.node_component.getWidth() / 2.0f, y - this.node_component.getHeight() / 2.0f);
		}
		if (this.node_type == 1) {
			this.node_component.size(this.node_component.getWidth() + (this.node_component.getX() - this.node_x), this.node_component.getHeight() - (this.node_component.getY() + this.node_component.getHeight() - this.node_y));
			this.node_component.set(x, this.node_component.getY());
		}
		if (this.node_type == 2) {
			this.node_component.size(this.node_component.getWidth(), this.node_component.getHeight() - (this.node_component.getY() + this.node_component.getHeight() - this.node_y));
		}
		if (this.node_type == 3) {
			this.node_component.size(this.node_component.getWidth() - (this.node_component.getX() + this.node_component.getWidth() - this.node_x), this.node_component.getHeight() - (this.node_component.getY() + this.node_component.getHeight() - this.node_y));
		}
		if (this.node_type == 4) {
			this.node_component.size(this.node_component.getWidth() + (this.node_component.getX() - this.node_x), this.node_component.getHeight());
			this.node_component.set(x, this.node_component.getY());
		}
		if (this.node_type == 5) {
			this.node_component.size(this.node_component.getWidth() - (this.node_component.getX() + this.node_component.getWidth() - this.node_x), this.node_component.getHeight());
		}
		if (this.node_type == 6) {
			this.node_component.size(this.node_component.getWidth() + (this.node_component.getX() - this.node_x), this.node_component.getHeight() + (this.node_component.getY() - this.node_y));
			this.node_component.set(x, y);
		}
		if (this.node_type == 7) {
			this.node_component.size(this.node_component.getWidth(), this.node_component.getHeight() + (this.node_component.getY() - this.node_y));
			this.node_component.set(this.node_component.getX(), y);
		}
		if (this.node_type == 8) {
			this.node_component.size(this.node_component.getWidth() - (this.node_component.getX() + this.node_component.getWidth() - this.node_x), this.node_component.getHeight() + (this.node_component.getY() - this.node_y));
			this.node_component.set(this.node_component.getX(), y);
		}
		HudNode[] arrhudNode = this.node_component.getNodes();
		int n = arrhudNode.length;
		int n2 = 0;
		while (n2 < n) {
			HudNode node = arrhudNode[n2];
			if (node != null) {
				node.updatePosition();
			}
			++n2;
		}
	}

	public final int getType() {
		return this.node_type;
	}

	public final HudComponent getComponent() {
		return this.node_component;
	}

}
