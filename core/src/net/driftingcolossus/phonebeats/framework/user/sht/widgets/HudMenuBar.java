package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudMenuBar extends HudWidget{

	private ShapeRenderer barRenderer;
	
	public HudMenuBar(HudComposite composite, String style) {
		super(composite, style);
		Rectangle clientArea = composite.getShell().getClientArea();
		setX(clientArea.x);
		setY(clientArea.y + (clientArea.height - SHT.Constants.MENUBAR_DEFAULT_HEIGHT));
		setWidth(clientArea.width);
		setHeight(SHT.Constants.MENUBAR_DEFAULT_HEIGHT);
		barRenderer = new ShapeRenderer();
	}

	@Override
	protected void render(SpriteBatch batch) {
		updateBounds();
		barRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		barRenderer.setColor(Color.LIGHT_GRAY);
		barRenderer.begin(ShapeType.Filled);
		barRenderer.rect(widget_x, widget_y, widget_width, widget_height);
		barRenderer.end();
	}

	@Override
	public void onDispose() {
		barRenderer.dispose();
	}
	public void updateBounds(){
		Rectangle clientArea = this.getComposite().getShell().getClientArea();
		setX(clientArea.x);
		setY(clientArea.y + (clientArea.height - SHT.Constants.MENUBAR_DEFAULT_HEIGHT));
		setWidth(clientArea.width);
		setHeight(SHT.Constants.MENUBAR_DEFAULT_HEIGHT);
	}

	

}
