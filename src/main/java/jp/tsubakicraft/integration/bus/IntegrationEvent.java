package jp.tsubakicraft.integration.bus;

import jp.tsubakicraft.integration.event.Event;
import jp.tsubakicraft.integration.event.EventType;

public class IntegrationEvent extends Event {

	private int direction;
	
	public IntegrationEvent(EventType eventType, Object object, int direction) {
		super(eventType, object);
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
}
