package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudProgressBar extends HudWidget{

	private boolean drawBorder;
	
	private ShapeRenderer renderer;

	public HudProgressBar(HudComposite composite, String style) {
		super(composite);
		renderer = new ShapeRenderer();
		if(style.contains(SHT.BORDER)){
			drawBorder = true;
		}
	}

	@Override
	protected void render(SpriteBatch batch) {
		if(drawBorder){
			renderer.begin(ShapeType.Line);
			renderer.rect(widget_x, widget_y, widget_width, widget_height);
			renderer.end();
		}
	}
	
	
	

}
