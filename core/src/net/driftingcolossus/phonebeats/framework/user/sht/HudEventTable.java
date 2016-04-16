package net.driftingcolossus.phonebeats.framework.user.sht;

import net.driftingcolossus.phonebeats.framework.user.sht.events.HudEvent;

public class HudEventTable {

	private int[] table_types;
	
	private HudListener[] table_listeners;
	
	private HudResource table_resource;
	
	public HudEventTable(HudResource resource){
		SHT.addTable(this);
	}
	
	protected void addListener(int type, HudListener listener){
		if(table_types == null){
			table_types = new int[1]; 
			table_types[0] = type;
			table_listeners = new HudListener[1];
			table_listeners[0] = listener;
			return;
		}
		if(type == 0 | type == SHT.None | listener == null){
			return;
		}
		int length = table_types.length;
		int[] newInt = new int[length];
		HudListener[] newListener = new HudListener[length];
		for(int i = 0; i < length; i++){
			if(i == length){
				newInt[i] = type;
				newListener[i] = listener;
				continue;
			}
			newInt[i] = table_types[i];
			newListener[i] = table_listeners[i];
		}
		table_types = newInt;
		table_listeners = newListener;
	}
	
	public void sendEvent(HudEvent event){
		for(int i = 0; i < table_types.length; i++){
			if(table_types[i] == event.eventType){
				table_listeners[i].handleEvent(event);
			}
		}
	}
	public HudResource getResource(){
		return table_resource;
	}
}
