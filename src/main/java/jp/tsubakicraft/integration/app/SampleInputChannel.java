package jp.tsubakicraft.integration.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.tsubakicraft.integration.bus.InputChannel;

public class SampleInputChannel extends InputChannel {

	private static final Logger LOG = LoggerFactory.getLogger(SampleInputChannel.class);
	
	public SampleInputChannel() {
		super(new SampleTransformer());
	}
	
	@Override
	public void onReceive(Object data) {
		LOG.info("SampleInputChannel.onReceive() called with data: " + data.toString());
	}

}
