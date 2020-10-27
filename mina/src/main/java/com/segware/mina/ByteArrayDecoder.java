package com.segware.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ByteArrayDecoder implements ProtocolDecoder {

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		int size = in.limit();
		if (size > 0) {
			byte[] data = new byte[size];
			in.get(data, 0, size);
			out.write(data);
		}
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
