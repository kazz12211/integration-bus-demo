package jp.tsubakicraft.integration.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.tsubakicraft.integration.bus.IntegrationEvent;
import jp.tsubakicraft.integration.event.Event;
import jp.tsubakicraft.integration.event.EventType;
import jp.tsubakicraft.integration.workflow.event.WorkflowEvent;

@RestController
public class MainController implements ApplicationEventPublisherAware {
	private static Logger LOG = LoggerFactory.getLogger(MainController.class);

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
	
	@RequestMapping(value="/events", method=RequestMethod.POST)
	public Event postEvent(@RequestBody Map<String, String>event) {
		EventType et = new EventType(event.get("type"), event.get("subtype"));
		Event e = null;
		if(et.getType().equals("DOCUMENT")) {
			e = new WorkflowEvent(et, event.get("object"));
			LOG.info("Posting WorkflowEvent: " + e.toString());
		} else {
			e = new IntegrationEvent(et, event.get("object"), 2);
			LOG.info("Posting IntegrationEvent: " + e.toString());
		}
		this.publisher.publishEvent(e);
		return e;
	}

}
