package jp.tsubakicraft.integration.bus;

import jp.tsubakicraft.integration.bus.transformer.Transformer;

public abstract class OutputChannel extends IntegrationChannel {

	public OutputChannel(Transformer transformer) {
		super(transformer);
	}

	public void send(BusMessage message) {
		Object data = message.getEvent().getObject();
		if(this.transformer != null) {
			onSend(this.transformer.transform(data));
		} else {
			onSend(data);
		}
	}
	
	public abstract void onSend(Object data);
	
}
