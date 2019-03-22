package jp.tsubakicraft.integration.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.tsubakicraft.integration.bus.transformer.Transformer;

public class SampleTransformer implements Transformer {

	private static final Logger LOG = LoggerFactory.getLogger(SampleTransformer.class);
	
	@Override
	public Object transform(Object source) {
		LOG.info("SampleTransformer.transform() called with source: " + source.getClass().getName());
		return source;
	}

}
