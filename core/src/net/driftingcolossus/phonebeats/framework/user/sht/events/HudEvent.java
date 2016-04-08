package net.driftingcolossus.phonebeats.framework.user.sht.events;

import java.util.HashMap;

import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;

public class HudEvent {
	
	private final HashMap<String, Object> event_data = new HashMap<String, Object>();
	
	public HudWidget widget;
	
	public int eventType;
	
	public HudEvent(){
		
	}
	public Object getData(String id){
		if(event_data.containsKey(id)){
			return event_data.get(id);
		}
		return null;
	}
	

}
