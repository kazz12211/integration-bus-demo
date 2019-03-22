package jp.tsubakicraft.integration.workflow.event;

public interface WorkflowEventListener {

	boolean canHandleEvent(WorkflowEvent event);
	void handleEvent(WorkflowEvent event);
}
