package jp.tsubakicraft.integration.bus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import jp.tsubakicraft.integration.workflow.event.WorkflowEvent;
import jp.tsubakicraft.integration.workflow.event.WorkflowEventListener;

public class IntegrationBus implements WorkflowEventListener, Runnable {

	private static Logger LOG = LoggerFactory.getLogger(IntegrationBus.class);
	private LinkedList<BusMessage> messageQueue;
	private Thread workerThreads[];
	private boolean running = false;
	private List<InputChannel> inputChannels;
	private List<OutputChannel> outputChannels;
	
	
	public IntegrationBus(int nThreads) {
		messageQueue = new LinkedList<BusMessage>();
		workerThreads = new Thread[nThreads];
		for(int i = 0; i < workerThreads.length; i++) {
			workerThreads[i] = new Thread(this);
		}
		inputChannels = new ArrayList<InputChannel>();
		outputChannels = new ArrayList<OutputChannel>();
	}
	
	public void addInputChannel(InputChannel channel) {
		inputChannels.add(channel);
	}
	
	public void addOutputChannel(OutputChannel channel) {
		outputChannels.add(channel);
	}
	
	public void start() {
		int nThreads = workerThreads.length;
		for(int i = 0; i < workerThreads.length; i++) {
			workerThreads[i].start();
		}
		running = true;
		LOG.info("IntegrationBus started with " + nThreads + " worker threads");
	}
	
	public void stop() {
		this.destroy();
	}
	
	public void destroy() {
		running = false;
		synchronized(messageQueue) {
			messageQueue.notifyAll();
		}
		LOG.info("IntegrationBus stopped");
	}
	
	public boolean isRunning() {
		return running;
	}
	
	@Override
	public boolean canHandleEvent(WorkflowEvent event) {
		return true;
	}

	@Override
	public void handleEvent(WorkflowEvent event) {
		synchronized(messageQueue) {
			messageQueue.add(new BusMessage(event));
			LOG.info("Workflow event " + event.toString() + " pushed to message queue");
			messageQueue.notifyAll();
		}
	}
	
	public void pushIntegrationEvent(final IntegrationEvent event) {
		synchronized(messageQueue) {
			messageQueue.add(new BusMessage(event,event.getDirection()));
			LOG.info("Integration event " + event.toString() + " pushed to message queue");
			messageQueue.notifyAll();
		}
	}

	@Override
	public void run() {
		while(running) {
			BusMessage message = null;
			synchronized(messageQueue) {
				if(messageQueue.size() == 0) {
					try {
						messageQueue.wait();
					} catch (InterruptedException ignore) {
					}
				}
				if(messageQueue.size() != 0) {
					message = messageQueue.removeFirst();
				}
			}
			
			if(message != null) {
				sendMessage(message);
			}
		}
	}
	
	public void sendMessage(BusMessage message) {
		LOG.info("IntegrationBus sending BusMessage: " + message.toString());
		if(message.getDirection() == BusMessage.INCOMING) {
			for(InputChannel channel : inputChannels) {
				channel.receive(message);
			}
		} else {
			for(OutputChannel channel : outputChannels) {
				channel.send(message);
			}
		}
	}
}
