package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import net.driftingcolossus.phonebeats.framework.Screen;

public class SHTProcessor implements InputProcessor{

	private float shell_drag_position_x;
	
	private float shell_drag_position_y;
	
	private float shell_drag_size_width;
	
	private float shell_drag_size_height;
	
	private boolean shell_drag_down;
	
	private boolean shell_drag_mode_move;
	
	private boolean shell_drag_mode_size;
	
	private boolean shell_drag_block_x;
	
	private boolean shell_drag_block_y;
	
	private boolean shell_drag_invert_x;
	
	private boolean shell_drag_invert_y;
	
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
			shell_drag_position_x = pos.x;
			shell_drag_position_y = pos.y;
			shell_drag_size_width = hud.getWidth();
			shell_drag_size_height = hud.getHeight();
			if(hud.inTitleArea((int)pos.x, (int)pos.y)){
				shell_drag_down = true;
				shell_drag_mode_move = true;
				hud.focus();
				return true;
			}
			if(hud.inLeftArea((int)pos.x, (int)pos.y)){
				shell_drag_down = true;
				shell_drag_mode_move = true;
				shell_drag_mode_size = true;
				shell_drag_block_y = true;
				hud.focus();
				return true;
			}
			if(hud.inRightArea((int)pos.x, (int)pos.y)){
				shell_drag_down = true;
				shell_drag_mode_size = true;
				shell_drag_block_y = true;
				shell_drag_invert_x = true;
				hud.focus();
				return true;
			}
			if(hud.inBottomArea((int)pos.x, (int)pos.y)){
				shell_drag_down = true;
				shell_drag_mode_size = true;
				shell_drag_mode_move = true;
				shell_drag_block_x = true;
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
		shell_drag_mode_size = false;
		shell_drag_block_x = false;
		shell_drag_block_y = false;
		shell_drag_invert_x = false;
		shell_drag_invert_y = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 pos = Screen.Camera_Main().unproject(new Vector3(screenX, screenY,0));
		if(shell_drag_down){
			if(SHT.getFocusedShell() != null){
				float diffX = shell_drag_position_x - pos.x;
				float diffY = shell_drag_position_y - pos.y;
				if(shell_drag_invert_x){
					diffX = 0 - diffX;
				}
				if(shell_drag_invert_y){
					diffY = 0 - diffY;
				}
				if(shell_drag_mode_move){
					SHT.getFocusedShell().translate(shell_drag_block_x ? 0 : -diffX, shell_drag_block_y ? 0 : -diffY);
					
				}
				if(shell_drag_mode_size){
					SHT.getFocusedShell().translateSize(shell_drag_block_x ? 0 : diffX, shell_drag_block_y ? 0 :  diffY);
				}
				shell_drag_position_x = pos.x;
				shell_drag_position_y = pos.y;
				
			}
		}
		/*
		 * if(shell_drag_down){
			if(SHT.getFocusedShell() != null){
				
				if(shell_drag_mode_move){
					SHT.getFocusedShell().translate(shell_drag_block_x ? 0 : -(shell_drag_position_x - pos.x), shell_drag_block_y ? 0 : -(shell_drag_position_y - pos.y));
					shell_drag_position_x = pos.x;
					shell_drag_position_y = pos.y;
				}
				if(shell_drag_mode_size){
					SHT.getFocusedShell().translateSize(shell_drag_block_x ? 0 : (SHT.getFocusedShell().getWidth() - (pos.x - SHT.getFocusedShell().getX())), shell_drag_block_y ? 0 :  (SHT.getFocusedShell().getHeight() - (pos.y - SHT.getFocusedShell().getY())));
				}
				
			}
		}
		 */
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector3 pos = Screen.Camera_Main().unproject(new Vector3(screenX, screenY,0));
		for(HudShell hud: SHT.getOpenShellStack()){
			if(hud.inRightArea((int)pos.x, (int)pos.y)){
			
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
