package jp.tsubakicraft.integration.bus;

import jp.tsubakicraft.integration.event.Event;

public class BusMessage {
	public static final int OUTGOING = 1;
	public static final int INCOMING = 2;
	
	private Event event;
	private int direction = BusMessage.OUTGOING;
	
	public BusMessage(Event event) {
		this.event = event;
	}
	
	public BusMessage(Event event, int direction) {
		this.event = event;
		this.direction = direction;
	}

	public Event getEvent() {
		return event;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public String toString() {
		return "{event: " + event.toString() + ", direction: " + direction + "}";
	}
}
