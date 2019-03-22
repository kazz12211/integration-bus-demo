package jp.tsubakicraft.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import jp.tsubakicraft.integration.workflow.WorkflowManager;

@SpringBootApplication
public class IntegrationBusDemoApplication {

	@Autowired
	WorkflowManager eventManager;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(IntegrationBusDemoApplication.class, args);
		
		IntegrationBusDemoApplication app = ctx.getBean(IntegrationBusDemoApplication.class);
		app.run(args);
	}

	public void run(String[] args) {
		eventManager.start();
	}
}
