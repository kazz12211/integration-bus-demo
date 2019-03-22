package jp.tsubakicraft.integration.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.tsubakicraft.integration.bus.OutputChannel;

public class SampleOutputChannel extends OutputChannel {

	private static final Logger LOG = LoggerFactory.getLogger(SampleOutputChannel.class);
	
	public SampleOutputChannel() {
		super(new SampleTransformer());
	}
	
	@Override
	public void onSend(Object data) {
		LOG.info("SampleOutputChannel.onSend() called with data: " + data.toString());
	}

}
