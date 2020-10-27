package com.segware.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ByteArrayCodecFactory implements ProtocolCodecFactory {
	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	public ByteArrayCodecFactory() {
		encoder = new ByteArrayEncoder();
		decoder = new ByteArrayDecoder();
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}
}
