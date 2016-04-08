package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import net.driftingcolossus.phonebeats.framework.Screen;

public class SHTProcessor implements InputProcessor{

	private float shell_drag_position_x;
	
	private float shell_drag_position_y;
	
	private boolean shell_drag_down;
	
	private boolean shell_drag_mode_move;
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = Screen.Camera_Main().unproject(new Vector3(screenX, screenY,0));
		for(HudShell hud: SHT.getOpenShellStack()){
			if(hud.inTitleArea((int)pos.x, (int)pos.y)){
				shell_drag_down = true;
				shell_drag_position_x = pos.x;
				shell_drag_position_y = pos.y;
				shell_drag_mode_move = true;
				hud.focus();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		shell_drag_down = false;
		shell_drag_mode_move = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 pos = Screen.Camera_Main().unproject(new Vector3(screenX, screenY,0));
		if(shell_drag_down){
			if(SHT.getFocusedShell() != null){
				if(shell_drag_mode_move){
					SHT.getFocusedShell().translate(-(shell_drag_position_x - pos.x), -(shell_drag_position_y - pos.y));
					shell_drag_position_x = pos.x;
					shell_drag_position_y = pos.y;
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
