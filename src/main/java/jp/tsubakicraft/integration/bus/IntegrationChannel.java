package jp.tsubakicraft.integration.bus;

import jp.tsubakicraft.integration.bus.transformer.Transformer;

public abstract class IntegrationChannel {

	protected Transformer transformer;
		
	public IntegrationChannel(Transformer transformer) {
		this.transformer = transformer;
	}
	
}
