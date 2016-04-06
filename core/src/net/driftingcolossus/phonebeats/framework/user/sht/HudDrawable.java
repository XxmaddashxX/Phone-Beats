package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface HudDrawable {

	public void renderShell(ShapeRenderer fillRenderer, ShapeRenderer lineRenderer);
	
	public void renderComponents(SpriteBatch batch);
	
}
