package jp.tsubakicraft.integration.bus;

import jp.tsubakicraft.integration.bus.transformer.Transformer;

public abstract class InputChannel extends IntegrationChannel {

	public InputChannel(Transformer transformer) {
		super(transformer);
	}

	public void receive(BusMessage message) {
		Object data = message.getEvent().getObject();
		if(this.transformer != null) {
			onReceive(this.transformer.transform(data));
		} else {
			onReceive(data);
		}
	}

	public abstract void onReceive(Object data);
}
