package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.HashMap;

public abstract class HudResource {

	private boolean resource_disposed = false;
	
	private final HashMap<String, Object> resource_data = new HashMap<String, Object>();

	private HudEventTable resource_table;
	
	private String resource_style;
	
	public HudResource(String style){
		resource_style = style;
		SHT.initResource(this);
	}
	
	public final void addListener(int listenerType, HudListener listener){
		if(resource_table == null){
			resource_table = new HudEventTable(this);
		}
		resource_table.addListener(listenerType, listener);
	}

	public final void removeListener(HudListener listener){

	}
	public final void setData(String id, Object obj){
		resource_data.put(id, obj);
	}
	public final Object getData(String id){
		if(resource_data.containsKey(id)){
			return resource_data.get(id);
		}
		return null;
	}
	public void pack(){

	}
	public final void dispose(){
		resource_disposed = true;
		onDispose();
	}
	public void onDispose(){
		
	}
	public final boolean isDisposed(){
		return resource_disposed;
	}
	public final String getStyle(){
		return resource_style;
	}
	public void onMouseOver(int x, int y){
		
	}
	public void onTouchDown(int button, int x, int y){
		
	}
	public void onTouchUp(int button, int x, int y){
		
	}
	public void onTouchDrag(int x, int y){
		
	}
	public void onUpdateTick(int tick){
		
	}

}
