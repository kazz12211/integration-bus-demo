package jp.tsubakicraft.integration.workflow.event;

import jp.tsubakicraft.integration.event.Event;
import jp.tsubakicraft.integration.event.EventType;

public class WorkflowEvent extends Event {
	
	public WorkflowEvent(EventType eventType, Object object) {
		super(eventType, object);
	}
	
}
