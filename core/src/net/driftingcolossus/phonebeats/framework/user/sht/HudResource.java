package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.HashMap;

public abstract class HudResource {

	private boolean resource_disposed = false;
	
	private final HashMap<String, Object> resource_data = new HashMap<String, Object>();

	private HudEventTable resource_table;
	
	public final void addListener(int listenerType, HudListener listener){
		if(resource_table == null){
			resource_table = new HudEventTable();
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
		SHT.initResource(this);
		onDispose();
	}
	public void onDispose(){
		
	}
	public final boolean isDisposed(){
		return resource_disposed;
	}

}
