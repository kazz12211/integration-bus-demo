package jp.tsubakicraft.integration.workflow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import jp.tsubakicraft.integration.app.SampleInputChannel;
import jp.tsubakicraft.integration.app.SampleOutputChannel;
import jp.tsubakicraft.integration.bus.IntegrationBus;
import jp.tsubakicraft.integration.bus.IntegrationEvent;
import jp.tsubakicraft.integration.workflow.event.WorkflowEvent;
import jp.tsubakicraft.integration.workflow.event.WorkflowEventListener;

@Component
public class WorkflowManager {

	private List<WorkflowEventListener> eventListeners = new ArrayList<WorkflowEventListener>();
	private IntegrationBus bus;

	public WorkflowManager() {
		this.bus = new IntegrationBus(5);
		this.registerEventListener(bus);
	}
	
	public void registerEventListener(WorkflowEventListener listener) {
		this.eventListeners.add(listener);
	}
	
	@EventListener
	public void processWorkflowEvent(final WorkflowEvent event) {
		for(WorkflowEventListener listener: eventListeners) {
			if(listener.canHandleEvent(event)) {
				listener.handleEvent(event);
			}
		}
	}
	
	@EventListener
	public void processIntegrationEvent(final IntegrationEvent event) {
		this.bus.pushIntegrationEvent(event);
	}

	public void start() {
		this.bus.addInputChannel(new SampleInputChannel());
		this.bus.addOutputChannel(new SampleOutputChannel());
		this.bus.start();
	}
}
