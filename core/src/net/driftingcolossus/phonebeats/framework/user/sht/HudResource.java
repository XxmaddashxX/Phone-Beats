package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.HashMap;

import net.driftingcolossus.phonebeats.framework.user.sht.listeners.HudListener;

public class HudResource {
	
	private final HashMap<String, Object> resource_data = new HashMap<String, Object>();
	
	public final void addListener(int listenerType, HudListener listener){
		
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
	

}
