package jp.tsubakicraft.integration.event;

import org.springframework.context.ApplicationEvent;

public abstract class Event extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private EventType eventType;
	
	public Event(EventType eventType, Object object) {
		super(object);
		this.eventType = eventType;
	}
	
	public EventType getEventType() {
		return eventType;
	}
		
	
	public String toString() {
		return "{eventType: " + eventType.toString() + ", object: " + getSource() + ", timestamp: " + getTimestamp() + "}";
	}
	
	public Object getObject() {
		return getSource();
	}

}
